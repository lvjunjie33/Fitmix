package com.business.core.entity.channel;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/5/9.
 *
 * 渠道APP 统计分析
 *
 */
@Document(collection = "ChannelAppAnalysis")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class ChannelAppAnalysis {
    /**
     * 统计分析编号
     */
    @Id
    private ObjectId id;

    /**
     * 页面统计
     */
    public static final Integer CLICK_TYPE = 0;
    /**
     * 下载统计
     */
    public static final Integer DOWNLOAD_TYPE = 1;

    /**
     * UV统计规则为每个IP在4小时内重复点击只计算一次
     */
    private Long UV;
    /**
     * 有效PV规则为点击页面并成功打开
     */
    private Long PV;
    /**
     * IOS 点击下载数
     */
    private Long iosDownloadCount;
    /**
     * Android 点击下载数
     */
    private Long androidDownloadCount;
    /**
     * 渠道编号
     */
    private Integer channelId;
    /**
     * 渠道名称
     */
    @Transient
    private String channelName;
    /**
     * 统计日期
     */
    private Long date;
    /**
     * 统计类型：0：点击统计，1：下载统计
     */
    private Integer type;
    /**
     * 添加时间
     */
    private Long addTime;

    public Long getUV() {
        return UV;
    }

    public void setUV(Long UV) {
        this.UV = UV;
    }

    public Long getPV() {
        return PV;
    }

    public void setPV(Long PV) {
        this.PV = PV;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getIosDownloadCount() {
        return iosDownloadCount;
    }

    public void setIosDownloadCount(Long iosDownloadCount) {
        this.iosDownloadCount = iosDownloadCount;
    }

    public Long getAndroidDownloadCount() {
        return androidDownloadCount;
    }

    public void setAndroidDownloadCount(Long androidDownloadCount) {
        this.androidDownloadCount = androidDownloadCount;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}

