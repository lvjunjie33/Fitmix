package com.business.app.mapper;

import com.business.core.abs.AbstractMapper;
import com.business.core.annotation.MyBatisRepository;
import com.business.core.entity.task.TaskFinishDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangtao on 2016/11/16.
 */
@MyBatisRepository
public interface TaskFinishDetailsMapper extends AbstractMapper<TaskFinishDetails> {
    TaskFinishDetails selectTaskFinishDetailsBy(Map<String, Object> map);

    void insertBatch(List<TaskFinishDetails> taskFinishDetailsesList);
}
