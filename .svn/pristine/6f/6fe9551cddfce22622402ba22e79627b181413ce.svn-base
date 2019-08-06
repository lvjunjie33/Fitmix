package com.business.core.entity.stat;

import org.bson.types.ObjectId;
import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

/**
 * Created by sin on 2015/8/24.
 */
@Document(collection = "Stat_UserRegisterChannelStat")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class UserRegisterChannelStat {

    /**
     * 编号
     */
    private String id;
    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 本渠道注册用户
     */
    private Integer registerCount;
    /**
     * 登陆次数（激活次数， 活跃）
     */
    private Integer loginCount;
    /**
     * 统计渠道号
     */
    private String channel;
    /**
     * 小时
     */
    private Integer hour;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 用户注册类型统计 编号
     */
    private List<ObjectId> userRegisterTypeStatId;

    ///
    /// 非存储字段

    /**
     * 渠道名称
     */
    @Transient
    private String channelName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public List<ObjectId> getUserRegisterTypeStatId() {
        return userRegisterTypeStatId;
    }

    public void setUserRegisterTypeStatId(List<ObjectId> userRegisterTypeStatId) {
        this.userRegisterTypeStatId = userRegisterTypeStatId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
