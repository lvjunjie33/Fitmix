package com.business.app.shop.task;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.context.SystemContextHolder;
import com.business.app.base.excetion.AppBusinessExcetion;
import com.business.app.mapper.RunLevelInfoMapper;
import com.business.app.mapper.TaskFinishDetailsMapper;
import com.business.app.mapper.TaskInfoMapper;
import com.business.app.mapper.UserRunStatisticsMapper;
import com.business.app.shop.account.AccountService;
import com.business.app.shop.liumi.LiumiOrderService;
import com.business.app.userRun.UserRunService;
import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.shop.LiumiOrder;
import com.business.core.entity.task.TaskFinishDetails;
import com.business.core.entity.task.TaskInfo;
import com.business.core.entity.task.TaskType;
import com.business.core.entity.user.AccountFlow;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.UserRunStatistics;
import com.business.core.utils.CodeMessageUtil;
import com.business.core.utils.DateUtil;
import com.business.core.utils.RunLevelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by fenxio on 2016/11/9.
 */
@Service
public class TaskInfoService extends AbstractServiceImpl<TaskInfo> {

    private static final Logger logger = LoggerFactory.getLogger(TaskInfoService.class);

    @Autowired
    private TaskInfoMapper taskInfoMapper;
    @Autowired
    private TaskFinishDetailsMapper taskFinishDetailsMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRunService userRunService;
    @Autowired
    private UserRunStatisticsMapper userRunStatisticsMapper;
    @Autowired
    private RunLevelInfoMapper runLevelInfoMapper;
    @Autowired
    private LiumiOrderService liumiOrderService;

    @Override
    protected AbstractMapper<TaskInfo> getAbstractMapper() {
        return taskInfoMapper;
    }


    /**
     * 完成任务
     * @param uid 用户编号
     * @param taskType 任务类型
     */
    @Transactional
    public AccountFlow finishTask(Integer uid, TaskType taskType) {
        AccountFlow accountFlow;
        TaskFinishDetails taskFinishDetails;
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("taskKey", taskType.toString());
        if(TaskInfo.TYPE_EVERY_DAY.equals(taskType.getTaskType())) {
            //1.先判断是否完成任务
            map.put("finishTime", DateUtil.format(new Date(), "yyyy-MM-dd"));
            taskFinishDetails = taskFinishDetailsMapper.selectTaskFinishDetailsBy(map);
        } else if(TaskInfo.TYPE_ONE_OFF.equals(taskType.getTaskType()) || TaskInfo.TYPE_HONOR.equals(taskType.getTaskType())) {
            taskFinishDetails = taskFinishDetailsMapper.selectTaskFinishDetailsBy(map);
        } else {
            throw new AppBusinessExcetion(CodeConstants.INVALID_TASK_TYPE, CodeMessageUtil.format(CodeConstants.INVALID_TASK_TYPE, SystemContextHolder.get().getLanguage()));
        }

        if (taskFinishDetails == null) {
            taskFinishDetails = new TaskFinishDetails(uid, taskType.getTaskType(),
                    taskType.toString(), "0", DateUtil.format(new Date(), "yyyy-MM-dd"), System.currentTimeMillis());
            Integer result = taskFinishDetailsMapper.insertSelective(taskFinishDetails);
            if (result == 0) {
                throw new AppBusinessExcetion(CodeConstants.HED_FINISH, CodeMessageUtil.format(CodeConstants.HED_FINISH, SystemContextHolder.get().getLanguage()));
            } else {
                // 更新账号积分信息
                accountFlow = accountService.updateCoinByTaskType(uid, taskType);
            }

        } else {
            throw new AppBusinessExcetion(CodeConstants.HED_FINISH, CodeMessageUtil.format(CodeConstants.HED_FINISH, SystemContextHolder.get().getLanguage()));
        }
        return accountFlow;

    }


    /**
     * 修改用户完成状态
     * @param uid 用户编号
     * @param taskKey 任务关键字
     * @param taskType  任务类型
     * @param additionId 附加编号
     */
    @Transactional
    public AccountFlow updateUserTaskStatus(Integer uid, String taskKey, Integer taskType, String additionId) {
        AccountFlow accountFlow = null;
        // 判断任务是否有效
        for(TaskType task : TaskType.values()) {
            if(task.toString().equals(taskKey) && TaskInfo.STATUS_INVALID.equals(task.getStatus())) {
                throw new AppBusinessExcetion(CodeConstants.INVALID_TASK, CodeMessageUtil.format(CodeConstants.INVALID_TASK, SystemContextHolder.get().getLanguage()));
            }
        }

        if (TaskInfo.TYPE_EVERY_DAY.equals(taskType)) {
            if (TaskType.EVERY_DAY_RUN_THREE_MILE.toString().equals(taskKey)) {
                //1.获取跑步记录
                UserRun userRun = null;
                if(additionId != null) {
                    userRun = userRunService.findUserRunByIdAndUid(Integer.parseInt(additionId), uid);
                }
                if (userRun != null && userRun.getDistance() >= 3000) {
                    //2.判断任务是否完成
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("uid", uid);
                    map.put("taskKey", TaskType.EVERY_DAY_RUN_THREE_MILE.toString());
                    map.put("finishTime", DateUtil.formatTimestamp(userRun.getStartTime(), "yyyy-MM-dd"));
                    TaskFinishDetails taskFinishDetails = taskFinishDetailsMapper.selectTaskFinishDetailsBy(map);
                    if (taskFinishDetails == null) {
                        taskFinishDetails = new TaskFinishDetails(uid, TaskType.EVERY_DAY_RUN_THREE_MILE.getTaskType(),
                                TaskType.EVERY_DAY_RUN_THREE_MILE.toString(), "0", DateUtil.formatTimestamp(userRun.getStartTime(), "yyyy-MM-dd"), System.currentTimeMillis());
                        Integer result = taskFinishDetailsMapper.insertSelective(taskFinishDetails);
                        if (result == 0) {
                            throw new AppBusinessExcetion(CodeConstants.HED_FINISH, CodeMessageUtil.format(CodeConstants.HED_FINISH, SystemContextHolder.get().getLanguage()));
                        } else {
                            // 更新账号积分信息
                            accountFlow = accountService.updateCoinByTaskType(uid, TaskType.EVERY_DAY_RUN_THREE_MILE);
                        }
                    } else {
                        throw new AppBusinessExcetion(CodeConstants.HED_FINISH, CodeMessageUtil.format(CodeConstants.HED_FINISH, SystemContextHolder.get().getLanguage()));
                    }
                }
            } else {
                for (TaskType each : TaskType.values()) {
                    if (each.toString().equals(taskKey) && taskType.equals(each.getTaskType())) {
                        accountFlow = finishTask(uid, each);
                    }
                }
            }
        } else if (TaskInfo.TYPE_ONE_OFF.equals(taskType) || TaskInfo.TYPE_HONOR.equals(taskType)) {
            for (TaskType each : TaskType.values()) {
                if (each.toString().equals(taskKey) && taskType.equals(each.getTaskType())) {
                    accountFlow = finishTask(uid, each);
                }
            }
        } else if(TaskInfo.TYPE_SHARE.equals(taskType)) {
            if(TaskType.SHARE_FLOW.toString().equals(taskKey)) {
                //1.判断订单 是否存在
                LiumiOrder liumiOrder = liumiOrderService.selectByOrderNo(additionId);
                if(liumiOrder == null){
                    throw new  AppBusinessExcetion(CodeConstants.NOT_MEET_CONDITIONS, CodeMessageUtil.format(CodeConstants.NOT_MEET_CONDITIONS, SystemContextHolder.get().getLanguage()));
                }
                //2.判断任务是否完成
                HashMap<String, Object> map = new HashMap<>();
                map.put("uid", uid);
                map.put("taskKey", TaskType.SHARE_FLOW.toString());
                map.put("additionId", additionId);
                TaskFinishDetails taskFinishDetails = taskFinishDetailsMapper.selectTaskFinishDetailsBy(map);

                if(taskFinishDetails == null) {
                    taskFinishDetails = new TaskFinishDetails(uid, TaskType.SHARE_FLOW.getTaskType(),
                            TaskType.SHARE_FLOW.toString(), additionId, DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"), System.currentTimeMillis());
                    Integer result = taskFinishDetailsMapper.insertSelective(taskFinishDetails);
                    if (result == 0) {
                        throw new AppBusinessExcetion(CodeConstants.HED_FINISH, CodeMessageUtil.format(CodeConstants.HED_FINISH, SystemContextHolder.get().getLanguage()));
                    } else {
                        // 更新账号积分信息
                        accountFlow = accountService.updateCoinByTaskType(uid, TaskType.SHARE_FLOW);
                    }

                } else {
                    throw new AppBusinessExcetion(CodeConstants.HED_FINISH, CodeMessageUtil.format(CodeConstants.HED_FINISH, SystemContextHolder.get().getLanguage()));
                }

            }
        } else {
            throw new AppBusinessExcetion(CodeConstants.INVALID_TASK_TYPE, CodeMessageUtil.format(CodeConstants.INVALID_TASK_TYPE, SystemContextHolder.get().getLanguage()));
        }

        if(accountFlow == null) {
            throw new AppBusinessExcetion(CodeConstants.NOT_MEET_CONDITIONS, CodeMessageUtil.format(CodeConstants.NOT_MEET_CONDITIONS, SystemContextHolder.get().getLanguage()));
        }

        return accountFlow;
    }



    public void init() {
        Long now = System.currentTimeMillis();
        logger.info("初始化 TaskType 开始...");
        List<TaskInfo> taskInfoList = taskInfoMapper.selectAll();

        for (TaskType taskType : TaskType.values()) {
            for (TaskInfo taskInfo : taskInfoList) {
                if (taskType.toString().equals(taskInfo.getTaskKey())) {
                    taskType.setCoin(taskInfo.getCoin());
                    taskType.setStatus(taskInfo.getStatus());
                }
            }
        }

        logger.info("初始化 TaskType 完成...消耗 {}毫秒", System.currentTimeMillis() - now);
    }

    /**
     * 获取用户任务列表
     * @param uid 用户编号
     */
    public List<TaskInfo> getUserTaskInfo(Integer uid, Integer type) {
        //1. 获取有效任务列表
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", TaskInfo.STATUS_NORMAL);
        map.put("taskType", type);
        List<TaskInfo> list  = taskInfoMapper.selectByParams(map);
        List<TaskInfo> tasks = new ArrayList<>();
        //2. 判读任务是否完成
        for(TaskInfo taskInfo: list) {
            map.clear();
            if(TaskInfo.TYPE_EVERY_DAY.equals(taskInfo.getTaskType())) {
                map.put("taskKey", taskInfo.getTaskKey());
                map.put("finishTime", DateUtil.format(new Date(), "yyyy-MM-dd"));
                map.put("uid", uid);
                List<TaskFinishDetails> taskFinishDetailsList = taskFinishDetailsMapper.selectByParams(map);
                if(taskFinishDetailsList != null && taskFinishDetailsList.size() > 0) {
                    taskInfo.setFinishStatus(TaskInfo.FINISH_STATUS_DONE);
                } else {
                    taskInfo.setFinishStatus(TaskInfo.FINISH_STATUS_UNDONE);
                }
            } else {
                map.put("taskKey", taskInfo.getTaskKey());
                map.put("uid", uid);
                List<TaskFinishDetails> taskFinishDetailsList = taskFinishDetailsMapper.selectByParams(map);
                if(taskFinishDetailsList != null && taskFinishDetailsList.size() > 0) {
                    taskInfo.setFinishStatus(TaskInfo.FINISH_STATUS_DONE);
                } else {
                    taskInfo.setFinishStatus(TaskInfo.FINISH_STATUS_UNDONE);
                }
            }
            if (!taskInfo.getDescription().contains("跑者")) {
                tasks.add(taskInfo);
            }
        }
        return tasks;
    }

    /**
     * 获取单个任务完成状态
     * @param uid 用户编号
     * @param taskKey 任务关键字
     */
    public TaskInfo getUserTaskInfo(Integer uid, String taskKey) {
        TaskInfo taskInfo = taskInfoMapper.selectTaskInfoByTaskKey(taskKey);
        // 判读任务是否完成
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskKey", taskInfo.getTaskKey());
        map.put("finishTime", DateUtil.format(new Date(), "yyyy-MM-dd"));
        map.put("uid", uid);
        List<TaskFinishDetails> taskFinishDetailsList = taskFinishDetailsMapper.selectByParams(map);
        if(taskFinishDetailsList != null && taskFinishDetailsList.size() > 0) {
            taskInfo.setFinishStatus(TaskInfo.FINISH_STATUS_DONE);
        } else {
            taskInfo.setFinishStatus(TaskInfo.FINISH_STATUS_UNDONE);
        }
        return taskInfo;
    }


    /**
     * 用户跑步累计
     * @param bpm bpm
     * @param distance 里程
     * @param runTime 运动时间
     * @param uid 用户编号
     */
    @Transactional
    public Map<String, Object> runLevelStatistics(Integer bpm, Long distance, Long runTime, Integer uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("levelUp", 0);
        if(bpm >= 120 && distance > 1000) {
            UserRunStatistics userRunStatistics = userRunStatisticsMapper.selectByUid(uid);
            if(userRunStatistics == null) {
                userRunStatistics = new UserRunStatistics(uid, 0L, UserRunStatistics.TEN_MILE_ABILITY_LEVEL_DEFAULT,
                        UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_DEFAULT, TaskType.RUN_LEVEL_1.toString(), System.currentTimeMillis(), System.currentTimeMillis());
                userRunStatisticsMapper.insertSelective(userRunStatistics);
            }

            // 判断能力值是否提升
            if(distance >= 10000 && distance < 21500) {
                Integer tenMileAbiltiy = RunLevelUtil.getTenMileAbility(distance, runTime);
                if(userRunStatistics.getTenMileAbility() < tenMileAbiltiy) {
                    userRunStatistics.setTenMileAbility(tenMileAbiltiy);
                }
            } else if(distance >= 21500) {
                Integer halfMarathonAbility = RunLevelUtil.getHalfMarathonAbility(distance, runTime);
                if(userRunStatistics.getHalfMarathonAbility() < halfMarathonAbility) {
                    userRunStatistics.setHalfMarathonAbility(halfMarathonAbility);
                }
            }
            // 获取run level
            String runLevel = RunLevelUtil.getRunLevel(distance + userRunStatistics.getDistance(), userRunStatistics.getTenMileAbility(), userRunStatistics.getHalfMarathonAbility());
            for(TaskType taskType : TaskType.values()) {
                if(taskType.toString().equals(runLevel) && TaskInfo.STATUS_NORMAL.equals(taskType.getStatus())) {
                    // 升级
                    if(!runLevel.equals(userRunStatistics.getRunLevel())) {
                        List<AccountFlow> list = new ArrayList<>();
                        // 判断是否跳级
                        Integer startLevel = RunLevelUtil.getLevel(userRunStatistics.getRunLevel());
                        Integer endLevel = RunLevelUtil.getLevel(runLevel);
                        for(int i = startLevel + 1; i <= endLevel; i++) {
                            list.add(updateUserTaskStatus(uid, "RUN_LEVEL_"+i, TaskInfo.TYPE_HONOR, null));
                        }
                        userRunStatistics.setRunLevel(runLevel);

                        map.put("levelUp", 1);
                        map.put("accountFlowList", list);
                        map.put("runLevelInfo", runLevelInfoMapper.selectByRunLevel(runLevel));
                    }
                    // 更新 信息
                    userRunStatistics.setDistance(distance);
                    userRunStatistics.setModifyTime(System.currentTimeMillis());
                    userRunStatisticsMapper.updateDistanceAndRunLevelById(userRunStatistics);
                    break;
                }
            }
            map.put("userRunStatistics", userRunStatistics);
        }
        return map;
    }

    /**
     * 根据 task key 获取任务信息
     * @param taskKey 任务关键字
     */
    public TaskInfo selectTaskInfoByTaskKey(String taskKey) {
        return taskInfoMapper.selectTaskInfoByTaskKey(taskKey);
    }
}
