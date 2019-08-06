package com.business.app.im;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.im.IMInfoGroup;
import com.business.core.entity.im.IMInfoUser;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2016/10/17.
 */
@Repository
public class IMDao extends BaseMongoDaoSupport{

    /**
     * 保存 环信用户帐号相关 信息
     *
     * @param user 环信用户帐号
     */
    public void saveIMInfoUser(IMInfoUser user) {
        insertToMongo(user);
    }

    /**
     * 查询用户的环信帐号信息
     *
     * @param uid 用户编号
     * @param fields 查询的字段
     */
    public IMInfoUser findIMInfoUser(Integer uid, String...fields) {
        Query query = Query.query(Criteria.where("uid").is(uid).and("state").is(Constants.STATE_EFFECTIVE));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, IMInfoUser.class);
    }

    /**
     * 查询用户的当前位置
     *
     * @param username 环信帐号
     */
    public IMInfoUser findIMInfoUserByUsername(String username, String...fields) {
        Query query = Query.query(Criteria.where("username").is(username).and("state").is(Constants.STATE_EFFECTIVE));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, IMInfoUser.class);
    }

    /**
     * 查询用户的当前位置
     *
     * @param usernames 环信帐号
     */
    public List<IMInfoUser> findIMInfoUserByUsernames(List<String> usernames, String...fields) {
        Query query = Query.query(Criteria.where("username").in(usernames).and("state").is(Constants.STATE_EFFECTIVE));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, IMInfoUser.class);
    }

    /**
     * 修改当前的位置
     *
     * @param username 环信帐号
     * @param update 修改的内容
     */
    public void modifyIMInfoUserByUsername(String username, Update update) {
        Query query = Query.query(Criteria.where("username").is(username).and("state").is(Constants.STATE_EFFECTIVE));
        getRoutingMongoOps().updateFirst(query, update, IMInfoUser.class);
    }

    /**
     * 保存环信群组信息
     *
     * @param imInfoGroup 群组信息
     */
    public void saveIMInfoGroup(IMInfoGroup imInfoGroup) {
        insertToMongo(imInfoGroup);
    }

    /**
     * 修改环信群组相关信息
     *
     * @param groupId 群组编号
     * @param update 修改的内容
     */
    public void modifyIMInfoGroup(String groupId, Update update) {
        Query query = Query.query(Criteria.where("groupId").is(groupId));
        getRoutingMongoOps().updateFirst(query, update, IMInfoGroup.class);
    }

    /**
     * 环信群组分页查询
     *
     * @param page 分页对象
     * @param fields 查询的字段
     */
    public void groupPage(Page<IMInfoGroup> page, String...fields) {
        Criteria criteria = Criteria.where("state").is(Constants.STATE_EFFECTIVE);
        Map<String, Object> filter = page.getFilter();
        if (filter.containsKey("groupName")) {
            criteria.and("groupName").regex(filter.get("groupName").toString());
        }
        if (filter.containsKey("groupId")) {
            criteria.and("groupId").is(filter.get("groupId"));
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "groupName"));
        findEntityPage(IMInfoGroup.class, page, query, fields);
    }

    /**
     * 查询某个群组的信息
     *
     * @param groupId 群组编号
     */
    public IMInfoGroup findGroup(String groupId, String...fields) {
        Query query = Query.query(Criteria.where("groupId").is(groupId));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, IMInfoGroup.class);
    }

    /**
     * 查询用户帐号
     *
     * @param userNames 用户环信帐号列表
     */
    public List<IMInfoUser> findUserNames(String[] userNames, String...fields) {
        Query query = Query.query(Criteria.where("username").in(Arrays.asList(userNames)));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, IMInfoUser.class);
    }
}
