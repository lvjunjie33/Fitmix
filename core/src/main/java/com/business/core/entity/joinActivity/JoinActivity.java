package com.business.core.entity.joinActivity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

/**
 * Created by fenxio on 2016/7/7.
 * 赛事接入
 */
@Document(collection = "JoinActivity")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class JoinActivity extends IncIdEntity<Long> {

    /**
     * 待加入
     */
    public static final Integer STATUS_TO_JOIN = 0;
    /**
     * 已加入
     */
    public static final Integer STATUS_HAS_JOIN = 1;
    /**
     * 拒绝
     */
    public static final Integer STATUS_REFUSED = 2;

    /**
     * 活动标题
     */
    private String themeName;
    /**
     * 活动封面图片
     */
    private String themeImage;
    /**
     * 活动描述
     */
    private String desc;
    /**
     * 活动地址
     */
    private String url;
    /**
     * 活动编号
     */
    private String activityId;
    /**
     * 活动开始时间
     */
    private Long activityBeginTime;
    /**
     * 活动结束时间
     */
    private Long activityEndTime;
    /**
     * 报名开始时间
     */
    private Long signUpBeginTime;
    /**
     * 报名结束时间
     */
    private Long signUpEndTime;
    /**
     * 渠道号
     */
    private Integer channel;
    /**
     * 报名金额
     */
    private HashMap<Object, Object> activityMoney = new HashMap<>();
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 状态
     */
    private Integer status;


    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeImage() {
        return themeImage;
    }

    public void setThemeImage(String themeImage) {
        this.themeImage = themeImage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Long getActivityBeginTime() {
        return activityBeginTime;
    }

    public void setActivityBeginTime(Long activityBeginTime) {
        this.activityBeginTime = activityBeginTime;
    }

    public Long getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Long activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Long getSignUpBeginTime() {
        return signUpBeginTime;
    }

    public void setSignUpBeginTime(Long signUpBeginTime) {
        this.signUpBeginTime = signUpBeginTime;
    }

    public Long getSignUpEndTime() {
        return signUpEndTime;
    }

    public void setSignUpEndTime(Long signUpEndTime) {
        this.signUpEndTime = signUpEndTime;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public HashMap<Object, Object> getActivityMoney() {
        return activityMoney;
    }

    public void setActivityMoney(HashMap<Object, Object> activityMoney) {
        this.activityMoney = activityMoney;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
