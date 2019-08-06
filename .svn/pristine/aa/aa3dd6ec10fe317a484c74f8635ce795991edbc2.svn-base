package com.business.work.base.tag;

import com.business.work.base.shiro.MyRolesAuthorizationFilter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by Administrator on 2015-05-18.
 */
public class AuthOperationTag extends TagSupport {

    private String url;

    @Override
    public int doStartTag() throws JspException {
        return MyRolesAuthorizationFilter.authorizating(url) ? TagSupport.EVAL_BODY_INCLUDE : TagSupport.SKIP_BODY;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
