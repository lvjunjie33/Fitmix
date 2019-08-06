package com.business.core.operations.user;

import com.business.core.entity.mix.Mix;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.entity.user.User;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by sin on 2015/6/5 0005.
 */
@Repository
public class UserCoreDao extends BaseMongoDaoSupport {

    /**
     * 自定义（精选） Mix 歌曲
     *
     * @param scene
     * @param limit
     * @param index
     * @param fields
     * @return
     */
    public List<Mix> findSortMixByType(Integer scene, Integer genre, int limit, int index, String... fields) {
        Criteria criteria = Criteria.where("state").is(Mix.STATE_2);
        if (genre != null) {
            criteria.and("genre").is(genre);
        }
        if (scene != null) {
            criteria.and("scene").is(scene);
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        index = index == 0 ? 0 : (index - 1) * limit;
        query.limit(limit);
        query.skip(index);
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        return getRoutingMongoOps().find(query, Mix.class);
    }

    /**
     * 热门 Mix 歌曲
     *
     * @param scene
     * @param limit
     * @param index
     * @param fields
     * @return
     */
    public List<Mix> findHotMixByType(Integer scene, Integer genre, int limit, int index, String... fields) {
        Criteria criteria = Criteria.where("state").is(Mix.STATE_2);
        if (genre != null) {
            criteria.and("genre").is(genre);
        }
        if (scene != null) {
            criteria.and("scene").is(scene);
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        index = index == 0 ? 0 : (index - 1) * limit;
        query.limit(limit);
        query.skip(index);
        query.with(new Sort(Sort.Direction.DESC, "downloadCount"));
        return getRoutingMongoOps().find(query, Mix.class);
    }

    /**
     * 最新 Mix 歌曲
     *
     * @param scene
     * @param limit
     * @param index
     * @param fields
     * @return
     */
    public List<Mix> findNewMixByType(Integer scene, Integer genre, int limit, int index, String... fields) {
        Criteria criteria = Criteria.where("state").is(Mix.STATE_2);
        if (genre != null) {
            criteria.and("genre").is(genre);
        }
        if (scene != null) {
            criteria.and("scene").is(scene);
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        index = index == 0 ? 0 : (index - 1) * limit;
        query.limit(limit);
        query.skip(index);
        query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
        return getRoutingMongoOps().find(query, Mix.class);
    }

    /**
     * 查询用户总数量
     *
     * @return 用户数量
     */
    public Integer findUserCount(Long beginTime, Long endTime) {
        Criteria criteria = new Criteria();
        if (beginTime != null && endTime != null) {
            criteria.and("addTime").gte(beginTime).lte(endTime);
        } else if (beginTime != null) {
            criteria.and("addTime").gte(beginTime);
        } else if (endTime != null) {
            criteria.and("addTime").lte(endTime);
        }
        criteria.and("state").is(User.STATE_ACTIVATES);
        Query query = new Query(criteria);
        return (int) getRoutingMongoOps().count(query, User.class);
    }

    /**
     * 根据 mail 查询 User 信息
     *
     * @param mail   邮箱
     * @param fields 列
     * @return 用户信息
     */
    public User findUserByEmail(String mail, String... fields) {
        Query query = new Query(Criteria.where("email").is(mail));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.ASC, "id"));
        return getRoutingMongoOps().findOne(query, User.class);
    }

    /**
     * 根据 mail 查询 User 信息
     *
     * @param mobile 手机号
     * @param fields 列
     * @return 用户信息
     */
    public User findUserByMobile(String mobile, String... fields) {
        Query query = new Query(Criteria.where("mobile").is(mobile));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.ASC, "id"));
        return getRoutingMongoOps().findOne(query, User.class);
    }


    /**
     * 根据 id 查询 User 信息
     *
     * @param id     编号
     * @param fields 列
     * @return 用户信息
     */
    public User findUserById(Integer id, String... fields) {
        Query query = new Query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, User.class);
    }

    /**
     * 根据 id 查询 User 信息
     *
     * @param ids    编号集合
     * @param fields 列
     * @return 用户信息
     */
    public List<User> findUserById(Collection<Integer> ids, Integer state, String... fields) {
        Criteria criteria = Criteria.where("id").in(ids);
        if (state != null) {
            criteria.and("state").is(state);
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, User.class);
    }


    /**
     * 根据 id 更新 User 信息
     *
     * @param id     编号
     * @param update 更新列
     */
    public void updateUserById(Integer id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, User.class);
    }

    /**
     * 用户登录，将占用该deviceToken的用户的帐号deviceToken设置为空
     *
     * @param deviceToken 用户设备标识
     */
    public void updateUserByDeviceToken(String deviceToken) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("deviceToken").is(deviceToken)), new Update().unset("deviceToken"), User.class);
    }

    /**
     * 查询并修改 返回新的
     *
     * @param id     用户编号
     * @param update 更新信息
     * @param fields 列
     * @return 修改后的
     */
    public User findAndModifyById(Integer id, Update update, String... fields) {
        Query query = Query.query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findAndModify(query, update, FIND_AND_MODIFY_OPTIONS_RETURN_NEW, User.class);
    }

    /**
     * 更新并修改，返回新的
     *
     * @param id     用户编号
     * @param update 更新信息
     * @return 更新后的
     */
    public User findAndModifyUserByIdNew(Integer id, Update update, String... fields) {
        Query query = Query.query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findAndModify(query, update, FIND_AND_MODIFY_OPTIONS_RETURN_NEW, User.class);
    }

    /**
     * 根据 udid 更新 User 信息
     *
     * @param udid   编号
     * @param update 更新列
     */
    public void updateUserByUdid(String udid, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("udid").is(udid)), update, User.class);
    }

    /**
     * 插入 User 信息
     *
     * @param user 用户信息
     */
    public void insertUser(User user) {
        insertToMongo(user);
    }

    /**
     * 根据添加时间查询用户
     *
     * @param beginAddTime 开始时间
     * @param endAddTime   结束时间
     * @param fields       列
     * @return 用户集合
     */
    public List<User> findUserByAddTime(Long beginAddTime, Long endAddTime, String... fields) {
        Query query = Query.query(Criteria.where("addTime").gte(beginAddTime).lte(endAddTime));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, User.class);
    }

    /**
     * 根据版本号查询用户
     *
     * @param version 版本号
     * @param fields  列
     * @return 用户信息
     */
    public List<User> findUserByVersion(String version, String sortField, String... fields) {
        Query query = Query.query(Criteria.where("version").is(version));
        query.with(new Sort(Sort.Direction.DESC, sortField));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, User.class);
    }

    /**
     * 根据版本号查询用户
     *
     * @param versions 版本号
     * @param fields   列
     * @return 用户信息
     */
    public List<User> findUserByVersion(Collection<String> versions, String sortField, String... fields) {
        Query query = Query.query(Criteria.where("version").in(versions));
        query.with(new Sort(Sort.Direction.DESC, sortField));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, User.class);
    }

    /**
     * 根据名称查询 User
     *
     * @param name   用户名称
     * @param fields 列
     * @return 用户信息
     */
    public User findUserByName(String name, String... fields) {
        Query query = Query.query(Criteria.where("name").is(name));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, User.class);
    }

    /**
     * 根据 手机更新 用户信息
     *
     * @param mobile 手机号
     * @param update 更新信息
     */
    public void updateUserByMobile(String mobile, Update update) {
        Query query = new Query(Criteria.where("mobile").is(update));
        getRoutingMongoOps().updateFirst(query, update, User.class);
    }

    /**
     * @param content    openId
     * @param openidType (1、app注册 2、QQ 注册 3、微信注册  4、微博注册 5、手机)
     * @param fields     列
     */
    public User findUserByOpenidType(String content, Integer openidType, String... fields) {
        Criteria criteria = new Criteria();
        switch (openidType) {
            case 1: // email
                criteria.and("email").is(content);
                break;
            case 2: // QQ
//                criteria.orOperator(Criteria.where("openid").is(content), Criteria.where("qqOpenid").is(content));
                criteria.and("qqOpenid").is(content);
                break;
            case 3:// WeChat
//                criteria.orOperator(Criteria.where("openid").is(content), Criteria.where("wxOpenid").is(content));
                criteria.and("wxOpenid").is(content);
                break;
            case 4: // WeBo
//                criteria.orOperator(Criteria.where("openid").is(content), Criteria.where("wbOpenid").is(content));
                criteria.and("wbOpenid").is(content);
                break;
            case 5: // mobile
                criteria.and("mobile").is(content);
                break;
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, User.class);
    }
}
