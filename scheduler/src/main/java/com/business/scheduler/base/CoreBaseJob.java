package com.business.scheduler.base;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.Serializable;

/**
 * 定时任务调度基类
 * User: sin
 * Date: 13-8-26
 * Time: 下午6:12
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public abstract class CoreBaseJob extends QuartzJobBean implements Serializable {

    protected static final Logger ERROR_LOGGER = LoggerFactory.getLogger("errorLogger");
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            doProcess(context);
        } catch (Throwable e) {
            // log下异常
            logger.error("[executeInternal][异常：{}]", ExceptionUtils.getStackTrace(e));
            // 抛出异常给上层QUARTZ，并关闭该定时器任务
            JobExecutionException jobExecutionException = new JobExecutionException(e);
            jobExecutionException.setUnscheduleFiringTrigger(true);
            throw jobExecutionException;
        }
    }

    /**
     * 子类实现调度
     * @param context 定时任务执行环境
     */
    protected abstract void doProcess(JobExecutionContext context);
}
