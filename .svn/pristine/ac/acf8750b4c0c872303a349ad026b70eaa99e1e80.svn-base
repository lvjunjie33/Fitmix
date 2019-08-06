package com.business.app.userSkipRope;

import com.business.core.constants.Constants;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.UserSkipRopeShare;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by edward on 2016/5/23.
 */
@Repository
public class UserSkipRopeDao extends BaseMongoDaoSupport{

    /**
     * 通过用户编号和跳绳开始时间查询跳绳记录
     *
     * @param uid 用户编号
     * @param startTime 开始时间
     * @param fields 查询的字段
     */
    public UserRun findByUidAndStartTime(Integer uid, Long startTime, String...fields) {
        Query query = Query.query(Criteria.where("uid").is(uid).and("startTime").is(startTime)
                .and("type").is(UserRun.TYPE_SKIP_ROPE));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "startTime"));
        return getRoutingMongoOps().findOne(query, UserRun.class);
    }

    /**
     * 记录用户跳绳信息
     *
     * @param userSkipRope 跳绳信息
     */
    public void insert(UserRun userSkipRope) {
        insertToMongo(userSkipRope);
    }

    /**
     * 通过用户编号和跳绳开始时间 修改跳绳记录信息
     *
     * @param uid 用户编号
     * @param startTime 跳绳开始时间
     * @param update 修改的内容
     */
    public void updateByUidAndStartTime(Integer uid, Long startTime, Update update) {
        Query query = Query.query(Criteria.where("uid").is(uid).and("startTime")
                .is(startTime).and("type").is(UserRun.TYPE_SKIP_ROPE));
        getRoutingMongoOps().updateFirst(query, update, UserRun.class);
    }

    /**
     *
     * 通过用户编号和跳绳开始时间 修改跳绳记录信息
     *
     * @param uid 用户编号
     * @param startTimes 跳绳开始时间 列表
     * @param update 修改的内容
     */
    public void updateByUidAndStartTimes(Integer uid, List<Long> startTimes, Update update) {
        Query query = Query.query(Criteria.where("uid").is(uid).and("startTime").in(startTimes)
                .and("type").is(UserRun.TYPE_SKIP_ROPE));
        getRoutingMongoOps().updateFirst(query, update, UserRun.class);
    }

    /**
     * 查询最后一次用户 跳绳记录
     *
     * @param uid 用户编号
     * @param fields 查询的字段
     */
    public UserRun findLastUpdateSkipRope (Integer uid, String...fields) {
        Query query = new Query(Criteria.where("uid").is(uid).and("type").is(UserRun.TYPE_SKIP_ROPE));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "updateTime"));
        return getRoutingMongoOps().findOne(query, UserRun.class);
    }

    /**
     * 查询最后一次有效的用户 跳绳记录
     *
     * @param uid 用户编号
     * @param fields 查询的字段
     */
    public UserRun findLastUpdateSkipRope2 (Integer uid, String...fields) {
        Query query = new Query(Criteria.where("uid").is(uid).and("type").is(UserRun.TYPE_SKIP_ROPE).and("state").is(UserRun.STATE_EFFECTIVE).and("heartRate").ne(null));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "updateTime"));
        return getRoutingMongoOps().findOne(query, UserRun.class);
    }

    /**
     * 查询 new 的 用户跳绳记录
     *
     * @param uid 用户编号
     * @param addTime 添加时间
     * @param fields 列
     * @return list 集合
     */
    public List<UserRun> findNewSkipRope (Integer uid, Long addTime, String... fields) {
        Query query = new Query(Criteria.where("uid").is(uid).and("updateTime").gt(addTime)
                .and("state").is(Constants.STATE_EFFECTIVE).and("type").is(UserRun.TYPE_SKIP_ROPE));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "startTime"));
        return getRoutingMongoOps().find(query, UserRun.class);
    }

    /**
     * 查询 remove 作废的 跳绳记录
     *
     * @param uid 用户编号
     * @param updateTime 更新记录时间
     * @param fields 列
     * @return list 集合
     */
    public List<UserRun> findRemoveUserSkipRope (Integer uid, Long updateTime, String... fields) {
        Query query = new Query(Criteria.where("uid").is(uid).and("updateTime").gt(updateTime)
                .and("state").is(Constants.STATE_INVALID).and("type").is(UserRun.TYPE_SKIP_ROPE));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "startTime"));
        return getRoutingMongoOps().find(query, UserRun.class);
    }

    /**
     * 根据用户编号，查询用户跳绳记录信息
     * @param uid 用户编号
     * @param fields 列
     * @return 用户跳绳历史记录 集合
     */
    public List<UserRun> findByUidWidthStartTime(Integer uid, String... fields) {
        Query query = new Query(Criteria.where("state").is(Constants.STATE_EFFECTIVE).and("uid").is(uid)
                .and("type").is(UserRun.TYPE_SKIP_ROPE));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "startTime"));
        return getRoutingMongoOps().find(query, UserRun.class);
    }

    /**
     * 根据用户编号和运动时间 查找分享记录
     * @param uid
     * @param startTime
     * @param fields
     * @return
     */
    public UserSkipRopeShare findUserSkipRopeByIdAndStartTime(Integer uid, Long startTime, String ... fields) {
        Query query = new Query(Criteria.where("uid").is(uid).and("startTime").is(startTime));
        return getRoutingMongoOps().findOne(query, UserSkipRopeShare.class);
    }

    /**
     * 保存 用户跳绳分享记录
     * @param userSkipRopeShare
     */
    public void saveUserSkipRopeShare(UserSkipRopeShare userSkipRopeShare) {
        insertToMongo(userSkipRopeShare);
    }
}
