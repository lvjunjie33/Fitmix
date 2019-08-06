package com.business.core.operations.activity;

import com.business.core.entity.Page;
import com.business.core.entity.activity.Activity;
import com.business.core.entity.activity.ActivitySignUp;
import com.business.core.entity.activity.ActivityUser;
import com.business.core.entity.stat.ActivityIntegralStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Sin on 2016/2/16.
 */
@Repository
public class ActivityCoreDao extends BaseMongoDaoSupport {


    /**
     * 活动列表
     *
     * @param page 分页
     * @param fields 列
     */
    public void list(Page<Activity> page, String...fields) {
        LinkedHashMap<String, Object> filter = page.getFilter();

        Criteria criteria = new Criteria();
        // 编号
        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }
        // 主题名
        if (!StringUtils.isEmpty(filter.get("themeName"))) {
            criteria.and("themeName").regex(String.format("%s.*", filter.get("themeName").toString().trim()));
        }
        // 状态
        if (filter.containsKey("status")) {
            criteria.and("status").is(filter.get("status"));
        }
        // 发布状态
        if (filter.containsKey("releaseStatus")) {
            criteria.and("releaseStatus").is(filter.get("releaseStatus"));
        }
        if (filter.containsKey("type")) {
            criteria.and("type").is(filter.get("type"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "status"));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.ASC, "releaseStatus"));
        query.with(new Sort(Sort.Direction.ASC, "activityBeginTime"));
        query.with(new Sort(Sort.Direction.DESC, "id"));


        findEntityPage(Activity.class, page, query, fields);
    }

    /**
     * 添加一个 活动
     *
     * @param activity 活动内容
     */
    public void insertActivity(Activity activity) {
        insertToMongo(activity);
    }

    /**
     * 根据 id 查询活动
     *
     * @param id 编号
     * @param fields 列
     * @return 活动信息
     */
    public Activity findActivityById(int id, String...fields) {
        Query query = new Query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Activity.class);
    }

    /**
     * 根据名称 查询活动
     *
     * @param themeName 活动主题
     * @param fields 列
     * @return 活动信息
     */
    public Activity findActivityByThemeName(String themeName, String...fields) {
        Query query = new Query(Criteria.where("themeName").is(themeName));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Activity.class);
    }

    /**
     * 根据编号更新 活动信息
     *
     * @param id 编号
     * @param update 更新信息
     */
    public void updateActivityById(int id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, Activity.class);
    }

    /**
     * 根据活动编号 用户编号 查询活动
     *
     * @param id 活动编号
     * @param uid 用户编号
     * @param fields 列
     * @return 活动信息
     */
    public Activity findActivityByIdAndUid(int id, int uid, String...fields) {
        Query query = new Query(Criteria.where("id").is(id).and("uid").is(uid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Activity.class);
    }


    ///
    /// ActivityUser


    /**
     * 添加 活动用户信息
     *
     * @param activityUser 活动信息
     */
    public void insertActivityUser(ActivityUser activityUser) {
        insertToMongo(activityUser);
    }

    /**
     * 查询 用户 参加了哪些活动
     *
     * @param ids 活动编号
     * @param uid 用户编号
     * @param fields 列
     * @return 用户信息
     */
    public List<ActivityUser> findActivityUserByIdsAndUid(Collection<Integer> ids, int uid, String...fields) {
        Query query = new Query(Criteria.where("activityId").in(ids).
                and("uid").is(uid).
                and("tradeStatus").is(ActivityUser.TRADE_STATUS_SUCCESS));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, ActivityUser.class);
    }

    /**
     * 更新 活动用户信息
     *
     * @param activityUserId 活动用户报名编号
     * @param update 更信息
     */
    public void updateActivityUserById(Long activityUserId, Update update) {
        updateEntityFieldsById(ActivityUser.class, activityUserId, update);
    }

    /**
     * 更新 活动信息 (交易订单号（商户）)
     *
     * @param tradeNo 商户交易订单号
     * @param update 更新信息
     */
    public void updateActivityUserByTradeNo(String tradeNo, Update update) {
        Query query = new Query(Criteria.where("tradeNo").is(tradeNo));
        getRoutingMongoOps().updateFirst(query, update, ActivityUser.class);
    }

    /**
     * 查询 用户报名信息
     *
     * @param activityId  活动编号
     * @param mobile 手机号码
     * @return
     */
    public ActivityUser findActivityUserByActivityIdAndMobile(Integer activityId, String mobile) {
        Query query = new Query(Criteria.where("activityId").is(activityId).and("userRealInfo.mobilePhone").is(mobile));
        return getRoutingMongoOps().findOne(query, ActivityUser.class);
    }

    /**
     * 查询用户报名 信息
     *
     * @param activityId 活动编号
     * @param uid 用户边哈
     * @return
     */
    public ActivityUser findActivityUserByActivityIdAndUid(Integer activityId, Integer uid) {
        Query query = new Query(Criteria.where("activityId").is(activityId).and("uid").is(uid));
        return getRoutingMongoOps().findOne(query, ActivityUser.class);
    }

    /**
     * 查询用户报名信息
     *
     * @param activityUserId
     *
     * @return
     */
    public ActivityUser findActivityUserById(Integer activityUserId) {
        return getRoutingMongoOps().findOne(Query.query(Criteria.where("id").is(activityUserId)), ActivityUser.class);
    }

    /**
     * 查询最近将开始的活动
     *
     * @param currentTime 当前时间
     * @param releaseStatus 发布状态
     * @param status 数据状态
     * @param fields 查询的字段
     */
    public Activity findCurrentActivity(Long currentTime, Integer releaseStatus, Integer status, String...fields) {
        Criteria criteria = Criteria.where("status").is(status).and("releaseStatus").is(releaseStatus)
                .and("signUpBeginTime").lte(currentTime).and("signUpEndTime").gte(currentTime);
        Query query = Query.query(criteria);
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.ASC, "activityBeginTime"));
        return getRoutingMongoOps().findOne(query, Activity.class);
    }

    /**
     * 查询已经开始的活动
     *
     * @param currentTime 当前时间
     * @param releaseStatus 发布状态
     * @param status 数据状态
     * @param type 赛事类型
     * @param fields 查询的字段
     */
    public List<Activity> findStartActivity(Long currentTime, Integer releaseStatus, Integer status, Integer type, String...fields) {
        Criteria criteria = Criteria.where("status").is(status).and("releaseStatus").is(releaseStatus).and("type").is(type)
        .and("activityBeginTime").lte(currentTime).and("activityEndTime").gte(currentTime);

        Query query = Query.query(criteria);
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.ASC, "activityBeginTime"));
        return getRoutingMongoOps().find(query, Activity.class);
    }

    /**
     * 查询参与活动的用户
     *
     * @param activityIds 活动列表
     */
    public List<ActivitySignUp> findActivitySignUpByActivityIds(List<Integer> activityIds) {
        return getRoutingMongoOps().find(Query.query(Criteria.where("activityId").in(activityIds)), ActivitySignUp.class);
    }

    /**
     * 查询参与活动的用户
     *
     * @param activityId 活动编号
     */
    public List<ActivitySignUp> findActivitySignUpByActivityId(Integer activityId) {
        return getRoutingMongoOps().find(Query.query(Criteria.where("activityId").is(activityId)), ActivitySignUp.class);
    }

    /**
     * 记录用户赛事 积分信息
     * @param stats 用户每日积分信息
     */
    public void insertActivityIntegralStats(List<ActivityIntegralStat> stats) {
        getRoutingMongoOps().insert(stats, ActivityIntegralStat.class);
    }

    /**
     *
     * @param activityId 活动编号
     */
    public List<ActivityIntegralStat> findActivityIntegralStat(Integer statDate, Integer activityId, String...fields) {
        Query query = Query.query(Criteria.where("activityId").is(activityId).and("statDate").is(statDate));
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, ActivityIntegralStat.class);
    }
    /**
     * 前10条数据
     *
     * @param activityId 活动编号
     */
    public List<ActivityIntegralStat> findActivityIntegralStat(Integer statDate, Integer activityId, Integer index, Integer size, String...fields) {
        Query query = Query.query(Criteria.where("activityId").is(activityId).and("statDate").is(statDate));
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        query.skip(index).limit(size);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, ActivityIntegralStat.class);
    }

    /**
     * 前10条数据
     *
     * @param activityId 活动编号
     */
    public List<ActivityIntegralStat> findActivityIntegralStatLast(Integer activityId, Integer index, Integer size, String...fields) {
        Query query = Query.query(Criteria.where("activityId").is(activityId));
        query.with(new Sort(Sort.Direction.DESC, "statDate").and(new Sort(Sort.Direction.ASC, "sort")));
        query.skip(index).limit(size);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, ActivityIntegralStat.class);
    }

    /**
     * 查询指定的赛事的统计情况
     *
     * @param activityIds 赛事列表
     * @param statDate 统计日期
     * @return
     */
    public List<ActivityIntegralStat> findActivityIntegralStats(Set<Integer> activityIds, Integer statDate, Integer index, Integer size, String...fields) {
        Query query = Query.query(Criteria.where("activityId").in(activityIds).and("statDate").is(statDate));
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, ActivityIntegralStat.class);
    }

    /**
     * 获取 第三方赛事
     * @param activityId 第三方赛事编号
     * @param channel 渠道号
     * @return
     */
    public Activity findActivityOutActivityAndChannel(String activityId, Integer channel) {
        Query query = Query.query(Criteria.where("outActivityId").is(activityId).and("channel").is(channel));
        return getRoutingMongoOps().findOne(query, Activity.class);
    }
}
