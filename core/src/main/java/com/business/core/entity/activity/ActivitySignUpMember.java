package com.business.core.entity.activity;

/**
 * Created by edward on 2016/4/16.
 * 活动中，选择参与活动的组类型填写的成员信息
 */
public class ActivitySignUpMember {

    /**
     * 名称
     */
    private String name;
    /**
     * 组中的名称
     */
    private String groupMemberName;
    /**
     * 成员手机号码
     */
    private String mobile;
    /**
     * 备注
     */
    private String memo;
    /**
     * 性别
     */
    private String gender;
    /**
     * 血型
     * A、B、AB、O
     */
    private String bloodType;
    /**
     * 衣服尺码
     * S、M、L、XL、2XL、3XL
     */
    private String clothesSize;
    /**
     * 用户身份证
     */
    private String idCard;
    /**
     * 用户身份证
     */
    private String idCardType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClothesSize() {
        return clothesSize;
    }

    public void setClothesSize(String clothesSize) {
        this.clothesSize = clothesSize;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getGroupMemberName() {
        return groupMemberName;
    }

    public void setGroupMemberName(String groupMemberName) {
        this.groupMemberName = groupMemberName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
