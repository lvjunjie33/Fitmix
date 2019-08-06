package com.business.core.entity.dynamic;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/10/8.
 * 朋友圈评论
 */
@Document(collection = "Comment")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class Comment extends IncIdEntity<Long> {

    /**
     * 动态编号 {@link com.business.core.entity.dynamic.Dynamic#id}
     */
    private Long did;
    /**
     * 评论来源用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer fromUid;
    /**
     * 回复用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer toUid;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 添加时间
     */
    private Long addTime;

    @Transient
    private User fromUser;

    @Transient
    private User toUser;

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Comment(Long did, Integer fromUid, String content) {
        this.did = did;
        this.fromUid = fromUid;
        this.content = content;
    }
}
