package com.business.msg.server.push;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.activity.Activity;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * APP赛事推送模板消费
 *
 * 通知：发送后会在系统通知栏收到展现，同时响铃或振动提醒用户。
 * 消息：发送后不会在系统通知栏展现，SDK将消息传给第三方应用后需要开发者写展现代码才能看到。
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_ACTIVITY_SEND)
public class ActivityPushCommand extends PushModule implements RedisConcurrentlyCommand {

    public static final String ACTIVITY_SEND_TITLE = "赛事推荐";

    @Override
    public void execute(String msgId) {

        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);

        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Map<String, String> map  = (Map<String, String>) message.getMsgBody();

        Integer activityId = Integer.parseInt(map.get("activityId"));
        Integer targetUid = Integer.parseInt(map.get("targetUid"));

        Activity activity = defaultDao.findOne(Query.query(Criteria.where("id").is(activityId)), Activity.class, "themeName");

        if (activity == null) {
            logger.error("空的");
            return;
        }

        Map<String, String> body = formatMap(activity);
        body.put("channel", MsgConstants.CHANNEL_ACTIVITY_SEND);

        if (StringUtils.isEmpty(targetUid)) {
            pushAllHandle(ACTIVITY_SEND_TITLE, activity.getThemeName(), body);
        } else {
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

            pushHandle(active, terminal, deviceToken, ACTIVITY_SEND_TITLE, activity.getThemeName(), body);
        }
    }

    public Map<String, String> formatMap(Activity activity) {
        Map<String, String> msg = new HashMap<>();
        msg.put("msgBody", JSON.toJSONString(activity));
        return msg;
    }
}
