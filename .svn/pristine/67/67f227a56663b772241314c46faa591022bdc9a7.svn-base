package com.business.app.userSkipRope;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.user.UserHeartRate;
import com.business.core.entity.user.UserRun;
import com.business.core.redis.RedisMsgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2016/5/23.
 */
@Service
public class UserSkipRopeService {

    @Autowired
    private UserSkipRopeDao userSkipRopeDao;
    /**
     * 记录跳绳信息
     *
     * @param fileMap 文件保存）
     * @param uid 用户编号
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param calorie 卡路里
     * @param runTime 运动时间
     * @param skipNum 跳跃次数
     * @param bpm 平均频率
     * @param bpmMatch bpm匹配度
     * @param type 运动类型
     * @param consumeFat 燃烧脂肪量
     */
    public Object[] addSkipRope(Map<String, MultipartFile> fileMap, Integer uid, Long startTime, Long endTime, Long calorie, Long runTime,
                                Long skipNum, Integer bpm, Double bpmMatch, Integer type, Double consumeFat, UserHeartRate userHeartRate, Boolean isIOS) {
        MultipartFile skipDetail = fileMap.get("file");
        MultipartFile heartRateFile = fileMap.get("heartRateFile");

        UserRun beforeUserRun = userSkipRopeDao.findByUidAndStartTime(uid, startTime);
        if (beforeUserRun != null) {
            beforeUserRun.setSkipDetail(FileCenterClient.buildUrl(beforeUserRun.getSkipDetail()));
            return new Object[]{2, beforeUserRun};
        }

        String skipDetailFile = "";
        if (skipDetail != null) {
            skipDetailFile = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_SKIP_ROPE_SKIP_DETAIL, skipDetail);
        }

        UserRun userRun = new UserRun();

        userRun.setHeartRate(userHeartRate);
        if (userHeartRate != null) {
            if (StringUtils.isEmpty(userHeartRate.getCurrentAge())){
                userHeartRate.setCurrentAge(UserHeartRate.DEFAULT_AGE);
            }
            if (StringUtils.isEmpty(userHeartRate.getCurrentRestHeartRate())) {
                userHeartRate.setCurrentRestHeartRate(UserHeartRate.DEFAULT_REST_HEART_RATE);
            }
        }

        userRun.setSkipDetail(skipDetailFile);
        userRun.setUid(uid);
        userRun.setStartTime(startTime);
        userRun.setEndTime(endTime);
        userRun.setCalorie(calorie);
        userRun.setBpmMatch(bpmMatch);

        userRun.setRunTime(runTime);
        userRun.setSkipNum(skipNum);
        userRun.setBpm(bpm);

        userRun.setType(type);
        userRun.setAddTime(System.currentTimeMillis());

        //默认值
        userRun.setAddTime(System.currentTimeMillis());
        userRun.setUpdateTime(System.currentTimeMillis());
        userRun.setState(UserRun.STATE_EFFECTIVE);

        beforeUserRun = userSkipRopeDao.findByUidAndStartTime(uid, startTime);
        if (beforeUserRun != null) {
            beforeUserRun.setSkipDetail(FileCenterClient.buildUrl(beforeUserRun.getSkipDetail()));
            return new Object[]{2, beforeUserRun};
        }

        {//设置用户运动燃烧脂肪量，默认为0
            if (null == consumeFat) {
                consumeFat = 0D;
            }
            userRun.setConsumeFat(consumeFat);
        }

        if (isIOS) {
            userRun.setDeviceType(Constants.TERMINAL_IOS);
        } else {
            userRun.setDeviceType(Constants.TERMINAL_ANDROID);
        }

        userSkipRopeDao.insert(userRun);
        userRun.setSkipDetail(FileCenterClient.buildUrl(userRun.getSkipDetail()));

        //更新最后运动数据、以及总运动数据
        //更新俱乐部信息
        //运动榜单
        //异步任务
        RedisMsgManager.sendUserRunTask(userRun);

        return new Object[]{0, userRun};
    }

    /**
     * 删除跳绳记录
     *
     * @param uid 用户编号
     * @param startTime 运动开始时间
     */
    public void removeSkipRope(Integer uid, Long startTime) {
        userSkipRopeDao.updateByUidAndStartTime(uid, startTime, Update.update("state", Constants.STATE_INVALID).set("updateTime", System.currentTimeMillis()));
    }

    /**
     * 根据 uid ，和 运动开始时间删除 更新历史记录 [多个]
     * @param uid 用户编号
     * @param startTime 运动开始时间
     */
    public void removeSkipRopes (Integer uid, Long[] startTime) {
        if (startTime == null || startTime.length == 0) {
            return;
        }
        userSkipRopeDao.updateByUidAndStartTimes(uid, Arrays.asList(startTime), Update.update("state", Constants.STATE_INVALID).set("updateTime", System.currentTimeMillis()));
    }

    /**
     * 历史跳绳数据 , lastLoginTime 空则返回 所有
     * @param uid 用户编号
     * @param lastAddTime 分页
     * @return 所有跳绳数据
     */
    public Object[] historySkipRope(Integer uid, Long lastAddTime) {
        // lastAddTime 查询 updateTime 和 addTime
        UserRun lastUpdateSkipeRope = userSkipRopeDao.findLastUpdateSkipRope(uid, "updateTime"); // 最后更新记录
        if (lastAddTime != null) {
            List<UserRun> newSkipRopes = userSkipRopeDao.findNewSkipRope(uid, lastAddTime);
            for (UserRun userRun : newSkipRopes) {
                if (null != userRun.getSkipDetail()) {
                    userRun.setSkipDetail(FileCenterClient.buildUrl(userRun.getSkipDetail()));
                }
                if (null != userRun.getHeartRate() && null != userRun.getHeartRate().getHeartRateFileLink()) {
                    userRun.getHeartRate().setHeartRateFileLink(FileCenterClient.buildUrl(userRun.getHeartRate().getHeartRateFileLink()));
                }
            }
            List<UserRun> removeUserSkipRopeList = userSkipRopeDao.findRemoveUserSkipRope(uid, lastAddTime, "startTime");
            return new Object[]{newSkipRopes, removeUserSkipRopeList, lastUpdateSkipeRope};
        }
        else {
            List<UserRun> newSkipRopes = userSkipRopeDao.findByUidWidthStartTime(uid);
            for (UserRun userRun : newSkipRopes) {
                userRun.setSkipDetail(FileCenterClient.buildUrl(userRun.getSkipDetail()));
            }
            return new Object[]{newSkipRopes, lastUpdateSkipeRope};
        }
    }

}
