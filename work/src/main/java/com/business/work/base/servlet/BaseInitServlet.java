package com.business.work.base.servlet;

import com.business.core.servlet.AbstractBaseInitServlet;
import com.business.core.utils.BeanManager;
import com.business.work.base.shiro.MyRolesAuthorizationFilter;

/**
 * Created by Administrator on 2015/4/20.
 */
public class BaseInitServlet extends AbstractBaseInitServlet{

    @Override
    protected void beforeInit() throws Exception {

    }

    @Override
    protected void afterInit() throws Exception {
        // 初始化 shiro
        BeanManager.getBean(MyRolesAuthorizationFilter.class).init();
    }

    @Override
    protected void beforeDestroy() throws Exception {

    }

    @Override
    protected void afterDestroy() throws Exception {

    }
}
