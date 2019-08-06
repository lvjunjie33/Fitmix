package com.business.core.operations.payOrder;

import com.business.core.entity.payOrder.PayOrder;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by edward on 2016/5/13.
 */
@Repository
public class PayOrderCoreDao extends BaseMongoDaoSupport{

    /**
     * 通过订单编号查询订单信息
     * @param orderNos 订单编号列表
     * @param fields 查询的字段
     */
    public List<PayOrder> findPayOrderByOrderNos(List<String> orderNos, String...fields) {
        Query query = Query.query(Criteria.where("orderNo").in(orderNos));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, PayOrder.class);
    }
}
