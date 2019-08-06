package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/9/18.
 * 用户运动排行
 */
@Document(collection = "UserRunRank")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserRunRank extends IncIdEntity<Integer> {

    /**
     * 每日排行
     */
    public static final Integer TYPE_DAY = 1;
    /**
     * 每周排行
     */
    public static final Integer TYPE_WEEK = 2;
    /**
     * 每月排行
     */
    public static final Integer TYPE_MONTH = 3;


    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 类型 1：天 2：周 3：月
     */
    private Integer type;
    /**
     * 对应type 的值
     */
    private String typeValue;
    /**
     * 距离
     */
    private Long distance;
    /**
     * 创建时间
     */
    private Long addTime;

    @Transient
    private User user;

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

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
