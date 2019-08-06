package com.business.core.operations.mixAuthor;

import com.business.core.entity.mix.MixAuthor;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by sin on 2015/8/4 0004.
 */
@Repository
public class MixAuthorCoreDao extends BaseMongoDaoSupport {

    /**
     * 添加 mix author
     * @param mixAuthor 作者信息
     */
    public void insertMixAuthor(MixAuthor mixAuthor) {
        insertToMongo(mixAuthor);
    }

    /**
     * 查询 author ：根据 uid and mid
     * @param id mix author 编号
     * @param fields 列
     * @return 作者
     */
    public MixAuthor findMixAuthor(Integer id, String...fields) {
        Query query = new Query(Criteria.where("id").is(id));
        query.with(new Sort(Sort.Direction.ASC, "name"));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, MixAuthor.class);
    }

    /**
     * 查询多个 author 信息
     * @param maids
     * @param fields
     * @return
     */
    public List<MixAuthor> findMixAuthor(Collection<Integer> maids, String...fields) {
        Query query = new Query(Criteria.where("id").in(maids));
        query.with(new Sort(Sort.Direction.ASC, "name"));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, MixAuthor.class);
    }

    /**
     * 查询评论信息：根据 name
     * @param name 作者名称
     * @param fields 列
     * @return 作者
     */
    public MixAuthor findMixAuthor(String name, String...fields) {
        Query query = new Query(Criteria.where("name").is(name));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, MixAuthor.class);
    }

    /**
     * 更新 mixAuthor
     * @param id 编号
     * @param update 更新对象
     */
    public void updateMixAuthor(Integer id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, MixAuthor.class);
    }

    /**
     * 查询所有 MixAuthor
     * @return MixAuthor集合
     */
    public List<MixAuthor> findAllMixAuthor(String...fields) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "name"));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, MixAuthor.class);
    }
}
