package com.business.core.entity.mix;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/8/4 0004.
 * mix 评论信息
 */
@Document(collection = "MixAuthorComment")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class MixAuthorComment extends IncIdEntity<Long> {

    ///
    /// 常量

    /**
     * 状态 有效
     */
    public static final Integer STATE_YES = 1;
    /**
     * 状态 无效
     */
    public static final Integer STAT_NO = 2;

    ///
    /// 属性

    /**
     * 用户编号 (评论的编号) {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 评论信息
     */
    private Integer comment;
    /**
     * 添加时间（评论时间）
     */
    private Long addTime;
    /**
     * 状态 1、有效 2、无效
     */
    private Integer state;
    /**
     * 排序（置顶）
     */
    private Integer sort;
    /**
     * 回复用户
     */
    private Integer replyUid;

    ///
    /// 非存储字段

    @Transient
    private User user;

    @Transient
    private User replyUser;

    public User getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(User replyUser) {
        this.replyUser = replyUser;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getReplyUid() {
        return replyUid;
    }

    public void setReplyUid(Integer replyUid) {
        this.replyUid = replyUid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
