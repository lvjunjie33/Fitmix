package com.business.app.mapper;

import com.business.core.abs.AbstractMapper;
import com.business.core.annotation.MyBatisRepository;
import com.business.core.entity.user.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangtao on 2016/11/16.
 */
@MyBatisRepository
public interface AccountMapper extends AbstractMapper<Account> {
    Account selectByUid(Integer uid);

    int reduceBalance(@Param("uid") Integer uid, @Param("coin") Integer coin);

    int augmentBalance(@Param("uid")Integer uid, @Param("coin") Integer coin);

    void insertBatch(List<Account> accountList);

    void updateCoinBatch(List<Account> list);

    List<Account> selectByUidIn(@Param("start") Integer start, @Param("end") Integer end);
}
