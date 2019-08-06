package com.business.app.wd;

import com.business.core.constants.Constants;
import com.business.core.entity.wd.Speedway;
import com.business.core.entity.wd.SpeedwayHeedUser;
import com.business.core.entity.wd.SpeedwayRun;
import com.business.core.entity.wd.SpeedwayRunStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by edward on 2016/7/20.
 */
@Repository
public class SpeedwayDao extends BaseMongoDaoSupport{

    /**
     * 保存 万德赛道运动数据
     *
     * @param speedwayRun 赛道运动数据
     */
    public void saveSpeedwayRun(SpeedwayRun speedwayRun) {
        insertToMongo(speedwayRun);
    }

    /**
     * 保存关注信息
     *
     * @param speedwayHeedUser
     */
    public void saveSpeedwayHeedUser(SpeedwayHeedUser speedwayHeedUser) {
        insertToMongo(speedwayHeedUser);
    }

    /**
     * 修改关注信息
     *
     * @param id 编号
     * @param update 修改的内容
     */
    public void updateSpeedwayHeedUser(Long id, Update update) {
        updateEntityFieldsById(SpeedwayHeedUser.class, id, update);
    }

    /**
     * 查询用户关注的赛道
     * @param uid 用户编号
     */
    public List<SpeedwayHeedUser> findSpeedwayHeedUserByUid(Integer uid, String...fields) {
        Query query = Query.query(Criteria.where("uid").is(uid));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "lastOperationTime"));
        return getRoutingMongoOps().find(query, SpeedwayHeedUser.class);
    }

    /**
     * 查询用户关注的赛道
     * @param uid 用户编号
     */
    public SpeedwayHeedUser findSpeedwayHeedUserByUidAndSpeedwayId(Integer uid, Integer speedwayId, String...fields) {
        Query query = Query.query(Criteria.where("uid").is(uid).and("speedwayId").is(speedwayId));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, SpeedwayHeedUser.class);
    }

    /**
     * 查询赛道信息
     *
     * @param city 城市
     * @param wayId 万德赛道编号
     * @param field 查询的字段
     */
    public Speedway findSpeedwayByCityAndWayId(String city, String wayId, String...field) {
        Query query = Query.query(Criteria.where("city").is(city).and("wayId").is(wayId).and("releaseStatus").is(Constants.RELEASE_STATUS_RELEASE));
        includeFields(query, field);
        return getRoutingMongoOps().findOne(query, Speedway.class);
    }

    /**
     * 查询前十名
     *
     * @param speedwayId 赛道编号
     * @param statDate 统计日期
     */
    public List<SpeedwayRunStat> findSpeedwayRunStats(Integer speedwayId, Integer statDate) {
        Query query = Query.query(Criteria.where("speedwayId").is(speedwayId).and("statDate").is(statDate));
        query.skip(0).limit(10);
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        return getRoutingMongoOps().find(query, SpeedwayRunStat.class);
    }

    /**
     * 查询自己的历史排名信息
     *
     * @param speedwayId 赛道编号
     * @param statDate 统计日期
     * @param uid 用户编号
     */
    public SpeedwayRunStat findSpeedwayRunStatsByUid(Integer speedwayId, Integer statDate, Integer uid) {
        Query query = Query.query(Criteria.where("speedwayId").is(speedwayId).and("statDate").is(statDate).and("uid").is(uid));
        return getRoutingMongoOps().findOne(query, SpeedwayRunStat.class);
    }

    /**
     * 查询今天的赛道 运动信息
     *
     * @param speedwayId 赛道编号
     * @param currentDate 今天的日期
     */
    public List<SpeedwayHeedUser> findSpeedwayHeedUserBySpeedwayId(Integer speedwayId, Integer currentDate, String...fields) {
        Query query = Query.query(Criteria.where("speedwayId").is(speedwayId).and("currentDate").is(currentDate));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "sumCalorie"));
        return getRoutingMongoOps().find(query, SpeedwayHeedUser.class);
    }

    /**
     * 通过赛道编号 查询赛道列表
     * @param ids 编号列表
     */
    public List<Speedway> findSpeedwayByIds(Collection<Integer> ids, String...fields) {
        Query query = Query.query(Criteria.where("id").in(ids));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Speedway.class);
    }
}
