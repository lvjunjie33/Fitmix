package com.business.msg.server.push;

import com.alibaba.fastjson.JSON;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.club.ClubNotice;
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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edward on 2017/9/19.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_CLUB_NOTICE_SEND)
public class ClubNoticeCommand extends PushModule implements RedisConcurrentlyCommand {

    public static final String MSG_NOTICE_TITLE = "俱乐部通知";

    @Override
    public void execute(String msgId) {
        String code = "";
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Map<String, String> map = (Map<String, String>) message.getMsgBody();
        Long clubMsgId = Long.parseLong(map.get("clubNoticeId"));
        ClubNotice clubNotice = defaultDao.findOne(Query.query(Criteria.where("id").is(clubMsgId)), ClubNotice.class);

        if (clubNotice == null) {
            logger.error("空的");
            return;
        }

        Criteria criteria = Criteria.where("id").is(clubNotice.getUid()).and("terminal").exists(true);

        User user = defaultDao.findOne(Query.query(criteria), User.class, "deviceToken", "terminal", "name", "avatar");
        user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
        clubNotice.setUser(user);

        Map<String, String> body = formatMap(clubNotice);
        body.put("channel", MsgConstants.CHANNEL_CLUB_NOTICE_SEND);

        Integer targetUid = Integer.parseInt(map.get("uid"));

        //查询设备信息
        UserDevice device = defaultDao.findOne(Query.query(Criteria.where("uid").is(targetUid)), UserDevice.class, "active", "deviceToken", "terminal");
        Integer active = UserDevice.ACTIVE_FALSE;
        if (device != null) {
            active = device.getActive();
        }
        User targetUser = defaultDao.findOne(Query.query(Criteria.where("id").is(targetUid).and("terminal").exists(true)), User.class, "deviceToken", "terminal");

        String deviceToken = targetUser.getDeviceToken();
        Integer terminal = targetUser.getTerminal();
        if (StringUtils.isEmpty(deviceToken)) {
            deviceToken = device.getDeviceToken();
        }
        if (StringUtils.isEmpty(terminal)) {
            terminal = device.getTerminal();
        }

        pushHandle(active, terminal, deviceToken, MSG_NOTICE_TITLE, clubNotice.getContent(), body);

        defaultDao.modifyFirst(Query.query(Criteria.where("id").is(message.getId())), Update.update("status", MsgConstants.HANDLE_STATUS_TRUE), Message.class);
    }

    public Map<String, String> formatMap(ClubNotice clubNotice) {
        Map<String, String> msg = new HashMap<>();
        msg.put("msgBody", JSON.toJSONString(clubNotice));
        return msg;
    }
}
