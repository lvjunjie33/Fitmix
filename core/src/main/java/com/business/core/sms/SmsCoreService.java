package com.business.core.sms;

import com.business.core.utils.MathUtil;
import com.business.core.utils.YunPianUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by sin on 2015/11/30.
 * 模板短信推送
 */
@Service
public class SmsCoreService {


    /**
     * 发验证码
     * <p>
     *     您的验证码是#code#。如非本人操作，请忽略本短信
     * </p>
     * @param mobile 手机号
     * @return 验证码
     */
    public String sendVerificationCode(String mobile, String code) {
        if (StringUtils.isEmpty(code)) {
            code = String.valueOf(MathUtil.random(100000, 999999));
        }
        String tpl_value = "#code#=" + code;
        //2018-09-11 手机号有+号表示国际号码，没有+号国内号码
        if(mobile.startsWith("+")){
            //国际模板短信
            YunPianUtil.tplSendSms(2428890, tpl_value, mobile);
        }else{
            //国内模板短信
            YunPianUtil.tplSendSms(1141097, tpl_value, mobile);
        }


        return code;
    }

    /**
     * 发验证码 万德
     * <p>
     *     您的验证码是#code#。如非本人操作，请忽略本短信
     * </p>
     * @param mobile 手机号
     * @return 验证码
     */
    public String sendWDVerificationCode(String mobile, String code) {
        if (StringUtils.isEmpty(code)) {
            code = String.valueOf(MathUtil.random(100000, 999999));
        }
        String tpl_value = "#code#=" + code;
        YunPianUtil.tplSendSms(1504680, tpl_value, mobile);
        return code;
    }

    /**
     * <b>
     *     恭喜您已成功报名第三届深圳迎新跑，您的编号为：xxxx，姓名：xxx，性别：男x，组别xx公里，衣服尺码：xxx，如信息有误，请及时联系我们，祝您2016新想事成！1月1日深圳湾运动公园，不见不散。
     * </b>
     * sms 模板推送
     * @param id 用户编号
     * @param name 用户名称
     * @param gender 用户性别
     * @param group 哪一组
     * @param size 衣服尺码
     */
    public void sendActivityRegisterMessage(String mobile, String id, String name, String gender, String group, String size) {
        String text = "恭喜您已成功报名第三届深圳迎新跑，您的编号为：" + id + "，姓名：" + name +
                "，性别：" + gender + "，组别：" + group + "公里，衣服尺码：" + size + "，如信息有误，请及时联系我们，祝您2016新想事成！1月1日深圳湾运动公园，不见不散。";
        YunPianUtil.sendSms(text, mobile);
    }

    public void sendActivityTip(String mobile) {
        String tpl_value = "";
        YunPianUtil.tplSendSms(1158657, tpl_value, mobile);
    }

    public void sendErrorMessage(String mobile) {
        String text = "【乐响动】微信支付异常，导致迎新跑报名不成功，您所支付的费用将原额退还，给您带来的不便，敬请谅解。";
        YunPianUtil.sendSms(text, mobile);
    }

    public void sendSystemError(String mobile, String text) {
        YunPianUtil.sendSms(text, mobile);
    }

    public void sendNotPay(String mobile) {
        String text = "【乐享动】“迎新跑”提醒您，您的参赛费用还 未支付，如有疑问请通过 \"乐享动\" 公众号反馈，给您带来的不便，敬请谅解。";
        YunPianUtil.sendSms(text, mobile);
    }

    /**
     * 选手包
     */
    public void send1185011(String mobile) {
        String text = "【乐享动】［迎新跑组委会］亲爱的跑友：" +
                "迎新跑新想事成选手包已准备就绪，请于29日—31日9:00—21:00前往华侨城华夏艺术中心前广场领取，" +
                "请携带身份证件，凭个人报名回执的手机短信领取，" +
                "帮他人代领请提供对方的相关证件信息及报名回执信息。" +
                "领取地址：深南大道华侨城段路北，地铁一号线华侨城站A出口。";
        YunPianUtil.sendSms(text, mobile);
    }

    public void send1189391(String mobile, String id, String name, String gender, String group, String size) {
        String text = "【乐享动】恭喜您已成功报名第三届深圳迎新跑，" +
                "您的编号为：" + id + "，姓名：" + name + "，性别：" + gender + "，组别" + group + "，衣服尺码：" + size + "，如信息有误，" +
                "请及时联系我们，祝您2016新想事成！1月1日深圳湾运动公园，不见不散。[农发代 用户注意][30日、31日9:00—21:00]，" +
                "前往华侨城华夏艺术中心前广场领取，请携带身份证件，凭个人报名回执的手机短信领取，帮他人代领请提供对方的相关证件信息及报名回执信息。" +
                "领取地址：深南大道华侨城段路北，地铁一号线华侨城站A出口。";
        YunPianUtil.sendSms(text, mobile);
    }


    public void send1194343(String mobile) {
        String text = "【乐享动】亲爱的跑友：迎新跑即将开跑，但您的报名支付还没有成功，" +
                "珍贵的名额已经为您保留，请您尽快到[选手包]领取处现场缴费，谢谢您的参与，如有疑问现场给您解答。";
        YunPianUtil.sendSms(text, mobile);
    }

    public void send1194923(String mobile) {
        String text = "【乐享动】亲爱的跑友：迎新跑明早就要正式起跑了，请大家再熟悉一下活动规程，" +
                "集合地点：深圳湾运动公园，在深圳湾（望海路）跨海大桥西侧800米左右。" +
                "检录时间6:30，起跑时间：7:55，请大家提前到场，由于现场停车位有限，建议大家搭乘公共交通工具.";
        YunPianUtil.sendSms(text, mobile);
    }

    public void send1212949(String mobile, int code) {
        String text = "【城市悦跑】《悦跑年会船票》亲爱的悦跑队友（" + code + "）：欢迎你参加悦跑年会，" +
                "你的报名支付已经成功，请于2016年1月31日17时假座华侨城洲际酒店（船吧）精彩派对，期待你的闪亮登场～";
        YunPianUtil.sendSms(text, mobile);
    }
}
