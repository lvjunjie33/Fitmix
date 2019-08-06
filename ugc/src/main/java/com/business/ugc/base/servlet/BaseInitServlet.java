package com.business.ugc.base.servlet;

import com.business.core.entity.code.CodeMessage;
import com.business.core.servlet.AbstractBaseInitServlet;
import com.business.core.utils.CodeMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sin on 2015/4/20.
 */
public class BaseInitServlet extends AbstractBaseInitServlet{

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void beforeInit() throws Exception {

    }

    @Override
    protected void afterInit() throws Exception {

        /// 初始化 app 错误码
        CodeMessageUtil.init(CodeMessage.SYS_APP);
    }

    @Override
    protected void beforeDestroy() throws Exception {

    }

    @Override
    protected void afterDestroy() throws Exception {
    }

}
