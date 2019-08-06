package com.business.msg.server.push.theme;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.community.discuss.Theme;
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
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 话题回答 推送
 *
 * Created by edward on 2017/11/14.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_THEME_ANSWER)
public class ThemeBeAnswerCommand extends PushModule implements RedisConcurrentlyCommand {

    private static Logger logger = LoggerFactory.getLogger(ThemeBeAnswerCommand.class);

    private static final String THEME_ANSWER_TITLE = "{0}回答了你的问题";

    @Override
    public void execute(String msgId) {

        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Map<String, String> map  = (Map<String, String>) message.getMsgBody();

        Long answerId = Long.parseLong(map.get("answerId"));
        Integer targetUid = Integer.parseInt(map.get("targetUid"));
        Integer fromUid = Integer.parseInt(map.get("fromUid"));

        Theme theme = defaultDao.findOne(Query.query(Criteria.where("id").is(answerId)), Theme.class, "title");
        if (theme == null) {
            logger.error("theme == null");
            return;
        }

        User fromUser = defaultDao.findOne(Query.query(Criteria.where("id").is(fromUid)), User.class, "name");

        User targetUser = defaultDao.findOne(Query.query(Criteria.where("id").is(targetUid).and("terminal").exists(true)), User.class, "deviceToken", "terminal");

        if (fromUser == null || targetUid == null) {
            logger.error("fromUser == null || targetUser == null");
            return;
        }

        Map<String, Object> msgBody = new HashMap<>();
        msgBody.put("answerId", answerId);

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

        pushHandle(active, terminal, deviceToken, MessageFormat.format(THEME_ANSWER_TITLE, fromUser.getName()), map.get("content"), body);
    }

    public Map<String, String> formatMap(Map<String, Object> msgBody) {
        Map<String, String> msg = new HashMap<>();
        msg.put("msgBody", JSON.toJSONString(msgBody));
        return msg;
    }
}
