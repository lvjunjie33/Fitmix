package com.business.app.userRunRank;

import com.business.core.entity.user.UserRunRank;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by fenxio on 2016/9/18.
 */
@Repository
public class UserRunRankDao extends BaseMongoDaoSupport {

    /**
     * 获取单条榜单数据
     * @param query
     * @param clszz
     * @return
     */
    public UserRunRank findOne(Query query, Class<UserRunRank> clszz) {
        return getRoutingMongoOps().findOne(query, clszz);
    }
}
