package com.business.core.entity.logs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Sin on 2016/2/29.
 *
 * 服务器 请求log
 *
 * 分表之后每隔一个月，log_requestLog10 表后缀加1，此表仅仅提供读取
 */
@Document(collection = "log_RequestLog17")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class RequestLog {

    /**
     * 本日
     */
    public static final int TYPE_DAY = 1;
    /**
     * 本周
     */
    public static final int TYPE_WEEK = 2;
    /**
     * 本月
     */
    public static final int TYPE_MONTH = 3;

    @Id
    private ObjectId id;

    ///
    /// request SystemContext

    /**
     * 请求地址
     */
    private String matchedPath;

    /**
     * 设备唯一碼
     */
    private String udid;
    /**
     * 应用版本
     */
    private Integer version;
    /**
     * 终端类型
     */
    private Integer terminal;
    /**
     * 渠道编号
     */
    private String channel;
    /**
     * 网络 :wifi :3G 2G
     */
    private String network;
    /**
     * 客户端系统
     */
    private String sdk;
    /**
     * 屌值
     */
    private String dickValue;
    /**
     * 在线缓存
     */
    private User user;
    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * IP
     */
    private String ip;
    /**
     * 是否进行登录
     */
    private boolean doLogin = false;
    /**
     * 客户端请求时间
     */
    private Long clientRequestTime;
    /**
     * 服务端接受请求时间
     */
    private Long serverReceiveTime;
    /**
     * 服务端响应请求时间
     */
    private Long serverResponseTime;
    /**
     * 客户端系统 1ios 2android
     */
    private Integer clientSystem;
    /**
     * 语言包
     */
    private String language;

    ///
    /// request 消耗

    /**
     * 消耗时间，单位：毫秒
     */
    private Long consumeTime;
    /**
     * 添加时间
     */
    private Long addTime;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getMatchedPath() {
        return matchedPath;
    }

    public void setMatchedPath(String matchedPath) {
        this.matchedPath = matchedPath;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public String getDickValue() {
        return dickValue;
    }

    public void setDickValue(String dickValue) {
        this.dickValue = dickValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isDoLogin() {
        return doLogin;
    }

    public void setDoLogin(boolean doLogin) {
        this.doLogin = doLogin;
    }

    public Long getClientRequestTime() {
        return clientRequestTime;
    }

    public void setClientRequestTime(Long clientRequestTime) {
        this.clientRequestTime = clientRequestTime;
    }

    public Long getServerReceiveTime() {
        return serverReceiveTime;
    }

    public void setServerReceiveTime(Long serverReceiveTime) {
        this.serverReceiveTime = serverReceiveTime;
    }

    public Long getServerResponseTime() {
        return serverResponseTime;
    }

    public void setServerResponseTime(Long serverResponseTime) {
        this.serverResponseTime = serverResponseTime;
    }

    public Integer getClientSystem() {
        return clientSystem;
    }

    public void setClientSystem(Integer clientSystem) {
        this.clientSystem = clientSystem;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
