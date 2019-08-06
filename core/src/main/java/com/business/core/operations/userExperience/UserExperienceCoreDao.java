package com.business.core.operations.userExperience;

import com.business.core.entity.user.UserExperience;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sin on 2015/9/7.
 */
@Repository
public class UserExperienceCoreDao extends BaseMongoDaoSupport {

    /**
     * 添加 体验用户
     * @param userExperience 体验用户对象
     */
    public void insert(UserExperience userExperience) {
        insertToMongo(userExperience);
    }

    /**
     * 查询 id 体验用户
     * @param id 编号
     * @param fields 列
     */
    public void findUserExperienceById(Integer id, String...fields) {
        getRoutingMongoOps().find(Query.query(Criteria.where("id").is(id)), UserExperience.class);
    }

    /**
     * 查询 udid 体验用户
     * @param udid 设备号
     * @param fields 列
     */
    public UserExperience findUserExperienceByUdid(String udid, String...fields) {
        return getRoutingMongoOps().findOne(Query.query(Criteria.where("udid").is(udid)), UserExperience.class);
    }

    /**
     * 根据 id 更新体验用户信息
     * @param id 编号
     * @param update 更新
     */
    public void updateUserExperienceById(Integer id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, UserExperience.class);
    }

    /**
     *
     * 根据 udid 更新体验用户信息
     * @param udid 编号
     * @param update 更新
     */
    public void updateUserExperienceByUdid(String udid, Update update) {
        Query query = new Query(Criteria.where("udid,").is(udid));
        getRoutingMongoOps().updateFirst(query, update, UserExperience.class);
    }

    /**
     * 根据添加时间查询用户
     * @param beginAddTime  开始时间
     * @param endAddTime 结束时间
     * @param fields 列
     * @return 用户集合
     */
    public List<UserExperience> findUserExperienceByAddTime(Long beginAddTime, Long endAddTime, String...fields) {
        Query query = Query.query(Criteria.where("addTime").gte(beginAddTime).lte(endAddTime));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserExperience.class);
    }
}
