package com.business.bbs.user;

import com.business.bbs.base.support.AbstractDao;
import com.business.core.entity.user.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by fenxio on 2016/9/18.
 */
@Repository
public class UserDao extends AbstractDao<User> {

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
