package com.business.msg.server.push;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.msg.Message;
import com.business.core.entity.runPlan.Stages;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.entity.user.User;
import com.business.core.entity.user.info.UserDevice;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import com.business.msg.MsgSubscribeAnnotation;
import com.business.msg.core.PushModule;
import com.business.msg.core.RedisConcurrentlyCommand;
import com.business.msg.util.PushUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2017/9/19.
 *
 * 训练计划 每日计划提醒
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_USER_RUN_PLAN_SEND)
public class UserRunPlanPushCommand extends PushModule implements RedisConcurrentlyCommand {

    private static final String USER_RUN_PLAN_TITLE = "训练计划";

    @Override
    public void execute(String msgId) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Map<String, String> map  = (Map<String, String>) message.getMsgBody();
        Integer targetUid = Integer.parseInt(map.get("targetUid"));
        String content = map.get("content");

        Map<String, String> body = formatMap(new HashMap<String, Object>());
        body.put("channel", message.getSelectChannel());

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

        pushHandle(active, terminal, deviceToken, USER_RUN_PLAN_TITLE, content, body);
    }

    public Map<String, String> formatMap(Map<String, Object> msgBody) {
        Map<String, String> msg = new HashMap<>();
        msg.put("msgBody", JSON.toJSONString(msgBody));
        return msg;
    }

}
