package com.business.core.entity.wd;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.mix.MixBanner;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by edward on 2016/7/19.
 * 万德 赛道 信息
 */
@Document(collection = "Speedway")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Speedway extends IncIdEntity<Integer> {

    /**
     * 赛道主题
     */
    private String title;
    /**
     * 赛道背景图片
     */
    private String backgroundImage;
    /**
     * 城市 名称
     */
    private String city;
    /**
     * 万德赛道编号
     */
    private String wayId;
    /**
     * 描述
     */
    private String des;
    /**
     * 赛道banner 列表
     */
    private List<Integer> bannerIds;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 数据状态(0可用，1删除) {@link com.business.core.constants.Constants#STATE_INVALID}
     */
    private Integer status;
    /**
     * 发布状态 {@link com.business.core.constants.Constants#RELEASE_STATUS_WAIT_RELEASE}
     */
    private Integer releaseStatus;

    ///
    ///======================view===================================
    /**
     * 赛道banner 列表
     */
    @Transient
    private List<MixBanner> mixBanners;

    public List<Integer> getBannerIds() {
        return bannerIds;
    }

    public void setBannerIds(List<Integer> bannerIds) {
        this.bannerIds = bannerIds;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWayId() {
        return wayId;
    }

    public void setWayId(String wayId) {
        this.wayId = wayId;
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

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

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

    public List<MixBanner> getMixBanners() {
        return mixBanners;
    }

    public void setMixBanners(List<MixBanner> mixBanners) {
        this.mixBanners = mixBanners;
    }
}
