package com.business.core.utils;

import com.business.core.constants.ApplicationConfig;
import com.business.push.AndroidNotification;
import com.business.push.IOSNotification;
import com.business.push.PushClient;
import com.business.push.UmengNotification;
import com.business.push.android.AndroidUnicast;
import com.business.push.ios.IOSUnicast;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSetMultimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sin on 2015/12/8.
 *
 * push util
 *
 * TODO 友盟就是个超级坑,api写的太烂了
 *
 * <p>
 *     1、umeng xmpp push
 * </p>
 */
public class PushUtil {

    protected static Logger logger = LoggerFactory.getLogger(PushUtil.class);

    private static final String APP_KEY = "5528bab4fd98c5ca0a000cab";

    private static final String APP_SECRET = "unad3r8gezudowkqmbvrrhtjzbwewbrp";

    private static final String APP_KEY_ANDROID = "5528b9b8fd98c5e67a001ae9";
    private static final String APP_SECRET_ANDROID = "ltainhihcwh5xkvj0hjuft2tszddce2j";

    private static AndroidUnicast androidUnicast;

    private static IOSUnicast iosUnicast;

    private static PushClient client  = new PushClient();

    public static synchronized void init() {
        try {
            iosUnicast = new IOSUnicast(APP_KEY, APP_SECRET);
            androidUnicast = new AndroidUnicast(APP_KEY_ANDROID, APP_SECRET_ANDROID);

            /*if (ApplicationConfig.SERVER) {// 不同环境初始化推送模式
                iosUnicast.setProductionMode();
                androidUnicast.setProductionMode();
            } else {
                iosUnicast.setTestMode();
                androidUnicast.goAppAfterOpen();
                androidUnicast.setTestMode();
            }*/
            iosUnicast.setTestMode();
            androidUnicast.goAppAfterOpen();
            androidUnicast.setTestMode();

        } catch (Exception e) {
            logger.info("init push error.. {} ", e);
        }
    }

    /*public static int iosUnicast(String token, String alert, int badge, Map<String, String> customizedField) throws Exception {
        IOSUnicast unicast = iosUnicast;
        unicast.setDeviceToken(token);
        unicast.setAlert(alert);
        unicast.setBadge(badge);
        unicast.setSound("default");
        unicast.setFields(customizedField);
        return client.send(unicast);
    }

    public static int androidUnicast(String token, String title, String context, Map<String, String> customizedField) throws Exception {
        AndroidUnicast unicast = androidUnicast;
        unicast.setDeviceToken(token);
        unicast.setTicker("Android unicast ticker");
        unicast.setTitle(title);
        if (StringUtil.isEmpty(context)) {
            context = "空值";
        }
        unicast.setText(context);
        unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);

        unicast.setFields(customizedField);
        client.send(unicast);
        return 0;
    }
*//*
    public static void main(String[] args) throws Exception {

        init();
        for (int i = 0; i < 1; i++) {

            ////////////756412d0e41be7c56ba369db1ff893894d23d9d2e9943c64996654d2a0819163
            Map<String, String> maps = new HashMap<>();//2bb652ea1238f0be35a7970ad1c057948f613e976b3a2ea7a95d5b32aabda828
            iosUnicast("28e7f122f794ad2504e9c5243cc7442eaadb1391b9e66f1dc3620d8929a44808", "测试测试普通消息a" + i, 1, maps);
            //androidUnicast("AvfnGKm7TSXbY8T7jTVKcpcV_FJXvouHe4v31uIJ9b0V", "aaa", "测试", maps);
            //iosUnicast("2bb652ea1238f0be35a7970ad1c057948f613e976b3a2ea7a95d5b32aabda828", "测试测试me" + i, 1, maps);
            //iosUnicast("3978da9742c43cbc3e36aa5e2e17161658fea0ccc552220c7316b2571d3165b6", "测试测试you" + i, 1, null);
        }
    }*/
}
