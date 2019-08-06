package com.business.core.entity.stat;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Administrator on 2015/6/5 0005.
 */
@Document(collection = "Stat_UserGrowthLossStat")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class UserGrowthLossStat {

    /**
     * 用户数量
     */
    private Integer userCount;
    /**
     * 新增用户数 (新增加的应用使用者（按设备），重安装应用不会重复计量。)
     */
    private Integer newAddUserCount;
    /**
     * 活跃用户数 (当日有使用应用（至少启动一次）的用户数。)
     */
    private Integer userActiveCount;
    /**
     * 新用户比例 (活跃用户中包含了新增用户和积累下的老用户，此比例为新增用户在其中所占的比率。)
     */
    private Double newUserScale;
    /**
     * 平均使用时间 - 毫秒 (算, 用户跑步时间)
     */
    private Long avgUseTime;
    /**
     * 用户登录次数 - 用户启动次数
     */
    private Integer loginTimes;
    /**
     * 人均启动 (当日，用户平均登录)  <<< (不包含, 未登录用户)
     */
    private Double eachUserLoginTimes;
    /**
     * 时间（小时）
     */
    private Integer hour;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * str 添加时间 (yyyy-MM-dd)
     */
    private String strAddTime;

    ///
    /// 非存储字段

    @Transient
    private String toDay;


    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getNewAddUserCount() {
        return newAddUserCount;
    }

    public void setNewAddUserCount(Integer newAddUserCount) {
        this.newAddUserCount = newAddUserCount;
    }

    public Integer getUserActiveCount() {
        return userActiveCount;
    }

    public void setUserActiveCount(Integer userActiveCount) {
        this.userActiveCount = userActiveCount;
    }

    public Double getNewUserScale() {
        return newUserScale;
    }

    public void setNewUserScale(Double newUserScale) {
        this.newUserScale = newUserScale;
    }

    public Long getAvgUseTime() {
        return avgUseTime;
    }

    public void setAvgUseTime(Long avgUseTime) {
        this.avgUseTime = avgUseTime;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public Double getEachUserLoginTimes() {
        return eachUserLoginTimes;
    }

    public void setEachUserLoginTimes(Double eachUserLoginTimes) {
        this.eachUserLoginTimes = eachUserLoginTimes;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getStrAddTime() {
        return strAddTime;
    }

    public void setStrAddTime(String strAddTime) {
        this.strAddTime = strAddTime;
    }

    public String getToDay() {
        return toDay;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
    }
}
