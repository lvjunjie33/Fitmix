package com.business.app.activityOther;

import com.business.core.entity.activityOther.ActivityJiaFeiMao;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Sin on 2016/2/17.
 */
@Repository
public class ActivityJiaFeiMaoDao extends BaseMongoDaoSupport {

    /**
     * 添加 加菲猫活动数据
     * @param activityJiaFeiMao 加菲猫信息
     */
    public void insertActivityJiaFeiMao(ActivityJiaFeiMao activityJiaFeiMao) {
        insertToMongo(activityJiaFeiMao);
    }

    /**
     * 更新活动 加菲猫
     * @param id 编号
     * @param update 更新信息
     */
    public void updateActivityJiaFeiMaoById(Integer id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, ActivityJiaFeiMao.class);
    }

    /**
     * 根据 id 查询活动信息
     * @param id 编号
     * @param fields 列
     * @return 活动信息
     */
    public ActivityJiaFeiMao findActivityJiaFeiMaoById(Integer id, String...fields) {
        return findEntityById(ActivityJiaFeiMao.class, id, fields);
    }

    /**
     * 根据 手机号 查询活动信息
     * @param mobilePhone 手机号
     * @param fields 列
     * @return 活动信息
     */
    public ActivityJiaFeiMao findActivityJiaFeiMaoByMobilePhoneAndTreadStatus(String mobilePhone, Integer tradeStatus, String...fields) {
        Query query = new Query(Criteria.where("mobilePhone").is(mobilePhone).and("tradeStatus").is(tradeStatus));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, ActivityJiaFeiMao.class);
    }

    public void updateActivityJiaFeiMaoByTradeNoAndNonceStr(String tradeNo, String nonceStr, Update update) {
        Query query = new Query(Criteria.where("tradeNo").is(tradeNo).and("nonceStr").is(nonceStr));
        getRoutingMongoOps().updateFirst(query, update, ActivityJiaFeiMao.class);
    }

    public long findActivityJiaFeiMaoCountByTradeStatus(int tradeStatus) {
        return getRoutingMongoOps().count(Query.query(Criteria.where("tradeStatus").is(tradeStatus)), ActivityJiaFeiMao.class);
    }

    public long findActivityJiaFeiMaoCountByGroup(String group) {
        return getRoutingMongoOps().count(Query.query(Criteria.where("group").is(group)), ActivityJiaFeiMao.class);
    }
}
