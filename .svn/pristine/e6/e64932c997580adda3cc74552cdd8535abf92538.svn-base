package com.business.msg.server.push;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.entity.mix.Mix;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edward on 2017/10/13.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_TYPE_MIX_SEND)
public class MixPushCommand extends PushModule implements RedisConcurrentlyCommand {

    private static final String MIX_RECOMMEND_TITLE = "推荐Mix";

    @Override
    public void execute(String msgId) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Map<String, String> map  = (Map<String, String>) message.getMsgBody();

        String mixId = map.get("mixId");
        String targetUid = map.get("targetUid");

        Mix mix = defaultDao.findOne(Query.query(Criteria.where("id").is(Integer.parseInt(mixId)).and("type").ne(Mix.TYPE_RADIO)), Mix.class, "name");

        if (mix == null) {
            logger.error("空的");
            return;
        }

        Map<String, String> body = formatMap(mix);
        body.put("channel", message.getSelectChannel());

        if (StringUtils.isEmpty(targetUid)) {
            pushAllHandle(MIX_RECOMMEND_TITLE, mix.getName(), body);
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

            pushHandle(active, terminal, deviceToken, MIX_RECOMMEND_TITLE, mix.getName(), body);
        }
    }

    public Map<String, String> formatMap(Mix mix) {
        Map<String, String> msg = new HashMap<>();
        msg.put("msgBody", JSON.toJSONString(mix));
        return msg;
    }
}
