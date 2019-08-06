package com.business.app.stat;

import com.business.app.notice.NoticeService;
import com.business.core.constants.Constants;
import com.business.core.entity.TaoBaoIp;
import com.business.core.entity.logs.UserBehaviorLog;
import com.business.core.entity.user.User;
import com.business.core.operations.logs.UserBehaviorLogCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.userExperience.UserExperienceCoreDao;
import com.business.core.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/11 0011.
 */
@Service
public class StatService {

    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserBehaviorLogCoreDao userBehaviorLogCoreDao;
    @Autowired
    private UserExperienceCoreDao userExperienceCoreDao;

    @Autowired
    private NoticeService noticeService;


    /**
     * 用户设备信息
     *
     * @param uid 用户编号
     * @param udid 设备编号
     * @param version app 版本
     * @param operators 运营商
     * @param sdk 手机系统 SDK
     * @param channel 渠道
     * @param mobileType 手机型号
     * @param ip ip 地址
     * @param deviceToken 微信用户（可以关联微信公众平台用户）
     * @param idfa 第三方广告推广（只有 IOS）
     */
    public void userDevicesInfo (Integer uid, String udid, String version,
                                 String operators, String sdk, String channel, String mobileType, String ip, String deviceToken,
                                 String idfa, String unionid, String appid) {

        //将占用了该deviceToken的用户的deviceToken 改为oldDeviceToken
        userCoreDao.updateUserByDeviceToken(deviceToken);

        Update update = Update.update("version", version).
                set("operators", operators).
                set("sdk", sdk).
                set("channel", channel).
                set("mobileType", mobileType).
                set("udid", udid).
                set("ip", ip).
                set("deviceToken", deviceToken).
                set("idfa", idfa).
                set("terminal", sdk.indexOf("OS") == -1 ? Constants.TERMINAL_ANDROID : Constants.TERMINAL_IOS).
                set("unionid", unionid).
                set("appid", appid);


        TaoBaoIp taoBaoIp = HttpUtil.ipArea(ip);
        if (taoBaoIp != null) {
            update.set("taoBaoIp", HttpUtil.ipArea(ip));
        }

        /// 更新 客户端信息
        userCoreDao.updateUserById(uid, update);

        User user = userCoreDao.findUserById(uid, "loginCount");

        /// 通知第三方 苹果
        if (channel.indexOf("appStore") != -1 && !StringUtils.isEmpty(idfa) && null != user.getLoginCount() && user.getLoginCount() <= 1) {
            // 部分但三方渠道， 只要这个用户 idfa 存在过， 之后不同 idfa 就不再计算
            noticeService.notice(uid, idfa);
        }
    }

    /**
     * 用户行为上传
     * @param uid 用户编号
     * @param type 行为情况 1、音乐  2、运动
     * @param lng 经度
     * @param lat 纬度
     */
    public void userBehavior(Integer uid, Integer type, Double lng, Double lat) {
        UserBehaviorLog userBehaviorStat = new UserBehaviorLog();
        userBehaviorStat.setUid(uid);
        userBehaviorStat.setType(type);
        userBehaviorStat.setLat(lat);
        userBehaviorStat.setLng(lng);
        userBehaviorStat.setAddTime(System.currentTimeMillis());
        userBehaviorLogCoreDao.insertUserBehaviorLog(userBehaviorStat);
    }
}
