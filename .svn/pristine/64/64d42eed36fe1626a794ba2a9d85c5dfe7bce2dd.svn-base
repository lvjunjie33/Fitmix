package com.business.work.mapper;


import com.business.core.abs.AbstractMapper;
import com.business.core.annotation.MyBatisRepository;
import com.business.core.entity.user.RunLevelInfo;

import java.util.List;

@MyBatisRepository
public interface RunLevelInfoMapper extends AbstractMapper<RunLevelInfo>{

    RunLevelInfo selectByRunLevel(String runLevel);

    void insertBatch(List<RunLevelInfo> list);
}