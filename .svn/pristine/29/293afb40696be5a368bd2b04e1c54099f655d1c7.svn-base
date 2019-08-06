package com.business.core.entity.stat;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/6/8 0008.
 */
@Document(collection = "Stat_UserActiveRetainedStat")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class UserActiveRetainedStat {

    /**
     * 一次性用户( % (以新增一年以内且超过7天（不含查询当天）的用户为样本，计算其中只在新增当天活跃，后续再无活跃的用户数。以及这些用户占样本的比例。)
     */
    private Double oneTimeUser;
    /**
     * 日活跃用户 % (当日的活跃用户占累计用户比例。)
     */
    private Double dayActiveUser;
    /**
     * 周活跃用户 % (截至当日，最近一周（含当日的7天）活跃用户数和这些用户占累计用户的比例。)
     */
    private Double weekActiveUser;
    /**
     * 月活跃用户 % (截至当日，最近一月（含当日的30天）活跃用户数和这些用户占累计用户的比例。)
     */
    private Double monthActiveUser;
    /**
     * 次日留存率（%） (次日留存率是指某日的新增用户中，第二日有使用过应用的用户比例。)
     */
    private Double dayRetained;
    /**
     * 周留存率（%） (7日留存率是指7日内的新增用户中，7日内有使用过应用的用户比例。)
     */
    private Double weekRetained;
    /**
     * 月留存率（%） (30日留存率是指30日内的新增用户中，30日内有使用过应用的用户比例。)
     */
    private Double monthRetained;
    /**
     * 添加时间
     */
    private Long addTime;

    ///
    /// 非存储字段

    @Transient
    private String toDay;


    public Double getOneTimeUser() {
        return oneTimeUser;
    }

    public void setOneTimeUser(Double oneTimeUser) {
        this.oneTimeUser = oneTimeUser;
    }

    public Double getDayActiveUser() {
        return dayActiveUser;
    }

    public void setDayActiveUser(Double dayActiveUser) {
        this.dayActiveUser = dayActiveUser;
    }

    public Double getWeekActiveUser() {
        return weekActiveUser;
    }

    public void setWeekActiveUser(Double weekActiveUser) {
        this.weekActiveUser = weekActiveUser;
    }

    public Double getMonthActiveUser() {
        return monthActiveUser;
    }

    public void setMonthActiveUser(Double monthActiveUser) {
        this.monthActiveUser = monthActiveUser;
    }

    public Double getDayRetained() {
        return dayRetained;
    }

    public void setDayRetained(Double dayRetained) {
        this.dayRetained = dayRetained;
    }

    public Double getWeekRetained() {
        return weekRetained;
    }

    public void setWeekRetained(Double weekRetained) {
        this.weekRetained = weekRetained;
    }

    public Double getMonthRetained() {
        return monthRetained;
    }

    public void setMonthRetained(Double monthRetained) {
        this.monthRetained = monthRetained;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getToDay() {
        return toDay;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
    }
}
