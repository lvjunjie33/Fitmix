package com.business.app.user;

import com.alibaba.fastjson.JSONObject;
import com.business.app.UserRunStatistics.UserRunStatisticsService;
import com.business.app.base.context.SystemContext;
import com.business.app.base.support.BaseServiceSupport;
import com.business.app.message.MessageDao;
import com.business.app.shop.account.AccountService;
import com.business.app.userRun.UserRunDao;
import com.business.app.userSkipRope.UserSkipRopeDao;
import com.business.core.client.*;
import com.business.core.constants.*;
import com.business.core.entity.SysParam;
import com.business.core.entity.TaoBaoIp;
import com.business.core.entity.logs.UserLoginLog;
import com.business.core.entity.logs.VerifyCodeLog;
import com.business.core.entity.user.Account;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRealInfo;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.*;
import com.business.core.operations.logs.UserLoginLogCoreDao;
import com.business.core.operations.logs.VerifyCodeCoreDao;
import com.business.core.operations.mail.MailCoreService;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.redis.RedisMsgManager;
import com.business.core.sms.SmsCoreService;
import com.business.core.utils.DateUtil;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.MD5Util;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by sin on 2015/4/16.
 */
@Service
public class UserService extends BaseServiceSupport {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserLoginLogCoreDao userLoginLogCoreDao;

    @Autowired
    private MailCoreService mailCoreService;
    @Autowired
    private UserCoreService userCoreService;
    @Autowired
    private SmsCoreService smsCoreService;
    @Autowired
    private UserRunDao userRunDao;
    @Autowired
    private UserSkipRopeDao userSkipRopeDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRunStatisticsService userRunStatisticsService;
    @Autowired
    private VerifyCodeCoreDao verifyCodeCoreDao;
    @Autowired
    private MessageDao messageDao;

    /**
     * 用户登录
     *
     * @param email    邮箱 or 手机号
     * @param password 密码
     * @return 0、成功 1、用户不存在 2、账号不可用,请联系客服 3、密码错误
     */
    public Object[] login(String email, String password, Integer version) {
        User user = null;
        Integer loginType = null;
        // 手机 邮箱 登录
        if (email.contains("@")) {//邮箱登录
            user = userCoreDao.findUserByEmail(email, User.BASIC_INFO_FIELDS);
            loginType = User.EMAIL;
        } else if (email.length() >= 11) {//手机号码登录
            user = userCoreDao.findUserByMobile(email, User.BASIC_INFO_FIELDS);
            loginType = User.MOBILE;
        } else {
            if (NumberUtils.isNumber(email)) {//Uid登录
                user = userCoreDao.findUserById(Integer.parseInt(email), User.BASIC_INFO_FIELDS);
                loginType = User.UID;
            }
        }

        if (user == null || loginType == null) {
            return new Object[]{1};
        }
        if (User.STATE_NO_ACTIVATES.equals(user.getState())) {
            return new Object[]{2};
        }
        Integer error = null;
        if (!StringUtils.isEmpty(user.getNewPwd())) {
            String pwd = MD5Util.MD5Encode(password, MD5Util.CHARSET_NAME_UTF);
            if (!user.getNewPwd().equals(pwd)) {
                error = 0;
            }
        } else if(!user.getPassword().equals(password)) {
            error = 0;
        }
        if (error != null) {
            if (!password.equals(user.getPassword())) {
                return new Object[]{3};
            }
        }

        updateUserLoginInfo(user, loginType, null, version); //  更新登录信息

        userCoreService.buildUserFileUrl(user);
        addLoginLog(user.getId(), user.getName(), User.EMAIL); //添加日志

        {
            //设置最后一次的心率数据
            UserRun userRun = userRunDao.findLastUpdateUserRun2(user.getId(), "heartRate");
            if (userRun != null && userRun.getHeartRate() != null) {
                user.setLastRunHeartRate(userRun.getHeartRate());
            } else {
                user.setLastRunHeartRate(null);
            }

            UserRun userSkipRun = userSkipRopeDao.findLastUpdateSkipRope2(user.getId(), "heartRate");
            if (userSkipRun != null && userSkipRun.getHeartRate() != null) {
                user.setLastSkipRopeHeartRate(userSkipRun.getHeartRate());
            } else {
                user.setLastSkipRopeHeartRate(null);
            }
        }
        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);

        return new Object[]{0, user};
    }


    /**
     * 官网用户登录
     *
     * @param email    邮箱 or 手机号
     * @param password 密码
     * @return 0、成功 1、用户不存在 2、账号不可用,请联系客服 3、密码错误
     */
    public Object[] loginGW(String email, String password, Integer version) {
        User user = null;
        Integer loginType = null;
        // 手机 邮箱 登录
        if (email.contains("@")) {//邮箱登录
            user = userCoreDao.findUserByEmail(email, User.BASIC_INFO_FIELDS_GW);
            loginType = User.EMAIL;
        } else if (email.length() >= 11) {//手机号码登录
            user = userCoreDao.findUserByMobile(email, User.BASIC_INFO_FIELDS_GW);
            loginType = User.MOBILE;
        } else {
            if (NumberUtils.isNumber(email)) {//Uid登录
                user = userCoreDao.findUserById(Integer.parseInt(email), User.BASIC_INFO_FIELDS_GW);
                loginType = User.UID;
            }
        }

        if (user == null || loginType == null) {
            return new Object[]{1};
        }
        if (User.STATE_NO_ACTIVATES.equals(user.getState())) {
            return new Object[]{2};
        }
        Integer error = null;
        if (!StringUtils.isEmpty(user.getNewPwd())) {
            String pwd = MD5Util.MD5Encode(password, MD5Util.CHARSET_NAME_UTF);
            if (!user.getNewPwd().equals(pwd)) {
                error = 0;
            }
        } else if(!user.getPassword().equals(password)) {
            error = 0;
        }
        if (error != null) {
            if (!password.equals(user.getPassword())) {
                return new Object[]{3};
            }
        }

        updateUserLoginInfo(user, loginType, null, version); //  更新登录信息

        userCoreService.buildUserFileUrl(user);
        addLoginLog(user.getId(), user.getName(), User.EMAIL); //添加日志

        {
            //设置最后一次的心率数据
            UserRun userRun = userRunDao.findLastUpdateUserRun2(user.getId(), "heartRate");
            if (userRun != null && userRun.getHeartRate() != null) {
                user.setLastRunHeartRate(userRun.getHeartRate());
            } else {
                user.setLastRunHeartRate(null);
            }

            UserRun userSkipRun = userSkipRopeDao.findLastUpdateSkipRope2(user.getId(), "heartRate");
            if (userSkipRun != null && userSkipRun.getHeartRate() != null) {
                user.setLastSkipRopeHeartRate(userSkipRun.getHeartRate());
            } else {
                user.setLastSkipRopeHeartRate(null);
            }
        }
        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);

        return new Object[]{0, user};
    }

    /**
     * QQ 登录
     *
     * @param token   QQ token
     * @param openid  QQ userId
     * @param channel 渠道
     * @return 0 成功
     */
    public Object[] loginQQ(String token, String openid, String channel, String ip, Integer version) {
        User user = userCoreDao.findUserByOpenidType(openid, User.QQ, User.BASIC_INFO_FIELDS);
        if (user == null) { // 没有则，注册

            // TODO 体育版 特殊处理
            JSONObject jsonObject;
            if (getTerminalType() == Constants.TERMINAL_IOS
                    && getContext().getVersion().equals(VersionConstants.IOS.VERSION_1.getVersion())) {
                jsonObject = QQCenterClient.getUserInfo(SysParam.INSTANCE.APP_LOGIN_QQ_LITE_KEY, token, openid);
            } else if (getContext().getSdk().equals("web")) {
                jsonObject = QQCenterClient.getUserInfo(SysParam.INSTANCE.WEB_LOGIN_QQ_KEY, token, openid);
            } else {
                jsonObject = QQCenterClient.getUserInfo(SysParam.INSTANCE.APP_LOGIN_QQ_KEY, token, openid);
            }

            String name = "";
            if (jsonObject.containsKey("nickname")) {
                name = jsonObject.get("nickname").toString();
            }

            user = User.Build.registerUser(name, null, null, null, null, User.QQ, channel, User.QQ, null);
            user.setToken(token);
            user.setOpenid(openid);
            user.setQqOpenid(openid);
            user.setQqName(name);
            user.setState(User.STATE_ACTIVATES);

            // 设置其他信息
            user.setName(name);
            user.setGender("男".equals(jsonObject.get("gender").toString()) ? 1 : 2);
            user.setRegisterTaoBaoIp(HttpUtil.ipArea(ip));
            user.setAvatar(jsonObject.get("figureurl_qq_2").toString());

            // 再次确定
            User baseUser = userCoreDao.findUserByOpenidType(openid, User.QQ, User.BASIC_INFO_FIELDS);
            if (baseUser == null) {
                userCoreDao.insertUser(user);
            } else {
                user = baseUser;
            }
        }
        // 登录
        user = updateUserLoginInfo(user, User.QQ, openid, version); //  更新登录信息
        addLoginLog(user.getId(), user.getName(), User.QQ); //添加日志
        userCoreService.buildUserFileUrl(user);

		// 创建用户金币账号信息
        iniAccountAndRunStatistics(user.getId());

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }


    /**
     * 官网微信登陆
     *
     * @param token   QQ token
     * @param openid  QQ userId
     * @param channel 渠道
     * @return 0 成功
     */
    public Object[] loginWXGW(String token, String openid, String channel, String ip, Integer version, String product) {
        User user = userCoreDao.findUserByOpenidType(openid, User.WX);
        if (user == null) { // 没有则，注册
            JSONObject jsonObject;
            if (UserDevice.PRODUCT_ROC.equals(product)) {
                jsonObject = WXCenterClient.userInfoROC(token, openid);
            } else {
                jsonObject = WXCenterClient.userInfo(token, openid);
            }

            String name = "";
            if (jsonObject.containsKey("nickname")) {
                name = jsonObject.get("nickname").toString();
            }

            user = User.Build.registerUser(name, null, null, null, null, User.WX, channel, User.WX, null);
            user.setToken(token);
            user.setOpenid(openid);
            user.setWxOpenid(openid);

            user.setWxName(name);
            user.setName(name);

            {//设置微信性别
                if(jsonObject.containsKey("sex")) {
                    user.setGender("1".equals(jsonObject.get("sex").toString()) ? 1 : 2);
                } else {
                    user.setGender(2);
                }
            }
            {//设置微信头像
                if (jsonObject.containsKey("headimgurl")) {
                    user.setAvatar(jsonObject.get("headimgurl").toString());
                }
            }
            user.setState(User.STATE_ACTIVATES);

            user.setRegisterTaoBaoIp(HttpUtil.ipArea(ip));

            // 再次确定
            User baseUser = userCoreDao.findUserByOpenidType(openid, User.WX);
            if (baseUser == null) {
                userCoreDao.insertUser(user);
            } else {
                user = baseUser;
            }
        }

//        user = updateUserLoginInfo(user, User.WX, openid, version); //  更新登录信息
        addLoginLog(user.getId(), user.getName(), User.WX); //添加日志
        userCoreService.buildUserFileUrl(user);

        // 创建用户金币账号信息
        iniAccountAndRunStatistics(user.getId());

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }

    /**
     * 微信登陆
     *
     * @param token   QQ token
     * @param openid  QQ userId
     * @param channel 渠道
     * @return 0 成功
     */
    public Object[] loginWX(String token, String openid, String channel, String ip, Integer version, String product) {
        User user = userCoreDao.findUserByOpenidType(openid, User.WX, User.BASIC_INFO_FIELDS);
        if (user == null) { // 没有则，注册
            JSONObject jsonObject;
            if (UserDevice.PRODUCT_ROC.equals(product)) {
                jsonObject = WXCenterClient.userInfoROC(token, openid);
            } else {
                jsonObject = WXCenterClient.userInfo(token, openid);
            }

            String name = "";
            if (jsonObject.containsKey("nickname")) {
                name = jsonObject.get("nickname").toString();
            }

            user = User.Build.registerUser(name, null, null, null, null, User.WX, channel, User.WX, null);
            user.setToken(token);
            user.setOpenid(openid);
            user.setWxOpenid(openid);

            user.setWxName(name);
            user.setName(name);

            {//设置微信性别
                if(jsonObject.containsKey("sex")) {
                    user.setGender("1".equals(jsonObject.get("sex").toString()) ? 1 : 2);
                } else {
                    user.setGender(2);
                }
            }
            {//设置微信头像
                if (jsonObject.containsKey("headimgurl")) {
                    user.setAvatar(jsonObject.get("headimgurl").toString());
                }
            }
            user.setState(User.STATE_ACTIVATES);

            user.setRegisterTaoBaoIp(HttpUtil.ipArea(ip));

            // 再次确定
            User baseUser = userCoreDao.findUserByOpenidType(openid, User.WX, User.BASIC_INFO_FIELDS);
            if (baseUser == null) {
                userCoreDao.insertUser(user);
            } else {
                user = baseUser;
            }
        }

        user = updateUserLoginInfo(user, User.WX, openid, version); //  更新登录信息
        addLoginLog(user.getId(), user.getName(), User.WX); //添加日志
        userCoreService.buildUserFileUrl(user);

		// 创建用户金币账号信息
        iniAccountAndRunStatistics(user.getId());

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }

    /**
     * 用户 微博登陆
     *
     * @param token   QQ token
     * @param openid  QQ userId
     * @param channel 渠道
     * @return 0 成功
     */
    public Object[] loginWB(String token, String openid, String channel, String ip, Integer version) {
        User user = userCoreDao.findUserByOpenidType(openid, User.WB, User.BASIC_INFO_FIELDS);
        if (user == null) { // 没有则，注册

            // TODO 体育版 特殊处理
            JSONObject jsonObject;
            if (getTerminalType() == Constants.TERMINAL_IOS
                    && getContext().getVersion().equals(VersionConstants.IOS.VERSION_1.getVersion())) {
                jsonObject = WBCenterClient.userInfo(SysParam.INSTANCE.APP_LOGIN_WB_LITE_KEY, token, openid);
            } else if (getContext().getSdk().equals("web")) {
                jsonObject = WBCenterClient.userInfo(SysParam.INSTANCE.WEB_LOGIN_WB_KEY, token, openid);
            } else {
                jsonObject = WBCenterClient.userInfo(SysParam.INSTANCE.APP_LOGIN_WB_KEY, token, openid);
            }

            String name = "";
            if (jsonObject.containsKey("name")) {
                name = jsonObject.get("name").toString();
            }

            user = User.Build.registerUser(name, null, null, null, null, User.WB, channel, User.WB, null);
            user.setToken(token);
            user.setOpenid(openid);
            user.setWbOpenid(openid);
            user.setState(User.STATE_ACTIVATES);
            user.setName(name);

            {// 设置其他信息

                if (jsonObject.containsKey("gender")) {
                    user.setGender("m".equals(jsonObject.get("gender").toString()) ? 1 : 2);
                }

                if (jsonObject.containsKey("avatar_hd")) {
                    user.setAvatar(jsonObject.get("avatar_hd").toString());
                }
            }

            user.setWbName(user.getName());

            user.setRegisterTaoBaoIp(HttpUtil.ipArea(ip));

            // 再次确定
            User baseUser = userCoreDao.findUserByOpenidType(openid, User.WB, User.BASIC_INFO_FIELDS);
            if (baseUser == null) {
                userCoreDao.insertUser(user);
            } else {
                user = baseUser;
            }
        }

        user = updateUserLoginInfo(user, User.WB, openid, version); //  更新登录信息
        addLoginLog(user.getId(), user.getName(), User.WB); //添加日志

        // 创建用户金币账号信息
        iniAccountAndRunStatistics(user.getId());

        userCoreService.buildUserFileUrl(user);

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }

    /**
     * 更新用户登录信息
     *
     * @param user       用户
     * @param loginType 登录类型
     *                  注意：此处还有另外一个方法会更新 {@link com.business.app.stat.StatController
     */
    public User updateUserLoginInfo(User user, Integer loginType, String openid, Integer currentVersion) {
        Integer uid = user.getId();
        String currentVersionStr = "";
        if (currentVersion != null) {
            currentVersionStr = currentVersion.toString();
        }
        Update update = Update.update("loginTime", System.currentTimeMillis()).inc("loginCount", 1).set("loginType", loginType).set("currentVersion", currentVersionStr);
        user.setLoginType(loginType);
        switch (loginType) {
            case User.QQ:
                update.set("qqOpenid", openid);
                break;
            case User.WX:
                update.set("wxOpenid", openid);
                break;
            case User.WB:
                update.set("wbOpenid", openid);
                break;
        }
        return userCoreDao.findAndModifyUserByIdNew(uid, update, User.BASIC_INFO_FIELDS);
    }

    /**
     * 添加用户登录日志
     *
     * @param uid      用户编号
     * @param name     用户名称
     * @param terminal 终端
     */
    public void addLoginLog(Integer uid, String name, Integer terminal) {
        String info;
        switch (terminal) {
            case 1: // User.EMAIL
                info = String.format("%s(%s) app 登录", name, uid);
                break;
            case 2: // User.QQ
                info = String.format("%s(%s) 第三方登录 (QQ)", name, uid);
                break;
            case 3: // User.WX
                info = String.format("%s(%s) 第三方登录 (WX)", name, uid);
                break;
            case 4: // User.WB
                info = String.format("%s(%s) 第三方登录 (WB)", name, uid);
                break;
            default:
                info = String.format("%s(%s) 未知登录", name, uid);
                break;
        }
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setTerminal(terminal);
        userLoginLog.setInfo(info);
        userLoginLog.setUid(uid);
        userLoginLog.setAddTime(System.currentTimeMillis());
        userLoginLogCoreDao.insertUserLoginLog(userLoginLog);
    }

    /**
     * TODO 用户登录（改为redis） sin to sin
     */
    public boolean doLogin(Integer uid) {
        SystemContext systemContext = getContext();
        // 获取 缓存在线用户
        User user = RedisConstants.cacheGetOnlineUser(uid);
        // 未登录。缓存
        if (user == null) {
            User baseUser = userCoreDao.findUserById(uid, User.BASIC_INFO_FIELDS);
            if (baseUser == null) {
                return false;
            }
            if (User.STATE_NO_ACTIVATES.equals(baseUser.getState())) {//未激活的用户，不允许登录
                return false;
            }

            // 用户是否存在
            RedisConstants.cacheOnlineUser(baseUser);

        }
        return true;
    }

    /**
     * 用户注册
     *
     * @param name     用户名称
     * @param email    电子邮箱
     * @param password 密码
     * @return 0 成功， 1 邮箱存在, 2 邮箱格式不正确, 3 密码长度 > 5
     */
    public synchronized Object[] register(String name, String email, String password, String channel, String ip, Integer version) {
        if (userCoreDao.findUserByEmail(email, "id") != null) {
            return new Object[]{1};
        }
        if (!email.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")) {
            return new Object[]{2};
        }
        if (password.length() < 6) {
            return new Object[]{3};
        }
        // name is null ,the's = email
        if (StringUtils.isEmpty(name)) {
            name = email;
        }

        // 设置默认值
        User user = User.Build.registerUser(name, DicConstants.DIC_VALUE_GENDER_MALE,
                null, email, password, User.EMAIL, channel, User.EMAIL, HttpUtil.ipArea(ip));
        // 添加数据库
        userCoreDao.insertUser(user);

        // 创建用户金币账号信息
        iniAccountAndRunStatistics(user.getId());

        //  更新登录信息
        user = updateUserLoginInfo(user, User.EMAIL, null, version);

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }

    /**
     * 万德游客 注册
     *
     * @param touristIdfa     iOSs设备唯一标识
     * @param touristUdid    安卓设备唯一标识
     */
    public Object[] touristDoLogin(Boolean isIOS, String touristIdfa, String touristUdid, String channel, String ip, Integer version) {
        User user = null;
        if (isIOS) {
            user = userDao.findUserByTouristIdfa(touristIdfa);
        } else {
            user = userDao.findUserByTouristUdid(touristUdid);
        }

        if (user == null) {
            //注册新的游客用户
            user = User.Build.registerUser("游客", DicConstants.DIC_VALUE_GENDER_MALE,
                    null, null, null, User.TOURIST, channel, User.TOURIST, HttpUtil.ipArea(ip));
            user.setTouristIdfa(touristIdfa);
            user.setTouristUdid(touristUdid);
            user.setIsTourist(User.IS_TOURIST_TRUE);
            // 添加数据库
            userCoreDao.insertUser(user);
        } else {
//            if (user.getIsTourist() != null && !User.IS_TOURIST_TRUE.equals(user.getIsTourist())) {
//                return new Object[]{1};
//            }
            //游客登录 or 转化为正式用户的游客登录
        }

        //  更新登录信息
        user = updateUserLoginInfo(user, User.TOURIST, null, version);

        addLoginLog(user.getId(), user.getName(), User.TOURIST); //添加日志

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }

    /**
     * 用户信息修改
     *
     * @param uid       用户编号
     * @param name      用户名 :1.0.8版本开始
     * @param gender    性别
     * @param age       年龄
     * @param height    身高
     * @param weight    体重
     * @param type      类型（kg/cm, ib/in）
     * @param signature 个性签名（kg/cm, ib/in）
     * @return 0 成功， 1 年龄大于0, 2 性别不正确, 3 用户类型不正确
     */
    public int userInfoModify(Integer uid, String name, Integer gender, Integer age,
                              Double height, Double weight, Integer type, String url, String signature) {
        User user = userDao.findUserById(uid, "avatar");
        if (user == null) {
            return 4;
        }

        Update update = new Update();
        if (!StringUtils.isEmpty(url)) {
            if (!StringUtils.isEmpty(user.getAvatar())) {
                AliyunCenterClient.deleteFile(user.getAvatar());
            }
            update.set("avatar", url);
        }
        if (!StringUtils.isEmpty(name)) {
            update.set("name", name);
        }
        if (!StringUtils.isEmpty(signature)) {
            update.set("signature", signature);
        }
        User userNew = userCoreDao.findAndModifyById(uid,
                update.set("gender", gender).
                        set("age", age).
                        set("height", height).
                        set("weight", weight).
                        set("type", type),
                User.BASIC_INFO_FIELDS);

        userCoreService.buildUserFileUrl(userNew);
        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(userNew);
        return 0;
    }




    /**
     * 用户找回密码
     *
     * @param email 邮件
     * @return 0 成功， 1 邮箱不存在， 2 用户不可用
     */
    public int mailRecoveryPassword(String email) {
        User user = userCoreDao.findUserByEmail(email, "password", "state", "name");
        if (user == null) {
            return 1;
        }
        mailCoreService.sendMail1(email, user.getName(), user.getPassword());
        return 0;
    }

    /**
     * 修改密码
     *
     * @param uid    用户编号
     * @param oldPwd 旧密码
     * @param nowPwd 现在密码
     * @return 0、成功 1、用户不存在 2、非 app 注册用户不能修改密码 3、密码错误 4、新密码长度不能小于 5
     */
    public int modifyPassword(Integer uid, String oldPwd, String nowPwd) {
        User user = userDao.findUserById(uid, "newPwd");
        // 用户不存在
        if (user == null) {
            return 1;
        }
        // 非 app 注册用户不能修改密码
//        if (null != user.getRegisterType() && !user.getRegisterType().equals(User.EMAIL)) {
//            return 2;
//        }

        // 密码错误
        if (!user.getNewPwd().equals(MD5Util.MD5Encode(oldPwd, MD5Util.CHARSET_NAME_UTF))) {
            return 3;
        }
        // 新密码长度不能小于 5
        if (nowPwd.length() < 6) {
            return 4;
        }
        // 更新用户信息
        User newUser = userCoreDao.findAndModifyById(uid, Update.update("newPwd", MD5Util.MD5Encode(nowPwd, MD5Util.CHARSET_NAME_UTF)), User.BASIC_INFO_FIELDS);

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(newUser);
        return 0;
    }

    /**
     * 用户最后运动记录
     *
     * @param uid 用户编号
     * @return User 用户信息 和 最后一次运动信息
     */
    public User lastRun(Integer uid) {
        UserRun lastRun = userRunDao.findLastUpdateUserRun(uid);
        User user = userCoreDao.findUserById(uid, "id", "name", "avatar");
        user.setLastRun(lastRun);

        userCoreService.buildUserFileUrl(user);
        return user;
    }


    /**
     * 修改真实信息
     *
     * @param name           名称
     * @param gender         性别
     * @param age            年龄
     * @param bloodType      血型
     * @param clothesSize    衣服尺码
     * @param idCard         身份证
     * @param mobilePhone    移动电话
     * @param emergencyPhone 紧急联系人电话
     * @param emergencyName  紧急联系人名称
     */
    public void modifyRealInfo(Integer uid, String name, Integer gender, Integer age,
                               String bloodType, String clothesSize, String idCard, String email,
                               String mobilePhone, String emergencyPhone, String emergencyName,
                               String country, String region, String city, String detail) {

        UserRealInfo userRealInfo = new UserRealInfo();
        userRealInfo.setName(name);
        userRealInfo.setGender(gender);
        userRealInfo.setAge(age);
        userRealInfo.setBloodType(bloodType);
        userRealInfo.setClothesSize(clothesSize);
        userRealInfo.setIdCard(idCard);
        userRealInfo.setEmail(email);
        userRealInfo.setMobilePhone(mobilePhone);
        userRealInfo.setEmergencyPhone(emergencyPhone);
        userRealInfo.setEmergencyName(emergencyName);

        userRealInfo.setCountry(country);
        userRealInfo.setRegion(region);
        userRealInfo.setCity(city);
        userRealInfo.setDetail(detail);

        User userNew = userCoreDao.findAndModifyById(uid, Update.update("userRealInfo", userRealInfo), User.BASIC_INFO_FIELDS);

        // 更新 缓存
        RedisConstants.cacheOnlineUser(userNew);
    }


    ///
    /// 手机号注册

    /**
     * 发送 验证码 TODO 改成消息队列推送
     *
     * @param mobile 手机号
     * @return 0、成功 1、手机号已被注册
     */
    public Object[] mobileRegisterCode(HttpServletRequest request, String mobile) {
        String ch = HttpUtil.getParameter(request, "_ch");
        if ("wande".equals(ch)) {
            return new Object[]{0, smsCoreService.sendWDVerificationCode(mobile, null)};
        }
        String code = smsCoreService.sendVerificationCode(mobile, null);
        return new Object[]{0, code};
    }

    /**
     * 手机号 注册用户
     *
     * @param mobile   移动电话
     * @param password 用户密码
     * @param channel  渠道
     * @param ip       ip地址
     * @return 用户信息 0、成功 1、手机号注册
     */
    public synchronized Object[] mobileRegister(String mobile, String password, String channel, String ip, Integer version) {

        if (userCoreDao.findUserByMobile(mobile, "id") != null) {
            return new Object[]{1};
        }

        if (password.length() < 6) {
            return new Object[]{2};
        }

        User user = User.Build.registerUser(mobile, DicConstants.DIC_VALUE_GENDER_MALE, mobile, null, password, User.EMAIL, channel, User.MOBILE, HttpUtil.ipArea(ip)); // 设置默认值
        userCoreDao.insertUser(user);

        user.setPassword(null);
        user.setAddTime(null);

        // 创建用户金币账号信息
        accountService.createAccount(user.getId());
        userRunStatisticsService.createUserRunStatistics(user.getId());
        //  更新登录信息
        user = updateUserLoginInfo(user, User.MOBILE, null, version);

        // 创建用户金币账号信息
        iniAccountAndRunStatistics(user.getId());

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);

        return new Object[]{0, user};
    }

    /**
     * 更换 手机号
     *
     * @param uid       用户编号
     * @param newMobile 更换手机号
     */
    public void replaceMobile(Integer uid, String newMobile) {
        userCoreDao.updateUserById(uid, Update.update("mobile", newMobile));
    }

    /**
     * 手机 重置 面膜
     *
     * @param mobile      手机号
     * @param newPassword 新密码
     * @return 0、成功 1、用户不存在
     */
    public int mobileResetPassword(String mobile, String newPassword) {
        User user = userCoreDao.findUserByMobile(mobile, "id");
        if (user == null) {
            return 1;
        }
        userCoreDao.updateUserById(user.getId(), Update.update("password", newPassword));
        return 0;
    }

    /**
     * 绑定 用户账号
     *
     * @param uid            用户编号
     * @param bindingContent 绑定内容 ：微信 微博 QQ （openid） 和 手机、邮箱
     * @param bindingName    绑定 名称 仅此 第三方有效 （2、QQ 注册 3、微信注册  4、微博注册）
     * @param type           1、app注册 2、QQ 注册 3、微信注册  4、微博注册 5、手机
     * @return 0、成功 1、用户不存在 2、已是乐享动账号不能绑定 3、 当前登录账号，不能解绑
     */
    public int binding(Integer uid, String bindingContent, String bindingName, String password, Integer type) {
        User user = userDao.findUserById(uid);
        // 用户不存在
        if (user == null) {
            return 1;
        }

        Update update = new Update();
        if (StringUtils.isEmpty(bindingContent)) {
            // 当前登录账号，不能解绑
//            if (type.equals(user.getLoginType())) {
//                return 3;
//            }

            // 处理 openid 任意一个解绑 都需要解绑 openid
            if (type.equals(User.QQ) || type.equals(User.WB) || type.equals(User.WX)) {
                update.set("openid", "");
            }

            //清除unionId
            if (type.equals(User.WX)) {
                update.set("unionid", "");
            }

        } else {
            // 已是乐享动账号不能绑定
            if (userCoreDao.findUserByOpenidType(bindingContent, type, "id") != null) {
                return 2;
            }
        }

        if (type.equals(User.EMAIL)) {
            update.set("email", bindingContent);
            user.setEmail(bindingContent);
        } else if (type.equals(User.MOBILE)) {
            update.set("mobile", bindingContent);
            user.setMobile(bindingContent);
        } else if (type.equals(User.QQ)) {
            update.set("qqOpenid", bindingContent).set("qqName", bindingName);
            user.setQqOpenid(bindingContent);
            user.setQqName(bindingName);
        } else if (type.equals(User.WX)) {
            update.set("wxOpenid", bindingContent).set("wxName", bindingName);
            user.setWxOpenid(bindingContent);
            user.setWxName(bindingName);
        } else if (type.equals(User.WB)) {
            update.set("wbOpenid", bindingContent).set("wbName", bindingName);
            user.setWbOpenid(bindingContent);
            user.setWbName(bindingName);
        }
        // 如果存在密码，如果有密码 设置密码
        if (!StringUtils.isEmpty(password)) {
            update.set("password", password.trim());
            user.setPassword(password.trim());
        }

        // 如果是游客，进行邮箱、手机绑定则升级为正式用户
        if (!StringUtils.isEmpty(user.getIsTourist()) && User.IS_TOURIST_TRUE.equals(user.getIsTourist())) {
            update.set("isTourist", User.IS_TOURIST_FALSE);
        }
        userCoreDao.updateUserById(uid, update);

        // 更新 缓存
        RedisConstants.cacheOnlineUser(user);
        return 0;
    }

    public User findUserById(Integer uid, String ... fields) {
        return userCoreDao.findUserById(uid, fields);
    }

    /**
     * 创建用户金币账号信息
     *
     * @param uid 用户编号
     */
    private void iniAccountAndRunStatistics(Integer uid) {
        Account account = accountService.selectByUid(uid);
        if (account == null) {
            //创建用户账号 跑步统计记录
            accountService.createAccount(uid);
            userRunStatisticsService.createUserRunStatistics(uid);
        }
    }

    //*******************************************新的注册与修改******************************************************************//

    /**
     * 邮箱注册
     *
     * @param name     用户名称
     * @param email    电子邮箱
     * @param password 密码
     * @return 0 成功， 1 邮箱存在, 2 邮箱格式不正确, 3 密码长度 > 5
     */
    public synchronized Object[] emailRegisterNew(String name, String email, String password, String channel, String ip, Integer version, String verifyCode) {
        if (userCoreDao.findUserByEmail(email, "id") != null) {
            return new Object[]{1};
        }

        if (!StringUtils.isEmpty(verifyCode)) {
            VerifyCodeLog verifyCodeLog = verifyCodeCoreDao.findVerifyCodeByMail(email);
            if (checkedVerifyCodeEmail(verifyCodeLog, verifyCode)) {
                return new Object[]{2};
            }
        }

        // 设置默认值
        User user = User.Build.registerUser(name, DicConstants.DIC_VALUE_GENDER_MALE, null, email, password, User.EMAIL, channel, User.EMAIL, HttpUtil.ipArea(ip));
        // 添加数据库
        userCoreDao.insertUser(user);

        // 创建用户金币账号信息
        iniAccountAndRunStatistics(user.getId());

        //  更新登录信息
        user = updateUserLoginInfo(user, User.EMAIL, null, version);

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }

    /**
     * 手机号 注册用户
     *
     * @param mobile   移动电话
     * @param password 用户密码
     * @param channel  渠道
     * @param ip       ip地址
     * @return 用户信息 0、成功 1、手机号注册
     */
    public synchronized Object[] mobileRegisterNew(String name, String mobile, String password, String verifyCode, String channel, String ip, Integer version) {

        if (userCoreDao.findUserByMobile(mobile, "id") != null) {
            return new Object[]{1};
        }

        if (!StringUtils.isEmpty(verifyCode)) {
            VerifyCodeLog verifyCodeLog = verifyCodeCoreDao.findVerifyCodeByMobile(mobile);
            if (checkedVerifyCode(verifyCodeLog, verifyCode)) {
                return new Object[]{2};
            }
        }

        User user = User.Build.registerUser(name, DicConstants.DIC_VALUE_GENDER_MALE, mobile, null, password, User.MOBILE, channel, User.MOBILE, HttpUtil.ipArea(ip)); // 设置默认值
        userCoreDao.insertUser(user);

        user.setPassword(null);
        user.setAddTime(null);

        // 创建用户金币账号信息
        accountService.createAccount(user.getId());
        userRunStatisticsService.createUserRunStatistics(user.getId());
        //  更新登录信息
        user = updateUserLoginInfo(user, User.MOBILE, null, version);

        // 创建用户金币账号信息
        iniAccountAndRunStatistics(user.getId());

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);

        return new Object[]{0, user};
    }

    /**
     * 邮箱修改密码
     *
     * @param email 邮箱
     * @param verifyCode 验证码
     * @param newPwd 新密码
     */
    public Integer emailResetPassword(String email, String verifyCode, String newPwd) {
        VerifyCodeLog verifyCodeLog = verifyCodeCoreDao.findVerifyCodeByMail(email);
        if (checkedVerifyCodeEmail(verifyCodeLog, verifyCode)) {
            return 1;
        }

        User user = userCoreDao.findUserByEmail(email, "name");
        if (user == null) {
            return 2;
        }
        userCoreDao.updateUserById(user.getId(), Update.update("newPwd", MD5Util.MD5Encode(newPwd, MD5Util.CHARSET_NAME_UTF)));
        return 0;
    }

    /**
     * 手机 重置 面膜
     *
     * @param mobile      手机号
     * @param newPassword 新密码
     * @return 0、成功 1、用户不存在
     */
    public int mobileResetPasswordNew(String mobile, String verifyCode, String newPassword) {
        VerifyCodeLog verifyCodeLog = verifyCodeCoreDao.findVerifyCodeByMobile(mobile);
        if (checkedVerifyCode(verifyCodeLog, verifyCode)) {
            return 1;
        }
        User user = userCoreDao.findUserByMobile(mobile, "id");
        if (user == null) {
            return 2;
        }
        userCoreDao.updateUserById(user.getId(), Update.update("newPwd", MD5Util.MD5Encode(newPassword, MD5Util.CHARSET_NAME_UTF)));
        return 0;
    }

    /**
     * 验证码 校验
     *
     * @param verifyCodeLog 验证码
     * @param verifyCode 输入值
     */
    private boolean checkedVerifyCode(VerifyCodeLog verifyCodeLog, String verifyCode) {
        if (verifyCodeLog == null) {
            return true;
        }
        //十分钟有效期
        if (System.currentTimeMillis() - verifyCodeLog.getAddTime() > 1000 * 60 * 10) {
            return true;
        }

        if (!verifyCode.equals(verifyCodeLog.getCode())) {
            return true;
        }
        return false;
    }

    /**
     * 验证码 校验(邮箱)
     *
     * @param verifyCodeLog 验证码
     * @param verifyCode 输入值
     */
    private boolean checkedVerifyCodeEmail(VerifyCodeLog verifyCodeLog, String verifyCode) {
        if (verifyCodeLog == null) {
            return true;
        }
        //十分钟有效期
        if (System.currentTimeMillis() - verifyCodeLog.getAddTime() > 1000 * 60 * 10) {
            return true;
        }

        if (!verifyCode.equals(verifyCodeLog.getCode())) {
            return true;
        }
        return false;
    }

    /**
     * 上传用户app device 活跃信息
     *
     * @param device 设备
     */
    public Long uploadDeviceStatus(UserDevice device) {
        userDao.modifyUserDeviceByToken(device.getDeviceToken(), new Update().unset("deviceToken"));

        UserDevice oldUserDevice = userDao.findUserDeviceByUid(device.getUid());
        if (oldUserDevice == null) {
            device.setAddTime(System.currentTimeMillis());
            device.setModifyTime(device.getAddTime());
            userDao.saveUserDevice(device);
        } else {
//            if (StringUtils.isEmpty(device.getDeviceToken())) {
//                User user = userDao.findUserById(device.getUid(), "deviceToken", "terminal");
//                device.setDeviceToken(user.getDeviceToken());
//                device.setTerminal(user.getTerminal());
//            }
            Update update = Update.update("active", device.getActive()).set("sdk", device.getSdk()).set("deviceToken", device.getDeviceToken())
                    .set("terminal", device.getTerminal()).set("product", device.getProduct())
                    .set("currentVersion", device.getCurrentVersion()).set("modifyTime", System.currentTimeMillis());
            userDao.modifyUserDeviceByUid(device.getUid(), update);
        }

        if (UserDevice.ACTIVE_TRUE.equals(device.getActive())) {

            //通知红点
            Long userMsgCount = messageDao.findNewMsgCountByUid(device.getUid());

            List<UserGroup> userGroups = messageDao.findUserGroupByUid(device.getUid(), UserGroup.TYPE_USER_PRIVATE, "uid");
            Set<String> groupIds = new HashSet<>();
            for (UserGroup userGroup : userGroups) {
                groupIds.add(userGroup.getId().toString());
            }
            Long groupMsgCount = messageDao.findNewMsgCountByGroupId(groupIds, device.getUid());
            return (userMsgCount + groupMsgCount);
        }
        return 0L;
    }

    /**
     * 浏览他人主页
     *
     * @param uid 用户编号
     * @param targetUid 目标用户编号
     */
    public Object[] browseHomepage(Integer uid, Integer targetUid) {
        UserRunStat stat = userRunDao.findUserRunStatByUid(targetUid, UserRunStat.STAT_MONTH, DateUtil.format(DateUtil.getMonthBegin(new Date()), "yyyy-MM-dd"), UserRunStat.BASIC_INFO_FIELDS_SUM);//本月运动数据
        if (stat == null) {
            stat = new UserRunStat();
            buildUserGroup(stat, uid);
        }

        UserRunStat allStat = userRunDao.findUserRunStatByUid(targetUid, UserRunStat.STAT_SUM, "--", UserRunStat.BASIC_INFO_FIELDS_SUM);//所有运动数据
        if (allStat == null) {
            allStat = new UserRunStat();
            buildUserGroup(allStat, uid);
        } else {
            Long runDay = userRunDao.findUserRunDay(targetUid);
            if (runDay != null) {
                allStat.setRunDay(Integer.parseInt(runDay.toString()));
            }
        }

        UserGroup userGroup;
        {
            List<Integer> uids = new ArrayList<>();
            uids.add(uid);
            uids.add(targetUid);

            //消息接收方与发送者的关系
            userGroup = messageDao.findUserGroupByMember(uid, targetUid, UserGroup.TYPE_USER_PRIVATE, "lastContent");
            if (userGroup != null) {
                UserGroupRelation userGroupRelation = messageDao.findUserGroupRelationByGroupId(userGroup.getId(), uid, "reject");
                userGroup.setReject(userGroupRelation.getReject());
            }
        }

        User targetUser = userCoreDao.findUserById(targetUid, "name", "avatar", "signature", "gender", "taoBaoIp.city", "taoBaoIp.region");
        targetUser.setAvatar(FileCenterClient.buildUrl(targetUser.getAvatar()));
        return new Object[] {stat, allStat, targetUser, userGroup};
    }

    private void buildUserGroup(UserRunStat stat, Integer uid) {
        stat.setUid(uid);
        stat.setSumStep(0L);
        stat.setSumDistance(0L);
        stat.setSumCalorie(0L);
        stat.setRunNum(0);
        stat.setRunDay(0);
        stat.setRunTime(0L);
    }

    /**
     * 保存用户智能设备信息
     *
     * @param uid 用户编号
     * @param type 类型
     * @param info 智能设备信息
     */
    public int uploadSmartDevice(Integer uid, Integer type, String[] keys, String key, Map<String, Object> info) {
        SmartDevice smartDevice = userDao.findSmartDeviceByType(type, keys[1] + "_" + keys[2]);
        if (smartDevice != null) {
            return 2;
        }
        smartDevice = userDao.findSmartDeviceByUIdAndType(uid,type, keys[2]);
        if (smartDevice == null) {
            SmartDevice s = new SmartDevice();
            s.setType(type);
            s.setUid(uid);
            s.setDeviceInfo(info);
            s.setAddTime(System.currentTimeMillis());
            s.setKey(key);
            s.setStatus(Constants.STATUS_YES);
            userDao.saveSmartDevice(s);
            return 1;
        }
        return 3;
    }

    /**
     * 删除设备
     *
     * @param uid 用户编号
     * @param type 设备类型
     * @param key 唯一标识
     */
    public int removeSmartDevice(Integer uid, Integer type, String key) {
        String[] keys = key.split("_");
        SmartDevice smartDevice = userDao.findSmartDeviceByUIdAndType(uid, type, keys[1] + "_" + keys[2]);
        if (smartDevice == null) {// 用户未绑定设备
            return 2;
        }
        smartDevice = userDao.findSmartDeviceByUIdAndType(uid, type, keys[2]);
        if (smartDevice == null) {// 设备不存在
            return 1;
        }
        userDao.removeSmartDevice(uid, type, key);
        return 0;
    }

    /**
     * 更新用户ip信息
     *
     * @param ip ip地址
     * @param uid 用户编号
     */
    public void modifyUserIp(TaoBaoIp ip, Integer uid) {
        userCoreDao.updateUserById(uid, Update.update("taoBaoIp", ip));
    }

    /**
     * 拉取用户设备信息列表
     *
     * @param uid 用户编号
     */
    public List<SmartDevice> getSmartDevice(Integer uid) {
        return userDao.getSmartDevice(uid);
    }
    /***
     * 发送异步消息
     * */
    public void sendAsyMessage(String ip,User user){
        RedisMsgManager.sendUserLoginTask(ip,user);
    }




    /**
     * 手机号 注册用户
     *
     * @param mobile   移动电话
     * @param password 用户密码
     * @param channel  渠道
     * @param ip       ip地址
     * @return 用户信息 0、成功 1、手机号注册s
     */
    public synchronized Object[] mobileRegisterNews(String name, String mobile, String password,String channel, String ip, Integer version) {

        User user = User.Build.registerUser(name, DicConstants.DIC_VALUE_GENDER_MALE, mobile, null, password, User.MOBILE, channel, User.MOBILE, HttpUtil.ipArea(ip)); // 设置默认值
        userCoreDao.insertUser(user);

        user.setPassword(null);
        user.setAddTime(null);

        // 创建用户金币账号信息
        accountService.createAccount(user.getId());
        userRunStatisticsService.createUserRunStatistics(user.getId());
        //  更新登录信息
        user = updateUserLoginInfo(user, User.MOBILE, null, version);

        // 创建用户金币账号信息
        iniAccountAndRunStatistics(user.getId());

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);

        return new Object[]{0, user};
    }
    /**
     * 用户手机注册校验手机号是否存在
     *
     * @param mobile   手机号
     */
    public synchronized Map<String, Object> mobileRegisterIsExistl(String mobile) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (userCoreDao.findUserByMobile(mobile, "id") == null) {
            modelMap.put("success", true);
        }else{
            modelMap.put("success", false);
        }
        return modelMap;
    }


    /**
     * 用户手机注册校验验证码
     *
     * @param mobile   手机号
     * @param verifyCode  验证码
     */
    public synchronized Map<String, Object> mobileRegisterVerifyCodes(String mobile,String verifyCode) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(verifyCode)) {
            VerifyCodeLog verifyCodeLog = verifyCodeCoreDao.findVerifyCodeByMobile(mobile);
            if (checkedVerifyCode(verifyCodeLog, verifyCode)) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
            }
        }
        return modelMap;
    }

    /**
     * 邮箱注册
     *
     * @param name     用户名称
     * @param email    电子邮箱
     * @param password 密码
     * @return 0 成功， 1 邮箱存在, 2 邮箱格式不正确, 3 密码长度 > 5
     */
    public synchronized Object[] emailRegisterNews(String name, String email, String password, String channel, String ip, Integer version) {
        if (userCoreDao.findUserByEmail(email, "id") != null) {
            return new Object[]{1};
        }

//        if (!StringUtils.isEmpty(verifyCode)) {
//            VerifyCodeLog verifyCodeLog = verifyCodeCoreDao.findVerifyCodeByMail(email);
//            if (checkedVerifyCodeEmail(verifyCodeLog, verifyCode)) {
//                return new Object[]{2};
//            }
//        }

        // 设置默认值
        User user = User.Build.registerUser(name, DicConstants.DIC_VALUE_GENDER_MALE, null, email, password, User.EMAIL, channel, User.EMAIL, HttpUtil.ipArea(ip));
        // 添加数据库
        userCoreDao.insertUser(user);

        // 创建用户金币账号信息
        iniAccountAndRunStatistics(user.getId());

        //  更新登录信息
        user = updateUserLoginInfo(user, User.EMAIL, null, version);

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }


    /**
     * 用户信息修改
     *
     * @param uid       用户编号
     * @param name      用户名 :1.0.8版本开始
     * @param gender    性别
     * @param age       年龄
     * @param height    身高
     * @param weight    体重
     * @param type      类型（kg/cm, ib/in）
     * @param signature 个性签名（kg/cm, ib/in）
     * @return 0 成功， 1 年龄大于0, 2 性别不正确, 3 用户类型不正确
     */
    public int userInfoModifyNew(Integer uid, String name, Integer gender, Integer age,
                              Double height, Double weight, Integer type, String url, String signature,String areaCode) {
        User user = userDao.findUserById(uid, "avatar");
        if (user == null) {
            return 4;
        }

        Update update = new Update();
        if (!StringUtils.isEmpty(url)) {
            if (!StringUtils.isEmpty(user.getAvatar())) {
                AliyunCenterClient.deleteFile(user.getAvatar());
            }
            update.set("avatar", url);
        }
        if (!StringUtils.isEmpty(name)) {
            update.set("name", name);
        }
        if (!StringUtils.isEmpty(signature)) {
            update.set("signature", signature);
        }
        User userNew = userCoreDao.findAndModifyById(uid,
                update.set("gender", gender).
                        set("age", age).
                        set("height", height).
                        set("weight", weight).
                        set("type", type).
                        set("area", areaCode),
                User.BASIC_INFO_FIELDS);

        userCoreService.buildUserFileUrl(userNew);
        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(userNew);
        return 0;
    }
}
