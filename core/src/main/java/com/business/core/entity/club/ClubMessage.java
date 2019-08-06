package com.business.core.entity.club;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/11/20.
 * 俱乐部消息
 * <p>
 *     在此 是留言板
 * </p>
 */
@Document(collection = "ClubMessage")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class ClubMessage extends IncIdEntity<Long> {

    /**
     * 留言类型
     * 1、文本
     * 2、语音
     * 3、图片
     */
    private Integer msgType;
    /**
     * 用户编号（发送者 | 留言者）
     * {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 俱乐部编号
     * {@link Club#id}
     */
    private Long clubId;
    /**
     * 内容
     */
    private String content;
    /**
     * 文件链接
     */
    private String fileLink;
    /**
     * 添加时间
     */
    private Long addTime;

    ///
    /// 非存储字段

    @Transient
    private User user;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
}
