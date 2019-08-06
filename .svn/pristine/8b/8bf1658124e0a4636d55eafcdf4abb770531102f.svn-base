package com.business.app.payOrder;

import com.business.core.entity.payOrder.PayOrder;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Sin on 2016/4/13.
 */
@Repository
public class PayOrderDao extends BaseMongoDaoSupport {

    /**
     * 添加 支付订单
     *
     * @param payOrder 支付订单
     */
    public void insertPayOrder(PayOrder payOrder) {
        insertToMongo(payOrder);
    }

    /**
     * 根据 orderNo 更新支付订单
     *
     * @param orderNo 订单编号
     * @param update 更新信息
     */
    public void updatePayOrderByOrderNo(String orderNo, Update update) {
        Query query = new Query(Criteria.where("orderNo").is(orderNo));
        getRoutingMongoOps().updateFirst(query, update, PayOrder.class);
    }

    /**
     * 更具 订单编号 ：查询 支付订单
     *
     * @param orderNo 订单编号
     * @param fields 列
     * @return 订单信息
     */
    public PayOrder findPayOrderByOrderNo(String orderNo, String...fields) {
        Query query = new Query(Criteria.where("orderNo").is(orderNo));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, PayOrder.class);
    }
}
