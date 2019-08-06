package com.business.app.community;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.activity.Activity;
import com.business.core.entity.community.discuss.CategoryMsg;
import com.business.core.entity.community.discuss.Discuss;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2017/5/31.
 */
@Repository
public class CategoryDao extends BaseMongoDaoSupport{

    /**
     * 查询新的消息
     *
     * @param targetUId 目标用户编号
     * @param type 消息类型
     */
    public List<CategoryMsg> findCategoryMsgByUid(Integer targetUId, Integer type, Integer status, String...fields) {
        Criteria criteria = Criteria.where("targetUId").is(targetUId).and("type").is(type).and("status").is(status);
        Query query = Query.query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, CategoryMsg.class);
    }


    /**
     * 查询个人的问题列表
     *
     * @param themeIds 问题编号
     * @param uid 用户编号
     */
    public List<Theme> findThemes(List<Long> themeIds, Integer uid, String...fields) {
        Criteria criteria = Criteria.where("id").in(themeIds).and("uid").is(uid).and("isConfirmed").is(Constants.CHECK_STATUS_SUCCESS).and("isSensitive").is(Constants.ENABLED);
        Query query = Query.query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Theme.class);
    }

    /**
     * 查询个人的回答列表
     *
     * @param discussIds 回答列表
     * @param fields 查询的字段
     */
    public List<Discuss> findDiscuss(List<Long> discussIds, String...fields) {
        Criteria criteria = Criteria.where("id").in(discussIds).and("isConfirmed").is(Constants.CHECK_STATUS_SUCCESS).and("isSensitive").is(Constants.ENABLED);
        Query query = Query.query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Discuss.class);
    }

    /**
     * 查询 banner 列表
     * @param fields 查询的字段
     */
    public List<Theme> findBannerTheme(String...fields) {
        Criteria criteria = Criteria.where("themeType").is(Theme.THEME_TYPE_PROBLEM).and("bannerSort").ne(null).and("backImage").ne(null)
                .and("isConfirmed").is(Constants.CHECK_STATUS_SUCCESS).and("isSensitive").is(Constants.ENABLED);
        Query query = Query.query(criteria);
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "bannerSort"));

        return getRoutingMongoOps().find(query, Theme.class);
    }

    /**
     * 通过编号查询问题
     *
     * @param id 编号
     */
    public Theme findThemeById(Long id, String...fields) {
        return findEntityById(Theme.class, id, fields);
    }
}
