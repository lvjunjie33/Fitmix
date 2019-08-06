package com.business.core.entity.msg;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2017/9/18.
 */
@Document(collection = "Messages")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class Message extends IncIdEntity<Long> {
    /**
     * 消息在服务器存储时间戳
     */
    private Long addTime;
    /**
     * 处理状态 {0、未处理，1、已经处理，2、处理失败}
     * {@link com.business.core.constants.MsgConstants#HANDLE_STATUS_FALSE}
     */
    private Integer status;
    /**
     * 异常信息
     */
    private String errorInfo;
    /**
     * 消息 处理 通道
     */
    private String selectChannel;
    /**
     * 消息实体
     */
    private Object msgBody;
    /**
     * 管理员id (存根)
     */
    private Integer adminId;

    /**
     * 发消息的人
     */
    @Transient
    private User fromUser;

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

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getSelectChannel() {
        return selectChannel;
    }

    public void setSelectChannel(String selectChannel) {
        this.selectChannel = selectChannel;
    }

    public Object getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(Object msgBody) {
        this.msgBody = msgBody;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}
