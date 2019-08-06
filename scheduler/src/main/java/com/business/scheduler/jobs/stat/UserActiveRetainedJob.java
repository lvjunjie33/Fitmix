package com.business.scheduler.jobs.stat;

import com.business.core.entity.logs.UserLoginLog;
import com.business.core.entity.stat.UserActiveRetainedStat;
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

import java.util.*;

/**
 * Created by sin on 2015/6/8 0008.
 */
@Service
public class UserActiveRetainedJob extends BaseJob {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserLoginLogCoreDao userLoginLogCoreDao;

    @Override
    public void execute () {
        logger.info("*** jobs start time:" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        Integer userTotalCount = userCoreDao.findUserCount(null, null); //用户总数量
//        List<UserLoginLog> userLoginLogList = userLoginLogCoreDao.findUserLoginLogByGteAddTimeAndLte(DateUtil.getDayBegin(new Date()).getTime(), DateUtil.getDayEnd(new Date()).getTime()); // 当日登录用户日志
//        Set<Integer> uidSet = CollectionUtil.buildSet(userLoginLogList, Integer.class, "uid"); // 当日登录用户 集合

        /// 计算一次性用户信息 ,以新增一年以内且超过7天（不含查询当天）的用户为样本，计算其中只在新增当天活跃，后续再无活跃的用户数。以及这些用户占样本的比例。
        Double oneTimeUser = MathUtil.round(((double) findUserByLtLoginTime(DateUtil.addDate(Calendar.DAY_OF_YEAR, -372).getTime())) / userTotalCount, 2);  //计算一次性用户信息

        /// 日活跃用户 % (当日的活跃用户占累计用户比例。)
        Double dayActiveUser = getUserActive(userTotalCount, Calendar.DAY_OF_YEAR, -1, -1, -2, -2);

        /// 周活跃用户 % (截至当日，最近一周（含当日的7天）活跃用户数和这些用户占累计用户的比例。)
        Double weekActiveUser = getUserActive(userTotalCount, Calendar.DAY_OF_YEAR, -1, -1, -8, -2);

        /// 月活跃用户 % (截至当日，最近一月（含当日的30天）活跃用户数和这些用户占累计用户的比例。)
        Double monthActiveUser = getUserActive(userTotalCount, Calendar.DAY_OF_YEAR, -1, -1, -31, -2);

        /// 次日留存率（%）
        Double dayRetained = getUserRetained(userTotalCount, Calendar.DAY_OF_YEAR, Calendar.DAY_OF_YEAR, -1, -1, -2, -2);

        /// 周留存率（%） (7日留存率是指7日内的新增用户中，7日内有使用过应用的用户比例。)
        Double weekRetained = getUserRetained(userTotalCount, Calendar.DAY_OF_YEAR, Calendar.DAY_OF_YEAR, -1, -1, -8, -2);

        ///  月留存率（%） (30日留存率是指30日内的新增用户中，30日内有使用过应用的用户比例。)
        Double monthRetainedUser = getUserRetained(userTotalCount, Calendar.DAY_OF_YEAR, Calendar.DAY_OF_YEAR, -1, -1, -31, -2);

        /// 添加数据
        UserActiveRetainedStat userActiveRetainedStat = new UserActiveRetainedStat();
        userActiveRetainedStat.setOneTimeUser(oneTimeUser);
        userActiveRetainedStat.setDayActiveUser(dayActiveUser);
        userActiveRetainedStat.setWeekActiveUser(weekActiveUser);
        userActiveRetainedStat.setMonthActiveUser(monthActiveUser);
        userActiveRetainedStat.setDayRetained(dayRetained);
        userActiveRetainedStat.setWeekRetained(weekRetained);
        userActiveRetainedStat.setMonthRetained(monthRetainedUser);
        userActiveRetainedStat.setAddTime(System.currentTimeMillis());
        getRoutingMongoOps().insert(userActiveRetainedStat);

        logger.info("*** UserActiveRetainedStat jobs end time:" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 用户活跃
     * @param userTotalCount
     * @param calendar
     * @param afterNumber1
     * @param afterNumber2
     * @param beforeNumber1
     * @param beforeNumber2
     * @return
     */
    public Double getUserActive (Integer userTotalCount, Integer calendar, Integer afterNumber1, Integer afterNumber2, Integer beforeNumber1, Integer beforeNumber2) {
        // 查询开始 日志
        List<UserLoginLog> afterUserLoginLog = userLoginLogCoreDao.findUserLoginLogByGteAddTimeAndLte(DateUtil.getDayBegin(DateUtil.addDate(calendar, afterNumber1)).getTime(), DateUtil.getDayEnd(DateUtil.addDate(calendar, afterNumber2)).getTime());
        // 查询开始 之后日志
        List<UserLoginLog> beforeUserLoginLog = userLoginLogCoreDao.findUserLoginLogByGteAddTimeAndLte(DateUtil.getDayBegin(DateUtil.addDate(calendar, beforeNumber1)).getTime(), DateUtil.getDayEnd(DateUtil.addDate(calendar, beforeNumber2)).getTime());

        Set<Integer> afterUidSet = CollectionUtil.buildSet(afterUserLoginLog, Integer.class, "uid");
        Set<Integer> beforeUidSet = CollectionUtil.buildSet(beforeUserLoginLog, Integer.class, "uid");

        // 非用户活跃数量   (用户活跃数量 = afterUidSet - monthAfterUidSet_1)
        Set<Integer> monthAfterUidSet_1 = new HashSet<>(afterUidSet);
        monthAfterUidSet_1.removeAll(beforeUidSet);
        return MathUtil.round((((double)afterUidSet.size()) - monthAfterUidSet_1.size()) / userTotalCount, 2);
    }

    /**
     * 用户留存
     * @param userTotalCount
     * @param userCalendar
     * @param logCalendar
     * @param userAddTime1
     * @param userAddTime2
     * @param logAddTime1
     * @param logAddTime2
     * @return
     */
    public Double getUserRetained (Integer userTotalCount, Integer userCalendar, Integer logCalendar, Integer userAddTime1, Integer userAddTime2, Integer logAddTime1, Integer logAddTime2) {
        // 新用户集合
        List<User> userList = findNewAddUserCountGteAddTimeAndLte(DateUtil.getDayBegin(DateUtil.addDate(userCalendar, userAddTime1)).getTime(), DateUtil.getDayEnd(DateUtil.addDate(userCalendar, userAddTime2)).getTime());
        Set<Integer> userIds = CollectionUtil.buildSet(userList, Integer.class, "id");

        // 登录日志
        List<UserLoginLog> userLoginLogList = userLoginLogCoreDao.findUserLoginLogByGteAddTimeAndLte(DateUtil.getDayBegin(DateUtil.addDate(logCalendar, logAddTime1)).getTime(), DateUtil.getDayEnd(DateUtil.addDate(logCalendar, logAddTime2)).getTime());
        Set<Integer> userLoginLogUserIds = CollectionUtil.buildSet(userLoginLogList, Integer.class, "uid");

        // 新用户非留存数量  (留存数量 = userIds - monthAfterUidSet_1)
        Set<Integer> monthAfterUidSet_1 = new HashSet<>(userIds);
        monthAfterUidSet_1.removeAll(userLoginLogUserIds);

        return MathUtil.round((((double)userIds.size()) - monthAfterUidSet_1.size()) / userTotalCount, 2);
    }

    /**
     * 新增用户数 (新增加的应用使用者（按设备），重安装应用不会重复计量。)
     */
    public List<User> findNewAddUserCountGteAddTimeAndLte (Long gteAddTime, Long lteAddTime, String...fields) {
        Query query = new Query(Criteria.where("addTime").gte(gteAddTime).lte(lteAddTime).and("state").is(User.STATE_ACTIVATES));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, User.class);
    }

    /**
     * 活跃用户数 (当日有使用应用（至少启动一次）的用户数。)
     */
    public Integer findUserActiveCount () {
        Query query = new Query(Criteria.where("loginTime").gte(DateUtil.getDayBefore(new Date())).and("state").is(User.STATE_ACTIVATES));
        return (int)getRoutingMongoOps().count(query, User.class);
    }

    /**
     *
     * @param lteLoginTime
     * @return
     */
    public Integer findUserByLtLoginTime(Long lteLoginTime) {
        Query query = new Query(Criteria.where("loginTime").lt(lteLoginTime));
        return (int)getRoutingMongoOps().count(query, User.class);
    }

    public static void main (String[] args) {
        Long lo = DateUtil.getDayEnd(DateUtil.addDate(Calendar.DAY_OF_YEAR, -1)).getTime();
//        Date date = DateUtil.addDate(Calendar.DAY_OF_YEAR, 0);
//        System.out.println(DateUtil.getField(date, Calendar.HOUR));
//        System.out.println(date.getTime());
    }
}
