package com.business.app.userCollection;

import com.business.core.entity.user.UserCollect;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/4/20.
 */
@Repository
public class UserCollectDao extends BaseMongoDaoSupport {

    /**
     * 用户收藏添加
     * @param userCollect 用户收藏
     */
    public void insertUserCollect (UserCollect userCollect) {
        insertToMongo(userCollect);
    }

    /**
     * 查询用户收藏
     * @param uid 用户编号
     * @param mid mix编号
     * @param fields 列
     * @return 用户收藏
     */
    public UserCollect findUserCollectByUidAndMid (Integer uid, Integer mid, String...fields) {
        Query query = new Query(Criteria.where("uid").is(uid).and("mid").is(mid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, UserCollect.class);
    }

    /**
     * 用户收藏分页
     * @param uid 用户编号
     * @param limit 分页size
     * @param index 页码
     * @param fields 列
     * @return 用户收藏集合
     */
    public List<UserCollect> findUserCollectPage(Integer uid, Integer limit, Integer index, String... fields) {
        Query query = new Query(Criteria.where("uid").is(uid));
        includeFields(query, fields);
        query.limit(limit);
        query.skip(index);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, UserCollect.class);
    }

    /**
     * 用户收藏信息
     * @param uid 用户编号
     * @param fields 列
     * @return 用户收藏集合
     */
    public List<UserCollect> findUserCollectByUid (Integer uid, String...fields) {
        Query query = new Query(Criteria.where("uid").is(uid));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserCollect.class);
    }

    /**
     * 更新用户收藏
     * @param id 编号
     * @param update 更新信息
     */
    public void updateUserCollect (Integer id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, UserCollect.class);
    }

    /**
     * 删除收藏
     * @param id 收藏编号
     */
    public void removeUserCollect (Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().remove(query, UserCollect.class);
    }

}
