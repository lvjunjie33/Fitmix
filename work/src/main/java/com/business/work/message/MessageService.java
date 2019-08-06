package com.business.work.message;

import com.business.core.constants.Constants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.Page;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.entity.logs.SnapTable;
import com.business.core.entity.runPlan.Stages;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.entity.user.User;
import com.business.core.mongo.DefaultDao;
import com.business.core.redis.RedisMsgManager;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.work.community.CategorysDao;
import com.business.work.user.UserService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by edward on 2016/5/20.
 */
@Service
public class MessageService {

    @Autowired
    private CategorysDao categorysDao;
    @Autowired
    private DefaultDao defaultDao;

    /**
     * 话题推荐
     */
    public void pushThemeRecommend(Integer targetUid, Long themeId, Integer adminId) {
        RedisMsgManager.sendThemeRecommend(targetUid, themeId.toString(), adminId);
    }

    /**
     * 赛事推送
     *
     * @param targetUid 用户编号
     * @param activityId 赛事编号
     */
    public void pushActivity(Integer targetUid, Integer activityId, Integer adminId) {
        RedisMsgManager.sendActivityRecommend(targetUid, activityId.toString(), adminId);
    }

    /**
     * Mix 推送
     *
     * @param targetUid 用户编号
     * @param mixId 赛事编号
     */
    public void pushMix(Integer targetUid, Integer mixId, Integer adminId) {
        RedisMsgManager.sendMixRecommend(targetUid, mixId.toString(), adminId);
    }

    /**
     * 电台推送
     *
     * @param targetUid 用户编号
     * @param radioId 电台编号
     */
    public void pushRadio(Integer targetUid, Integer radioId, Integer adminId) {
        RedisMsgManager.sendRadioRecommend(targetUid, radioId.toString(), adminId);
    }

    /**
     * 视频推送
     *
     * @param targetUid 用户编号
     * @param videoId 视频编号
     */
    public void pushVideo(Integer targetUid, Integer videoId, Integer adminId) {
        RedisMsgManager.sendVideoRecommend(targetUid, videoId.toString(), adminId);
    }

    /**
     * 推送 训练计划每日通知
     *
     * @param runPlanId 训练计划编号
     */
    public void pushRunPlan(Integer runPlanId, Integer adminId) {

        String currentDateStr = DateUtil.formatTimestamp(System.currentTimeMillis(), "yyyy-MM-dd");

        UserRunPlan userRunPlan = defaultDao.findOne(Query.query(Criteria.where("id").is(runPlanId)),UserRunPlan.class);
        if (userRunPlan == null) {
            return;
        }
        Stages todayStages = null;
        for (Stages stages : userRunPlan.getStagesLists()) {
            if (currentDateStr.equals(stages.getDateTime())) {
                todayStages = stages;
                break;
            }
        }
        Integer uid = userRunPlan.getUid();
        if (todayStages != null && todayStages.getDistance() != 0.0) {
            RedisMsgManager.sendRunPlanNotice(uid, todayStages.getContentDescription(), adminId);
        }
    }

    /**
     * 推送第三方链接
     */
    public void pushOtherLink(Integer targetUid, String title, String content, String link, Integer adminId) {
        RedisMsgManager.sendOtherLinkNotice(targetUid, title, content, link, adminId);
    }
}
