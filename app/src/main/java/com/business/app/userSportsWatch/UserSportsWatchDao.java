package com.business.app.userSportsWatch;

import com.business.core.entity.user.watch.UserSportsWatch;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by edward on 2017/4/5.
 */
@Repository
public class UserSportsWatchDao extends BaseMongoDaoSupport {

    /**
     * 保存运动手表的运动记录
     *
     * @param userSportsWatch 运动记录
     */
    public void saveSportWatch(UserSportsWatch userSportsWatch) {
        insertToMongo(userSportsWatch);
    }

    /**
     * 查询某个用户某个时间点的运动记录
     * @param sportBTime 运动时间
     * @param uid 用户编号
     */
    public UserSportsWatch findBySportBTime(Long sportBTime, Integer uid, String...fields) {
        Query query = Query.query(Criteria.where("sportBTime").is(sportBTime).and("uid").is(uid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, UserSportsWatch.class);
    }

    /**
     * 查询运动手表的运动记录
     * @param sportBTime 开始运动时间
     * @param fields 查询的字段
     */
    public List<UserSportsWatch> findUserSportsWatch(Long sportBTime, Integer uid, String...fields) {
        Query query = Query.query(Criteria.where("sportBTime").gt(sportBTime).and("uid").is(uid));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserSportsWatch.class);
    }
}
