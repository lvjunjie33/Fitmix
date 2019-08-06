package com.business.work.task;

import com.business.core.entity.task.TaskFinishDetails;
import com.business.core.entity.task.TaskInfo;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.work.base.support.BaseControllerSupport;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fenxio on 2016/11/9.
 */
@Controller
@RequestMapping("task")
public class TaskController extends BaseControllerSupport {

    @Autowired
    private TaskInfoService taskInfoService;

    @Autowired
    private TaskFinishDetailsService taskFinishDetailsService;


    /**
     * 任务列表
     * @return
     */
    @RequestMapping("task-list")
    public String taskList(Model model) {
        model.addAttribute("list", taskInfoService.list());
        return "task/task-list";
    }

    /**
     * 添加任务信息
     * @param taskInfo
     */
    @RequestMapping("task-add")
    public void taskAdd(TaskInfo taskInfo) {
        taskInfo.setAddTime(System.currentTimeMillis());
        taskInfoService.insertSelective(taskInfo);
    }

    /**
     * 修改任务信息
     * @param taskInfo
     */
    @RequestMapping("task-modify")
    public void taskModify(TaskInfo taskInfo) {
        taskInfoService.updateByPrimaryKeySelective(taskInfo);
    }

    /**
     * 获取任务信息
     * @param id
     * @return
     */
    @RequestMapping("task-info")
    public TaskInfo getTaskInfo(@RequestParam("id") Integer id) {
        return taskInfoService.selectByPrimaryKey(id);
    }

    /**
     * 初始化任务信息
     */
    @RequestMapping("init")
    public void init() {
        taskInfoService.init();
    }

    @RequestMapping("statistics")
    public String search(String startTime,
                         String endTime,
                         Model model) {
        Long start = 0L;
        Long end = 0L;
        if(startTime != null && !StringUtils.isEmpty(startTime)) {
            start = DateUtil.parse(startTime, "yyyy-MM-dd").getTime();
        }
        if(endTime != null && !StringUtils.isEmpty(endTime)) {
            end = DateUtil.parse(endTime, "yyyy-MM-dd").getTime();
        }

        List<TaskFinishDetails> list = taskFinishDetailsService.statisticsBy(start, end);
        List<TaskInfo> taskInfoList = taskInfoService.list();
        Map<String, TaskInfo> taskInfoMap = CollectionUtil.buildMap(taskInfoList, String.class, TaskInfo.class, "taskKey");
        for(TaskFinishDetails taskFinishDetails : list) {
            taskFinishDetails.setTaskInfo(taskInfoMap.get(taskFinishDetails.getTaskKey()));
        }
        model.addAttribute("list", list);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        return "task/taskStatistics";
    }

}
