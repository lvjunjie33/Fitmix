package com.business.work.joinActivity;

import com.business.core.entity.Page;
import com.business.core.entity.joinActivity.JoinActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fenxio on 2016/7/7.
 */
@Controller
@RequestMapping("join-activity")
public class JoinActivityController {

    @Autowired
    private JoinActivityService joinActivityService;

    /**
     * 第三方赛事列表
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(Page<JoinActivity> page, Model model) {
        joinActivityService.list(page);
        return "joinActivity/join-activity-list";
    }

    /**
     * 修改第三方赛事状态
     * @param id 第三方赛事编号
     * @param status 状态
     */
    @RequestMapping("join-activity-modify-status")
    public void joinActivityModifyStatus(Long id, Integer status) {
        joinActivityService.modifyJoinActivtyStatus(id, status);
    }

}
