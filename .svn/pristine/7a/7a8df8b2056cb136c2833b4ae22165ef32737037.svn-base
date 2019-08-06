package com.business.core.operations.runPlan;

import com.business.core.entity.runPlan.Stages;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weird on 2016/8/24.
 */
@Service
public class RunPlanCoreService {

    @Autowired
    private RunPlanCoreDao runPlanCoreDao;

    /**
     * 修改UserRunPlan
     * @param userRunPlan
     */
    public void updateUserRunPlan(UserRunPlan userRunPlan) {
        Update update = new Update();

        /*update.set("executeStage", userRunPlan.getExecuteStage());*/
        runPlanCoreDao.upDateUserRunPlan(userRunPlan.getId(), update);
    }

    public Map<Integer, List<Stages>> stagesListToMap(List<List<Stages>> stagesList) {
        Map<Integer, List<Stages>> stagesMap = new HashMap<>();
        for (int i = 0; i < stagesList.size(); i++) {
            stagesMap.put(stagesList.get(i).get(0).getStageType(), stagesList.get(i));
        }
        return stagesMap;
    }

    /**
     *  Map<Integer,List<Stages> --> List<List<Stages>>
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
     *  List<Stages> --> List<List<Stages>>
     * @param stages
     * @return
     */
    public List<List<Stages>> aListToStagesList(List<Stages> stages) {
        Map<Integer,List<Stages>> stagesMap = CollectionUtil.buildMultimap(stages, Integer.class, Stages.class, "stageType");
        return stageMapToList(stagesMap);
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

}
