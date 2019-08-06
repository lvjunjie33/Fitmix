package com.business.core.entity.wx;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sin on 2015/10/13.
 *
 * 微信公众号 关注用户信息
 */
@Document(collection = "WXUser")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class WXUser extends IncIdEntity<Integer> {

    /**
     * 微信步数设置限制
     * key = unionId
     * val = {lastTime, sets}
     */

    /**
     * 是否开启订阅
     */
    private int subscribe;
    /**
     * openid  微信公众号关注的openId
     */
    private String openid;
    /**
     * 名称
     */
    private String nickname;
    /**
     * 性别
     */
    private int sex;
    /**
     * 城市
     */
    private String city;
    /**
     * 省市
     */
    private String province;
    /**
     * 国家
     */
    private String country;
    /**
     * 头像地址
     */
    private String headimgurl;
    /**
     * unionid
     */
    private String unionid;
    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;
    /**
     * 用户所在的分组ID
     */
    private String groupid;
    /**
     */
    private Long addTime;

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}
