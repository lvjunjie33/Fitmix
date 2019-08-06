package com.business.core.entity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 客户端安装包配置
 * Created by yunai on 13-11-27.
 */
@Document(collection = "InstallPackageConfig")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class InstallPackageConfig extends IncIdEntity<Integer> {

    /**
     * 类型 - 用户端 - IOS
     */
    public static final Integer TYPE_USER_IOS = 1;
    /**
     * 类型 - 用户端 - 安卓
     */
    public static final Integer TYPE_USER_ANDROID = 2;

    /**
     * 下载地址
     */
    private String uri;
    /**
     * 类型.  该字段冗余：
     */
    private Integer type;
    /**
     * 版本. 该字段冗余：
     */
    private Integer version;
    /**
     * 客户端版本
     */
    private Integer clientVersion;
    /**
     * 是否为最新版本
     * 每个类型都会有个最新版本。有该标识的，约定为该{@link #type}下的最新的版本，
     * 当老版本客户端请求过来时，会按照该标识下的版本为参考，并且返回的也是该版本的下载的地址
     * 该字段冗余：<br />
     */
    private Boolean newest;
    /**
     * 升级说明
     */
    private String upgradeNote;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(Integer clientVersion) {
        this.clientVersion = clientVersion;
    }

    public Boolean getNewest() {
        return newest;
    }

    public void setNewest(Boolean newest) {
        this.newest = newest;
    }

    public String getUpgradeNote() {
        return upgradeNote;
    }

    public void setUpgradeNote(String upgradeNote) {
        this.upgradeNote = upgradeNote;
    }
}