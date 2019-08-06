package com.business.work.scheduler;

import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by edward on 2016/6/17.
 */
@Repository
public class SchedulerDao extends BaseMongoDaoSupport{

    /**
     * 查询定时任务操作记录
     *
     * @param id 定时任务编号
     *//*
    public SchedulerTaskRecord findSchedulerTaskRecord(Long id) {
        return getRoutingMongoOps().findOne(Query.query(Criteria.where("id").is(id)), SchedulerTaskRecord.class);
    }

    *//**
     * 添加定时任务操作记录
     *//*
    public void addSchedulerTaskRecord(SchedulerTaskRecord schedulerTaskRecord) {
        insertToMongo(schedulerTaskRecord);
    }

    *//**
     * 添加定时任务
     *
     * @param task 定时任务
     *//*
    public void addTask(Task task) {
        insertToMongo(task);
    }

    *//**
     * 修改定时任务配置信息
     *
     * @param id 定时任务编号
     * @param update 修改的内容
     *//*
    public Task findAndModifyTask(Long id, Update update, String...fields) {
        Query query = Query.query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findAndModify(query, update, BaseMongoDaoSupport.FIND_AND_MODIFY_OPTIONS_RETURN_OLD, Task.class);
    }

    *//**
     * 通过主键查询 定时任务
     *
     * @param id 主键编号
     * @param fields 查询的字段
     *//*
    public Task findTaskById(Long id, String...fields) {
        Query query = Query.query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return findEntityById(Task.class, id, fields);
    }

    *//**
     * 修改任务信息
     *
     * @param id 任务编号
     * @param update 修改的内容
     *//*
    public void modifyTask(Long id, Update update) {
        Query query = Query.query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, Task.class);
    }

    *//**
     * 定时任务 分页查询
     *
     * @param page 分页对象
     *//*
    public void list(Page<Task> page, String...fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (filter.containsKey("topicName")) {
            criteria.and("topicName").is(filter.get("topicName"));
        }
        if (filter.containsKey("currentStatus")) {
            criteria.and("currentStatus").is(filter.get("currentStatus"));
        }
        if (filter.containsKey("status")) {
            criteria.and("status").is(filter.get("status"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(Task.class, page, query, fields);
    }

    *//**
     * 通过当前状态修改定时任务
     *
     * @param currentStatus 当前任务状态
     *//*
    public void updateByCurrentStatus(Integer currentStatus, Update update) {
        Query query = Query.query(Criteria.where("currentStatus").is(currentStatus));
        getRoutingMongoOps().updateFirst(query, update, Task.class);
    }*/

}
