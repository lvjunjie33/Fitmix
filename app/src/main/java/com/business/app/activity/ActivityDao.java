package com.business.app.activity;

import com.business.core.entity.activity.Activity;
import com.business.core.entity.activity.ActivitySignUp;
import com.business.core.entity.stat.ActivityIntegralStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by edward on 2016/4/16.
 */
@Repository
public class ActivityDao extends BaseMongoDaoSupport {

    /**
     * 记录用户活动报名信息
     *
     * @param activitySignUp 报名信息
     */
    public void insertActivitySignUp(ActivitySignUp activitySignUp) {
        insertToMongo(activitySignUp);
    }

    /**
     * 查询报名信息
     *
     * @param activityId 活动编号
     * @param mobile 手机号码
     * @param fields 查询的字段
     */
    public ActivitySignUp findActivitySignUp(Integer activityId, String mobile, String...fields) {
        Query query = Query.query(Criteria.where("activityId").is(activityId).and("mobilePhone").is(mobile));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, ActivitySignUp.class);
    }

    /**
     * 查询报名信息
     *
     * @param activityId 活动编号
     * @param userId 用户编号
     * @param fields 查询的字段
     */
    public ActivitySignUp findActivitySignUp(Integer activityId, Integer userId, String...fields) {
        Query query = Query.query(Criteria.where("activityId").is(activityId).and("userId").is(userId));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().findOne(query, ActivitySignUp.class);
    }

    /**
     * 查询报名信息
     *
     * @param userId 用户编号
     * @param fields 查询的字段
     */
    public List<ActivitySignUp> findActivitySignUps(Integer userId, String...fields) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, ActivitySignUp.class);
    }

    /**
     * 修改活动报名信息
     *
     * @param activityId 活动编号
     * @param mobile 报名联系人手机号码
     * @param update 修改的内容
     */
    public void modifyActivitySignUp(Integer activityId, String mobile, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("activityId").is(activityId).and("mobilePhone").is(mobile)), update, ActivitySignUp.class);
    }

    /**
     * 查询某个用户在某次赛事 的最近一次积分统计信息
     * @param activityId 赛事活动编号
     * @param uid 用户编号
     */
    public ActivityIntegralStat findActivityIntegralStatByUidAndActivityId(Integer activityId, Integer uid, Integer statDate) {
        Query query = Query.query(Criteria.where("activityId").is(activityId).and("uid").is(uid).and("statDate").is(statDate));
        return getRoutingMongoOps().findOne(query, ActivityIntegralStat.class);
    }

    public ActivityIntegralStat findActivityIntegralLast(Integer activityId, Integer uid) {
        Query query = Query.query(Criteria.where("activityId").is(activityId).and("uid").is(uid));
        query.with(new Sort(Sort.Direction.DESC, "statDate").and(new Sort(Sort.Direction.ASC, "sort")));
        return getRoutingMongoOps().findOne(query, ActivityIntegralStat.class);
    }

    /**
     * 判断身份证是否已经参与报名了
     *
     * @param idCards 身份证列表
     * @param activityId 赛事编号
     */
    public long findActivitySignUpByIdCardAndActivityId(Collection<String> idCards, Integer activityId) {
        Query query = Query.query(Criteria.where("activityId").is(activityId).and("activitySignUpMembers.idCard").in(idCards));
        return getRoutingMongoOps().count(query, ActivitySignUp.class);
    }

    /**
     * 查询报名组数
     *
     * @param activityId 赛事编号
     */
    public long findActivitySignUpByActivityId(Integer activityId) {
        Query query = Query.query(Criteria.where("activityId").is(activityId));
        return getRoutingMongoOps().count(query, ActivitySignUp.class);
    }

    /**
     * 获取 第三方赛事
     * @param activityId 第三方赛事编号
     * @param channel 渠道号
     */
    public Activity findActivityOutActivityAndChannel(String activityId, Integer channel) {
        Query query = Query.query(Criteria.where("outActivityId").is(activityId).and("channel").is(channel));
        return getRoutingMongoOps().findOne(query, Activity.class);
    }

    /**
     * 累加报名人数
     * @param id 报名信息编号
     * @param enteredNum 报名人数
     */
    public void updateActivitySignUpNumber(Integer id, Integer enteredNum) {
        Update update = new Update();
        update.inc("activitySignUpNumber", enteredNum);
        updateEntityFieldsById(ActivitySignUp.class, id, update);
    }
}
