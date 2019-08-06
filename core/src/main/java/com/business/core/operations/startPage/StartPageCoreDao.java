package com.business.core.operations.startPage;

import com.business.core.entity.startPage.StartPage;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by edward on 2016/5/16.
 */
@Repository
public class StartPageCoreDao extends BaseMongoDaoSupport{
    /**
     * 查询最后一次发布的有效的启动闪图
     *
     * @param releaseStatus 发布状态
     * @param status 数据状态
     * @param currentTime 当前时间
     */
    public StartPage findStartPage(Integer releaseStatus, Integer status, Long currentTime, String...fields) {
        Query query = Query.query(Criteria.where("releaseStatus").is(releaseStatus).and("status").is(status).and("startTime").lte(currentTime).and("deadline").gte(currentTime));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().findOne(query, StartPage.class);
    }
}
