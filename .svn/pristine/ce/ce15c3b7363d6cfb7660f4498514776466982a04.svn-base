package com.business.work.mapper;

import com.business.core.abs.AbstractMapper;
import com.business.core.annotation.MyBatisRepository;
import com.business.core.entity.task.TaskFinishDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangtao on 2016/11/16.
 */
@MyBatisRepository
public interface TaskFinishDetailsMapper extends AbstractMapper<TaskFinishDetails> {
    TaskFinishDetails selectTaskFinishDetailsBy(@Param("uid") Integer uid, @Param("typeKey") String typeKey, @Param("finishTime") String finishTime);

    List<TaskFinishDetails> statisticsBy(@Param("startTime") Long start, @Param("endTime") Long end);
}
