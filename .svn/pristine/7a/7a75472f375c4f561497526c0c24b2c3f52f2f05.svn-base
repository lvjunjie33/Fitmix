package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Sin on 2016/2/18.
 */
@Document(collection = "UserAddress")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserAddress extends IncIdEntity<Integer> {

    /**
     * 状态/有效
     */
    public static final int STATUS_VALIDITY = 0;
    /**
     * 状态/无效
     */
    public static final int STATUS_INVALID = 1;
    /**
     * 默认地址
     */
    public static final boolean IS_DEFAULT = true;
    /**
     * 非默认地址
     */
    public static final boolean IS_NOT_DEFAULT = false;

    /**
     * 用户编号 {@link User#id}
     */
    private Integer uid;
    /**
     * 国家
     */
    private String country;
    /**
     * 区域
     */
    private String region;
    /**
     * 省市
     */
    private String city;
    /**
     * 详细地址
     */
    private String detail;
    /**
     * 状态
     *  0、有效 1、无效
     */
    private Integer status;
    /**
     * 是否 默认地址
     */
    private boolean isDefault;
    /**
     * 添加时间
     */
    private Long addTime;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
