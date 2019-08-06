package com.business.core.entity.startPage;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by edward on 2016/5/16.
 * app 启动页 信息
 */
@Document(collection = "StartPage")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class StartPage extends IncIdEntity<Long>{
    /**
     * 启动页标题
     */
    private String title;
    /**
     * 启动页倒计时的时长
     */
    private Integer countdown;
    /**
     * 背景轮播图片(目前只有一张)
     */
    private String backgroundImg;
    /**
     * 启动页 启用状态
     * {@link com.business.core.constants.Constants#DATA_STATUS_1}
     */
    private Integer status;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 截止时间
     * (说明:超过截止时间，则此广告过期)
     */
    private Long deadline;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 描述
     */
    private String des;
    /**
     * 发布状态
     */
    private Integer releaseStatus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCountdown() {
        return countdown;
    }

    public void setCountdown(Integer countdown) {
        this.countdown = countdown;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }
}
