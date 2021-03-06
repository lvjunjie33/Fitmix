package com.business.core.entity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import com.business.core.mongo.RoutingMongoOperations;
import com.business.core.utils.BeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.annotation.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/4/29.
 * 系统参数
 */
@Document(collection = "SysParam")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class SysParam {

    private static Logger logger = LoggerFactory.getLogger(SysParam.class);

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Documented
    public static @interface Description {
        /**
         * @return 模块
         */
        String module();

        /**
         * @return 描述
         */
        String desc();

    }

    ///
    /// app home 宣传图
    @Description(module = "背景图片", desc = "app 首页背景图片")
    public String APP_HOME_BACKGROUND_IMAGE;


    ///
    /// geekery 产品购买地址..
    @Description(module = "产品", desc = "geekery BOLT 购买地址")
    public String APP_GEEKERY_BOLT_PAY_ADDRESS;
    @Description(module = "产品", desc = "geekery BAO 购买地址")
    public String APP_GEEKERY_BAO_PAY_ADDRESS;
    @Description(module = "产品", desc = "geekery WATCH 购买地址")
    public String APP_GEEKERY_WATCH_PAY_ADDRESS;
    @Description(module = "产品", desc = "geekery LIGHT 购买地址")
    public String APP_GEEKERY_LIGHT_PAY_ADDRESS;
    @Description(module = "产品", desc = "geekery SHINE 购买地址")
    public String APP_GEEKERY_SHINE_PAY_ADDRESS;

    ///
    /// IOS 版本控制（appStore 审核中, on 不能上线在审核， yes 上线审核通过）
    @Description(module = "app", desc = "是否上线 ios需要经过 appStore 审核 (11,no,12,yes)")
    public String APP_VERSION_ONLINE;

    ///
    /// 后台

    @Description(module = "后台", desc = "后台登陆ip限制")
    public Set<String> WORK_LOGIN_IPS;
    @Description(module = "后台", desc = "后台系统管理员")
    public String WORK_LOGIN_ROOT_NAM;
    @Description(module = "后台", desc = "刷新缓存地址(http1,http2)")
    public String WORK_REFRESH_BUFFER_URLS;
    @Description(module = "后台", desc = "Dictionary刷新缓存地址")
    public String WORK_REFRESH_DICTIONARY_BUFFER_URLS;

    ///
    /// app

    @Description(module = "app", desc = "首页 mix 歌曲每页显示条数")
    public Integer APP_MIX_SCENE_PAGE_LIMIT_SIZE;
    @Description(module = "app", desc = "运动歌曲分页")
    public Integer APP_MIX_TYPE_PAGE_LIMIT_SIZE;
    @Description(module = "app", desc = "mix 搜索条数")
    public Integer APP_MIX_SEARCH_PAGE_SIZE;
    @Description(module = "app", desc = "userRunHistory 用户历史运动数据分页条数")
    public Integer APP_USER_RUN_HISTORY_SIZE;

    @Description(module = "数据安全", desc = "微信步数设置接口调用时间限制")
    public Integer WX_RUN_SET_STEP_TIME_MIX;


    @Description(module = "app", desc = "IOS 升级版本")
    public Integer APP_IOS_UPGRADE_VERSION;
    @Description(module = "app", desc = "Android 升级版本")
    public Integer APP_ANDROID_UPGRADE_VERSION;
    @Description(module = "app", desc = "Android ROC升级版本")
    public Integer APP_ROC_ANDROID_UPGRADE_VERSION;

    @Description(module = "app", desc = "IOS 升级版本（显示给用户看的版本）")
    public String APP_IOS_UPGRADE_VERSION_VIEW;
    @Description(module = "app", desc = "Android 升级版本（显示给用户看的版本）")
    public String APP_ANDROID_UPGRADE_VERSION_VIEW;
    @Description(module = "app", desc = "Android ROC升级版本（显示给用户看的版本）")
    public String APP_ROC_ANDROID_UPGRADE_VERSION_VIEW;

    @Description(module = "app", desc = "IOS 升级版本介绍")
    public String APP_IOS_UPGRADE_VERSION_INTRODUCTION;
    @Description(module = "app", desc = "Android 升级版本介绍")
    public String APP_ANDROID_UPGRADE_VERSION_INTRODUCTION;
    @Description(module = "app", desc = "Android ROC升级版本介绍")
    public String APP_ROC_ANDROID_UPGRADE_VERSION_INTRODUCTION;
    @Description(module = "app", desc = "IOS Introduction of upgraded version")
    public String APP_IOS_UPGRADE_VERSION_INTRODUCTION_EN;
    @Description(module = "app", desc = "Android Introduction of upgraded version")
    public String APP_ANDROID_UPGRADE_VERSION_INTRODUCTION_EN;
    @Description(module = "app", desc = "ROC Android Introduction of upgraded version")
    public String APP_ROC_ANDROID_UPGRADE_VERSION_INTRODUCTION_EN;


    @Description(module = "app", desc = "IOS 升级下载地址")
    public String APP_IOS_UPGRADE_URL;
    @Description(module = "app", desc = "Android 升级下载地址")
    public String APP_ANDROID_UPGRADE_URL;
    @Description(module = "app", desc = "Android ROC升级下载地址")
    public String APP_ROC_ANDROID_UPGRADE_URL;

    @Description(module = "数据安全", desc = "最新的iOS版本")
    public Integer NEWEST_APP_IOS_VERSION;
    @Description(module = "数据安全", desc = "最新的Android版本")
    public Integer NEWEST_APP_ANDROID_VERSION;
    @Description(module = "数据安全", desc = "最新的ROC Android版本")
    public Integer NEWEST_APP_ROC_ANDROID_VERSION;

    @Description(module = "数据安全", desc = "app升级提示的内容")
    public String UPGRADE_CONTENT;
    @Description(module = "数据安全", desc = "app ROC 升级提示的内容")
    public String UPGRADE_ROC_CONTENT;

    @Description(module = "数据安全", desc = "时间戳校验")
    public Integer TIMESTAMP_CHECK_SIZE;

    @Description(module = "数据安全", desc = "app是否升级的开关(1、打开，0、关闭)")
    public Integer SWITCH_APP_UPGRADE;
    @Description(module = "数据安全", desc = "公钥1地址")
    public String PUBLIC_KEY_LINK1;
    @Description(module = "数据安全", desc = "公钥2地址")
    public String PUBLIC_KEY_LINK2;
    @Description(module = "数据安全", desc = "公钥3地址")
    public String PUBLIC_KEY_LINK3;


    ///
    /// app 运动分享

    @Description(module = "app运动", desc = "app 运动分享 DNS")
    public String APP_USER_RUN_SHARE_DNS;
    @Description(module = "app直播", desc = "app 直播分享 DNS")
    public String APP_USER_LIVE_SHARE_DNS;
    @Description(module = "app直播", desc = "app webSocket host")
    public String APP_USER_LIVE_WEB_SOCKET_HOST;
    @Description(module = "app分享", desc = "app 分享链接下载地址 IOS")
    public String APP_USER_RUN_SHARE_DOWNLOAD_IOS;
    @Description(module = "app分享", desc = "app 分享链接下载地址 Android")
    public String APP_USER_RUN_SHARE_DOWNLOAD_ANDROID;
    @Description(module = "app分享", desc = "app 运动分享地址")
    public String APP_USER_RUN_SHARE_LINK;
    @Description(module = "app分享", desc = "app截图分享二维码图片地址")
    public String APP_DOWNLOAD_QR_CODE_LINK;
    @Description(module = "回答分享", desc = "话题回答分享地址")
    public String APP_THEME_ANSWER_SHARE_LINK;

    ///
    /// app 三方登录

    @Description(module = "app三方登录", desc = "QQ登录key")
    public String APP_LOGIN_QQ_KEY;
    @Description(module = "app三方登录", desc = "微信登录key")
    public String APP_LOGIN_WX_KEY;
    @Description(module = "app三方登录", desc = "微博登录key")
    public String APP_LOGIN_WB_KEY;
    @Description(module = "微信公众平台", desc = "AppID")
    public String APP_MP_WEI_XIN_APP_ID;
    @Description(module = "微信公众平台", desc = "AppSecret")
    public String APP_MP_WEI_XIN_APP_SECRET;
    @Description(module = "微信支付平台", desc = "partnerKey")
    public String APP_MP_WEI_XIN_APP_PARTNER_KEY;

    ///
    /// web 第三方登录

    @Description(module= "web第三方登录", desc = "QQ登录key")
    public String WEB_LOGIN_QQ_KEY;
    @Description(module= "web第三方登录", desc = "微博登录key")
    public String WEB_LOGIN_WB_KEY;
    @Description(module= "web第三方登录", desc = "微博登录授权secret")
    public String WEB_LOGIN_WB_SECRET;
    @Description(module= "web第三方登录", desc = "微博登录回调地址")
    public String WEB_LOGIN_WB_REDIRECTURL;
    @Description(module= "web第三方登录", desc = "微信登录授权key")
    public String WEB_LOGIN_WX_KEY;
    @Description(module= "web第三方登录", desc = "微信登录授权secret")
    public String WEB_LOGIN_WX_SECRET;
    @Description(module= "web第三方登录", desc = "微信登录回调地址")
    public String WEB_LOGIN_WX_REDIRECTURL;
    @Description(module= "web第三方登录", desc = "微信绑定回调地址")
    public String WEB_BIND_WX_REDIRECTURL;

    ///
    /// app 第三方登录 体育部

    @Description(module = "app三方登录", desc = "QQ登录key 体育版")
    public String APP_LOGIN_QQ_LITE_KEY;
    @Description(module = "app三方登录", desc = "微信登录key 体育版")
    public String APP_LOGIN_WX_LITE_KEY;
    @Description(module = "app三方登录", desc = "微博登录key 体育版")
    public String APP_LOGIN_WB_LITE_KEY;

    ///
    /// app 俱乐部

    @Description(module = "俱乐部", desc = "俱乐部成员人数加入限制")
    public Integer APP_CLUB_MEMBER_JOIN_MAX_MEMBER;
    @Description(module = "俱乐部", desc = "开放置顶俱乐部(编号)")
    public List<Long> APP_CLUB_TOP_CLUB;


    ///
    /// mail 邮件模板

    @Description(module = "邮箱模板", desc = "用户忘记密码发送模板主题")
    public String MAIL_USER_RECOVERY_PASSWORD_SUBJECT;
    @Description(module = "邮箱模板", desc = "用户忘记密码发送模板")
    public String MAIL_USER_RECOVERY_PASSWORD_TEMPLATE;
    @Description(module = "邮箱模版", desc = "邮件验证码发送模版")
    public String MAIL_USER_RECOVERY_VERIFICATION_CODE_TEMPLATE;
    @Description(module = "邮箱模版", desc = "C罗邮件验证码发送模版")
    public String MAIL_USER_RECOVERY_VERIFICATION_CODE_TEMPLATE_ROC;

    ///
    /// mix歌曲评论 and mix作者评论

    @Description(module = "mix歌曲评论", desc = "分页数")
    public Integer MIX_COMMENT_PAGE_SIZE;


    ///
    /// 活动

    @Description(module = "活动用户运动排行", desc = "活动版本号 格式：JSON对象数组格式")
    public String ACTIVITY_USER_RUN_VERSION;

    ///
    /// 微信

    @Description(module = "微信", desc = "关注回复")
    public String WX_SUBSCRIBE_REPLY_CONTENT;

    @Description(module = "微信", desc = "微信回复")
    public String WX_REPLY;

    ///
    /// 第三方赛事
    @Description(module = "第三方赛事", desc = "第三方赛事禁用列表")
    public Set<Integer> JOIN_ACTIVITY_DISABLE;

    /**
     * 单例对象
     */
    public static SysParam INSTANCE;

    public static synchronized void init() {
        Long now = System.currentTimeMillis();
        logger.info("初始化 SysParam 开始...");
        SysParam param = BeanManager.getBean(RoutingMongoOperations.class).findOne(new Query(), SysParam.class);
        INSTANCE = param;
        logger.info("初始化 SysParam 完成...消耗 {}毫秒", System.currentTimeMillis() - now);
    }
}
