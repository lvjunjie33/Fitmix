package com.business.core.entity.logs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by edward on 2016/4/18.
 * 系统 接口 异常信息
 */
@Document(collection = "SysErrorLog")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class SysErrorLog extends IncIdEntity<Integer> {

    /**
     * 没有解决
     */
    public static int SOLVE_STATUS_NO = 0;
    /**
     * 已经解决
     */
    public static int SOLVE_STATUS_YES = 1;

    /**
     * 异常
     */
    private String exception;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 系统类型
     * 1、app
     * 2、work
     * 3、scheduler
     * 4、mq-server
     * 5、待定
     */
    private Integer sys;

    ///接口 异常

    /**
     * 请求地址
     */
    private String requestUri;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * UA
     */
    private String ua;
    /**
     * IP
     */
    private String ip;
    /**
     * 匹配地址
     */
    private String matchedPath;

    /// MQ 异常
    /**
     * 消息编号
     */
    private String msgId;

    /**
     * 记录时间
     */
    private Date addTime;
    /**
     * 解决状态
     */
    private Integer solveStatus;

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMatchedPath() {
        return matchedPath;
    }

    public void setMatchedPath(String matchedPath) {
        this.matchedPath = matchedPath;
    }

    public Integer getSys() {
        return sys;
    }

    public void setSys(Integer sys) {
        this.sys = sys;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSolveStatus() {
        return solveStatus;
    }

    public void setSolveStatus(Integer solveStatus) {
        this.solveStatus = solveStatus;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
