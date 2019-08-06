package com.business.app.keyword;

import com.business.core.entity.keyword.Keyword;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fenxio on 2016/5/17.
 */
@Repository
public class KeywordDao extends BaseMongoDaoSupport {

    /**
     * 获取所有上架的 热词关键字
     */
    public List<Keyword> findAllkeywordList() {
        Criteria criteria = new Criteria();
        criteria.and("state").is(Keyword.STATE_2);
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "type"));
        query.with(new Sort(Sort.Direction.ASC, "state"));
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        return getRoutingMongoOps().find(query, Keyword.class);
    }
}
