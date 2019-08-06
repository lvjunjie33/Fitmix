package com.business.work.community;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.community.discuss.CategoryMsg;
import com.business.core.entity.community.discuss.Categorys;
import com.business.core.entity.community.discuss.Discuss;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by edward on 2017/6/1.
 */
@Repository
public class CategorysDao extends BaseMongoDaoSupport{

    /**
     * 板块分页
     *
     * @param page 板块实体
     * @param fields 查询的字段
     */
    public void categoryPage(Page<Categorys> page, String...fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (filter.containsKey("title")) {
            criteria.and("title").is(filter.get("title"));
        }
        if (filter.containsKey("status")) {
            criteria.and("status").is(filter.get("status"));
        }

        if (filter.containsKey("isConfirmed")) {
            criteria.and("isConfirmed").is(filter.get("isConfirmed")).and("isSensitive").is(filter.get("isSensitive"));
        } else {
            criteria.and("isConfirmed").is(Constants.CHECK_STATUS_SUCCESS).and("isSensitive").is(Constants.ENABLED);
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(Categorys.class, page, query, fields);
    }

    /**
     * 保存板块
     *
     * @param category 板块信息
     */
    public void saveCategory(Categorys category) {
        insertToMongo(category);
    }

    /**
     * 话题分页
     *
     * @param page 话题实体
     */
    public void themeList(Page<Theme> page, String...fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (filter.containsKey("title")) {
            criteria.and("title").is(filter.get("title"));
        }
        if (filter.containsKey("categoryId")) {
            criteria.and("categoryId").is(filter.get("categoryId"));
        }
        if (filter.containsKey("themeType")) {
            criteria.and("themeType").is(filter.get("themeType"));
        }
        if (filter.containsKey("searchText")) {
            criteria.and("title").regex(filter.get("searchText").toString());
        }

        if (filter.containsKey("isConfirmed")) {
            criteria.and("isConfirmed").is(filter.get("isConfirmed"));
        }

        if(filter.containsKey("isSensitive")) {
            criteria.and("isSensitive").is(filter.get("isSensitive"));
        }

        Query query = new Query(criteria);
        if (filter.containsKey("searchType")) {
            Integer searchType = (Integer) filter.get("searchType");
            if (Theme.SEARCH_TYPE_DISCUSS_NUM.equals(searchType)) {
                query.with(new Sort(Sort.Direction.DESC, "discussNum"));
            } else if (Theme.SEARCH_TYPE_UP_NUM.equals(searchType)) {
                query.with(new Sort(Sort.Direction.DESC, "upNum"));
            } else {
                query.with(new Sort(Sort.Direction.DESC, "addTime"));
            }
        }
        findEntityPage(Theme.class, page, query, fields);
    }

    /**
     * 查询话题
     *
     * @param themeId 话题编号
     * @param fields 查询的字段
     */
    public Theme findThemeById(Long themeId, String...fields) {
        Query query = Query.query(Criteria.where("id").is(themeId));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Theme.class);
    }

    /**
     * 通过编号查询回复
     *
     * @param id 编号
     * @param fields 查询的字段
     */
    public Discuss findDiscussById(Long id, String...fields) {
        Query query = Query.query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Discuss.class);
    }

    public void clear() {
        getRoutingMongoOps().remove(new Query(), Theme.class);
        getRoutingMongoOps().remove(new Query(), Discuss.class);
        getRoutingMongoOps().remove(new Query(), CategoryMsg.class);
    }
}
