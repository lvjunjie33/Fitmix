package com.business.scheduler.jobs.runPlan;

import com.business.core.entity.runPlan.Stages;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.mongo.DefaultDao;
import com.business.core.redis.RedisMsgManager;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import com.business.scheduler.base.BaseJob;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by edward on 2017/10/13.
 *
 * 训练计划 计划每日通知 定时任务
 */
@Service
public class RunPlanNoticeJob extends BaseJob {

    @Scheduled(cron = "0 0 6 * * ?")
    public void execute() {

        Date currentDate = new Date();

        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);

        List<UserRunPlan> userRunPlans = defaultDao.query(Query.query(Criteria.where("plan_state").is(UserRunPlan.PLAN_IMPLEMENTATION)), UserRunPlan.class, "uid");
        String currentDateStr = DateUtil.formatTimestamp(currentDate.getTime(), "yyyy-MM-dd");

        for (UserRunPlan plan : userRunPlans) {
            UserRunPlan userRunPlan = defaultDao.findById(UserRunPlan.class, plan.getId(), "uid", "stagesLists");
            Stages todayStages = null;
            for (Stages stages : userRunPlan.getStagesLists()) {
                if (currentDateStr.equals(stages.getDateTime())) {
                    todayStages = stages;
                    break;
                }
            }
            Integer uid = userRunPlan.getUid();
            try {
                if (todayStages != null && todayStages.getDistance() != 0.0) {
                    RedisMsgManager.sendRunPlanNotice(uid, todayStages.getContentDescription(), null);
                }
            } catch (Exception e) {
                logger.error("uid:" + uid + "" + ExceptionUtils.getFullStackTrace(e));
            }
        }
    }
}
