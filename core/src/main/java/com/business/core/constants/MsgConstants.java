package com.business.core.constants;

/**
 * Created by edward on 2017/9/18.
 */
public class MsgConstants {

    ///
    /// 消息处理状态

    /**
     * 未处理
     */
    public static final int HANDLE_STATUS_FALSE = 0;
    /**
     * 处理成功
     */
    public static final int HANDLE_STATUS_TRUE = 1;
    /**
     * 处理失败
     */
    public static final int HANDLE_STATUS_ERROR = 2;

    /// 消息类型

    //没有内容的消息
    public static final int CONTENT_TYPE_EMPTY = 0;
    //文本
    public static final int CONTENT_TYPE_TEXT = 1;
    //语音
    public static final int CONTENT_TYPE_VOICE = 2;
    //图片
    public static final int CONTENT_TYPE_IMG = 3;
    //链接
    public static final int CONTENT_TYPE_LINK = 4;


    // ========== 消息 ==========
    /**
     * 主题 - MAIL - 发送邮件
     */
    public final static String CHANNEL_MAIL_SEND = "1";
    /**
     * 主题 - Sms - 发送短信
     */
    public final static String CHANNEL_SMS_SEND = "2";
    /**
     * 俱乐部消息发送
     */
    public final static String CHANNEL_CLUB_MSG_SEND = "10";
    /**
     * 俱乐部通知
     */
    public final static String CHANNEL_CLUB_NOTICE_SEND = "11";
    /**
     * 训练计划消息推送
     */
    public final static String CHANNEL_USER_RUN_PLAN_SEND = "12";
    /**
     * 赛事消息推送
     */
    public final static String CHANNEL_ACTIVITY_SEND = "13";
    /**
     * 话题推荐推送
     */
    public final static String CHANNEL_THEME_RECOMMEND_SEND = "14";
    /**
     * Mix 推荐推送
     */
    public final static String CHANNEL_TYPE_MIX_SEND = "15";
    /**
     * 电台 推荐推送
     */
    public final static String CHANNEL_TYPE_RADIO = "16";
    /**
     * 视频 推荐推送
     */
    public final static String CHANNEL_TYPE_VIDEO = "17";
    /**
     * 第三方外链(在app内嵌浏览器中打开第三方的网址)
     */
    public final static String CHANNEL_TYPE_OTHER_LINK = "18";
    /**
     * 用户私信 (单用户对群体每日100个人，单用户对单人5秒钟只能发送一次)
     */
    public final static String CHANNEL_TYPE_USER_PRIVATE_MSG = "19";
    /**
     * 话题 被回答了
     */
    public final static String CHANNEL_THEME_ANSWER = "20";
    /**
     * 话题回答 被讨论了
     */
    public final static String CHANNEL_ANSWER_DISCUSS = "21";


    ///=====================================任务===========================================================
    /**
     * app运动运动数据 异步任务
     */
    public final static String CHANNEL_USER_RUN_TASK = "50";
    /**
     * 运动结束排行榜
     */
    public final static String CHANNEL_USER_RUN_RANK_TASK = "51";
    /**
     * 积分赛事 统计
     */
    public final static String CHANNEL_ACTIVITY_INTEGRAL_TASK = "53";
    /**
     * 万德赛道统计
     */
    public final static String CHANNEL_SPEEDWAY_RUN_TASK = "54";
    /**
     * 用户运动数据汇总(周、月、年)统计
     */
    public final static String CHANNEL_USER_RUN_STAT_TASK = "55";
    /**
     * 俱乐部榜单统计 任务
     */
    public final static String CHANNEL_CLUB_USER_RUN_RANK_TASK = "56";
    /**
     * 手表运动保存数据 异步任务
     */
    public final static String CHANNEL_USER_RUN_WATCH_TASK = "57";
    /**
     * 用户登录 异步任务
     */
    public final static String CHANNEL_USER_LOGIN_TASK = "58";

}
