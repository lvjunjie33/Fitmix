package com.business.app.shop.task;

import com.business.app.mapper.TaskFinishDetailsMapper;
import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.task.TaskFinishDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
