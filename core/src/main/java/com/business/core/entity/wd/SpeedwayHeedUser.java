package com.business.core.entity.wd;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2016/7/20.
 * 关注过 某赛道的 用户信息
 */
@Document(collection = "SpeedwayHeedUser")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class SpeedwayHeedUser extends IncIdEntity<Long>{

    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 赛道信息
     */
    private Integer speedwayId;
    /**
     * 关注时间
     */
    private Long heedTime;
    ///
    ///====================今天的运动累计记录==================================
    /**
     * 今日时间
     */
    private Integer currentDate;
    /**
     * 赛道总里程
     */
    private Long sumDistance;
    /**
     * 赛道总步数
     */
    private Long sumStep;
    /**
     * 赛道总卡路里
     */
    private Long sumCalorie;
    /**
     * 最后一次操作时间
     */
    private Long lastOperationTime;

    ///==================================================
    /**
     * 今日排名
     */
    @Transient
    private Integer sort;
    /**
     * 用户信息 {@link User}
     */
    @Transient
    private User user;
    /**
     * 赛道信息
     */
    @Transient
    private Speedway speedway;
    /**
     * 链接地址
     */
    @Transient
    private String link;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSpeedwayId() {
        return speedwayId;
    }

    public void setSpeedwayId(Integer speedwayId) {
        this.speedwayId = speedwayId;
    }

    public Long getHeedTime() {
        return heedTime;
    }

    public void setHeedTime(Long heedTime) {
        this.heedTime = heedTime;
    }

    public Integer getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Integer currentDate) {
        this.currentDate = currentDate;
    }

    public Long getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(Long sumDistance) {
        this.sumDistance = sumDistance;
    }

    public Long getSumStep() {
        return sumStep;
    }

    public void setSumStep(Long sumStep) {
        this.sumStep = sumStep;
    }

    public Long getSumCalorie() {
        return sumCalorie;
    }

    public void setSumCalorie(Long sumCalorie) {
        this.sumCalorie = sumCalorie;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Speedway getSpeedway() {
        return speedway;
    }

    public void setSpeedway(Speedway speedway) {
        this.speedway = speedway;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getLastOperationTime() {
        return lastOperationTime;
    }

    public void setLastOperationTime(Long lastOperationTime) {
        this.lastOperationTime = lastOperationTime;
    }
}
