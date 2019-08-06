package com.business.core.operations.mixComment;

import com.business.core.entity.mix.MixComment;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sin on 2015/8/5 0005.
 */
@Repository
public class MixCommentCoreDao extends BaseMongoDaoSupport {

    /**
     * 添加评论信息
     * @param mixComment
     */
    public void insertMixComment(MixComment mixComment) {
        insertToMongo(mixComment);
    }

    /**
     * 根据 歌曲编号 分页查询
     * @param mid
     * @param index
     * @param limit
     * @param fields
     * @return
     */
    public List<MixComment> findMixCommentByMidPage(Integer mid, Integer index, Integer limit, String...fields) {
        Query query = new Query(Criteria.where("mid").is(mid).and("state").is(MixComment.STATE_YES));
        includeFields(query, fields);
        index = index + 1 < 1 ? 0 : limit * index;
        query.skip(index).limit(limit);
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, MixComment.class);
    }

    /**
     * 根据编号 更新评论信息
     * @param id MixComment id
     * @param update 更新信息
     */
    public void updateMixCommentById(Long id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, MixComment.class);
    }
}
