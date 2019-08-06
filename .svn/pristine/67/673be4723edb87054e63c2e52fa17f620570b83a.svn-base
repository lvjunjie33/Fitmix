package com.business.app.clubRanking;

import com.business.app.clubMember.ClubMemberDao;
import com.business.core.entity.Page;
import com.business.core.entity.club.ClubRanking;
import com.business.core.entity.club.ClubUserRunStat;
import com.business.core.entity.user.User;
import com.business.core.operations.club.ClubCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by sin on 2015/11/24.
 */
@Service
public class ClubRankingService {

    @Autowired
    private ClubMemberDao clubMemberDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserCoreService userCoreService;
    @Autowired
    private ClubRankingDao clubRankingDao;
    @Autowired
    private ClubCoreDao clubCoreDao;

    public void updateClubRanking(Collection<Long> clubIds, long runTime, long distance, long calorie, long step) {
        /// 天
        long dayBeginTime = DateUtil.getDayBegin(new Date()).getTime();
        long dayEndTime = DateUtil.getDayEnd(new Date()).getTime();

        /// 周
        long weekBeginTime = DateUtil.getDayBegin(DateUtil.getWeekBegin(new Date())).getTime();
        long weekEndTime = DateUtil.getDayEnd(DateUtil.getWeekEnd(new Date())).getTime();

        /// 月
        long monthBeginTime =  DateUtil.getDayBegin(DateUtil.getMonthBegin(new Date())).getTime();
        long monthEndTime = DateUtil.getDayEnd(DateUtil.getMonthEnd(new Date())).getTime();

        /// 俱乐部排行统计
        appendClubRanking(dayBeginTime, dayEndTime, clubIds, runTime, distance, calorie, step, ClubUserRunStat.TYPE_DAY);
        appendClubRanking(weekBeginTime, weekEndTime, clubIds, runTime, distance, calorie, step, ClubUserRunStat.TYPE_WEEK);
        appendClubRanking(monthBeginTime, monthEndTime, clubIds, runTime, distance, calorie, step, ClubUserRunStat.TYPE_MONTH);
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
            }
            else {
                clubRankingDao.updateClubRankingById(clubRanking.getId(),
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
        clubRanking.setAddTimeStr(DateUtil.formatTimestamp(System.currentTimeMillis(), "yyyy-MM-dd"));
        return clubRanking;
    }

    /**
     * 俱乐部 排行榜
     *
     * @param clubId 俱乐部编号
     * @param type 类型：2、周 3、月
     * @return 俱乐部 排行信息
     */
    public Object[] ranking(long clubId, int type) {

        Date beginDate;
        Date endDate;

        Date today = new Date();

        /// 周 or 月
        if (type == ClubUserRunStat.TYPE_WEEK) {
            beginDate = DateUtil.getDayBegin(DateUtil.getWeekBegin(today));
            endDate = DateUtil.getDayEnd(DateUtil.getWeekEnd(today));
        } else {
            beginDate = DateUtil.getDayBegin(DateUtil.getMonthBegin(today));
            endDate = DateUtil.getDayEnd(DateUtil.getMonthEnd(today));
        }

        //天数
        int dayNumber = DateUtil.diffDay(endDate, beginDate, true);

        ClubRanking ranking = clubRankingDao.findClubRankingByClubIdAndTypeAndAddTimeForAddTimeToDesc2(clubId,
                type, beginDate.getTime(), endDate.getTime(), "distance", "calorie", "step");

        if (ranking == null) {
            ranking = new ClubRanking();
            ranking.setStep(0L);
            ranking.setCalorie(0L);
            ranking.setDistance(0L);
        }

        /// 俱乐部排行榜 (统计图数据)
        List<ClubRanking> clubRankingDayList = clubRankingDao.findClubRankingByClubIdAndTypeAndAddTimeForAddTimeToDesc(clubId, ClubUserRunStat.TYPE_DAY,
                beginDate.getTime(), endDate.getTime());
        List<Long> rankingStatViewList = new ArrayList<>(dayNumber);
        // 每天的运动记录
        Map<String, ClubRanking> timeMap = CollectionUtil.buildMap(clubRankingDayList, String.class, ClubRanking.class, "addTimeStr");
        // 没用记录 补零
        for (int i = 0; i < dayNumber; i++) {
            String strTime = DateUtil.format(DateUtil.addDate(beginDate, Calendar.DAY_OF_MONTH, i), "yyyy-MM-dd");
            ClubRanking clubRanking = timeMap.get(strTime);
            if (clubRanking == null) {
                rankingStatViewList.add(0L);
            }
            else {
                rankingStatViewList.add(clubRanking.getDistance());
            }
        }
        return new Object[]{0, rankingStatViewList, ranking};

    }

    /**
     * 俱乐部用户 排行榜
     *
     * @param clubId 俱乐部编号
     * @param type 类型 2、周 3、月 {@link com.business.core.entity.club.ClubRanking}
     * @param index 分页索引
     */
    public List<ClubUserRunStat> userRanking(long clubId, int type, int index) {
        long beginTime;
        long endTime;
        Date today = new Date();

        /// 周 or 月
        if (type == ClubUserRunStat.TYPE_WEEK) {
            beginTime = DateUtil.getDayBegin(DateUtil.getWeekBegin(today)).getTime();
            endTime = DateUtil.getDayEnd(DateUtil.getWeekEnd(today)).getTime();
        }
        else {
            beginTime = DateUtil.getDayBegin(DateUtil.getMonthBegin(today)).getTime();
            endTime = DateUtil.getDayEnd(DateUtil.getMonthEnd(today)).getTime();
        }

        Set<Integer> uidSet = CollectionUtil.buildSet(clubMemberDao.findClubMemberByClubIdByType(clubId, null, "uid"), Integer.class, "uid");
        Page<ClubUserRunStat> page = new Page<>();
        page.setPageNo(index);
        page.getFilter().put("beginTime", beginTime);
        page.getFilter().put("endTime", endTime);
        page.getFilter().put("uidSet", uidSet);
        page.getFilter().put("type", type);

        clubRankingDao.findPageClubUserStat(page);

        /// 设置用户信息
        List<User> userList = userCoreDao.findUserById(uidSet, null, "id", "name", "avatar");
        userCoreService.buildUserFileUrl(userList);
        Map<Integer, User> userMap = CollectionUtil.buildMap(userList, Integer.class, User.class, "id");

        for (ClubUserRunStat clubUserRunStat : page.getResult()) {
            clubUserRunStat.setUser(userMap.get(clubUserRunStat.getUid()));
        }
        return page.getResult();
    }
}
