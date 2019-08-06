package com.business.core.entity.mix;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by sin on 2015/8/4 0004.
 * mix 歌曲 作者信息
 */
@Document(collection = "MixAuthor")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class MixAuthor extends IncIdEntity<Integer> {

    /**
     * 名称
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 照片
     */
    private List<String> photos;
    /**
     * 国家/城市
     */
    private String international;
    /**
     * 电话
     */
    private String phone;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 网站
     */
    private String webUrl;
    /**
     * 微信
     */
    private String weiXin;
    /**
     * 微博
     */
    private String weiBo;
    /**
     * 介绍，简介
     */
    private String introduce;
    /**
     * 介绍，详细
     */
    private String introduceDetail;
    /**
     * 动态
     */
    private String dynamic;
    /**
     * 备注
     */
    private String memo;
    /**
     * 用户数量（关注）
     */
    private Integer userCount;
    /**
     * 添加时间
     */
    private Long addTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getInternational() {
        return international;
    }

    public void setInternational(String international) {
        this.international = international;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public String getWeiBo() {
        return weiBo;
    }

    public void setWeiBo(String weiBo) {
        this.weiBo = weiBo;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroduceDetail() {
        return introduceDetail;
    }

    public void setIntroduceDetail(String introduceDetail) {
        this.introduceDetail = introduceDetail;
    }

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
