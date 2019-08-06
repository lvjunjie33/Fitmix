package com.business.work.mixAuthor;

import com.business.core.entity.Page;
import com.business.core.entity.mix.MixAuthor;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by sin on 2015/8/4 0004.
 */
@Repository
public class MixAuthorDao extends BaseMongoDaoSupport {


    /**
     * MixAuthor 分页
     * @param page 分页工具
     * @param fields 列
     */
    public void findMixAuthorPage(Page<MixAuthor> page, String...fields) {
        Map<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();
        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }
        if (!StringUtils.isEmpty(filter.get("name"))) {
            criteria.and("name").regex(String.format("%s.*", filter.get("name")));
        }
        Query query = new Query(criteria);
        findEntityPage(MixAuthor.class, page, query, fields);
    }

    /**
     * 删除本次评论
     * @param id 用户编号
     */
    public void removeMixAuthor(Integer id) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("id").is(id)), MixAuthor.class);
    }
}
