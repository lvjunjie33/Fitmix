package com.business.work.runPlan;

import com.business.core.entity.Page;
import com.business.core.entity.runPlan.*;
import com.business.core.sort.SortFactory;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by weird on 2016/5/17.
 */
@Service
public class RunPlanService {

    @Autowired
    private RunPlanDao runPlanDao;

    /**
     * 跑步计划模板(分页)
     *
     * @param page
     */
    public void templetPage(Page<RunPlanTemplate> page) {
        runPlanDao.findTempletWithPage(page);
    }


    /**
     * get runPlanTemplet by id
     *
     * @param id
     * @return
     */
    public Map<Integer, List<Stages>> findRunPlanTempletById(Integer id) {
        List<Stages> stages = runPlanDao.findRunPlanTempletById(id, "id", "type", "beginTime", "classify", "stages").getStages();
        Map<Integer, List<Stages>> stagesMap = CollectionUtil.buildMultimap(stages, Integer.class, Stages.class, "stageType");

        for (Map.Entry<Integer, List<Stages>> entry : stagesMap.entrySet()) {
            List<Stages> stages1 = entry.getValue();
            Collections.sort(stages1, SortFactory.STAGES_SORT);
            stagesMap.put(entry.getKey(), stages1);
        }
        return stagesMap;
    }


    /**
     * update runPlanTemplet
     *
     * @param stageName
     * @param result
     * @param id
     */
    public void changeToRunPlanTemplet(String stageName, String result, Integer id) {
        Map<Integer, List<Stages>> stagesMap = findRunPlanTempletById(id);
        RunPlanTemplate runPlanTemplate = runPlanDao.findRunPlanTempletById(id, "id", "type", "beginTime", "classify", "stages");
        String[] name = stageName.split(",");
        String[] content = result.split(",");
        int countName = 0;
        int countContent = 0;
        for (Map.Entry<Integer, List<Stages>> entry : stagesMap.entrySet()) {
            List<Stages> stages = stagesMap.get(entry.getKey());
            for (int j = 0; j < stages.size(); j++) {
                stages.get(j).setStageName(name[countName]);
                stages.get(j).setStageContent(content[countContent]);
                countContent++;
            }
            countName++;
            stagesMap.put(entry.getKey(), stages);
        }
        List<Stages> stages = stagesMap.get(0);
        for (Map.Entry<Integer, List<Stages>> entry : stagesMap.entrySet()) {
            if (entry.getKey() != 0) {
                stages.addAll(stagesMap.get(entry.getKey()));
            }
        }
        runPlanTemplate.setStages(stages);
        runPlanDao.updateRunPlanTemplet(runPlanTemplate);
    }

    /**
     * 跑步计划速度列表(分页)
     *
     * @param page
     */
    public void runPlanSpeedPage(Page<RunPlanSpeed> page) {
        runPlanDao.runPlanSpeedWithPage(page);
    }

    /**
     * get runPlanSpeed by id
     *
     * @param id
     * @return
     */
    public RunPlanSpeed findRunPlanSpeedById(Integer id) {
        return runPlanDao.findRunPlanSpeedById(id, "id", "type", "classify", "ages", "speed", "gender");
    }

    /**
     * runPlanSpeed modify
     *
     * @param id
     * @param type
     * @param gender
     * @param ages
     * @param speed
     */
    public void changeRunPlanSpeed(Integer id, Integer type, Integer classify, Integer gender, Integer ages, String speed) {
        runPlanDao.updateRunPlanSpeed(id, Update.update("type", type).set("gender", gender).set("ages", ages).set("speed", speed).set("classify", classify));
    }

    /**
     * RunPlanStageRatio list (page)
     *
     * @param page
     */
    public void stageRatioPage(Page<RunPlanStageRatio> page) {
        runPlanDao.findStageRatioWithPage(page);
    }

    /**
     * get RunPlanStageRatio by type
     *
     * @param type
     * @return
     */
    public RunPlanStageRatio findStageRatioByType(Integer type) {
        return runPlanDao.findRunPlanStageRatioByType(type, "type", "runStages", "distance");
    }

    /**
     * runPlanStageRatio modify
     *
     * @param result
     */
    public void changeStageRatio(String result) {
        Update update = new Update();
        String[] temp = result.split(",");

        Map map = new HashMap<String, Object>();
        for (int i = 0; i < temp.length; i++) {
            if (i % 2 != 0) {
                continue;
            } else {
                map.put(temp[i], temp[i + 1]);
            }
        }
        int type = Integer.parseInt(map.get("type").toString());
        map.remove("type");

        Double distance = Double.parseDouble(map.get("distance").toString());
        map.remove("distance");
        update.set("distance", distance);

        RunPlanStageRatio runPlanStageRatio = runPlanDao.findRunPlanStageRatioByType(type, "type", "runStages");
        List<RunStage> runStages = runPlanStageRatio.getRunStages();

        for (int j = 0; j < runStages.size(); j++) {
            String name = runStages.get(j).getNameEn();
            String[] temp1 = map.get(name).toString().split("-");
            if (temp1.length == 1) {
                Double[] array = new Double[]{Double.parseDouble(temp1[0])};
                runStages.get(j).setRatio(array);
            } else if (temp1.length == 2) {
                Double[] array = new Double[]{Double.parseDouble(temp1[0]), Double.parseDouble(temp1[1])};
                runStages.get(j).setRatio(array);
            }
        }

        update.set("runStages", runStages);

        runPlanDao.updateStageRatio(type, update);

    }

    /**
     * runPlanClassify list (page)
     *
     * @param page
     */
    public void runPlanClassifyPage(Page<RunPlanClassify> page) {
        runPlanDao.findClassifyWithPage(page);
    }

    /**
     * add runPlanClassify
     *
     * @param type
     * @param ability
     * @param classify
     * @param speed_min
     * @param speed_max
     */
    public void insertRunPlanClassify(Integer type, Integer ability, Integer classify, Integer speed_min, Integer speed_max) {
        RunPlanClassify runPlanClassify = new RunPlanClassify();
        runPlanClassify.setType(type);
        runPlanClassify.setAbility(ability);
        runPlanClassify.setClassify(classify);
        runPlanClassify.setSpeed_min(speed_min);
        runPlanClassify.setSpeed_max(speed_max);
        runPlanDao.insertRunPlanClassify(runPlanClassify);
    }

    /**
     * RunPlanClassify modify
     *
     * @param id
     * @param type
     * @param ability
     * @param classify
     * @param speed_min
     * @param speed_max
     */
    public void updateRunPlanClassify(Integer id, Integer type, Integer ability, Integer classify, Integer speed_min, Integer speed_max) {
        Update update = new Update();
        update.set("type", type);
        update.set("ability", ability);
        update.set("classify", classify);
        update.set("speed_min", speed_min);
        update.set("speed_max", speed_max);
        runPlanDao.updateRunPlanClassify(update, id);
    }

    /**
     * get scheduled run time by run-plan
     *
     * @param type
     * @param ability
     * @param ability_time (分钟)
     * @param age
     * @param gender
     * @return (second)
     */
    public Integer findResultTime(Integer type, Integer ability, Double ability_time, Integer age, Integer gender) {
        int ages;
        if (age <= 30) {
            ages = 0;
        } else if (age <= 40) {
            ages = 1;
        } else {
            ages = 2;
        }
        if (ability <= 2) {
            String speed = runPlanDao.getSpeed(type, ability, ages, gender).getSpeed();
            String[] temp = speed.split(":");
            return Integer.parseInt(temp[0]) * 3600 + Integer.parseInt(temp[1]) * 60 + Integer.parseInt(temp[2]);
        } else {
            UserRunPlan userRunPlan = new UserRunPlan();
            userRunPlan.setType(type);
            userRunPlan.setAbility(ability);
            userRunPlan.setAbilityTime(ability_time);
            return userRunPlan.getResultTime();
        }
    }

    /**
     * get templet by type,ability and ability_time
     *
     * @param type
     * @param ability
     * @param ability_time
     * @return templet content
     */
    public List<Stages> findTemplet(Integer type, Integer ability, String ability_time, Integer beginTime) {
        String[] temp = ability_time.split(":");
        Integer speed = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
        Integer claasify;
        if (ability < 3) {
            claasify = runPlanDao.getRunPlanClassify(type, ability).getClassify();
        } else {
            claasify = runPlanDao.getRunPlanClassify_time(type, ability, speed).getClassify();
        }
        return runPlanDao.findRunPlanTemplet(type, claasify, beginTime).getStages();
    }

    /**
     * get stageSpeed
     *
     * @param type
     * @param ability
     * @param ability_time
     * @param age
     * @param gender
     * @return
     */
    public List<Stages> findStageSpeed(Integer type, Integer ability, String ability_time,
                                       Integer age, Integer gender, Integer beginTime) {
        List<String> stageSpeed = new ArrayList<String>();

        String[] temp = ability_time.split(":");
        Double speed = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]) + Double.parseDouble(temp[2]) / 60;

        //获取模板主体内容
        List<Stages> stages = findTemplet(type, ability, ability_time, beginTime);

        //获取个跑步类型的速度比值列表
        RunPlanStageRatio runPlanStageRatio = runPlanDao.getRunPlanStageRatio(type);
        List<RunStage> runStages = runPlanStageRatio.getRunStages();

        //获取预测时间以及比赛速度
        Integer resultTime = findResultTime(type, ability, speed, age, gender);
        Double perResultTime = resultTime / runPlanStageRatio.getDistance();

        //获取跑步类型
        Map runTypeMap = new HashMap<String, Integer[]>();
        String[] array = new String[runStages.size()];
        for (int i = 0; i < runStages.size(); i++) {
            array[i] = runStages.get(i).getName();
            Integer[] num = new Integer[runStages.get(i).getRatio().length];
            for (int j = 0; j < runStages.get(i).getRatio().length; j++) {
                num[j] = (int) (runStages.get(i).getRatio()[j] * perResultTime);
            }
            runTypeMap.put(array[i], num);
        }
        for (int j = 0; j < stages.size(); j++) {
            for (int k = 0; k < array.length; k++) {
                String stage = stages.get(j).getStageContent();
                if (stage.contains(array[k])) {
                    Integer[] time_second = (Integer[]) runTypeMap.get(array[k]);
                    String tempTime = "";
                    for (int n = 0; n < time_second.length; n++) {
                        if (time_second.length > 1 && n == 0) {
                            tempTime = time_second[n] / 60 / 60 + ":" + time_second[n] / 60 % 60 + ":" + time_second[n] % 60 % 60 + "-";
                        } else {
                            tempTime += time_second[n] / 60 / 60 + ":" + time_second[n] / 60 % 60 + ":" + time_second[n] % 60 % 60;
                        }
                    }
                    stages.get(j).setStageSpeed(tempTime + "/公里");
                }
                if (stage.contains("比赛")) {
                    int gameResult = (int) (perResultTime / 1);
                    stages.get(j).setStageSpeed(gameResult / 60 / 60 + ":" + gameResult / 60 % 60 + ":" + gameResult % 60 % 60 + "/公里");
                }
            }
        }

        return stages;
    }

    /**
     * create runPlan
     *
     * @param type
     * @param ability
     * @param ability_time
     * @param age
     * @param gender
     * @return
     */

    public UserRunPlan createRunPlan(Integer type, Integer ability, String ability_time, Integer age, Integer gender) {
        UserRunPlan userRunPlan = new UserRunPlan();
        Integer beginTime = DateUtil.dayForWeek(new Date()) - 1;
        String[] temp = ability_time.split(":");
        Double abilityTime = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]) + Double.parseDouble(temp[2]) / 60;
        userRunPlan.setType(type);
        userRunPlan.setAbility(ability);
        userRunPlan.setAbilityTime(abilityTime);
        userRunPlan.setBeginTime(beginTime);
        userRunPlan.setResultTime(findResultTime(type, ability, abilityTime, age, gender));
       /* Map<Integer,List<Stages>> stagesMap = CollectionUtil.buildMultimap(stages,Integer.class,Stages.class,"stageType");*/
        /*userRunPlan.setStages(stagesMap);*/
        return userRunPlan;
    }

    /**
     * 模板模型生成
     *
     * @param type
     * @param classify
     * @param beginTime
     * @param stage
     * @param stageContent
     * @return
     */
    public RunPlanTemplate modelGeneration(Integer type, Integer classify, Integer beginTime,
                                           Integer stage, Integer[] stageContent) {
        RunPlanTemplate runPlanTemplate = new RunPlanTemplate();

        runPlanTemplate.setType(type);
        runPlanTemplate.setClassify(classify);
        runPlanTemplate.setBeginTime(beginTime);

        List<Stages> stages = new ArrayList<>();

        for (int i = 0; i < stageContent.length; i++) {
            for (int j = 0; j < stageContent[i]; j++) {
                for (int k = 0; k < 7; k++) {
                    Stages st = new Stages();
                    st.setStageName(i + "");
                    stages.add(st);
                }
            }
        }

        runPlanTemplate.setStages(stages);


        return runPlanTemplate;
    }

    public Map<String, String> getDescription() {
        Map<String, String> desMap = new HashMap<String, String>();
        List<RunPlanDescription> runPlanDescriptions = runPlanDao.getDescription();
        for (RunPlanDescription runPlanDescription : runPlanDescriptions) {
            desMap.put(runPlanDescription.getKey(), runPlanDescription.getDescription());
        }
        return desMap;
    }

    public void addModule(String[] stageName, String[] content, RunPlanTemplate runPlanTemplate) {
        RunPlanTemplate runPlanTemplate1 = runPlanTemplate;
        List<Stages> stages = runPlanTemplate.getStages();
        int stage_name;
        for (int i = 0; i < content.length; i++) {
            stage_name = Integer.parseInt(stages.get(i).getStageName());

            stages.get(i).setStageName(stageName[stage_name]);

            //todo 增加添加后台管理维护内容 to weird
            switch (stageName[stage_name]) {
                case "预备":
                    stages.get(i).setStageType(0);
                    break;
                case "提高速度":
                    stages.get(i).setStageType(1);
                    break;
                case "进一步提高":
                    stages.get(i).setStageType(2);
                    break;
                case "模拟":
                    stages.get(i).setStageType(3);
                    break;
                case "比赛":
                    stages.get(i).setStageType(4);
                    break;
                case "恢复":
                    stages.get(i).setStageType(5);
                    break;
            }

            stages.get(i).setDate(i);

            stages.get(i).setState(-1);

            stages.get(i).setStageContent(content[i]);

            if (content[i].contains("-")) {
                String[] temp = content[i].split("-");
                double distance = 0.0;
                for (int j = 0; j < temp.length; j++) {
                    distance += Double.parseDouble(temp[j].split("公里")[0]);
                }
                stages.get(i).setDistance(distance);
            } else {
                if (content[i].contains("公里")) {
                    stages.get(i).setDistance(Double.parseDouble(content[i].split("公里")[0]));
                } else {
                    stages.get(i).setDistance(0.0);
                }
            }

            Map<String, String> desMap = getDescription();

            for (Map.Entry<String, String> entry : desMap.entrySet()) {
                if (stages.get(i).getStageContent().contains(entry.getKey())) {
                    stages.get(i).setContentDescription(entry.getValue());
                }
            }

        }
        runPlanTemplate1.setStages(stages);
        runPlanDao.insertRunPlanTemplate(runPlanTemplate1);
    }

    /**
     * 额外阶段(分页)
     *
     * @param page
     */
    public void extraPage(Page<RunPlanExtraStage> page) {
        runPlanDao.findExtraStageWithPage(page);
    }

    /**
     * 添加额外阶段
     *
     * @param type
     * @param classify
     * @param stageName
     * @param stageContent
     */
    public void addExtraStage(Integer type, Integer classify, String stageName, String[] stageContent) {
        List<Stages> stages = new ArrayList<>();
        for (int i = 0; i < stageContent.length; i++) {
            Stages stage = new Stages();
            stage.setStageName(stageName);
            stage.setStageType(6);
            stage.setDate(i);
            stage.setState(-1);
            stage.setStageContent(stageContent[i]);

            if (stageContent[i].contains("-")) {
                String[] temp = stageContent[i].split("-");
                double distance = 0.0;
                for (int j = 0; j < temp.length; j++) {
                    distance += Double.parseDouble(temp[j].split("公里")[0]);
                }
                stage.setDistance(distance);
            } else {
                if (stageContent[i].contains("公里")) {
                    stage.setDistance(Double.parseDouble(stageContent[i].split("公里")[0]));
                } else {
                    stage.setDistance(0.0);
                }
            }

            Map<String, String> desMap = getDescription();

            for (Map.Entry<String, String> entry : desMap.entrySet()) {
                if (stage.getStageContent().contains(entry.getKey())) {
                    stage.setContentDescription(entry.getValue());
                }
            }
            stages.add(stage);
        }

        RunPlanExtraStage runPlanExtraStage = new RunPlanExtraStage();
        runPlanExtraStage.setType(type);
        runPlanExtraStage.setClassify(classify);
        runPlanExtraStage.setStages(stages);

        runPlanDao.addRunPlanExtraStage(runPlanExtraStage);
    }

    /**
     * 描述内容(分页)
     *
     * @param page
     */
    public void desPage(Page<RunPlanDescription> page) {
        runPlanDao.findRunPlanDescriptionWithPage(page);
    }

    /**
     * 添加描述
     *
     * @param key
     * @param des
     */
    public void addDes(String key, String des) {
        RunPlanDescription runPlanDescription = new RunPlanDescription();
        runPlanDescription.setKey(key);
        runPlanDescription.setDescription(des);
        runPlanDao.addRunPlanDescription(runPlanDescription);

        List<RunPlanTemplate> runPlanTemplates = runPlanDao.getRunPlanTemplateList();
        for (int i = 0; i < runPlanTemplates.size(); i++) {
            RunPlanTemplate runPlanTemplate = runPlanTemplates.get(i);
            List<Stages> stages = runPlanTemplate.getStages();
            for (int j = 0; j < stages.size(); j++) {
                Stages stage = stages.get(j);
                if (stage.getStageContent().contains(key)) {
                    stage.setContentDescription(des);
                    stages.set(j, stage);
                }
            }
            runPlanTemplate.setStages(stages);
            runPlanDao.updateRunPlanTemplet(runPlanTemplate);
        }
    }

    /**
     * 用户界面(分页)
     *
     * @param page
     */
    public void userPlanPage(Page<UserRunPlan> page) {
        runPlanDao.findUserRunPlanWithPage(page);
    }

    /**
     * 获取计划
     *
     * @param id 编号
     */
    public UserRunPlan findUserRunPlan(Integer id) {
        return runPlanDao.findUserRunPlanById(id);
    }

    /**
     * 修改训练计划状态
     *
     * @param id 编号
     */
    public void modifyUserRunPlanStatus(Integer id, Integer status) {
        runPlanDao.modifyRunPlan(id, Update.update("plan_state", status));
    }

}
