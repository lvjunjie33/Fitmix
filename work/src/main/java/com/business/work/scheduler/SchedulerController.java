package com.business.work.scheduler;

import com.business.work.base.support.BaseControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 定时器Controller
 * User: sin
 * Date: 15-8-27
 * Time: 上午10:49
 */
@Controller
@RequestMapping("scheduler")
public class SchedulerController extends BaseControllerSupport {
/*
    @Autowired
    private SchedulerService schedulerService;

    *//**
     * 定时任务分页
     *
     * @param page 分页对象
     *//*
    @RequestMapping("list")
    public String list(Page<Task> page) {
        page.removeEmptys("status", "currentStatus", "topicName").convertInt("status", "currentStatus");
        schedulerService.list(page);
        return "scheduler/list";
    }

    *//**
     * 添加定时任务
     *
     * @param cron 表达式
     * @param topicName 通道名称
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param des 描述
     *//*
    @RequestMapping("add")
    public void add(String cron, String topicName, String beginTime, String endTime, String des) {
        cron = cron.trim();
        schedulerService.addTask(cron, topicName, DateUtil.parse(beginTime, "yyyy-MM-dd HH:mm:ss").getTime(),
                DateUtil.parse(endTime, "yyyy-MM-dd HH:mm:ss").getTime(), des);
    }

    *//**
     * 修改定时任务
     *
     * @param id 任务编号
     * @param cron 表达式
     * @param topicName 消息通道名称
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param des 描述
     * @param status 任务状态
     *//*
    @RequestMapping(value = "modify",method = RequestMethod.POST)
    public void modify(Long id, String cron, String topicName, String beginTime, String endTime, String des, Integer status) {
        schedulerService.modify(id, cron, topicName, DateUtil.parse(beginTime, "yyyy-MM-dd HH:mm:ss").getTime(),
                DateUtil.parse(endTime, "yyyy-MM-dd HH:mm:ss").getTime(), des, status);
    }

    *//**
     * 修改定时任务
     *
     * @param id 任务编号
     *//*
    @RequestMapping(value = "modify",method = RequestMethod.GET)
    public String modify(Model model, Long id) {
        model.addAttribute("task", schedulerService.findTaskById(id));
        return "scheduler/modify";
    }


    ///操作

    *//**
     * 停止定时任务
     *
     * @param id 任务编号
     * @param type {@link Task#CURRENT_STATUS_START}
     *//*
    @RequestMapping("cmd")
    public void taskCmd(Long id, Integer type) {
        schedulerService.taskCmd(id, type);
    }

    *//**
     * 重设cron 表达式
     *
     * @param id 任务编号
     * @param newCron 表达式
     *//*
    @RequestMapping("reset")
    public void resetCron(Long id, String newCron) {
        schedulerService.resetCron(id, newCron);
    }

    *//**
     * 恢复数据
     *
     * @param id 任务编号
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @param offset 时间偏移量
     *//*
    @RequestMapping("restore-history")
    public void restoreHistory(Long id, String bTime, String eTime, Long offset) {
        schedulerService.restoreHistory(id, bTime, eTime, offset);
    }*/

}
