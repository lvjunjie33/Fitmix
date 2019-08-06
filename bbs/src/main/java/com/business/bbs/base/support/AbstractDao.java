package com.business.bbs.base.support;

import com.business.bbs.base.util.ReflectionUtil;
import com.business.core.entity.Page;
import com.business.core.mongo.BaseMongoDaoSupport;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fenxio on 2016/8/23.
 */
public class AbstractDao<T> extends BaseMongoDaoSupport {

    private Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    /**
     * 保存 实体 信息
     * @param record 实体
     */
    public void insert(T record) {
        insertToMongo(record);
    }

    /**
     * 更新实体信息
     * @param clazz 类
     * @param id 编号
     * @param update 更新信息
     */
    public void updateById(Class<T> clazz, Object id, Update update) {
        updateEntityFieldsById(clazz, id, update);
    }

    /**
     * 更新实体信息 部分更新
     *
     * @param record 更新实体
     */
    public void updateByIdSelective(Class<T> clazz, T record) {
        Object id = ReflectionUtil.getValueByFieldName(record, "id");
        Update update = ReflectionUtil.getUpdateInfo(record);
        updateEntityFieldsById(clazz, id, update);
    }

    /**
     * 根据ID 获取实体信息
     * @param clazz 实体类
     * @param id 编号
     * @param fields 列
     * @return
     */
    public T findById(Class<T> clazz, Object id, String... fields) {
        return findEntityById(clazz, id, fields);
    }

    /**
     * 根据ID 删除实体信息
     * @param clazz 实体类
     * @param id 编号
     */
    public void deleteById(Class<T> clazz, Object id) {
        removeEntityById(clazz, id);
    }

    /**
     * 获取所有列表信息
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> findAll(Class<T> clazz) {
        return super.getRoutingMongoOps().findAll(clazz);
    }

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    public List<T> find(Query query) {
        return super.getRoutingMongoOps().find(query, this.clazz);
    }

    /**
     * 分页
     * @param clazz
     * @param page
     * @param orderBy
     * @param fields
     */
    public void findByPage(Class<T> clazz , Page<T> page, Map<String, Sort.Direction> orderBy, String ... fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();
        for(String key : filter.keySet()) {
            criteria.and(key).is(filter.get(key));
        }
        Query query = new Query(criteria);
        for(String key : orderBy.keySet()) {
            query.with(new Sort(orderBy.get(key), key));
        }
        super.findEntityPage(clazz, page, query, fields);
    }

    /**
     * 查找单个实体
     * @param map
     * @return
     */
    public T findOneByMap(Map<String, Object> map, Map<String, Sort.Direction> orderBy) {
        Criteria criteria = new Criteria();
        for(String key : map.keySet()) {
            criteria.and(key).is(map.get(key));
        }
        Query query = new Query(criteria);
        for(String key : orderBy.keySet()) {
            query.with(new Sort(orderBy.get(key), key));
        }
        return super.getRoutingMongoOps().findOne(query, this.clazz);
    }

    /**
     * 查找多个实体类
     * @param clazz
     * @param map
     * @param orderBy
     * @return
     */
    public List<T> findByMap(Class<T> clazz, Map<String, Object> map, Map<String, Sort.Direction> orderBy) {
        Criteria criteria = new Criteria();
        for(String key : map.keySet()) {
            criteria.and(key).is(map.get(key));
        }
        Query query = new Query(criteria);
        for(String key : orderBy.keySet()) {
            query.with(new Sort(orderBy.get(key), key));
        }
        return this.getRoutingMongoOps().find(query, clazz);
    }

    /**
     * 根据条件删除
     * @param clazz
     * @param map
     */
    public void deleteByMap(Class<T> clazz, Map<String, Object> map) {
        Criteria criteria = new Criteria();
        for(String key : map.keySet()) {
            criteria.and(key).is(map.get(key));
        }
        Query query = new Query(criteria);
        super.getRoutingMongoOps().remove(query, clazz);
    }

    /**
     * 更新
     * @param query
     * @param update
     */
    public void updateFirst(Query query, Update update) {
        getRoutingMongoOps().updateFirst(query, update, this.clazz);
    }
}
