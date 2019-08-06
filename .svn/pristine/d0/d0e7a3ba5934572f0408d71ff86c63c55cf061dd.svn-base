package com.business.scheduler.base;

import com.business.core.utils.BeanManager;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

/**
 * 定时任务调度实现
 * User: sin
 * Date: 15-8-26
 * Time: 下午9:17
 */
public class InvokingProxyJob extends CoreBaseJob {

    @Override
    protected void doProcess(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        String jobName = jobDetail.getKey().getName();
        BaseJob job = BeanManager.getBean(jobName);
        if (job != null) {
            logger.info("job execute start...");
            job.execute();
            logger.info("job execute end...");
        }
    }
}
