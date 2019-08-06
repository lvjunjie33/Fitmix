package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/9/9.
 */
@Document(collection = "UserSkipRopeShare")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserSkipRopeShare extends IncIdEntity<Integer> {

    /**
     * 用户编号
     */
    private Integer uid;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 分享图片地址
     */
    private String imgUrl;

    /**
     * 浏览次数
     */
    private Integer browseCount;

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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
}
