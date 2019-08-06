package com.business.core.operations.community;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.community.discuss.CategoryMsg;
import com.business.core.entity.community.discuss.Discuss;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2017/5/31.
 */
@Repository
public class CategoryCoreDao extends BaseMongoDaoSupport {

    /**
     * 保存话题
     *
     * @param theme 话题
     */
    public void saveTheme(Theme theme) {
        insertToMongo(theme);
    }

    /**
     * 保存回复
     *
     * @param discuss 回复、讨论
     */
    public void saveDiscuss(Discuss discuss) {
        insertToMongo(discuss);
    }

    /**
     * 查询话题
     *
     * @param themeId 话题编号
     * @param fields 查询的字段
     */
    public Theme findThemeById(Long themeId, String...fields) {
        Query query = Query.query(Criteria.where("id").is(themeId).and("isConfirmed").is(Constants.CHECK_STATUS_SUCCESS).and("isSensitive").is(Constants.ENABLED));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Theme.class);
    }

    /**
     * 修改话题信息
     *
     * @param id 编号
     * @param update 修改的内容
     */
    public void modifyTheme(Long id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, Theme.class);
    }

    /**
     * 保存 话题消息
     *
     * @param msg 消息
     */
    public void saveCategoryMsg(CategoryMsg msg) {
        insertToMongo(msg);
    }


    /**
     * 通过编号修改回复
     *
     * @param id 编号
     * @param update 修改的内容
     */
    public void modifyDiscussById(Long id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, Discuss.class);
    }

    /**
     * 通过编号查询回复
     *
     * @param id 编号
     * @param fields 查询的字段
     */
    public Discuss findDiscussById(Long id, String...fields) {
        Query query = Query.query(Criteria.where("id").is(id).and("isConfirmed").is(Constants.CHECK_STATUS_SUCCESS).and("isSensitive").is(Constants.ENABLED));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Discuss.class);
    }

    /**
     * 查询用户问题的回答数量
     *
     * @param parentThemeId 问题编号
     * @param uid 用户编号
     */
    public long findThemeByUidAndParentThemeId(Long parentThemeId, Integer uid) {
        return getRoutingMongoOps().count(Query.query(Criteria.where("parentThemeId").is(parentThemeId).and("uid").is(uid).and("themeType").is(Theme.THEME_TYPE_ANSWER)), Theme.class);
    }

    /**
     * 话题列表
     *
     * @param page 话题实体
     * @param fields 查询的字段
     */
    public void themeList(Page<Theme> page, String...fields) {
        LinkedHashMap<String, Object> filter = page.getFilter();

        Criteria criteria = new Criteria();
        if (filter.containsKey("uid")) {
            criteria.and("uid").is(filter.get("uid"));
        }
        if (filter.containsKey("parentThemeId")) {
            criteria.and("parentThemeId").is(filter.get("parentThemeId"));
        }
        if (filter.containsKey("themeType")) {
            criteria.and("themeType").is(filter.get("themeType"));
        }

        if (filter.containsKey("searchText")) {
            criteria.and("title").regex(filter.get("searchText").toString());
        }

        //默认查询正常的数据
        if (filter.containsKey("isConfirmed")) {
            criteria.and("isConfirmed").is(filter.get("isConfirmed")).and("isSensitive").is(filter.get("isSensitive"));
        }

        Query query = new Query(criteria);
        if (filter.containsKey("searchType")) {
            Integer searchType = (Integer) filter.get("searchType");
            if (Theme.SEARCH_TYPE_DISCUSS_NUM.equals(searchType)) {
                query.with(new Sort(Sort.Direction.DESC, "discussNum", "upNum", "addTime"));
            } else if (Theme.SEARCH_TYPE_UP_NUM.equals(searchType)) {
                query.with(new Sort(Sort.Direction.DESC, "upNum", "addTime"));
            } else if (Theme.SEARCH_TYPE_TAO_LUN_NUM.equals(searchType)) {
                query.with(new Sort(Sort.Direction.DESC, "taoLunNum", "upNum", "addTime"));
            } else {//Theme.SEARCH_TYPE_ADD_TIME.equals(searchType) 默认为该排序方式
                query.with(new Sort(Sort.Direction.DESC,"addTime", "upNum"));
            }
        } else {
            query.with(new Sort(Sort.Direction.DESC, "addTime"));
        }
        findEntityPage(Theme.class, page, query, fields);
    }

    /**
     * 查询问题的回答 上一个、下一个
     *
     * @param themeId 问题编号
     */
    public List<Theme> findPopularByThemeId(Long themeId, Integer sortType, String...fields) {
        Criteria criteria = Criteria.where("parentThemeId").is(themeId).and("themeType").is(Theme.THEME_TYPE_ANSWER)
                .and("isConfirmed").is(Constants.CHECK_STATUS_SUCCESS).and("isSensitive").is(Constants.ENABLED);
        Query query = new Query();
        if (Theme.SEARCH_TYPE_DISCUSS_NUM.equals(sortType)) {
            query.with(new Sort(Sort.Direction.DESC, "discussNum", "upNum", "addTime"));
        } else if (Theme.SEARCH_TYPE_UP_NUM.equals(sortType)) {
            query.with(new Sort(Sort.Direction.DESC, "upNum", "addTime"));
        } else if (Theme.SEARCH_TYPE_TAO_LUN_NUM.equals(sortType)) {
            query.with(new Sort(Sort.Direction.DESC, "taoLunNum", "upNum", "addTime"));
        } else {
            query.with(new Sort(Sort.Direction.DESC, "addTime", "upNum"));
        }

        query.addCriteria(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Theme.class);
    }

    /**
     * 查询自己的回答
     *
     * @param parentThemeId 问题编号
     * @param uid 用户编号
     * @param fields 查询的字段
     */
    public Theme findThemeByUidAndParentThemeId(Long parentThemeId, Integer uid, String...fields) {
        Query query = Query.query(Criteria.where("parentThemeId").is(parentThemeId).and("uid").is(uid)
                .and("isConfirmed").is(Constants.CHECK_STATUS_SUCCESS).and("isSensitive").is(Constants.ENABLED));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Theme.class);
    }

    /**
     * 回复列表
     *
     * @param page 回复实体
     * @param fields 查询的字段
     */
    public void discussList(Page<Discuss> page, String...fields) {
        Map<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();
        if (filter.containsKey("themeId")) {
            criteria.and("themeId").is(filter.get("themeId"));
        }
        if (filter.containsKey("uid")) {
            criteria.and("uid").is(filter.get("uid"));
        }

        //默认查询正常的数据
        if (filter.containsKey("isConfirmed")) {
            criteria.and("isConfirmed").is(filter.get("isConfirmed")).and("isSensitive").is(filter.get("isSensitive"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "addTime"));
        findEntityPage(Discuss.class, page, query, fields);
    }

}
