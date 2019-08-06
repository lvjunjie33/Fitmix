package com.business.app.webUser;

import com.business.core.entity.user.User;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by weird on 2016/8/25.
 */
@Repository
public class WebUserDao extends BaseMongoDaoSupport {

    /**
     *
     * @param content
     * @return
     */
    public User findUserByUnionId(String content) {
        Query query = new Query(Criteria.where("unionid").is(content));
        return getRoutingMongoOps().findOne(query,User.class);
    }
}
