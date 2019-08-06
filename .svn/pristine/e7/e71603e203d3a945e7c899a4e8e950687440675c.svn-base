package com.business.app.restHeartRate;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.user.info.RestHeartRate;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by edward on 2016/9/5.
 */
@Repository
public class RestHeartRateDao extends BaseMongoDaoSupport {

    /**
     * 保存用户静息心率
     *
     * @param restHeartRate 静息心率数据
     */
    public void save(RestHeartRate restHeartRate) {
        insertToMongo(restHeartRate);
    }

    /**
     * 修改用户的心率信息
     *
     * @param id 静息心率编号
     * @param uid 用户编号
     * @param update 修改的内容
     */
    public void modify(Long id, Integer uid, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id).and("uid").is(uid)), update, RestHeartRate.class);
    }

    /**
     * 查询静息心率的全列表
     *
     * @param uid 用户编号
     */
    public List<RestHeartRate> list(Integer uid, String...fields) {
        Query query = Query.query(Criteria.where("uid").is(uid).and("isRemove").is(Constants.STATE_EFFECTIVE));
        query.with(new Sort(Sort.Direction.DESC, "detectTime"));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, RestHeartRate.class);
    }

    /**
     * 静息心率分页
     *
     * @param page 分页对象
     */
    public void page(Page<RestHeartRate> page, String...fields) {

        LinkedHashMap filter = page.getFilter();
        Criteria criteria = Criteria.where("isRemove").is(Constants.STATE_EFFECTIVE);

        if (filter.containsKey("uid")) {
            criteria.and("uid").is(filter.get("uid"));
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "detectTime"));
        findEntityPage(RestHeartRate.class, page, query, fields);
    }
}
