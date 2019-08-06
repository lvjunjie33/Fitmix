package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/11/18.
 * 歌曲分组
 */
@Document(collection = "UserMusicGroup")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserMusicGroup extends IncIdEntity<Long> {

    /**
     * 有效
     */
    public static final Integer STATUS_SONG_NORMAL = 0;
    /**
     * 无效
     */
    public static final Integer STATUS_SONG_INVALID = 1;

    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 描述
     */
    private String desc;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态 0、有效 1、无效（删除）
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Long addTime;

    ///
    /// 非存储字段

    @Transient
    private UserMusicGroup userMusicGroup;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public UserMusicGroup getUserMusicGroup() {
        return userMusicGroup;
    }

    public void setUserMusicGroup(UserMusicGroup userMusicGroup) {
        this.userMusicGroup = userMusicGroup;
    }
}
