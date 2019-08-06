package com.business.msg.server.push;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.User;
import com.business.core.entity.user.info.UserDevice;
import com.business.core.entity.user.info.UserGroup;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.msg.MsgSubscribeAnnotation;
import com.business.msg.core.PushModule;
import com.business.msg.core.RedisConcurrentlyCommand;
import com.business.msg.util.PushUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edward on 2017/11/1.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_TYPE_USER_PRIVATE_MSG)
public class UserPrivateMsgPushCommand extends PushModule implements RedisConcurrentlyCommand {

    public static final String USER_PRIVATE_MSG_TITLE = "私信";

    @Override
    public void execute(String msgId) {
        String code = "";
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Map<String, String> map = (Map<String, String>) message.getMsgBody();
        String groupId = map.get("groupId");
        String content = map.get("content");

        UserGroup userGroup = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(groupId))), UserGroup.class);

        if (userGroup == null) {
            logger.error("空的");
            return;
        }

        if (content.length() > 50) {
            content = content.substring(0, 50);
        }

        Map<String, Object> msgBody = new HashMap<>();
        msgBody.put("groupId", userGroup.getId());
        Integer fromUid = userGroup.getLastMsgUid();

        Map<String, String> body = formatMap(msgBody);
        body.put("channel", MsgConstants.CHANNEL_TYPE_USER_PRIVATE_MSG);

        for (Integer uid : userGroup.getMember()) {
            if (fromUid.equals(uid)) {
                    continue;
            }
            //查询设备信息
            UserDevice device = defaultDao.findOne(Query.query(Criteria.where("uid").is(uid)), UserDevice.class, "active", "deviceToken", "terminal");
            Integer active = UserDevice.ACTIVE_FALSE;
            if (device != null) {
                active = device.getActive();
            }
            User targetUser = defaultDao.findOne(Query.query(Criteria.where("id").is(uid).and("terminal").exists(true)), User.class, "deviceToken", "terminal");
            String deviceToken = targetUser.getDeviceToken();
            Integer terminal = targetUser.getTerminal();
            if (StringUtils.isEmpty(deviceToken)) {
                deviceToken = device.getDeviceToken();
            }
            if (StringUtils.isEmpty(terminal)) {
                terminal = device.getTerminal();
            }
            pushHandle(active, terminal, deviceToken, USER_PRIVATE_MSG_TITLE, content, body);
        }

//        defaultDao.modifyFirst(Query.query(Criteria.where("id").is(message.getId())), Update.update("status", MsgConstants.HANDLE_STATUS_TRUE), Message.class);
    }

    public Map<String, String> formatMap(Map<String, Object> msgBody) {
        Map<String, String> msg = new HashMap<>();
        msg.put("msgBody", JSON.toJSONString(msgBody));
        return msg;
    }
}
