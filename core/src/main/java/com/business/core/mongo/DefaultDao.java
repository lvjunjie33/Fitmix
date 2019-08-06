package com.business.core.mongo;

import com.business.core.entity.IncIdEntity;
import com.business.core.entity.Page;
import com.business.core.entity.activity.ActivitySignUp;
import com.business.core.entity.user.User;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2016/6/28.
 *
 * 默认的数据库操作 不提供任何实际业务的操作方式
 */
@Repository
public class DefaultDao extends BaseMongoDaoSupport{

    public void save(Object obj) {
        insertToMongo(obj);
    }

    public <T> List<T> query(Query query, Class<T> clazz, String...fields) {
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.ASC, "id"));
        return getRoutingMongoOps().find(query, clazz);
    }

    public <T> T findById(Class<T> clazz, Object id, String...fields) {
        return findEntityById(clazz, id, fields);
    }

    public<T> long count(Query query, Class<T> clazz) {
        return getRoutingMongoOps().count(query, clazz);
    }

    public <T> T findOne(Query query, Class<T> clazz, String...fields) {
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, clazz);
    }

    public <T> List<T> findAll(Class<T> clazz) {
        return getRoutingMongoOps().findAll(clazz);
    }

    public<T> void modifyFirst(Query query, Update update, Class<T> clazz) {
        getRoutingMongoOps().updateFirst(query, update, clazz);
    }

    public<T extends IncIdEntity> void modifyBean(IncIdEntity incIdEntity) {
        updateEntityFieldsById(incIdEntity);
    }

    public<T extends IncIdEntity> void modifyMore(Query query, Update update, Class<T> clazz) {
        getRoutingMongoOps().updateMulti(query, update, clazz);
    }

    /**
     *
     * @param clazz
     * @param limit 条数
     * @param skip 从哪儿开始
     */
    public <T> List<T> page(Class<T> clazz, Integer limit, Integer skip, Query query, String sortName, String...fields) {
        query.limit(limit);
        query.skip(skip);
        query.with(new Sort(Sort.Direction.DESC, sortName));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, clazz);
    }

    public<T> void upsert(Query query, Update update, Class<T> clazz) {
        getRoutingMongoOps().upsert(query, update, clazz);
    }
}
