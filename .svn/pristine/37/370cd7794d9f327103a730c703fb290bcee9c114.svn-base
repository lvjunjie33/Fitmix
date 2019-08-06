package com.business.work.keyword;

import com.business.core.entity.Page;
import com.business.core.entity.keyword.Keyword;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * Created by fenxio on 2016/5/17.
 */
@Repository
public class KeywordDao extends BaseMongoDaoSupport {

    /**
     * 关键字分页信息查询
     * @param page
     * @param fields
     */
    public void findKeywordPage(Page<Keyword> page, String ... fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();
        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }
        if(!StringUtils.isEmpty(filter.get("name"))){
            criteria.and("name").is(filter.get("name"));
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "type"));
        query.with(new Sort(Sort.Direction.ASC, "state"));
        query.with(new Sort(Sort.Direction.ASC, "sort"));

        findEntityPage(Keyword.class, page, query, fields);
    }

    /**
     * 插入关键字
     * @param keyword 关键字对象
     */
    public void insertKeyword(Keyword keyword) {
        insertToMongo(keyword);
    }

    /**
     * 根据编号 查询关键字
     * @param id  关键字编号
     * @param fields 列
     * @return
     */
    public Keyword findKeywordById(Integer id, String ... fields) {
        return findEntityById(Keyword.class, id, fields);
    }

    /**
     * 根据 编号 修改关键字信息
     * @param id 编号
     * @param update 更新
     */
    public void updateKeywordById(Long id, Update update) {
        updateEntityFieldsById(Keyword.class, id, update);
    }

    /**
     * 根据 编号 删除关键字
     * @param id 编号
     */
    public void removeKeywordById(Long id) {
        removeEntityById(Keyword.class, id);
    }
}
