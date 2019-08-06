package com.business.core.entity.bbs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/9/19.
 */
@Document(collection = "Advertisement")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Advertisement extends IncIdEntity<Integer> {

    /**
     * 状态 - 发布
     */
    public static final int STATUS_RELEASE = 0;
    /**
     * 状态 - 未发布
     */
    public static final int STATUS_NOT_RELEASE = 1;
    /**
     * 首页模块
     */
    public static final int MODULE_BBS = 0;
    /**
     * 文章模块
     */
    public static final int MODULE_ARTICLE = 1;
    /**
     * 图片类型
     */
    public static final int TYPE_IMAGE = 1;

    /**
     * 名称
     */
    private String name;
    /**
     * 所属模块
     */
    private Integer module;
    /**
     * 类型 1：图片类型
     */
    private Integer type;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 跳转链接
     */
    private String url;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 添加时间
     */
    private Long addTime;

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
