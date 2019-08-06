package com.business.app.mapper;


import com.business.core.abs.AbstractMapper;
import com.business.core.annotation.MyBatisRepository;
import com.business.core.entity.user.RunLevelInfo;

@MyBatisRepository
public interface RunLevelInfoMapper extends AbstractMapper<RunLevelInfo>{

    RunLevelInfo selectByRunLevel(String runLevel);
}