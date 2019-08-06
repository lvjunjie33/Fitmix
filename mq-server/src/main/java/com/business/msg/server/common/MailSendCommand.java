package com.business.msg.server.common;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.msg.Message;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.msg.MsgSubscribeAnnotation;
import com.business.msg.core.RedisConcurrentlyCommand;
import com.business.msg.util.MailUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Map;

/**
 * Created by edward on 2017/9/18.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_MAIL_SEND)
public class MailSendCommand implements RedisConcurrentlyCommand {

    public void execute(String msgId) {

        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);
        Map<String, String> map = (Map<String, String>) message.getMsgBody();
        try {
            MailUtil.defaultSend(map.get("email"), map.get("titile"), map.get("content"));
            defaultDao.modifyFirst(Query.query(Criteria.where("id").is(message.getId())), Update.update("status", MsgConstants.HANDLE_STATUS_TRUE), Message.class);
        } catch (Exception e) {
        }
    }
}
