package com.business.core.entity.bbs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/9/9.
 */
@Document(collection = "Article")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Article extends IncIdEntity<Integer> {

    /**
     * banner 状态 - 发布
     */
    public static final int STATUS_RELEASE = 0;
    /**
     * banner 状态 - 未发布
     */
    public static final int STATUS_NOT_RELEASE = 1;

    /**
     * 作者Id
      */
    private Integer authorId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 简介
     */
    private String desc;
    /**
     * 角标
     */
    private String cornerMark;
    /**
     * 类别
     */
    private String category;
    /**
     * 浏览量
     */
    private Integer viewCount;
    /**
     * 封面
     */
    private String coverUrl;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Long addTime;

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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
}
