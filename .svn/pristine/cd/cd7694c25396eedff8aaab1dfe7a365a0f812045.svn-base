package com.business.work.apiDetails;

import com.business.core.entity.api.ApiDetails;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangtao on 2016/4/26.
 */
@Repository
public class ApiDetailsDao extends BaseMongoDaoSupport {

    /**
     * 添加ApiDetails
     * @param apiDetails 对象
     */
    public void apiDetailsAdd(ApiDetails apiDetails) {
        insertToMongo(apiDetails);
    }

    /**
     * 根据Api 编号获取Api详情
     * @param apiId Api编号
     * @return
     */
    public ApiDetails findApiDetailsByApiId(Integer apiId) {
        Query query = new Query(Criteria.where("apiId").is(apiId));
        return getRoutingMongoOps().findOne(query, ApiDetails.class);
    }

    /**
     * 删除所有API 详情
     */
    public void removeAll() {
        Query qurey = new Query();
        getRoutingMongoOps().remove(qurey, ApiDetails.class);
    }
}
