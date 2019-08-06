package com.business.core.entity.channel;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/5/10.
 */
@Document(collection = "ChannelAppDownload")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class ChannelAppDownload extends IncIdEntity<Long> {

    /**
     * 渠道ID
     */
    private Integer channelId;
    /**
     * 下载者ip
     */
    private String ip;
    /**
     * 类型：android , ios
     */
    private String type;
    /**
     * 下载状态 0:开始下载
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Long addTime;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
