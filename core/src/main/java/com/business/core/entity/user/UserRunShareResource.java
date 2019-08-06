package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Sin on 2016/1/14.
 */
@Document(collection = "UserRunShareResource")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserRunShareResource extends IncIdEntity<Integer> {

    /**
     * 内容
     */
    public static final int TYPE_CONTENT = 1;
    /**
     * 图片
     */
    public static final int TYPE_IMAGE = 2;
    /**
     * 正常
     */
    public static final int STATUS_NORMAL = 0;
    /**
     * 无效
     */
    public static final int STATUS_INVALID = 1;


    /**
     * 内容信息
     */
    private String content;
    /**
     * 图片
     */
    private String imageUrl;
    /**
     * 类型  1、内容 2、图片
     */
    public Integer type;
    /**
     * 状态 0、有效 1、无效
     */
    public Integer status;
    /**
     * 添加时间
     */
    public Long addTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
