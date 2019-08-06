package com.business.work.script;

import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.community.discuss.CategoryMsg;
import com.business.core.entity.logs.SysErrorLog;
import com.business.core.entity.msg.Message;
import com.business.core.entity.runPlan.RunStage;
import com.business.core.entity.runPlan.Stages;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.UserRunStat;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.*;

import com.business.work.base.support.BaseControllerSupport;
import com.business.work.stat.StatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 *
 * 所有的修复、迁移、临时统计等之类的脚本都写在这儿
 *
 * Created by edward on 2017/11/13.
 */
@Controller
@RequestMapping
public class ScriptController extends BaseControllerSupport {

    private static final Logger log = LoggerFactory.getLogger(ScriptController.class);


    private static final String YEAR_END = "--";
    @Autowired
    private DefaultDao defaultDao;
    @Autowired
    private StatService statService;

    /**
     * 迁移话题消息 已经完成
     *
     * @param bId 开始编号
     * @param eId 结束编号
     */
    @RequestMapping("move/theme/to/msg")
    public void moveCategoryMsgToMessage(Long bId, Long eId) {
        if (true) {
            return;
        }
        for (; bId <= eId; bId++) {
            log.error("id : " + bId);
            CategoryMsg categoryMsg = defaultDao.findById(CategoryMsg.class, bId);
            if (categoryMsg == null) {
                continue;
            }

            Message message = defaultDao.findOne(Query.query(Criteria.where("msgBody.id").is(categoryMsg.getId().toString())), Message.class);
            if (message != null) {
                continue;
            }

            Map<String, String> body = new HashMap<>();
            body.put("targetUid", categoryMsg.getTargetUId().toString());
            body.put("fromUid", categoryMsg.getFromUId().toString());

            //迁移
            {
                Message newMsg = new Message();
                //消息类型
                if (CategoryMsg.TYPE_THEME_ANSWER.equals(categoryMsg.getType())) {
                    newMsg.setSelectChannel(MsgConstants.CHANNEL_THEME_ANSWER);
                    body.put("themeId", categoryMsg.getTargetId().toString());
                } else if (CategoryMsg.TYPE_THEME_DISCUSS.equals(categoryMsg.getType())) {
                    newMsg.setSelectChannel(MsgConstants.CHANNEL_ANSWER_DISCUSS);
                    body.put("themeId", categoryMsg.getTargetId().toString());
                }

                newMsg.setAddTime(categoryMsg.getAddTime());
                newMsg.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
                newMsg.setMsgBody(body);
                defaultDao.save(newMsg);
            }
        }
    }

    /**
     * 密码密文 已经完成
     *
     * @param bId
     * @param eId
     */
    @RequestMapping("pwd/md5")
    public void passwordMd5(Integer bId, Integer eId) {
        if (true) {
            return;
        }
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        for (; bId <= eId; bId++) {
            User user = defaultDao.findById(User.class, bId, "password");
            if (user == null) {
                continue;
            }
            if (!StringUtils.isEmpty(user.getPassword())) {
                String newPwd = MD5Util.MD5Encode(user.getPassword(), MD5Util.CHARSET_NAME_UTF);
                user.setNewPwd(newPwd);
                defaultDao.modifyFirst(Query.query(Criteria.where("id").is(user.getId())), Update.update("newPwd", user.getNewPwd()), User.class);
            }
            log.error(user.getId() + " == " + user.getPassword() + " === " + user.getNewPwd());
        }
    }

    //====================================修复训练计划 begin================================================

    @RequestMapping("restore/plan")
    public void restoreUserRunPlan(Integer uid) {
        if (true) {
            return;
        }

        UserRunPlan userRunPlan = defaultDao.findOne(Query.query(Criteria.where("id").is(18116).and("uid").is(uid)), UserRunPlan.class);
        for (Stages stages : userRunPlan.getStagesLists()) {
            if ("2017-09-25".equals(stages.getDateTime())) {
                stages.setState(Stages.STATE_SUCCESS);
            }
        }
        defaultDao.modifyFirst(Query.query(Criteria.where("id").is(userRunPlan.getId())), Update.update("stagesLists", userRunPlan.getStagesLists()), UserRunPlan.class);
    }

    //====================================修复训练计划 begin================================================


    /**
     * 修复用户身高数据
     */
    @RequestMapping("/restore/user/height")
    public void restoreUserHeight() {
        defaultDao.modifyMore(Query.query(Criteria.where("height").gt(300).and("gender").in(1, null, 3)), Update.update("height", 170), User.class);
        defaultDao.modifyMore(Query.query(Criteria.where("height").gt(300).and("gender").in(2)), Update.update("height", 165), User.class);
    }


//    @RequestMapping("/restore/a")
    public void a() {
        User user = null;
        List<UserRun> userRunList = null;
        for(int i=2000000;i<4500000;i++) {
            user = defaultDao.findOne(Query.query(Criteria.where("id").is(i)), User.class, "id", "name");
            if (user != null) {
                //根据用户id获取所有运动记录
                userRunList = defaultDao.query(Query.query(Criteria.where("uid").is(user.getId())),
                        UserRun.class, "step", "distance", "calorie", "runTime", "uid", "startTime", "addTime", "bpm", "consumeFat");
            }


            if (user != null && userRunList != null) {
                if (userRunList.size() > 0) {
                    log.error("用户ID：" + user.getId() + "===修复数据开始");
                    long startTime = System.currentTimeMillis();
//                    //统计所有
//                    revertAll(user,userRunList);
//                    //统计每年
//                    revertYear(user,userRunList);
//                    //统计每月
//                    revertMonth(user,userRunList);
//                    //统计每周
//                    revertWeek(user,userRunList);
                    //循环该用户所有运动数据
                    for (int x = 0; x < userRunList.size(); x++) {
                        //统计每日
                        revertDay(user, userRunList.get(x));
                        Date today = new Date(userRunList.get(x).getStartTime());
                        //统计周、月、年、全部数据
                        handleOther(defaultDao, today, userRunList.get(x));
                    }

                    long endTime = System.currentTimeMillis();
                    log.error("用户ID：" + user.getId() + "===修复数据结束,总耗时：" + (endTime - startTime));
                }
            }
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
            if(userRun.getDistance()!=null && userRun.getBpm()!=null){
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
    public void revertDay(User user, UserRun userRun) {
        List<UserRun> userRunListSum = null;
//        for(int i=0;i<userRunList.size();i++){
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
        //四舍五入，保留两位小数
//       consumeFatSum=consumeFatSum.setScale(2, BigDecimal.ROUND_HALF_UP);
        //更新该用户汇总数据类型为全部
        Update update = Update.update("sumConsumeFat", consumeFatSum);
        defaultDao.modifyFirst(Query.query(Criteria.where("uid").is(user.getId()).and("type").is(UserRunStat.STAT_DAY).and("statTime").is(DateUtil.format(dayBegin, "yyyy-MM-dd"))), update, UserRunStat.class);
//        }
    }

    public void revertWeek(User user, List<UserRun> userRunList) {
        List<UserRun> userRunListSum = null;
        for (int i = 0; i < userRunList.size(); i++) {
            double consumeFat = 0;
            double consumeFatSum = 0;
            Date today = new Date(userRunList.get(i).getStartTime());
            Date weekBegin = DateUtil.getWeekBeginTime(today);
            Date weekEnd = DateUtil.getWeekEndTime(today);
            userRunListSum = defaultDao.query(Query.query(Criteria.where("uid").is(user.getId()).and("startTime").gte(weekBegin.getTime()).lte(weekEnd.getTime())),
                    UserRun.class, "step", "distance", "calorie", "runTime", "uid", "startTime", "addTime", "bpm", "consumeFat");
            for (int x = 0; x < userRunListSum.size(); x++) {
                if (userRunListSum.get(x).getConsumeFat() != null) {
                    //获取脂肪燃烧量
                    consumeFat = userRunListSum.get(x).getConsumeFat();
                    //汇总脂肪燃烧量
                    consumeFatSum = consumeFatSum + consumeFat;
                }
            }
            //四舍五入，保留两位小数
//                consumeFatSum=consumeFatSum.setScale(2, BigDecimal.ROUND_HALF_UP);
            //更新该用户汇总数据类型为全部
            Update update = Update.update("sumConsumeFat", consumeFatSum);
            defaultDao.modifyFirst(Query.query(Criteria.where("uid").is(user.getId()).and("type").is(UserRunStat.STAT_WEEK).and("statTime").is(DateUtil.format(weekBegin, "yyyy-MM-dd"))), update, UserRunStat.class);
        }
    }

    public void revertMonth(User user, List<UserRun> userRunList) {
        List<UserRun> userRunListSum = null;
        for (int i = 0; i < userRunList.size(); i++) {
            double consumeFat = 0;
            double consumeFatSum = 0;
            Date today = new Date(userRunList.get(i).getStartTime());
            Date monthBegin = DateUtil.getMonthBegin(today);
            Date monthEnd = DateUtil.getMonthEnd(today);
            userRunListSum = defaultDao.query(Query.query(Criteria.where("uid").is(user.getId()).and("startTime").gte(monthBegin.getTime()).
                    lte(monthEnd.getTime())), UserRun.class, "step", "distance", "calorie", "runTime", "uid", "startTime", "addTime", "bpm", "consumeFat");
            for (int x = 0; x < userRunListSum.size(); x++) {
                if (userRunListSum.get(x).getConsumeFat() != null) {
                    //获取脂肪燃烧量
                    consumeFat = userRunListSum.get(x).getConsumeFat();
                    //汇总脂肪燃烧量
                    consumeFatSum = consumeFatSum + consumeFat;
                }
            }
            //四舍五入，保留两位小数
//            consumeFatSum=consumeFatSum.setScale(2, BigDecimal.ROUND_HALF_UP);
            //更新该用户汇总数据类型为全部
            Update update = Update.update("sumConsumeFat", consumeFatSum);
            defaultDao.modifyFirst(Query.query(Criteria.where("uid").is(user.getId()).and("type").is(UserRunStat.STAT_MONTH).and("statTime").is(DateUtil.format(monthBegin, "yyyy-MM-dd"))), update, UserRunStat.class);
        }
    }

    public void revertYear(User user, List<UserRun> userRunList) {
        List<UserRun> userRunListSum = null;
        for (int i = 0; i < userRunList.size(); i++) {
            double consumeFat = 0;
            double consumeFatSum = 0;
            Date today = new Date(userRunList.get(i).getStartTime());
            Date yearBegin = DateUtil.getCurrYearFirst(Integer.parseInt(new SimpleDateFormat("yyyy").format(today)));
            Date yearEnd = DateUtil.getCurrYearLast(Integer.parseInt(DateUtil.format(today, "yyyy")));
            userRunListSum = defaultDao.query(Query.query(Criteria.where("uid").is(user.getId()).and("startTime").gte(yearBegin.getTime())
                    .lte(yearEnd.getTime())), UserRun.class, "step", "distance", "calorie", "runTime", "uid", "startTime", "addTime", "bpm", "consumeFat");
            for (int x = 0; x < userRunListSum.size(); x++) {
                if (userRunListSum.get(x).getConsumeFat() != null) {
                    //获取脂肪燃烧量
                    consumeFat = userRunListSum.get(x).getConsumeFat();
                    //汇总脂肪燃烧量
                    consumeFatSum = consumeFatSum + consumeFat;
                }
            }
            //四舍五入，保留两位小数
//            consumeFatSum=consumeFatSum.setScale(2, BigDecimal.ROUND_HALF_UP);
            //更新该用户汇总数据类型为全部
            Update update = Update.update("sumConsumeFat", consumeFatSum);
            defaultDao.modifyFirst(Query.query(Criteria.where("uid").is(user.getId()).and("type").is(UserRunStat.STAT_YEAR).and("statTime").is(DateUtil.format(yearBegin, "yyyy--"))), update, UserRunStat.class);
        }
    }

    public void revertAll(User user, List<UserRun> userRunListSum) {
        double consumeFat = 0;
        double consumeFatSum = 0;
        for (int i = 0; i < userRunListSum.size(); i++) {
            //获取脂肪燃烧量
            if (userRunListSum.get(i).getConsumeFat() != null) {
                //获取脂肪燃烧量
                consumeFat = userRunListSum.get(i).getConsumeFat();
                //汇总脂肪燃烧量
                consumeFatSum = consumeFatSum + consumeFat;
            }
        }
        //四舍五入，保留两位小数
//        consumeFatSum=consumeFatSum.setScale(2, BigDecimal.ROUND_HALF_UP);
        //更新该用户汇总数据类型为全部
        Update update = Update.update("sumConsumeFat", consumeFatSum);
        defaultDao.modifyFirst(Query.query(Criteria.where("uid").is(user.getId()).and("type").is(UserRunStat.STAT_SUM)), update, UserRunStat.class);
    }


    public static void sendUserRunStatTaskMsg(Message message, String _id) {
//        message.setAddTime(System.currentTimeMillis());
//        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
//        message.setSelectChannel(MsgConstants.CHANNEL_USER_RUN_STAT_TASK);
//        message.setMsgBody(userRun.getId());
//        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), _id);
    }

    public static void sendRunRankTaskHandleMsg(Message message, String _id) {
//        Message message = new Message();
//        message.setAddTime(System.currentTimeMillis());
//        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
//        message.setSelectChannel(MsgConstants.CHANNEL_USER_RUN_RANK_TASK);
//        message.setMsgBody(userRun.getId());
//        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), _id);
    }

    public static void sendUserRunTask(Message message, String _id) {
//        Message message = new Message();
//        message.setAddTime(System.currentTimeMillis());
//        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
//        message.setSelectChannel(MsgConstants.CHANNEL_USER_RUN_TASK);
//        message.setMsgBody(userRun.getId());
//        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), _id);
    }

    public static void sendClubUserRunRankTask(Message message, String _id) {
//        Message message = new Message();
//        message.setAddTime(System.currentTimeMillis());
//        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
//        message.setSelectChannel(MsgConstants.CHANNEL_CLUB_USER_RUN_RANK_TASK);
//        message.setMsgBody(userRun.getId());
//        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), _id);
    }

    private static void send(String channel, String msgId) {
        Jedis jedis = RedisUtil.getResourcePubSub();
        jedis.publish(channel, msgId);
        //释放redis
        RedisUtil.returnResource(jedis);
    }

}