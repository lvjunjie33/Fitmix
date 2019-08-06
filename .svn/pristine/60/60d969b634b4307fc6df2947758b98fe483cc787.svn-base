package com.business.core.entity.joinActivity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

/**
 * Created by fenxio on 2016/7/7.
 */
@Document(collection = "JoinActivityEntered")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class JoinActivityEntered extends IncIdEntity<Long> {

    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 报名人数
     */
    private Integer enteredNum;
    /**
     * 报名组信息
     */
    private HashMap<Object, Object> enteredMem;
    /**
     * 活动编号
     */
    private String activityId;
    /**
     * 渠道号
     */
    private Integer channel;

    /**
     * 通知ID
     */
    private String notifyId;
    /**
     * 添加时间
     */
    private Long addTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getEnteredNum() {
        return enteredNum;
    }

    public void setEnteredNum(Integer enteredNum) {
        this.enteredNum = enteredNum;
    }

    public HashMap<Object, Object> getEnteredMem() {
        return enteredMem;
    }

    public void setEnteredMem(HashMap<Object, Object> enteredMem) {
        this.enteredMem = enteredMem;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
