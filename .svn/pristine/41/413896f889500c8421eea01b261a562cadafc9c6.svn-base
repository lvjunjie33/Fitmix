package com.business.core.operations.logs;

import com.business.core.entity.logs.UserLoginLog;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/6/8 0008.
 */
@Repository
public class UserLoginLogCoreDao extends BaseMongoDaoSupport {

    /**
     * 添加 用户login log
     * @param userLoginLog 日志信息
     */
    public void insertUserLoginLog (UserLoginLog userLoginLog) {
        getRoutingMongoOps().insert(userLoginLog);
    }

    /**
     * 用户登录日志 根据addTime
     * @param gteAddTime 添加时间
     * @param lteAddTime 添加时间
     * @param fields 列
     * @return 日志系你想
     */
    public List<UserLoginLog> findUserLoginLogByGteAddTimeAndLte (Long gteAddTime, Long lteAddTime, String...fields) {
        Query query = new Query(Criteria.where("addTime").gte(gteAddTime).lte(lteAddTime));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserLoginLog.class);
    }
}
