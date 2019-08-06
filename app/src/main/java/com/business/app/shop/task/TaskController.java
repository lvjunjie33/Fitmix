package com.business.app.shop.task;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.context.SystemContextHolder;
import com.business.app.base.excetion.AppBusinessExcetion;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.task.TaskFinishDetails;
import com.business.core.entity.task.TaskInfo;
import com.business.core.entity.user.AccountFlow;
import com.business.core.operations.language.CharTableCoreService;
import com.business.core.utils.CodeMessageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fenxio on 2016/11/10.
 */
@Api(value = "task", description = "任务接口")
@Controller
@RequestMapping("task")
public class TaskController extends BaseControllerSupport{

    @Autowired
    private TaskInfoService taskInfoService;
    @Autowired
    private TaskFinishDetailsService taskFinishDetailsService;
    @Autowired
    private CharTableCoreService charTableCoreService;

    /**
     * 完成任务入口
     * @param taskKey 任务关键字
     * @param taskType 任务类型
     * @param uid 用户编号
     * @param additionId 附加编号
     */
    @RequestMapping(value = "/task-finish", method = RequestMethod.POST)
    public void task(Model model, @RequestParam("taskKey") String taskKey,
                     @RequestParam("taskType") Integer taskType,
                     @RequestParam("uid") Integer uid,
                     @RequestParam(value = "additionId", required = false) String additionId) {
        if (taskKey.contains("RUN_LEVEL")) {
            throw new AppBusinessExcetion(CodeConstants.INVALID_TASK, CodeMessageUtil.format(CodeConstants.INVALID_TASK, SystemContextHolder.get().getLanguage()));
        }
        AccountFlow accountFlow = taskInfoService.updateUserTaskStatus(uid, taskKey, taskType, additionId);
        model.addAttribute("accountFlow", accountFlow);
    }

    /**
     * 获取任务列表
     *
     * @param uid 用户编号
     * @param type 任务类型（不传返回全部类型）
     */
    @RequestMapping(value = "/task-list", method = RequestMethod.GET)
    public void getTaskList(Model model, HttpServletRequest request, @RequestParam("uid") Integer uid, @RequestParam(value = "type", required = false) Integer type) {
        List<TaskInfo> list = taskInfoService.getUserTaskInfo(uid, type);
        if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
            charTableCoreService.taskInfosToEn(list);
        }
        model.addAttribute("taskList", list);
    }

    /**
     * 根据 taskKey 获取任务信息
     * @param taskKey 任务关键字
     */
    @RequestMapping("/get-task")
    public void getTask(Model model, HttpServletRequest request, @RequestParam("taskKey") String taskKey) {
        TaskInfo taskInfo = taskInfoService.selectTaskInfoByTaskKey(taskKey);
        if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
            charTableCoreService.taskInfoToEn(taskInfo);
        }
        model.addAttribute("taskInfo", taskInfo);
    }


    /**
     * 获取已完成任务列表
     * @param uid 用户编号
     */
    @ApiOperation(value = "获取已完成任务列表", notes = "获取已完成任务列表", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping(value = "/task-finish-details-list", method = RequestMethod.GET)
    public void getTaskFinishDetailsList(Model model, HttpServletRequest request, @RequestParam("uid") Integer uid, @RequestParam("pageNum") Integer pageNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        PageHelper.startPage(pageNum, Page.DEFAULT_PAGE_SIZE);
        List<TaskFinishDetails> list = taskFinishDetailsService.selectByParams(map);
        PageInfo<TaskFinishDetails> pageInfo = new PageInfo<>(list);

        if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
            for (TaskFinishDetails detail :  list) {
                if (detail.getTaskInfo() != null) {
                    charTableCoreService.taskInfoToEn(detail.getTaskInfo());
                }
            }
        }

        model.addAttribute("page", pageInfo);
    }

}
