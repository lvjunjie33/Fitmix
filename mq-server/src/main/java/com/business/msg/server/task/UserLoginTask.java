package com.business.msg.server.task;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.TaoBaoIp;

import com.business.core.entity.user.User;

import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;

import com.business.core.utils.HttpUtil;

import com.business.msg.MsgSubscribeAnnotation;
import com.business.msg.core.RedisConcurrentlyCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by edward on 2018/07/13.
 * lvjj add
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_USER_LOGIN_TASK)
public class UserLoginTask implements RedisConcurrentlyCommand {

    private static Logger logger = LoggerFactory.getLogger(UserLoginTask.class);
    @Override
    public void execute(String msgId) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        if (msgId.contains(",")) {
            String[] strs = msgId.split(",");
            String userId=strs[0];
            String ip=strs[1];
            TaoBaoIp taoBaoIp = HttpUtil.ipArea(ip);
            if (taoBaoIp != null) {
                defaultDao.modifyFirst(Query.query(Criteria.where("id").is(userId)), update("taoBaoIp", ip), User.class);
            }
        }
    }
}
