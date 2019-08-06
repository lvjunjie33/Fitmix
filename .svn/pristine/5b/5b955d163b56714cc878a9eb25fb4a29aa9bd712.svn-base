package com.business.app.shop.account;

import com.business.app.base.support.BaseControllerSupport;
import com.business.app.shop.task.TaskInfoService;
import com.business.app.user.UserService;
import com.business.core.entity.Page;
import com.business.core.entity.task.TaskType;
import com.business.core.entity.user.Account;
import com.business.core.entity.user.AccountFlow;
import com.business.core.operations.language.CharTableCoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangtao on 2016/11/28.
 */
@Api(value = "account", description = "账号接口")
@Controller
@RequestMapping("account")
public class AccountController extends BaseControllerSupport {

    @Autowired
    private TaskInfoService taskInfoService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountFlowService accountFlowService;
    @Autowired
    private UserService userService;
    @Autowired
    private CharTableCoreService charTableCoreService;

    /**
     * 获取账户明细
     * @param uid 用户编号
     * @param pageNum 页码
     */
    @ApiOperation(value = "获取账户明细", notes = "获取账户明细", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping(value = "{uid}/accountFlow", method = RequestMethod.GET)
    public void getAccountFlow(@ApiParam(required = true, value = "用户编号") @PathVariable("uid") Integer uid,
                               @ApiParam(required = true, value = "页码") @RequestParam("pageNo") Integer pageNum,
                               @ApiIgnore Model model) {
        Account account = accountService.selectByUid(uid);
        PageHelper.startPage(pageNum, Page.DEFAULT_PAGE_SIZE).setOrderBy("add_time desc");
        HashMap<String, Object> map = new HashMap<>();
        map.put("aid", account.getId());
        List<AccountFlow> list = accountFlowService.selectByParams(map);
        PageInfo<AccountFlow> pageInfo = new PageInfo<>(list);
        charTableCoreService.accountFlowsToEn(list);
        model.addAttribute("page", pageInfo);
    }
    /**
     * 获取账号信息
     * @param uid 用户编号
     */
    @ApiOperation(value = "获取账户信息", notes = "获取账户信息", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping(value = "{uid}",method = RequestMethod.GET)
    public void getAccount(@ApiParam(required = true, value = "用户编号") @PathVariable("uid") Integer uid,
                           @ApiIgnore Model model) {

        model.addAttribute("account", accountService.selectByUid(uid));
        model.addAttribute(TaskType.EVERY_DAY_SIGN_IN.toString(), taskInfoService.getUserTaskInfo(uid, TaskType.EVERY_DAY_SIGN_IN.toString()));
    }
}
