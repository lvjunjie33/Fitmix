package com.business.msg.server.task;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.UserRunStat;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import com.business.core.utils.RunUtil;
import com.business.msg.MsgSubscribeAnnotation;
import com.business.msg.core.RedisConcurrentlyCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by edward on 2017/10/23.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_USER_RUN_STAT_TASK)
public class UserRunStatTask implements RedisConcurrentlyCommand {
    private static final Logger log = LoggerFactory.getLogger(UserRunStatTask.class);
    private static final String YEAR_END  = "--";

    private static Logger logger = LoggerFactory.getLogger(UserRunStatTask.class);

    @Override
    public void execute(String msgId) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Long userRunId = Long.parseLong(message.getMsgBody().toString());

        UserRun userRun = defaultDao.findOne(Query.query(Criteria.where("id").is(userRunId)), UserRun.class, "step", "distance", "calorie", "runTime", "uid", "startTime", "addTime", "bpm","consumeFat");
        if (userRun == null) {
            return;
        }
        Date today = new Date(userRun.getStartTime());

        handUserRun(userRun);
        try {

            //本日汇总
            {
                String todayTime = DateUtil.format(today, "yyyy-MM-dd");
                UserRunStat stat = defaultDao.findOne(Query.query(Criteria.where("uid").is(userRun.getUid()).and("statTime").is(todayTime).and("type").is(UserRunStat.STAT_DAY)), UserRunStat.class);

                handUserRunStat(stat);

                if (stat == null) {
                    stat = new UserRunStat();
                    stat.setUid(userRun.getUid());
                    stat.setSumStep(userRun.getStep());
                    stat.setSumDistance(userRun.getDistance());
                    stat.setSumCalorie(userRun.getCalorie());
                    stat.setRunNum(1);
                    stat.setRunDay(1);
                    stat.setRunTime(userRun.getRunTime());
                    stat.setType(UserRunStat.STAT_DAY);
                    stat.setStatTime(todayTime);
                    stat.setAddTime(System.currentTimeMillis());
                    stat.setModifyTime(userRun.getStartTime());
                    stat.setSumConsumeFat(userRun.getConsumeFat());

                    if (userRun.getDistance() >= 1000 && userRun.getBpm() > 120) {
                        stat.setSumDistanceValid(0L);
                        stat.setRunTimeValid(0L);

                        String pace = RunUtil.pace(userRun.getDistance(), userRun.getRunTime());
                        if (RunUtil.getPaceInt(pace) > 230) {//小于230视为作弊，世界记录为230
                            stat.setSumStepValid(userRun.getStep());

                            stat.setSumDistanceValid(userRun.getDistance());
                            stat.setRunTimeValid(userRun.getRunTime());

                            stat.setSumCalorieValid(userRun.getCalorie());
                            stat.setRunNumValid(1);
                            stat.setRunDayValid(1);


                            stat.setPace(RunUtil.getPaceInt(pace));
                            stat.setLevel(RunUtil.getPaceLevel(pace));
                            stat.setModifyTimeValid(userRun.getStartTime());
                        }
                    }
                    defaultDao.save(stat);
                } else {
                    Update update = Update.update("modifyTime", today.getTime())
                            .inc("sumStep", userRun.getStep())
                            .inc("sumDistance", userRun.getDistance())
                            .inc("sumCalorie", userRun.getCalorie())
                            .inc("runTime", userRun.getRunTime())
                            .inc("runNum", 1)
                            .inc("sumConsumeFat", userRun.getConsumeFat());

                    if (userRun.getDistance() >= 1000 && userRun.getBpm() > 120) {//汇总有效运动数据
                        if (stat.getSumDistanceValid() == null) {
                            stat.setSumDistanceValid(0L);
                        }
                        if (stat.getRunTimeValid() == null) {
                            stat.setRunTimeValid(0L);
                        }

                        String pace = RunUtil.pace(stat.getSumDistanceValid() + userRun.getDistance(), stat.getRunTimeValid() + userRun.getRunTime());
                        if (RunUtil.getPaceInt(pace) > 230) { //小于230视为作弊，世界记录为230
                            update.set("modifyTimeValid", today.getTime())
                                    .inc("sumStepValid", userRun.getStep())
                                    .inc("sumDistanceValid", userRun.getDistance())
                                    .inc("sumCalorieValid", userRun.getCalorie())
                                    .inc("runTimeValid", userRun.getRunTime())
                                    .inc("runNumValid", 1);
                            if (stat.getRunDayValid() == null || stat.getRunDayValid() == 0) {
                                stat.setRunDayValid(1);
                            }
                            update.set("pace", RunUtil.getPaceInt(pace));
                            update.set("level", RunUtil.getPaceLevel(pace));
                        }
                    }
                    defaultDao.modifyFirst(Query.query(Criteria.where("id").is(stat.getId())), update, UserRunStat.class);
                    //2018-08-27 lvjj 修复脂肪燃烧量统计数据
//                    revertConsumeFat(defaultDao,userRun.getUid());
                }
            }

            UserRunStatTask.handleOther(defaultDao, today, userRun);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleOther(DefaultDao defaultDao, Date today, UserRun userRun) {

        //本周汇总
        {
            Set<String> weeks = DateUtil.getWeekElement(today);
            List<UserRunStat> weeksStat = defaultDao.query(Query.query(Criteria.where("uid").is(userRun.getUid()).and("statTime").in(weeks).and("type").is(UserRunStat.STAT_DAY)), UserRunStat.class);

            if (!CollectionUtils.isEmpty(weeksStat)) {
                String weekStatTime = DateUtil.format(DateUtil.getWeekBegin(today), "yyyy-MM-dd");
                UserRunStat oldStat = defaultDao.findOne(Query.query(Criteria.where("uid").is(userRun.getUid()).and("statTime").is(weekStatTime).and("type").is(UserRunStat.STAT_WEEK)), UserRunStat.class, "sumDistanceValid", "modifyTime", "modifyTimeValid", "runTimeValid");

                UserRunStat stat = new UserRunStat();
                stat.setUid(userRun.getUid());
                stat.setRunDay(weeksStat.size());
                stat.setType(UserRunStat.STAT_WEEK);
                stat.setStatTime(weekStatTime);
                stat.setAddTime(System.currentTimeMillis());
                stat.setModifyTime(System.currentTimeMillis());
                handleSummary(weeksStat, oldStat, userRun, stat, defaultDao, today);
            }
        }

        //本月汇总
        {
            String regexMonth = DateUtil.format(DateUtil.getMonthBegin(today), "yyyy-MM-");
            List<UserRunStat> monthsStat = defaultDao.query(Query.query(Criteria.where("uid").is(userRun.getUid()).and("statTime").regex(regexMonth).and("type").is(UserRunStat.STAT_DAY)), UserRunStat.class);

            if (!CollectionUtils.isEmpty(monthsStat)) {
                String monthStatTime = DateUtil.format(DateUtil.getMonthBegin(today), "yyyy-MM-dd");
                UserRunStat oldStat = defaultDao.findOne(Query.query(Criteria.where("uid").is(userRun.getUid()).and("statTime").is(monthStatTime).and("type").is(UserRunStat.STAT_MONTH)), UserRunStat.class, "sumDistanceValid", "modifyTime", "modifyTimeValid", "runTimeValid");

                UserRunStat stat = new UserRunStat();
                stat.setUid(userRun.getUid());
                stat.setRunDay(monthsStat.size());
                stat.setType(UserRunStat.STAT_MONTH);
                stat.setStatTime(monthStatTime);
                stat.setAddTime(System.currentTimeMillis());
                stat.setModifyTime(System.currentTimeMillis());
                handleSummary(monthsStat, oldStat, userRun, stat, defaultDao, today);
            }
        }

        //本年汇总
        {

            String regexYear = DateUtil.format(today, "yyyy-");
            List<UserRunStat> monthsStat = defaultDao.query(Query.query(Criteria.where("uid").is(userRun.getUid()).and("statTime").regex(regexYear).and("type").is(UserRunStat.STAT_MONTH)), UserRunStat.class);

            if (!CollectionUtils.isEmpty(monthsStat)) {
                String yearStatTime = DateUtil.format(today, "yyyy") + YEAR_END;
                UserRunStat oldStat = defaultDao.findOne(Query.query(Criteria.where("uid").is(userRun.getUid()).and("statTime").is(yearStatTime).and("type").is(UserRunStat.STAT_YEAR)), UserRunStat.class, "sumDistanceValid", "modifyTime", "modifyTimeValid", "runTimeValid");

                UserRunStat stat = new UserRunStat();
                stat.setUid(userRun.getUid());
                stat.setRunDay(monthsStat.size());
                stat.setType(UserRunStat.STAT_YEAR);
                stat.setStatTime(yearStatTime);
                stat.setAddTime(System.currentTimeMillis());
                stat.setModifyTime(System.currentTimeMillis());
                handleSummary(monthsStat, oldStat, userRun, stat, defaultDao, today);
            }
        }


        //全部汇总
        {
            List<UserRunStat> monthsStat = defaultDao.query(Query.query(Criteria.where("uid").is(userRun.getUid()).and("type").is(UserRunStat.STAT_YEAR)), UserRunStat.class);

            if (!CollectionUtils.isEmpty(monthsStat)) {
                String sumStatTime = YEAR_END;
                UserRunStat oldStat = defaultDao.findOne(Query.query(Criteria.where("uid").is(userRun.getUid()).and("statTime").is(sumStatTime).and("type").is(UserRunStat.STAT_SUM)), UserRunStat.class, "sumDistanceValid", "modifyTime", "modifyTimeValid", "runTimeValid");

                UserRunStat stat = new UserRunStat();
                stat.setUid(userRun.getUid());
                stat.setRunDay(monthsStat.size());
                stat.setType(UserRunStat.STAT_SUM);
                stat.setStatTime(sumStatTime);
                stat.setAddTime(System.currentTimeMillis());
                stat.setModifyTime(System.currentTimeMillis());
                handleSummary(monthsStat, oldStat, userRun, stat, defaultDao, today);
            }
        }
        defaultDao.modifyMore(Query.query(Criteria.where("uid").is(userRun.getUid()).and("startTime").gte(DateUtil.getDayBegin(today).getTime()).lte(DateUtil.getDayEnd(today).getTime())),
                Update.update("isStat", UserRun.STAT_TRUE), UserRun.class);

    }

    /**
     * 汇总每日数据
     *
     * @param targetStats 目标数据
     * @param oldStat 历史的统计数据
     * @param userRun 运动数据
     * @param stat 汇总数据
     * @param today 日期
     */
    public static void handleSummary(List<UserRunStat> targetStats, UserRunStat oldStat, UserRun userRun, UserRunStat stat, DefaultDao defaultDao, Date today) {
        handUserRunStat(stat);
        {
            for (UserRunStat todayStat : targetStats) {
                handUserRunStat(todayStat);
                stat.setSumStep(todayStat.getSumStep() + stat.getSumStep());
                stat.setSumDistance(todayStat.getSumDistance() + stat.getSumDistance());
                stat.setSumCalorie(todayStat.getSumCalorie() + stat.getSumCalorie());
                stat.setRunNum(todayStat.getRunNum() + stat.getRunNum());
                stat.setRunTime(todayStat.getRunTime() + stat.getRunTime());
                stat.setSumConsumeFat(todayStat.getSumConsumeFat()+stat.getSumConsumeFat());


                stat.setSumStepValid(todayStat.getSumStepValid() + stat.getSumStepValid());
                stat.setSumDistanceValid(todayStat.getSumDistanceValid() + stat.getSumDistanceValid());
                stat.setRunTimeValid(todayStat.getRunTimeValid() + stat.getRunTimeValid());

                stat.setSumCalorieValid(todayStat.getSumCalorieValid() + stat.getSumCalorieValid());
                stat.setRunNumValid(todayStat.getRunNumValid() + stat.getRunNumValid());
                stat.setRunDayValid(todayStat.getRunDayValid() + stat.getRunDayValid());
                stat.setModifyTimeValid(userRun.getStartTime());
            }
            if (stat.getSumDistanceValid() == null) {
                stat.setSumDistanceValid(0L);
            }
            if (stat.getRunTimeValid() == null) {
                stat.setRunTimeValid(0L);
            }

            //计算配速
            String pace = RunUtil.pace(stat.getSumDistanceValid(), stat.getRunTimeValid());
            stat.setPace(RunUtil.getPaceInt(pace));
            stat.setLevel(RunUtil.getPaceLevel(pace));
        }
        if (oldStat == null) {
            defaultDao.save(stat);
        } else {
            Update update = Update.update("modifyTime", today.getTime())
                    .set("sumStep", stat.getSumStep())
                    .set("sumDistance", stat.getSumDistance())
                    .set("sumCalorie", stat.getSumCalorie())
                    .set("runTime", stat.getRunTime())
                    .set("runNum", stat.getRunNum())
                    .set("runDay", stat.getRunDay())
                    .set("sumConsumeFat", stat.getSumConsumeFat());
            if (userRun.getDistance() >= 1000 && userRun.getBpm() > 120 && stat.getPace() > 230) {//汇总有效运动数据 //小于230视为作弊，世界记录为230
                update.set("modifyTimeValid", stat.getModifyTimeValid())
                        .set("sumStepValid", stat.getSumStepValid())
                        .set("sumDistanceValid", stat.getSumDistanceValid())
                        .set("sumCalorieValid", stat.getSumCalorieValid())
                        .set("runTimeValid", stat.getRunTimeValid())
                        .set("runNumValid", stat.getRunNumValid())
                        .set("runDayValid", stat.getRunDayValid())
                        .set("pace", stat.getPace())
                        .set("level", stat.getLevel());
            }
            //配速为0默认青铜（小于230视为作弊，世界记录为230）
            if(stat.getPace() ==0){
                update.set("level", 1);
            }
            defaultDao.modifyFirst(Query.query(Criteria.where("id").is(oldStat.getId())), update, UserRunStat.class);
        }

    }

    public static void handUserRun(UserRun userRun) {
        if (userRun.getStep() == null) {
            userRun.setStep(0L);
        }
        if (userRun.getDistance() == null) {
            userRun.setDistance(0L);
        }
        if (userRun.getCalorie() == null) {
            userRun.setCalorie(0L);
        }
        if (userRun.getRunTime() == null) {
            userRun.setRunTime(0L);
        }
        if (userRun.getConsumeFat() == null) {
            userRun.setConsumeFat(0D);
        }
    }

    public static void handUserRunStat(UserRunStat stat) {
        if (stat == null) {
            return;
        }
        if (stat.getSumStep() == null) {
            stat.setSumStep(0L);
        }
        if (stat.getSumDistance() == null) {
            stat.setSumDistance(0L);
        }
        if (stat.getSumCalorie() == null) {
            stat.setSumCalorie(0L);
        }
        if (stat.getRunNum() == null) {
            stat.setRunNum(0);
        }
        if (stat.getRunTime() == null) {
            stat.setRunTime(0L);
        }
        if (stat.getSumConsumeFat() == null) {
            stat.setSumConsumeFat(0D);
        }
        if (stat.getSumStepValid() == null) {
            stat.setSumStepValid(0L);
        }
        if (stat.getSumDistanceValid() == null) {
            stat.setSumDistanceValid(0L);
        }
        if (stat.getRunTimeValid() == null) {
            stat.setRunTimeValid(0L);
        }
        if (stat.getSumCalorieValid() == null) {
            stat.setSumCalorieValid(0L);
        }
        if (stat.getRunNumValid() == null) {
            stat.setRunNumValid(0);
        }
        if (stat.getRunDayValid() == null) {
            stat.setRunDayValid(0);
        }
        if (stat.getModifyTimeValid() == null) {
            stat.setModifyTimeValid(0L);
        }
    }

    public static void revertConsumeFat(DefaultDao defaultDao,Integer uid) {
        User user = null;
        List<UserRun> userRunList = null;
            user = defaultDao.findOne(Query.query(Criteria.where("id").is(uid)), User.class, "id", "name");
            if (user != null) {
                //根据用户id获取所有运动记录
                userRunList = defaultDao.query(Query.query(Criteria.where("uid").is(user.getId())),
                        UserRun.class, "step", "distance", "calorie", "runTime", "uid", "startTime", "addTime", "bpm", "consumeFat");
            }
            if (user != null && userRunList != null) {
                if (userRunList.size() > 0) {
                    log.error("用户ID：" + user.getId() + "===修复数据开始");
                    long startTime = System.currentTimeMillis();
                    //循环该用户所有运动数据
                    for (int x = 0; x < userRunList.size(); x++) {
                        //统计每日
                        revertDay(defaultDao,user, userRunList.get(x));
                    }
                    long endTime = System.currentTimeMillis();
                    log.error("用户ID：" + user.getId() + "===修复数据结束,总耗时：" + (endTime - startTime));
                }
            }
    }

    public static void revertDay(DefaultDao defaultDao,User user, UserRun userRun) {
        List<UserRun> userRunListSum = null;
        double consumeFat = 0;
        double consumeFatSum = 0;
        Date today = new Date(userRun.getStartTime());
        Date dayBegin = DateUtil.getDayBegin(today);
        Date dayEnd = DateUtil.getDayEnd(today);
        userRunListSum = defaultDao.query(Query.query(Criteria.where("uid").is(user.getId()).and("startTime").gte(dayBegin.getTime()).lte(dayEnd.getTime())),
                UserRun.class, "step", "distance", "calorie", "runTime", "uid", "startTime", "addTime", "bpm", "consumeFat");
        for (int x = 0; x < userRunListSum.size(); x++) {
            if (userRunListSum.get(x).getConsumeFat() != null) {
                //获取脂肪燃烧量
                consumeFat = userRunListSum.get(x).getConsumeFat();
                //汇总脂肪燃烧量
                consumeFatSum = consumeFatSum + consumeFat;
            }
        }
        //更新该用户汇总数据类型为全部
        Update update = Update.update("sumConsumeFat", consumeFatSum);
        defaultDao.modifyFirst(Query.query(Criteria.where("uid").is(user.getId()).and("type").is(UserRunStat.STAT_DAY).and("statTime").is(DateUtil.format(dayBegin, "yyyy-MM-dd"))), update, UserRunStat.class);
    }

}
