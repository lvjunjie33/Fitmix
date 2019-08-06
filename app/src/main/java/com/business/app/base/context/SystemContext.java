package com.business.app.base.context;

import com.business.core.entity.user.User;
import com.business.core.utils.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户请求时的Context
 * User: sin
 * Date: 15-7-25
 * Time: 上午10:40
 */
public class SystemContext {

    /**
     * 请求
     */
    private HttpServletRequest request;
    /**
     * 响应
     */
    private HttpServletResponse response;
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
     * 客户端系统 1ios 2android
     */
    @Deprecated
    private Integer clientSystem;

    /**
     * 语言包
     */
    private String language;

    /**
     * 用户请求时的Context
     */
    public SystemContext() {
    }

    /**
     * 用户请求时的Context
     *
     * @param request     请求
     * @param response    响应
     * @param matchedPath 匹配路径
     */
    public SystemContext(HttpServletRequest request, HttpServletResponse response, String matchedPath) {
        this.request = request;
        this.response = response;
        this.matchedPath = matchedPath;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
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

    public Integer getClientSystem() {
        return clientSystem;
    }

    public void setClientSystem(Integer clientSystem) {
        this.clientSystem = clientSystem;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return IP
     */
    public String getIp() {
        if (ip == null) {
            ip = HttpUtil.getIP(request);
        }
        return ip;
    }
}