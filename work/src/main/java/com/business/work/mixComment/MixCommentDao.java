package com.business.work.mixComment;

import com.business.core.entity.Page;
import com.business.core.entity.mix.MixComment;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by sin on 2015/8/7 0007.
 */
@Repository
public class MixCommentDao extends BaseMongoDaoSupport {

    /**
     * mixComment 分页查询
     * @param page 分页
     * @param fields 列
     */
    public void findMixCommentPage(Page<MixComment> page, String...fields) {
        Criteria criteria = Criteria.where("state").is(MixComment.STATE_YES);
        Map<String, Object> filter =page.getFilter();
        if (filter.containsKey("mid")) {
            criteria.and("mid").is(filter.get("mid"));
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(MixComment.class, page, query, fields);
    }

    /**
     * 查询sort第一的评论
     * @param mid 歌曲编号
     * @param fields 列
     * @return
     */
    public MixComment findMixCommentWithSortFirst(Integer mid, String...fields) {
        Query query = new Query(Criteria.where("mid").is(mid).and("state").is(MixComment.STATE_YES));
        query.with(new Sort(Sort.Direction.DESC,"sort"));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, MixComment.class);
    }
}
