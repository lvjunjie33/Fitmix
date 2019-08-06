package com.business.work.dic;

import com.business.core.entity.Dictionary;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/4/29.
 */
@Repository
public class DictionaryDao extends BaseMongoDaoSupport {

    /**
     * 查询所有 dictionary 值
     * @return
     */
    public List<Dictionary> findAllDictionary () {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "_id"));
        return getRoutingMongoOps().find(query, Dictionary.class);
    }

    /**
     * 查询单个 dictionary
     * @param id 编号
     * @param fields 列
     * @return 字典信息 dictionary
     */
    public Dictionary findDictionaryById (Integer id, String...fields) {
        return findEntityById(Dictionary.class, id, fields);
    }

    /**
     * 添加 dictionary 信息
     * @param dictionary 字典信息
     */
    public void insertDictionary (Dictionary dictionary) {
        insertToMongo(dictionary);
    }

    /**
     * 更新 dictionary 信息
     * @param id 编号
     * @param update 更新信息
     */
    public void updateDictionaryById (Integer id, Update update) {
        updateEntityFieldsById(Dictionary.class, id, update);
    }

    /**
     * 删除 dictionary
     * @param id 编号
     */
    public void removeDictionaryById (Integer id) {
        removeEntityById(Dictionary.class, id);
    }

    /**
     * 根据类型获取字典值
     * @param type
     * @param fields
     * @return
     */
    public List<Dictionary> findDictionaryByType(Integer type, String ... fields) {
        Criteria criteria = Criteria.where("type").is(type);
        Query query = new Query(criteria);
        return getRoutingMongoOps().find(query,Dictionary.class);
    }
}
