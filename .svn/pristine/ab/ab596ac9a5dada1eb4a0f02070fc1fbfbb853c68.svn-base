package com.business.core.entity.activityOther;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.UserRealInfo;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Sin on 2016/2/17.
 */
@Document(collection = "ActivityJiaFeiMao")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
@Deprecated
public class ActivityJiaFeiMao extends IncIdEntity<Integer> {

    public static final String GROUP_PUBLIC = "公开优惠组";

    public static final String GROUP_SAN_REN = "家庭3人优惠组";

    /**
     * 组：公开组 情侣 家庭2人族 家庭3人族 家庭4人族 优惠组：公开组优惠 家庭3人组优惠
     */
    private String group;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 需要金额
     */
    private Double needMoney;
    /**
     * 成员
     */
    private List<ActivityJiaFeiMaoMember> jiaFeiMaoMembers;
    /**
     * 移动电话名称
     */
    private String mobileName;
    /**
     * 移动电话
     */
    private String mobilePhone;
    /**
     * 紧急联系人
     */
    private String emergencyPhone;
    /**
     * 紧急联系人 名称
     */
    private String emergencyName;

    ///
    /// 第三方支付 交易信息

    /**
     * 支付 的用户身份
     */
    private String openid;
    /**
     * 交易单号
     */
    private String tradeNo;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 交易单号 （第三方）
     */
    private String tradeNoPlatform;
    /**
     * 交易金额
     */
    private Double tradeMoney;
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
     * 1、微信公众授权支付 2、微信支付 3、
     */
    private Integer tradeType;
    /**
     * 交易状态
     * 0、成功 1、待支付 2、支付失败
     */
    private Integer tradeStatus;

    ///
    /// 其他信息

    /**
     * 添加时间
     */
    private Long addTime;


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getNeedMoney() {
        return needMoney;
    }

    public void setNeedMoney(Double needMoney) {
        this.needMoney = needMoney;
    }

    public List<ActivityJiaFeiMaoMember> getJiaFeiMaoMembers() {
        return jiaFeiMaoMembers;
    }

    public void setJiaFeiMaoMembers(List<ActivityJiaFeiMaoMember> jiaFeiMaoMembers) {
        this.jiaFeiMaoMembers = jiaFeiMaoMembers;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTradeNoPlatform() {
        return tradeNoPlatform;
    }

    public void setTradeNoPlatform(String tradeNoPlatform) {
        this.tradeNoPlatform = tradeNoPlatform;
    }

    public Double getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(Double tradeMoney) {
        this.tradeMoney = tradeMoney;
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

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
