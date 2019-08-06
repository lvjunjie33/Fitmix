package com.business.msg.server.push.theme;

import com.alibaba.fastjson.JSON;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 话题推荐推送
 *
 * Created by edward on 2017/9/20.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_THEME_RECOMMEND_SEND)
public class ThemeRecommendCommand extends PushModule implements RedisConcurrentlyCommand {

    private static final String THEME_RECOMMEND_TITLE = "推荐话题";

    private static Logger logger = LoggerFactory.getLogger(ThemeRecommendCommand.class);

    @Override
    public void execute(String msgId) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Map<String, String> map  = (Map<String, String>) message.getMsgBody();

        Long themeId = Long.parseLong(map.get("themeId"));
        Integer targetUid = Integer.parseInt(map.get("targetUid"));

        Theme theme = defaultDao.findOne(Query.query(Criteria.where("id").is(themeId)), Theme.class, "title");

        if (theme == null) {
            logger.error("空的");
            return;
        }

        theme.setContent(null);

        Map<String, String> body = formatMap(theme);
        body.put("channel", message.getSelectChannel());

        if (StringUtils.isEmpty(targetUid)) {
            pushAllHandle(THEME_RECOMMEND_TITLE, theme.getTitle(), body);
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

            pushHandle(active, terminal, deviceToken, THEME_RECOMMEND_TITLE, theme.getTitle(), body);
        }
    }

    public Map<String, String> formatMap(Theme theme) {
        Map<String, String> msg = new HashMap<>();
        msg.put("msgBody", JSON.toJSONString(theme));
        return msg;
    }
}
