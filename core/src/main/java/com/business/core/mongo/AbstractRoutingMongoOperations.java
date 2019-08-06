package com.business.core.mongo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;

/**
 * Mongo数据库路由基类
 */
public abstract class AbstractRoutingMongoOperations implements InitializingBean {

    /**
     * 数据库操作集合
     */
    private Map<String, MongoOperations> targetMongoOperations;
    /**
     * 默认数据库操作
     */
    private MongoOperations defaultTargetMongoOperations;

    public void setTargetMongoOperations(Map<String, MongoOperations> targetMongoOperations) {
        this.targetMongoOperations = targetMongoOperations;
    }

    public void setDefaultTargetMongoOperations(MongoTemplate defaultTargetMongoOperations) {
        this.defaultTargetMongoOperations = defaultTargetMongoOperations;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.defaultTargetMongoOperations, "Property 'defaultTargetMongoOperations' is required");
        if (targetMongoOperations == null) {
            targetMongoOperations = Collections.emptyMap();
        }
    }

    /**
     * @param lookupKey 数据库枚举
     * @return 数据库操作
     */
    public MongoOperations getMongoOps(String lookupKey) {
        MongoOperations operations = targetMongoOperations.get(lookupKey);
        return operations != null ? operations : defaultTargetMongoOperations;
    }

}