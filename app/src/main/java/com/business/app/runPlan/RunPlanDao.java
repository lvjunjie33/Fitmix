
package com.business.app.runPlan;

import com.business.core.entity.runPlan.*;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by weird on 2016/5/11.
 */

@Repository
public class RunPlanDao extends BaseMongoDaoSupport {

    /**
     * 将跑步阶段比值写入数据库
     *
     * @param runPlanStageRatio
     */

    public void setData(RunPlanStageRatio runPlanStageRatio) {
        getRoutingMongoOps().insert(runPlanStageRatio);
    }

    public RunPlanStageRatio getStageRatio(Integer type) {
        Query query = new Query(Criteria.where("type").is(type));
        return getRoutingMongoOps().findOne(query, RunPlanStageRatio.class);
    }

    public RunPlanStageRatio getRunPlanStageRatio(Integer type) {
        Query query = new Query(Criteria.where("type").is(type));
        return getRoutingMongoOps().findOne(query, RunPlanStageRatio.class);
    }

    /**
     * 能力值为0~2之间的固定跑步速度
     *
     * @param type
     * @param ability
     * @param ages
     * @param gender
     * @return
     */
    public RunPlanSpeed getSpeed(Integer type, Integer ability, Integer ages, Integer gender) {
        Query query = new Query(Criteria.where("type").is(type).and("ages").is(ages).and("gender").is(gender).and("classify").is(ability));
        return getRoutingMongoOps().findOne(query, RunPlanSpeed.class);
    }

    /**
     * get RunPlanClassify without time
     *
     * @param type
     * @param ability
     * @param fields
     * @return
     */
    @Deprecated
    public RunPlanClassify getRunPlanClassify(Integer type, Integer ability, String... fields) {
        Query query = new Query(Criteria.where("type").is(type).and("ability").is(ability));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, RunPlanClassify.class);
    }

    /**
     * get RunPlanClssify
     *
     * @param type
     * @param ability
     * @param speed
     * @param fields
     * @return
     */
    @Deprecated
    public RunPlanClassify getRunPlanClassify_time(Integer type, Integer ability, Integer speed, String... fields) {
        Query query = new Query(Criteria.where("type").is(type).and("ability").is(ability).and("speed_min").lte(speed).and("speed_max").gte(speed));
        return getRoutingMongoOps().findOne(query, RunPlanClassify.class);
    }

    public RunPlanClassify getRunPlanClassify(Integer type, Integer ability, Integer speed) {
        Criteria criteria = new Criteria();
        criteria.and("type").is(type).and("ability").is(ability);
        if (speed > 0) {
            criteria.and("speed_min").lte(speed).and("speed_max").gte(speed);
        }
        Query query = new Query(criteria);
        return getRoutingMongoOps().findOne(query, RunPlanClassify.class);
    }

    /**
     * 获取runPLanTemplet
     *
     * @param type
     * @param classify
     * @param beginTime
     * @return
     */
    public RunPlanTemplate findRunPlanTemplet(Integer type, Integer classify, Integer beginTime) {
        Query query = new Query(Criteria.where("type").is(type).and("classify").is(classify).and("beginTime").is(beginTime));
        return getRoutingMongoOps().findOne(query, RunPlanTemplate.class);
    }

    /**
     * 写入UserRunPlan
     *
     * @param userRunPlan
     */
    public void insertUserRunPlan(UserRunPlan userRunPlan) {
        insertToMongo(userRunPlan);
    }

    /**
     * 查询用户计划
     *
     * @param uid
     * @return
     */
    public List<UserRunPlan> findUserRunPlanListByUid(Integer uid) {
        Query query = new Query(Criteria.where("uid").is(uid));
        return getRoutingMongoOps().find(query, UserRunPlan.class);
    }

    /**
     * 查询用户正在执行的计划
     *
     * @return
     */
    public UserRunPlan findUserRunPlanByUid(Integer uid) {
        Query query = new Query(Criteria.where("uid").is(uid).and("plan_state").is(UserRunPlan.PLAN_IMPLEMENTATION));
        return getRoutingMongoOps().findOne(query, UserRunPlan.class);
    }

    /**
     * 计划延期
     *
     * @param userRunPlan
     */
    public void delayUserRunPlan(UserRunPlan userRunPlan) {
        Query query = new Query(Criteria.where("uid").is(userRunPlan.getUid()).and("plan_state").is(UserRunPlan.PLAN_IMPLEMENTATION));
        Update update = new Update();
        update.set("beginTime", userRunPlan.getBeginTime());
        update.set("planTime", userRunPlan.getPlanTime());
        update.set("competitionTime", userRunPlan.getCompetitionTime());
        update.set("endTime", userRunPlan.getEndTime());
        update.set("stagesLists", userRunPlan.getStagesLists());
        getRoutingMongoOps().updateFirst(query, update, UserRunPlan.class);
    }

    /**
     * 删除用户的跑步计划
     *
     * @param uid
     */
    public void deleteUserRunPlan(Integer uid) {
        Query query = new Query(Criteria.where("uid").is(uid).and("plan_state").is(UserRunPlan.PLAN_IMPLEMENTATION));
        getRoutingMongoOps().remove(query, UserRunPlan.class);
    }

    /**
     * 获取特定页面的所有相关描述
     *
     * @return
     */
    public List<RunPlanDescription> getRunPlanDescription(int position) {
        Query query = new Query(Criteria.where("position").is(position));
        return getRoutingMongoOps().find(query, RunPlanDescription.class);
    }

    /**
     * 获取正在进行中的计划
     *
     * @param uid
     * @return
     */
    public UserRunPlan getPresentPlan(Integer uid) {
        Query query = new Query(Criteria.where("uid").is(uid).and("plan_state").is(UserRunPlan.PLAN_IMPLEMENTATION));
        return getRoutingMongoOps().findOne(query, UserRunPlan.class);
    }

    /**
     * 获取结束的计划
     *
     * @param uid
     * @return
     */
    public List<UserRunPlan> getEndPlan(Integer uid) {
        Criteria criteria = new Criteria();
        criteria.where("uid").is(uid);
        Criteria[] criterias = new Criteria[1];
        /*criterias[0] = Criteria.where("plan_state").is(UserRunPlan.PLAN_OBSOLETE);*/
        criterias[0] = Criteria.where("plan_state").is(UserRunPlan.PLAN_OFF_THE_STHCKS);
        criteria.orOperator(criterias);
        Query query = new Query(criteria);
        return getRoutingMongoOps().find(query, UserRunPlan.class);
    }

    public void updateUserRunPlan(Update update, Integer uid) {
        Query query = new Query(Criteria.where("uid").is(uid).and("plan_state").is(UserRunPlan.PLAN_IMPLEMENTATION));
        getRoutingMongoOps().updateFirst(query, update, UserRunPlan.class);
    }

    /**
     * 获取已过去的计划(即已完成计划)
     *
     * @param uid
     * @return
     */
    public List<UserRunPlan> getPastPlanByUid(Integer uid) {
        Query query = new Query(Criteria.where("uid").is(uid).and("plan_state").is(UserRunPlan.PLAN_OFF_THE_STHCKS));
        return getRoutingMongoOps().find(query, UserRunPlan.class);
    }

    public List<RunPlanTemplate> getRunPlanTemplateByType(Integer type) {
        Query query = new Query(Criteria.where("type").is(type));
        return getRoutingMongoOps().find(query, RunPlanTemplate.class);
    }

}

