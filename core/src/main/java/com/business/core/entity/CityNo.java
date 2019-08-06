package com.business.core.entity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Sin on 2016/2/26.
 *
 * 气象局 城市对应编码：
 * CityNo
 *
 * 国家 -> 省市 -> 区域 -> 地区
 */
@Document(collection = "CityNo")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class CityNo {

    ///
    /// 地区信息

    /**
     * 地区编号
     */
    private Integer areaId;
    /**
     * 地区名 en
     */
    private String areaEn;
    /**
     * 地区名 ch
     */
    private String areaCh;

    ///
    /// 国家

    /**
     * 国家名 en
     */
    private String countryEn;
    /**
     * 国家名 ch
     */
    private String countryCh;

    ///
    /// 省市

    /**
     * 省市名 ch
     */
    private String provCh;
    /**
     * 省市名 en
     */
    private String provEn;

    ///
    /// 区域信息

    /**
     * 区域名 en
     */
    private String districtEn;
    /**
     * 区域名 ch
     */
    private String districtCh;


    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaEn() {
        return areaEn;
    }

    public void setAreaEn(String areaEn) {
        this.areaEn = areaEn;
    }

    public String getAreaCh() {
        return areaCh;
    }

    public void setAreaCh(String areaCh) {
        this.areaCh = areaCh;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCountryCh() {
        return countryCh;
    }

    public void setCountryCh(String countryCh) {
        this.countryCh = countryCh;
    }

    public String getProvCh() {
        return provCh;
    }

    public void setProvCh(String provCh) {
        this.provCh = provCh;
    }

    public String getProvEn() {
        return provEn;
    }

    public void setProvEn(String provEn) {
        this.provEn = provEn;
    }

    public String getDistrictEn() {
        return districtEn;
    }

    public void setDistrictEn(String districtEn) {
        this.districtEn = districtEn;
    }

    public String getDistrictCh() {
        return districtCh;
    }

    public void setDistrictCh(String districtCh) {
        this.districtCh = districtCh;
    }
}
