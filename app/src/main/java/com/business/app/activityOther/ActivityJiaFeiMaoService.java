package com.business.app.activityOther;

import com.alibaba.fastjson.JSON;
import com.business.core.entity.activity.ActivityUser;
import com.business.core.entity.activityOther.ActivityJiaFeiMao;
import com.business.core.entity.activityOther.ActivityJiaFeiMaoMember;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Sin on 2016/2/17.
 */
@Service
public class ActivityJiaFeiMaoService {

    @Autowired
    private ActivityJiaFeiMaoDao activityJiaFeiMaoDao;

    public Object[] getSignUpCount() {
        Long signUpCount = activityJiaFeiMaoDao.findActivityJiaFeiMaoCountByTradeStatus(ActivityUser.TRADE_STATUS_SUCCESS);
        Long publicGroupCount = activityJiaFeiMaoDao.findActivityJiaFeiMaoCountByGroup(ActivityJiaFeiMao.GROUP_PUBLIC);
        Long sanRenGroupCount = activityJiaFeiMaoDao.findActivityJiaFeiMaoCountByGroup(ActivityJiaFeiMao.GROUP_SAN_REN);
        return new Object[]{signUpCount, publicGroupCount, sanRenGroupCount};
    }

    /**
     * 加菲猫 活动 报名
     * @param group 组
     * @param email 电子邮箱
     * @param mobileName 移动电话名称
     * @param mobilePhone 移动电话
     * @param emergencyName 紧急联系人名
     * @param emergencyPhone 紧急联系人电话
     * @param memberJson 成员JSON
     * @return 0、成功
     */
    public Object[] signUpData(String group, String email, String mobileName,
                           String mobilePhone, String emergencyName, String emergencyPhone, String memberJson) {

        ActivityJiaFeiMao activityJiaFeiMaoBase =
                activityJiaFeiMaoDao.findActivityJiaFeiMaoByMobilePhoneAndTreadStatus(mobilePhone, ActivityUser.TRADE_STATUS_SUCCESS);

        // 一个手机号只能注册一次
        if (activityJiaFeiMaoBase != null) {
            return new Object[]{1};
        }
        // 人数达到上限
        if (activityJiaFeiMaoDao.findActivityJiaFeiMaoCountByTradeStatus(ActivityUser.TRADE_STATUS_SUCCESS) > 1000) {
            return new Object[]{2};
        }


        int maxMember = 250; // 限时优惠 用户 250 名额内 享受优惠
        double money = 0;
        switch (group) {
            case "公开组":
                if (activityJiaFeiMaoDao.findActivityJiaFeiMaoCountByGroup(ActivityJiaFeiMao.GROUP_PUBLIC) <= maxMember) {
                    money = 100;
                    group = "公开优惠组";
                }
                else {
                    money = 188;
                }
                break;
            case "情侣组":
                money = 358;
                break;
            case "家庭2人组":
                money = 328;
                break;
            case "家庭3人组":
                if (activityJiaFeiMaoDao.findActivityJiaFeiMaoCountByGroup(ActivityJiaFeiMao.GROUP_SAN_REN) <= maxMember) {
                    money = 300;
                    group = "家庭3人优惠组";
                }
                else {
                    money = 398;
                }
                break;
            case "家庭4人组":
                money = 498;
                break;
        }

        List<ActivityJiaFeiMaoMember> memberList = JSON.parseArray(memberJson, ActivityJiaFeiMaoMember.class);

        ActivityJiaFeiMao activityJiaFeiMao = new ActivityJiaFeiMao();
        activityJiaFeiMao.setGroup(group);
        activityJiaFeiMao.setEmail(email);

        activityJiaFeiMao.setNeedMoney(money);
        activityJiaFeiMao.setMobileName(mobileName);
        activityJiaFeiMao.setMobilePhone(mobilePhone);

        activityJiaFeiMao.setEmergencyName(emergencyName);
        activityJiaFeiMao.setEmergencyPhone(emergencyPhone);

        activityJiaFeiMao.setTradePlatform(ActivityUser.TRADE_TYPE_WEI_CHAR_PAY);
        activityJiaFeiMao.setTradeStatus(ActivityUser.TRADE_STATUS_WAIT_APY);
        activityJiaFeiMao.setJiaFeiMaoMembers(memberList);
        activityJiaFeiMao.setAddTime(System.currentTimeMillis());

        activityJiaFeiMaoDao.insertActivityJiaFeiMao(activityJiaFeiMao);
        return new Object[]{0, activityJiaFeiMao};
    }


    public ActivityJiaFeiMao getActivityJiaFeiMao(Integer signUpId) {
        return activityJiaFeiMaoDao.findActivityJiaFeiMaoById(signUpId);
    }

    public int tradeCarried(Integer signUpId, String nonceStr, String tradeNo, Double tradeMoney, String openid) {
        ActivityJiaFeiMao activityJiaFeiMao = activityJiaFeiMaoDao.findActivityJiaFeiMaoById(signUpId, "id", "tradeStatus");

        // 判断 交易状态 是否成功 : 不能重复支付
        if (activityJiaFeiMao != null && activityJiaFeiMao.getTradeStatus().equals(ActivityUser.TRADE_STATUS_SUCCESS)) {
            return 1;
        }

        activityJiaFeiMaoDao.updateActivityJiaFeiMaoById(signUpId, Update.update("tradeNo", tradeNo).
                set("nonceStr", nonceStr).set("tradeMoney", tradeMoney).set("tradeTime", System.currentTimeMillis()).set("openid", openid));
        return 0;
    }

    public void weChatNotify(Map<String, Element> payInfo) {
        String outTradeNo = payInfo.get("out_trade_no").getStringValue(); // 订单号
        String nonceStr = payInfo.get("nonce_str").getStringValue(); // 随机号
        String transactionId = payInfo.get("transaction_id").getStringValue(); // 微信订单号
        activityJiaFeiMaoDao.updateActivityJiaFeiMaoByTradeNoAndNonceStr(outTradeNo, nonceStr,
                Update.update("tradeSuccessTime", System.currentTimeMillis()).
                        set("tradeStatus", ActivityUser.TRADE_STATUS_SUCCESS).
                        set("tradeNoPlatform", transactionId));
    }

    public ActivityJiaFeiMao queryDetail(String mobilePhone) {
        return activityJiaFeiMaoDao.findActivityJiaFeiMaoByMobilePhoneAndTreadStatus(mobilePhone, ActivityUser.TRADE_STATUS_SUCCESS);
    }
}
