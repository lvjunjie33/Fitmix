package com.business.app.base.support;


import com.business.app.base.context.SystemContext;
import com.business.app.base.context.SystemContextHolder;
import com.business.core.entity.user.User;

/**
 * 请注释文件作用
 * User: sin
 * Date: 13-7-30
 * Time: 下午4:24
 */
public class BaseServiceSupport {

    /**
     * @return SystemContext
     */
    protected SystemContext getContext() {
        return SystemContextHolder.get();
    }

    /**
     * 终端类型 IOS Android
     */
    protected int getTerminalType() {
        if (getContext().getSdk().indexOf("OS") != -1) {
            return com.business.core.constants.Constants.TERMINAL_IOS;
        }
        else {
            return com.business.core.constants.Constants.TERMINAL_ANDROID;
        }
    }
}
