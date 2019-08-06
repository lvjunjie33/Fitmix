package com.business.work.task;

import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.task.TaskInfo;
import com.business.core.entity.task.TaskType;
import com.business.work.mapper.TaskFinishDetailsMapper;
import com.business.work.mapper.TaskInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by fenxio on 2016/11/9.
 */
@Service
public class TaskInfoService extends AbstractServiceImpl<TaskInfo>{

    @Autowired
    private TaskInfoMapper taskInfoMapper;
    @Autowired
    private TaskFinishDetailsMapper taskFinishDetailsMapper;


    @Override
    protected AbstractMapper<TaskInfo> getAbstractMapper() {
        return taskInfoMapper;
    }


    public TaskInfo selectTaskInfoByTaskKey(String taskKey) {
        return taskInfoMapper.selectTaskInfoByTaskKey(taskKey);
    }

    /**
     * 初始化
     */
    public void init() {
        for(TaskType taskType : TaskType.values()) {
            TaskInfo taskInfo = taskInfoMapper.selectTaskInfoByTaskKey(taskType.toString());
            if(taskInfo == null) {
                taskInfo = new TaskInfo(System.currentTimeMillis(), System.currentTimeMillis(), TaskInfo.STATUS_INVALID,
                        0, taskType.toString(), taskType.getTaskType(), taskType.getDesc());
                taskInfoMapper.insertSelective(taskInfo);
            }
        }

    }



}
