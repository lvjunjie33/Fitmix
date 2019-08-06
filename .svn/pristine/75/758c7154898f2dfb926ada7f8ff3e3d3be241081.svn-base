package com.business.app.mapper;


import com.business.core.abs.AbstractMapper;
import com.business.core.annotation.MyBatisRepository;
import com.business.core.entity.user.UserRunStatistics;

import java.util.List;

@MyBatisRepository
public interface UserRunStatisticsMapper extends AbstractMapper<UserRunStatistics> {

    void insertBatch(List<UserRunStatistics> userRunStatisticsList);

    UserRunStatistics selectByUid(Integer uid);

    int updateDistanceAndRunLevelById(UserRunStatistics userRunStatistics);
}