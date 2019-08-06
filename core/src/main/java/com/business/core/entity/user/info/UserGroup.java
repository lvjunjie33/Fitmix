package com.business.core.entity.user.info;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by edward on 2017/11/2.
 *
 * 用户关系形式(私信、群、俱乐部、好友等)
 */
@Document(collection = "UserGroup")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class UserGroup extends IncIdEntity<Long>{

    //私信
    public static final Integer TYPE_USER_PRIVATE = 1;
    //好友
    public static final Integer TYPE_USER_FRIEND = 2;
    //关注
    public static final Integer TYPE_USER_ATTENTION = 3;


    /**
     * 会话类型
     */
    private Integer type;
    /**
     * 会话成员
     */
    private List<Integer> member;
    /**
     * 添加时间
     */
    private Long addTime;

    //============================================消息相关内容======================================================

    /**
     * 最后一次更新时间
     */
    private Long lastUpdateTime;
    /**
     * 最后一次内容 (冗余)
     */
    private String lastContent;
    /**
     * 最后一个说话的用户编号
     */
    private Integer lastMsgUid;
    /**
     * 用户
     */
    @Transient
    private User user;
    /**
     * 屏蔽
     */
    @Transient
    private Integer reject;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Integer> getMember() {
        return member;
    }

    public void setMember(List<Integer> member) {
        this.member = member;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public Integer getLastMsgUid() {
        return lastMsgUid;
    }

    public void setLastMsgUid(Integer lastMsgUid) {
        this.lastMsgUid = lastMsgUid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getReject() {
        return reject;
    }

    public void setReject(Integer reject) {
        this.reject = reject;
    }
}
