package com.business.core.constants;

import com.business.core.utils.CommonUtil;
import com.business.core.utils.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.security.Key;
import java.util.Set;

/**
 * 应用所有配置
 */
public class ApplicationConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    /**
     * 系统默认编码
     */
    public static final Charset CHARSET = Charset.forName("UTF-8");

    /**
     * 是否为开发模式.该参数主要用于一些工具类的延迟加载。<br />
     * true: 不使用{@link com.business.core.servlet.AbstractBaseInitServlet}进行初始化
     * false: 当调用到相应的工具类，靠类加载器去加载
     */
    public static boolean DEV = false;
    /**
     * 是否在服务器上.该参数主要用于一些程序需要调用服务器的脚本等等。
     */
    public static Boolean SERVER;

    /**
     * MONGODB - admin - 数据库名
     */
    public static String MONGO_ADMIN_DBNAME;
    /**
     * MONGODB - admin - 用户名
     */
    public static String MONGO_ADMIN_USERNAME;
    /**
     * MONGODB - admin - 密码
     */
    public static String MONGO_ADMIN_PASSWORD;

    /**
     * 文件服务器 - 请求基础地址（根地址）
     */
    public static String FILECENTER_REQUEST_URI_PREFIX;
    /**
     * 文件服务器 - 基础地址
     */
    public static String FILECENTER_REQUEST_URI;
    /**
     * 文件服务器 - 基础存储地址
     */
    public static String FILECENTER_STORAGE_PATH;

    /**
     * Redis配置 - HOST
     */
    public static String REDIS_URI;
    /**
     * Redis配置 - 端口
     */
    public static int REDIS_PORT;
    /**
     * Redis配置 - 最大连接数//默认8
     */
    public static int REDIS_MAX_ACTIVE;
    /**
     * Redis配置 - 最大空闲的连接数//默认8
     */
    public static int REDIS_MAX_IDLE;
    /**
     * Redis配置 - 超时时间//默认-1 没有限制
     */
    public static int REDIS_MAX_WAIT;
    /**
     * Redis配置 - 当调用borrow Object方法时，是否进行有效性检查  //默认 false
     */
    public static boolean REDIS_TEST_ON_BORROW;
    /**
     * Redis配置 - redis连接超时时间 默认2000毫秒
     */
    public static int REDIS_TIMEOUT;

    /**
     * Redis配置 - 密码
     */
    public static String REDIS_PASSWD;

    /**
     * Scheduler配置 - host
     */
    public static String SCHEDULE_HOST;

    ///
    /// 邮箱 FitMix

    /**
     * 邮箱/用户名
     */
    public static String MAIL_USERNAME;
    /**
     * 邮箱/密码
     */
    public static String MAIL_PASSWORD;
    /**
     * smtp host
     */
    public static String MAIL_HOST;

    ///
    /// 邮箱C罗
    /**
     * 邮箱/用户名
     */
    public static String MAIL_USERNAME_CL;
    /**
     * 邮箱/密码
     */
    public static String MAIL_PASSWORD_CL;
    /**
     * smtp host
     */
    public static String MAIL_HOST_CL;


    ///
    /// 直播 (live)

    /**
     * 直播地址(web socket)
     */
    public static String LIVE_WEB_SOCKET_URL;
    /**
     * socket 端口
     */
    public static Integer LIVE_PORT;
    /**
     * 连接数量
     */
    public static Integer LIVE_CONNECT_COUNT;


    ///
    /// app 缓存地址

    /**
     * 系统参数地址
     */
    public static String APP_SYS_PARAM_URL;
    /**
     * 字典地址
     */
    public static String APP_DIC_URL;


    ///
    /// aliyun file

    /**
     * 文件传输地址
     */
    public static String ALIYUN_ENDPOINT;
    /**
     * access id
     */
    public static String ALIYUN_ACCESS_ID;
    /**
     * access key
     */
    public static String ALIYUN_ACCESS_KEY;
    /**
     * bucket name
     */
    public static String ALIYUN_BUCKET_NAME;

    ///
    /// 第三方

    /**
     * umeng app key
     */
    public static String UMENG_APP_KEY;
    /**
     * umeng app secret
     */
    public static String UMENG_APP_SECRET;

    ///
    /// IOS 信息

    /**
     * ios 在appStore id
     */
    public static String IOS_APP_ID;
    /**
     * ios 在appStore id
     */
    public static String IOS_BUNDLE_ID;
    /**
     * app端公钥地址
     */
    public static String PUB_KEY_URL;
    /**
     * 公钥
     */
    public static Key pubKey;
    /**
     * 私钥
     */
    public static Key priKey;

    //TODO [sin to sin 参数 version 暂时不用，暂不区分模式]
    public static synchronized void init() {
        System.setProperty ("jsse.enableSNIExtension", "false");// 防止HttpGet请求报错(javax.net.ssl.SSLProtocolException: handshake alert: unrecognized_name)
        System.setProperty("https.protocols", "SSLv3,SSLv2Hello"); // 解决：java.lang.RuntimeException: javax.net.ssl.SSLException: Received fatal alert: bad_record_mac

        long now = System.currentTimeMillis();
        LOGGER.info("初始ApplicationConfig开始...");

        // 开发模式
        PropertiesLoader loader = new PropertiesLoader("/application-core.properties");
        if (loader.getProperties().isEmpty()) {
            throw new Error(String.format("加载配置文件[%s]失败", "/application-core.properties"));
        }

        //DEV = loader.getBoolean("dev");
        Set<String> localIps = CommonUtil.getLocalIps();
        for (String ip : localIps) {
            if (ip.startsWith("120.76.74.90.") || ip.startsWith("120.76.40.")) { // 正服服务器内网IP
                SERVER = true;
            }
        }
        if (SERVER == null) {
            SERVER = false;// 本地/测试 服务器内网IP
        }

        // MONGO服务器
        MONGO_ADMIN_DBNAME = loader.getProperty("mongo.dbname");
        MONGO_ADMIN_USERNAME = loader.getProperty("mongo.username");
        MONGO_ADMIN_PASSWORD = loader.getProperty("mongo.password");
        // 文件服务器配置
//        FILECENTER_REQUEST_URI_PREFIX = loader.getProperty("FileCenter.REQUEST_URI_PREFIX");
//        FILECENTER_REQUEST_URI = loader.getProperty("FileCenter.URI");
        FILECENTER_STORAGE_PATH = loader.getProperty("FileCenter.STORAGE_PATH");
        // Redis配置
        REDIS_URI = loader.getProperty("redis.url");
        REDIS_PORT = loader.getInteger("redis.port");
        REDIS_PASSWD=loader.getProperty("redis.pass");
        REDIS_MAX_ACTIVE = loader.getInteger("redis.maxActive");
        REDIS_MAX_IDLE = loader.getInteger("redis.maxIdle");
        REDIS_MAX_WAIT = loader.getInteger("redis.maxWait");
        REDIS_TEST_ON_BORROW = loader.getBoolean("redis.testOnBorrow");
        REDIS_TIMEOUT = loader.getInteger("redis.timeout");
        // Scheduler配置
        SCHEDULE_HOST = loader.getProperty("Schedule.host");
        // 邮箱
        MAIL_USERNAME = loader.getProperty("mail.username");
        MAIL_PASSWORD = loader.getProperty("mail.password");
        MAIL_HOST = loader.getProperty("mail.host");

        // C罗邮箱
        MAIL_USERNAME_CL = loader.getProperty("mail.username_c");
        MAIL_PASSWORD_CL = loader.getProperty("mail.password_c");
        MAIL_HOST_CL = loader.getProperty("mail.host_c");

        // live
        LIVE_WEB_SOCKET_URL = loader.getProperty("live.webSocketUrl");
        LIVE_PORT = Integer.valueOf(loader.getProperty("live.port"));
        LIVE_CONNECT_COUNT = Integer.valueOf(loader.getProperty("live.connectCount"));
        // app
        APP_DIC_URL = loader.getProperty("app.dicUrl");
        APP_SYS_PARAM_URL = loader.getProperty("app.sysParamUrl");
        // aliyun oss
        ALIYUN_ENDPOINT = loader.getProperty("Aliyun.ENDPOINT");
        ALIYUN_ACCESS_ID = loader.getProperty("Aliyun.ACCESS_ID");
        ALIYUN_ACCESS_KEY = loader.getProperty("Aliyun.ACCESS_KEY");
        ALIYUN_BUCKET_NAME = loader.getProperty("Aliyun.BUCKET_NAME");
        // 第三方
        UMENG_APP_KEY = loader.getProperty("umeng.appKey");
        UMENG_APP_SECRET = loader.getProperty("umeng.appSecret");
        // ios
        IOS_APP_ID =  loader.getProperty("ios.appid");
        IOS_BUNDLE_ID = loader.getProperty("ios.bundleid");

        LOGGER.info("初始ApplicationConfig 完成...消耗：{}毫秒", System.currentTimeMillis() - now);
    }

}