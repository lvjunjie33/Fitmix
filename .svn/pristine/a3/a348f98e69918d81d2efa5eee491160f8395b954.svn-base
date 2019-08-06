package com.business.work.base.support;

import com.business.core.constants.ViewConstants;
import com.business.core.entity.auth.Admin;
import org.apache.shiro.SecurityUtils;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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

    protected String redirect(String url) {
        return "redirect:/" + url;
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
            return URLEncoder.encode(content,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public String encodeTry(String content) throws UnsupportedEncodingException {
        return URLEncoder.encode(content,"UTF-8");
    }
}
