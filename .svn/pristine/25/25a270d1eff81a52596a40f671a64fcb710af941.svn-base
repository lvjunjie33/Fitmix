package com.business.msg.core;

import com.business.core.constants.Constants;
import com.business.core.entity.user.info.UserDevice;
import com.business.msg.util.PushUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by edward on 2017/11/16.
 */
public abstract class PushModule {

    protected static Logger logger = LoggerFactory.getLogger(PushModule.class);

    /**
     * 推送单个设备
     *
     * @param active 设备激活状态
     * @param terminal 设备类型
     * @param deviceToken 设备token
     * @param title 标题
     * @param content 内容
     * @param body 参数
     */
    protected void pushHandle(Integer active, Integer terminal, String deviceToken, String title, String content, Map<String, String> body) {
        try {
            String code = "";
            if (UserDevice.ACTIVE_TRUE.equals(active)) {
                if (Constants.TERMINAL_ANDROID == terminal) {
                    code = PushUtil.pushTokenAndroid(deviceToken, title, content, body);
                } else if(Constants.TERMINAL_IOS == terminal) {
                    code = PushUtil.pushTokenIOS(deviceToken, title, content, body);
                }
                logger.error("push code:" + code);
            } else {
                if (Constants.TERMINAL_ANDROID == terminal) {
                    code = PushUtil.pushTokenAndroidDefault(deviceToken, title, content, body);
                } else if(Constants.TERMINAL_IOS == terminal) {
                    code = PushUtil.pushTokenIOSDefault(deviceToken, title, content, body);
                }
                logger.error("push code:" + code);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 推送全用户
     *
     * @param title 标题
     * @param content 内容
     * @param body 参数
     */
    protected void pushAllHandle(String title, String content, Map<String, String> body) {
        try {
            String code = "";
            code = PushUtil.pushAllDeviceAndroid(title, content, body);
            logger.error("push Android");
            code = PushUtil.pushAllDeviceIOS(title, content, body);
            logger.error("push iOS");
        } catch (Exception e) {
            logger.error("异常");
        }
    }
}
