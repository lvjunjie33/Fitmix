package com.business.app.message;

import com.business.app.user.UserDao;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.Page;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.User;
import com.business.core.entity.user.info.UserGroup;
import com.business.core.entity.user.info.UserGroupRelation;
import com.business.core.redis.RedisMsgManager;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by edward on 2016/4/25.
 */
@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserDao userDao;

    /**
     *  发送私密消息
     *
     * @param uid 用户编号
     * @param targetUid 目标用户
     * @param content 内容
     */
    public Object[] sendPrivateMsg(Integer uid, Integer targetUid, String content) {

        Long currentTime = System.currentTimeMillis();
        //最后一次发送消息时间
        Message message = messageDao.findMessageByUid(uid, MsgConstants.CHANNEL_TYPE_USER_PRIVATE_MSG, "addTime");
        if (message != null) {
            if (currentTime - message.getAddTime() < 5000) {// 5秒钟发送消息限制
                return new Object[] {2};
            }
        }

        //今天发送次数
        Long sendCount = messageDao.findMessageCount(uid, MsgConstants.CHANNEL_TYPE_USER_PRIVATE_MSG, DateUtil.getDayBefore(new Date(currentTime)).getTime());

        if (sendCount > 10000) {//每天一万条消息
            return new Object[] {3};
        }

        List<Integer> uids = new ArrayList<>();
        uids.add(uid);
        uids.add(targetUid);

        //消息接收方与发送者的关系
        UserGroup userGroup = messageDao.findUserGroupByMember(uid, targetUid, UserGroup.TYPE_USER_PRIVATE);
        if (userGroup == null) {
            userGroup = new UserGroup();
            userGroup.setType(UserGroup.TYPE_USER_PRIVATE);
            userGroup.setAddTime(currentTime);
            userGroup.setMember(uids);
            messageDao.saveUserGroup(userGroup);

            //记录私信双方的关系
            UserGroupRelation targetUser = new UserGroupRelation();
            targetUser.setUid(targetUid);
            targetUser.setGroupId(userGroup.getId());
            targetUser.setReject(UserGroupRelation.REJECT_FALSE);
            targetUser.setAddTime(currentTime);
            messageDao.saveUserGroupRelation(targetUser);

            UserGroupRelation fromUser = new UserGroupRelation();
            fromUser.setUid(uid);
            fromUser.setGroupId(userGroup.getId());
            fromUser.setReject(UserGroupRelation.REJECT_FALSE);
            fromUser.setAddTime(currentTime);
            fromUser.setReadState(UserGroupRelation.READ_STATE_TRUE);
            messageDao.saveUserGroupRelation(fromUser);
        }
        UserGroupRelation myGroupRelation = messageDao.findUserGroupRelationByGroupId(userGroup.getId(), uid, "reject");
        if (myGroupRelation != null) {
            if (UserGroupRelation.REJECT_TRUE.equals(myGroupRelation.getReject())) {
                return new Object[]{4};
            }
        }


        UserGroupRelation userGroupRelation = messageDao.findUserGroupRelationByGroupId(userGroup.getId(), targetUid, "reject");
        if (userGroupRelation != null) {
            if (UserGroupRelation.REJECT_TRUE.equals(userGroupRelation.getReject())) {
                return new Object[] {1};
            }
            Update update = Update.update("lastUpdateTime", currentTime).set("lastContent", content).set("lastMsgUid", uid);
            messageDao.modifyUserGroup(userGroup.getId(), update);
            messageDao.modifyUserGroupRelationById(userGroupRelation.getId(), Update.update("readState", UserGroupRelation.READ_STATE_FALSE));
            messageDao.modifyUserGroupRelationByGroupId(userGroup.getId(), Update.update("state", Constants.STATE_EFFECTIVE));
        }

        Message msg = RedisMsgManager.sendUserPrivateMsg(userGroup.getId(), uid, content);
        return new Object[] {0, userGroup.getId(), msg};
    }

    /**
     * 用户私信分页
     *
     * @param page 分页对象
     */
    public void getUserPrivateMsg(Page<UserGroup> page, Integer uid) {
        {//不下发已经删除的会话
            List<UserGroupRelation> relations = messageDao.findUserGroupRelationByUid(uid, Constants.STATE_EFFECTIVE, "state", "groupId");
            List<Long> groupIds = new ArrayList<>();
            if (!CollectionUtils.isEmpty(relations)) {
                for (UserGroupRelation userGroupRelation : relations) {
                    groupIds.add(userGroupRelation.getGroupId());
                }
            }
            page.getFilter().put("groupIds", groupIds);
        }

        messageDao.userGroupPage(page);
        Map<Integer, UserGroup> maps = new HashMap<>();
        Map<Long, UserGroup> groupMap = new HashMap<>();
        for(UserGroup userGroup : page.getResult()) {
            groupMap.put(userGroup.getId(), userGroup);
            for (Integer fromUid : userGroup.getMember()) {
                if (!uid.equals(fromUid)) {
                    maps.put(fromUid, userGroup);
                }
            }
        }
        //设置对话信息头像
        {
            List<User> users = userDao.findUserByIds(maps.keySet(), "avatar", "name");
            for (User user : users) {
                user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
                maps.get(user.getId()).setUser(user);
            }
        }

        {
            //获取是否屏蔽的用户列表
            List<UserGroupRelation> relations = messageDao.findUserGroupRelationByGroupIds(groupMap.keySet(), uid, "reject", "groupId");
            for (UserGroupRelation relation : relations) {
                UserGroup userGroup = groupMap.get(relation.getGroupId());
                if (userGroup != null) {
                    userGroup.setReject(relation.getReject());
                }
            }
        }
    }

    /**
     * 查询私信
     */
    public void getUserPrivateMsgInfo(Page<Message> page, Long groupId, Integer uid) {
        if (page.getPageNo() == 1) {
            List<Message> messages = messageDao.findMessageByGroupId(groupId, uid.toString(), MsgConstants.CHANNEL_TYPE_USER_PRIVATE_MSG, MsgConstants.HANDLE_STATUS_FALSE);
            if (!CollectionUtils.isEmpty(messages)) {
                page.setSize(messages.size());
                page.setResult(messages);
            } else {
//                messageDao.messagePage(page);
            }
        } else {
//            messageDao.messagePage(page);
        }
        Set<Integer> uids = new HashSet<>();
        Set<Long> msgIds = new HashSet<>();
        for(Message message : page.getResult()) {
            Map<String, String> map = (Map<String, String>) message.getMsgBody();
            uids.add(Integer.parseInt(map.get("fromUid")));
            msgIds.add(message.getId());
        }
        List<User> users = userDao.findUserByIds(uids, "avatar", "name");
        Map<String, User> userMap = new HashMap<>();
        for (User user : users) {
            user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
            userMap.put(user.getId().toString(), user);
        }

        for (Message message : page.getResult()) {
            Map<String, String> map = (Map<String, String>) message.getMsgBody();
            message.setFromUser(userMap.get(map.get("fromUid")));
        }

        messageDao.modifyUserGroupRelationByGroupIdAndUid(groupId, uid, Update.update("readState", UserGroupRelation.READ_STATE_TRUE));
        messageDao.modifyMessageStatus(msgIds, uid);
    }

    /**
     * 设置用户关系
     *
     * @param uid 用户编号
     * @param groupId 群组编号
     */
    public void setUserPrivateMsgReject(Integer uid, Long groupId, Integer handleType) {
        UserGroupRelation userGroupRelation = messageDao.findUserGroupRelationByGroupId(groupId, uid, "reject");
        if (userGroupRelation == null) {
            return;
        }
        messageDao.modifyUserGroupRelationById(userGroupRelation.getId(), Update.update("reject", handleType));
    }

    /**
     * 删除用户私信列表
     * @param uid 用户编号
     * @param groupId 群组编号
     */
    public void deleteUserPrivateMsg(Integer uid, Long groupId) {
        UserGroupRelation userGroupRelation = messageDao.findUserGroupRelationByGroupId(groupId, uid, "state");
        if (userGroupRelation == null) {
            return;
        }
        messageDao.modifyUserGroupRelationById(userGroupRelation.getId(), Update.update("state", Constants.STATE_INVALID));
    }

    /**
     * 获取用户通知
     *
     * @param uid 用户编号
     */
    public List<Message> getUserNotice(Integer uid) {
        List<Message> messages = messageDao.getUserNotice(uid);

        Set<Integer> uids = new HashSet<>();
        for(Message message : messages) {
            Map<String, String> map = (Map<String, String>) message.getMsgBody();
            uids.add(Integer.parseInt(map.get("fromUid")));
        }
        List<User> users = userDao.findUserByIds(uids, "avatar", "name");
        Map<String, User> userMap = new HashMap<>();
        for (User user : users) {
            user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
            userMap.put(user.getId().toString(), user);
        }

        for (Message message : messages) {
            Map<String, String> map = (Map<String, String>) message.getMsgBody();
            message.setFromUser(userMap.get(map.get("fromUid")));
        }

        return messages;
    }

    /**
     * 读取消息
     *
     * @param id 消息编号
     * @param uid 用户编号
     */
    public void readUserMsg(Long id, Integer uid) {
        if (StringUtils.isEmpty(uid)) {
            return;
        }
        messageDao.modifyMessageById(id, uid, Update.update("status", MsgConstants.HANDLE_STATUS_TRUE));
    }
}
