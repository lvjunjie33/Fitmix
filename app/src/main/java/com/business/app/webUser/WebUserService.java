package com.business.app.webUser;

import com.alibaba.fastjson.JSONObject;
import com.business.app.base.support.BaseServiceSupport;
import com.business.app.user.UserService;
import com.business.core.client.AliyunCenterClient;
import com.business.core.client.WXCenterClient;
import com.business.core.constants.RedisConstants;
import com.business.core.entity.user.User;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by weird on 2016/8/25.
 */
@Service
public class WebUserService extends BaseServiceSupport {

    @Autowired
    private UserCoreService userCoreService;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private WebUserDao webUserDao;
    @Autowired
    private UserService userService;

    /**
     * 修改个性签名
     */
    public int updateSignature(User user, String signature) {
        Update update = new Update();
        User userNew = userCoreDao.findAndModifyById(user.getId(),
                update.set("signature", signature),
                User.BASIC_INFO_FIELDS);
        userCoreService.buildUserFileUrl(userNew);
        // 添加或者更新缓存
        RedisConstants.cacheOnlineUser(userNew);
        return 0;
    }

    /**
     * 上传图片，修改头像
     */
    public void updateAvatar(User user, String avatarUrl) {
        Update update = new Update();
        if (!StringUtils.isEmpty(avatarUrl)) {
            if (!StringUtils.isEmpty(user.getAvatar())) {
                AliyunCenterClient.deleteFile(user.getAvatar());
            }
            User userNew = userCoreDao.findAndModifyById(user.getId(),
                    update.set("avatar", avatarUrl),
                    User.BASIC_INFO_FIELDS);
            userCoreService.buildUserFileUrl(userNew);
            // 添加或者更新缓存
            RedisConstants.cacheOnlineUser(userNew);
        }
    }

    /**
     * 微信登录
     *
     * @param token
     * @param openid
     * @param channel 渠道
     * @param ip
     * @return
     */
    public Object[] loginWX(String token, String openid, String channel, String ip) {
        JSONObject jsonObject = WXCenterClient.userInfo(token, openid);
        //获取unionid
        if (!StringUtils.isEmpty(jsonObject.get("unionid"))) {
            String unionId = jsonObject.get("unionid").toString();
            User user = webUserDao.findUserByUnionId(unionId);
            if (user == null) {
                user = User.Build.registerUser(openid, null, null, null, null, User.WX, channel, User.WX, null);
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
            user = userService.updateUserLoginInfo(user, User.WX, openid, null); //  更新登录信息
            userService.addLoginLog(user.getId(), user.getName(), User.WX); //添加日志
            userCoreService.buildUserFileUrl(user);

            // 添加或者更新缓存
            RedisConstants.cacheOnlineUser(user);
            return new Object[]{0, user};
        } else {
            return new Object[]{1};
        }
    }

    /**
     * 微信绑定
     *
     * @param token
     * @param openid
     * @return
     */
    public int bindWX(User user, String token, String openid) {
        JSONObject jsonObject = WXCenterClient.userInfo(token, openid);
        //获取unionid
        if (!StringUtils.isEmpty(jsonObject.get("unionid"))) {
            String unionId = jsonObject.get("unionid").toString();
            User user1 = webUserDao.findUserByUnionId(unionId);
            if (user1 == null) {
                Update update = new Update();
                update.set("wxName", jsonObject.get("nickname").toString());
                update.set("wxOpenid", openid);
                update.set("unionid", unionId);
                User userNew = userCoreDao.findAndModifyById(user.getId(), update, User.BASIC_INFO_FIELDS);
                userCoreService.buildUserFileUrl(userNew);
                // 添加或者更新缓存
                RedisConstants.cacheOnlineUser(userNew);
                return 0;
            } else {
                return 2;
            }
        } else {
            return 3;
        }

    }

}
