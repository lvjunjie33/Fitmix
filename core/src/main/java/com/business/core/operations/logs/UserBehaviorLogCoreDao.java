package com.business.core.operations.logs;

import com.business.core.entity.logs.UserBehaviorLog;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/7/30 0030.
 */
@Repository
public class UserBehaviorLogCoreDao extends BaseMongoDaoSupport {

    /**
     * 添加 UserBehaviorLog
     * @param userBehaviorLog 用户行为信息
     */
    public void insertUserBehaviorLog(UserBehaviorLog userBehaviorLog) {
        getRoutingMongoOps().insert(userBehaviorLog);
    }

    /**
     * 根据添加时间查询用户行为日志
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param fields 列
     * @return 用户行为集合
     */
    public List<UserBehaviorLog> findWithAddTime(Long beginTime, Long endTime, String...fields) {
        Query query = Query.query(Criteria.where("addTime").gte(beginTime).lte(endTime));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, UserBehaviorLog.class);
    }
}
