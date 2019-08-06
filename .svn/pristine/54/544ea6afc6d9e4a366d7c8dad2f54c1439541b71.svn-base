package com.business.core.entity.channel;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/5/9.
 *
 * 渠道APP信息
 * <p>
 *     渠道下绑定的APP信息
 * </p>
 *
 */
@Document(collection = "ChannelApp")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class ChannelApp extends IncIdEntity<Long> {

    /**
     * status/正常
     */
    public static final Integer STATUS_NORMAL = 0;
    /**
     * status/无效
     */
    public static final Integer STATUS_INVALID = 1;

    /**
     * 渠道字典值
     */
    private Integer channelId;
    /**
     * 安卓APK 名称 todo
     */
    private String androidApkName;
    /**
     * 安卓APK下载地址
      */
    private String androidApkUrl;
    /**
     * IOS APK 下载地址
     */
    private String iosUrl;

    /**
     * 0有效，1无效
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Long addTime;

    /**
     * 渠道名称
     */
    @Transient
    private String channelName;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getAndroidApkName() {
        return androidApkName;
    }

    public void setAndroidApkName(String androidApkName) {
        this.androidApkName = androidApkName;
    }

    public String getAndroidApkUrl() {
        return androidApkUrl;
    }

    public void setAndroidApkUrl(String androidApkUrl) {
        this.androidApkUrl = androidApkUrl;
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
