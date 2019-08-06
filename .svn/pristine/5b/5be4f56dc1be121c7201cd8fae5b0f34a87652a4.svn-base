package com.business.scheduler.base;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by sin on 2015/10/30.
 */
public class LchSchedulerFactoryBean extends SchedulerFactoryBean {

    @Override
    protected void startScheduler(Scheduler scheduler, int startupDelay) throws SchedulerException {
        if (startupDelay < 0) {
            logger.info("Does not automatically start!");
        }
        else {
            super.startScheduler(scheduler, startupDelay);
        }
    }
}
