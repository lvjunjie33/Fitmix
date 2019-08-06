package com.business.core.sms;

import com.business.core.utils.YunPianUtil;

/**
 * Created by sin on 2015/11/30.
 * 模板短信推送
 */
public class SmsSendTem {

    /**
     * sms 模板推送
     * @param id 用户编号
     * @param name 用户名称
     * @param gender 用户性别
     * @param group 哪一组
     * @param size 衣服尺码
     * @param time 活动时间
     */
    public void send1139455(String mobile, String id, String name, String gender, String group, String size, String time) {
        String tpl_value = "#id#=" + id + "&#name#=" + name + "&#gender#=" + gender + "&#group#=" + group + "&#size#=" + size + "&#time#=" + time;
        YunPianUtil.tplSendSms(1139455, tpl_value, mobile);
    }
}
