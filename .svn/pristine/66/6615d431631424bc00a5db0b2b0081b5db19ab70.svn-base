package com.business.msg.core;

import com.business.core.utils.BeanManager;
import com.business.core.utils.ClassUtil;
import com.business.core.utils.RedisUtil;
import com.business.msg.MsgSubscribeAnnotation;
import redis.clients.jedis.Jedis;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2017/4/19.
 */
public class MsgDistributionManagement {

    private static Map<String, RedisConcurrentlyCommand> subscribers = new HashMap<>();

    public static void iniSubscribers() {
        List<Class<RedisConcurrentlyCommand>> clazzList = ClassUtil.getAllClassByInterface(RedisConcurrentlyCommand.class, "com.business.msg.server");
        for (Class clazz : clazzList) {
            Annotation[] annotations = clazz.getAnnotations();
            if (annotations.length == 0) continue;
            for (Annotation a : annotations) {
                if(a instanceof MsgSubscribeAnnotation) {
                    MsgSubscribeAnnotation m = (MsgSubscribeAnnotation) a;
                    RedisConcurrentlyCommand concurrentlyCommand = (RedisConcurrentlyCommand) BeanManager.getBean(clazz);
                    MsgDistributionManagement.subscribers.put(m.channel(), concurrentlyCommand);
                }
            }
        }
        System.out.println(clazzList.size());
    }

    /**
     * 消息订阅
     */
    public static void listener() {
        Jedis jedis = RedisUtil.getResource(12);
        RedisMsgPubSubListener redisMsgPubSubListener = new RedisMsgPubSubListener();

        List<String> keys = new ArrayList<>(MsgDistributionManagement.subscribers.keySet());
        String[] channels = new String[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            channels[i] = keys.get(i);
        }
        jedis.subscribe(redisMsgPubSubListener, channels);
    }

    /**
     * 消息分发
     */
    public static void distribution(String channel, String message) {
        if(!MsgDistributionManagement.subscribers.containsKey(channel))return;
        RedisConcurrentlyCommand command = MsgDistributionManagement.subscribers.get(channel);
        try {
            command.execute(message);
        } catch (Exception e) {
        }
    }

    /**
     * 销毁redis
     */
    public static void destroy() {
        RedisUtil.destroy();
    }
}
