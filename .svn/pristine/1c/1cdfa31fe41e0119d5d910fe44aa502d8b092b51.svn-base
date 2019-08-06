package com.business.work.userRunShareResource;

import com.business.core.entity.Page;
import com.business.core.entity.user.UserRunShareResource;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sin on 2016/1/14.
 */
@Repository
public class UserRunShareResourceDao extends BaseMongoDaoSupport {


    public void page(Page<UserRunShareResource> page, String...fields) {
        LinkedHashMap<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();

        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }

        if (filter.containsKey("type")) {
            criteria.and("type").is(filter.get("type"));
        }

        if (!StringUtils.isEmpty(filter.get("content"))) {
            criteria.and("content").regex(filter.get("content") + ".*");
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        query.with(new Sort(Sort.Direction.ASC, "status"));
        findEntityPage(UserRunShareResource.class, page, query, fields);
    }

    /**
     * 根据 内容 查询资源信息
     *
     * @param content 内容
     * @param fields 列
     * @return 资源信息
     */
    public UserRunShareResource findUserRunShareResourceByContent(String content, String...fields) {
        Query query = new Query(Criteria.where("content").is(content));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, UserRunShareResource.class);
    }
}