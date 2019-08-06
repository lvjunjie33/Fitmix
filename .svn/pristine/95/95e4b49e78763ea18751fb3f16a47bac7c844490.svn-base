package com.business.work.runLevel;

import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.task.TaskInfo;
import com.business.core.entity.task.TaskType;
import com.business.core.entity.user.RunLevelInfo;
import com.business.work.mapper.RunLevelInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangtao on 2016/11/29.
 */
@Service
public class RunLevelService extends AbstractServiceImpl<RunLevelInfo> {

    @Autowired
    private RunLevelInfoMapper runLevelInfoMapper;

    @Override
    protected AbstractMapper<RunLevelInfo> getAbstractMapper() {
        return runLevelInfoMapper;
    }

    public void init() {
        List<RunLevelInfo> list = new ArrayList<>();
        for(TaskType taskType : TaskType.values()) {
            if(taskType.getTaskType() == TaskInfo.TYPE_HONOR) {
                RunLevelInfo runLevelInfo = new RunLevelInfo(taskType.toString(), taskType.getDesc(), "", System.currentTimeMillis(), System.currentTimeMillis());
                list.add(runLevelInfo);
            }
        }
        runLevelInfoMapper.insertBatch(list);
    }
}
