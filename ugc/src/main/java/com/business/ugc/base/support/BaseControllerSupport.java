package com.business.ugc.base.support;

import com.business.core.constants.ViewConstants;
import com.business.core.entity.auth.Admin;
import org.apache.shiro.SecurityUtils;
import org.springframework.ui.Model;

/**
 * Created by sin on 2015/4/22.
 */
public class BaseControllerSupport {


    /**
     * @return 管理员
     */
    protected Admin getCurrentAdmin() {
        return (Admin) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 是否的登录
     * @return
     */
    protected boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

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
