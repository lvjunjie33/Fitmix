package com.business.app.joinActivity;

import com.business.app.activity.ActivityDao;
import com.business.app.base.constants.CodeConstants;
import com.business.app.user.UserDao;
import com.business.core.constants.ActivityConstants;
import com.business.core.constants.DicConstants;
import com.business.core.entity.Dictionary;
import com.business.core.entity.SysParam;
import com.business.core.entity.activity.Activity;
import com.business.core.entity.activity.ActivitySignUp;
import com.business.core.entity.joinActivity.JoinActivity;
import com.business.core.entity.joinActivity.JoinActivityEntered;
import com.business.core.entity.joinActivity.JoinActivityViewLog;
import com.business.core.entity.user.User;
import com.business.core.utils.DicUtil;
import com.business.core.utils.MongoUtil;
import com.business.core.utils.ValidatedUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by fenxio on 2016/5/17.
 */
@Service
public class JoinActivityService {

    @Autowired
    private JoinActivityDao joinActivityDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ActivityDao activityDao;


    /**
     * 校验 第三方赛事 参数
     * @param joinActivity 三方赛事
     */
    public int validatedJoinActivity(JoinActivity joinActivity) {
        int result = 0;
        if(ValidatedUtil.checkFieldValueNull(joinActivity)) {
            result = ActivityConstants.ERROR_PARAMTER_NULL;
        } else  {
            if(joinActivity.getActivityBeginTime() < System.currentTimeMillis()) {
                result = ActivityConstants.ERROR_ACTIVITY_TIME;
            }
            if(joinActivity.getActivityMoney().size() > 0) {
                for(Object key : joinActivity.getActivityMoney().keySet()) {
                    if(!NumberUtils.isNumber((String)joinActivity.getActivityMoney().get(key))) {
                        result = ActivityConstants.ERROR_ACTIVITY_MONEY;
                        break;
                    }
                }
            }
            if(joinActivity.getChannel() != null) {
                Dictionary dictionary = DicUtil.getDictionary(DicConstants.DIC_TYPE_JOIN_ACTIVITY, joinActivity.getChannel());
                Set<Integer> disSet = SysParam.INSTANCE.JOIN_ACTIVITY_DISABLE;
                if(dictionary == null || disSet.contains(dictionary.getValue())) {
                    result = ActivityConstants.ERROR_CHANNEL;
                }
            }
            if(joinActivity.getActivityId() != null) {
                JoinActivity activity = joinActivityDao.findJoinActivityByActivityIdAndChannel(joinActivity.getActivityId(), joinActivity.getChannel());
                if(activity != null) {
                    result = ActivityConstants.ERROR_ACTIVITY_EXIST;
                }
            }
            if(joinActivity.getActivityMoney().size() > 0) {
                for(Object key : joinActivity.getActivityMoney().keySet()) {
                    if(!NumberUtils.isNumber((String) joinActivity.getActivityMoney().get(key)) || Double.parseDouble( (String) joinActivity.getActivityMoney().get(key) ) < 0) {
                        result = ActivityConstants.ERROR_ACTIVITY_MONEY;
                    }
                }
            }
        }

        return result;
    }

    /**
     * 检验 通知回调 参数
     * @param joinActivityEntered 通知回调
     */
    public int validatedJoinActivityEntered(JoinActivityEntered joinActivityEntered) {
        int result = 0;
        if(ValidatedUtil.checkFieldValueNull(joinActivityEntered)) {
            result = ActivityConstants.ERROR_PARAMTER_NULL;
        } else {
            if (joinActivityEntered.getEnteredNum() <= 0) {
                result = ActivityConstants.ERROR_ENTERED_NUM;
            }
            if (joinActivityEntered.getChannel() != null) {
                Dictionary dictionary = DicUtil.getDictionary(DicConstants.DIC_TYPE_JOIN_ACTIVITY, joinActivityEntered.getChannel());
                Set<Integer> disSet = SysParam.INSTANCE.JOIN_ACTIVITY_DISABLE;
                if (dictionary == null || disSet.contains(dictionary.getValue())) {
                    result = ActivityConstants.ERROR_CHANNEL;
                }
            }
            if (joinActivityEntered.getUid() != null) {
                User user = userDao.findUserById(joinActivityEntered.getUid());
                if (user == null) {
                    result = CodeConstants.LOGIN_USER_NOT_EXIST;
                }
            }
            if (joinActivityEntered.getActivityId() != null) {
                Activity activity = activityDao.findActivityOutActivityAndChannel(joinActivityEntered.getActivityId(), joinActivityEntered.getChannel());
                if (activity == null) {
                    result = ActivityConstants.ERROR_ACTIVITY_NULL;
                }
            }
            if (joinActivityEntered.getNotifyId() != null) {
                JoinActivityEntered entered = joinActivityDao.findJoinActivityEnteredByNotifyId(joinActivityEntered.getNotifyId());
                if (entered != null) {
                    result = ActivityConstants.ERROR_NOTIFY_REPEAT;
                }
            }
            if(joinActivityEntered.getEnteredMem().size() > 0) {
                for(Object key : joinActivityEntered.getEnteredMem().keySet()) {
                    if(!NumberUtils.isNumber((String) joinActivityEntered.getEnteredMem().get(key)) || Double.parseDouble( (String) joinActivityEntered.getEnteredMem().get(key) ) < 0) {
                        result = ActivityConstants.ERROR_ACTIVITY_MONEY;
                    }
                }
            }

        }
        return result;
    }

    /**
     * 添加第三方赛事
     * @param joinActivity 第三方赛事
     */
    public int addJoinActivity(JoinActivity joinActivity) {
        joinActivity.setAddTime(System.currentTimeMillis());
        joinActivity.setStatus(JoinActivity.STATUS_TO_JOIN);
        int result = validatedJoinActivity(joinActivity);
        if(result == 0) {
            joinActivity.setActivityMoney(MongoUtil.enConverterMap(joinActivity.getActivityMoney()));
            joinActivityDao.insertJoinActivity(joinActivity);
            joinActivity.setActivityMoney(MongoUtil.deConverterMap(joinActivity.getActivityMoney()));
        }
        return result;
    }

    /**
     * 添加通知回调
     * @param joinActivityEntered 通知
     */
    public int addJoinActivityEntered(JoinActivityEntered joinActivityEntered) {
        joinActivityEntered.setAddTime(System.currentTimeMillis());
        int result = validatedJoinActivityEntered(joinActivityEntered);
        if(result == 0) {
            joinActivityEntered.setEnteredMem(MongoUtil.enConverterMap(joinActivityEntered.getEnteredMem()));
            joinActivityDao.insertJoinActivityEntered(joinActivityEntered);
            joinActivityEntered.setEnteredMem(MongoUtil.deConverterMap(joinActivityEntered.getEnteredMem()));
            Activity activity = activityDao.findActivityOutActivityAndChannel(joinActivityEntered.getActivityId(), joinActivityEntered.getChannel());
            ActivitySignUp activitySignUp = activityDao.findActivitySignUp(activity.getId(), joinActivityEntered.getUid());
            if(activitySignUp == null) {
                activitySignUp = new ActivitySignUp();
                activitySignUp.setAddTime(System.currentTimeMillis());
                activitySignUp.setActivityId(activity.getId());
                activitySignUp.setUserId(joinActivityEntered.getUid());
                activitySignUp.setActivitySignUpNumber(joinActivityEntered.getEnteredNum());
                activityDao.insertActivitySignUp(activitySignUp);
            } else {
                activityDao.updateActivitySignUpNumber(activitySignUp.getId(), joinActivityEntered.getEnteredNum());
            }

        }
        return result;
    }

    /**
     * 添加第三方赛事访问日志
     * @param joinActivityViewLog 访问日志
     */
    public void addJoinActivityViewLog(JoinActivityViewLog joinActivityViewLog) {
        joinActivityDao.insertJoinActivityViewLog(joinActivityViewLog);
    }
}
