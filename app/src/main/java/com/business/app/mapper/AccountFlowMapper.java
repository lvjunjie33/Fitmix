package com.business.app.mapper;

import com.business.core.abs.AbstractMapper;
import com.business.core.annotation.MyBatisRepository;
import com.business.core.entity.user.AccountFlow;

import java.util.List;

/**
 * Created by zhangtao on 2016/11/16.
 */
@MyBatisRepository
public interface AccountFlowMapper extends AbstractMapper<AccountFlow> {
    void insertBatch(List<AccountFlow> accountFlowList);
}
