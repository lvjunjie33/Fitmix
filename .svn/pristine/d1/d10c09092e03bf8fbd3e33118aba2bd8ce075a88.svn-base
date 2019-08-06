package com.business.app.userEveryDayRun;

import com.business.core.entity.user.UserEveryDayRun;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by sin on 2015/12/25.
 */
@Repository
public class UserEveryDayRunDao extends BaseMongoDaoSupport {

    /**
     * 添加 用户运动（一天）
     * @param userEveryDayRun
     */
    public void insert(UserEveryDayRun userEveryDayRun) {
        insertToMongo(userEveryDayRun);
    }

    /**
     * 查询 用户每天运动信息
     * @param uid 用户编号
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param fields 列
     * @return 用户每天运动信息
     */
    public UserEveryDayRun findByUid(int uid, long beginTime, long endTime, String...fields) {
        Query query = new Query(Criteria.where("uid").is(uid).and("addTime").gte(beginTime).lte(endTime));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, UserEveryDayRun.class);
    }

    /**
     * 更新 用户每天运动
     * @param uid 用户编号
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param update 更新信息
     */
    public void updateByUidAndAddTime(int uid, long beginTime, long endTime, Update update){
        Query query = new Query(Criteria.where("uid").is(uid).and("addTime").gte(endTime).lte(endTime));
        getRoutingMongoOps().updateFirst(query, update, UserEveryDayRun.class);
    }

    /**
     * 更新 用户每天运动
     * @param id 编号
     * @param update 更新信息
     */
    public void updateById(long id, Update update){
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, UserEveryDayRun.class);
    }
}
