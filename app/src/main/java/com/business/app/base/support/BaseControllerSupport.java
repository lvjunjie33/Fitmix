package com.business.app.base.support;

import com.business.app.base.context.SystemContext;
import com.business.app.base.context.SystemContextHolder;
import com.business.core.constants.Constants;
import com.business.core.constants.ViewConstants;
import com.business.core.entity.SysParam;
import com.business.core.utils.Base64Util;
import com.business.core.utils.CodeMessageUtil;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.KeyRSAUtil;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * 描述:控制器基类
 * User: sin
 * Time: 2012-11-14 17:46
 */
public abstract class BaseControllerSupport {

    public static Set<String> MOBILE_REGISTER = new HashSet<>();
    public static Set<String> MAIL_REGISTER = new HashSet<>();

    protected boolean checkVersion(HttpServletRequest request, Integer iOSVersion, Integer androidVersion){
        boolean isIOS = checkSDKType(request);
        //version app版本信息暂时没用到
        Integer version = HttpUtil.getIntParameter(request, "_v");
        if (isIOS) {
            return version > iOSVersion;
        } else {
            return version > androidVersion;
        }
    }

    protected boolean checkSDKType(HttpServletRequest request) {//是否是iOS
        String sdk = HttpUtil.getParameter(request, "_sdk");//sdk 设备类型
        if (sdk.contains("iPhone") || sdk.contains("iOS1") || sdk.contains("iPad")) {
           return true;
        }
        return false;
    }


    /**
     * 提示信息
     * @param code 错误码
     * @param msg 提示信息
     */
    @Deprecated
    public void tip (Model model, Integer code, String msg) {
        if (false) {
            model.addAttribute(ViewConstants.KEY_CODE, code);
            model.addAttribute(ViewConstants.KEY_MSG, msg);
        }
        tip(model, code, new Object[]{});
    }

    /**
     * 提示信息
     * @param code 错误码
     * @param params 参数
     */
    public void tip (Model model, int code, Object...params) {
        model.addAttribute(ViewConstants.KEY_CODE, code);
        model.addAttribute(ViewConstants.KEY_MSG, CodeMessageUtil.format(code, getLanguage(), params));
    }

    /**
     * @return 当前用户的编号
     */
    protected Integer getCurrentUserId(HttpServletRequest request) {
        return HttpUtil.getIntParameter(request, "uid");
    }

    /**
     * @return 获得用户请求时的Context
     */
    protected SystemContext getContext() {
        return SystemContextHolder.get();
    }

    /**
     * 获取语言
     */
    protected String getLanguage () {

        if (null == SystemContextHolder.get()) {
            return Constants.LANGUAGE_CH;
        }
        return SystemContextHolder.get().getLanguage();
    }

    protected String getLanguage(HttpServletRequest request) {
        return HttpUtil.getParameter(request, com.business.app.base.constants.Constants.PARAM_LANGUAGE);
    }

    /**
     * 获取终端分类 fitmix、ROC等 {@link com.business.core.entity.user.info.UserDevice#PRODUCT_ROC}
     * @param request
     * @return
     */
    protected String getProduct(HttpServletRequest request) {
        return HttpUtil.getParameter(request, "_product");
    }

    /**
     * 站内跳转
     *
     * @param url 相对地址
     * @return 跳转地址
     */
    protected String redirect(String url) {
        return "redirect:/" + url;
    }

    /**
     * 获取版本
     *
     * @return 版本号
     */
    protected int getVersion() {
        return SystemContextHolder.get().getVersion();
    }

    /**
     * 站外跳转
     *
     * @param url 相对
     * @return 跳转地址
     */
    protected String redirectOutside(String url) {
        return "redirect:" + url;
    }

    /**
     * 终端类型 IOS Android
     */
    protected int getTerminalType() {
        if (getContext().getSdk().contains("OS")) {
            return com.business.core.constants.Constants.TERMINAL_IOS;
        } else if(getContext().getSdk().equals("web")) {
            return com.business.core.constants.Constants.TERMINAL_WEB;
        } else {
            return com.business.core.constants.Constants.TERMINAL_ANDROID;
        }
    }

    /**
     * 追加服务器地址前缀
     *
     * @param url 相对地址
     */
    protected String addServerPathPrefix(HttpServletRequest request, String url) {
        return HttpUtil.getServerPath(request) + url;
    }

    protected Map<String, String> decodeParameter(String parameter) throws Exception {
        parameter = new String(Base64Util.IBase64.decode(parameter));

        parameter = KeyRSAUtil.decrypt(parameter);

        String[] parent = parameter.split("\\^\\^");
        if (parent.length == 0) {
            return Collections.emptyMap();
        }
        Map<String, String> maps = new HashMap<>();
        for (String node : parent) {
            if(!node.contains("==")){
               continue;
            }
            String[] keyVal = node.split("==");
            if (keyVal.length == 0) {
                continue;
            }
            if (keyVal.length > 1) {
                maps.put(keyVal[0], keyVal[1]);
            } else {
                maps.put(keyVal[0], null);
            }
        }
        return maps;
    }

    /**
     * 校验时间戳是否过期
     *
     * @param timestamp app端时间戳
     * @return true 过期，false 没过期
     */
    protected boolean timestampCheck(String timestamp) {
        if (StringUtils.isEmpty(timestamp)) {
            return true;
        }
        Long tp = Long.parseLong(timestamp);
        Integer count = SysParam.INSTANCE.TIMESTAMP_CHECK_SIZE;
        Long currentTime = System.currentTimeMillis();
        if (( currentTime / 1000 - tp / 1000) >  count) {
            return true;
        }
        return false;
    }

    public String decode(String html) {
        if(StringUtils.isEmpty(html) ) {
            return "";
        } else {
            try {
                html = URLDecoder.decode(html,"UTF-8");
            } catch (Exception e) {
                html = html;
            }
        }
        return html;
    }

    public String encode(String content) {
        try {
            content = content.replaceAll(" ", "%20");
            return URLEncoder.encode(content,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public String encodeTry(String content) throws UnsupportedEncodingException {
        return URLEncoder.encode(content,"UTF-8");
    }

//    /**
//     * 手机号码注册 频繁点击校验
//     *
//     * @param mobile 手机号码
//     */
//    protected synchronized boolean mobileRegisterChecked(String mobile) {
//        if(BaseControllerSupport.MOBILE_REGISTER.contains(mobile)) {// 坑爹的东西
//            return true; //频繁点击
//        } else {
//            BaseControllerSupport.MOBILE_REGISTER.add(mobile);
//            return false;
//        }
//    }
//
//    /**
//     * 手机号码注册 移除存根
//     *
//     * @param mobile 手机号码
//     */
//    protected void mobileRegisterRemoveCurrent(String mobile) {
//        BaseControllerSupport.MOBILE_REGISTER.remove(mobile);
//    }
//
//    /**
//     * 邮箱注册 频繁点击校验
//     * @param email 邮箱
//     */
//    protected synchronized boolean emailRegisterChecked(String email) {
//        if(BaseControllerSupport.MAIL_REGISTER.contains(email)) {// 坑爹的东西
//            return true; //频繁点击
//        } else {
//            BaseControllerSupport.MAIL_REGISTER.add(email);
//            return false;
//        }
//    }
//
//    /**
//     * 邮箱注册 移除存根
//     *
//     * @param email 邮箱
//     */
//    protected void emailRegisterRemoveCurrent(String email) {
//        BaseControllerSupport.MAIL_REGISTER.remove(email);
//    }
}
