package com.business.app.shop.account;

import com.business.app.mapper.*;
import com.business.app.user.UserDao;
import com.business.app.userRun.UserRunService;
import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.task.TaskFinishDetails;
import com.business.core.entity.task.TaskInfo;
import com.business.core.entity.task.TaskType;
import com.business.core.entity.user.*;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.core.utils.RunLevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by zhangtao on 2016/11/28.
 */
@Service
public class AccountService extends AbstractServiceImpl<Account> {

    @Autowired
    private UserRunService userRunService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountFlowMapper accountFlowMapper;
    @Autowired
    private UserRunStatisticsMapper userRunStatisticsMapper;
    @Autowired
    private RunLevelInfoMapper runLevelInfoMapper;
    @Autowired
    private TaskFinishDetailsMapper taskFinishDetailsMapper;


    @Override
    protected AbstractMapper<Account> getAbstractMapper() {
        return accountMapper;
    }

    /**
     * 更新积分
     *
     * @param uid 用户编号
     * @param taskType 任务类型
     */
    @Transactional
    public AccountFlow updateCoinByTaskType(Integer uid, TaskType taskType) {
        Account account = accountMapper.selectByUid(uid);
        Date nowTime = new Date();
        if (account == null) {
            //插入
            account = new Account(taskType.getCoin(), uid, nowTime.getTime(), nowTime.getTime());
            accountMapper.insertSelective(account);
        } else {
            // 增加金币
            accountMapper.augmentBalance(account.getUid(), taskType.getCoin());
        }

        AccountFlow accountFlow;

        if(TaskInfo.TYPE_HONOR.equals(taskType.getTaskType())) {
            RunLevelInfo runLevelInfo = runLevelInfoMapper.selectByRunLevel(taskType.toString());
            accountFlow = new AccountFlow(taskType.getCoin(), uid, String.format("达到-%s", runLevelInfo.getName()), account.getId(), AccountFlow.FLOW_TYPE_GAIN, nowTime.getTime(), nowTime.getTime());
        } else {
            //更新 积分明细
            accountFlow = new AccountFlow(taskType.getCoin(), uid, String.format("完成-%s", taskType.getDesc()), account.getId(), AccountFlow.FLOW_TYPE_GAIN, nowTime.getTime(), nowTime.getTime());
        }
        accountFlowMapper.insertSelective(accountFlow);
        return accountFlow;
    }

    /**
     * 根据用户Id 获取账号信息
     *
     * @param uid 用户编号
     */
    public Account selectByUid(Integer uid) {
        return accountMapper.selectByUid(uid);
    }

    /**
     * 创建金币账户
     * @param uid 用户编号
     */
    public Integer createAccount(Integer uid) {
        Account account = new Account(0, 0, System.currentTimeMillis(), System.currentTimeMillis());
        account.setUid(uid);
        Integer len = null;
        if(accountMapper.selectByUid(uid)==null){
               len=accountMapper.insertSelective(account);
        }
        return len;
    }


    /**
     * 生成账户， 只执行一次
     */
    @Transactional
    public void init() {
        if(accountMapper.count(null) == 0) {
            List<Account> accountList = new ArrayList<>();
            Long count = userDao.count();
            Integer totalPage = count.intValue() / 1000 + 1;
            for(int i = 1; i <= totalPage; i++) {
                List<User> userList = userDao.findUserByPageNo(i);
                if(userList != null && userList.size() > 0) {
                    for(User user : userList) {
                        Account account = new Account(0, 0, System.currentTimeMillis(), System.currentTimeMillis());
                        account.setUid(user.getId());
                        accountList.add(account);
                    }
                }
                if(accountList.size() > 0) {
                    accountMapper.insertBatch(accountList);
                    accountList.clear();
                }
                System.out.println(String.format("进度 ：%d / %d", i, totalPage));
            }
        }

    }

    /**
     * 跑步累计统计 初始化
     * @param start 开始
     * @param end 结束
     */
    @Transactional
    public void runInit(Integer start, Integer end) {
        // 总时间统计
        Long totalTime = System.currentTimeMillis();
        List<UserRunStatistics> userRunStatisticsList = new ArrayList<>();
        List<AccountFlow> accountFlowList = new ArrayList<>();
        List<TaskFinishDetails> taskFinishDetailsesList = new ArrayList<>();


        // 用户跑步记录
        Map<Integer, Long> map = userRunService.statisticsValidUserRunByUid(start, end);
        // 账号列表
        List<Account> accountList = accountMapper.selectByUidIn(start, end);
        // 根据 uid 组
        Map<Integer, Account> accountMap = CollectionUtil.buildMap(accountList, Integer.class, Account.class, "uid");
        Set<Integer> keySet = map.keySet();
        Integer length = map.keySet().size();
        Integer count = 0;
        for(Integer uid : keySet) {
            Long startTime = System.currentTimeMillis();
            // 跑步等级
            String runLevel = RunLevelUtil.getRunLevel(map.get(uid), UserRunStatistics.TEN_MILE_ABILITY_LEVEL_DEFAULT, UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_DEFAULT);
            UserRunStatistics userRunStatistics = new UserRunStatistics(uid, map.get(uid), UserRunStatistics.TEN_MILE_ABILITY_LEVEL_DEFAULT,
                    UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_DEFAULT, runLevel, System.currentTimeMillis(), System.currentTimeMillis());
            userRunStatisticsList.add(userRunStatistics);

            // 增加对应等级金币
            Account account = accountMap.get(uid);
//                taskInfoService.finishTask(i, runLevel, TaskInfo.TYPE_HONOR, null);
            if(account != null) {
                for (TaskType taskType : TaskType.values()) {
                    if(taskType.toString().equals(runLevel) && TaskInfo.STATUS_NORMAL.equals(taskType.getStatus())) {
                        accountMapper.augmentBalance(uid, taskType.getCoin());
                        //账号浮动 信息
                        AccountFlow accountFlow = new AccountFlow(taskType.getCoin(), uid, String.format("达到-%s", taskType.getDesc()), account.getId(),
                                AccountFlow.FLOW_TYPE_GAIN, System.currentTimeMillis(), System.currentTimeMillis());
                        //完成任务记录
                        TaskFinishDetails taskFinishDetails = new TaskFinishDetails(uid, taskType.getTaskType(), taskType.toString(), null,
                                DateUtil.format(new Date(), "yyyy-MM-dd"), System.currentTimeMillis());
                        taskFinishDetailsesList.add(taskFinishDetails);
                        accountFlowList.add(accountFlow);
                        break;
                    }
                }
            }

            if(userRunStatisticsList.size() >= 1000) {
                userRunStatisticsMapper.insertBatch(userRunStatisticsList);
                userRunStatisticsList.clear();

                accountFlowMapper.insertBatch(accountFlowList);
                accountFlowList.clear();

                taskFinishDetailsMapper.insertBatch(taskFinishDetailsesList);
                taskFinishDetailsesList.clear();

            }
            count++;
            System.out.println(String.format("进度 ：%d %%, 时间：%d ms", (count * 100) / length, System.currentTimeMillis() - startTime));
        }

        if(userRunStatisticsList.size() > 0) {
            userRunStatisticsMapper.insertBatch(userRunStatisticsList);
            accountFlowMapper.insertBatch(accountFlowList);
            taskFinishDetailsMapper.insertBatch(taskFinishDetailsesList);
        }
        System.out.println("执行完毕，花费时间：" + (System.currentTimeMillis() - totalTime) / 1000 + " s");
    }
}
