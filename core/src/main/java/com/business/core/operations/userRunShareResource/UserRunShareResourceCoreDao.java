package com.business.core.operations.userRunShareResource;

import com.business.core.entity.user.UserRunShareResource;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sin on 2016/1/14.
 */
@Repository
public class UserRunShareResourceCoreDao extends BaseMongoDaoSupport {

    /***
     * 插入数据
     *
     * @param userRunShareResource 运动分享资源信息
     */
    public void insertUserRunShareResource(UserRunShareResource userRunShareResource) {
        insertToMongo(userRunShareResource);
    }

    /**
     * 根据 id 查询资源信息
     *
     * @param id 编号
     * @param fields 列
     * @return 运动分享资源信息
     */
    public UserRunShareResource findUserRunShareResourceById(Integer id, String...fields) {
        Query query = new Query(Criteria.where("id").is(id).and("status").is(UserRunShareResource.STATUS_NORMAL));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, UserRunShareResource.class);
    }

    /**
     * 根据 id 查询资源信息
     *
     * @param id 编号
     * @param status 状态
     * @param fields 列
     * @return 运动分享资源信息
     */
    public UserRunShareResource findUserRunShareResourceByIdAndStatus(Integer id, Integer status, String...fields) {
        Criteria criteria = Criteria.where("id").is(id);
        if (status != null) {
            criteria.and("status").is(status);
        }
        Query query = new Query();
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, UserRunShareResource.class);
    }


    /**
     * 查询所有 <pre>有效</pre> 运动分享资源信息
     *
     * @param fields 列
     * @return 运动分享资源信息
     */
    public List<UserRunShareResource> findAllUserRunShareResource(String...fields) {
        Query query = Query.query(Criteria.where("status").is(UserRunShareResource.STATUS_NORMAL));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserRunShareResource.class);
    }

    /**
     *  根据 id 更新信息
     *
     * @param id 编号
     * @param update 更新的信息
     */
    public void updateUserRunShareResourceById(Integer id, Update update) {
        Query query = Query.query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update,UserRunShareResource.class);
    }
}