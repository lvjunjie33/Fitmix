package com.business.app.clubMember;

import com.business.app.club.ClubDao;
import com.business.app.clubRanking.ClubRankingDao;
import com.business.app.clubRanking.ClubRankingService;
import com.business.core.entity.Page;
import com.business.core.entity.club.Club;
import com.business.core.entity.club.ClubMember;
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

import java.util.*;

/**
 * Created by sin on 2015/11/24.
 */
@Service
public class ClubMemberService {


    @Autowired
    private UserCoreDao userCoreDao;

    @Autowired
    private ClubDao clubDao;
    @Autowired
    private ClubCoreDao clubCoreDao;
    @Autowired
    private ClubMemberDao clubMemberDao;

    @Autowired
    private UserCoreService userCoreService;
    @Autowired
    private ClubRankingDao clubRankingDao;
    @Autowired
    private ClubRankingService clubRankingService;

    /**
     * 俱乐部 成员分页
     *
     * @param clubId 俱乐部编号
     * @return 成员数据
     */
    public Page<ClubMember> list(Long clubId, Integer index) {
        Page<ClubMember> page = new Page<>();
        page.setPageNo(index);
        page.getFilter().put("clubId", clubId);
        clubMemberDao.findPageClubMember(page);
        // 用户信息
        List<User> userList = userCoreDao.findUserById(CollectionUtil.buildSet(page.getResult(), Integer.class, "uid"), null, "id", "name", "avatar");
        // 构建地址
        userCoreService.buildUserFileUrl(userList);
        Map<Integer, User> userMap = CollectionUtil.buildMap(userList, Integer.class, User.class, "id");
        for (ClubMember clubMember : page.getResult()) {
            clubMember.setUser(userMap.get(clubMember.getUid()));
        }
        return page;
    }

    /**
     * 添加俱乐部 成员
     *
     * <p>
     *     添加俱乐部成员，如果在期间 加入过俱乐部
     *     则恢复俱乐部之前的数据 修改 status 状态
     *     <strong>
     *         只有在首次加入的用户才，进行添加处理
     *     </strong>
     * </p>
     * @param clubId 俱乐部编号
     * @param uid 加入用户
     * @return 俱乐部成功信息 0、成功 1、用户不存在 2、加入失败、俱乐部不存在
     * 3、已是俱乐部成员 4、之前加入过俱乐部，恢复之前数据 5、加入失败，俱乐部已删除 6、俱乐部成员达到上限
     */
    public Object[] add(Long clubId, Integer uid, Integer memberType) {

        ClubMember clubMember = clubMemberDao.findClubMemberByClubIdAndUidAndStatusAndType(clubId, uid, null, null);

        /// 用户是否存在
        if (userCoreDao.findUserById(uid, "id") == null) {
            return new Object[]{1};
        }

        /// 俱乐部不存在
        Club club = clubDao.findClubById(clubId, "status", "memberCount", "memberUidSet", "maxMember");
        if (club == null) {
            return new Object[]{2};
        }
        /// 俱乐部已删除
        if (club.getStatus().equals(Club.STATUS_INVALID)) {
            return new Object[]{5};
        }

        /// 已是俱乐部成员
        if (clubMember != null && clubMember.getStatus().equals(ClubMember.STATUS_NORMAL)) {
            clubMember.setAddTime(null);
            clubMember.setStatus(null);
            return new Object[]{3, clubMember};
        }

        /// 退出了俱乐部，在此加入恢复状态
        if (clubMember != null && clubMember.getStatus().equals(ClubMember.STATUS_INVALID)) {
            clubMemberDao.updateClubMemberById(clubMember.getId(), Update.update("status", ClubMember.STATUS_NORMAL));
            //clubMemberDao.updateClubMemberByClubIdAndUid(clubId, uid, Update.update("status", ClubMember.STATUS_NORMAL));

            clubMember.setAddTime(null);
            clubMember.setStatus(null);
            // 更新 俱乐部 成员数量 和成员编号
            updateClubQuitAndJoinInfo(clubId, uid, club.getMemberUidSet(), 1);
            return new Object[]{4, clubMember};
        }

        /// 俱乐部成员限制
        if ( null != club.getMaxMember() && clubMemberDao.findClubMemberCountByClubId(clubId) > club.getMaxMember()) {
            return new Object[]{6};
        }
        /// 进行添加 成员

        ClubMember clubMemberNew = new ClubMember();
        clubMemberNew.setClubId(clubId);
        clubMemberNew.setUid(uid);
        clubMemberNew.setStatus(ClubMember.STATUS_NORMAL);
        clubMemberNew.setType(memberType == null ? ClubMember.TYPE_MEMBER : memberType);
        clubMemberNew.setAddTime(System.currentTimeMillis());

        // 插入 俱乐部成员信息
        clubMemberDao.insertClubMember(clubMemberNew);

        // 更新 俱乐部 成员数量 和成员编号
        updateClubQuitAndJoinInfo(clubId, uid, club.getMemberUidSet(), 1);
        return new Object[]{0, clubMemberNew};
    }


    /**
     * 加入 或 加入俱乐部, 更新信息
     *
     * <strong>
     *     两个特殊 业务：
     *
     *     1、处理俱乐部 memberUidSet 、memberCount
     *     2、处理俱乐部 排行榜（ClubRanking）
     * </strong>
     *
     * @param clubId 俱乐部编号
     * @param uid 用户编号
     * @param memberUidSet 成员编号
     * @param inc 增长数：1 则+1  -1位-1
     */
    public void updateClubQuitAndJoinInfo(long clubId, int uid, Set<Integer> memberUidSet, int inc) {

        /// 查询用户运动 信息
        long dayBeginTime = DateUtil.getDayBegin(new Date()).getTime();
        long dayEndTime = DateUtil.getDayEnd(new Date()).getTime();

        ClubUserRunStat clubUserRunStat = clubCoreDao.findOneClubUserRunStatByUidAndTypeAndAddTime(uid, ClubUserRunStat.TYPE_DAY, dayBeginTime, dayEndTime);
        List<Long> clubIds = new ArrayList<>();
        clubIds.add(clubId);

        if (inc == 1) { // 加入俱乐部
            /// 更新 俱乐部 加入用户
            memberUidSet.add(uid);
            /// 更新 俱乐部排行榜 总成绩 ClubRankingStat
            if (clubUserRunStat != null) {
                //TODO from edward 这块先注释掉，不做处理，之后再确定解决方案(目前的这个解决方案内部有问题)
//                clubRankingService.updateClubRanking(clubIds, clubUserRunStat.getRunTime(), clubUserRunStat.getDistance(), clubUserRunStat.getCalorie(), clubUserRunStat.getStep());
            }
        } else {
            memberUidSet.remove(uid);
            /// 更新 俱乐部排行榜 总成绩 ClubRankingStat
            if (clubUserRunStat != null) {
                //TODO from edward 这块先注释掉，不做处理，之后再确定解决方案(目前的这个解决方案内部有问题)
//                clubRankingService.updateClubRanking(clubIds, -clubUserRunStat.getRunTime(), -clubUserRunStat.getDistance(), -clubUserRunStat.getCalorie(), -clubUserRunStat.getStep());
            }
        }

        /// 更新 俱乐部 成员信息
        clubDao.updateClubById(clubId,Update.update("memberUidSet", memberUidSet).inc("memberCount", inc));
    }

    /**
     * 更新 成员信息 返回 修改后的信息
     *
     * @param clubId 俱乐部编号
     * @param uid 成员编号
     * @param modifyName 修改名称
     * @return 更新后信息
     */
    public ClubMember modify(Long clubId, Integer uid, String modifyName) {
        return clubMemberDao.findAndModifyNewByClubIdAndUid(clubId, uid, Update.update("name", modifyName));
    }

    /**
     * 退出俱乐部
     *
     * @param uid 用户编号
     * @param clubId 俱乐部编号
     * @return 0、成功 1、俱乐部不存在  2、您是俱乐部创建者不能退出
     */
    public int quit(Integer uid, Long clubId) {
        Club club = clubDao.findClubById(clubId, "uid", "memberCount", "memberUidSet");
        if (club == null || club.getUid() == null) {
            return 1;
        }
        if (club.getUid().equals(uid)) {
            return 2;
        }

        // 更新 俱乐部 成员数量 和成员编号
        updateClubQuitAndJoinInfo(clubId, uid, club.getMemberUidSet(), -1);
        ClubMember clubMember = clubMemberDao.findClubMemberByClubIdAndUid(clubId, uid, "id");
        clubMemberDao.updateClubMemberById(clubMember.getId(), Update.update("status", ClubMember.STATUS_INVALID));
        //clubMemberDao.updateClubMemberByClubIdAndUid(clubId, uid, Update.update("status", ClubMember.STATUS_INVALID));
        return 0;
    }

    /**
     * 强制退出 俱乐部成员
     *
     * @param uid 用户编号（创建者）
     * @param clubId 俱乐部编号
     * @param quitUid 退出用户
     */
    public int forcedQuit(Integer uid, Long clubId, Integer quitUid) {
        // 不能移除自己
        if (uid.equals(quitUid)) {
            return 1;
        }

        Club club = clubDao.findClubById(clubId, "id", "uid", "memberCount", "memberUidSet");
        // 权限不足
        if (club == null || !club.getUid().equals(uid)) {
            return 2;
        }

        // 更新 俱乐部 成员数量 和成员编号
        updateClubQuitAndJoinInfo(clubId, quitUid, club.getMemberUidSet(), -1);
        ClubMember clubMember = clubMemberDao.findClubMemberByClubIdAndUid(clubId, quitUid, "id");
        clubMemberDao.updateClubMemberById(clubMember.getId(), Update.update("status", ClubMember.STATUS_INVALID));
        //clubMemberDao.updateClubMemberByClubIdAndUid(clubId, quitUid, Update.update("status", ClubMember.STATUS_INVALID));
        return 0;
    }
}
