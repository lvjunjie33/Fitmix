package com.business.app.clubMessage;

import com.business.app.club.ClubDao;
import com.business.app.clubMember.ClubMemberDao;
import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.Page;
import com.business.core.entity.club.Club;
import com.business.core.entity.club.ClubMember;
import com.business.core.entity.club.ClubMessage;
import com.business.core.entity.user.User;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.redis.RedisMsgManager;
import com.business.core.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/11/24.
 */
@Service
public class ClubMessageService {


    @Autowired
    private ClubDao clubDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserCoreService userCoreService;
    @Autowired
    private ClubMessageDao clubMessageDao;
    @Autowired
    private ClubMemberDao clubMemberDao;

    /**
     * 添加 留言板消息
     *
     * @param clubId 俱乐部
     * @param uid 用户编号
     * @param content 内容
     * @return 0、成功 1、俱乐部不存在 2、你已不是俱乐部成员
     */
    public Object[] add(Long clubId, Integer uid, String content, Integer contentType, Map<String, MultipartFile> files) {

        Club club = clubDao.findClubById(clubId, "id", "uid", "status", "memberUidSet", "name");
        if (club == null || Club.STATUS_INVALID.equals(club.getStatus())) {
            return new Object[]{1};
        }

        ClubMember clubMember = clubMemberDao.findClubMemberByClubIdAndUid(clubId, uid, "status");
        if (clubMember.getStatus().equals(ClubMember.STATUS_INVALID)) {
            return new Object[]{2};
        }

        ClubMessage clubMessage = new ClubMessage();// TODO 后期将俱乐部消息改为 关联消息MessageBodyPush
        clubMessage.setUid(uid);
        clubMessage.setClubId(clubId);
        clubMessage.setContent(content);
        clubMessage.setMsgType(contentType);

        String fileLink = null;
        {
            MultipartFile contentFile = files.get("contentFile");
            if (contentFile != null) {
                switch (contentType) {
                    case MsgConstants.CONTENT_TYPE_VOICE:
                        fileLink = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_CLUB_MSG_VOICE, contentFile);
                        break;
                    case MsgConstants.CONTENT_TYPE_IMG:
                        fileLink = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_CLUB_MSG_IMAGE, contentFile);
                        break;
                }
                clubMessage.setFileLink(fileLink);
            }
        }

        clubMessage.setAddTime(System.currentTimeMillis());
        clubMessageDao.insertClubMessage(clubMessage);

        {//俱乐部留言通知
            RedisMsgManager.sendClubMsg(clubMessage);
        }
        return new Object[]{0, clubMessage};
    }

    /**
     * 留言板消息（浏览 分页）
     *
     * @param clubId 俱乐部信息
     * @param index 第几页
     * @return 留言板信息
     */
    public List<ClubMessage> page(Long clubId, Integer index, boolean isOldVersion) {
        Page<ClubMessage> page = new Page<>();
        page.setPageNo(index);
        page.getFilter().put("clubId", clubId);
        if (isOldVersion) {
            page.getFilter().put("isOldVersion", isOldVersion);
        }
        clubMessageDao.page(page);

        List<User> userList = userCoreDao.findUserById(CollectionUtil.buildSet(page.getResult(), Integer.class, "uid"), null, "name", "avatar");
        Map<Integer, User> userMap = CollectionUtil.buildMap(userList, Integer.class, User.class, "id");
        for (ClubMessage clubMessage : page.getResult()) {
            User user = userMap.get(clubMessage.getUid());
            userCoreService.buildUserFileUrl(user);
            clubMessage.setUser(user);
            if (!StringUtils.isEmpty(clubMessage.getFileLink())) {
                clubMessage.setFileLink(FileCenterClient.buildUrl(clubMessage.getFileLink()));
            }
        }
        return page.getResult();
    }
}
