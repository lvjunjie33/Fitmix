package com.business.app.clubNotice;

import com.business.app.club.ClubDao;
import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.club.Club;
import com.business.core.entity.club.ClubNotice;
import com.business.core.entity.user.User;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.redis.RedisMsgManager;
import com.business.core.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/12/7.
 */
@Service
public class ClubNoticeService {

    @Autowired
    private ClubDao clubDao;
    @Autowired
    private ClubNoticeDao clubNoticeDao;
    @Autowired
    private UserCoreDao userCoreDao;

    /**
     * 公告分页
     * @param clubId 俱乐部编号
     * @param index 第几页
     * @return 俱乐部分页
     */
    public Page<ClubNotice> page(Long clubId, int index) {
        Page<ClubNotice> page = new Page<>();
        page.setPageNo(index);
        clubNoticeDao.page(page, clubId);
        buildClubNoticeUrl(page.getResult());
        CollectionUtil.buildSet(page.getResult(), Integer.class, "id");
        List<User> users = userCoreDao.findUserById(CollectionUtil.buildSet(page.getResult(), Integer.class, "uid"), null, "id", "name");
        Map<Integer, User> userMap = CollectionUtil.buildMap(users, Integer.class, User.class, "id");
        for (ClubNotice clubNotice : page.getResult()) {
            clubNotice.setUser(userMap.get(clubNotice.getUid()));
        }
        return page;
    }

    public void buildClubNoticeUrl(List<ClubNotice> clubNoticeList) {
        for (ClubNotice clubNotice : clubNoticeList) {
            clubNotice.setBackImageUrl(FileCenterClient.buildUrl(clubNotice.getBackImageUrl()));
        }
    }

    /**
     * 公告信息 添加
     * @param uid 用户编号
     * @param clubId 俱乐部编号
     * @param name 公告名称
     * @param content 公告内容
     * @param address 公告地址
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param desc 描述
     * @param image 背景图
     * @return 0、成功
     */
    public Object[] add(Integer uid, Long clubId, String name, String content,
                    String address, Long beginTime, Long endTime, String desc, MultipartFile image) {

        Club club = clubDao.findClubById(clubId, "id", "status", "memberUidSet", "name");
        if (club == null || Club.STATUS_INVALID.equals(club.getStatus())) {
            return new Object[]{1};
        }

        ClubNotice clubNotice = new ClubNotice();
        clubNotice.setUid(uid);
        clubNotice.setClubId(clubId);
        clubNotice.setName(name);
        clubNotice.setContent(content);
        clubNotice.setAddress(address);
        clubNotice.setBeginTime(beginTime);
        clubNotice.setEndTime(endTime);
        clubNotice.setDesc(desc);
        String imageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_CLUB_NOTICE_IMAGE, image);
        clubNotice.setBackImageUrl(imageUrl);
        clubNotice.setStatus(ClubNotice.STATUS_NORMAL);
        clubNotice.setModifyCount(0);
        clubNotice.setAddTime(System.currentTimeMillis());

        clubNoticeDao.insertClubNotice(clubNotice);

        {//俱乐部 活动公告 通知
            RedisMsgManager.sendClubNotice(clubNotice);
        }

        exclude(clubNotice);
        return new Object[]{0, clubNotice};
    }

    public Object[] modify(Long id, Integer uid, Long clubId, String name, String content,
                             String address, Long beginTime, Long endTime, String desc, MultipartFile image) {

        ClubNotice clubNoticeBase = clubNoticeDao.findClubNoticeAndIdAndClubIdAndUid(id, clubId, uid, ClubNotice.STATUS_NORMAL, "id", "backImageUrl");

        if (clubNoticeBase == null) {
            return new Object[]{1};
        }

        Update update = Update.update("name", name).set("content", content).
                set("address", address).set("beginTime", beginTime).set("endTime", endTime).set("desc", desc);
        if (image != null) {
            String imageUrlNew = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_CLUB_NOTICE_IMAGE, image);
            update.set("backImageUrl", imageUrlNew);
            AliyunCenterClient.deleteFile(clubNoticeBase.getBackImageUrl());
        }

        ClubNotice clubNotice = clubNoticeDao.findAndModifyNew(id, clubId, uid, update);
        exclude(clubNotice);
        return new Object[]{0, clubNotice};
    }

    /**
     * 排除 clubNotice
     * @param clubNotice 俱乐部不返回字段
     */
    public void exclude(ClubNotice clubNotice) {
        clubNotice.setStatus(null);
        clubNotice.setAddTime(null);
    }


    /**
     * 删除 公告
     * @param noticeId 公告编号
     * @param clubId 俱乐部编号
     * @param uid 用户编号
     */
    public void remove(Long noticeId, Long clubId, Integer uid) {
        clubNoticeDao.findAndModifyNew(noticeId, clubId, uid, Update.update("status", ClubNotice.STATUS_INVALID));
    }
}
