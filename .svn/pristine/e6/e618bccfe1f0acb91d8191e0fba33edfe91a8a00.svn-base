package com.business.msg.util;

import com.alibaba.fastjson.JSON;
import com.business.core.utils.DateUtil;
import com.business.msg.util.xinge.XingeConstants;
import com.tencent.xinge.*;
import org.json.JSONObject;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by edward on 2017/9/18.
 */
public class PushUtil {

    /**
     * iOS通知栏通知
     */
    public static String pushTokenIOSDefault(String deviceToken, String title, String alert, Map<String, String> custom) {
        Assert.notNull(deviceToken, "deviceToken is null");

        MessageIOS message = new MessageIOS();

        Map<String, Object> objectMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(custom)) {
            for (String key : custom.keySet()) {
                objectMap.put(key, custom.get(key));
            }
        }
        message.setType(MessageIOS.TYPE_APNS_NOTIFICATION);
        message.setCustom(objectMap);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body", alert);
        jsonObject.put("title", title);
        message.setAlert(jsonObject);

        message.setExpireTime(86400);

        XingeApp xinge = new XingeApp(XingeConstants.ACCESS_ID_IOS, XingeConstants.SECRET_KEY_IOS);
        JSONObject ret = xinge.pushSingleDevice(deviceToken, message, XingeApp.IOSENV_DEV);
        return JSON.toJSONString(ret);
    }

    /**
     * iOS 站内信
     */
    public static String pushTokenIOS(String deviceToken, String title, String alert, Map<String, String> custom) {
        Assert.notNull(deviceToken, "deviceToken is null");

        MessageIOS message = new MessageIOS();

        Map<String, Object> objectMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(custom)) {
            for (String key : custom.keySet()) {
                objectMap.put(key, custom.get(key));
            }
        }
        message.setType(MessageIOS.TYPE_REMOTE_NOTIFICATION);
        message.setCustom(objectMap);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body", alert);
        jsonObject.put("title", title);
        message.setAlert(jsonObject);

        message.setExpireTime(86400);

        XingeApp xinge = new XingeApp(XingeConstants.ACCESS_ID_IOS, XingeConstants.SECRET_KEY_IOS);
        JSONObject ret = xinge.pushSingleDevice(deviceToken, message, XingeApp.IOSENV_DEV);
        return JSON.toJSONString(ret);
    }

    /**
     * iOS推送所有用户
     *
     * @param title 标题
     * @param alert 内容
     * @param custom 参数
     */
    public static String pushAllDeviceIOS(String title, String alert, Map<String, String> custom) {
        MessageIOS message = new MessageIOS();

        Map<String, Object> objectMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(custom)) {
            for (String key : custom.keySet()) {
                objectMap.put(key, custom.get(key));
            }
        }
        message.setCustom(objectMap);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body", alert);
        jsonObject.put("title", title);
        message.setAlert(jsonObject);

        System.out.println(jsonObject.toString());
        message.setExpireTime(86400);

        XingeApp xinge = new XingeApp(XingeConstants.ACCESS_ID_IOS, XingeConstants.SECRET_KEY_IOS);
        JSONObject ret = xinge.pushAllDevice(0, message, XingeApp.IOSENV_DEV);
        return JSON.toJSONString(ret);
    }

    /**
     * 安卓 通知栏通知
     */
    public static String pushTokenAndroidDefault(String deviceToken, String title, String content, Map<String, String> custom) {

        Assert.notNull(deviceToken, "deviceToken is null");

        Message message = new com.tencent.xinge.Message();
        message.setTitle(title);
        message.setContent(content);
        message.setType(Message.TYPE_NOTIFICATION);
        message.setStyle(new Style(0, 0, 0, 1, 1, 1, 0, 1));

        Map<String, Object> objectMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(custom)) {
            for (String key : custom.keySet()) {
                objectMap.put(key, custom.get(key));
            }
        }
        message.setCustom(objectMap);
        message.setExpireTime(86400);

        XingeApp xinge = new XingeApp(XingeConstants.ACCESS_ID_A, XingeConstants.SECRET_KEY_A);
        JSONObject ret = xinge.pushSingleDevice(deviceToken, message);
        return ret.toString();
    }

    /**
     * 安卓 站内信
     */
    public static String pushTokenAndroid(String deviceToken, String title, String content, Map<String, String> custom) {
        Assert.notNull(deviceToken, "deviceToken is null");

        Message message = new com.tencent.xinge.Message();
        message.setTitle(title);
        message.setContent(content);
        message.setType(Message.TYPE_MESSAGE);
        message.setStyle(new Style(0, 0, 0, 1, 1, 1, 0, 1));

        Map<String, Object> objectMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(custom)) {
            for (String key : custom.keySet()) {
                objectMap.put(key, custom.get(key));
            }
        }
        message.setCustom(objectMap);
        message.setExpireTime(86400);

        XingeApp xinge = new XingeApp(XingeConstants.ACCESS_ID_A, XingeConstants.SECRET_KEY_A);
        JSONObject ret = xinge.pushSingleDevice(deviceToken, message);
        return ret.toString();
    }

    public static String pushAllDeviceAndroid(String title, String content, Map<String, String> custom) {
        Message message = new com.tencent.xinge.Message();
        message.setTitle(title);
        message.setContent(content);
        message.setType(Message.TYPE_NOTIFICATION);
        message.setStyle(new Style(0, 0, 0, 1, 1, 1, 0, 1));

        Map<String, Object> objectMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(custom)) {
            for (String key : custom.keySet()) {
                objectMap.put(key, custom.get(key));
            }
        }
        message.setCustom(objectMap);
        message.setExpireTime(86400);

        XingeApp xinge = new XingeApp(XingeConstants.ACCESS_ID_A, XingeConstants.SECRET_KEY_A);
        JSONObject ret = xinge.pushAllDevice(0, message);
        return ret.toString();
    }
}
