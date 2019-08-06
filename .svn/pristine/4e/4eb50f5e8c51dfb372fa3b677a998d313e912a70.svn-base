package com.business.core.operations.userRun;

import com.business.core.entity.Page;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.UserRunStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by edward on 2016/5/27.
 */
@Repository
public class UserRunCoreDao extends BaseMongoDaoSupport{

    /**
     * 查询某个时间段的用户运动记录
     *
     * @param uids 用户编号
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param types 运动类型
     * @param fields 查询的字段
     */
    public List<UserRun> findUserRunByTime(List<Integer> uids, Long startTime, Long endTime, List<Integer> types, String...fields) {
        Query query = Query.query(Criteria.where("uid").in(uids).and("startTime").gte(startTime).and("endTime").lte(endTime).and("type").in(types));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserRun.class);
    }

    /**
     * 查询某个时间段的用户运动记录
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param types 运动类型
     * @param fields 查询的字段
     */
    public List<UserRun> findUserRunByTimeAll( Long startTime, Long endTime, List<Integer> types, String...fields) {
        Query query = Query.query(Criteria.where("startTime").gte(startTime).lte(endTime).and("type").in(types));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserRun.class);
    }

    /**
     * 查询万德  某个时间段的用户运动记录
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param types 运动类型
     * @param fields 查询的字段
     */
    public List<UserRun> findUserRunByTimeAllWd( Long startTime, Long endTime, List<Integer> types, String...fields) {
        Query query = Query.query(Criteria.where("startTime").gte(startTime).lte(endTime).and("type").in(types).and("speedwayRunInfo").exists(true));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserRun.class);
    }

    /**
     * 查询某个时间段的用户运动记录
     *
     * @param uid 用户编号
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param types 运动类型
     * @param fields 查询的字段
     */
    public List<UserRun> findUserRunByTime(Integer uid, Long startTime, Long endTime, List<Integer> types, String...fields) {
        Query query = Query.query(Criteria.where("uid").is(uid).and("startTime").gte(startTime).and("endTime").lte(endTime).and("type").in(types));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserRun.class);
    }

    /**
     * 运动排行榜分页
     *
     * @param page 分页对象
     */
    public void runMonthRank(Page<UserRunStat> page, String...fields) {
        LinkedHashMap filter = page.getFilter();
        Criteria criteria = Criteria.where("pace").ne(null).gt(0).and("statTime").is(filter.get("statTime")).and("type").is(filter.get("type"));
        if (filter.containsKey("level")) {
            criteria.and("level").is(filter.get("level"));
        }
        if (filter.containsKey("maxDistance")) {
            if (filter.containsKey("minDistance")) {
                criteria.and("sumDistance").gte(filter.get("minDistance")).lte(filter.get("maxDistance"));
            } else {
                criteria.and("sumDistance").lte(filter.get("maxDistance"));
            }
        } else {
            if (filter.containsKey("minDistance")) {
                criteria.and("sumDistance").gte(filter.get("minDistance"));
            }
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "sumDistanceValid")).with(new Sort(Sort.Direction.ASC, "pace")).with(new Sort(Sort.Direction.DESC, "registerTime"));
        findEntityPage(UserRunStat.class, page, query, fields);
    }

}
