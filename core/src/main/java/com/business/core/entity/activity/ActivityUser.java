package com.business.core.entity.activity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRealInfo;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Sin on 2016/2/16.
 *
 * app 活动 报名用户信息
 */
@Document(collection = "ActivityUser")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
@Deprecated
public class ActivityUser extends IncIdEntity<Long> {

    /**
     * 支付状态/待支付
     */
    public static final int TRADE_STATUS_WAIT_APY = 0;
    /**
     * 支付状态/错误
     */
    public static final int TRADE_STATUS_ERROR = 1;
    /**
     * 支付状态/成功
     */
    public static final int TRADE_STATUS_SUCCESS = 2;

    /**
     * 支付平台/微信支付
     */
    public static final Integer TRADE_TYPE_WEI_CHAR_PAY = 1;
    /**
     * 支付平台/支付宝
     */
    public static final Integer TRADE_TYPE_ALI_PAY = 2;


    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 活动编号 {@link ActivityUser#id}
     */
    private Integer activityId;
    /**
     * 需要金额
     */
    private Double needMoney;

    ///
    /// 第三方支付 交易信息

    /**
     * 交易单号
     */
    private String tradeNo;
    /**
     * 交易金额
     */
    private Double tradeMoney;
    /**
     * 交易单号 （第三方）
     */
    private String tradeNoPlatform;
    /**
     * 交易时间
     */
    private Long tradeTime;
    /**
     * 交易成功时间
     */
    private Long tradeSuccessTime;
    /**
     * 交易类型
     * 1、微信 2、支付宝
     */
    private Integer tradePlatform;
    /**
     * 交易详细类型
     * 1、微信公众授权支付  2、支付宝网页支付
     */
    private Integer tradeType;
    /**
     * 交易状态
     * 0、待支付 1、支付失败 2、成功
     */
    private Integer tradeStatus;

    ///
    /// 用户真实信息

    /**
     * 用户真实信息
     */
    private UserRealInfo userRealInfo;


    ///
    /// 其他信息

    /**
     * 添加时间
     */
    private Long addTime;

    ///
    /// 非存储字段

    /**
     * 用户信息
     */
    @Transient
    private User user;

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

    public Double getNeedMoney() {
        return needMoney;
    }

    public void setNeedMoney(Double needMoney) {
        this.needMoney = needMoney;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Double getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(Double tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getTradeNoPlatform() {
        return tradeNoPlatform;
    }

    public void setTradeNoPlatform(String tradeNoPlatform) {
        this.tradeNoPlatform = tradeNoPlatform;
    }

    public Long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Long getTradeSuccessTime() {
        return tradeSuccessTime;
    }

    public void setTradeSuccessTime(Long tradeSuccessTime) {
        this.tradeSuccessTime = tradeSuccessTime;
    }

    public Integer getTradePlatform() {
        return tradePlatform;
    }

    public void setTradePlatform(Integer tradePlatform) {
        this.tradePlatform = tradePlatform;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public UserRealInfo getUserRealInfo() {
        return userRealInfo;
    }

    public void setUserRealInfo(UserRealInfo userRealInfo) {
        this.userRealInfo = userRealInfo;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
