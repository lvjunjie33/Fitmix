package com.business.core.entity.stat;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/8/24.
 */
@Document(collection = "Stat_UserRegisterTypeStat")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class UserRegisterTypeStat {
    /**
     * 编号
     */
    private ObjectId id;
    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 统计渠道号
     */
    private String channel;
    /**
     * 注册方式 (1、app注册 2、QQ 注册 3、微信注册  4、微博注册 )
     */
    private Integer registerType;
    /**
     * 添加时间
     */
    private Long addTime;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
