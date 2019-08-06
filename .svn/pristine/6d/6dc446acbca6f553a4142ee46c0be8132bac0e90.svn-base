package com.business.work.scheduler;

import org.springframework.stereotype.Service;

/**
 * 定时器Service
 * User: sin
 * Date: 15-8-27
 * Time: 上午10:50
 */
@Service
public class SchedulerService {
/*
    @Autowired
    private SchedulerDao schedulerDao;

    public void list(Page<Task> page){
        schedulerDao.list(page);
    }

    *//**
     * 添加定时任务
     *
     * @param cron 表达式
     * @param topicName 消息通道名称
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param des 描述
     *//*
    public void addTask(String cron, String topicName, Long beginTime, Long endTime, String des) {
        Long currentTime = System.currentTimeMillis();
        Task task = new Task();
        task.setAddTime(currentTime);
        task.setCron(cron);
        task.setBeginTime(beginTime);
        task.setEndTime(endTime);
        task.setTopicName(topicName);
        task.setLastTime(System.currentTimeMillis());
        task.setStatus(Constants.STATUS_NORMAL);
        task.setCurrentStatus(Task.CURRENT_STATUS_UNSTART);
        task.setDes(des);
        schedulerDao.addTask(task);
    }

    *//**
     * 修改任务信息
     *
     * @param id 任务编号
     * @param cron 表达式
     * @param topicName 类名
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param des 描述
     * @param status 任务状态
     *//*
    public void modify(Long id, String cron, String topicName, Long beginTime, Long endTime, String des, Integer status) {
        schedulerDao.modifyTask(id, Update.update("cron", cron).set("topicName", topicName).set("beginTime", beginTime).set("endTime", endTime).set("des", des).set("status", status));
    }

    *//**
     * 查询 定时任务
     *
     * @param id 任务编号
     *//*
    public Task findTaskById(Long id) {
        return schedulerDao.findTaskById(id);
    }

    *//**
     * 定时任务命令
     *
     * @param id 任务编号
     * @param type 命令类型 {@link Task#CURRENT_STATUS_START}
     *//*
    public void taskCmd(Long id, Integer type) {

        SchedulerTaskRecord schedulerTaskRecord = new SchedulerTaskRecord();
        schedulerTaskRecord.setTaskId(id);
        schedulerTaskRecord.setAddTime(System.currentTimeMillis());
        schedulerTaskRecord.setOperationType(type);
        schedulerTaskRecord.setType(SchedulerTaskRecord.TYPE_OPERATION);
        schedulerDao.addSchedulerTaskRecord(schedulerTaskRecord);

        if (SchedulerTaskRecord.OPERATION_TYPE_STOP == type) {
            schedulerDao.modifyTask(id, Update.update("currentStatus", Task.CURRENT_STATUS_UNSTART));
        }

        Task task = this.findTaskById(id);
        //指定通道
        schedulerTaskRecord.setSelectTopic(task.getTopicName());

        BeanManager.getBean(MqSendCore.class).send(BeanManager.getBean(SchedulerTaskMessageHandle.class), schedulerTaskRecord);
    }

    *//**
     * 恢复数据
     *
     * @param id 任务编号
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @param offset 时间偏移量
     *//*
    public void restoreHistory(Long id, String bTime, String eTime, Long offset) {

        SchedulerTaskRecord schedulerTaskRecord = new SchedulerTaskRecord();
        schedulerTaskRecord.setTaskId(id);
        schedulerTaskRecord.setAddTime(System.currentTimeMillis());
        schedulerTaskRecord.setOperationType(SchedulerTaskRecord.OPERATION_TYPE_RESTORE_HISTORY);
        schedulerTaskRecord.setRunStatus(SchedulerTaskRecord.RUN_STATUS_READY);

        schedulerTaskRecord.setbTime(bTime);
        schedulerTaskRecord.seteTime(eTime);
        schedulerTaskRecord.setOffset(offset);
        schedulerTaskRecord.setType(SchedulerTaskRecord.TYPE_OPERATION);

        schedulerDao.addSchedulerTaskRecord(schedulerTaskRecord);

        Task task = this.findTaskById(id);
        //指定通道
        schedulerTaskRecord.setSelectTopic(task.getTopicName());

        BeanManager.getBean(MqSendCore.class).send(BeanManager.getBean(SchedulerTaskMessageHandle.class), schedulerTaskRecord);
    }

    *//**
     * 重设cron 表达式
     *
     * @param id 任务编号
     * @param newCron 表达式
     *//*
    public void resetCron(Long id, String newCron) {
        newCron = newCron.trim();
        Task task = schedulerDao.findAndModifyTask(id, Update.update("cron", newCron), "cron");

        SchedulerTaskRecord schedulerTaskRecord = new SchedulerTaskRecord();
        schedulerTaskRecord.setTaskId(id);
        schedulerTaskRecord.setAddTime(System.currentTimeMillis());
        schedulerTaskRecord.setOperationType(SchedulerTaskRecord.OPERATION_TYPE_RESET_CRON);
        schedulerTaskRecord.setRunStatus(SchedulerTaskRecord.RUN_STATUS_READY);

        schedulerTaskRecord.setNewCron(newCron);
        schedulerTaskRecord.setOldCron(task.getCron());
        schedulerTaskRecord.setType(SchedulerTaskRecord.TYPE_OPERATION);
        schedulerDao.addSchedulerTaskRecord(schedulerTaskRecord);

        //指定通道
        schedulerTaskRecord.setSelectTopic(task.getTopicName());

        BeanManager.getBean(MqSendCore.class).send(BeanManager.getBean(SchedulerTaskMessageHandle.class), schedulerTaskRecord);
    }

    *//**
     * 修改定时任务运行状态信息
     *
     * @param taskId 定时任务编号
     * @param currentStatus 当前状态
     *//*
    public void updateTaskStatus(Long taskId, Integer currentStatus) {
        schedulerDao.modifyTask(taskId, Update.update("currentStatus", currentStatus));
    }*/
}
