package com.business.core.entity.task;

import com.alibaba.druid.sql.visitor.functions.Lpad;
import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by fenxio on 2016/11/8.
 */
public class TaskFinishDetails {

    private Integer id;
    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 任务类型
     */
    private Integer taskType;
    /**
     * 任务
     */
    private String taskKey;
    /**
     * 附加ID
     */
    private String additionId;
    /**
     * 完成时间
     */
    private String finishTime;
    /**
     * 添加时间
     */
    private Long addTime;

    private Long finishCount;

    private TaskInfo taskInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getAdditionId() {
        return additionId;
    }

    public void setAdditionId(String additionId) {
        this.additionId = additionId;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public Long getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Long finishCount) {
        this.finishCount = finishCount;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public TaskFinishDetails() {
    }

    public TaskFinishDetails(Integer uid, Integer taskType, String taskKey, String additionId, String finishTime, Long addTime) {
        this.uid = uid;
        this.taskType = taskType;
        this.taskKey = taskKey;
        this.additionId = additionId;
        this.finishTime = finishTime;
        this.addTime = addTime;
    }
}

