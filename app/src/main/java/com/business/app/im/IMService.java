package com.business.app.im;

import com.business.app.base.support.BaseServiceSupport;
import com.business.app.easeMob.EaseMobManager;
import com.business.app.easeMob.api.impl.EasemobChatGroup;
import com.business.app.easeMob.api.impl.EasemobIMUsers;
import com.business.app.easeMob.api.impl.EasemobSendMessage;
import com.business.app.easeMob.comm.EasemobRestAPIFactory;
import com.business.app.easeMob.comm.body.*;
import com.business.app.easeMob.comm.constant.MsgTargetType;
import com.business.app.easeMob.comm.wrapper.ResponseWrapper;
import com.business.app.user.UserDao;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.im.IMInfo;
import com.business.core.entity.im.IMInfoGroup;
import com.business.core.entity.im.IMInfoUser;
import com.business.core.entity.user.User;
import com.business.core.utils.CollectionUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by edward on 2016/10/17.
 */
@Service
public class IMService extends BaseServiceSupport {

    @Autowired
    private IMDao imDao;
    @Autowired
    private UserDao userDao;

    /**
     * 注册 环信帐号
     */
    public void registerIM(User user) {
        String username = "im" + user.getId();
        IMInfoUser imInfoUser = new IMInfoUser();
        imInfoUser.setType(IMInfo.USER_TYPE);
        imInfoUser.setState(Constants.STATE_EFFECTIVE);
        imInfoUser.setUid(user.getId());
        imInfoUser.setAddTime(System.currentTimeMillis());
        imInfoUser.setUsername(username);

        EasemobIMUsers easemobIMUsers = EaseMobManager.getOperationClass(EasemobRestAPIFactory.USER_CLASS);
        Map<String, Object> createInfo = checkEaseMobImUser(easemobIMUsers, username);
        if (createInfo == null) {// 新用户
            imInfoUser.setPassword(getPassword());
            imInfoUser.setNickname(user.getName());

            //创建帐号
            IMUserBody imUserBody = new IMUserBody(imInfoUser.getUsername(), imInfoUser.getPassword() , imInfoUser.getNickname());
            ResponseWrapper responseWrapper = (ResponseWrapper)easemobIMUsers.createNewIMUserSingle(imUserBody);

            //解析返回的注册信息
            imInfoUser.setCreateInfo(parseEaseMobImUserBody(responseWrapper));
            //保存环信用户帐号信息
            imDao.saveIMInfoUser(imInfoUser);
            return;
        }

        //帐号已经存在
        if (createInfo.containsKey("nickname")) {
            imInfoUser.setNickname(createInfo.get("nickname").toString());
            createInfo.remove("nickname");
        }
        imInfoUser.setCreateInfo(createInfo);
        imInfoUser.setPassword(getPassword());
        // 重置该用户的环信密码
        ResetPasswordBody imUserBody = new ResetPasswordBody(imInfoUser.getPassword());
        ResponseWrapper responseWrapper = (ResponseWrapper) easemobIMUsers.modifyIMUserPasswordWithAdminToken(username, imUserBody);
        //保存环信用户帐号信息
        imDao.saveIMInfoUser(imInfoUser);
    }

    /**
     * 检测该环信帐号是否被注册了
     *
     * @param easemobIMUsers 环信api调用操作对象
     * @param username 环信帐号
     */
    public Map<String, Object> checkEaseMobImUser(EasemobIMUsers easemobIMUsers ,String username) {
        ResponseWrapper responseWrapper = (ResponseWrapper)easemobIMUsers.getIMUsersByUserName(username);

        if (responseWrapper.getResponseStatus() == 200) {
            return parseEaseMobImUserBody(responseWrapper);
        } else {
            return null;
        }
    }

    /**
     * 解析帐号注册，已经获取帐号信息时，返回消息的解析
     * @param responseWrapper 消息body
     */
    private Map<String, Object> parseEaseMobImUserBody(ResponseWrapper responseWrapper) {
        //解析返回的注册信息
        ObjectNode nodes = (ObjectNode)responseWrapper.getResponseBody();
        Map<String, Object> createInfo = new HashMap<>();

        JsonNode jsonNode = nodes.get("entities");
        JsonNode node = jsonNode.get(0);

        //记录注册信息
        createInfo.put("uuid", node.get("uuid").textValue());
        createInfo.put("type", node.get("type").textValue());
        createInfo.put("created", node.get("created").textValue());
        createInfo.put("modified", node.get("modified").textValue());
        createInfo.put("username", node.get("username").textValue());
        createInfo.put("activated", node.get("activated").textValue());
        if (node.get("nickname") != null) {
            createInfo.put("nickname", node.get("nickname").textValue());
        }
        return createInfo;
    }

    /**
     * 设置环信用户信息
     *
     * @param user app 用户
     */
    public void setIMInfoUser(User user) {
        Integer uid = user.getId();
        IMInfoUser imInfoUser = imDao.findIMInfoUser(uid, "username", "password");
        if (imInfoUser == null) {
            registerIM(user);
            try {
                Thread.sleep(3000);// 避免刚注册帐号，立马登录，导致app 端登录环信帐号失败(因该是环信那边有异步任务)
            } catch (Exception e) {
                e.printStackTrace();
            }
            imInfoUser = imDao.findIMInfoUser(uid, "username", "password");
        }
        user.setImInfoUser(imInfoUser);
    }

    /**
     * 获取目标用户位置信息
     *
     * @param usernames 环信帐号
     */
    public List<IMInfoUser> getPositions(List<String> usernames) {
        return imDao.findIMInfoUserByUsernames(usernames, "lng", "lat");
    }

    /**
     *  修改用户当前的位置
     *
     * @param username 环信帐号
     * @param lng 经度
     * @param lat 纬度
     */
    public void modifyPosition(String username, Double lng, Double lat) {
        imDao.modifyIMInfoUserByUsername(username, Update.update("lng", lng).set("lat", lat));
    }

    private String getPassword() {
        String password = System.currentTimeMillis() + "";
        int i = new Random().nextInt(6);
        password = password.substring(i, i + 7);
        return password;
    }

    /**
     * 查询群组信息
     *
     * @param chatName 群组信息
     */
    public Map<String, Object> chatShareInfo(String chatName, String imUserName){
        EasemobChatGroup easemobChatGroup = EaseMobManager.getOperationClass(EasemobRestAPIFactory.CHATGROUP_CLASS);
        ResponseWrapper responseWrapper = (ResponseWrapper)easemobChatGroup.getChatGroupDetails(new String[]{chatName});
        //解析返回的注册信息
        ObjectNode nodes = (ObjectNode)responseWrapper.getResponseBody();
        Map<String, Object> chatInfo = new HashMap<>();
        int size = nodes.size();
        if (size <= 0) {
            return Collections.emptyMap();
        }

        JsonNode jsonNode = nodes.get("data").get(0);

        chatInfo.put("chatName", jsonNode.get("name").textValue());
        chatInfo.put("chatDes", jsonNode.get("description").textValue());
        chatInfo.put("inviteUserName", imUserName);

        IMInfoUser imInfoUser = imDao.findIMInfoUserByUsername(imUserName);
        if (imInfoUser == null) {
            chatInfo.put("inviteNickName", "");
        } else {
            chatInfo.put("inviteNickName", imInfoUser.getNickname());
        }
        return chatInfo;
    }

    /**\
     * 创建环信群组
     *
     * @param userName 环信用户帐号
     * @param groupName 群组名称
     * @param groupPassword 群组加入密码
     * @param groupDes 群组描述
     */
    public IMInfoGroup addGroup(String userName, String groupName, String groupPassword, String groupDes) {
        EasemobChatGroup easemobChatGroup = EaseMobManager.getOperationClass(EasemobRestAPIFactory.CHATGROUP_CLASS);
        ChatGroupBody chatGroupBody = new ChatGroupBody(groupName, groupDes, true, 500L, true, userName, new String[]{userName});
        ResponseWrapper responseWrapper = (ResponseWrapper)easemobChatGroup.createChatGroup(chatGroupBody);
        ObjectNode nodes = (ObjectNode)responseWrapper.getResponseBody();
        int size = nodes.size();
        if (size <= 0) {
            return null;
        }

        String groupId = nodes.get("data").get("groupid").textValue();

        IMInfoGroup imInfoGroup = new IMInfoGroup();
        imInfoGroup.setGroupId(groupId);
        imInfoGroup.setGroupName(groupName);
        imInfoGroup.setPassword(groupPassword);
        imInfoGroup.setDes(groupDes);
        imInfoGroup.setCreateIMUserName(userName);

        imInfoGroup.setAddTime(System.currentTimeMillis());
        imInfoGroup.setType(IMInfo.GROUP_TYPE);
        imInfoGroup.setState(Constants.STATE_EFFECTIVE);
        imInfoGroup.setSort(IMInfoGroup.DEFAULT_SORT);
        imDao.saveIMInfoGroup(imInfoGroup);
        return imInfoGroup;
    }

    /**
     * 修改群组加入密码
     *
     * @param groupPassword 群组密码
     * @param groupId 群组编号
     */
    public void modifyGroupPassword(String groupPassword, String groupId) {
        imDao.modifyIMInfoGroup(groupId, Update.update("groupPassword", groupPassword));
    }

    /**
     * 修改环信群组信息
     *
     * @param groupId 群组编号
     * @param groupName 群组名称
     * @param groupDes 群组描述
     */
    public boolean modifyGroupInfo(String groupId, String groupName, String groupDes) {
        EasemobChatGroup easemobChatGroup = EaseMobManager.getOperationClass(EasemobRestAPIFactory.CHATGROUP_CLASS);
        ModifyChatGroupBody modifyChatGroupBody = new ModifyChatGroupBody(groupName, groupDes, 500L);
        ResponseWrapper responseWrapper = (ResponseWrapper)easemobChatGroup.modifyChatGroup(groupId, modifyChatGroupBody);
        return responseWrapper.getResponseStatus() == 200;
    }

    /**
     * 环信群组 分页 查询
     * @param page 分页对象
     */
    public void groupPage(Page<IMInfoGroup> page) {
        page.setSize(Page.DEFAULT_PAGE_SIZE);
        imDao.groupPage(page, IMInfoGroup.fields);
    }

    /**
     * 加入群聊
     *
     * @param userName 环信用户名
     * @param groupId 群组帐号
     * @param password 加入校验密码
     */
    public int addGroupUser(String userName, String groupId, String password) {
        IMInfoGroup group = imDao.findGroup(groupId, "password");
        if (group == null) {
            return 3;
        }
        if (StringUtils.isEmpty(group.getPassword()) && StringUtils.isEmpty(password)) {

        } else if (!StringUtils.isEmpty(group.getPassword()) && group.getPassword().equals(password)) {//密码不为空，则判断是否相等

        } else {
            return 1;
        }

        EasemobChatGroup easemobChatGroup = EaseMobManager.getOperationClass(EasemobRestAPIFactory.CHATGROUP_CLASS);
        ResponseWrapper responseWrapper = (ResponseWrapper)easemobChatGroup.addSingleUserToChatGroup(groupId, userName);

        return responseWrapper.getResponseStatus() != 200 ? 2 : 0;
    }

    /**
     * 获取用户头像
     *
     * @param userNames 用户名
     */
    public List<IMInfoUser> findUsersAvatar(String[] userNames) {
        List<IMInfoUser> imInfoUsers = imDao.findUserNames(userNames, "uid", "username");
        if (CollectionUtils.isEmpty(imInfoUsers)) {
            return Collections.EMPTY_LIST;
        }
        List<User> users = userDao.findUserByIds(CollectionUtil.buildSet(imInfoUsers, Integer.class, "uid"), "avatar", "name");
        if (CollectionUtils.isEmpty(users)) {
            return Collections.EMPTY_LIST;
        }

        Map<Integer, User> userMap = CollectionUtil.buildMap(users, Integer.class, User.class, "id");

        for (IMInfoUser imInfoUser : imInfoUsers) {
            Integer uid = imInfoUser.getUid();
            if (userMap.containsKey(uid)) {
                User user = userMap.get(uid);
                String avatar = user.getAvatar();
                if (!StringUtils.isEmpty(avatar)) {
                    imInfoUser.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
                }
                imInfoUser.setNickname(user.getName());
            }
        }
        return imInfoUsers;
    }

    /**
     * 退群、解散群
     *
     * @param userName 用户名
     * @param groupId 群组编号
     */
    public int quitGroup(String userName, String groupId) {
        EasemobChatGroup easemobChatGroup = EaseMobManager.getOperationClass(EasemobRestAPIFactory.CHATGROUP_CLASS);
        ResponseWrapper responseWrapper = (ResponseWrapper)easemobChatGroup.getChatGroupDetails(new String[]{groupId});
        //解析返回的注册信息
        ObjectNode nodes = (ObjectNode)responseWrapper.getResponseBody();
        int size = nodes.size();
        if (size <= 0) {
            return 2;
        }

        Iterator<JsonNode> iterator = nodes.get("data").get(0).get("affiliations").elements();
        Map<String, String> members = new HashMap<>();
        while (iterator.hasNext()){
            JsonNode jsonNode = iterator.next();
            jsonNode.textValue();
            if (jsonNode.get("member") != null) {
                members.put(jsonNode.get("member").textValue(), "member");
            } else if (jsonNode.get("owner") != null) {
                members.put(jsonNode.get("owner").textValue(), "owner");
            }
        }

        if (!members.containsKey(userName)) {//不在该群组里面
            return 3;
        }

        if (members.get(userName).equals("member")) {//退出群
            ResponseWrapper rw = (ResponseWrapper)easemobChatGroup.removeSingleUserFromChatGroup(groupId, userName);
            if (rw.getResponseStatus() != 200) {//退出失败-未知的原因
                return 2;
            }
            //TODO 异步消息 通知消息

        } else {//群主解散群
            ResponseWrapper rw = (ResponseWrapper)easemobChatGroup.deleteChatGroup(groupId);
            if (rw.getResponseStatus() != 200) {//解散失败-未知的原因
                return 2;
            }
            //关闭数据库的群
            imDao.modifyIMInfoGroup(groupId, Update.update("state", Constants.STATE_INVALID));

            //异步消息 通知群成员该群已经被解散
            if (members.size() > 1) {
                String[] targets = new String[members.size()];
                Object[] objs = members.keySet().toArray();
                for (int i = 0; i < targets.length; i++) {
                    targets[i] = objs[i].toString();
                }
                EasemobSendMessage easemobSendMessage= EaseMobManager.getOperationClass(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
                TextMessageBody textMessageBody = new TextMessageBody(MsgTargetType.USERS, targets, "系统公告",
                        Collections.EMPTY_MAP, "【" + nodes.get("data").get(0).get("name") + "】聊天室已经被群主解散了");
                easemobSendMessage.sendMessage(textMessageBody);
            }
        }
        return 1;
    }

}
