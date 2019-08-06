package com.business.scheduler.jobs.stat;

import com.business.core.entity.logs.UserLoginLog;
import com.business.core.entity.stat.UserGrowthLossStat;
import com.business.core.entity.user.User;
import com.business.core.operations.logs.UserLoginLogCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.core.utils.MathUtil;
import com.business.scheduler.base.BaseJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sin on 2015/6/5 0005.
 */
@Service
public class UserGrowthLossJob extends BaseJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserLoginLogCoreDao userLoginLogCoreDao;

    @Override
    public void execute () {
        Date date = new Date();
        logger.info("*** jobs start time:{}  开始..", DateUtil.format(date, "yyyy-MM-dd HH:mm:ss"));
        Long now = System.currentTimeMillis();
        home(date);
        logger.info("*** jobs end time:{}  结束.. 消耗 {} 毫秒", DateUtil.format(date, "yyyy-MM-dd HH:mm:ss"), System.currentTimeMillis() - now);
    }

    public void home(Date date) {
        Date endTime = DateUtil.getHourEnd(DateUtil.addDate(date, Calendar.HOUR_OF_DAY, -1)); // 少一个小时， 23 点 实际统计的是 22点 至 22.59 分的数据
        int hour = DateUtil.getField(endTime, Calendar.HOUR_OF_DAY); // 获取当前世界 小时
        Date beginTime = DateUtil.getDayBegin(endTime);

        if (!CollectionUtils.isEmpty(getRoutingMongoOps().find(Query.query(Criteria.where("strAddTime").is(DateUtil.format(endTime, "yyyy-MM-dd")).and("hour").is(hour)), UserGrowthLossStat.class))) {
            logger.info("*** jobs 终止 统计已存在..");
            return;
        }

        List<UserLoginLog> userLoginLogList = userLoginLogCoreDao.findUserLoginLogByGteAddTimeAndLte(beginTime.getTime(), endTime.getTime()); // 当日登录用户日志
        Set<Integer> uidSet = CollectionUtil.buildSet(userLoginLogList, Integer.class, "uid"); // 当日登录用户 集合

        /// 用户总数量
        Integer userCount = userCoreDao.findUserCount(null, endTime.getTime());
        /// 新增用户数
        Integer newAddUserCount = findNewAddUserCount(beginTime.getTime(), endTime.getTime());
        /// 活跃用户数
        Integer userActiveCount = uidSet.size();
        /// 新用户比例 (不包含)
        Double newUserScale = MathUtil.round(newAddUserCount / (((double)userCount)), 2);

        /// 用户登录次数
        Integer loginTimes = userLoginLogList.size();

        /// 人均启动 (当日，用户平均登录)
        Double eachUserLoginTimes =  MathUtil.round((((double)userLoginLogList.size()) / uidSet.size()), 2);

        /// 添加统计数据
        UserGrowthLossStat userActiveStat = new UserGrowthLossStat();
        userActiveStat.setUserCount(userCount);
        userActiveStat.setNewAddUserCount(newAddUserCount);
        userActiveStat.setUserActiveCount(userActiveCount);
        userActiveStat.setNewUserScale(newUserScale);
        userActiveStat.setLoginTimes(loginTimes);
        userActiveStat.setEachUserLoginTimes(eachUserLoginTimes);
        userActiveStat.setHour(hour);
        userActiveStat.setAddTime(endTime.getTime());
        userActiveStat.setStrAddTime(DateUtil.format(endTime, "yyyy-MM-dd"));
        getRoutingMongoOps().insert(userActiveStat);
    }

    /**
     * 新增用户数 (新增加的应用使用者（按设备），重安装应用不会重复计量。)
     */
    public Integer findNewAddUserCount (Long beginTime, Long endTime) {
        Query query = new Query(Criteria.where("addTime")
                .gte(beginTime)
                .lte(endTime)
                .and("state").is(User.STATE_ACTIVATES));
        return (int)getRoutingMongoOps().count(query, User.class);
    }
}
