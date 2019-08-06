package com.business.core.entity.payOrder;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Sin on 2016/4/13.
 */
@Document(collection = "PayOrder")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class PayOrder extends IncIdEntity<Long> {

    ///
    /// 支付平台

    /**
     * 微信支付
     */
    public static final int PAY_PLATFORM_WW_CHAT = 1;
    /**
     * 阿里支付（支付宝）
     */
    public static final int PAY_PLATFORM_ALI_PAY = 2;

    ///
    /// 支付方式

    /**
     * 微信支付 - 公众号授权支付
     */
    public static final int PAYMENT_METHOD_PUBLIC_NUMBER = 1;
    /**
     * 阿里支付 - 移动 h5 支付
     */
    public static final int PAYMENT_METHOD_ALI_WEB_H5 = 2;

    ///
    /// 支付状态

    /**
     * 支付状态 - 等待支付
     */
    public static final int PAY_STATE_CREATE = 0;
    /**
     * 支付状态 - 等待支付
     */
    public static final int PAY_STATE_WAIT = 1;
    /**
     * 支付状态 - 支付成功
     */
    public static final int PAY_STATE_SUCCESS = 2;
    /**
     * 支付状态 - 支付异常
     */
    public static final int PAY_STATE_ERROR = 3;

    ///
    /// 订单信息

    /**
     * 支付订单
     */
    private String orderNo;
    /**
     * 支付平台 订单号
     */
    private String platformNo;
    /**
     * 支付名称
     */
    private String payName;
    /**
     * 支付金额
     */
    private Double payMoney;

    ///
    /// 第三方信息

    /**
     * 支付品台
     */
    private Integer payPlatform;
    /**
     * 支付方式
     */
    private Integer paymentMethod;


    /// 支付信息

    /**
     * 创建时间
     */
    private Long addTime;
    /**
     * 下单时间
     */
    private Long placeOrderTime;
    /**
     * 支付成功时间
     */
    private Long successTime;
    /**
     * 支付状态：0、创建订单 1、等待支付 2、支付成功 3、支付异常
     */
    private Integer payState;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPlatformNo() {
        return platformNo;
    }

    public void setPlatformNo(String platformNo) {
        this.platformNo = platformNo;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayPlatform() {
        return payPlatform;
    }

    public void setPayPlatform(Integer payPlatform) {
        this.payPlatform = payPlatform;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getPlaceOrderTime() {
        return placeOrderTime;
    }

    public void setPlaceOrderTime(Long placeOrderTime) {
        this.placeOrderTime = placeOrderTime;
    }

    public Long getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Long successTime) {
        this.successTime = successTime;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }
}
