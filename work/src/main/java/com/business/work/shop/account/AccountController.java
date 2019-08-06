package com.business.work.shop.account;

import com.business.core.entity.Page;
import com.business.core.entity.user.Account;
import com.business.core.entity.user.AccountFlow;
import com.business.core.entity.user.User;
import com.business.core.operations.user.UserCoreService;
import com.business.core.utils.CollectionUtil;
import com.business.work.mapper.AccountFlowMapper;
import com.business.work.mapper.UserRunStatisticsMapper;
import com.business.work.user.UserDao;
import com.business.work.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangtao on 2016/12/22.
 */
@Controller
@RequestMapping("shop")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRunStatisticsMapper userRunStatisticsMapper;

    @Autowired
    private AccountFlowMapper accountFlowMapper;

    @Autowired
    private UserDao userDao;

    @RequestMapping("account")
    public void getAccount(int start, int end, Model model) {
        model.addAttribute("list", accountService.selectByStartAndEnd(start, end));
    }

    @RequestMapping("userStatisticsRun")
    public void getUserStatisticsRun(int start, int end, Model model) {
        model.addAttribute("list", userRunStatisticsMapper.selectByStartAndEnd(start, end));
    }

    @RequestMapping("search")
    public String search(Page<Account> page) {
        PageHelper.startPage(page.getPageNo(), Page.DEFAULT_PAGE_SIZE);
        accountService.findAccountBy(page);
        return "shop/account/statistics";
    }

    @RequestMapping("account-flow-list")
    public String accountFlowList(Page<AccountFlow> page) {
        PageHelper.startPage(page.getPageNo(), page.DEFAULT_PAGE_SIZE);
        List<AccountFlow> list = accountFlowMapper.selectByParams(page.getFilter());
        PageInfo pageInfo = new PageInfo(list);
        page.setTotal(pageInfo.getTotal());
        if(list != null && list.size() > 0) {
            User user = userDao.findUserByUid(list.get(0).getUid());
            for(AccountFlow accountFlow : list) {
                accountFlow.setUser(user);
            }
        }
        page.setResult(list);
        return "shop/account/accountFlowList";
    }

}
