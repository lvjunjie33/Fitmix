package com.business.live.base.support;

import com.business.core.constants.ViewConstants;
import org.springframework.ui.Model;

/**
 * 描述:控制器基类
 * User: jeff
 * Time: 2012-11-14 17:46
 */
public abstract class BaseControllerSupport {


    /**
     * 提示信息
     * @param code 错误码
     * @param msg 提示信息
     */
    public void tip (Model model, Integer code, String msg) {
        model.addAttribute(ViewConstants.KEY_CODE, code);
        model.addAttribute(ViewConstants.KEY_MSG, msg);
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
     * 站外跳转
     *
     * @param url 相对
     * @return 跳转地址
     */
    protected String redirectOutside(String url) {
        return "redirect:" + url;
    }

}
