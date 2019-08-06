package com.business.core.utils;

/**
 * 描述:屌值信息
 * User: sin
 * Time: 2015-10-10 15:49
 */
public class DickInfo {

    /**
     * 用户id
     */
    public Integer uid;
    /**
     * 用户类型
     */
    public Integer language;
    /**
     * 渠道号
     */
    public Integer channel;
    /**
     * 终端：QQ 微信 微博 网页 论坛
     */
    public Integer terminal;
    /**
     * 用户版本
     */
    public Integer version;
    /**
     * 随机类型
     */
    public Integer randomType;
    /**
     * 密码
     */
    public String password;
    /**
     * 密码hash
     */
    public String passwordHash;
    /**
     * 屌值
     */
    public String dickValue;
    /**
     * 错误码
     * 1 : 验证失败
     */
    public Integer errorCode;

    public DickInfo(){

    }
}
