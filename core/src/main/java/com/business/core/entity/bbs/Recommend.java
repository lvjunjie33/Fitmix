package com.business.core.entity.bbs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/9/13.
 */
@Document(collection = "Recommend")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Recommend extends IncIdEntity<Integer> {
    /**
     * 文章类型
     */
    public static final Integer TYPE_ARTICLE = 1;
    /**
     * banner 类型 - url 网页
     */
    public static final int TYPE_URL = 2;
    /**
     * banner 状态 - 发布
     */
    public static final int STATUS_RELEASE = 0;
    /**
     * banner 状态 - 未发布
     */
    public static final int STATUS_NOT_RELEASE = 1;

    /**
     * 类型 1：文章类型
     */
    private Integer type;
    /**
     * type 值
     */
    private String typeValue;
    /**
     * 排序ID
     */
    private Integer sort;
    /**
     * banner 状态：0、发布 1、未发布
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 背景图片
     */
    private String backImage;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String desc;
    /**
     * 角标文字
     */
    private String cornerMark;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCornerMark() {
        return cornerMark;
    }

    public void setCornerMark(String cornerMark) {
        this.cornerMark = cornerMark;
    }
}
