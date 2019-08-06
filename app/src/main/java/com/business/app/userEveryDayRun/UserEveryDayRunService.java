package com.business.app.userEveryDayRun;

import com.business.core.entity.user.UserEveryDayRun;
import com.business.core.utils.DateUtil;
import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by sin on 2015/12/25.
 */
@Service
public class UserEveryDayRunService {

    @Autowired
    private UserEveryDayRunDao userEveryDayRunDao;


    /**
     * 设置 everyDay 运动数据
     * <p>
     *     注意：
     *     1、当天没有任何信息，则为空
     *     2、每日首次记录新建、非首次记录 累加
     * </p>
     * @param runId 用户运动
     * @param uid 用户编号
     * @param runTime 运动时间
     * @param step 步数
     * @param calorie 卡路里
     * @param distance 距离
     * @param rememberStep 计步器 步数
     */
    public void setEveryDayRun(Long runId, Integer uid, Long runTime,
                           Long step, Long calorie, Long distance, Long rememberStep) {

        long beginTime = DateUtil.getDayBegin(new Date()).getTime();
        long endTime = DateUtil.getDayEnd(new Date()).getTime();

        UserEveryDayRun userEveryDayRun = userEveryDayRunDao.findByUid(uid, beginTime, endTime);
        // 是否第一次进入
        if (userEveryDayRun == null) {
            add(runId, uid, runTime, step, calorie, distance, rememberStep);
        }
        else {
            append(userEveryDayRun, runTime, step, calorie, distance, rememberStep);
        }
    }

    /**
     * 添加 用户每天运动
     * @param runId 用户运动
     * @param uid 用户编号
     * @param runTime 运动时间
     * @param step 步数
     * @param calorie 卡路里
     * @param distance 距离
     * @param rememberStep 计步器 步数
     */
    public void add(Long runId, Integer uid, Long runTime, Long step, Long calorie, Long distance, Long rememberStep) {
        UserEveryDayRun userEveryDayRun = new UserEveryDayRun();
        userEveryDayRun.setRunId(ImmutableSet.of(runId));
        userEveryDayRun.setUid(uid);
        userEveryDayRun.setRunTime(runTime);
        userEveryDayRun.setStep(step);
        userEveryDayRun.setCalorie(calorie);
        userEveryDayRun.setDistance(distance);

        userEveryDayRun.setCalorie(rememberStep);
        userEveryDayRun.setAddTime(System.currentTimeMillis());

        userEveryDayRunDao.insert(userEveryDayRun);
    }


    /**
     * 累加 用户每天运动
     * @param userEveryDayRun 累加对象
     * @param runTime 运动时间
     * @param step 步数
     * @param calorie 卡路里
     * @param distance 距离
     * @param rememberStep 计步器 步数
     */
    public void append(UserEveryDayRun userEveryDayRun, long runTime,
                       long step, long calorie, long distance, long rememberStep) {
        userEveryDayRunDao.updateById(userEveryDayRun.getId(),
                Update.update("runTime", userEveryDayRun.getRunTime() + runTime).
                        set("distance", userEveryDayRun.getDistance() + distance).
                        set("calorie", userEveryDayRun.getCalorie() + calorie).
                        set("step", userEveryDayRun.getStep() + step).
                        set("rememberStep", userEveryDayRun.getRememberStep() + rememberStep));
    }

    /**
     * 获取昨日用户数据
     * @param uid   用户id
     * @return  返回用户昨日运动相关数据
     */
    public UserEveryDayRun findUserRunInYesterday(Integer uid){
        long addTime = DateUtil.getDayBegin(DateUtil.getDayBefore(new Date())).getTime();
        UserEveryDayRun userEveryDayRun = userEveryDayRunDao.findByUid(uid,addTime,addTime,"runId", "runTime", "distance", "calorie", "step");
        return userEveryDayRun;
    }

}
