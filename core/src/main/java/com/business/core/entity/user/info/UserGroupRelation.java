package com.business.core.entity.user.info;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2017/11/2.
 *
 * 会话关系
 */
@Document(collection = "UserGroupRelation")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class UserGroupRelation extends IncIdEntity<Long> {

    /**
     * 未读
     */
    public static final Integer READ_STATE_FALSE = 0;
    /**
     * 已读
     */
    public static final Integer READ_STATE_TRUE = 1;

    /**
     * 非黑名单
     */
    public static final Integer REJECT_FALSE = 0;
    /**
     * 黑名单
     */
    public static final Integer REJECT_TRUE = 1;

    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 团体编号
     */
    private Long groupId;
    /**
     * 添加时间
     */
    private Long addTime;

    /**
     * 拒绝/屏蔽/拉黑
     *
     * 0、正常
     * 1、屏蔽
     * 2、拉黑
     */
    private Integer reject;
    /**
     * 1、正常，0、删除
     */
    private Integer state;

    /**
     * 已读未读状态
     * 0、未读
     * 1、已读
     */
    private Integer readState;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getReject() {
        return reject;
    }

    public void setReject(Integer reject) {
        this.reject = reject;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getReadState() {
        return readState;
    }

    public void setReadState(Integer readState) {
        this.readState = readState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
