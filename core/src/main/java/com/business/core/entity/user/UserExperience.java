package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.TaoBaoIp;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/9/7.
 * 体验用户
 */
@Document(collection = "UserExperience")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserExperience extends IncIdEntity<Integer> {

    ///
    /// 体验用户

    /**
     * ip
     */
    private TaoBaoIp taoBaoIp;
    /**
     * 体验用户 udid
     */
    private String udid;
    /**
     * 体验次数
     */
    private Integer count;


    ///
    ///终端信息, 用户活跃 ， 最后登录信息

    /**
     * 终端信息
     */
    private String sdk;
    /**
     * 手机型号
     */
    private String mobileType;
    /**
     * 运营商,  (中国联通, 中国电信, 中国移动 等.)
     */
    private String operators;
    /**
     * 渠道信息.（登录渠道） 1 官网  2 应用宝 3 appStore 等. 根据发包情况
     */
    private String channel;
    /**
     * 最后登录时间
     */
    private Long experienceTime;
    /**
     * 用户版本
     */
    private String version;

    ///
    /// 第三方信息

    /**
     * 友盟 推送 id
     */
    private String deviceToken;

    ///
    /// 其它信息

    /**
     * 添加时间
     */
    private Long addTime;


    public TaoBaoIp getTaoBaoIp() {
        return taoBaoIp;
    }

    public void setTaoBaoIp(TaoBaoIp taoBaoIp) {
        this.taoBaoIp = taoBaoIp;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(Long experienceTime) {
        this.experienceTime = experienceTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
