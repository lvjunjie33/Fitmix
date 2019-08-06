package com.business.work.task;

import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.task.TaskFinishDetails;
import com.business.work.mapper.TaskFinishDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangtao on 2016/11/16.
 */
@Service
public class TaskFinishDetailsService extends AbstractServiceImpl<TaskFinishDetails> {

    @Autowired
    private TaskFinishDetailsMapper taskFinishDetailsMapper;

    @Override
    protected AbstractMapper<TaskFinishDetails> getAbstractMapper() {
        return taskFinishDetailsMapper;
    }

    public List<TaskFinishDetails> statisticsBy(Long start, Long end) {
        return taskFinishDetailsMapper.statisticsBy(start, end);
    }
}
