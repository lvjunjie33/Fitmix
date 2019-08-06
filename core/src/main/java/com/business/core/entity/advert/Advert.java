package com.business.core.entity.advert;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2016/5/16.
 *
 * 广告
 */
@Document(collection = "Advert")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Advert extends IncIdEntity<Long> {

    /**
     * 广告标题(用于后台展示)
     */
    private String title;
    /**
     * 广告指向的地址
     */
    private String toUrl;
    /**
     * 广告图片
     */
    private String advertImg;
    /**
     * 广告说明
     */
    private String des;
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
     * TODO 具体干啥的还没确定
     */
    private Integer operationType;
    /**
     * 广告位置
     * (默认:启动页广告=1)
     */
    private Integer position;
    /**
     * 添加时间
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

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }

    public String getAdvertImg() {
        return advertImg;
    }

    public void setAdvertImg(String advertImg) {
        this.advertImg = advertImg;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
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
