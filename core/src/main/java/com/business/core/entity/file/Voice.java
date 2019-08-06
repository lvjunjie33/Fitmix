package com.business.core.entity.file;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2017/9/14.
 * 语音包清单
 */
@Document(collection = "Voice")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Voice extends IncIdEntity<Integer> {
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String des;
    /**
     * 文件icon
     */
    private String iconLink;
    /**
     * 封面图
     */
    private String coverLink;
    /**
     * 标签
     */
    private String tags;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 标签
     */
    @Transient
    private String[] tagList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getCoverLink() {
        return coverLink;
    }

    public void setCoverLink(String coverLink) {
        this.coverLink = coverLink;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String[] getTagList() {
        return tagList;
    }

    public void setTagList(String[] tagList) {
        this.tagList = tagList;
    }
}
