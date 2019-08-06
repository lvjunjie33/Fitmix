package com.business.scheduler.base.servlet;

import com.business.core.utils.BeanManager;
import com.business.scheduler.base.BaseJob;
import com.business.scheduler.base.InvokingProxyJob;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 请注释文件作用
 * User: jeff
 * Date: 13-8-26
 * Time: 下午3:52
 */
public class ScheduleServlet extends HttpServlet {

    private Scheduler scheduler;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        scheduler = context.getBean(SchedulerFactoryBean.class).getScheduler();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Pattern pattern = Pattern.compile("^.*/manage/(.+)$");
        Matcher matcher = pattern.matcher(uri);
        if (matcher.find()) {
            String method = matcher.group(1);
            try {
                String triggerType = req.getParameter("triggerType");
                String triggerName = req.getParameter("triggerName");
                String triggerGroup = req.getParameter("triggerGroup");
                String jobName = req.getParameter("jobName");
                String jobGroup = req.getParameter("jobGroup");
                String triggerExpression = req.getParameter("triggerExpression");
                switch (method) {
                    case "add": {//新增
                        String configId = req.getParameter("configId");
                        JobDetail jobDetail = JobBuilder.newJob(InvokingProxyJob.class)
                                .withIdentity(jobName, jobGroup)
                                .usingJobData("_id", configId)
                                .build();
                        Trigger trigger = null;
                        switch (triggerType) {
                            case "Simple": {
                                trigger = TriggerBuilder.newTrigger()
                                        .withIdentity(triggerName, triggerGroup)
                                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                                .withIntervalInSeconds(Integer.valueOf(triggerExpression))
                                                .repeatForever())
                                        .build();
                            }
                            break;
                            case "Cron": {
                                //http:/manage/add?
                                // triggerType=Cron&
                                // triggerName=com.business.scheduler.jobs.DemoSchedulerTrigger&
                                // triggerGroup=DEFAULT&
                                // jobName=com.business.scheduler.jobs.DemoScheduler&
                                // jobGroup=DEFAULT&
                                // triggerExpression=0%2F10+*+*+*+*+%3F&
                                // configId=2
                                trigger = TriggerBuilder.newTrigger()
                                        .withIdentity(triggerName, triggerGroup)
                                        .withSchedule(CronScheduleBuilder.cronSchedule(triggerExpression))
                                        .build();
                            }
                            break;
                        }
                        scheduler.scheduleJob(jobDetail, trigger);
                    }
                    break;
                    case "modify": {//修改trigger
                        Trigger trigger = null;
                        switch (triggerType) {
                            case "Simple": {
                                trigger = TriggerBuilder.newTrigger()
                                        .withIdentity(triggerName, triggerGroup)
                                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                                .withIntervalInSeconds(Integer.valueOf(triggerExpression))
                                                .repeatForever())
                                        .build();
                            }
                            break;
                            case "Cron": {
                                trigger = TriggerBuilder.newTrigger()
                                        .withIdentity(triggerName, triggerGroup)
                                        .withSchedule(CronScheduleBuilder.cronSchedule(triggerExpression))
                                        .build();
                            }
                            break;
                        }
                        scheduler.rescheduleJob(new TriggerKey(triggerName, triggerGroup), trigger);
//                        scheduler.resumeJob(new JobKey(jobName, jobGroup));
                    }
                    break;
                    case "remove": {//移除
//                        scheduler.unscheduleJob(new TriggerKey(triggerName, triggerGroup));
                        scheduler.deleteJob(new JobKey(jobName, jobGroup));
                    }
                    break;
                    case "pause": {//暂停任务
                        scheduler.pauseJob(new JobKey(jobName, jobGroup));
                    }
                    break;
                    case "resume": {//恢复任务
                        scheduler.resumeJob(new JobKey(jobName, jobGroup));
                        scheduler.resumeTrigger(new TriggerKey(triggerName, triggerGroup));
                    }
                    break;
                    case "run": {//马上执行一次
                        String beanName = req.getParameter("beanName");
                        if (StringUtils.isEmpty(beanName)) {
                            scheduler.triggerJob(new JobKey(jobName, jobGroup));
                        } else {
                            BaseJob baseJob = BeanManager.getBean(beanName);
                            baseJob.execute();
                        }
                    }
                    break;
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            PrintWriter writer = resp.getWriter();
            writer.print("success");
            writer.flush();
            writer.close();
        } else {
            resp.setStatus(500);
        }
        System.out.println(uri);
    }
}
