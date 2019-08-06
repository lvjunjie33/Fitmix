package com.business.core.entity.wd;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2016/7/20.
 *  万德赛道排名统计
 */
@Document(collection = "SpeedwayRunStat")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class SpeedwayRunStat {
    /**
     * 活动编号 {@link Speedway#id}
     */
    private Integer speedwayId;
    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;

    /**
     * 历史累计卡路里
     */
    private Long sumCalorie;
    /**
     * 历史累计总步数
     */
    private Long sumStep;
    /**
     * 历史累计运动里程
     */
    private Long sumDistance;

    /**
     * 排名
     */
    private Integer sort;

    /**
     * 单日的总步数
     */
    private Long step;
    /**
     * 单日的总里程
     */
    private Long distance;
    /**
     * 单日总里程
     */
    private Long calorie;

    /**
     * 统计日期
     */
    private Integer statDate;

    /**
     * 添加时间
     */
    private Long addTime;

    ///
    ///视图显示

    @Transient
    private User user;

    public Integer getSpeedwayId() {
        return speedwayId;
    }

    public void setSpeedwayId(Integer speedwayId) {
        this.speedwayId = speedwayId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getSumStep() {
        return sumStep;
    }

    public void setSumStep(Long sumStep) {
        this.sumStep = sumStep;
    }

    public Long getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(Long sumDistance) {
        this.sumDistance = sumDistance;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }

    public Integer getStatDate() {
        return statDate;
    }

    public void setStatDate(Integer statDate) {
        this.statDate = statDate;
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

    public Long getSumCalorie() {
        return sumCalorie;
    }

    public void setSumCalorie(Long sumCalorie) {
        this.sumCalorie = sumCalorie;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getCalorie() {
        return calorie;
    }

    public void setCalorie(Long calorie) {
        this.calorie = calorie;
    }
}
