package com.business.bbs.user;

import com.alibaba.fastjson.JSONObject;
import com.business.bbs.base.support.AbstractDao;
import com.business.bbs.base.support.AbstractService;
import com.business.core.client.QQCenterClient;
import com.business.core.client.WBCenterClient;
import com.business.core.client.WXCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.DicConstants;
import com.business.core.constants.RedisConstants;
import com.business.core.constants.VersionConstants;
import com.business.core.entity.SysParam;
import com.business.core.entity.logs.UserLoginLog;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.operations.logs.UserLoginLogCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.sms.SmsCoreService;
import com.business.core.utils.HttpUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by weird on 2016/10/8.
 */
@Service
public class UserService extends AbstractService<User> {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserCoreService userCoreService;
    @Autowired
    private UserLoginLogCoreDao userLoginLogCoreDao;
    @Autowired
    private SmsCoreService smsCoreService;


    @Override
    protected AbstractDao<User> getAbstractDao() {
        return userDao;
    }

    public Object[] login(String email, String password) {
        User user;
        // 手机 邮箱 登录
        if (email.indexOf("@") != -1) {
            user = userCoreDao.findUserByEmail(email, User.BASIC_INFO_FIELDS);
            if (user != null) {
                updateUserLoginInfo(user.getId(), User.EMAIL, null); //  更新登录信息
            }
        } else {
            // uid 判断uid是否在Integer max_value的范围内
            if (NumberUtils.isNumber(email) && email.length() <= 10 && Long.parseLong(email) <= Integer.MAX_VALUE) {
                user = userCoreDao.findUserById(Integer.parseInt(email), User.BASIC_INFO_FIELDS);
                if (user != null) {
                    updateUserLoginInfo(user.getId(), User.UID, null); //  更新登录信息
                }
            } else { // 手机
                user = userCoreDao.findUserByMobile(email, User.BASIC_INFO_FIELDS);
                if (user != null) {
                    updateUserLoginInfo(user.getId(), User.MOBILE, null); //  更新登录信息
                }
            }
        }

        if (user == null) {
            return new Object[]{1};
        }
        if (user.getState().equals(User.STATE_NO_ACTIVATES)) {
            return new Object[]{2};
        }
        if (!user.getPassword().equals(password)) {
            return new Object[]{3};
        }

        userCoreService.buildUserFileUrl(user);
        addLoginLog(user.getId(), user.getName(), User.EMAIL); //添加日志

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);

        return new Object[]{0, user};
    }

    /**
     * 更新用户登录信息
     *
     * @param uid       用户编号
     * @param loginType 登录类型
     *
     */
    public User updateUserLoginInfo(Integer uid, Integer loginType, String openid) {
        Update update = Update.update("loginTime", System.currentTimeMillis()).inc("loginCount", 1).set("loginType", loginType);

        switch (loginType) {
            case User.WX:
                update.set("wxOpenid", openid);
                break;
            case User.QQ:
                update.set("qqOpenid", openid);
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
        userLoginLog.setUid(uid);
        userLoginLog.setInfo(info);
        userLoginLog.setAddTime(System.currentTimeMillis());
        userLoginLogCoreDao.insertUserLoginLog(userLoginLog);
    }

    /**
     * 手机号 注册用户
     *
     * @param mobile   移动电话
     * @param password 用户密码
     * @param ip       ip地址
     * @return 用户信息 0、成功 1、手机号注册
     */
    public Object[] mobileRegister(String mobile, String password, String ip) {

        if (userCoreDao.findUserByMobile(mobile, "id") != null) {
            return new Object[]{1};
        }

        User user = User.Build.registerUser(mobile, DicConstants.DIC_VALUE_GENDER_MALE,
                null, password,null,User.EMAIL, null, User.MOBILE, HttpUtil.ipArea(ip)); // 设置默认值
        user.setMobile(mobile);
        userCoreDao.insertUser(user);

        user.setPassword(null);
        user.setAddTime(null);

        //  更新登录信息
        user = updateUserLoginInfo(user.getId(), User.MOBILE, null);

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);

        return new Object[]{0, user};
    }

    /**
     * 用户注册
     *
     * @param name     用户名称
     * @param email    电子邮箱
     * @param password 密码
     * @return 0 成功， 1 邮箱存在, 2 邮箱格式不正确, 3 密码长度 > 5
     */
    public Object[] register(String name, String email, String password, String ip) {
        if (userCoreDao.findUserByEmail(email, "id") != null) {
            return new Object[]{1};
        }
        if (!email.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")) {
            return new Object[]{2};
        }
        if (password.length() <= 5) {
            return new Object[]{3};
        }
        // name is null ,the's = email
        if (StringUtils.isEmpty(name)) {
            name = email;
        }
        // 设置默认值
        User user = User.Build.registerUser(name, DicConstants.DIC_VALUE_GENDER_MALE,
                email, password, null,User.EMAIL, null, User.EMAIL, HttpUtil.ipArea(ip));
        // 添加数据库
        userCoreDao.insertUser(user);

        //  更新登录信息
        user = updateUserLoginInfo(user.getId(), User.EMAIL, null);

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }

    /**
     * QQ 登录
     *
     * @param token   QQ token
     * @param openid  QQ userId
     * @return 0 成功
     */
    public Object[] loginQQ(String token, String openid, String ip) {
        User user = userCoreDao.findUserByOpenidType(openid, User.QQ, User.BASIC_INFO_FIELDS);
        if (user == null) { // 没有则，注册

            JSONObject jsonObject;
            //todo appkey打通后用APP_LOGIN_QQ_KEY
            jsonObject = QQCenterClient.getUserInfo(SysParam.INSTANCE.WEB_LOGIN_QQ_KEY, token, openid);
            user = User.Build.registerUser(openid, null
                    , null, null,null, User.QQ, null, User.QQ, null);
            user.setToken(token);
            user.setOpenid(openid);
            user.setQqOpenid(openid);
            user.setQqName(jsonObject.get("nickname").toString());
            user.setState(User.STATE_ACTIVATES);

            // 设置其他信息
            user.setName(jsonObject.get("nickname").toString());
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
        user = updateUserLoginInfo(user.getId(), User.QQ, openid); //  更新登录信息
        addLoginLog(user.getId(), user.getName(), User.QQ); //添加日志
        userCoreService.buildUserFileUrl(user);

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }

    /**
     * 用户 微博登陆
     *
     * @param token   QQ token
     * @param openid  QQ userId
     * @return 0 成功
     */
    public Object[] loginWB(String token, String openid, String ip) {
        User user = userCoreDao.findUserByOpenidType(openid, User.WB, User.BASIC_INFO_FIELDS);
        if (user == null) { // 没有则，注册

            JSONObject jsonObject;
            jsonObject = WBCenterClient.userInfo(SysParam.INSTANCE.WEB_LOGIN_WB_KEY, token, openid);
            user = User.Build.registerUser(openid, null
                    , null, null,null, User.WB, null, User.WB, null);
            user.setToken(token);
            user.setOpenid(openid);
            user.setWbOpenid(openid);
            user.setWbName(jsonObject.get("name").toString());
            user.setState(User.STATE_ACTIVATES);

            // 设置其他信息
            user.setName(jsonObject.get("name").toString());
            user.setGender("m".equals(jsonObject.get("gender").toString()) ? 1 : 2);
            user.setRegisterTaoBaoIp(HttpUtil.ipArea(ip));
            user.setAvatar(jsonObject.get("avatar_hd").toString());

            // 再次确定
            User baseUser = userCoreDao.findUserByOpenidType(openid, User.WB, User.BASIC_INFO_FIELDS);
            if (baseUser == null) {
                userCoreDao.insertUser(user);
            } else {
                user = baseUser;
            }
        }
        user = updateUserLoginInfo(user.getId(), User.WB, openid); //  更新登录信息
        addLoginLog(user.getId(), user.getName(), User.WB); //添加日志
        userCoreService.buildUserFileUrl(user);

        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(user);
        return new Object[]{0, user};
    }

    /**
     * 微信登录
     *
     * @param token
     * @param openid
     * @param ip
     * @return
     */
    public Object[] loginWX(String token, String openid, String ip) {
        JSONObject jsonObject = WXCenterClient.userInfo(token, openid);
        //获取unionid
        if (!StringUtils.isEmpty(jsonObject.get("unionid"))) {
            String unionId = jsonObject.get("unionid").toString();
            User user = userDao.findUserByUnionId(unionId);
            if (user == null) {
                user = User.Build.registerUser(openid, null
                        , null, null,null, User.WX, null, User.WX, null);
                user.setToken(token);
                user.setWxOpenid(openid);
                user.setUnionId(unionId);

                if (!StringUtils.isEmpty(jsonObject.get("nickname"))) {
                    user.setWxName(jsonObject.get("nickname").toString());
                    user.setName(user.getWxName());
                }
                if (!StringUtils.isEmpty(user.getName())) {
                    user.setWxName(user.getName());
                } else {
                    user.setName("");
                    user.setWxName("");
                }
                user.setState(User.STATE_ACTIVATES);

                // 设置其他信息
                user.setGender("1".equals(jsonObject.get("sex").toString()) ? 1 : 2);
                user.setRegisterTaoBaoIp(HttpUtil.ipArea(ip));

                if (!StringUtils.isEmpty(jsonObject.get("headimgurl"))) {
                    user.setAvatar(jsonObject.get("headimgurl").toString());
                }

                // 再次确定
                User baseUser = userCoreDao.findUserByOpenidType(openid, User.WX, User.BASIC_INFO_FIELDS);
                if (baseUser == null) {
                    userCoreDao.insertUser(user);
                } else {
                    user = baseUser;
                }
            }
            user = updateUserLoginInfo(user.getId(), User.WX, openid); //  更新登录信息
            addLoginLog(user.getId(), user.getName(), User.WX); //添加日志
            userCoreService.buildUserFileUrl(user);

            // 添加或者更新缓存
            RedisConstants.cacheOnlineUser(user);
            return new Object[]{0, user};
        } else {
            return new Object[]{1};
        }
    }

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

}
