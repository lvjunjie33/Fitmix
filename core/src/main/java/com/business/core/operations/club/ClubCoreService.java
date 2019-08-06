package com.business.core.operations.club;

import com.business.core.entity.club.ClubMember;
import com.business.core.entity.club.ClubRanking;
import com.business.core.entity.club.ClubUserRunStat;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by edward on 2016/7/26.
 */
@Service
public class ClubCoreService {

    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserCoreService userCoreService;
    @Autowired
    private ClubCoreDao clubCoreDao;

    /**
     * 俱乐部 排行榜 添加
     * <p>
     *     添加有三种：
     *     周：每一周，只会有一条数据，如果没有运动则 空
     *         每周的数据累加，新的一周重新产生新的数据
     *
     *     月:跟周一致
     *
     *     日:每天累加
     *
     *     注意：日、周 和 月 数据互不影响
     * </p>
     * @param uid 用户编号
     * @param runTime 运动时间
     * @param distance 运动距离
     * @param calorie 卡路里
     */
    public void add(int uid, long runTime, long distance, long calorie, long step) {

        /// 天
        long dayBeginTime = DateUtil.getDayBegin(new Date()).getTime();
        long dayEndTime = DateUtil.getDayEnd(new Date()).getTime();

        /// 周
        long weekBeginTime = DateUtil.getDayBegin(DateUtil.getWeekBegin(new Date())).getTime();
        long weekEndTime = DateUtil.getDayEnd(DateUtil.getWeekEnd(new Date())).getTime();

        /// 月
        long monthBeginTime =  DateUtil.getDayBegin(DateUtil.getMonthBegin(new Date())).getTime();
        long monthEndTime = DateUtil.getDayEnd(DateUtil.getMonthEnd(new Date())).getTime();

        /// 俱乐部成员统计
        appendClubUserRunStat(dayBeginTime, dayEndTime, uid, runTime, distance, calorie, step, ClubUserRunStat.TYPE_DAY);
        appendClubUserRunStat(weekBeginTime, weekEndTime, uid, runTime, distance, calorie, step, ClubUserRunStat.TYPE_WEEK);
        appendClubUserRunStat(monthBeginTime, monthEndTime, uid, runTime, distance, calorie, step, ClubUserRunStat.TYPE_MONTH);

        /// 俱乐部排行统计
        List<ClubMember> clubMemberList = clubCoreDao.findClubMemberByUidAndType(uid, null, "clubId");
        Set<Long> clubIds = CollectionUtil.buildSet(clubMemberList, Long.class, "clubId");
        appendClubRanking(dayBeginTime, dayEndTime, clubIds, runTime, distance, calorie, step, ClubUserRunStat.TYPE_DAY);
        appendClubRanking(weekBeginTime, weekEndTime, clubIds, runTime, distance, calorie, step, ClubUserRunStat.TYPE_WEEK);
        appendClubRanking(monthBeginTime, monthEndTime, clubIds, runTime, distance, calorie, step, ClubUserRunStat.TYPE_MONTH);
    }

    /**
     * 俱乐部用户运动统计 累加
     * <p>
     *     1、存在则更新累加
     *     2、不存在添加
     * </p>
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param uid 用户编号
     * @param runTime 运动时间
     * @param distance 运动距离
     * @param calorie 卡路里
     * @param step 不是
     * @param type 类型：1、日 2、周 3、月
     */
    public void appendClubUserRunStat(long beginTime, long endTime,
                                      int uid, long runTime, long distance, long calorie, long step, int type) {
        /// 有则 更新累加 没有 添加新增
        ClubUserRunStat clubUserRunStat = clubCoreDao.findOneClubUserRunStatByUidAndTypeAndAddTime(uid, type, beginTime, endTime);
        if (clubUserRunStat == null) {
            ClubUserRunStat clubUserRunStatNew = buildClubUserRunStat(uid, runTime, distance, calorie, step, type);
            clubCoreDao.insertClubUserRunStat(clubUserRunStatNew);
        }
        else {
            clubCoreDao.updateClubUserRunStatById(clubUserRunStat.getId(),
                    Update.update("runTime", clubUserRunStat.getRunTime() + runTime).
                            set("distance", clubUserRunStat.getDistance() + distance).
                            set("calorie", clubUserRunStat.getCalorie() + calorie).
                            set("step", clubUserRunStat.getStep() + step));
        }
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
    public ClubUserRunStat buildClubUserRunStat(int uid, long runTime, long distance, long calorie, long step, int type) {
        ClubUserRunStat clubUserRunStat = new ClubUserRunStat();
        clubUserRunStat.setUid(uid);
        clubUserRunStat.setRunTime(runTime);
        clubUserRunStat.setDistance(distance);
        clubUserRunStat.setCalorie(calorie);
        clubUserRunStat.setStep(step);
        clubUserRunStat.setType(type);
        clubUserRunStat.setAddTime(System.currentTimeMillis());
        clubUserRunStat.setAddTimeStr(DateUtil.format(new Date(), "yyyy-MM-dd"));
        return clubUserRunStat;
    }

    /**
     * 俱乐部排行榜 累加
     * <p>
     *     1、存在则更新累加
     *     2、不存在添加
     * </p>
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param clubIds 俱乐部编号
     * @param runTime 运动时间
     * @param distance 运动距离
     * @param calorie 卡路里
     * @param step 不是
     * @param type 类型：1、日 2、周 3、月
     */
    public void appendClubRanking(long beginTime, long endTime, Collection<Long> clubIds,
                                  long runTime, long distance, long calorie, long step, int type) {

        for (Long clubId: clubIds) {
            ClubRanking clubRanking = clubCoreDao.findClubRankingByClubIdAndTypeAndAddTime(clubId, type, beginTime, endTime);
            if (clubRanking == null) {
                ClubRanking clubRankingWeekNew = buildClubRanking(clubId, runTime, distance, calorie, step, type);
                clubCoreDao.insertClubRanking(clubRankingWeekNew);
            } else {
                clubCoreDao.updateClubRankingById(clubRanking.getId(),
                        Update.update("runTime", clubRanking.getRunTime() + runTime).
                                set("distance", clubRanking.getDistance() + distance).
                                set("calorie", clubRanking.getCalorie() + calorie).
                                set("step", clubRanking.getStep() + step));
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
     * @return
     */
    public ClubRanking buildClubRanking(long clubId, long runTime, long distance, long calorie, long step, int type) {
        ClubRanking clubRanking = new ClubRanking();
        clubRanking.setClubId(clubId);
        clubRanking.setRunTime(runTime);
        clubRanking.setDistance(distance);
        clubRanking.setCalorie(calorie);
        clubRanking.setStep(step);
        clubRanking.setType(type);
        clubRanking.setAddTime(System.currentTimeMillis());
        clubRanking.setAddTimeStr(DateUtil.format(new Date(), "yyyy-MM-dd"));
        return clubRanking;
    }
}
