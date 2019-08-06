package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/11/18.
 * 用户歌单
 */
@Document(collection = "UserMusic")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserMusic extends IncIdEntity<Long>{

    /**
     * 用户编号  ..//冗余
     */
    private Integer uid;
    /**
     * 分组 {@link UserMusicGroup#id}
     */
    private Long groupId;
    /**
     * 歌曲名称
     */
    private String name;
    /**
     * 歌曲作者
     */
    private String author;
    /**
     * 状态 0、有效 1、无效
     * {@link UserMusicGroup#status}
     */
    private Integer status;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
