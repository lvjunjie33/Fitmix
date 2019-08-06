package com.business.work.joinActivity;


import com.business.core.entity.Page;
import com.business.core.entity.activity.Activity;
import com.business.core.entity.joinActivity.JoinActivity;
import com.business.core.operations.activity.ActivityCoreDao;
import com.business.work.activity.ActivityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by fenxio on 2016/5/17.
 */
@Service
public class JoinActivityService {

    @Autowired
    private JoinActivityDao joinActivityDao;
    @Autowired
    private ActivityCoreDao activityCoreDao;

    /**
     * 分页信息
     * @param page
     */
    public void list(Page<JoinActivity> page) {
        joinActivityDao.findJoinActivityPage(page);
    }

    /**
     * 修改第三方赛事状态
     * @param id
     * @param status
     */
    public void modifyJoinActivtyStatus(Long id, Integer status) {
        if(JoinActivity.STATUS_HAS_JOIN == status) {
            joinActivityDao.modifyJoinActivityStatus(id, status);
            JoinActivity joinActivity = joinActivityDao.findJoinActivityById(id);
            Activity activity = activityCoreDao.findActivityOutActivityAndChannel(joinActivity.getActivityId(), joinActivity.getChannel());
            if(activity == null) {
                activity = new Activity();
                activity.setAddTime(System.currentTimeMillis());
                activity.setStatus(Activity.STATUS_NORMAL);
                activity.setReleaseStatus(Activity.RELEASE_STATUS_WAIT_RELEASE);
                activity.setType(Activity.TYPE_OUTER);
                activity.setThemeName(joinActivity.getThemeName());
                activity.setThemeImage(joinActivity.getThemeImage());
                activity.setActivityBeginTime(joinActivity.getActivityBeginTime());
                activity.setActivityEndTime(joinActivity.getActivityEndTime());
                activity.setSignUpBeginTime(joinActivity.getSignUpBeginTime());
                activity.setSignUpEndTime(joinActivity.getSignUpEndTime());
                activity.setUrl(joinActivity.getUrl());
                activity.setChannel(joinActivity.getChannel());
                activity.setDesc(joinActivity.getDesc());
                activity.setOutActivityId(joinActivity.getActivityId());
                activityCoreDao.insertActivity(activity);
            }
        } else {
            joinActivityDao.modifyJoinActivityStatus(id, status);
        }
    }
}
