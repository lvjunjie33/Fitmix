package com.business.core.entity.user.info;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Created by edward on 2017/12/29.
 *
 * 用户智能设备 (手表、心率耳机、跳绳等等)
 */
@Document(collection = "SmartDevice")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class SmartDevice extends IncIdEntity<Long>{

    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 类型
     * 1、手表
     */
    private Integer type;
    /**
     * 设备唯一标识键
     */
    private String key;
    /**
     * 设备信息
     * Mac地址
     * 主板号
     * SN号
     */
    private Map<String, Object> deviceInfo;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 状态 0删除、1正常
     * {@link com.business.core.constants.Constants#STATUS_YES}
     */
    private Integer status;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Map<String, Object> getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(Map<String, Object> deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
