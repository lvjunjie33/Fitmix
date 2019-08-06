package com.business.core.operations.advert;

import com.business.core.entity.advert.Advert;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by edward on 2016/5/16.
 */
@Repository
public class AdvertCoreDao extends BaseMongoDaoSupport{

    /**
     * 获取发布了的有效的 广告
     * @param releaseStatus 发布状态
     * @param status 数据状态
     */
    public List<Advert> findAdverts(Integer releaseStatus, Integer status, Long currentTime, String...fields) {
        Query query = Query.query(Criteria.where("releaseStatus").is(releaseStatus).and("status").is(status).and("startTime").lte(currentTime).and("deadline").gte(currentTime));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, Advert.class);
    }
}
