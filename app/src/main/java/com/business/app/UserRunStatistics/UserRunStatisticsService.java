package com.business.app.UserRunStatistics;

import com.business.app.mapper.UserRunStatisticsMapper;
import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.task.TaskType;
import com.business.core.entity.user.UserRunStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhangtao on 2016/11/30.
 */
@Service
public class UserRunStatisticsService extends AbstractServiceImpl<UserRunStatistics> {

    @Autowired
    private UserRunStatisticsMapper userRunStatisticsMapper;

    @Override
    protected AbstractMapper<UserRunStatistics> getAbstractMapper() {
        return userRunStatisticsMapper;
    }

    /**
     * 获取用户跑步统计记录
     * @param uid
     * @return
     */
    public UserRunStatistics getUserRunStatisticsByUid(Integer uid) {
        UserRunStatistics userRunStatistics = userRunStatisticsMapper.selectByUid(uid);
        if(userRunStatistics == null) {
            userRunStatistics = new UserRunStatistics(uid, 0L, 0, 0, TaskType.RUN_LEVEL_1.toString() , System.currentTimeMillis(), System.currentTimeMillis());
        }
        return userRunStatistics;
    }

    /**
     * 创建用户跑步统计
     * @param uid
     * @return
     */
    public UserRunStatistics createUserRunStatistics(Integer uid) {
        UserRunStatistics userRunStatistics = new UserRunStatistics(uid, 0L, UserRunStatistics.TEN_MILE_ABILITY_LEVEL_DEFAULT,
                UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_DEFAULT, TaskType.RUN_LEVEL_1.toString(), System.currentTimeMillis(), System.currentTimeMillis());
        userRunStatisticsMapper.insertSelective(userRunStatistics);
        return userRunStatistics;
    }


    /**
     * 构建 链接
     * @param path
     * @return
     */
    public String bluidUrl(String path) {
        return FileCenterClient.buildUrl(path);
    }


}
