package com.business.core.entity;

/**
 * 定时任务配置
 * User: jeff
 * Date: 13-8-27
 * Time: 上午10:54
 */
public class SchedulerJobConfig {

    /**
     * 任务状态 - 未初始化
     */
    public static final Integer STATUS_NOT_INIT = 0;
    /**
     * 任务状态 - 正常运行
     */
    public static final Integer STATUS_RUNNING = 1;
    /**
     * 任务状态 - 运行异常
     */
    public static final Integer STATUS_RUN_EXCEPTION = 2;
    /**
     * 任务状态 - 暂停运行
     */
    public static final Integer STATUS_STOP = 3;
    /**
     * 任务状态 - 已被移除
     */
    public static final Integer STATUS_REMOVED = 4;
    /**
     * 触发类型 - 时间间隔，单位：秒
     */
    public static final String TRIGGER_TYPE_SIMPLE = "Simple";
    /**
     * 触发类型 - Cron表达式
     */
    public static final String TRIGGER_TYPE_CRON = "Cron";
    /**
     * 默认分组
     */
    public static final String DEFAULT_GROUP = "DEFAULT";

    /**
     * 编号
     */
    private Integer id;
    /**
     * 任务名
     */
    private String jobNickname;
    /**
     * 映射服务名
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 触发器名字
     */
    private String triggerName;
    /**
     * 触发器分组
     */
    private String triggerGroup;
    /**
     * 触发器类型
     */
    private String triggerType;
    /**
     * 触发器表达式
     */
    private String triggerExpression;
    /**
     * 任务描述
     */
    private String jobDesc;
    /**
     * 任务状态
     */
    private Integer status;

    ///
    /// 非数据库字段


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobNickname() {
        return jobNickname;
    }

    public void setJobNickname(String jobNickname) {
        this.jobNickname = jobNickname;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public String getTriggerExpression() {
        return triggerExpression;
    }

    public void setTriggerExpression(String triggerExpression) {
        this.triggerExpression = triggerExpression;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
