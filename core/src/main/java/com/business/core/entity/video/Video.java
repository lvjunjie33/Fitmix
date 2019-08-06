package com.business.core.entity.video;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zhangtao on 2016/4/25.
 */
@Document(collection = "Video")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Video extends IncIdEntity<Integer> {

    /**
     * status/正常
     */
    public static final Integer STATUS_NORMAL = 0;
    /**
     * status/无效
     */
    public static final Integer STATUS_INVALID = 1;
    /**
     * 普通视频
     */
    public static final Integer TYPE_NORMAL = 0;
    /**
     * 360 全景视频
     */
    public static final Integer TYPE_PANORAMA = 1;

    /**
     * 封面名称
     */
    private String posterName;

    /**
     * 封面链接
     */
    private String posterUrl;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 视频链接
     */
    private String videoUrl;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频内容
     */
    private String content;

    /**
     * 视频时长
     */
    private Long trackLength;

    /**
     * 创建时间
     */
    private Long addTime;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 分享次数
     */
    private Integer shareCount;

    /**
     * 视频类型 0:普通视频 1: 360 全景视频
     */
    private Integer type;


    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(Long trackLength) {
        this.trackLength = trackLength;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
