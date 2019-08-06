package com.business.core.entity.joinActivity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/7/11.
 */
@Document(collection = "JoinActivityViewLog")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class JoinActivityViewLog extends IncIdEntity<Long> {

    /**
     * 用户ID
     */
    private Integer uid;
    /**
     * 活动ID
     */
    private Integer activityId;
    /**
     * 外部活动ID
     */
    private String outActivityId;
    /**
     * 渠道
     */
    private Integer channel;
    /**
     * 访问IP
     */
    private String ip;
    /**
     * 访问时间
     */
    private Long addTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getOutActivityId() {
        return outActivityId;
    }

    public void setOutActivityId(String outActivityId) {
        this.outActivityId = outActivityId;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
