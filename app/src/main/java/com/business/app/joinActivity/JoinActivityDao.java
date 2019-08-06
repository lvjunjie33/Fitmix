package com.business.app.joinActivity;

import com.business.core.entity.joinActivity.JoinActivity;
import com.business.core.entity.joinActivity.JoinActivityEntered;
import com.business.core.entity.joinActivity.JoinActivityViewLog;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by fenxio on 2016/5/17.
 */
@Repository
public class JoinActivityDao extends BaseMongoDaoSupport {


    /**
     * 根据 活动编号和渠道获取 赛事
     * @param activityId 活动编号
     * @param channel 渠道
     */
    public JoinActivity findJoinActivityByActivityIdAndChannel(String activityId, Integer channel) {
        Query query = Query.query(Criteria.where("activityId").is(activityId).and("channel").is(channel));
        return getRoutingMongoOps().findOne(query, JoinActivity.class);
    }

    /**
     * 添加第三方赛事
     * @param joinActivity 三方赛事
     */
    public void insertJoinActivity(JoinActivity joinActivity) {
        insertToMongo(joinActivity);
    }

    /**
     * 获取通知信息
     * @param notifyId 通知
     */
    public JoinActivityEntered findJoinActivityEnteredByNotifyId(String notifyId) {
        Query query = Query.query(Criteria.where("notifyId").is(notifyId));
        return getRoutingMongoOps().findOne(query, JoinActivityEntered.class);
    }

    /**
     * 添加回调通知
     * @param joinActivityEntered 回调信息
     */
    public void insertJoinActivityEntered(JoinActivityEntered joinActivityEntered) {
        insertToMongo(joinActivityEntered);
    }

    /**
     * 添加 第三方赛事访问日志
     * @param joinActivityViewLog 访问日志
     */
    public void insertJoinActivityViewLog(JoinActivityViewLog joinActivityViewLog) {
        insertToMongo(joinActivityViewLog);
    }
}
