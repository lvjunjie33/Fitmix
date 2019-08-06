package com.business.work.version;

import com.business.core.entity.Page;
import com.business.core.entity.version.Version;
import com.business.core.entity.video.Video;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Created by fenxio on 2016/5/25.
 */
@Repository
public class VersionDao extends BaseMongoDaoSupport {

    /**
     * 查找版本信息列表
     * @param page 分页信息
     * @param fields 列
     */
    public void findVersionPage(Page<Version> page, String ... fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (!StringUtils.isEmpty(filter.get("id"))) {
            criteria.and("id").is(filter.get("id"));
        }

        Query query = new Query(criteria);
        findEntityPage(Version.class, page, query, fields);
    }

    /**
     * 插入版本信息
     * @param version 版本信息对象
     */
    public void insertVersion(Version version) {
        insertToMongo(version);
    }

    /**
     * 根据编号查找版本信息
     * @param id 编号
     * @param fields 列
     * @return
     */
    public Version findVersionById(Long id, String ... fields) {
       return findEntityById(Version.class, id, fields);
    }

    /**
     * 根据编号修改版本基本信息
     * @param id 编号
     * @param update 跟新
     */
    public void updateVersionById(Long id, Update update) {
        updateEntityFieldsById(Version.class, id, update);
    }

    /**
     * 根据上架状获取版本信息
     * @param state 上架状态
     * @return
     */
    public Version findVersionByState(Integer state) {
        Query query = new Query(Criteria.where("state").is(state));
        return getRoutingMongoOps().findOne(query, Version.class);
    }

    /**
     * 更新版本信息
     * @param query 条件
     * @param update 更新
     * @param clazz
     */
    public void updateMulti(Query query, Update update, Class<?> clazz) {
        getRoutingMongoOps().updateMulti(query, update, clazz);
    }

}
