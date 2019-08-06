package com.business.msg.server.push.theme;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.community.discuss.Discuss;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.User;
import com.business.core.entity.user.info.UserDevice;
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
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 回答评论推送
 *
 * Created by edward on 2017/11/14.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_ANSWER_DISCUSS)
public class AnswerBeDiscussCommand extends PushModule implements RedisConcurrentlyCommand {

    private static final String ANSWER_DISCUSS_TITLE = "{0}评论了你";

    @Override
    public void execute(String msgId) {

        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Map<String, String> map  = (Map<String, String>) message.getMsgBody();

        Long discussId = Long.parseLong(map.get("discussId"));
        Integer targetUid = Integer.parseInt(map.get("targetUid"));
        Integer fromUid = Integer.parseInt(map.get("fromUid"));

        User fromUser = defaultDao.findOne(Query.query(Criteria.where("id").is(fromUid)), User.class, "name");

        User targetUser = defaultDao.findOne(Query.query(Criteria.where("id").is(targetUid).and("terminal").exists(true)), User.class, "deviceToken", "terminal");

        Assert.notNull(fromUser);
        Assert.notNull(targetUser);

        Map<String, Object> msgBody = new HashMap<>();
        msgBody.put("discussId", discussId);
        Map<String, String> body = formatMap(msgBody);
        body.put("channel", message.getSelectChannel());

        //查询设备信息
        UserDevice device = defaultDao.findOne(Query.query(Criteria.where("uid").is(targetUid)), UserDevice.class, "active", "deviceToken", "terminal");
        Integer active = UserDevice.ACTIVE_FALSE;
        if (device != null) {
            active = device.getActive();
        }

        String deviceToken = targetUser.getDeviceToken();
        Integer terminal = targetUser.getTerminal();
        if (StringUtils.isEmpty(deviceToken)) {
            deviceToken = device.getDeviceToken();
        }
        if (StringUtils.isEmpty(terminal)) {
            terminal = device.getTerminal();
        }

        pushHandle(active, terminal, deviceToken, MessageFormat.format(ANSWER_DISCUSS_TITLE, fromUser.getName()), map.get("content"), body);
    }

    public Map<String, String> formatMap(Map<String, Object> msgBody) {
        Map<String, String> msg = new HashMap<>();
        msg.put("msgBody", JSON.toJSONString(msgBody));
        return msg;
    }
}
