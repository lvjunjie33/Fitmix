package com.business.app.surprise;

import com.business.core.entity.surprise.Surprise;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by fenxio on 2016/5/19.
 */
@Repository
public class SurpriseDao extends BaseMongoDaoSupport {

    /**
     * 查找单个彩蛋信息
     * @param query 查询信息
     * @param entityClass 实体类
     * @return
     */
    public Surprise findOne(Query query, Class<Surprise> entityClass) {
        return getRoutingMongoOps().findOne(query, entityClass);
    }
}
