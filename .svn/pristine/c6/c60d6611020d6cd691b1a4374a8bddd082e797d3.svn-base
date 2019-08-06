package com.business.work.api;

import com.business.core.entity.api.Api;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangtao on 2016/4/26.
 */
@Repository
public class ApiDao extends BaseMongoDaoSupport {

    /**
     * 添加API
     * @param api
     */
    public void insertApi(Api api) {
        insertToMongo(api);
    }

    /**
     * 根据URL 查找API
     * @param url
     * @return
     */
    public Api findApiByUrl(String url) {
        Query query = new Query(Criteria.where("url").is(url));
        return getRoutingMongoOps().findOne(query,Api.class);
    }

    /**
     * 获取所有API
     * @return
     */
    public List<Api> findAllApi() {
        return getRoutingMongoOps().findAll(Api.class);
    }

    /**
     * 查找API列表
     * @param modules 模块
     * @return
     */
    public List<Api> findAllApiByModules(String modules) {
        Query query = new Query(Criteria.where("modules").is(modules));
        return getRoutingMongoOps().find(query,Api.class);
    }

    /**
     * 删除所有API
     */
    public void removeAll() {
        Query qurey = new Query();
        getRoutingMongoOps().remove(qurey, Api.class);
    }
}
