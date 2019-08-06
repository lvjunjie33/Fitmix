package com.business.core.entity.activity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by edward on 2016/4/16.
 * 用户参与活动的报名信息
 */
@Document(collection = "ActivitySignUp")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class ActivitySignUp extends IncIdEntity<Integer> {

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
     * 活动编号
     * {@link com.business.core.entity.activity.Activity#id}
     */
    private Integer activityId;
    /**
     * 用户编号(可以为空)
     * {@link com.business.core.entity.user.User#id}
     */
    private Integer userId;
    /**
     * 组：公开组 情侣 家庭2人族 家庭3人族 家庭4人族 优惠组：公开组优惠 家庭3人组优惠
     */
    private String groupName;
    /**
     * 组编号
     * {@link com.business.core.entity.activity.ActivityGroup#id}
     */
    private String groupId;
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
    private List<ActivitySignUpMember> activitySignUpMembers;
    /**
     * 报名人数
     */
    private Integer activitySignUpNumber;
    /**
     * 联系人姓名
     */
    private String mobileName;
    /**
     * 联系人手机号码
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
    /// 团体、组织、群体、机构名称(例如:跑团名称)

    /**
     * 团体名称
     */
    private String userTeamName;
    /**
     * 团体口号
     */
    private String userTeamSlogan;

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
     * 交易类型
     * 1、微信 2、支付宝
     */
    private Integer tradePlatform;
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

    /* ======================view 字段============================== */

    /**
     * 该手机号码参与所有活动次数
     */
    private Integer count;

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

    public Integer getTradePlatform() {
        return tradePlatform;
    }

    public void setTradePlatform(Integer tradePlatform) {
        this.tradePlatform = tradePlatform;
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

    public List<ActivitySignUpMember> getActivitySignUpMembers() {
        return activitySignUpMembers;
    }

    public void setActivitySignUpMembers(List<ActivitySignUpMember> activitySignUpMembers) {
        this.activitySignUpMembers = activitySignUpMembers;
    }
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getActivitySignUpNumber() {
        return activitySignUpNumber;
    }

    public void setActivitySignUpNumber(Integer activitySignUpNumber) {
        this.activitySignUpNumber = activitySignUpNumber;
    }

    public String getUserTeamName() {
        return userTeamName;
    }

    public void setUserTeamName(String userTeamName) {
        this.userTeamName = userTeamName;
    }

    public String getUserTeamSlogan() {
        return userTeamSlogan;
    }

    public void setUserTeamSlogan(String userTeamSlogan) {
        this.userTeamSlogan = userTeamSlogan;
    }
}
