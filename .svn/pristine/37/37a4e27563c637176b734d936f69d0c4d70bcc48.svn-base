package com.business.work.mapper;

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

    int augmentBalance(@Param("uid") Integer uid, @Param("coin") Integer coin);

    List<Account> selectByStartAndEnd(@Param("start") int start, @Param("end") int end);

    long selectByCount(@Param("residualCoinMin") Integer residualCoinMin,
                       @Param("residualCoinMax") Integer residualCoinMax,
                       @Param("spendCoinMin") Integer spendCoinMin,
                       @Param("spendCoinMax") Integer spendCoinMax,
                       @Param("uid") Integer uid);

    List<Account> selectBy(@Param("residualCoinMin") Integer residualCoinMin,
                           @Param("residualCoinMax") Integer residualCoinMax,
                           @Param("spendCoinMin") Integer spendCoinMin,
                           @Param("spendCoinMax") Integer spendCoinMax,
                           @Param("uid") Integer uid,
                           @Param("start") Integer start,
                           @Param("size") Integer size);
}
