package com.business.ugc.user;

import com.business.core.entity.user.User;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.ugc.base.support.AbstractDao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by fenxio on 2016/8/23.
 */
@Repository
public class UserDao extends AbstractDao<User> {

    /**
     * 根据 邮箱查找用户
     * @param email 邮箱
     * @return
     */
    public User selectByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        return super.getRoutingMongoOps().findOne(query, User.class);
    }

    /**
     * 根据手机号 查找用户
     * @param mobile 手机号
     * @return
     */
    public User selectByMobile(String mobile) {
        Query query = Query.query(Criteria.where("mobile").is(mobile));
        return super.getRoutingMongoOps().findOne(query, User.class);
    }
}
