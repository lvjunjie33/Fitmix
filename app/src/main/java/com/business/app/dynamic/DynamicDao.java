package com.business.app.dynamic;

import com.business.core.entity.dynamic.Comment;
import com.business.core.entity.dynamic.Dynamic;
import com.business.core.entity.dynamic.Flower;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by fenxio on 2016/10/8.
 */
@Repository
public class DynamicDao extends BaseMongoDaoSupport {

    /**
     * 保存动态信息
     * @param dynamic 动态信息
     */
    public void insert(Dynamic dynamic) {
        insertToMongo(dynamic);
    }

    /**
     * 根据动态编号和用户编号删除动态信息
     * @param id 动态编号
     * @param uid 用户编号
     */
    public Dynamic removeDynamicByIdAndUid(Long id, Integer uid) {
        Query query = new Query(Criteria.where("id").is(id).and("uid").is(uid));
        return getRoutingMongoOps().findAndRemove(query, Dynamic.class);
    }

    /**
     * 根据动态编号删除点赞列表
     * @param did 动态编号
     */
    public void removeFlowerByDid(Long did) {
        Query query = new Query(Criteria.where("did").is(did));
        getRoutingMongoOps().remove(query, Flower.class);
    }

    /**
     * 根据动态编号删除评论列表
     * @param did 动态编号
     */
    public void removeCommentByDid(Long did) {
        Query query = new Query(Criteria.where("did").is(did));
        getRoutingMongoOps().remove(query, Comment.class);
    }

    /**
     * 根据点赞编号和用户编号删除点赞信息
     * @param id 点赞编号
     * @param uid 用户编号
     */
    public void removeFlowerByIdAndUid(Long id, Integer uid) {
        Query query = new Query(Criteria.where("id").is(id).and("uid").is(uid));
        getRoutingMongoOps().remove(query, Flower.class);
    }

    /**
     * 根据点赞编号和用户编号删除评论信息
     * @param id 点赞编号
     * @param uid 用户编号
     */
    public void removeCommentByIdAndUid(Long id, Integer uid) {
        Query query = new Query(Criteria.where("id").is(id).and("uid").is(uid));
        getRoutingMongoOps().remove(query, Comment.class);
    }

    /**
     * 插入点赞信息
     * @param flower 点赞信息
     */
    public void insertFlower(Flower flower) {
        insertToMongo(flower);
    }

    /**
     * 插入评论信息
     * @param comment 评论信息
     */
    public void insertComment(Comment comment) {
        insertToMongo(comment);
    }
}
