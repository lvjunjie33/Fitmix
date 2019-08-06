package com.business.bbs.base.support;

import com.business.core.entity.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by fenxio on 2016/8/23.
 */
public abstract class AbstractService<T> {

    protected abstract AbstractDao<T> getAbstractDao();

    private Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    /**
     * 保存 实体 信息
     *
     * @param record 实体
     */
    public void insert(T record) {
        getAbstractDao().insert(record);
    }

    /**
     * 更新实体信息 全部更新
     *
     * @param id     编号
     * @param update 更新信息
     */
    public void updateById(Object id, Update update) {
        getAbstractDao().updateById(this.clazz, id, update);
    }

    /**
     * 更新实体信息 部分更新
     *
     * @param record
     */
    public void updateByIdSelective(T record) {
        getAbstractDao().updateByIdSelective(this.clazz, record);
    }

    /**
     * 根据ID 获取实体信息
     *
     * @param id     编号
     * @param fields 列
     * @return
     */
    public T findById(Object id, String... fields) {
        return getAbstractDao().findById(this.clazz, id, fields);
    }

    /**
     * 根据 ID 删除实体信息
     *
     * @param id
     */
    public void deleteById(Object id) {
        getAbstractDao().deleteById(this.clazz, id);
    }

    /**
     * 根据条件删除
     * @param map
     */
    public void deleteByMap(Map<String, Object> map) {
        getAbstractDao().deleteByMap(this.clazz, map);
    }


    /**
     * 获取所有列表信息
     *
     * @return
     */
    public List<T> findAll() {
        return this.getAbstractDao().findAll(this.clazz);
    }

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    public List<T> find(Query query) {
       return getAbstractDao().find(query);
    }

    /**
     * 分页获取信息
     * @param page
     */
    public void findByPage(Page<T> page, Map<String, Sort.Direction> orderBy) {
        this.getAbstractDao().findByPage(this.clazz, page, orderBy);
    }

    /**
     * 获取单个实体信息
     * @param map
     * @return
     */
    public T findOneByMap(Map<String, Object> map, Map<String, Sort.Direction> orderBy) {
        return this.getAbstractDao().findOneByMap(map, orderBy);
    }

    /**
     * 根据条件查找实体类
     * @param map 条件
     * @param orderBy 排序
     * @return
     */
    public List<T> findByMap(Map<String, Object> map, Map<String, Sort.Direction> orderBy) {
        return this.getAbstractDao().findByMap(this.clazz, map, orderBy);
    }
}
