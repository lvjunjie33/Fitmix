package com.business.work.runPlan;

import com.business.core.entity.Page;
import com.business.core.entity.runPlan.*;
import com.business.core.entity.user.UserRun;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by weird on 2016/5/17.
 */
@Controller
@RequestMapping("run-plan")
public class RunPlanController extends BaseControllerSupport{

    @Autowired
    private RunPlanService runPlanService;

    /**
     * 跑步计划模板列表
     *
     * @param page
     * @return
     */
    @RequestMapping("run-plan-templet")
    public String runPlanTempletPage(Page<RunPlanTemplate> page) {
        page.convertInt("id", "type", "classify", "beginTime");
        runPlanService.templetPage(page);
        return "runPlan/run-plan-list";
    }

    /**
     * get runPlanTemplet by id
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("templet-modify-content")
    public String templetModifyContent(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("stage", runPlanService.findRunPlanTempletById(id));
        model.addAttribute("tid", id);
        return "runPlan/content-modify";
    }

    /**
     * update runPlanTemplet
     *
     * @param stageName
     * @param result
     * @param tid
     */
    @RequestMapping("templet-modify")
    public void templetModify(@RequestParam("stageName") String stageName,
                              @RequestParam("result") String result,
                              @RequestParam("tid") Integer tid) {
        runPlanService.changeToRunPlanTemplet(stageName, result, tid);

    }

    /**
     * runPlanSpeed List(page)
     *
     * @param page
     * @return
     */
    @RequestMapping("run-plan-speed")
    public String runPlanSpeed(Page<RunPlanSpeed> page) {
        page.convertInt("id", "type", "classify", "ages", "gender");
        runPlanService.runPlanSpeedPage(page);
        return "runPlan/run-plan-speed";
    }

    /**
     * get runPlanSpeed by id
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("speed-modify")
    public String speedModify(Model model,
                              @RequestParam("id") Integer id) {
        model.addAttribute("speedModify", runPlanService.findRunPlanSpeedById(id));
        return "runPlan/speed-modify";
    }

    /**
     * update runPlanSpeed
     *
     * @param id
     * @param type
     * @param ages
     * @param gender
     * @param speed
     */
    @RequestMapping("change-speed")
    public void changeSpeed(@RequestParam("id") Integer id,
                            @RequestParam("type") Integer type,
                            @RequestParam("classify") Integer classify,
                            @RequestParam("ages") Integer ages,
                            @RequestParam("gender") Integer gender,
                            @RequestParam("speed") String speed) {
        runPlanService.changeRunPlanSpeed(id, type, classify, gender, ages, speed);
    }

    /**
     * 跑步计划阶段速度比值管理(分页)
     *
     * @param page
     * @return
     */
    @RequestMapping("run-plan-stage-ratio")
    public String runPlanStageRatio(Page<RunPlanStageRatio> page) {
        page.convertInt("type");
        runPlanService.stageRatioPage(page);
        return "runPlan/run-plan-stage-ratio";
    }

    /**
     * get runPlanStageRatio by type
     *
     * @param model
     * @param type
     * @return
     */
    @RequestMapping("stage-ratio-modify")
    public String StageRatioModify(Model model, @RequestParam("type") Integer type) {
        model.addAttribute("stageRatio", runPlanService.findStageRatioByType(type));
        return "runPlan/stage-ratio-modify";
    }

    /**
     * RunPlanStageRatio modify
     *
     * @param result
     */
    @RequestMapping("change-stage-ratio")
    public void changeStageRatio(@RequestParam("result") String result) {
        runPlanService.changeStageRatio(result);
    }

    /**
     * RunPlanClassify列表(分页)
     *
     * @param page
     * @return
     */
    @RequestMapping("run-plan-classify")
    public String runPlanClassifyPage(Page<RunPlanClassify> page) {
        page.convertInt("id", "type", "classify");
        runPlanService.runPlanClassifyPage(page);
        return "runPlan/run-plan-classify";
    }

    /**
     * 添加RunPlanClassify
     *
     * @param type
     * @param ability
     * @param classify
     * @param speed_min
     * @param speed_max
     */
    @RequestMapping("classify-add")
    public void classifyAdd(@RequestParam("type") Integer type,
                            @RequestParam("ability") Integer ability,
                            @RequestParam("classify") Integer classify,
                            @RequestParam("speed_min") Integer speed_min,
                            @RequestParam("speed_max") Integer speed_max) {
        runPlanService.insertRunPlanClassify(type, ability, classify, speed_min, speed_max);
    }

    /**
     * 修改RunPlanClassify
     *
     * @param cid
     * @param type
     * @param ability
     * @param classify
     * @param speed_min
     * @param speed_max
     */
    @RequestMapping("classify-modify")
    public void classifyModify(@RequestParam("cid") Integer cid,
                               @RequestParam("type") Integer type,
                               @RequestParam("ability") Integer ability,
                               @RequestParam("classify") Integer classify,
                               @RequestParam("speed_min") Integer speed_min,
                               @RequestParam("speed_max") Integer speed_max) {
        runPlanService.updateRunPlanClassify(cid, type, ability, classify, speed_min, speed_max);
    }

    /**
     * runPlan界面
     *
     * @return
     */
    @RequestMapping("run-plan")
    public String runPlan() {
        return "runPlan/run-plan-customization";
    }

    /**
     * 生成跑步计划
     *
     * @param model
     * @param type
     * @param ability
     * @param gender
     * @param age
     * @param ability_time
     * @return
     */
    @RequestMapping("createRunPlan")
    public String creatRunPlan(Model model,
                               @RequestParam("type") Integer type,
                               @RequestParam("ability") Integer ability,
                               @RequestParam("gender") Integer gender,
                               @RequestParam("age") Integer age,
                               @RequestParam("ability_time") String ability_time) {

        //model.addAttribute("runPlanModule",runPlanService.getContent(type,ability,ability_time));
/*        model.addAttribute("info",runPlanService.runPlanInfo(type,ability,ability_time,age,gender));
        model.addAttribute("speed",runPlanService.getSpeed(type,ability,ability_time,age,gender));*/

        return "runPlan/user-run-plan";
    }


/*    @RequestMapping("add-modify-content")
    public String moduleContent(Model model,
                                @RequestParam("type") Integer type,
                                @RequestParam("classify") Integer classify,
                                @RequestParam("beginTime") Integer beginTime,
                                @RequestParam("stage") Integer stage,
                                @RequestParam("stageCount") Integer[] stageContent){

        model.addAttribute("stageContent",runPlanService.getRunPlanModule(type,classify,beginTime,stage,stageContent));

        return "runPlan/module-add-content";
    }*/

    /**
     *  添加模板1
     * @param model
     * @param request
     * @param type 类型
     * @param classify 类别
     * @param beginTime 星期几
     * @param stage 阶段数
     * @param stageContent 阶段行数
     * @return
     */
    @RequestMapping("add-modify-content")
    public String moduleContent(Model model,
                                HttpServletRequest request,
                                @RequestParam("type") Integer type,
                                @RequestParam("classify") Integer classify,
                                @RequestParam("beginTime") Integer beginTime,
                                @RequestParam("stage") Integer stage,
                                @RequestParam("stageCount") Integer[] stageContent) {
        RunPlanTemplate runPlanTemplate = runPlanService.modelGeneration(type, classify, beginTime, stage, stageContent);
        request.getSession().setAttribute("runPlanTemplate", runPlanTemplate);
        model.addAttribute("stage", stageContent);
        return "runPlan/module-add-content";
    }



   /* @RequestMapping("create-run-plan")
    public String generateRunningPlan(Model model,
                                      @RequestParam("type") Integer type,
                                      @RequestParam("ability") Integer ability,
                                      @RequestParam("gender") Integer gender,
                                      @RequestParam("age") Integer age,
                                      @RequestParam("ability_time") String ability_time){
        model.addAttribute("runPlan",runPlanService.createRunPlan(type,ability,ability_time,age,gender));
        return "runPlan/user-run-plan";
    }*/

    /**
     *   添加模板2
     * @param request
     * @param stageName
     * @param content
     */
    @RequestMapping("add-module")
    public void addModule(
                          HttpServletRequest request,
                          @RequestParam("stageName") String[] stageName,
                          @RequestParam("content") String[] content) {
        Object object = request.getSession().getAttribute("runPlanTemplate");
        if(object != null) {
            RunPlanTemplate runPlanTemplate = (RunPlanTemplate) object;
            runPlanService.addModule(stageName, content, runPlanTemplate);
            request.getSession().removeAttribute("runPlanTemplate");
        }

    }

    /**
     *  额外阶段内容分页
     * @param page
     * @return
     */
    @RequestMapping("extra-stage-list")
    public String extraStageList(Page<RunPlanExtraStage> page) {
        page.convertInt("id", "type", "classify");
        runPlanService.extraPage(page);
        return "runPlan/extra-stage";
    }

    /**
     *  额外阶段列
     * @param model
     * @param type 类型
     * @param classify 类别
     * @param stageCount 阶段数
     * @return
     */
    @RequestMapping("extra-stage-page")
    public String addExtraStage(Model model,
                                @RequestParam("type") Integer type,
                                @RequestParam("classify") Integer classify,
                                @RequestParam("stage") Integer stageCount) {
        model.addAttribute("type", type);
        model.addAttribute("classify", classify);
        model.addAttribute("stage", stageCount);
        return "runPlan/add-extra-stage";
    }

    /**
     *  添加额外阶段内容
     * @param model
     * @param type 类型
     * @param classify 类别
     * @param stageName 阶段名
     * @param content 内容
     */
    @RequestMapping("add-extra-stage")
    public void extraStageAdd(Model model,
                              @RequestParam("type") Integer type,
                              @RequestParam("classify") Integer classify,
                              @RequestParam("stageName") String stageName,
                              @RequestParam("stageContent") String[] content
                              ) {
        runPlanService.addExtraStage(type, classify, stageName, content);
    }

    /**
     *  描述列表（分页）
     * @param page
     * @return
     */
    @RequestMapping("description-list")
    public String contentDesList(Page<RunPlanDescription> page) {
        page.convertInt("id");
        runPlanService.desPage(page);
        return "runPlan/des-list";
    }

    /**
     *  添加描述内容
     * @param key  所描述的跑步类型（慢跑、快速跑等）
     * @param des  描述内容
     */
    @RequestMapping("des-add")
    public void addDes(@RequestParam("key") String key,
                       @RequestParam("des") String des) {
        runPlanService.addDes(key, des);
    }

    /**
     *  用户计划列表
     * @param page
     * @return
     */
    @RequestMapping("list")
    public String userRunPlanList(Page<UserRunPlan> page) {
        page.removeEmptys("id", "uid", "plan_state", "eTime").convertInt("id","uid", "plan_state");
        Map<String, Object> filter = page.getFilter();
        if (!filter.containsKey("plan_state")) {
            filter.put("plan_state", 1);
        }
        if (filter.containsKey("eTime")) {
            filter.put("endTime", DateUtil.getDayBegin(DateUtil.parse(filter.get("eTime").toString(), "yyyy-MM-dd")).getTime());
        }
        runPlanService.userPlanPage(page);
        return "runPlan/user-run-plan-list";
    }

    /**
     * 获取训练计划
     *
     * @param id 编号
     */
    @RequestMapping("user-run-plan")
    public void userRunPlan(Model model, Integer id) {
        model.addAttribute("runPlan", runPlanService.findUserRunPlan(id));
    }

    /**
     * 修改训练计划状态
     *
     * @param id 编号
     */
    @RequestMapping("modify/status")
    public String modifyStatus(Integer id, Integer status) {
        runPlanService.modifyUserRunPlanStatus(id, status);
        return redirect("/run-plan/list.htm");
    }

    @RequestMapping("wc")
    public void wc() {
        BeanManager.getBean(DefaultDao.class).modifyMore(Query.query(Criteria.where("endTime").lt(1498320000000L)), Update.update("plan_state", UserRunPlan.PLAN_OFF_THE_STHCKS), UserRunPlan.class);
    }

    @RequestMapping("hh")
    public void hh() {
        final DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        final List<UserRunPlan> list = defaultDao.query(Query.query(Criteria.where("plan_state").is(UserRunPlan.PLAN_IMPLEMENTATION)), UserRunPlan.class, "uid");
        Date today = new Date();
        final Integer todayInt = DateUtil.formatDate2Int(today);
        System.out.println("总计划数量:" + list.size());
        int i = 1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    for (UserRunPlan plan : list) {
//                        if (plan.getId() != 13172) {
//                            continue;
//                        }
                        UserRunPlan userRunPlan = defaultDao.findById(UserRunPlan.class, plan.getId());
                        List<Stages> stages = userRunPlan.getStagesLists();
                        System.out.println("当前计划id:" + plan.getId() + ", uid:" + plan.getUid());
                        for (Stages s : stages) {
                            Date currentDate = DateUtil.parse(s.getDateTime(), "yyyy-MM-dd");
                            Integer currentDateInt = DateUtil.formatDate2Int(currentDate);
                            {
                                if (currentDateInt > todayInt) {
                                    continue;
                                }
//                                System.out.println("A");
                                if (Stages.STATE_SUCCESS.equals(s.getState())) {
                                    continue;
                                }
//                                System.out.println("B");
                                if (s.getStageContent().contains("休息")) {
                                    s.setState(Stages.STATE_SUCCESS);
                                    continue;
                                }
//                                System.out.println("C");

                                if(s.getDistance() <= 0) {
                                    continue;
                                }
//                                System.out.println("D");
                                Double distance = s.getDistance();
                                List<UserRun> userRuns = defaultDao.query(Query.query(Criteria.where("uid").is(plan.getUid())
                                        .and("endTime").gt(DateUtil.getDayBegin(currentDate).getTime()).lt(DateUtil.getDayEnd(currentDate).getTime())), UserRun.class, "distance");
//                                System.out.println("size:" + userRuns.size());
                                Double sumDistance = 0D;
                                for (UserRun u : userRuns) {
                                    sumDistance += u.getDistance();
//                                    System.out.println("sum:" + u.getDistance() + ",current:" + u.getDistance());
                                }
                                if (distance * 1000 <= sumDistance) {
                                    s.setState(Stages.STATE_SUCCESS);
                                }
//                                System.out.println("E" + "distance:" + (distance * 1000) + ", sumDistance:" + sumDistance);
                            }
                        }

                        System.out.println("F");
                        defaultDao.modifyFirst(Query.query(Criteria.where("id").is(plan.getId())), Update.update("stagesLists", stages), UserRunPlan.class);
                    }
                }
            }
        }).start();

    }

}
