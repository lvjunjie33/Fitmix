package com.business.core.operations.schedulerValue;

import com.business.core.entity.SchedulerValue;
import com.business.core.entity.wx.WXUser;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by sin on 2015/10/14.
 */
@Repository
public class SchedulerValueCoreDao extends BaseMongoDaoSupport {

    /**
     *
     * @param fields
     * @return
     */
    public SchedulerValue findSchedulerValue(String...fields) {
        Query query = new Query();
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, SchedulerValue.class);
    }

    public void schedulerValueUpsert(Update update) {
        Query query = new Query();
        getRoutingMongoOps().upsert(query, update, SchedulerValue.class);
    }
}
