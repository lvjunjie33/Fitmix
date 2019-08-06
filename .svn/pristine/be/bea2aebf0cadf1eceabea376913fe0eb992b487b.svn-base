package com.business.core.entity.stat;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/9/8.
 * 总新增用户、新增注册用户、新增游客用户，做折线图，图可以按照日、周、月变换
 * 按日显示、显示近周
 * 每天执行一次
 */
@Document(collection = "Stat_UserExperienceAndUserStat")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class UserExperienceAndUserStat {

    /**
     * 用户总数量：包括体验用户
     * {@link com.business.core.entity.user.User}
     * {@link com.business.core.entity.user.UserExperience}
     */
    private Integer totalCount;
    /**
     * 注册用户：不包括体验用户(相当于 User 总用户数)
     */
    private Integer registerCount;
    /**
     * 体验用户
     */
    private Integer experienceCount;

    ///
    /// 累加数量

    /**
     * 累加数量 ：用户总数量 （包括体验用户）
     */
    private Long appendTotalCount;
    /**
     * 累加数量 ：注册用户
     */
    private Long appendRegisterCount;
    /**
     * 累加数量：体验用户
     */
    private Long appendExperienceCount;
    /**
     * 添加时间 方便构建 Echarts yyyy-MM-dd
     */
    private String addTimeStr;
    /**
     * 添加时间
     */
    private Long addTime;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }

    public Integer getExperienceCount() {
        return experienceCount;
    }

    public void setExperienceCount(Integer experienceCount) {
        this.experienceCount = experienceCount;
    }

    public Long getAppendTotalCount() {
        return appendTotalCount;
    }

    public void setAppendTotalCount(Long appendTotalCount) {
        this.appendTotalCount = appendTotalCount;
    }

    public Long getAppendRegisterCount() {
        return appendRegisterCount;
    }

    public void setAppendRegisterCount(Long appendRegisterCount) {
        this.appendRegisterCount = appendRegisterCount;
    }

    public Long getAppendExperienceCount() {
        return appendExperienceCount;
    }

    public void setAppendExperienceCount(Long appendExperienceCount) {
        this.appendExperienceCount = appendExperienceCount;
    }

    public String getAddTimeStr() {
        return addTimeStr;
    }

    public void setAddTimeStr(String addTimeStr) {
        this.addTimeStr = addTimeStr;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
