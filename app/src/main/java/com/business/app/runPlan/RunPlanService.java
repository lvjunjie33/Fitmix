
package com.business.app.runPlan;

import com.business.app.userRun.UserRunService;
import com.business.core.entity.runPlan.*;
import com.business.core.entity.user.UserRun;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by weird on 2016/5/11.
 */

@Service
public class RunPlanService {

    @Autowired
    private RunPlanDao runPlanDao;
    @Autowired
    private UserRunService userRunService;

    /**
     * 获取跑步阶段的速度比值(测试用，记得删)
     *
     * @param type
     * @return
     */

    public RunPlanStageRatio getRatio(Integer type) {
        return runPlanDao.getStageRatio(type);
    }

    /**
     * 获取模板主体内容并根据阶段分段
     *
     * @param runDistance
     * @param classify
     * @param beginTime
     * @param resultTime
     * @return
     */
    public List<Stages> getStagesList(Integer runDistance, Integer classify, Integer beginTime, Integer resultTime, Long planTime) {
        //获取模板内容
        RunPlanTemplate runPlanTemplate = runPlanDao.findRunPlanTemplet(runDistance, classify, beginTime);
        List<Stages> stages = runPlanTemplate.getStages();

        //获取各阶段速度与比赛速度的比值列表
        RunPlanStageRatio runPlanStageRatio = runPlanDao.getRunPlanStageRatio(runDistance);
        List<RunStage> runStages = runPlanStageRatio.getRunStages();

        //每公里的比赛速度所需时间
        Double perResultTime = resultTime / runPlanStageRatio.getDistance();

        //转换计划开始时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTime dateTime = DateTime.parse(sdf.format(planTime));
        SimpleDateFormat sd = new SimpleDateFormat("hh:mm:ss");


        for (int i = 0; i < stages.size(); i++) {
            String stageContent = stages.get(i).getStageContent();
            for (int j = 0; j < runStages.size(); j++) {
                if (stageContent.contains(runStages.get(j).getName())) {
                    String tempStr = "";
                    String tempStrSum = "";
                    for (int k = 0; k < runStages.get(j).getRatio().length; k++) {
                        int resultTemp = (int) (runStages.get(j).getRatio()[k] * perResultTime);
                        int resultTempSum = (int) (runStages.get(j).getRatio()[k] * perResultTime * stages.get(i).getDistance());
                        if (k == 1) {
                            tempStr += "-" + resultTemp / 60 % 60 + "′" + resultTemp % 60 % 60 + "″";
                            tempStrSum += "-" + resultTempSum / 60 / 60 + ":" + resultTempSum / 60 % 60 + ":" + resultTempSum % 60 % 60;
                        } else {
                            tempStr += resultTemp / 60 % 60 + "′" + resultTemp % 60 % 60 + "″";
                            tempStrSum += resultTempSum / 60 / 60 + ":" + resultTempSum / 60 % 60 + ":" + resultTempSum % 60 % 60;
                        }
                    }
                    stages.get(i).setStageSpeed(tempStr);
                    stages.get(i).setStageTime(tempStrSum);
                }
                if (stageContent.contains("比赛")) {
                    int gameResult = (int) (perResultTime / 1);
                    stages.get(i).setStageSpeed(gameResult / 60 % 60 + "′" + gameResult % 60 % 60 + "″" );
                }
            }
            //将计划的每个日期赋予当前计划
            stages.get(i).setDateTime(sdf.format(dateTime.plusDays(i - beginTime).toDate()));
        }
        return stages;
    }

    /**
     * 获取类型名
     * TODO 暂时写死
     *
     * @param type
     * @return
     */
    public String getTypeNameByType(Integer type) {
        String typeName = null;
        switch (type) {
            case 0:
                typeName = "5km";
                break;
            case 1:
                typeName = "10km";
                break;
            case 2:
                typeName = "Half marathon";
                break;
            case 3:
                typeName = "Marathon";
                break;
        }
        return typeName;
    }

    /**
     * 生成计划的主体部分
     *
     * @param runDistance
     * @param runProject
     * @param hour
     * @param minute
     * @param second
     * @param gender
     * @param age
     * @return
     */
    public UserRunPlan createRunPlan(Integer runDistance, String planTime, String runProject, Integer hour, Integer minute, Integer second, Integer gender, Integer age,
                                     String competeTime, Integer targetHour, Integer targetMinute, Integer targetSecond) {
        int ability;
        String ability_time = "";
        int speed = hour * 60 + minute;
        Integer classify = -1;
        SimpleDateFormat sd = new SimpleDateFormat("yy/MM/dd");
        Integer beginTime = -1;
        Date date = null;
        try {
            date = sd.parse(planTime);
            beginTime = DateUtil.dayForWeek(date) - 1;
            planTime = sd.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserRunPlan userRunPlan = new UserRunPlan();

        //获取跑步类型
        userRunPlan.setType(runDistance);

        //跑步类型名称
        userRunPlan.setTypeName(getTypeNameByType(runDistance));

        //获取开始时间的是周几
        userRunPlan.setBeginTime(beginTime);

        //获取计划开始时间
        userRunPlan.setPlanTime(date.getTime());

        //获取能力值
        switch (runProject) {
            case "beginner_1":
                ability = 0;
                break;
            case "beginner_2":
                ability = 1;
                break;
            case "beginner_3":
                ability = 2;
                break;
            case "perf_5000m":
                ability = 3;
                break;
            case "perf_10k":
                ability = 4;
                break;
            case "perf_half":
                ability = 5;
                break;
            case "perf_full":
                ability = 6;
                break;
            default:
                ability = -1;
        }
        userRunPlan.setAbility(ability);

        //获取计划结果速度
        int resultTime = 0;
        int ages;
        if (age <= 30) {
            ages = 0;
        } else if (age <= 40) {
            ages = 1;
        } else {
            ages = 2;
        }

        ability_time += (hour == 0) ? "00:" : hour + ":";
        ability_time += (minute == 0) ? "00:" : minute + ":";
        ability_time += (second == 0) ? "00" : second;
        userRunPlan.setAbilityTime(hour * 60 + minute + Double.parseDouble(second + "") / 60);

        if (ability < 3 && ability >= 0) {
            classify = runPlanDao.getRunPlanClassify(runDistance, ability, -1).getClassify();
        } else if (ability >= 3) {
            classify = runPlanDao.getRunPlanClassify(runDistance, ability, speed).getClassify();
        }

        if (ability <= 2) {
            String result = runPlanDao.getSpeed(runDistance, ability, ages, gender).getSpeed();
            String[] temp = result.split(":");
            resultTime = Integer.parseInt(temp[0]) * 3600 + Integer.parseInt(temp[1]) * 60 + Integer.parseInt(temp[2]);
            userRunPlan.setResultTime(resultTime);
        } else {
            resultTime = userRunPlan.getResultTime();
            userRunPlan.setResultTime(userRunPlan.getResultTime());
        }
        Map<Integer, List<Stages>> stagesMap = CollectionUtil.buildMultimap(getStagesList(runDistance, classify, beginTime, resultTime, userRunPlan.getPlanTime()), Integer.class, Stages.class, "stageType");
        userRunPlan.setStages(stagesMap);

        return userRunPlan;
    }


    /**
     * 获取计划每天的日期
     *
     * @param userRunPlan
     * @return
     */
    public Map<Integer, List<String>> getTempletDate(UserRunPlan userRunPlan) {
        Map<Integer, List<String>> datesMap = new HashMap<Integer, List<String>>();
        Map<Integer, List<Stages>> stages = userRunPlan.getStages();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        DateTime dateTime = DateTime.parse(sd.format(userRunPlan.getPlanTime()));
        for (Map.Entry<Integer, List<Stages>> entry : stages.entrySet()) {
            List<String> list = new ArrayList<String>();
            List<Stages> stages1 = stages.get(entry.getKey());
            for (int j = 0; j < stages1.size(); j++) {
                Stages stage = stages1.get(j);
                String dateStr = sdf.format(dateTime.plusDays(stage.getDate() - userRunPlan.getBeginTime()).toDate());
                String[] temp = dateStr.split("-");
                String str = (Integer.parseInt(temp[1]) == 1) ? temp[0] + "月" + temp[1] + "日" : temp[1] + "日";
                list.add(str);
            }
            datesMap.put(entry.getKey(), list);
        }
        return datesMap;
    }

    /**
     * 返回计划的5个基本信息：
     * 类型名
     * 预计完成时长
     * 计划开始日期
     * 比赛日期
     * 跑步距离
     * TODO 要改(整合进UserRunPlan)
     *
     * @param userRunPlan
     * @return
     */
    public String[] getInfo(UserRunPlan userRunPlan) {
        String[] str = new String[5];
        String typeName = "";
        switch (userRunPlan.getType()) {
            case 0:
                typeName = "5公里";
                break;
            case 1:
                typeName = "10公里";
                break;
            case 2:
                typeName = "半程马拉松";
                break;
            case 3:
                typeName = "马拉松";
                break;
        }
        str[0] = typeName;

        int resultTime = userRunPlan.getResultTime();
        String resultTimeStr = "";
        if (resultTime / 60 / 60 == 0) {
            resultTimeStr += "00:";
        } else if (resultTime / 60 / 60 < 10) {
            resultTimeStr += "0" + resultTime / 60 / 60 + ":";
        } else {
            resultTimeStr += resultTime / 60 / 60 + ":";
        }

        if (resultTime / 60 % 60 == 0) {
            resultTimeStr += "00:";
        } else if (resultTime / 60 % 60 < 10) {
            resultTimeStr += "0" + resultTime / 60 % 60 + ":";
        } else {
            resultTimeStr += resultTime / 60 % 60 + ":";
        }

        if (resultTime % 60 % 60 == 0) {
            resultTimeStr += "00";
        } else if (resultTime % 60 % 60 < 10) {
            resultTimeStr += "0" + resultTime % 60 % 60 + "";
        } else {
            resultTimeStr += resultTime % 60 % 60 + "";
        }
        str[1] = resultTimeStr;

        Long planTime = userRunPlan.getPlanTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        str[2] = sdf.format(planTime);
        DateTime dateTime = DateTime.parse(sdf1.format(planTime));

        Map<Integer, List<Stages>> stages = userRunPlan.getStages();
        double distance = 0;
        for (Map.Entry<Integer, List<Stages>> entry : stages.entrySet()) {
            List<Stages> stage = stages.get(entry.getKey());
            for (int i = 0; i < stage.size(); i++) {
                if (stage.get(i).getStageContent().contains("比赛日")) {
                    dateTime = dateTime.plusDays(stage.get(i).getDate() - userRunPlan.getBeginTime());
                }
                distance += stage.get(i).getDistance();
            }
        }
        str[3] = sdf.format(dateTime.toDate());
        str[4] = distance + "公里";
        return str;
    }

    /**
     * 检测用户是否存在正在执行的计划
     *
     * @param uid
     * @return
     */
    public boolean checkUserPlanState(Integer uid) {
        List<UserRunPlan> userRunPlanList = runPlanDao.findUserRunPlanListByUid(uid);
        boolean state = false;
        for (UserRunPlan userRunPlan : userRunPlanList) {
            if (userRunPlan.getPlan_state() == UserRunPlan.PLAN_IMPLEMENTATION) {
                state = true;
            }
        }
        return state;
    }

    /**
     * 写入UserRunPlan
     *
     * @param userRunPlan
     */
    public void insertUserRunPlan(UserRunPlan userRunPlan) {
        runPlanDao.insertUserRunPlan(userRunPlan);
    }

    public UserRunPlan delayUserRunPlan(Integer uid, Integer delay_day) {
        UserRunPlan userRunPlan = runPlanDao.findUserRunPlanByUid(uid);
        Map<Integer, List<Stages>> stages = userRunPlan.getStages();
        long now_day = DateUtil.getDayBegin(new Date()).getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long planTime = userRunPlan.getPlanTime();
        DateTime planTime_dt = DateTime.parse(sdf.format(planTime));
        //判断当前日期与计划开始日期是否同一天
        if (now_day - planTime < 0) {
            //计划延期开始至delay_day后开始
            DateTime dateTime = DateTime.parse(sdf.format(planTime));
            dateTime = dateTime.plusDays(delay_day);
            planTime = dateTime.toDate().getTime();
            userRunPlan.setBeginTime((userRunPlan.getBeginTime() + delay_day) % 7);
            userRunPlan.setPlanTime(planTime);
            int classify = -1;
            Double abilityTime = userRunPlan.getAbilityTime();
            if (userRunPlan.getAbility() < 3 && userRunPlan.getAbility() >= 0) {
                classify = runPlanDao.getRunPlanClassify(userRunPlan.getType(), userRunPlan.getAbility()).getClassify();
            } else if (userRunPlan.getAbility() >= 3) {
                classify = runPlanDao.getRunPlanClassify_time(userRunPlan.getType(), userRunPlan.getAbility(), abilityTime.intValue()).getClassify();
            }
            Map<Integer, List<Stages>> stagesMap = CollectionUtil.buildMultimap(getStagesList(userRunPlan.getType(), classify, userRunPlan.getBeginTime(), userRunPlan.getResultTime(), userRunPlan.getPlanTime()), Integer.class, Stages.class, "stageType");
            userRunPlan.setStages(stagesMap);

        } else {
            /*boolean state = false;
            for (Map.Entry<Integer, List<Stages>> entry : stages.entrySet()) {
                List<Stages> stage = entry.getValue();
                int point = 0;
                int stageth = -1;
                List<Stages> tempStage = new ArrayList<Stages>();
                for (int i = 0; i < stage.size(); i++) {
                    if (stage.get(i).getState() == -1 && !state) {
                        tempStage = new ArrayList<Stages>();
                        for (int j = 0; j < delay_day; j++) {
                            point = i;
                            Stages astage = new Stages();
                            astage.setStageName(stage.get(i).getStageName());
                            astage.setStageType(stage.get(i).getStageType());
                            astage.setDistance(0.0);
                            astage.setState(-1);
                            astage.setDate(stage.get(i).getDate() + i);
                            astage.setStageContent("休息");
                            astage.setDateTime(sdf.format(planTime_dt.plusDays(astage.getDate() - userRunPlan.getBeginTime()).toDate()));
                            tempStage.add(astage);
                        }
                        state = true;
                        stageth = entry.getKey();
                    }
                    if (state) {
                        stage.get(i).setDate(stage.get(i).getDate() + delay_day);
                        stage.get(i).setDateTime(sdf.format(planTime_dt.plusDays(stage.get(i).getDate() - userRunPlan.getBeginTime()).toDate()));
                    }
                }
                if (stageth == entry.getKey()) {
                    stage.addAll(point, tempStage);
                }
            }*/
            Integer diffDay = DateUtil.diffDay(new Date(), new Date(planTime));
            List<Stages> astages = new ArrayList<>();
            int point = 0;
            boolean state = false;
            for (Map.Entry<Integer, List<Stages>> entry : stages.entrySet()) {
                List<Stages> stagesList = entry.getValue();
                List<Stages> tempStList = new ArrayList<>();
                for (int i = 0; i < stagesList.size(); i++) {
                    if (!state) {
                        tempStList.add(stagesList.get(i));
                    }
                    if (stagesList.get(i).getDate() == diffDay) {
                        for (int j = 0; j < delay_day; j++) {
                            Stages astage = new Stages();
                            astage.setStageName(stagesList.get(i).getStageName());
                            astage.setStageType(stagesList.get(i).getStageType());
                            astage.setDistance(0.0);
                            astage.setState(-1);
                            astage.setDate(diffDay);
                            astage.setStageContent("休息");
                            astage.setDateTime(stagesList.get(i).getDateTime());
                            tempStList.add(astage);
                        }
                        point = i;
                        state = true;
                        stagesList.get(i).setDate(diffDay + delay_day);
                        stagesList.get(i).setDateTime(sdf.format(planTime_dt.plusDays(diffDay + delay_day).toDate()));
                    } else if (stagesList.get(i).getDate() > diffDay) {
                        stagesList.get(i).setDate(stagesList.get(i).getDate() + delay_day);
                        stagesList.get(i).setDateTime(sdf.format(planTime_dt.plusDays(diffDay + delay_day).toDate()));
                        tempStList.add(stagesList.get(i));
                    }
                }

                stages.put(entry.getKey(), tempStList);
            }
            userRunPlan.setStages(stages);
        }
        runPlanDao.delayUserRunPlan(userRunPlan);
        return runPlanDao.findUserRunPlanByUid(uid);
    }

    public void modifyDateOfUserRunPlan(UserRunPlan userRunPlan) {
        Date date = DateUtil.getDayBegin(new Date());
        Map<Integer, List<Stages>> stages = userRunPlan.getStages();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTime dateTime = DateTime.parse(sdf.format(userRunPlan.getPlanTime()));
        for (Map.Entry<Integer, List<Stages>> entry : stages.entrySet()) {
            List<Stages> stage = entry.getValue();
            for (int i = 0; i < stage.size(); i++) {
                dateTime = dateTime.plusDays(stage.get(i).getDate());
                if (DateUtil.diffDay(date, dateTime.toDate()) > 0) {
                    stage.get(i).setState(3);
                } else if (DateUtil.diffDay(date, dateTime.toDate()) == 0) {
                    stage.get(i).setState(4);
                }
            }
        }
    }

    /**
     * 删除该用户的跑步计划
     *
     * @param uid
     */
    public void deleteUserRunPlan(Integer uid) {
        runPlanDao.deleteUserRunPlan(uid);
    }

    /**
     * 获取浏览页面的描述文本内容
     *
     * @return
     */
    public Map<String, String> getRunPlanDescriptionByPreview() {
        List<RunPlanDescription> runPlanDescriptions = runPlanDao.getRunPlanDescription(RunPlanDescription.POSITION_PREVIEW);
        Map<String, String> runPlanDescriptionMap = new HashMap<String, String>();
        for (int i = 0; i < runPlanDescriptions.size(); i++) {
            runPlanDescriptionMap.put(runPlanDescriptions.get(i).getKey(), runPlanDescriptions.get(i).getDescription());
        }
        return runPlanDescriptionMap;
    }

    /**
     * 获取执行中计划
     *
     * @param uid
     * @return
     */
    public Object[] getPresentPlan(Integer uid) {
        UserRunPlan userRunPlan = runPlanDao.getPresentPlan(uid);
        if (userRunPlan == null) {
            return new Object[]{0};
        } else {
            return new Object[]{1, userRunPlan};
        }
    }

    public Object[] getEndPlans(Integer uid) {
        List<UserRunPlan> userRunPlan = runPlanDao.getEndPlan(uid);
        if (userRunPlan.size() == 0) {
            return new Object[]{0};
        } else {
            return new Object[]{1, userRunPlan};
        }
    }

    /**
     * 生成计划
     *
     * @param runDistance
     * @param planTime
     * @param runProject
     * @param projectTime
     * @param gender
     * @param age
     * @param competitionTime
     * @return
     */
    public Object[] generatingPlan(Integer runDistance, Long planTime, Integer runProject, Integer projectTime, Integer gender, Integer age, Long competitionTime) {

        SimpleDateFormat sd = new SimpleDateFormat("yy/MM/dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (planTime < DateUtil.getDayBegin(new Date()).getTime()) {
            return new Object[]{1};
        } else {
            UserRunPlan userRunPlan = new UserRunPlan();

            //若输入的比赛时间为0则为训练的计划、反之为比赛的计划
            if (competitionTime == 0) {
                userRunPlan.setPlanType(UserRunPlan.PLAN_TYPE_TRAINING);
            } else {
                userRunPlan.setPlanType(UserRunPlan.PLAN_TYPE_COMPETITION);
            }

            //type
            userRunPlan.setType(runDistance);

            //typeName
            switch (runDistance) {
                case 0:
                    userRunPlan.setTypeName("5公里");
                    break;
                case 1:
                    userRunPlan.setTypeName("10公里");
                    break;
                case 2:
                    userRunPlan.setTypeName("半程马拉松");
                    break;
                case 3:
                    userRunPlan.setTypeName("马拉松");
                    break;
            }

            //planTime&beginTime
            Date competeDate = null;
            Date plan_date = null;
            try {
                plan_date = new Date(planTime);
                Integer beginTime = DateUtil.dayForWeek(plan_date) - 1;
                if (competitionTime != 0) {
                    competeDate = new Date(competitionTime);
                }
                userRunPlan.setBeginTime(beginTime);
                userRunPlan.setPlanTime(planTime);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //获取能力值
            int ability = runProject;
            userRunPlan.setAbility(ability);

            //获取年龄分段
            int ages;
            if (age <= 30) {
                ages = 0;
            } else {
                if (age <= 40) {
                    ages = 1;
                } else {
                    ages = 2;
                }
            }

            //获取abilityTime&classify&resultTime
            String ability_time = "";
            ability_time = projectTime / 60 / 60 + ":" + projectTime / 60 % 60 + ":" + projectTime % 60 % 60;
            userRunPlan.setAbilityTime(Double.parseDouble(projectTime + "") / 60);

            //获取resultTime
            if (ability <= 2) {
                String result = runPlanDao.getSpeed(runDistance, ability, ages, gender).getSpeed();
                String[] temp = result.split(":");
                userRunPlan.setResultTimeStr(result);
                userRunPlan.setResultTime(Integer.parseInt(temp[0]) * 3600 + Integer.parseInt(temp[1]) * 60 + Integer.parseInt(temp[2]));
            } else {
                Integer resultTime = userRunPlan.getResultTime();
                userRunPlan.setResultTime(userRunPlan.getResultTime());
                userRunPlan.setResultTimeStr(resultTime / 60 / 60 + ":" + resultTime / 60 % 60 + ":" + resultTime % 60 % 60);
            }

            Integer classify = null;
            int speed = projectTime / 60;
            int resultTime = 0;
            if (ability < 3 && ability >= 0) {
                classify = runPlanDao.getRunPlanClassify(runDistance, ability, -1).getClassify();
                String result = runPlanDao.getSpeed(runDistance, ability, ages, gender).getSpeed();
                String[] temp = result.split(":");
                resultTime = Integer.parseInt(temp[0]) * 3600 + Integer.parseInt(temp[1]) * 60 + Integer.parseInt(temp[2]);
            } else if (ability >= 3) {
                classify = runPlanDao.getRunPlanClassify(runDistance, ability, speed).getClassify();
                resultTime = userRunPlan.getResultTime();
            }
            userRunPlan.setResultTime(userRunPlan.getResultTime());

            //获取总里程以及比赛日
            double distance = 0;
            DateTime dateTime = DateTime.parse(sdf.format(plan_date));
            DateTime endTime;
            int diffDay;
            int diffDayBegin;

            //获取计划
            List<Stages> stages1 = getStagesList(runDistance, classify, userRunPlan.getBeginTime(), resultTime, userRunPlan.getPlanTime());
            //获取计划设定的比赛时间
            for (int stSize = 0; stSize < stages1.size(); stSize++) {
                if (stages1.get(stSize).getStageContent().contains("比赛日")) {
                    dateTime = dateTime.plusDays(stSize - userRunPlan.getBeginTime());
                }
            }


            //判断输入的比赛时间修改计划
            if (competeDate != null) {
                diffDayBegin = DateUtil.diffDay(dateTime.toDate(), plan_date);
                diffDay = DateUtil.diffDay(competeDate, plan_date);
                ///
                Object[] objects = buildPlanByCompeteTime(runDistance, classify, diffDayBegin, diffDay, stages1, plan_date, userRunPlan.getBeginTime(), resultTime);
                plan_date = (Date) objects[0];
                userRunPlan.setPlanTime(plan_date.getTime());
                userRunPlan.setBeginTime((Integer) objects[1]);
                Map<Integer, List<Stages>> stMap = aListToStagesMap((List<Stages>) objects[2]);
                stMap = adjustPlan(stMap, userRunPlan.getPlanTime(), userRunPlan.getBeginTime());
                userRunPlan.setStages(stMap);
                userRunPlan.setStagesList(stageMapToList(stMap));
            } else {
                Map<Integer, List<Stages>> stagesMap = aListToStagesMap(stages1);
                stagesMap = adjustPlan(stagesMap, userRunPlan.getPlanTime(), userRunPlan.getBeginTime());
                userRunPlan.setStages(stagesMap);
                userRunPlan.setStagesList(stageMapToList(stagesMap));
            }

            userRunPlan.setStagesLists(stagesListToAList(userRunPlan.getStagesList()));

            Integer runDay = 0;

            //遍历计算日期、跑步天数及跑步距离
            dateTime = DateTime.parse(sdf.format(plan_date)).plusDays(-userRunPlan.getBeginTime());
            endTime = DateTime.parse(sdf.format(plan_date)).plusDays(-userRunPlan.getBeginTime() - 1);
            Integer diffEndTime = 0;
            for (int list = 0; list < userRunPlan.getStagesLists().size(); list++) {
                Stages stage = userRunPlan.getStagesLists().get(list);
                if (stage.getStageContent().contains("比赛日")) {
                    dateTime = dateTime.plusDays(stage.getDate());
                }
                //初始化跑步天数
                if (stage.getDistance() > 0) {
                    runDay++;
                    diffEndTime = list;
                }
                distance += stage.getDistance();
            }

            endTime = endTime.plusDays(diffEndTime + 1);

            userRunPlan.setDistance(distance);
            userRunPlan.setCompletedDistance(0.0);
            userRunPlan.setRunDay(runDay);
            userRunPlan.setCompletedRunDay(0);
            userRunPlan.setCompetitionTime(dateTime.toDate().getTime());
            userRunPlan.setEndTime(endTime.toDate().getTime());
            //初始化状态
            userRunPlan = adjustPlanState(userRunPlan);


            return new Object[]{0, userRunPlan};
        }

    }

    /**
     * 获取相应的extralStage 重新构建计划
     * todo 增加类型的后台管理
     *
     * @param type
     * @param classify
     * @param diffDay
     * @param stages
     * @return
     */
    public Object[] buildPlanByCompeteTime(Integer type, Integer classify, Integer diffDayBegin, Integer diffDay, List<Stages> stages, Date plan_date, Integer beginTime, Integer resultTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (diffDay > diffDayBegin) {
            Date newPlanDate = DateTime.parse(sdf.format(plan_date)).plusDays(diffDay - diffDayBegin).toDate();
            Integer newBeginTime = DateUtil.dayForWeek(newPlanDate) - 1;
            List<Stages> stages1 = getStagesList(type, classify, newBeginTime, resultTime, newPlanDate.getTime());
            Integer competitionToPlan = 0;
            for (int i = newBeginTime; i < stages1.size(); i++) {
                if (stages1.get(i).getStageContent().contains("比赛日")) {
                    competitionToPlan = i - newBeginTime;
                }
            }
            if (competitionToPlan > diffDayBegin) {
                newPlanDate = DateTime.parse(sdf.format(newPlanDate)).plusDays(-7).toDate();
            } else if (competitionToPlan < diffDayBegin) {
                newPlanDate = DateTime.parse(sdf.format(newPlanDate)).plusDays(7).toDate();
            }
            /*//TODO 暂时写死
            switch (type) {
                case 0:
                    if (competitionToPlan < 28) {
                        newPlanDate = DateTime.parse(sdf.format(newPlanDate)).plusDays(7).toDate();
                    }
                    break;
                case 1:
                    if (competitionToPlan < 35) {
                        newPlanDate = DateTime.parse(sdf.format(newPlanDate)).plusDays(7).toDate();
                    }
                    break;
                case 2:
                    if (competitionToPlan < 119) {
                        newPlanDate = DateTime.parse(sdf.format(newPlanDate)).plusDays(7).toDate();
                    }
                    break;
                case 3:
                    if (competitionToPlan < 147) {
                        newPlanDate = DateTime.parse(sdf.format(newPlanDate)).plusDays(7).toDate();
                    }
                    break;
            }*/
            /*stages1 = getStagesList(type, classify, newBeginTime, resultTime, newPlanDate.getTime());*/
            return new Object[]{newPlanDate, newBeginTime, stages1};
        } else {
            return new Object[]{plan_date, beginTime, stages};
        }
    }

    /**
     * 计划内容调整
     *
     * @param stages
     * @param date   plan_date
     * @return
     */
    public Map<Integer, List<Stages>> adjustPlan(Map<Integer, List<Stages>> stages, Long date, Integer beginTime) {
        List<Stages> lastStages = new ArrayList<>();
        for (Map.Entry<Integer, List<Stages>> entry : stages.entrySet()) {
            List<Stages> stage = entry.getValue();
            if (lastStages.size() != 0) {
                for (int t = 0; t < lastStages.size(); t++) {
                    Stages lastStage = lastStages.get(t);
                    lastStage.setStageType(stage.get(0).getStageType());
                    lastStage.setStageName(stage.get(0).getStageName());
                    stage.add(t, lastStages.get(t));
                }
                lastStages.clear();
            }
            if (stage.size() % 7 != 0) {
                if (entry.getKey() == Stages.STAGE_PREPARATIONANDCOMPETITION) {
                    int oldStage = stage.size();
                    for (int i = oldStage / 7 * 7; i < oldStage; i++) {
                        if (stage.get(i).getStageContent().contains("比赛日")) {
                            lastStages = new ArrayList<>();
                            for (int j = 0; j < 6 - (oldStage % 7 - 1); j++) {
                                Stages tempStage = new Stages();
                                tempStage.setDate(1);
                                tempStage.setStageType(stage.get(i).getStageType());
                                tempStage.setStageName(stage.get(i).getStageName());
                                tempStage.setContentDescription("与跑步一样，休息也是训练中的重要部分。它可让您的身体恢复，使您在下一次跑步中变得更强。休息能够提高当前训练的效果。");
                                tempStage.setDistance(0.0);
                                tempStage.setStageContent("休息");
                                tempStage.setState(-1);
                                stage.add(tempStage);
                                tempStage = new Stages();
                                tempStage.setStageType(stage.get(i).getStageType());
                                tempStage.setStageName(stage.get(i).getStageName());
                                tempStage.setContentDescription("与跑步一样，休息也是训练中的重要部分。它可让您的身体恢复，使您在下一次跑步中变得更强。休息能够提高当前训练的效果。");
                                tempStage.setDistance(0.0);
                                tempStage.setStageContent("休息");
                                tempStage.setState(-1);
                                lastStages.add(tempStage);
                            }
                            break;
                        } else {
                            lastStages.add(stage.get(i));
                        }
                    }
                    stage = stage.subList(0, stage.size() - stage.size() % 7);
                } else {
                    if (entry.getKey() == Stages.STAGE_RECOVER) {
                        int tempSize = stage.size();
                        int extral = 7 - tempSize % 7;
                        for (int k = 0; k < extral; k++) {
                            Stages tempStage = new Stages();
                            tempStage.setStageName(stage.get(0).getStageName());
                            tempStage.setStageType(stage.get(0).getStageType());
                            tempStage.setStageContent("休息");
                            tempStage.setContentDescription("与跑步一样，休息也是训练中的重要部分。它可让您的身体恢复，使您在下一次跑步中变得更强。休息能够提高当前训练的效果。");
                            tempStage.setDate(1);
                            tempStage.setDistance(0.0);
                            tempStage.setState(-1);
                            stage.add(tempSize + k, tempStage);
                        }
                    } else {
                        for (int l = stage.size() / 7 * 7; l < stage.size(); l++) {
                            lastStages.add(stage.get(l));
                        }
                        stage = stage.subList(0, stage.size() - stage.size() % 7);
                    }
                }
            }
            stages.put(entry.getKey(), stage);
        }

        int count = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTime dateTime = DateTime.parse(sdf.format(date)).plusDays(-beginTime);
        //对调整后的内容进行梳理排序
        for (Map.Entry<Integer, List<Stages>> entry : stages.entrySet()) {
            List<Stages> stagesList = entry.getValue();
            for (int i = 0; i < stagesList.size(); i++) {
                stagesList.get(i).setDate(count);
                stagesList.get(i).setDateTime(sdf.format(dateTime.plusDays(count).toDate()));
                stagesList.get(i).setDateLong(dateTime.plusDays(count).toDate().getTime());
                count++;
            }
            stages.put(entry.getKey(), stagesList);
        }
        return stages;
    }

    /**
     * Map<Integer,List<Stages> --> List<List<Stages>>
     *
     * @param stagesMap
     * @return
     */
    public List<List<Stages>> stageMapToList(Map<Integer, List<Stages>> stagesMap) {
        List<List<Stages>> objects = new ArrayList<>();
        for (Map.Entry<Integer, List<Stages>> entry : stagesMap.entrySet()) {
            List<Stages> stagesList = entry.getValue();
            objects.add(stagesList);
        }
        return objects;
    }

    /**
     * Map<Integer, List<Stages>> --> List<Stages>
     *
     * @param stagesMap
     * @return
     */
    public List<Stages> stagesMapToAList(Map<Integer, List<Stages>> stagesMap) {
        List<List<Stages>> stagesList = stageMapToList(stagesMap);
        return stagesListToAList(stagesList);
    }

    /**
     * List<List<Stages>> --> Map<Integer, List<Stages>>
     *
     * @param stagesList
     * @return
     */
    public Map<Integer, List<Stages>> stagesListToMap(List<List<Stages>> stagesList) {
        Map<Integer, List<Stages>> stagesMap = new HashMap<>();
        for (int i = 0; i < stagesList.size(); i++) {
            stagesMap.put(stagesList.get(i).get(0).getStageType(), stagesList.get(i));
        }
        return stagesMap;
    }

    /**
     * List<List<Stages>> --> List<Stages>
     *
     * @param stagesList
     * @return
     */
    public List<Stages> stagesListToAList(List<List<Stages>> stagesList) {
        List<Stages> stagesLists = new ArrayList<>();
        for (int i = 0; i < stagesList.size(); i++) {
            stagesLists.addAll(stagesList.get(i));
        }
        return stagesLists;
    }

    /**
     * List<Stages> --> List<List<Stages>>
     *
     * @param stages
     * @return
     */
    public List<List<Stages>> aListToStagesList(List<Stages> stages) {
        Map<Integer, List<Stages>> stagesMap = CollectionUtil.buildMultimap(stages, Integer.class, Stages.class, "stageType");
        return stageMapToList(stagesMap);
    }

    /**
     * List<Stages> --> Map<Integer, List<Stages>>
     *
     * @param stages
     * @return
     */
    public Map<Integer, List<Stages>> aListToStagesMap(List<Stages> stages) {
        Map<Integer, List<Stages>> stagesMap = CollectionUtil.buildMultimap(stages, Integer.class, Stages.class, "stageType");
        return stagesMap;
    }

    public Integer getAbility(String runProject) {
        Integer ability = null;
        switch (runProject) {
            case "beginner_1":
                ability = 0;
                break;
            case "beginner_2":
                ability = 1;
                break;
            case "beginner_3":
                ability = 2;
                break;
            case "perf_5000m":
                ability = 3;
                break;
            case "perf_10k":
                ability = 4;
                break;
            case "perf_half":
                ability = 5;
                break;
            case "perf_full":
                ability = 6;
                break;
        }
        return ability;
    }

    /**
     * 获取用户计划
     *
     * @param uid
     * @return
     */
    public Object[] getUserRunPlan(Integer uid) {
        UserRunPlan userRunPlan = runPlanDao.findUserRunPlanByUid(uid);
        if (userRunPlan == null) {
            List<UserRunPlan> userRunPlans = runPlanDao.getPastPlanByUid(uid);
            if (userRunPlans.size() > 0) {
                userRunPlan = userRunPlans.get(userRunPlans.size() - 1);
                userRunPlan.setStagesList(aListToStagesList(userRunPlan.getStagesLists()));
                return new Object[]{1, userRunPlan};
            } else {
                return new Object[]{2};
            }
        } else {
            userRunPlan.setStagesList(aListToStagesList(userRunPlan.getStagesLists()));
            return new Object[]{0, userRunPlan};
        }

    }

    /**
     * 计划延期
     *
     * @param uid
     * @param delayDay
     * @return
     */
    public Object[] delayUserPlan(Integer uid, Integer delayDay) {
        UserRunPlan userRunPlan = runPlanDao.findUserRunPlanByUid(uid);
        if (userRunPlan == null) {
            return new Object[]{1};
        } else {
            List<Stages> stagesLists = userRunPlan.getStagesLists();
            Integer delayType = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long planTime = DateTime.parse(sdf.format(userRunPlan.getPlanTime())).toDate().getTime();
            long nowDate = DateUtil.getDayBegin(new Date()).getTime();
            boolean isSameDay = DateUtil.isSameDay(DateTime.parse(sdf.format(nowDate)).toDate(), DateTime.parse(sdf.format(planTime)).toDate());
            boolean isFinished = false;
            Integer diffDay = DateUtil.diffDay(new Date(), new Date(planTime));
            Integer count = diffDay + userRunPlan.getBeginTime();

            if(nowDate - planTime >= 0) {
                Stages stage = null;
                String today = DateUtil.format(new Date(), "yyyy-MM-dd");
                for (Stages s : stagesLists) {
                    if (today.equals(s.getDateTime())) {
                        stage = s;
                    }
                }
                if(stage.getState() == 1) {
                    isFinished = true;
                }
            }

            if(isSameDay) {
                if(isFinished) {
                    delayType = 1;
                    count++;
                } else {
                    delayType  = 0;
                }
            } else{
                if(nowDate - planTime < 0) {
                    delayType = 0;
                } else {
                    delayType = 1;
                    if(isFinished) {
                        count++;
                    }
                }
            }

            if (delayType == 0) { //延期整体计划
                DateTime dateTime = DateTime.parse(sdf.format(planTime));
                dateTime = dateTime.plusDays(delayDay);
                planTime = dateTime.toDate().getTime();
                Integer newBeginTime = DateUtil.dayForWeek(dateTime.toDate()) - 1;
                userRunPlan.setBeginTime(newBeginTime);
                userRunPlan.setPlanTime(planTime);
                int classify = -1;
                Double abilityTime = userRunPlan.getAbilityTime();
                if (userRunPlan.getAbility() < 3 && userRunPlan.getAbility() >= 0) {
                    classify = runPlanDao.getRunPlanClassify(userRunPlan.getType(), userRunPlan.getAbility(), -1).getClassify();
                } else if (userRunPlan.getAbility() >= 3) {
                    classify = runPlanDao.getRunPlanClassify(userRunPlan.getType(), userRunPlan.getAbility(), abilityTime.intValue()).getClassify();
                }
                Map<Integer, List<Stages>> stagesMap = CollectionUtil.buildMultimap(getStagesList(userRunPlan.getType(), classify, userRunPlan.getBeginTime(), userRunPlan.getResultTime(), userRunPlan.getPlanTime()), Integer.class, Stages.class, "stageType");
                userRunPlan.setStagesLists(stagesMapToAList(stagesMap));

                Integer lastTimeRun = 0;
                for (int list = 0; list < userRunPlan.getStagesLists().size(); list++) {
                    Stages stage = userRunPlan.getStagesLists().get(list);
                    if (stage.getStageContent().contains("比赛日")) {
                        userRunPlan.setCompetitionTime(dateTime.plusDays(list - newBeginTime).toDate().getTime());
                    }
                    if (stage.getDistance() > 0) {
                        lastTimeRun = list;
                    }
                }
                userRunPlan.setEndTime(DateTime.parse(sdf.format(planTime)).plusDays(lastTimeRun - userRunPlan.getBeginTime()).toDate().getTime());
                userRunPlan.setStagesList(aListToStagesList(userRunPlan.getStagesLists()));
                userRunPlan = adjustPlanState(userRunPlan);
                userRunPlan.setStagesLists(adjustPlanDate(userRunPlan.getStagesLists(), userRunPlan.getBeginTime(), userRunPlan.getPlanTime()));
            } else {
                if (CollectionUtils.isEmpty(stagesLists)) {
                    return new Object[]{1};
                }
                Stages competitionStages = stagesLists.get(count);
                competitionStages.setState(-1);
                stagesLists.set(count, competitionStages);
                for (int i = 0; i < delayDay; i++) {
                    Stages tempStage = new Stages();
                    tempStage.setStageName(stagesLists.get(count).getStageName());
                    tempStage.setStageType(stagesLists.get(count).getStageType());
                    tempStage.setStageContent("休息");
                    tempStage.setContentDescription("与跑步一样，休息也是训练中的重要部分。它可让您的身体恢复，使您在下一次跑步中变得更强。休息能够提高当前训练的效果。");
                    tempStage.setDate(1);
                    tempStage.setDistance(0.0);
                    tempStage.setState((i == 0 && !isFinished) ? 0 : -1);
                    stagesLists.add(count + i, tempStage);
                }

                userRunPlan.setStagesLists(stagesLists);
                //计算延后的比赛时间
                DateTime competitionTime = DateTime.parse(sdf.format(userRunPlan.getCompetitionTime())).plusDays(delayDay);
                DateTime endTime = DateTime.parse(sdf.format(userRunPlan.getEndTime())).plusDays(delayDay);
                userRunPlan.setCompetitionTime(competitionTime.toDate().getTime());
                userRunPlan.setEndTime(endTime.toDate().getTime());
                userRunPlan.setStagesList(aListToStagesList(userRunPlan.getStagesLists()));
                Map<Integer, List<Stages>> stMap = aListToStagesMap(userRunPlan.getStagesLists());
                stMap = adjustPlan(stMap, userRunPlan.getPlanTime(), userRunPlan.getBeginTime());
                userRunPlan.setStagesLists(adjustPlanDate(stagesMapToAList(stMap), userRunPlan.getBeginTime(), userRunPlan.getPlanTime()));
                Integer endMax = 0;
                for (int end = 0; end < userRunPlan.getStagesLists().size(); end++) {
                    Stages tempStage = userRunPlan.getStagesLists().get(end);
                    if (tempStage.getDistance() > 0) {
                        endMax = end;
                    }
                }
                endTime = DateTime.parse(sdf.format(userRunPlan.getPlanTime())).plusDays(endMax - userRunPlan.getBeginTime());
                userRunPlan.setEndTime(endTime.toDate().getTime());
                stagesLists = userRunPlan.getStagesLists();
                Integer diffEnd = DateUtil.diffDay(endTime.toDate(), DateUtil.parse(userRunPlan.getPlanTime())) + userRunPlan.getBeginTime();
                if ((stagesLists.size() - diffEnd) % 7 > 0) {
                    stagesLists = stagesLists.subList(0, stagesLists.size() - (stagesLists.size() - diffEnd) / 7 * 7);
                }
                userRunPlan.setStagesLists(stagesLists);
            }
            userRunPlan.setStagesList(aListToStagesList(userRunPlan.getStagesLists()));
            runPlanDao.delayUserRunPlan(userRunPlan);
            return new Object[]{0, userRunPlan};
        }

    }

    /**
     * 更新训练计划完成状态
     */
    public void updateUserRunPlan(Integer uid, Long currentTime) {
        UserRunPlan userRunPlan = runPlanDao.findUserRunPlanByUid(uid);
        if(userRunPlan == null) {//没有活动
            return;
        }
        Date currentDate = new Date(currentTime);
        String currentDateStr = DateUtil.format(currentDate, "yyyy-MM-dd");
        Stages stage = null;
        for (Stages s : userRunPlan.getStagesLists()) {
            if (currentDateStr.equals(s.getDateTime())) {
                stage = s;
                break;
            }
        }

        if (stage == null) {
            return;
        }
        if (stage.getStageContent().contains("休息")) {
            return;
        }

        if (Stages.STATE_SUCCESS.equals(stage.getState())) {//如果今天处理过了，则不再进行处理
            return;
        }

        List<UserRun> userRuns = userRunService.findUserRun(uid, currentDate);

        Long distance = 0L;
        for (UserRun ur : userRuns) {
            distance += ur.getDistance();
        }

        if (stage.getDistance() * 1000 <= distance) {//完成任务
            stage.setState(Stages.STATE_SUCCESS);
            userRunPlan.setCompletedDistance(userRunPlan.getCompletedDistance() + stage.getDistance());
            userRunPlan.setCompletedRunDay(userRunPlan.getCompletedRunDay() + 1);
            Update update = new Update();
            update.set("stagesLists", userRunPlan.getStagesLists());
            update.set("completedDistance", userRunPlan.getCompletedDistance());
            update.set("completedRunDay", userRunPlan.getCompletedRunDay());
            runPlanDao.updateUserRunPlan(update, uid);
        }
    }

    /**
     * 获取用户已结束的计划列表
     *
     * @param uid
     * @return
     */
    public Object[] getPastPlanByUid(Integer uid) {
        List<UserRunPlan> userRunPlans = runPlanDao.getPastPlanByUid(uid);
        if (userRunPlans.size() == 0) {
            return new Object[]{1};
        } else {
            return new Object[]{0, userRunPlans};
        }
    }

    /**
     * 放弃计划(app)
     *
     * @param uid
     * @return
     */
    public Object[] giveUpPlan(Integer uid) {
        UserRunPlan userRunPlan = runPlanDao.findUserRunPlanByUid(uid);
        if (userRunPlan == null) {
            return new Object[]{1};
        } else {
            runPlanDao.deleteUserRunPlan(uid);
            return new Object[]{0};
        }
    }

    /**
     * 获取第一周训练计划内容
     *
     * @param type
     * @param classify
     * @param beginTime
     * @return
     */
    public List<Stages> getFirstWeekStages(Integer type, Integer classify, Integer beginTime) {
        List<Stages> stages = runPlanDao.findRunPlanTemplet(type, classify, beginTime).getStages();
        return stages.subList(0, 6);
    }

    /**
     * 根据type统计RunPlanTemplate
     *
     * @param type
     * @return
     */
    public List<String> getDiffDayByType(Integer type) {
        List<RunPlanTemplate> runPlanTemplates = runPlanDao.getRunPlanTemplateByType(type);
        List<String> str = new ArrayList<>();
        boolean state = false;
        for (int i = 0; i < runPlanTemplates.size(); i++) {
            RunPlanTemplate runPlanTemplate = runPlanTemplates.get(i);
            List<Stages> stages = runPlanTemplate.getStages();
            for (int j = runPlanTemplate.getBeginTime(); j < stages.size(); j++) {
                Stages stage = stages.get(j);
                if (stage.getStageContent().contains("比赛日")) {
                    String temp = "id:" + runPlanTemplate.getId() + " diffDay:" + (j - runPlanTemplate.getBeginTime()) + " size:" + runPlanTemplate.getStages().size();
                    str.add(temp);
                    state = true;
                    break;
                }
            }
            if (state) {
                state = false;
                continue;
            } else {
                str.add("id:" + runPlanTemplate.getId() + "没有比赛日");
            }
        }
        return str;
    }

    /**
     * 获取今天的执行计划
     *
     * @param uid
     * @return
     */
    public Object[] getStageInToday(Integer uid) {
        UserRunPlan userRunPlan = runPlanDao.findUserRunPlanByUid(uid);
        if (userRunPlan == null) {
            return new Object[]{1};
        } else {
            List<Stages> stages = userRunPlan.getStagesLists();
            String today = DateUtil.format(new Date(), "yyyy-MM-dd");
            for (Stages s : stages) {
                if (today.equals(s.getDateTime())) {
                    return new Object[]{0, s};
                }
            }
            return new Object[]{2};
        }
    }


    /**
     * 计划时间校正
     *
     * @param stages
     * @param beginTime
     * @param planTime
     * @return
     */
    public List<Stages> adjustPlanDate(List<Stages> stages, Integer beginTime, Long planTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTime startTime = DateTime.parse(sdf.format(planTime)).plusDays(-beginTime);
        for (int i = 0; i < stages.size(); i++) {
            Stages stage = stages.get(i);
            stage.setDate(i);
            stage.setDateTime(sdf.format(startTime.plusDays(i).toDate()));
            stage.setDateLong(startTime.plusDays(i).toDate().getTime());
            stages.set(i, stage);
        }
        return stages;
    }

    /**
     * 校正计划状态
     *
     * @param userRunPlan
     * @return
     */
    public UserRunPlan adjustPlanState(UserRunPlan userRunPlan) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //初始化执行阶段及天数
        Date nowDate = new Date();
        Date planDate = DateTime.parse(sdf.format(userRunPlan.getPlanTime())).toDate();
        Stages ttempStage;
        List<List<Stages>> tempSS = userRunPlan.getStagesList();
        if (DateUtil.diffDay(nowDate, planDate) >= 0) {
            //stagesList的第一个
            userRunPlan.setExecuteStage(0);
            userRunPlan.setExecuteNo(userRunPlan.getBeginTime());

            //对初始状态赋值
            for (int i = 0; i <= userRunPlan.getBeginTime(); i++) {
                if (i == userRunPlan.getBeginTime()) {
                    ttempStage = userRunPlan.getStagesList().get(userRunPlan.getExecuteStage()).get(userRunPlan.getExecuteNo());
                    ttempStage.setState(0);
                    List<Stages> tempS = userRunPlan.getStagesList().get(userRunPlan.getExecuteStage());
                    tempS.set(userRunPlan.getExecuteNo(), ttempStage);
                    tempSS.set(userRunPlan.getExecuteStage(), tempS);
                } else {
                    ttempStage = userRunPlan.getStagesList().get(userRunPlan.getExecuteStage()).get(i);
                    ttempStage.setState(1);
                    List<Stages> tempS = userRunPlan.getStagesList().get(userRunPlan.getExecuteStage());
                    tempS.set(i, ttempStage);
                    tempSS.set(userRunPlan.getExecuteStage(), tempS);
                }
            }

            userRunPlan.setStagesList(tempSS);
            userRunPlan.setStages(stagesListToMap(userRunPlan.getStagesList()));
        } else {
            Date firstTime = DateUtil.getWeekBegin(planDate);
            Date nowTime = DateUtil.getDayBegin(new Date());
            Integer nowDayForWeek = DateUtil.dayForWeek(nowTime) - 1;
            if (DateUtil.diffDay(nowTime, firstTime) >= 0) {
                for (int n = 0; n <= nowDayForWeek; n++) {
                    if (n == nowDayForWeek) {
                        ttempStage = userRunPlan.getStagesList().get(0).get(nowDayForWeek);
                        ttempStage.setState(0);
                    } else {
                        ttempStage = userRunPlan.getStagesList().get(0).get(n);
                        ttempStage.setState(1);
                    }
                    List<Stages> tempS = userRunPlan.getStagesList().get(0);
                    tempS.set(n, ttempStage);
                    tempSS.set(0, tempS);

                }
            } else {
                ttempStage = userRunPlan.getStagesList().get(0).get(0);
                ttempStage.setState(0);
                List<Stages> tempS = userRunPlan.getStagesList().get(0);
                tempS.set(0, ttempStage);
                tempSS.set(0, tempS);
            }

            //-1代表计划未开始
            userRunPlan.setExecuteStage(-1);
            userRunPlan.setExecuteNo(-1);

        }
        return userRunPlan;
    }

    public int delayPlanPermit(Integer uid, Integer delayDay) {
        UserRunPlan userRunPlan = runPlanDao.findUserRunPlanByUid(uid);
        int type = userRunPlan.getType();
        int ability = userRunPlan.getAbility();
        int abilityTime = userRunPlan.getAbilityTime().intValue();
        List<Stages> stages = runPlanDao.findRunPlanTemplet(type, runPlanDao.getRunPlanClassify(type, ability, abilityTime).getClassify(), userRunPlan.getBeginTime()).getStages();
        if ((userRunPlan.getStagesLists().size() + delayDay) > stages.size() * 4 / 3) {
            return 2;
        }
        if (DateUtil.formatDate2Int(new Date(userRunPlan.getEndTime())) < DateUtil.formatDate2Int(new Date())) {
            return 3;
        }
        return 1;
    }

}
