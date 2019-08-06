package com.business.core.entity;


import com.business.core.entity.user.User;

/**
 * 用户在线缓存
 * User: sin
 * Date: 16-05-25
 * Time: 晚上 10:04
 */
public class UserOnline {

    public UserOnline(User user) {
        this.user = user;
    }

    /**
     * 用户信息.目前只缓存以下几个属性.
     * <pre>
     *     1、{@link User#BASIC_INFO_FIELDS}
     *     2、{@link User#password}
     * </pre>
     */
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
