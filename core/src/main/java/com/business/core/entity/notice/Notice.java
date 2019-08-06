package com.business.core.entity.notice;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/12/9.
 * 通知
 */
@Document(collection = "Notice")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Notice extends IncIdEntity<Long> {

    /**
     * 等待
     */
    public static final String STATUS_WAIT = "wait";
    /**
     * 成功
     */
    public static final String STATUS_SUCCESS = "success";
    /**
     * 爱思
      */
    public static final String CHANNEL_AI_SI = "爱思";
    /**
     * 苹果助手
     */
    @Deprecated
    public static final String CHANNEL_PING_GUO_ZHU_SHOU = "苹果助手";
    /**
     * 点入移动
     */
    public static final String CHANNEL_PING_GUO_DIAN_RU = "点入移动";
    /**
     * 聚友在线
     */
    @Deprecated
    public static final String CHANNEL_PING_GUO_JU_YOU = "聚友在线";
    /**
     * 点入移动
     */
    public static final String CHANNEL_UNKNOWN = "未知";

    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 设备 IDFA
     */
    private String idfa;
    /**
     * 应用编号 appstore
     */
    @Deprecated
    private String appid;
    /**
     * 哪个渠道通知：爱思 苹果助手
     */
    private String channel;
    /**
     * 状态：wait success
     */
    private String status;
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

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
