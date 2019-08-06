package com.business.core.entity.community.discuss;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2017/7/4.
 *
 * TODO 已经废弃
 */
@Document(collection = "CategoryMsg")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class CategoryMsg extends IncIdEntity<Long> {
    /**
     * 问题被回答的消息
     */
    public static final Integer TYPE_THEME_ANSWER = 1;
    /**
     * 回答被讨论的消息
     */
    public static final Integer TYPE_THEME_DISCUSS = 2;
    /**
     * 讨论被@ 如:张三 @ 李四
     */
    public static final Integer TYPE_THEME_TO_DISCUSS = 3;
    /**
     * 消息类型
     * 1、话题相关消息
     * 2、回答相关消息
     */
    private Integer type;
    /**
     * 目标编号
     * {@link Theme#id}
     * {@link Discuss#id}
     */
    private Long targetId;
    /**
     * 被回复的用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer targetUId;
    /**
     * 回复的用户编号
     */
    private Integer fromUId;
    /**
     * 消息状态
     * 1、未读
     * 0、已读 {@link com.business.core.constants.Constants#STATE_INVALID}
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Long addTime;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getTargetUId() {
        return targetUId;
    }

    public void setTargetUId(Integer targetUId) {
        this.targetUId = targetUId;
    }

    public Integer getFromUId() {
        return fromUId;
    }

    public void setFromUId(Integer fromUId) {
        this.fromUId = fromUId;
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
