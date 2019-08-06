package com.business.bbs.base.support;

import com.business.core.constants.Constants;
import com.business.core.constants.ViewConstants;
import com.business.core.utils.CodeMessageUtil;
import org.springframework.ui.Model;


/**
 * 描述:控制器基类
 * User: sin
 * Time: 2012-11-14 17:46
 */
public abstract class BaseControllerSupport {

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
        model.addAttribute(ViewConstants.KEY_MSG, CodeMessageUtil.format(code, Constants.LANGUAGE_CH, params));
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
