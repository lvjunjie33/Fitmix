package com.business.msg.server.push;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.User;
import com.business.core.entity.user.info.UserDevice;
import com.business.core.entity.video.Video;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edward on 2017/10/13.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_TYPE_VIDEO)
public class VideoPushCommand extends PushModule implements RedisConcurrentlyCommand {

    private static final String VIDEO_RECOMMEND_TITLE = "推荐电台";

    @Override
    public void execute(String msgId) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Map<String, String> map  = (Map<String, String>) message.getMsgBody();

        String videoId = map.get("videoId");
        String targetUid = map.get("targetUid");

        Video video = defaultDao.findOne(Query.query(Criteria.where("id").is(Integer.parseInt(videoId))), Video.class, "title");

        if (video == null) {
            logger.error("空的");
            return;
        }

        Map<String, String> body = formatMap(video);
        body.put("channel", message.getSelectChannel());

        if (StringUtils.isEmpty(targetUid)) {
            pushAllHandle(VIDEO_RECOMMEND_TITLE, video.getTitle(), body);
        } else {
            //查询设备信息
            UserDevice device = defaultDao.findOne(Query.query(Criteria.where("uid").is(Integer.parseInt(targetUid))), UserDevice.class, "active", "deviceToken", "terminal");
            Integer active = UserDevice.ACTIVE_FALSE;
            if (device != null) {
                active = device.getActive();
            }
            User targetUser = defaultDao.findOne(Query.query(Criteria.where("id").is(Integer.parseInt(targetUid)).and("terminal").exists(true)), User.class, "deviceToken", "terminal");

            String deviceToken = targetUser.getDeviceToken();
            Integer terminal = targetUser.getTerminal();
            if (StringUtils.isEmpty(deviceToken)) {
                deviceToken = device.getDeviceToken();
            }
            if (StringUtils.isEmpty(terminal)) {
                terminal = device.getTerminal();
            }

            pushHandle(active, terminal, deviceToken, VIDEO_RECOMMEND_TITLE, video.getTitle(), body);
        }
    }

    public Map<String, String> formatMap(Video video) {
        Map<String, String> msg = new HashMap<>();
        msg.put("msgBody", JSON.toJSONString(video));
        return msg;
    }
}
