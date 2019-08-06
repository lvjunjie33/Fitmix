package com.business.core.entity.stat;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2016/5/27.
 */
@Document(collection = "ActivityIntegralStat")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class ActivityIntegralStat {

    /**
     * 活动编号 {@link com.business.core.entity.activity.Activity#id}
     */
    private Integer activityId;
    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;

    /**
     * 历史累计总积分
     */
    private Long sumIntegral;
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
    private Long integral;
    /**
     * 单日的总积分
     */
    private Long distance;

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

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getStatDate() {
        return statDate;
    }

    public void setStatDate(Integer statDate) {
        this.statDate = statDate;
    }

    public Long getSumIntegral() {
        return sumIntegral;
    }

    public void setSumIntegral(Long sumIntegral) {
        this.sumIntegral = sumIntegral;
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

    public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
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
}
