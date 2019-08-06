package com.business.work.payOrder;

import com.business.core.entity.Page;
import com.business.core.entity.payOrder.PayOrder;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Created by fenxio on 2016/5/28.
 */
@Repository
public class PayOrderDao extends BaseMongoDaoSupport {

    /**
     * 获取订单分页信息
     * @param page 分页
     * @param fields 列
     */
    public void findPayOrderPage(Page<PayOrder> page, String ... fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (!StringUtils.isEmpty(filter.get("id"))) {
            criteria.and("id").is(filter.get("id"));
        }

        if (!StringUtils.isEmpty(filter.get("orderNo"))) {
            criteria.and("orderNo").is(filter.get("orderNo"));
        }

        if (!StringUtils.isEmpty(filter.get("platformNo"))) {
            criteria.and("platformNo").is(filter.get("platformNo"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(PayOrder.class, page, query, fields);
    }
}
