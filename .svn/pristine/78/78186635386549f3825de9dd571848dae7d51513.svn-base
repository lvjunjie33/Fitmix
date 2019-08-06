package com.business.scheduler.jobs.runPlan;

import com.business.core.constants.Constants;
import com.business.core.entity.logs.SysErrorLog;
import com.business.core.entity.runPlan.Stages;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import com.business.scheduler.base.BaseJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by edward on 2017/10/13.
 */
@Service
public class RunPlanHandleJob extends BaseJob {

    private final Logger logger = LoggerFactory.getLogger(RunPlanHandleJob.class);

    /**
     * 每天0点2分执行
     */
    @Scheduled(cron = "0 2 0 * * ?")
    public void execute() {

        Date today = new Date();
        handle(today);
    }

    protected void handle(Date currentDate) {
        try {
            DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);

            String today = DateUtil.format(currentDate, "yyyy-MM-dd");//今天
            String yesterday = DateUtil.format(DateUtil.addDate(currentDate, Calendar.DAY_OF_YEAR, -1), "yyyy-MM-dd");//昨天
            // 获取进行中的计划
            Query query = new Query(Criteria.where("plan_state").is(UserRunPlan.PLAN_IMPLEMENTATION));
            List<UserRunPlan> userRunPlans = defaultDao.query(query, UserRunPlan.class, "uid");

            for (UserRunPlan plan : userRunPlans) {
                try {
                    UserRunPlan userRunPlan = defaultDao.findById(UserRunPlan.class, plan.getId());

                    //训练计划最后一天,完成训练计划
                    if (yesterday.equals(DateUtil.format(new Date(userRunPlan.getEndTime()), "yyyy-MM-dd"))) {
                        defaultDao.modifyFirst(new Query(Criteria.where("_id").is(userRunPlan.getId())), Update.update("plan_state", UserRunPlan.PLAN_OFF_THE_STHCKS), UserRunPlan.class);
                    }

                    {//今天是计划开始时间
                        if(DateUtil.getDayBegin(currentDate).getTime() == userRunPlan.getBeginTime()) {
                            defaultDao.modifyFirst(Query.query(Criteria.where("id").is(userRunPlan.getId())),
                                    Update.update("complete_degree", 0).set("executeStage", 0).set("executeNo", 0), UserRunPlan.class);
                        }
                    }

                    handleUserRunPlan(defaultDao, userRunPlan, today, yesterday, currentDate);
                } catch (Exception ex) {
                    saveException(ex, plan, defaultDao, today);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleUserRunPlan(DefaultDao defaultDao, UserRunPlan userRunPlan, String today, String yesterday, Date currentDate) {
        List<Stages> stages = userRunPlan.getStagesLists();
        Stages todayStage = null;
        Stages yesterdayStage = null;
        if (CollectionUtils.isEmpty(stages)) {
            return;
        }
        for (Stages s : stages) {
            if (today.equals(s.getDateTime())) {
                todayStage = s;
                continue;
            }
            if (yesterday.equals(s.getDateTime())) {
                yesterdayStage = s;
            }
        }
        {//yesterdayStage
            if (yesterdayStage != null) {
                if (!Stages.STATE_SUCCESS.equals(yesterdayStage.getState())) {
                    yesterdayStage.setState(Stages.STATE_DEFEAT);
                }
            }
        }

        {//todayStage
            if (todayStage != null) {
                if (todayStage.getDistance() <= 0) {
                    todayStage.setState(Stages.STATE_SUCCESS);
                }
            }
        }

        {//更新训练计划信息
            userRunPlan.setComplete_degree(userRunPlan.getCompletedRunDay() / userRunPlan.getRunDay());

            Update update = new Update();
            update.set("stagesLists", userRunPlan.getStagesLists());
            update.set("complete_degree", userRunPlan.getComplete_degree());
            update.set("executeStage", userRunPlan.getExecuteStage());
            update.set("executeNo", userRunPlan.getExecuteNo());
            defaultDao.modifyFirst(Query.query(Criteria.where("id").is(userRunPlan.getId())), update, UserRunPlan.class);
        }
    }

    private void saveException(Exception ex, UserRunPlan plan, DefaultDao defaultDao, String today) {
        SysErrorLog log = new SysErrorLog();
        String msg = org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(ex);
        if (msg.length() > 500) {
            log.setException(msg.substring(0, 500));
        } else {
            log.setException(msg);
        }
        log.setUserId(null);
        log.setSys(Constants.SERVER_NAME_MQ_SERVER);

        log.setRequestParams("planId=" + plan.getId() + ",today=" + today);
        log.setUa("");
        log.setIp("");
        log.setAddTime(new Date());

        log.setSolveStatus(SysErrorLog.SOLVE_STATUS_NO);
        defaultDao.save(log);
    }
}
