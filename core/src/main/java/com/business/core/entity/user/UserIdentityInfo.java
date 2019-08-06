package com.business.core.entity.user;

/**
 * Created by fenxio on 2016/8/26.
 */
public class UserIdentityInfo {

    /**
     * 姓名
     */
    private String realName;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 身份证照片
     */
    private String idCardImg;
    /**
     * 主播信息
     */
    private String radio;
    /**
     * 主播经历
     */
    private String radioUploadDesc;
    /**
     * 主播经历截图
     */
    private String radioUploadImg;
    /**
     * 粉丝
     */
    private String follower;
    /**
     * 截图
     */
    private String followerImg;
    /**
     * 验证状态
     */
    private Integer checkStatus;

    /**
     * 审核信息
     */
    private String checkMessage;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardImg() {
        return idCardImg;
    }

    public void setIdCardImg(String idCardImg) {
        this.idCardImg = idCardImg;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getRadioUploadDesc() {
        return radioUploadDesc;
    }

    public void setRadioUploadDesc(String radioUploadDesc) {
        this.radioUploadDesc = radioUploadDesc;
    }

    public String getRadioUploadImg() {
        return radioUploadImg;
    }

    public void setRadioUploadImg(String radioUploadImg) {
        this.radioUploadImg = radioUploadImg;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowerImg() {
        return followerImg;
    }

    public void setFollowerImg(String followerImg) {
        this.followerImg = followerImg;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckMessage() {
        return checkMessage;
    }

    public void setCheckMessage(String checkMessage) {
        this.checkMessage = checkMessage;
    }
}
