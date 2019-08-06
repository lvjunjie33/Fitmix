package com.business.work.stat;

import com.business.core.entity.Page;
import com.business.core.entity.logs.SnapTable;
import com.business.core.entity.stat.*;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.UserRunStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/6/9 0009.
 */
@Repository
public class StatDao extends BaseMongoDaoSupport {

    /**
     * 查询用户活跃,和留存信息
     * @param gteAddTime
     * @param lteAddTime
     * @return
     */
    public List<UserActiveRetainedStat> findUserActiveRetainedStatByGteAddTimeAndLte (Long gteAddTime, Long lteAddTime) {
        Query query = new Query(Criteria.where("addTime").gte(gteAddTime).lte(lteAddTime));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, UserActiveRetainedStat.class);
    }

    /**
     * 查询 新增用户,用户流失
     * @param gteAddTime
     * @param lteAddTime
     * @return
     */
    public List<UserGrowthLossStat> findUserGrowthLossStatByGteAddTimeAndLte (Long gteAddTime, Long lteAddTime) {
        Query query = new Query(Criteria.where("addTime").gte(gteAddTime).lte(lteAddTime));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, UserGrowthLossStat.class);
    }

    /**
     * 查询本日最新运动数据
     * @param startTime 开始时间
     * @param endTime 结束时间
//     * @param limit 条数
     * @param fields 列
     * @return 用户运动集合
     */
   public List<UserRun> findUserRunWithToday(long startTime, long endTime, /*Integer limit, */String... fields) {
       Query query = new Query(Criteria.where("startTime").gte(startTime).lte(endTime));
//       query.skip(0).limit(limit);
       includeFields(query, fields);
       return getRoutingMongoOps().find(query, UserRun.class);
   }


    /**
     * 根据添加时间 查询注册渠道
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param fields 列
     * @return 用户注册渠道
     */
    public List<UserRegisterChannelStat> findUserRegisterChannelStatByAddTime(Long beginTime, Long endTime, String...fields) {
        Query query = new Query(Criteria.where("addTime").gte(beginTime).lte(endTime));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserRegisterChannelStat.class);
    }

    /**
     * 用户注册类型，根据ids
     * @param ids 编号
     * @param fields 列
     * @return 用户注册类型集合
     */
    public List<UserRegisterTypeStat> findUserRegisterTypeStatByIds(Collection<ObjectId> ids, String...fields) {
        Query query = new Query(Criteria.where("id").in(ids));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserRegisterTypeStat.class);
    }

    /**
     * 根据添加时间 查询 体验用户 和 用户
     * @param beginAddTime 开始时间
     * @param endAddTime 结束时间
     * @param fields 列
     * @return 集合
     */
    public List<UserExperienceAndUserStat> findUserExperienceAndUserStatByAddTime(Long beginAddTime, Long endAddTime, String...fields) {
        Query query = new Query(Criteria.where("addTime").gte(beginAddTime).lte(endAddTime));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserExperienceAndUserStat.class);
    }

    /**
     * 分页
     *
     * @param page 分页对象
     * @param fields 查询的字段
     */
    public void cityRunStat(Page<SnapTable> page, String...fields) {
        Map<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();
        if (filter.containsKey("city")) {
            criteria.and("values.city").regex(filter.get("city").toString());
        }
        if (filter.containsKey("type")) {
            criteria.and("type").is(filter.get("type"));
        }
        if (filter.containsKey("planId")) {
            criteria.and("values.planId").is(filter.get("planId"));
        }

        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(SnapTable.class, page, query, fields);
    }

    /**
     * 删除计划
     *
     * @param id 编号
     */
    public void removeSnap(Long id) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("id").is(id)), SnapTable.class);
    }

    /**
     * 删除城市计划 统计详细
     *
     * @param planId 计划编号
     */
    public void removeSnapByPlanId(Long planId) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("values.planId").is(planId)), SnapTable.class);
    }


    public void removeRunStat() {
        getRoutingMongoOps().remove(Query.query(new Criteria()), UserRunStat.class);
    }

    public void statRepeatUserDelete() {
        getRoutingMongoOps().remove(Query.query(Criteria.where("type").is(SnapTable.TYPE_REPEAT_USER)), SnapTable.class);
    }

    public void deleteSnapTableById(Long id) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("id").is(id)), SnapTable.class);
    }

    public void deleteSnapTableByMobile(String mobile, Set<Integer> uids) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("values.mobile").is(mobile).and("type").is(SnapTable.TYPE_REPEAT_USER).and("values.uid").ne(uids)), SnapTable.class);
    }

    public void deleteSnapTableByEmail(String email, Set<Integer> uids) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("values.email").is(email).and("type").is(SnapTable.TYPE_REPEAT_USER).and("values.uid").ne(uids)), SnapTable.class);
    }
}
