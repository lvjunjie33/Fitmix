package com.business.core.entity.startVideo;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2016/5/17.
 * app 首次登录启动视频
 */
@Document(collection = "StartVideo")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class StartVideo extends IncIdEntity<Long> {

    /**
     * 视频标题
     */
    private String title;
    /**
     * 视频描述
     */
    private String des;
    /**
     * 视频背景图片地址
     */
    private String backgroundImg;
    /**
     * 展示的视频地址
     */
    private String video;

    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 截止时间
     */
    private Long deadline;

    /**
     * 添加时的时间
     */
    private Long addTime;
    /**
     * 发布状态
     * 0、发布 1、待发布
     */
    private Integer releaseStatus;
    /**
     * 状态
     * 0、正常 1、无效
     */
    private Integer status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
