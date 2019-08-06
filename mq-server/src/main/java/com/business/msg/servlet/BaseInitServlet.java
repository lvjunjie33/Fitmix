package com.business.msg.servlet;

import com.business.core.servlet.AbstractBaseInitServlet;
import com.business.core.utils.PushUtil;
import com.business.msg.core.MsgDistributionManagement;

/**
 * MQ 初始化
 */
public class BaseInitServlet extends AbstractBaseInitServlet {


    @Override
    protected void beforeInit() throws Exception {
    }

    @Override
    protected void afterInit() throws Exception {

        MsgDistributionManagement.iniSubscribers();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MsgDistributionManagement.listener();
            }
        });
        thread.start();
    }

    @Override
    protected void beforeDestroy() throws Exception {
    }

    @Override
    protected void afterDestroy() throws Exception {
        MsgDistributionManagement.destroy();
    }
}
