package com.business.work.message;

import com.business.core.entity.auth.Admin;
import com.business.work.base.support.BaseControllerSupport;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by edward on 2016/5/20.
 */
@Controller
@RequestMapping()
public class MessageController extends BaseControllerSupport{

    @Autowired
    private MessageService messageService;

    /**
     * 推送管理
     */
    @RequestMapping("push/manager")
    public String manager() {
        return "message/manager";
    }

    /**
     * 推送 话题推荐
     */
    @RequestMapping("push/theme")
    public void pushThemeRecommend(@RequestParam(value = "targetUid", required = false) Integer targetUid, Long themeId) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        messageService.pushThemeRecommend(targetUid, themeId, admin.getId());
    }

    /**
     * 推送 通知
     */
    @RequestMapping("push/notice")
    public void pushNotice() {

    }

    /**
     * 推送 外部 link
     */
    @RequestMapping("push/outer/link")
    public void pushOuterLink(@RequestParam(value = "targetUid", required = false) Integer targetUid, String title, String content, String link) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        messageService.pushOtherLink(targetUid, title, content, link, admin.getId());
    }

    /**
     * 推送 赛事
     */
    @RequestMapping("push/activity")
    public void pushActivity(@RequestParam(value = "targetUid", required = false) Integer targetUid, Integer activityId) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        messageService.pushActivity(targetUid, activityId, admin.getId());
    }

    /**
     * 推送Mix
     *
     * @param targetUid 用户编号
     * @param mixId mix编号
     */
    @RequestMapping("push/mix")
    public void pushMix(@RequestParam(value = "targetUid", required = false) Integer targetUid, Integer mixId) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        messageService.pushMix(targetUid, mixId, admin.getId());
    }

    /**
     *  推送 电台
     *
     * @param targetUid 用户编号
     * @param radioId 电台编号
     */
    @RequestMapping("push/radio")
    public void pushRadio(@RequestParam(value = "targetUid", required = false) Integer targetUid, Integer radioId) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        messageService.pushRadio(targetUid, radioId, admin.getId());
    }

    /**
     * 推送 视频
     *
     * @param targetUid 用户编号
     * @param videoId 视频编号
     */
    @RequestMapping("push/video")
    public void pushVideo(@RequestParam(value = "targetUid", required = false) Integer targetUid, Integer videoId) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        messageService.pushVideo(targetUid, videoId, admin.getId());
    }

    /**
     * 推送 训练计划每日通知
     *
     * @param runPlanId 训练计划编号
     */
    @RequestMapping("push/run/plan")
    public void pushRunPlan(Integer runPlanId) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        messageService.pushRunPlan(runPlanId, admin.getId());
    }
}
