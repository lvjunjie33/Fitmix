
package com.business.app.runPlan;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.constants.Constants;
import com.business.core.entity.runPlan.Stages;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.entity.user.User;
import com.business.core.operations.language.CharTableCoreService;
import com.business.core.operations.user.UserCoreDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by weird on 2016/5/11.
 */
@Api(value = "RunPlan", description = "跑步计划")
@Controller
@RequestMapping("run-plan")
public class RunPlanController extends BaseControllerSupport {

    @Autowired
    private RunPlanService runPlanService;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private CharTableCoreService charTableCoreService;

    /**
     * 阶段速度比值(test)
     *
     * @param type 类型
     */
    @ApiOperation(value = "阶段速度比值", notes = "阶段速度比值", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("runStageRadio")
    public void getRadio(@ApiIgnore Model model,
                         @ApiParam(required = true, value = "类型") @RequestParam(value = "type") Integer type) {
        model.addAttribute("runStageRadio", runPlanService.getRatio(type));
    }


    @RequestMapping("run-plan")
    public String runPlan() {
        return "runPlan/run-plan";
    }

    /**
     * 跑步计划 首页展示
     */
    @RequestMapping("run-plan-presentation")
    public String runPlanPresentation() {
        return "runPlan/run-plan-presentation";
    }

    /**
     * 创建预览计划
     *
     * @param runDistance
     * @param planTime
     * @param runProject
     * @param hour
     * @param minute
     * @param second
     * @param gender
     * @param age
     * @return
     */
    @RequestMapping("create-plan")
    public String createRunPlan(@RequestParam("runDistance") Integer runDistance,
                                @RequestParam("planTime") String planTime,
                                @RequestParam("runProject") String runProject,
                                @RequestParam("hour") Integer hour,
                                @RequestParam("minute") Integer minute,
                                @RequestParam("second") Integer second,
                                @RequestParam("gender") Integer gender,
                                @RequestParam("age") Integer age,
                                @RequestParam("competeTime") String competeTime) throws ParseException {
        Integer projectTime = hour * 60 * 60 + minute * 60 + second;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        long planTimeLong = sdf.parse(planTime).getTime();
        long competeTimeLong = sdf.parse(competeTime).getTime();
        //// TODO: 2016/11/14  先完成app再回来改
        /*UserRunPlan userRunPlan = runPlanService.generatingPlan(runDistance, planTimeLong, runPlanService.getAbility(runProject), projectTime, gender, age, competeTimeLong);
        request.getSession().setAttribute("runPlan", userRunPlan);
        model.addAttribute("runPlan", userRunPlan);
        model.addAttribute("date", runPlanService.getTempletDate(userRunPlan));
        model.addAttribute("info", runPlanService.getInfo(userRunPlan));*/
        return "runPlan/run-plan-preview";
    }

    /**
     * app端生成计划
     *
     * @param runDistance 用户目标距离
     * @param planTime 计划开始时间
     * @param runProject 能力项
     * @param projectTime 能力项目完成时间
     * @param gender 性别
     * @param age 年龄
     * @param competitionTime 比赛时间
     */
    @RequestMapping("generating-plan")
    public void generatingRunPlan(Model model, HttpServletRequest request, @RequestParam("uid") Integer uid,
                                  @RequestParam("runDistance") Integer runDistance,
                                  @RequestParam("planTime") Long planTime,
                                  @RequestParam("runProject") Integer runProject,
                                  @RequestParam("projectTime") Integer projectTime,
                                  @RequestParam(value = "gender", required = false) Integer gender,
                                  @RequestParam(value = "age", required = false) Integer age,
                                  @RequestParam("competitionTime") Long competitionTime) {

        if (gender == null || age == null) {
            User user = userCoreDao.findUserById(uid, "gender", "age");
            gender = user.getGender();
            age = user.getAge();
        }

        Object[] objects = runPlanService.generatingPlan(runDistance, planTime, runProject, projectTime, gender, age, competitionTime);
        if ( (Integer) objects[0] == 0) {
            UserRunPlan userRunPlan = (UserRunPlan) objects[1];
            if ( (Integer) runPlanService.getUserRunPlan(uid)[0] == 0) {
                tip(model, CodeConstants.RUN_PLAN_ALREADY_EXISTS_IN_THE_EXECUTION);
            } else {
                userRunPlan.setUid(uid);
                userRunPlan.setPlan_state(UserRunPlan.PLAN_IMPLEMENTATION);
                runPlanService.insertUserRunPlan(userRunPlan);
                userRunPlan.setStages(null);

                if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
                    charTableCoreService.runPlanToEn(userRunPlan);
                }
                model.addAttribute("user_plan", userRunPlan);
            }
        } else if ( (Integer) objects[0] == 1) {
            tip(model, CodeConstants.RUN_PLAN_PLAN_TIME_OF_NO_AVAIL);
        }


    }

    /**
     * 跳转至用户当前执行中的计划
     */
    @RequestMapping("present-plan")
    public String checkedRunPlan(Model model, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("user");
        Object object1 = request.getSession().getAttribute("user_plan");
        if (object == null) {
            return "web-user/login-page";
        }
        if (object1 == null) {
            return "web-user/error";
        } else {
            UserRunPlan userRunPlan = (UserRunPlan) object1;
            if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
                charTableCoreService.runPlanToEn(userRunPlan);
            }
            model.addAttribute("runPlan", userRunPlan);
            model.addAttribute("date", runPlanService.getTempletDate(userRunPlan));
            model.addAttribute("info", runPlanService.getInfo(userRunPlan));
            return "runPlan/run-plan-training";
        }
    }

    /**
     * 用户保存计划
     */
    @RequestMapping("user-run-plan-save")
    public void userRunPlanSave(Model model, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("user");
        Object object1 = request.getSession().getAttribute("runPlan");

        if (object == null) {
            //未登录
            tip(model, CodeConstants.LOGIN_USER_STATE_NO_LOGIN);

        } else {
            if (object1 == null) {
                //计划已失效
                tip(model, CodeConstants.RUN_PLAN_STATE_EXPIRATION);
            } else {
                UserRunPlan userRunPlan = (UserRunPlan) object1;
                User user = (User) object;
                if (runPlanService.checkUserPlanState(user.getId())) {
                    //已存在执行中的计划
                    tip(model, CodeConstants.RUN_PLAN_ALREADY_EXISTS_IN_THE_EXECUTION);
                } else {
                    //未存在执行中的计划
                    userRunPlan.setUid(user.getId());
                    userRunPlan.setPlan_state(UserRunPlan.PLAN_IMPLEMENTATION);
                    userRunPlan.setComplete_degree(0);
                    runPlanService.insertUserRunPlan(userRunPlan);

                    if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
                        charTableCoreService.runPlanToEn(userRunPlan);
                    }
                    request.getSession().setAttribute("user_plan", userRunPlan);
                }
            }
        }
    }

    @RequestMapping("turnToSave")
    public String turnToSave(Model model, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("user");
        Object object1 = request.getSession().getAttribute("runPlan");
        if (object != null) {
            if (object1 != null) {
                UserRunPlan userRunPlan = (UserRunPlan) object1;

                if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
                    charTableCoreService.runPlanToEn(userRunPlan);
                }

                model.addAttribute("runPlan", userRunPlan);
                model.addAttribute("date", runPlanService.getTempletDate(userRunPlan));
                model.addAttribute("info", runPlanService.getInfo(userRunPlan));
                return "runPlan/run-plan-preview";
            } else {
                return "runPlan/error";
            }
        } else {
            return "runPlan/error";
        }
    }

    /**
     * 计划延期
     *
     * @param delayDay 延期天数
     */
    @RequestMapping("delay-plan")
    public void delayPlan(HttpServletRequest request, @RequestParam("delayDay") Integer delayDay) {
        User user = (User) request.getSession().getAttribute("user");
        UserRunPlan userRunPlan = runPlanService.delayUserRunPlan(user.getId(), delayDay);
        if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
            charTableCoreService.runPlanToEn(userRunPlan);
        }
        request.getSession().setAttribute("user_plan", userRunPlan);
        request.getSession().setAttribute("info", runPlanService.getInfo(userRunPlan));
    }

    /**
     * 计划取消
     */
    @RequestMapping("cancel-plan")
    public void cancelPlan(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        runPlanService.deleteUserRunPlan(user.getId());
        request.getSession().removeAttribute("user_plan");
    }

    /**
     * 获取执行中的计划(app),若获取不到但能获取到7天内完成计划则返回该计划
     *
     * @param uid 用户编号
     */
    @RequestMapping("get-user-plan")
    public void getUserPlan(Model model, HttpServletRequest request, @RequestParam("uid") Integer uid) {
        Object[] objects = runPlanService.getUserRunPlan(uid);
        if ( (Integer) objects[0] == 0) {
            UserRunPlan userRunPlan = (UserRunPlan) objects[1];
            if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
                charTableCoreService.runPlanToEn(userRunPlan);
            }
            model.addAttribute("user_plan", userRunPlan);
        } else if ( (Integer) objects[0] == 1) {
            UserRunPlan userRunPlan = (UserRunPlan) objects[1];
            userRunPlan.setStages(null);
            model.addAttribute("user_plan", userRunPlan);
        } else {
            tip(model, CodeConstants.RUN_PLAN_USER_DO_NOT_OWN_RUN_PLAN);
        }
    }

    /**
     * 计划延期(app)
     *
     * @param delayDay 延期天数
     * @param uid 用户编号
     */
    @RequestMapping("delay-user-plan")
    public void delayUserPlan(Model model, @RequestParam("delayDay") Integer delayDay, @RequestParam("uid") Integer uid) {
        int code = runPlanService.delayPlanPermit(uid,delayDay);
        if(code == 1){
            Object[] objects = runPlanService.delayUserPlan(uid, delayDay);
            if ( (Integer) objects[0] == 0) {
                UserRunPlan userRunPlan = (UserRunPlan) objects[1];
                userRunPlan.setStages(null);
                model.addAttribute("user_plan", userRunPlan);
            } else {
                tip(model, CodeConstants.RUN_PLAN_USER_DO_NOT_OWN_RUN_PLAN);
            }
        } else if(code == 2){
            tip(model, CodeConstants.RUN_PLAN_DELAY_TOO_MORE);
        } else if(code == 3) {
            tip(model, CodeConstants.RUN_PLAN_END_PLAN);
        }
    }

    /**
     *  获取已结束的计划列表
     * @param uid 用户编号
     */
    @RequestMapping("get-past-plan")
    public void getPastPlanList(Model model, HttpServletRequest request, @RequestParam("uid") Integer uid) {
        Object[] objects = runPlanService.getPastPlanByUid(uid);
        if ( (Integer) objects[0] == 0) {
            List<UserRunPlan> userRunPlans = (List<UserRunPlan>) objects[1];
            if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
                charTableCoreService.runPlansToEn(userRunPlans);
            }
            model.addAttribute("pastPlanList", userRunPlans);
        } else {
            tip(model, CodeConstants.RUN_PLAN_USER_DO_NOT_OWN_PAST_RUN_PLAN);
        }
    }

    /**
     *  放弃计划(app)
     * @param uid 用户编号
     */
    @RequestMapping("give-up-plan")
    public void giveUpPlan(Model model, @RequestParam("uid") Integer uid) {
        Object[] objects = runPlanService.giveUpPlan(uid);
        if ( (Integer) objects[0] == 1) {
            tip(model, CodeConstants.RUN_PLAN_USER_DO_NOT_OWN_RUN_PLAN);
        }
    }

    /**
     * 根据type统计RunPlanTemplate
     * @param type 计划类型
     */
    @RequestMapping("get-type-diffDay")
    public void getTypeDiffDay(Model model, @RequestParam("type") Integer type) {
        model.addAttribute("typeList", runPlanService.getDiffDayByType(type));
    }

    /**
     *  获取今天的训练内容
     * @param uid 用户编号
     */
    @RequestMapping("get-today-stages")
    public void getStagesInToday(Model model, HttpServletRequest request, @RequestParam("uid") Integer uid) {
        Object[] objects = runPlanService.getStageInToday(uid);
        if ( (Integer) objects[0] == 1) {
            tip(model, CodeConstants.RUN_PLAN_USER_DO_NOT_OWN_RUN_PLAN);
        } else if( (Integer) objects[0] ==2 ) {
            tip(model, CodeConstants.RUN_PLAN_NO_PLAN_TO_START);
        }else{
            Stages stages = (Stages) objects[1];
            if (Constants.LANGUAGE_EN.equals(getLanguage(request))) {
                charTableCoreService.stagesToEn(stages);
            }
            model.addAttribute("stages", objects[1]);
        }
    }

}

