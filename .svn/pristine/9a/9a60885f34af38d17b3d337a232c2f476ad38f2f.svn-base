package com.business.app.base.servlet;

import com.business.app.shop.task.TaskInfoService;
import com.business.core.constants.ApplicationConfig;
import com.business.core.entity.code.CodeMessage;
import com.business.core.servlet.AbstractBaseInitServlet;
import com.business.core.utils.BeanManager;
import com.business.core.utils.CodeMessageUtil;
import com.business.core.utils.KeyRSAUtil;
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
		BeanManager.getBean(TaskInfoService.class).init();
        String path = this.getServletContext().getRealPath("/");
        try {
            ApplicationConfig.pubKey = KeyRSAUtil.loadPubKey(path + "WEB-INF/classes/keys/publickey.keystore");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ApplicationConfig.priKey = KeyRSAUtil.loadPriKey(path + "WEB-INF/classes/keys/privatekey.keystore");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void beforeDestroy() throws Exception {

    }

    @Override
    protected void afterDestroy() throws Exception {
    }

}
