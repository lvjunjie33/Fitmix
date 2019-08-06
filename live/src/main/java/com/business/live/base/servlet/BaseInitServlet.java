package com.business.live.base.servlet;

//import com.business.app.base.tcp.TcpServerThread;

import com.business.core.constants.ApplicationConfig;
import com.business.core.servlet.AbstractBaseInitServlet;
import com.business.live.socket.SocketClientServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/4/20.
 */
public class BaseInitServlet extends AbstractBaseInitServlet{

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void beforeInit() throws Exception {
        tcpServerInit();
    }

    @Override
    protected void afterInit() throws Exception {

    }

    @Override
    protected void beforeDestroy() throws Exception {
        tcpServerDestroy();
    }

    @Override
    protected void afterDestroy() throws Exception {

    }

    public void tcpServerInit () {
        Long now = System.currentTimeMillis();
        logger.info("初始化 tcp server 开始...");
        SocketClientServer.startServer(ApplicationConfig.LIVE_PORT, ApplicationConfig.LIVE_CONNECT_COUNT);
        logger.info("初始化 tcp server 完成...{}毫秒", System.currentTimeMillis() - now);
    }

    public void tcpServerDestroy () {
        Long now = System.currentTimeMillis();
        logger.info("销毁 tcp server 开始...");
        SocketClientServer.closeServer();
        logger.info("销毁 tcp server 完成...{}毫秒", System.currentTimeMillis() - now);
    }
}
