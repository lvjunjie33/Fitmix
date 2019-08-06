package com.business.core.entity.version;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/5/25.
 * 版本信息
 */
@Document(collection = "Version")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Version extends IncIdEntity<Long> {

    /**
     * 类型/android
     */
    public static final Integer TYPE_ANDROID = 1;
    /**
     * 类型/IOS
     */
    public static final Integer TYPE_IOS = 2;
    /**
     * 未上架
     */
    public static final Integer STATE_1 = 1;
    /**
     * 上架
     */
    public static final Integer STATE_2 = 2;


    /**
     * 类型 1.Android 2.IOS
     */
    private Integer type;
    /**
     * 版本号
     */
    private Integer versionCode;
    /**
     * 上架状态 1.未上架 2.已上架
     */
    private Integer state;
    /**
     * 应用地址
     */
    private String url;
    /**
     * 添加时间
     */
    private Long addTime;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
