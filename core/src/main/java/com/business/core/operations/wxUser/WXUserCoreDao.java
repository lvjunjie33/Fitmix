package com.business.core.operations.wxUser;

import com.business.core.entity.wx.WXUser;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sin on 2015/10/13.
 */
@Repository
public class WXUserCoreDao extends BaseMongoDaoSupport {

    /**
     * 查询 第一个，最新的 WXUser
     * @param fields 列
     * @return 微信用户信息
     */
    public WXUser findFirstWXUser(String...fields) {
        Query query = new Query();
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().findOne(query, WXUser.class);
    }

    /**
     * 根据 unionid 查询 微信用户信息
     * @param unionid
     * @param fields 列
     * @return 微信用户信息
     */
    public WXUser findWXUserByUnionId(String unionid, String...fields) {
        Query query = new Query(Criteria.where("unionid").is(unionid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, WXUser.class);
    }

    /**
     * 根据 openid 查询 微信用户信息
     * @param openid
     * @param fields 列
     * @return 微信用户信息
     */
    public WXUser findWXUserByOpenid(String openid, String...fields) {
        Query query = new Query(Criteria.where("openid").is(openid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, WXUser.class);
    }

    /**
     * 查询所有
     * @return
     */
    public List<WXUser> findAll(String...fields) {
        Query query = new Query();
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, WXUser.class);
    }

    /**
     * 添加 微信用户信息
     * @param wxUser 微信用户
     */
    public void insertWXUser(WXUser wxUser) {
        insertToMongo(wxUser);
    }
}
