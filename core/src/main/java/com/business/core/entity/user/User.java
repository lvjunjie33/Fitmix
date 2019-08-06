package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.constants.Constants;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.TaoBaoIp;
import com.business.core.entity.im.IMInfoUser;
import com.business.core.mongo.MongoType;
import com.business.core.utils.CommonUtil;
import com.business.core.utils.MD5Util;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by sin on 15/3/29.
 *
 * 用户信息
 */
@Document(collection = "User")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class User extends IncIdEntity<Integer>{

    /**
     * 微信步数设置黑名单
     */
    public static final Integer INTERFACE_WX_SET_STEP = 1;
    /**
     * 金币系统黑名单
     */
    public static final Integer INTERFACE_SHOP_ACCOUNT = 2;

    /**
     * 正式用户
     */
    public static final Integer IS_TOURIST_FALSE = 0;
    /**
     * 游客
     */
    public static final Integer IS_TOURIST_TRUE = 1;
    /**
     * 用户是否激活 : 是
     */
    public static final Integer STATE_ACTIVATES = 1;
    /**
     * 用户是否激活 : 否
     */
    public static final Integer STATE_NO_ACTIVATES = 2;
    /**
     * 话题(认证用户)
     */
    public static final Integer THEME_VIP_TRUE = 1;
    /**
     * 话题普通用户
     */
    public static final Integer THEME_VIP_FALSE = 0;

    ///
    /// 注册类型 终端类型 登录类型

    /**
     * email 登录
     */
    public static final Integer EMAIL = 1;
    /**
     * QQ 登录
     */
    public static final int QQ = 2;
    /**
     * 微信 登录
     */
    public static final int WX = 3;
    /**
     * 微博 登录
     */
    public static final int WB = 4;
    /**
     * 手机
     */
    public static final int MOBILE = 5;
    /**
     * uid
     */
    public static final int UID = 6;
    /**
     * 游客
     */
    public static final int TOURIST = 7;

    ///
    /// 用户类型

    /**
     * 用户类型：中国 kg/cm
     */
    public static final Integer TYPE_1 = 1;
    /**
     * 用户那些：外国 ib/in
     */
    public static final Integer TYPE_2 = 2;

    /**
     * 基础资料字段
     */
    public static final String[] BASIC_INFO_FIELDS = new String[] {"id", "name", "gender", "age", "height", "loginType", "loginCount",
            "weight", "avatar", "type", "email", "mobile",  "state",  "registerType", "userRealInfo",
            "qqOpenid", "qqName", "wbOpenid",  "wbName", "openid", "wxOpenid", "wxName", "signature",
            "password", "newPwd", "sumSkipRope", "lastSkipRopeHeartRate", "lastRunHeartRate","isTourist", "themeVip"};

    /**
     * 官网基础资料字段
     */
    public static final String[] BASIC_INFO_FIELDS_GW = new String[] {"id", "name", "gender", "age", "height", "loginType", "loginCount",
            "weight", "avatar", "type", "email", "mobile",  "state",  "registerType", "userRealInfo",
            "qqOpenid", "qqName", "wbOpenid",  "wbName", "openid", "wxOpenid", "wxName", "signature",
            "password", "newPwd", "sumSkipRope", "lastSkipRopeHeartRate", "lastRunHeartRate","isTourist", "themeVip","taoBaoIp"};

    private Long newId;
    /**
     * 设备编号
     */
    private String udid;
    /**
     * 名称
     */
    private String name;
    /**
     * 性别(1:男 2:女 3:保密)
     */
    private Integer gender;
    /**
     * 生日
     */
    private Map<String, String> birthday;
    /**
     * 城市
     */
    private String city;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 身高 (cm、in)
     */
    private Double height;
    /**
     * 体重 (kg、ib)
     */
    private Double weight;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户地区类型 (区分国内国外身高体重，国内 cm/kg 国外 in/ib)
     */
    private Integer type;

    /**
     * 个性签名
     */
    private String signature;
    /**
     * 密码
     */
    private String password;
    /**
     * 密文密码
     */
    private String newPwd;
    /**
     * 登录次数
     */
    private Integer loginCount;
    /**
     * mix 下载次数
     */
    private Integer downloadCount;
    /**
     * 最后一次跑步心率数据
     */
    private UserHeartRate lastRunHeartRate;
    /**
     * 最后一次跳绳心率数据
     */
    private UserHeartRate lastSkipRopeHeartRate;
    /**
     * 登录地区
     */
    private TaoBaoIp taoBaoIp;
    /**
     * 状态， 是否激活
     * 1、是 2、否
     */
    private Integer state;

    ///
    ///终端信息, 用户活跃 ， 最后登录信息

    /**
     * ios idfa 编号
     */
    private String idfa;
    /**
     * 终端信息
     */
    private String sdk;
    /**
     * 终端
     *
     * (1、app注册 2、QQ 注册 3、微信注册  4、微博注册 5、手机)
     */
    private Integer terminal;
    /**
     * 手机型号
     */
    private String mobileType;
    /**
     * 运营商,  (中国联通, 中国电信, 中国移动 等.)
     */
    private String operators;
    /**
     * 渠道信息.（登录渠道） 1 官网  2 应用宝 3 appStore 等. 根据发包情况
     */
    private String channel;
    /**
     * 注册方式 (1、app注册 2、QQ 注册 3、微信注册  4、微博注册 5、手机)
     */
    private Integer loginType;
    /**
     * 最后登录时间
     */
    private Long loginTime;
    /**
     * 注册的版本
     */
    private String version;
    /**
     * 当前版本
     */
    private String currentVersion;

    ///
    /// （登录方式）

    /**
     * 电子邮箱
     */
//    @Indexed(unique=true, sparse = true)
    @Indexed
    private String email;
    /**
     * 手机号
     */
//    @Indexed(unique=true, sparse = true)
    @Indexed
    private String mobile;
    /**
     * qq openid
     */
    @Indexed
    private String qqOpenid;
    /**
     * qq openid name
     */
    private String qqName;
    /**
     * 微博 openid
     */
    @Indexed
    private String wbOpenid;
    /**
     * 微博 openid name
     */
    private String wbName;
    /**
     * 微信 app openid  (微信登录授权openId)
     */
    @Indexed
    private String wxOpenid;
    /**
     * 微信 app openid
     */
    private String wxName;
    /**
     * 最后一次设置微信步数记录
     */
    private UserWXStep lastSetWXStep;

    ///
    /// 关联信息 （微信 微博 qq openid）

    /**
     * 微信 关联信息
     */
    private String unionid;
    /**
     * qq 关联信息
     */
    private String appid;


    ///
    /// 三方登录信息

    /**OO
     * 身份 token
     */
    private String token;
    /**
     * oopenid ：2.3前版本 QQ 微信 微博 公用
     */
    @Deprecated
    private String openid;
    /**
     * 公众号 openid
     */
    private String mqOpenId;
    /**
     * 微信 unionId 同开放平台，unionId 互通 TODO unionId 与unionid重复了
     */
    private String unionId;

    ///
    /// 注册信息

    /**
     * 注册渠道
     */
    private String registerChannel;
    /**
     * 注册方式 (1、app注册 2、QQ 注册 3、微信注册  4、微博注册 5、手机)
     */
    private Integer registerType;
    /**
     * 注册版本 2.0 加入
     */
    @Deprecated
    private Integer registerVersion;
    /**
     * 注册地区地区
     */
    private TaoBaoIp registerTaoBaoIp;
    /**
     * 注册平台 ( 1:app 2:ugc 3:跑步计划 4:社区)
     */
    private Integer registerPlatform;
    /**
     * 是否激活 APP (2:未激活 1:激活)
     */
    private Integer activatesApp;
    /**
     * 是否激活 UGC (2:未激活 1:激活)
     */
    private Integer activatesUgc;
    /**
     * 是否激活 RunPlan (2:未激活 1:激活)
     */
    private Integer activatesRunPlan;
    /**
     * 是否激活 BBS (2:未激活 1:激活)
     */
    private Integer activatesBBS;


    ///
    /// 三方推送 设备id

    /**
     * 友盟 推送  deviceToken
     */
    private String deviceToken;

    ///
    /// 用户真实信息

    /**
     * 用户真实信息
     */
    private UserRealInfo userRealInfo;

    /**
     * 平台身份信息
     */
    private UserIdentityInfo userIdentityInfo;

    /**
     * 环信帐号信息
     */
    @Transient
    private IMInfoUser imInfoUser;

    ///
    /// 其它

    /**
     * 创建时间
     */
    private Long addTime;
    /**
     * 万德游客帐号
     * 0 or null = 正式用户
     * 1=游客
     */
    private Integer isTourist;
    /**
     * iOS 游客 idfa
     */
    private String touristIdfa;
    /**
     * 安卓 游客 udid
     */
    private String touristUdid;

    private Integer isNew;
    /**
     * null或0、话题普通用户
     * 1、话题(认证用户)
     * {@link User#THEME_VIP_TRUE}
     */
    private Integer themeVip;

    /**
     * 用户跑步距离（总距离）
     * {@link UserRun}
     */
    @Transient
    private Long distance;
    /**
     * 步(总步数)
     */
    @Transient
    private Long step;
    /**
     * 运动时间(分)
     */
    @Transient
    private Long runTime;
    /**
     * 卡路里
     */
    @Transient
    private Long calorie;
    /**
     * 燃脂总量(运动消耗的脂肪总数量)
     */
    @Transient
    private Double consumeFatSum;

    /**
     * 最后运动 (累计总里程)
     */
    @Transient
    private UserRun lastRun;
    /**
     * 总跳绳 (累计总个数)
     */
    private UserRun sumSkipRope;

    /**
     * 国家/地区
     */
    private String area;

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public UserRun getLastRun() {
        return lastRun;
    }

    public void setLastRun(UserRun lastRun) {
        this.lastRun = lastRun;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public Long getCalorie() {
        return calorie;
    }

    public void setCalorie(Long calorie) {
        this.calorie = calorie;
    }

    public TaoBaoIp getTaoBaoIp() {
        return taoBaoIp;
    }

    public void setTaoBaoIp(TaoBaoIp taoBaoIp) {
        this.taoBaoIp = taoBaoIp;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getQqName() {
        return qqName;
    }

    public void setQqName(String qqName) {
        this.qqName = qqName;
    }

    public String getWbOpenid() {
        return wbOpenid;
    }

    public void setWbOpenid(String wbOpenid) {
        this.wbOpenid = wbOpenid;
    }

    public String getWbName() {
        return wbName;
    }

    public void setWbName(String wbName) {
        this.wbName = wbName;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public UserWXStep getLastSetWXStep() {
        return lastSetWXStep;
    }

    public void setLastSetWXStep(UserWXStep lastSetWXStep) {
        this.lastSetWXStep = lastSetWXStep;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMqOpenId() {
        return mqOpenId;
    }

    public void setMqOpenId(String mqOpenId) {
        this.mqOpenId = mqOpenId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRegisterChannel() {
        return registerChannel;
    }

    public void setRegisterChannel(String registerChannel) {
        this.registerChannel = registerChannel;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public Integer getRegisterVersion() {
        return registerVersion;
    }

    public void setRegisterVersion(Integer registerVersion) {
        this.registerVersion = registerVersion;
    }

    public TaoBaoIp getRegisterTaoBaoIp() {
        return registerTaoBaoIp;
    }

    public void setRegisterTaoBaoIp(TaoBaoIp registerTaoBaoIp) {
        this.registerTaoBaoIp = registerTaoBaoIp;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public UserRealInfo getUserRealInfo() {
        return userRealInfo;
    }

    public void setUserRealInfo(UserRealInfo userRealInfo) {
        this.userRealInfo = userRealInfo;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getRegisterPlatform() {
        return registerPlatform;
    }

    public void setRegisterPlatform(Integer registerPlatform) {
        this.registerPlatform = registerPlatform;
    }

    public Integer getActivatesApp() {
        return activatesApp;
    }

    public void setActivatesApp(Integer activatesApp) {
        this.activatesApp = activatesApp;
    }

    public Integer getActivatesUgc() {
        return activatesUgc;
    }

    public void setActivatesUgc(Integer activatesUgc) {
        this.activatesUgc = activatesUgc;
    }

    public Integer getActivatesRunPlan() {
        return activatesRunPlan;
    }

    public void setActivatesRunPlan(Integer activatesRunPlan) {
        this.activatesRunPlan = activatesRunPlan;
    }

    public Integer getActivatesBBS() {
        return activatesBBS;
    }

    public void setActivatesBBS(Integer activatesBBS) {
        this.activatesBBS = activatesBBS;
    }

    public Map<String, String> getBirthday() {
        return birthday;
    }

    public void setBirthday(Map<String, String> birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserIdentityInfo getUserIdentityInfo() {
        return userIdentityInfo;
    }

    public void setUserIdentityInfo(UserIdentityInfo userIdentityInfo) {
        this.userIdentityInfo = userIdentityInfo;
    }

    public UserRun getSumSkipRope() {
        return sumSkipRope;
    }

    public void setSumSkipRope(UserRun sumSkipRope) {
        this.sumSkipRope = sumSkipRope;
    }

    public UserHeartRate getLastRunHeartRate() {
        return lastRunHeartRate;
    }

    public void setLastRunHeartRate(UserHeartRate lastRunHeartRate) {
        this.lastRunHeartRate = lastRunHeartRate;
    }

    public UserHeartRate getLastSkipRopeHeartRate() {
        return lastSkipRopeHeartRate;
    }

    public void setLastSkipRopeHeartRate(UserHeartRate lastSkipRopeHeartRate) {
        this.lastSkipRopeHeartRate = lastSkipRopeHeartRate;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getIsTourist() {
        return isTourist;
    }

    public void setIsTourist(Integer isTourist) {
        this.isTourist = isTourist;
    }

    public String getTouristIdfa() {
        return touristIdfa;
    }

    public void setTouristIdfa(String touristIdfa) {
        this.touristIdfa = touristIdfa;
    }

    public String getTouristUdid() {
        return touristUdid;
    }

    public void setTouristUdid(String touristUdid) {
        this.touristUdid = touristUdid;
    }

    public IMInfoUser getImInfoUser() {
        return imInfoUser;
    }

    public void setImInfoUser(IMInfoUser imInfoUser) {
        this.imInfoUser = imInfoUser;
    }

    public Double getConsumeFatSum() {
        return consumeFatSum;
    }

    public void setConsumeFatSum(Double consumeFatSum) {
        this.consumeFatSum = consumeFatSum;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Long getNewId() {
        return newId;
    }

    public void setNewId(Long newId) {
        this.newId = newId;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public static class Build{

        public static User registerUser(String name, Integer gender, String mobile, String email, String password, Integer terminal, String channel, Integer registerType, TaoBaoIp taoBaoip) {
            User user = new User();

            user.setName(name);
            user.setGender(gender == null ? 1 : gender);
            user.setAge(0);
            user.setHeight(0.0);
            user.setWeight(0.0);
            user.setAvatar("");
            user.setType(User.TYPE_1);

            user.setMobile(StringUtils.isEmpty(mobile) ? null : mobile);
            /// 邮箱
            user.setEmail(StringUtils.isEmpty(email) ? null : email);
            /// 密码 （如果为空 生成随机密码）
            if (StringUtils.isEmpty(password)) {
                password = CommonUtil.genRandomNum(Constants.USER_PASSWORD_GEN_LENGTH);
            }

            user.setPassword(password);
            user.setNewPwd(MD5Util.MD5Encode(password, MD5Util.CHARSET_NAME_UTF));

            user.setLoginCount(0);
            user.setDownloadCount(0);
            user.setLastRun(null);
            user.setDistance(0L);
            user.setStep(0L);
            user.setRunTime(0L);
            user.setCalorie(0L);
            user.setState(User.STATE_ACTIVATES);
            user.setTaoBaoIp(taoBaoip);

            ///终端信息, 用户活跃 ， 最后登录信息
            user.setSdk("");
            user.setTerminal(terminal);
            user.setMobileType("");
            user.setOperators("");
            user.setChannel(channel);
            user.setLoginTime(System.currentTimeMillis());
            user.setLoginType(registerType);
            user.setVersion("");

            /// 三方登录信息
            user.setToken("");
            user.setOpenid("");
            user.setWbOpenid("");
            user.setWbName("");
            user.setWxOpenid("");
            user.setWxName("");
            user.setQqOpenid("");
            user.setQqName("");

            /// 第三方信息
            user.setDeviceToken("");

            /// 注册信息
            user.setRegisterChannel(channel);
            user.setRegisterType(registerType);
            user.setRegisterTaoBaoIp(taoBaoip);
            user.setAddTime(System.currentTimeMillis());

            return user;
        }
    }

    public Integer getThemeVip() {
        return themeVip;
    }

    public void setThemeVip(Integer themeVip) {
        this.themeVip = themeVip;
    }
}
