package com.business.core.entity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/10/13.
 * TODO 和 redis 缓存一起改 合并 SysParam
 */
@Document(collection = "SchedulerValue")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class SchedulerValue {

    /**
     * 微信公众平台 access_token
     */
    private String wxMqAccessToken;
    /**
     * 最后一次修改时间
     */
    private Long lastModifyTime;
    /**
     * 上一次的accessToken
     */
    private String oldAccessToken;
    /**
     * 微信 JsapiTicket
     */
    private String jsapiTicket;
    /**
     * redis 中缓存的AccessToken
     */
    @Transient
    private String redisAccessToken;

    public String getWxMqAccessToken() {
        return wxMqAccessToken;
    }

    public void setWxMqAccessToken(String wxMqAccessToken) {
        this.wxMqAccessToken = wxMqAccessToken;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getOldAccessToken() {
        return oldAccessToken;
    }

    public void setOldAccessToken(String oldAccessToken) {
        this.oldAccessToken = oldAccessToken;
    }

    public String getRedisAccessToken() {
        return redisAccessToken;
    }

    public void setRedisAccessToken(String redisAccessToken) {
        this.redisAccessToken = redisAccessToken;
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }
}
