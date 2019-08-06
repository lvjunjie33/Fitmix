package com.business.core.constants;

import com.business.core.entity.user.User;
import com.business.core.utils.RedisUtil;

/**
 * Created by Sin on 2016/4/26.
 *
 * redis cache 和 key
 */
public class RedisConstants {

    ///
    /// util

    /**
     * key format str
     */
    public static final String CACHE_UNIQUE_KEY = "_%s_%s_";

    /**
     * 获取唯一 key ：用于存储 bean 集合
     *
     * @param number id
     * @param tClass class
     * @param <T>
     * @return unique key
     */
    public static <T> String getUniqueKey(Number number, Class<T> tClass) {
        return String.format(CACHE_UNIQUE_KEY, number.toString(), tClass.getName());
    }

    ///
    /// 用户

    /**
     * 用户缓存 - 用户登录
     */
    public static final int CACHE_USER_ONLINE_DB = 1;
    /**
     * 用户缓存 - UserOnline的存活时间，单位：秒
     */
    public static final int CACHE_USER_EXPIRE_TIME = 60 * 30;

    /**
     * 缓存在线用户
     * @param user
     */
    public static void cacheOnlineUser(User user) {
        RedisUtil.cacheAddUpdate(RedisConstants.CACHE_USER_ONLINE_DB, RedisConstants.getUniqueKey(user.getId(), User.class), user, RedisConstants.CACHE_USER_EXPIRE_TIME);
    }

    /**
     * 获取缓存用户
     * @param id
     * @return
     */
    public static User cacheGetOnlineUser(Integer id) {
        return RedisUtil.cacheGet(RedisConstants.CACHE_USER_ONLINE_DB, RedisConstants.getUniqueKey(id, User.class), User.class);
    }

    ///
    /// MQNotice

    public static final int CACHE_NOTICE_NEW_DB = 2;
    /**
     * 用户缓存 - UserOnline的存活时间，单位：秒 25 小时内对接
     *
     * 定时器 > 每日 11 点整 > 更新到 mongo > 并清除当前 notice 所有key
     */
    public static final int CACHE_NOTICE_EXPIRE_TIME = 60 * 60 * 24;

    ///
    /// wx run

    /**
     * 微信运动 - 微信用户信息
     */
    public static final int CACHE_WX_RUN_DB = 1;
    /**
     * 微信运动 - 缓存 10 分钟
     */
    public static final int CACHE_WX_RUN_EXPIRE_TIME = 60 * 10;


    ///
    /// WeChat access token

    /**
     * 微信 AccessToken - 存储的数据库
     */
    public static final int CACHE_WE_CHAT_ACCESS_TOKEN_DB = 3;
    /**
     * 微信 AccessToken - 存储时间 无限,不过期
     */
    public static final int CACHE_WX_CHAT_ACCESS_TOKEN_EXPIRE_TIME = -1;
    /**
     * 微信 AccessToken - WeChat Access Token 存储的 key
     */
    public static final String WE_CHAT_ACCESS_TOKEN_CACHE_KEY = "we_chat_access_token";
    /**
     * 微信 JsapiTicket - 存储 key
     */
    public static final String WE_CHAT_JSAPI_TICKET_CHACHE_KEY = "we_chat_jsapi_ticket";
    /**
     * 微信 AccessToken - 获取 WeChat Access Token
     *
     *  定时器 > 1小时更新一次 > 数据库和 Redis
     *
     * @return accessToken
     */
    public static String getWeChatAccessToken() {
        return RedisUtil.cacheGet(CACHE_WE_CHAT_ACCESS_TOKEN_DB, WE_CHAT_ACCESS_TOKEN_CACHE_KEY);
    }

    /**
     * 微信 AccessToken - 设置
     *
     * @param accessToken 需要更新的 accessToken
     */
    public static void setWeChatAccessToken(String accessToken) {
        RedisUtil.cacheAddUpdate(CACHE_WE_CHAT_ACCESS_TOKEN_DB,
                WE_CHAT_ACCESS_TOKEN_CACHE_KEY, accessToken, CACHE_WX_CHAT_ACCESS_TOKEN_EXPIRE_TIME);
    }

    /**
     * 获取 JsapiTicket
     *
     * 和token 同时更新
     *
     * @return jsapi_ticket
     */
    public static  String getJsapiTicket() {
        return RedisUtil.cacheGet(CACHE_WE_CHAT_ACCESS_TOKEN_DB, WE_CHAT_JSAPI_TICKET_CHACHE_KEY);
    }

    /**
     * 设置 JsapiTicket
     * @param jsapiTicket 需要更新的 jsapiTicket
     */
    public static void setJsapiTicket(String jsapiTicket) {
        RedisUtil.cacheAddUpdate(CACHE_WE_CHAT_ACCESS_TOKEN_DB,
                WE_CHAT_JSAPI_TICKET_CHACHE_KEY, jsapiTicket, CACHE_WX_CHAT_ACCESS_TOKEN_EXPIRE_TIME);
    }

    /**
     * 公共的订阅
     */
    public static final String CHANNEL_STATIC_COMMON = "channel_static_common";
    /**
     * 开启 redis 订阅
     */
    public static final String OPEN_REDIS_SUBSCRIBE = "open_redis_subscribe";


    /**
     * 流米 token 保存数据库
     */
    public static final Integer LIUMI_TOKEN_DB = 4;

    /**
     * 流米 token key
     */
    public static final String LIUMI_TOKEN_KEY = "liumi_token";

    /**
     * 流米 token 保存时间
     */
    public static final Integer LIUMI_TOKEN_EXPIRE_TIME = 60 * 60 * 24;



}
