package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * Created by sin on 2015/12/25.
 *
 *  用户每天的运动（每天总记录）
 *
 * <p>
 *     包括以下信息：
 *     1、用户当天跑步记录，总和
 *     2、后台计步器 步数
 * </p>
 *
 */
@Document(collection = "UserEveryDayRun")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserEveryDayRun extends IncIdEntity<Long> {


    ///
    /// 用户运动信息

    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 运动编号
     */
    private Set<Long> runId;
    /**
     * 运动时间
     */
    private Long runTime;
    /**
     * 距离
     */
    private Long distance;
    /**
     * 卡路里
     */
    private Long calorie;
    /**
     * 步数
     */
    private Long step;

    ///
    /// 计步器

    /**
     * 步数（计步器 数据）
     */
    private Long rememberStep;

    ///
    /// 其他

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

    public Set<Long> getRunId() {
        return runId;
    }

    public void setRunId(Set<Long> runId) {
        this.runId = runId;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
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

    public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }

    public Long getRememberStep() {
        return rememberStep;
    }

    public void setRememberStep(Long rememberStep) {
        this.rememberStep = rememberStep;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
