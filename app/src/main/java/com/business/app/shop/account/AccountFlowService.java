package com.business.app.shop.account;

import com.business.app.mapper.AccountFlowMapper;
import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.user.AccountFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtao on 2016/11/28.
 */
@Service
public class AccountFlowService extends AbstractServiceImpl<AccountFlow> {

    @Autowired
    private AccountFlowMapper accountFlowMapper;

    @Override
    protected AbstractMapper<AccountFlow> getAbstractMapper() {
        return accountFlowMapper;
    }
}
