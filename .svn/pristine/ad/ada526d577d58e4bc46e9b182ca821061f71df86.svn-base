package com.business.scheduler.base.servlet;

import com.business.core.servlet.AbstractBaseInitServlet;
import com.business.core.utils.BeanManager;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;

/**
 * Created by Administrator on 2015/6/5 0005.
 */
public class BaseInitServlet extends AbstractBaseInitServlet {

    private StdScheduler scheduler;

    @Override
    protected void beforeInit() throws Exception {

    }

    @Override
    protected void afterInit() throws Exception {
        startScheduler();
    }

    @Override
    protected void beforeDestroy() throws Exception {

    }

    @Override
    protected void afterDestroy() throws Exception {
//        destroyScheduler();
    }



    private void startScheduler() throws SchedulerException {
        long now = System.currentTimeMillis();
        logger.info("初始StdScheduler开始...");

        logger.info("初始StdScheduler完成...消耗：{}毫秒!", System.currentTimeMillis() - now);
    }

    private void destroyScheduler() {
        long now = System.currentTimeMillis();
        logger.info("销毁StdScheduler开始...");
        scheduler.shutdown(true);
        logger.info("销毁StdScheduler完成...消耗：{}毫秒!", System.currentTimeMillis() - now);
    }
}
