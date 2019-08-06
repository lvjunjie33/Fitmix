package com.business.msg.server.common;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.msg.Message;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.msg.MsgSubscribeAnnotation;
import com.business.msg.core.RedisConcurrentlyCommand;
import com.business.msg.util.YunPianUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Map;

/**
 * Created by edward on 2016/4/20.
 * 短信发送
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_SMS_SEND)
public class SmsSendCommand implements RedisConcurrentlyCommand {

    @Override
    public void execute(String id) {

        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(id))), Message.class);
        Map<String, String> map = (Map<String, String>) message.getMsgBody();
        String mobile = map.get("mobile");
        Integer status = MsgConstants.HANDLE_STATUS_TRUE;
        try {
            //短信群发
            String codes = YunPianUtil.sendSms(map.get("content").toString(), mobile);
            if (codes != "200") {
                status = MsgConstants.HANDLE_STATUS_FALSE;
            }
        } catch (Exception e) {
            status = MsgConstants.HANDLE_STATUS_ERROR;
        } finally {
            defaultDao.modifyFirst(Query.query(Criteria.where("id").is(message.getId())), Update.update("status", status), Message.class);
        }
    }
}
