package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.TaoBaoIp;
import com.business.core.entity.mix.Mix;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Administrator on 2015-05-22.
 */
@Document(collection = "UserRunShare")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserRunShare {
    /**
     * 编号
     */
    private String id;
    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 浏览次数
     */
    private Integer browseCount;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 分享背景 根据 {@link UserRunShareResource#imageUrl} 随机
     */
    private String imageUrl;
    /**
     * 分享内容 根据 {@link UserRunShareResource#content} 随机
     */
    private String content;
    /**
     * 分享地址
     */
    private TaoBaoIp address;


    /// 2.0 修改后信息

    /**
     * 距离 （米）
     */
    private String distance;
    /**
     * 运动时间（秒）
     */
    private String runTime;
    /**
     * 均速
     */
    private String step;
    /**
     * 配速
     */
    private String matchingSpeed;
    /**
     * 大卡
     */
    private String calorie;


    ///
    /// 非存储字段

    /**
     * 用户信息
     */
    @Transient
    private User user;
    @Transient
    private Mix mix;
    @Transient
    private String runTimeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TaoBaoIp getAddress() {
        return address;
    }

    public void setAddress(TaoBaoIp address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getMatchingSpeed() {
        return matchingSpeed;
    }

    public void setMatchingSpeed(String matchingSpeed) {
        this.matchingSpeed = matchingSpeed;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Mix getMix() {
        return mix;
    }

    public void setMix(Mix mix) {
        this.mix = mix;
    }

    public String getRunTimeStr() {
        return runTimeStr;
    }

    public void setRunTimeStr(String runTimeStr) {
        this.runTimeStr = runTimeStr;
    }
}
