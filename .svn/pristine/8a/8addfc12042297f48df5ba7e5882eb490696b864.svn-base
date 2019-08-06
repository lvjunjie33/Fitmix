package com.business.app.event;

import com.business.app.activity.ActivityService;
import com.business.core.entity.activity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by wjcaozhi1314 on 2016/1/20.
 * TODO 后续该类可以废弃了, 该类不提供api接口文档
 */
@ApiIgnore
@Controller
@RequestMapping("event")
public class EventController {

    @Autowired
    private ActivityService activityService;

    /***
     * 鹏程义工跑赛事
     *
     * @param activityId    赛事id
     * @param register      是否已经报名
     * @param titleImg      赛事title照片
     * @param diffHour      赛事是否已经开始状态
     * @param startDiffHour 距离赛事开始时间 小时
     */
    @RequestMapping("event-independent")
    public String event(Model model, @RequestParam Integer activityId, @RequestParam String register,
                        @RequestParam String titleImg, @RequestParam String diffHour,
                        @RequestParam String startDiffHour,
                        @RequestParam boolean apply) {

        Activity activity =activityService.getActivity(activityId);

        //存储页面
        model.addAttribute("activityId", activityId);
        model.addAttribute("register", register);
        model.addAttribute("titleImg", titleImg);
        model.addAttribute("diffHour", diffHour);
        model.addAttribute("apply", apply);


        String returnValue = "event/event-independent";
        //设置跳转地址
        if (activity.getUrl().indexOf("http") == 0) {
            model.addAttribute("url",activity.getUrl());
            returnValue = "event/httpEvent/httpEvent";
        } else if(activity.getUrl().equals("event-jd-run")) {
            returnValue = "event/jd/run";
            model.addAttribute("uid", "");
        }

        //根据赛事id 查询单个赛事
        return returnValue;
    }

    @RequestMapping("supplementary-user-info")
    public String supplementaryUserInfo() {
        return "event/jd/info";
    }
}
