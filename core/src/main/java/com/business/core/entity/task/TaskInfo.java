package com.business.core.entity.task;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import com.business.core.mongo.RoutingMongoOperations;
import com.business.core.utils.BeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/11/7.
 */
public class TaskInfo {

    /**
     * status/正常
     */
    public static final Integer STATUS_NORMAL = 0;
    /**
     * status/无效
     */
    public static final Integer STATUS_INVALID = 1;
    /**
     * finish status/已完成
     */
    public static final Integer FINISH_STATUS_DONE = 0;
    /**
     * finish status/未完成
     */
    public static final Integer FINISH_STATUS_UNDONE = 1;
    /**
     * type / 每日任务
     */
    public static final Integer TYPE_EVERY_DAY = 0;
    /**
     * type / 一次性任务
     */
    public static final Integer TYPE_ONE_OFF = 1;
    /**
     * type / 荣誉任务
     */
    public static final Integer TYPE_HONOR = 2;
    /**
     * type / 分享任务
     */
    public static final Integer TYPE_SHARE = 3;



    private Integer id;
    /**
     * 任务类型
     */
    private Integer taskType;
    /**
     * 任务
     */
    private String taskKey;
    /**
     * 积分
     */
    private Integer coin;
    /**
     * 0：正常 1：无效
     */
    private Integer status;
    /**
     * 描述
     */
    private String description;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 修改时间
     */
    private Long modifyTime;

    // 非储存字段
    /**
     * 任务完成状态
     */
    private Integer finishStatus;

    public TaskInfo(Long addTime, Long modifyTime, Integer status, Integer coin, String taskKey, Integer taskType, String description) {
        this.addTime = addTime;
        this.modifyTime = modifyTime;
        this.status = status;
        this.coin = coin;
        this.taskKey = taskKey;
        this.taskType = taskType;
        this.description = description;
    }

    public TaskInfo() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }
}
