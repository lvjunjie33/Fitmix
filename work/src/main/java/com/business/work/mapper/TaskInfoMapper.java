package com.business.work.mapper;

import com.business.core.abs.AbstractMapper;
import com.business.core.annotation.MyBatisRepository;
import com.business.core.entity.task.TaskInfo;

/**
 * Created by zhangtao on 2016/11/16.
 */
@MyBatisRepository
public interface TaskInfoMapper extends AbstractMapper<TaskInfo> {

    TaskInfo selectTaskInfoByTaskKey(String taskKey);
}
