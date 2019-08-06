package com.business.msg.server.task;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.UserRunRank;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import com.business.msg.MsgSubscribeAnnotation;
import com.business.msg.core.RedisConcurrentlyCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

/**
 * Created by edward on 2017/10/30.
 *
 * 榜单统计
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_USER_RUN_RANK_TASK)
public class UserRunRankTask implements RedisConcurrentlyCommand {

    private static Logger logger = LoggerFactory.getLogger(UserRunRankTask.class);

    @Override
    public void execute(String msgId) {

        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Long userRunId = Long.parseLong(message.getMsgBody().toString());
        UserRun userRun = defaultDao.findOne(Query.query(Criteria.where("id").is(userRunId)), UserRun.class);

        Integer uid = userRun.getUid();

        try {
            // 每日榜单
            String day = DateUtil.formatTimestamp(userRun.getStartTime(), "yyyy-MM-dd");
            UserRunRank userRunRank = defaultDao.findOne(Query.query(Criteria.where("uid").is(uid).and("type").is(UserRunRank.TYPE_DAY).and("typeValue").is(day)), UserRunRank.class);
            updateUserRunRank(UserRunRank.TYPE_DAY, day, userRunRank, userRun, defaultDao);

            //每周榜单
            String weekStart = DateUtil.format(DateUtil.getWeekBegin(new Date(userRun.getStartTime())), "yyyy-MM-dd");
            String weekEnd = DateUtil.format(DateUtil.getWeekEnd(new Date(userRun.getStartTime())), "yyyy-MM-dd");
            String typeValue = weekStart + "~" + weekEnd;
            userRunRank = defaultDao.findOne(Query.query(Criteria.where("uid").is(uid).and("type").is(UserRunRank.TYPE_WEEK).and("typeValue").is(typeValue)), UserRunRank.class);
            updateUserRunRank(UserRunRank.TYPE_WEEK, typeValue, userRunRank, userRun, defaultDao);

            //月榜单
            String month = DateUtil.formatTimestamp(userRun.getStartTime(), "yyyy-MM");
            userRunRank = defaultDao.findOne(Query.query(Criteria.where("uid").is(uid).and("type").is(UserRunRank.TYPE_MONTH).and("typeValue").is(month)), UserRunRank.class);
            updateUserRunRank(UserRunRank.TYPE_MONTH, month, userRunRank, userRun, defaultDao);

            logger.error("Run Rank End : messageId={}, uid={}, userRunId={}, SUCCESS", message.getId(), uid, userRunId);
        } catch (Exception e) {
            logger.error("Run Rank End : messageId={}, uid={}, userRunId={}, ERROR={}", message.getId(), uid, userRunId, e.getMessage());
        }

    }

    /**
     * 更新榜单信息
     * @param type
     * @param typeValue
     * @param userRunRank
     * @param userRun
     */
    public void updateUserRunRank(Integer type, String typeValue, UserRunRank userRunRank, UserRun userRun, DefaultDao defaultDao) {
        if (null != userRunRank) {
            Update update = new Update();
            update.inc("distance", userRun.getDistance());
            defaultDao.modifyFirst(Query.query(Criteria.where("id").is(userRunRank.getId())), update, UserRunRank.class);
        } else {
            userRunRank = new UserRunRank();
            userRunRank.setType(type);
            userRunRank.setTypeValue(typeValue);
            userRunRank.setDistance(userRun.getDistance());
            userRunRank.setUid(userRun.getUid());
            userRunRank.setAddTime(System.currentTimeMillis());
            defaultDao.save(userRunRank);
        }
    }
}
