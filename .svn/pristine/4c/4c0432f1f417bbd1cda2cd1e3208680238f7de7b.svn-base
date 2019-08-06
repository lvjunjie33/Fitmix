package com.business.work.shop.account;

import com.business.core.abs.AbstractMapper;
import com.business.core.abs.AbstractServiceImpl;
import com.business.core.entity.Page;
import com.business.core.entity.user.*;
import com.business.core.utils.CollectionUtil;
import com.business.work.mapper.AccountMapper;
import com.business.work.user.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by zhangtao on 2016/11/28.
 */
@Service
public class AccountService extends AbstractServiceImpl<Account> {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserService userService;

    @Override
    protected AbstractMapper<Account> getAbstractMapper() {
        return accountMapper;
    }


    public List<Account> selectByStartAndEnd(int start, int end) {
        return accountMapper.selectByStartAndEnd(start, end);
    }

    public void findAccountBy(Page<Account> page) {
        Integer residualCoinMin = -1;
        Integer residualCoinMax = -1;
        Integer spendCoinMin = -1;
        Integer spendCoinMax = -1;
        Integer uid = -1;

        Map<String, Object> filter = page.getFilter();
        if (filter.containsKey("residualCoinMin") && !filter.get("residualCoinMin").equals("")) {
            residualCoinMin = Integer.parseInt((String) filter.get("residualCoinMin"));
        }
        if (filter.containsKey("residualCoinMax") && !filter.get("residualCoinMax").equals("")) {
            residualCoinMax = Integer.parseInt((String) filter.get("residualCoinMax"));
        }
        if (filter.containsKey("spendCoinMin") && !filter.get("spendCoinMin").equals("")) {
            spendCoinMin = Integer.parseInt((String) filter.get("spendCoinMin"));
        }
        if (filter.containsKey("spendCoinMax") && !filter.get("spendCoinMax").equals("")) {
            spendCoinMax = Integer.parseInt((String) filter.get("spendCoinMax"));
            if(spendCoinMin == -1) {
                spendCoinMin = 0;
            }
        }
        if (filter.containsKey("uid") && !filter.get("uid").equals("")) {
            uid = Integer.parseInt((String) filter.get("uid"));
        }

        List<Account> list = accountMapper.selectBy(residualCoinMin, residualCoinMax, spendCoinMin, spendCoinMax, uid, page.getIndex(), page.getSize());
        PageInfo<Account> pageInfo = new PageInfo<>(list);
        page.setTotal(pageInfo.getTotal());
        if(list != null && list.size() > 0) {
            Set<Integer> uids = CollectionUtil.buildSet(list, Integer.class, "uid");
            List<User> userList = userService.findUserByIdIn(uids);
            Map<Integer, User> userMap = CollectionUtil.buildMap(userList, Integer.class, User.class, "id");
            for(Account account : list) {
                account.setUser(userMap.get(account.getUid()));
            }
        }

        page.setResult(list);


    }
}
