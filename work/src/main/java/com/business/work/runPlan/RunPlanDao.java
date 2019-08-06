package com.business.work.runPlan;

import com.business.core.entity.Page;
import com.business.core.entity.runPlan.*;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by weird on 2016/5/17.
 */
@Repository
public class RunPlanDao extends BaseMongoDaoSupport {

    /**
     * 跑步计划模型(分页)
     *
     * @param page
     * @param fields
     */
    public void findTempletWithPage(Page<RunPlanTemplate> page, String... fields) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "type", "beginTime", "classify"));
        findEntityPage(RunPlanTemplate.class, page, query, fields);
    }

    /**
     * get runPlanTemplate by id
     *
     * @param id
     * @param fields
     * @return
     */
    public RunPlanTemplate findRunPlanTempletById(Integer id, String... fields) {
        Query query = new Query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, RunPlanTemplate.class);
    }

    /**
     * update runPalnTemplet
     *
     * @param runPlanTemplate
     */
    public void updateRunPlanTemplet(RunPlanTemplate runPlanTemplate) {
        Query query = new Query(Criteria.where("id").is(runPlanTemplate.getId()));
        getRoutingMongoOps().updateFirst(query, Update.update("stages", runPlanTemplate.getStages()), RunPlanTemplate.class);
    }

    /**
     * 跑步计划速度列表(分页)
     *
     * @param page
     * @param fields
     */
    public void runPlanSpeedWithPage(Page<RunPlanSpeed> page, String... fields) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "id", "type", "classify", "ages", "gender"));
        findEntityPage(RunPlanSpeed.class, page, query, fields);
    }

    /**
     * 通过id获取计划速度
     *
     * @param id
     * @param fields
     * @return
     */
    public RunPlanSpeed findRunPlanSpeedById(Integer id, String... fields) {
        Query query = new Query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, RunPlanSpeed.class);
    }

    /**
     * 修改固定的计划后速度
     *
     * @param id
     * @param update
     */
    public void updateRunPlanSpeed(Integer id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, RunPlanSpeed.class);
    }

    /**
     * 获取跑步类型的速度比值列表(分页)
     *
     * @param page
     * @param fields
     */
    public void findStageRatioWithPage(Page<RunPlanStageRatio> page, String... fields) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "type"));
        findEntityPage(RunPlanStageRatio.class, page, query, fields);
    }


    /**
     * 通过类型获取该类型下各跑步类型的比值
     *
     * @param type
     * @param fields
     * @return
     */
    public RunPlanStageRatio findRunPlanStageRatioByType(Integer type, String... fields) {
        Query query = new Query(Criteria.where("type").is(type));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, RunPlanStageRatio.class);
    }

    /**
     * 修改跑步类型的值
     *
     * @param type
     * @param update
     */
    public void updateStageRatio(Integer type, Update update) {
        Query query = new Query(Criteria.where("type").is(type));
        getRoutingMongoOps().updateFirst(query, update, RunPlanStageRatio.class);
    }

    /**
     * RunPlanClassify list (page)
     *
     * @param page
     * @param fields
     */
    public void findClassifyWithPage(Page<RunPlanClassify> page, String... fields) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "id", "type", "classify"));
        findEntityPage(RunPlanClassify.class, page, query, fields);
    }

    /**
     * insert RunPlanClassify
     *
     * @param runPlanClassify
     */
    public void insertRunPlanClassify(RunPlanClassify runPlanClassify) {
        insertToMongo(runPlanClassify);
    }

    /**
     * update RunPlanClassify
     *
     * @param update
     * @param id
     */
    public void updateRunPlanClassify(Update update, Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, RunPlanClassify.class);
    }

    /**
     * get RunPlanClassify without time
     *
     * @param type
     * @param ability
     * @param fields
     * @return
     */
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
    public RunPlanClassify getRunPlanClassify_time(Integer type, Integer ability, Integer speed, String... fields) {
        Query query = new Query(Criteria.where("type").is(type).and("ability").is(ability).and("speed_min").lte(speed).and("speed_max").gte(speed));
        return getRoutingMongoOps().findOne(query, RunPlanClassify.class);
    }

    /**
     * get RunPlanStageRatio by type
     *
     * @param type
     * @return
     */
    public RunPlanStageRatio getRunPlanStageRatio(Integer type) {
        Query query = new Query(Criteria.where("type").is(type));
        return getRoutingMongoOps().findOne(query, RunPlanStageRatio.class);
    }

    /**
     * @param type
     * @param ages
     * @param gender
     * @return
     */
    public RunPlanSpeed getSpeed(Integer type, Integer ability, Integer ages, Integer gender) {
        Query query = new Query(Criteria.where("type").is(type).and("ages").is(ages).and("gender").is(gender).and("classify").is(ability));
        return getRoutingMongoOps().findOne(query, RunPlanSpeed.class);
    }

    public RunPlanTemplate findRunPlanTemplet(Integer type, Integer classify, Integer beginTime) {
        Query query = new Query(Criteria.where("type").is(type).and("classify").is(classify).and("beginTime").is(beginTime));
        return getRoutingMongoOps().findOne(query, RunPlanTemplate.class);
    }

    /**
     *  获取runPlan的描述
     * @return
     */
    public List<RunPlanDescription> getDescription() {
        Query query = new Query();
        return getRoutingMongoOps().find(query, RunPlanDescription.class);
    }

    public void insertRunPlanTemplate(RunPlanTemplate runPlanTemplate) {
        insertToMongo(runPlanTemplate);
    }

    public void findExtraStageWithPage(Page<RunPlanExtraStage> page, String... fields) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "type", "classify"));
        findEntityPage(RunPlanExtraStage.class, page, query, fields);
    }

    public void addRunPlanExtraStage(RunPlanExtraStage runPlanExtraStage) {
        insertToMongo(runPlanExtraStage);
    }

    public void findRunPlanDescriptionWithPage(Page<RunPlanDescription> page, String... fields) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "id"));
        findEntityPage(RunPlanDescription.class, page, query, fields);
    }

    public void addRunPlanDescription(RunPlanDescription runPlanDescription) {
        insertToMongo(runPlanDescription);
    }

    public List<RunPlanTemplate> getRunPlanTemplateList() {
        Query query = new Query();
        return getRoutingMongoOps().find(query, RunPlanTemplate.class);
    }

    public void findUserRunPlanWithPage(Page<UserRunPlan> page, String...fields) {
        Map<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();
        criteria.and("plan_state").is(filter.get("plan_state"));
        if (filter.containsKey("endTime")) {
            criteria.and("endTime").is(filter.get("endTime"));
        }
        if (filter.containsKey("uid")) {
            criteria.and("uid").is(filter.get("uid"));
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "id"));
        findEntityPage(UserRunPlan.class, page, query, fields);
    }

    /**
     * 查询训练计划
     *
     * @param id 编号
     */
    public UserRunPlan findUserRunPlanById(Integer id, String...fields) {
        return findEntityById(UserRunPlan.class, id, fields);
    }

    /**
     * 修改训练计划
     *
     * @param id 训练计划编号
     * @param update 修改的内容
     */
    public void modifyRunPlan(Integer id, Update update) {
        updateEntityFieldsById(UserRunPlan.class, id, update);
    }
}
