package com.business.msg.server.task;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.club.ClubMember;
import com.business.core.entity.club.ClubRanking;
import com.business.core.entity.club.ClubUserRunStat;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.UserRun;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.club.ClubCoreDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.msg.MsgSubscribeAnnotation;
import com.business.msg.core.RedisConcurrentlyCommand;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by edward on 2017/10/30.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_CLUB_USER_RUN_RANK_TASK)
public class ClubUserRunRankStatTask implements RedisConcurrentlyCommand {
    @Override
    public void execute(String msgId) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Long userRunId = Long.parseLong(message.getMsgBody().toString());
        UserRun userRun = defaultDao.findOne(Query.query(Criteria.where("id").is(userRunId)), UserRun.class);
        handleClubStatInfo(userRun);
    }

    /**
     * 统计俱乐部用户运动对俱乐部相关统计的数据影响
     *
     * 周：每一周，只会有一条数据，如果没有运动则 空
     *         每周的数据累加，新的一周重新产生新的数据
     *
     * 月:跟周一致
     *
     * 日:每天累加
     */
    public static void handleClubStatInfo(UserRun userRun) {

        ClubCoreDao clubCoreDao = BeanManager.getBean(ClubCoreDao.class);

        /// 天
        long dayBeginTime = DateUtil.getDayBegin(new Date()).getTime();
        long dayEndTime = DateUtil.getDayEnd(new Date()).getTime();

        /// 周
        long weekBeginTime = DateUtil.getDayBegin(DateUtil.getWeekBegin(new Date())).getTime();
        long weekEndTime = DateUtil.getDayEnd(DateUtil.getWeekEnd(new Date())).getTime();

        /// 月
        long monthBeginTime =  DateUtil.getDayBegin(DateUtil.getMonthBegin(new Date())).getTime();
        long monthEndTime = DateUtil.getDayEnd(DateUtil.getMonthEnd(new Date())).getTime();

        Integer uid = userRun.getUid();
        Long runTime = userRun.getRunTime();
        Long distance = userRun.getDistance();
        Long calorie = userRun.getCalorie();
        Long step = userRun.getStep();


        long runEndTime = userRun.getEndTime();

        //是今天
        boolean isDay = (dayBeginTime < runEndTime && dayEndTime > runEndTime);
        //是本周
        boolean isWeek = (weekBeginTime < runEndTime && weekEndTime > runEndTime);
        //是本月
        boolean isMonth = (monthBeginTime < runEndTime && monthEndTime > runEndTime);

        {/// 俱乐部成员运动数据累计 排行
            //日
            ClubUserRunStat dayStat = clubCoreDao.findOneClubUserRunStatByUidAndTypeAndAddTime(uid, ClubUserRunStat.TYPE_DAY, dayBeginTime, dayEndTime);
            if (null == dayStat && isDay) {
                clubCoreDao.insertClubUserRunStat(buildClubUserRunStat(uid, runTime, distance, calorie, step, ClubUserRunStat.TYPE_DAY));
            } else if (isDay) {
                Update update = new Update().inc("runTime", runTime).inc("distance", distance).set("calorie", calorie).set("step", step);
                clubCoreDao.updateClubUserRunStatById(dayStat.getId(), update);
            }
            //周
            ClubUserRunStat weekStat = clubCoreDao.findOneClubUserRunStatByUidAndTypeAndAddTime(userRun.getUid(), ClubUserRunStat.TYPE_WEEK, weekBeginTime, weekEndTime);
            if (null == weekStat && isWeek) {
                clubCoreDao.insertClubUserRunStat(buildClubUserRunStat(uid, runTime, distance, calorie, step, ClubUserRunStat.TYPE_WEEK));
            } else if (isWeek) {
                Update update = new Update().inc("runTime", runTime).inc("distance", distance).set("calorie", calorie).set("step", step);
                clubCoreDao.updateClubUserRunStatById(weekStat.getId(), update);
            }
            //月
            ClubUserRunStat monthStat = clubCoreDao.findOneClubUserRunStatByUidAndTypeAndAddTime(userRun.getUid(), ClubUserRunStat.TYPE_MONTH, monthBeginTime, monthEndTime);
            if (null == monthStat && isMonth) {
                clubCoreDao.insertClubUserRunStat(buildClubUserRunStat(uid, runTime, distance, calorie, step, ClubUserRunStat.TYPE_MONTH));
            } else if (isMonth) {
                Update update = new Update().inc("runTime", runTime).inc("distance", distance).set("calorie", calorie).set("step", step);
                clubCoreDao.updateClubUserRunStatById(monthStat.getId(), update);
            }
        }

        {
            /// 俱乐部总用户运动累计 统计
            List<ClubMember> clubMemberList = clubCoreDao.findClubMemberByUidAndType(uid, null, "clubId");
            Set<Long> clubIds = CollectionUtil.buildSet(clubMemberList, Long.class, "clubId");

            //日
            for (Long clubId: clubIds) {
                ClubRanking clubRanking = clubCoreDao.findClubRankingByClubIdAndTypeAndAddTime(clubId, ClubUserRunStat.TYPE_DAY, dayBeginTime, dayEndTime);
                if (null == clubRanking && isDay) {
                    ClubRanking clubRankingWeekNew = buildClubRanking(clubId, runTime, distance, calorie, step, ClubUserRunStat.TYPE_DAY);
                    clubCoreDao.insertClubRanking(clubRankingWeekNew);
                } else if (isDay) {
                    Update update = new Update().inc("runTime", runTime).inc("distance", distance).inc("calorie", calorie).inc("step", step);
                    clubCoreDao.updateClubRankingById(clubRanking.getId(),update);
                }
            }
            //周
            for (Long clubId: clubIds) {
                ClubRanking clubRanking = clubCoreDao.findClubRankingByClubIdAndTypeAndAddTime(clubId, ClubUserRunStat.TYPE_WEEK, weekBeginTime, weekEndTime);
                if (null == clubRanking && isWeek) {
                    ClubRanking clubRankingWeekNew = buildClubRanking(clubId, runTime, distance, calorie, step, ClubUserRunStat.TYPE_WEEK);
                    clubCoreDao.insertClubRanking(clubRankingWeekNew);
                } else if (isWeek) {
                    Update update = new Update().inc("runTime", runTime).inc("distance", distance).inc("calorie", calorie).inc("step", step);
                    clubCoreDao.updateClubRankingById(clubRanking.getId(),update);
                }
            }
            //月
            for (Long clubId: clubIds) {
                ClubRanking clubRanking = clubCoreDao.findClubRankingByClubIdAndTypeAndAddTime(clubId, ClubUserRunStat.TYPE_MONTH, monthBeginTime, monthEndTime);
                if (null == clubRanking && isMonth) {
                    ClubRanking clubRankingWeekNew = buildClubRanking(clubId, runTime, distance, calorie, step, ClubUserRunStat.TYPE_MONTH);
                    clubCoreDao.insertClubRanking(clubRankingWeekNew);
                } else if (isMonth) {
                    Update update = new Update().inc("runTime", runTime).inc("distance", distance).inc("calorie", calorie).inc("step", step);
                    clubCoreDao.updateClubRankingById(clubRanking.getId(),update);
                }
            }
        }
    }

    /**
     * 构建 运动排行榜
     *
     * @param clubId 俱乐部编号
     * @param runTime 运动时间
     * @param distance 运动距离
     * @param calorie 卡路里
     * @param step 不是
     * @param type 类型：1、日 2、周 3、月
     */
    private static ClubRanking buildClubRanking(long clubId, long runTime, long distance, long calorie, long step, int type) {
        ClubRanking clubRanking = new ClubRanking();
        clubRanking.setClubId(clubId);
        clubRanking.setRunTime(runTime);
        clubRanking.setDistance(distance);
        clubRanking.setCalorie(calorie);
        clubRanking.setStep(step);
        clubRanking.setType(type);
        clubRanking.setAddTime(System.currentTimeMillis());
        clubRanking.setAddTimeStr(DateUtil.formatTimestamp(System.currentTimeMillis(), "yyyy-MM-dd"));
        return clubRanking;
    }

    /**
     * 构建 俱乐部用户运动统计
     *
     * @param uid 用户编号
     * @param runTime 运动时间
     * @param distance 运动距离
     * @param calorie 卡路里
     * @param step 不是
     * @param type 类型：1、日 2、周 3、月
     * @return 俱乐部用户运动统计
     */
    private static ClubUserRunStat buildClubUserRunStat(int uid, long runTime, long distance, long calorie, long step, int type) {
        ClubUserRunStat clubUserRunStat = new ClubUserRunStat();
        clubUserRunStat.setUid(uid);
        clubUserRunStat.setRunTime(runTime);
        clubUserRunStat.setDistance(distance);
        clubUserRunStat.setCalorie(calorie);
        clubUserRunStat.setStep(step);
        clubUserRunStat.setType(type);
        clubUserRunStat.setAddTime(System.currentTimeMillis());
        clubUserRunStat.setAddTimeStr(DateUtil.formatTimestamp(System.currentTimeMillis(), "yyyy-MM-dd"));
        return clubUserRunStat;
    }
}
