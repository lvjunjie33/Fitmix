package com.business.core.entity.logs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/7/30 0030.
 */
@Document(collection = "Log_UserBehaviorLog")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class UserBehaviorLog {

    /**
     * 行为情况 1、音乐
     */
    public static final Integer TYPE_MUSIC = 1;
    /**
     * 行为情况 2、运动
     */
    public static final Integer TYPE_RUN = 2;


    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 行为情况 1、音乐  2、运动 3、直播
     */
    private Integer type;
    /**
     * 经度
     */
    private Double lng;
    /**
     * 纬度
     */
    private Double lat;
    /**
     * 添加
     */
    private Long addTime;

    ///
    /// 非存储字典

    @Transient
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
