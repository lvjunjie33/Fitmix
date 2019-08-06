package com.business.core.entity.channel;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/5/9.
 *
 * 渠道统计信息
 *
 */
@Document(collection = "ChannelAppStatistics")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class ChannelAppStatistics extends IncIdEntity<Long> {

    /**
     * openStatus/未成功打开
     */
    public static final Integer OPENSTATUS_FAIL = 0;
    /**
     * openStatus/成功打开
     */
    public static final Integer OPENSTATUS_SUCCESS = 1;


    /**
     * 渠道字典值
     */
    private Integer channelId;
    /**
     * 请求IP
     */
    private String ip;
    /**
     * 是否成功打开 0:未成功打开 1:成功打开
      */
    private Integer openStatus;
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

    public Integer getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(Integer openStatus) {
        this.openStatus = openStatus;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
