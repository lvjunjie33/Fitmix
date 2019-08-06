package com.business.app.club;

import com.business.app.base.support.BaseServiceSupport;
import com.business.app.clubMember.ClubMemberDao;
import com.business.app.clubMember.ClubMemberService;
import com.business.app.clubRanking.ClubRankingDao;
import com.business.app.userRun.UserRunDao;
import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.constants.VersionConstants;
import com.business.core.entity.Page;
import com.business.core.entity.SysParam;
import com.business.core.entity.club.Club;
import com.business.core.entity.club.ClubMember;
import com.business.core.entity.club.ClubUserRunStat;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.operations.club.ClubCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by sin on 2015/11/23.
 */
@Service
public class ClubService extends BaseServiceSupport {

    @Autowired
    private UserRunDao userRunDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserCoreService userCoreService;

    @Autowired
    private ClubDao clubDao;
    @Autowired
    private ClubCoreDao clubCoreDao;
    @Autowired
    private ClubMemberDao clubMemberDao;
    @Autowired
    private ClubMemberService clubMemberService;

    @Autowired
    private ClubRankingDao clubRankingDao;

    /**
     * 俱乐部信息
     *
     * @param uid 用户编号
     * @return 俱乐部信息
     */
    public List<Club> list(Integer uid) {
        List<Club> publicClubList = new ArrayList<>(0);

        if (getTerminalType() == Constants.TERMINAL_IOS
                && getContext().getVersion() >= VersionConstants.IOS.VERSION_6.getVersion()
                || getTerminalType() == Constants.TERMINAL_ANDROID
                && getContext().getVersion() >= VersionConstants.Android.VERSION_31.getVersion()) {

            // 公开 置顶 俱乐部
            publicClubList = clubDao.findClubByIds(SysParam.INSTANCE.APP_CLUB_TOP_CLUB,
                    "uid", "name", "backImageUrl", "desc", "memberCount", "memberUidSet");
            buildClubMemberUrls(publicClubList);
            for (Club club : publicClubList) {
                club.setType(Club.TYPE_PUBLIC);
                club.setHaveMember((club.getMemberUidSet().contains(uid)) ? Club.SAVE_MEMBER_YES : Club.SAVE_MEMBER_NO);
            }
        }

        // 个人 俱乐部
        List<ClubMember> clubMemberList = clubCoreDao.findClubMemberByUidAndType(uid, null, "id", "uid", "clubId");
        Set<Long> clubIdSet = CollectionUtil.buildSet(clubMemberList, Long.class, "clubId");
        clubIdSet.removeAll(SysParam.INSTANCE.APP_CLUB_TOP_CLUB); // 去除重复 俱乐部
        List<Club> clubList = clubDao.findClubByIds(clubIdSet, "uid", "name", "backImageUrl", "desc", "memberCount");

        buildClubMemberUrls(clubList);
        for (Club club : clubList) {
            club.setType(Club.TYPE_USER);
        }

        // 合并俱乐部 集合
        publicClubList.addAll(clubList);
        return publicClubList;
    }

    /**
     * 俱乐部查询
     *
     * @param clubId 俱乐部查询
     * @param uid 用户编号
     */
    public Club find(Long clubId, Integer uid) {
        ClubMember clubMember = clubMemberDao.findClubMemberByClubIdAndUid(clubId, uid, "uid");
        if (clubMember == null) {
            return null;
        }
        Club club = clubDao.findClubById(clubId);
        club.setUser(userCoreDao.findUserById(club.getUid(), "name"));
        buildClubFileUrl(club);
        return club;
    }


    /**
     * 构建 url 信息
     *
     * @param club 俱乐部信息
     */
    public void buildClubFileUrl(Club club) {
        if (null != club && null != club.getBackImageUrl()) {
            club.setBackImageUrl(FileCenterClient.buildUrl(club.getBackImageUrl()));
        }
    }

    public void buildClubMemberUrls(Collection<Club> clubs) {
        for (Club club : clubs) {
            club.setBackImageUrl(FileCenterClient.buildUrl(club.getBackImageUrl()));
        }
    }

    /**
     * 添加 俱乐部
     * <p>
     *     添加拒了的同时，会想 ClubMember 添加数据
     *     默认在 ClubMember 中为创建者
     * </p>
     * @param image 背景图
     * @param name 名称
     * @param desc 描述
     * @return 0、正常 1、俱乐部名称已存在
     */
    public Object[] add(MultipartFile image, Integer uid, String name, String desc) {
        if (StringUtils.isEmpty(uid)) {
            return new Object[]{2};
        }
        if (clubDao.findClubByUidName(uid, name) != null) {
            return new Object[]{1};
        }
        String backImageUrl = "";
        if (image != null) {
            backImageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_USER_CLUB_BACK_IMAGE.toString(), image);
        }

        Club club = buildClub(uid, backImageUrl, name, desc);

        clubDao.insertClub(club);

        club.setAddTime(null);
        club.setStatus(null);

        /// 添加俱乐部成员
        clubMemberService.add(club.getId(), uid, ClubMember.TYPE_CREATOR);
        return new Object[]{0, club};
    }

    /**
     * 构建一个 俱乐部
     *
     * @param backImageUrl 俱乐部背景图
     * @param name 名称
     * @param desc 描述
     * @return 俱乐部 构建对象
     */
    public Club buildClub(Integer uid, String backImageUrl, String name, String desc) {
        Club club = new Club();
        club.setUid(uid);
        club.setBackImageUrl(backImageUrl);
        club.setName(name);
        club.setDesc(desc);
        club.setMaxMember(SysParam.INSTANCE.APP_CLUB_MEMBER_JOIN_MAX_MEMBER);
        club.setMemberCount(0);
        club.setMemberUidSet(new HashSet<Integer>(0));
        club.setStatus(Club.STATUS_NORMAL);
        club.setAddTime(System.currentTimeMillis());
        return club;
    }


    /**
     * 修改 俱乐部
     * <p>
     *     image 等于 null 时，不作任何处理
     * </p>
     * @param image 背景图
     * @param clubId 俱乐部编号
     * @param name 名称
     * @param desc 描述
     * @return 0、成功 1、俱乐部不存在
     */
    public Object[] modify(MultipartFile image, Long clubId, String name, String desc) {
        Club clubOld = clubDao.findClubById(clubId, "id", "name", "backImageUrl");
        if (clubOld == null) {
            return new Object[1];
        }

        if (clubDao.findClubByUidName(clubOld.getUid(), name) != null) {
            return new Object[]{2};
        }

        Update update = new Update();
        if (image != null) {
            String backImageUrlNew = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_USER_CLUB_BACK_IMAGE.toString(), image);
            AliyunCenterClient.deleteFile(clubOld.getBackImageUrl());
            update.set("backImageUrl", backImageUrlNew);
        }
        update.set("name", name).set("desc", desc);

        Club clubNew = clubDao.findAndModifyNew(clubId, update);


        clubNew.setStatus(null);
        clubNew.setAddTime(null);

        // 构建地址
        buildClubFileUrl(clubNew);
        return new Object[]{0, clubNew};
    }

    /**
     * 删除 俱乐部，修改俱乐部状态
     *
     * @param clubId 俱乐部编号
     * @param uid 用户编号
     * @return 0、成功 1、俱乐部不存在
     *
     */
    public int remove(Long clubId, Integer uid) {
        if (clubDao.findClubByIdAndUid(clubId, uid, "id") == null) {
            return 1;
        }
        clubDao.updateClubByIdAndUid(clubId, uid, Update.update("status", Club.STATUS_INVALID));

        // 创建者 删除俱乐部，修改成员加入俱乐部
        clubMemberDao.updateClubMemberByClubId(clubId, Update.update("status", ClubMember.STATUS_INVALID));
        return 0;
    }

    /**
     * 活跃
     *
     * <p>
     *     返回 map 集合，返回俱乐部成员信息 和 今日动态
     *     memberCount ： 俱乐部成员
     *     activeCount ： 活跃人数
     * </p>
     * @param clubId 俱乐部编号
     * @return 俱乐部活跃
     */
    public Map<String, Object> active(Long clubId) {
        long memberCount = clubMemberDao.findClubMemberCountByClubId(clubId);

        Set<Integer> ids = CollectionUtil.buildSet(
                clubMemberDao.findClubMemberByClubIdByType(clubId, null, "uid"), Integer.class, "uid");

        List<UserRun> userRunList = userRunDao.findUserRunByUidAndAddTime(ids,
                DateUtil.getDayBegin(new Date()).getTime(),DateUtil.getDayEnd(new Date()).getTime(), "uid");

        int activeCount = CollectionUtil.buildSet(userRunList, Integer.class, "uid").size();

        Map<String, Object> resultActive = new LinkedHashMap<>(2);
        resultActive.put("memberCount", memberCount);
        resultActive.put("activeCount", activeCount);
        return resultActive;
    }

    /**
     * 俱乐部活跃用户
     *
     * <p>
     *     注意：
     *     1、如果俱乐部活跃人数为 0 ，则从俱乐部任意挑选 10 人显示
     * </p>
     *
     * @param clubId 俱乐部编号
     * @param uid 用户编号
     * @return 活跃用户信息
     */
    public List<User> activeUser(Long clubId, Integer uid) {
        Club club = clubDao.findClubById(clubId, "id", "memberUidSet", "memberCount");

        Page<ClubUserRunStat> page = new Page<>();
        page.setSize(10);
        page.getFilter().put("uidSet", club.getMemberUidSet());
        page.getFilter().put("type", ClubUserRunStat.TYPE_DAY);
        page.getFilter().put("beginTime", DateUtil.getDayBegin(new Date()).getTime());
        page.getFilter().put("endTime", DateUtil.getDayEnd(new Date()).getTime());

        clubRankingDao.findPageClubUserStat(page, "uid");
        List<User> activeUserSort = new ArrayList<>(10);
        // 如果没有 则从俱乐部中 任意挑选成员
        if (page.getResult().size() < 1) {
            Set<Integer> ids = new HashSet<>(10);
            List<Integer> uidList = new ArrayList<>(club.getMemberUidSet());
            for (int i = 0; i < club.getMemberUidSet().size(); i++) {
                if (i > 10) {
                    break;
                }
                ids.add(uidList.get(i));
            }
            activeUserSort = userCoreDao.findUserById(uidList, null, "id", "name", "avatar");
            userCoreService.buildUserFileUrl(activeUserSort);
        }
        else {
            Set<Integer> ids = CollectionUtil.buildSet(page.getResult(), Integer.class, "uid");
            List<User> userList = userCoreDao.findUserById(ids, null, "id", "name", "avatar");
            userCoreService.buildUserFileUrl(userList);

            Map<Integer, User> userMap = CollectionUtil.buildMap(userList,Integer.class, User.class, "id");

            for (ClubUserRunStat clubUserRunStat : page.getResult()) {
                activeUserSort.add(userMap.get(clubUserRunStat.getUid()));
            }
        }
        return activeUserSort;
    }

    /**
     * 俱乐部详细
     *
     * @param clubId 俱乐部编号
     * @return 俱乐部信息
     */
    public Club shareInfo(Long clubId) {
        Club club = clubDao.findClubById(clubId);
        club.setUser(userCoreDao.findUserById(club.getUid(), "name"));
        buildClubFileUrl(club);
        return club;
    }

    /**
     * 俱乐部 动态
     *
     * @param clubId 俱乐部编号
     * @return 俱乐部动态
     */
    public List<UserRun> dynamic(Long clubId, int index) {
        // 获取俱乐部 成员 编号
        Set<Integer> ids = CollectionUtil.buildSet(clubMemberDao.findClubMemberByClubIdByType(clubId, null, "uid"), Integer.class, "uid");

        // 获取今日用户运动信息
        Page<UserRun> page = new Page<>();
        page.setPageNo(index);
        page.getFilter().put("uidSet", ids);
        page.getFilter().put("beginTime", DateUtil.getDayBegin(new Date()).getTime());
        page.getFilter().put("endTime", DateUtil.getDayEnd(new Date()).getTime());
        List<Integer> types = new ArrayList<>();
        types.add(UserRun.TYPE_RUN);
        types.add(UserRun.TYPE_UNKNOWN);

        page.getFilter().put("type", types);
        userRunDao.findPageUserRun(page, "id", "uid", "runTime", "distance", "step", "calorie", "bpm", "model", "userBpmMatch", "startTime");

        // 查询用户信息
        List<User> userList= userCoreDao.findUserById(ids, null, "id", "name", "avatar");

        // 构建 file url
        userCoreService.buildUserFileUrl(userList);

        // 转成map 集合对应关系
        Map<Integer, User> userMap = CollectionUtil.buildMap(userList, Integer.class, User.class, "id");

        // 设置 用户数据 并 处理空用户 数据不返回
        for (UserRun userRun : page.getResult()) {
            userRun.setUser(userMap.get(userRun.getUid()));
        }
        return page.getResult();
    }

    /**
     * 解散俱乐部
     *
     * @param uid 用户编号
     * @param clubId 俱乐部编号
     * @return 0、成功 1、您的权限不足
     */
    public int dissolution(Integer uid, Long clubId) {
        Club club = clubDao.findClubByIdAndUid(clubId, uid,  "id", "uid");

        // 权限不足
        if (club == null) {
            return 1;
        }
        return 0;
    }
}
