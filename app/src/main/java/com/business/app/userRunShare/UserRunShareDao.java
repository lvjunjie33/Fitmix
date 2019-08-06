package com.business.app.userRunShare;

import com.business.core.entity.user.UserRunShare;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015-05-22.
 */
@Repository
public class UserRunShareDao extends BaseMongoDaoSupport{

    /**
     * 查询用户分享信息
     * @param id 分享编号
     * @return 用户运动分享数据
     */
    public UserRunShare findUserRunShareByIdAndUid(String id, Integer uid, String...fields) {
        Query query = new Query(Criteria.where("id").is(id).and("uid").is(uid));
        return getRoutingMongoOps().findOne(query, UserRunShare.class);
    }

    /**
     * 用户
     * @param rid
     * @param fields
     * @return
     */
    public UserRunShare findUserRunShareByRid (Long rid, String...fields) {
        Query query = new Query(Criteria.where("rid").is(rid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, UserRunShare.class);
    }

    /**
     * 用户运动分享添加
     * @param userRunShear 用户分享
     */
    public void insertUserRunShear (UserRunShare userRunShear) {
        insertToMongo(userRunShear);
    }

}
