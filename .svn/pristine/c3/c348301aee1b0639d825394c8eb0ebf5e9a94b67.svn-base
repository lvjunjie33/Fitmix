package com.business.core.operations.language;

import com.business.core.entity.Dictionary;
import com.business.core.entity.code.CodeMessage;
import com.business.core.entity.language.CharTable;
import com.business.core.entity.runPlan.Stages;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.entity.task.TaskInfo;
import com.business.core.entity.user.AccountFlow;
import com.business.core.entity.user.RunLevelInfo;
import com.business.core.mongo.DefaultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2017/8/29.
 */
@Service
public class CharTableCoreService {

    @Autowired
    private DefaultDao defaultDao;

    private static final Map<String, String> CHAR_TABLE = new HashMap<>();

    private void cache(String strCN) {
        if (!CHAR_TABLE.containsKey(strCN)) {
            CharTable charTable = defaultDao.findOne(Query.query(Criteria.where("strCN").is(strCN)), CharTable.class, "strCN", "strEN");
            if (charTable != null && !StringUtils.isEmpty(charTable.getStrEN())) {
                CHAR_TABLE.put(strCN, charTable.getStrEN());
            }
        }
    }

    public void codeMessageToEn(CodeMessage codeMessage) {
        if (codeMessage == null) {
            return;
        }
        String msg = codeMessage.getMsgChina();
        cache(msg);
        if (CHAR_TABLE.containsKey(msg)) {
            codeMessage.setMsgChina(CHAR_TABLE.get(msg));
        }
    }

    public void dicsToEn(List<Dictionary> dictionaries) {
        if (CollectionUtils.isEmpty(dictionaries)) {
            return;
        }
        for (Dictionary dictionary : dictionaries) {
            dicToEn(dictionary);
        }
    }

    public void dicToEn(Dictionary dictionary) {
        if (dictionary == null) {
            return;
        }
        String name = dictionary.getName();
        cache(name);
        if (CHAR_TABLE.containsKey(name)) {
            dictionary.setName(CHAR_TABLE.get(name));
        }
    }



    public void runPlansToEn(List<UserRunPlan> runPlans) {
        if (!CollectionUtils.isEmpty(runPlans)) {
            for (UserRunPlan userRunPlan : runPlans) {
                runPlanToEn(userRunPlan);
            }
        }
    }

    public void runPlanToEn(UserRunPlan runPlan) {
        if (runPlan == null || CollectionUtils.isEmpty(runPlan.getStagesLists())) {
            return;
        }

        {
            String typeName = runPlan.getTypeName();
            if (!StringUtils.isEmpty(typeName)) {
                cache(typeName);
                if (CHAR_TABLE.containsKey(typeName)) {
                    runPlan.setTypeName(CHAR_TABLE.get(typeName));
                }
            }
        }

        for (Stages stages : runPlan.getStagesLists()) {
            stagesToEn(stages);
        }
    }

    public void stagesToEn(Stages stages) {
        if (stages == null) {
            return;
        }
        String stageName = stages.getStageName();
        String stageContent = stages.getStageContent();
        String contentDescription = stages.getContentDescription();

        cache(stageName);
        if (CHAR_TABLE.containsKey(stageName)) {
            stages.setStageName(CHAR_TABLE.get(stageName));
        }
//
        cache(stageContent);
        if (CHAR_TABLE.containsKey(stageContent)) {
            stages.setStageContent(CHAR_TABLE.get(stageContent));
        }
//
        cache(contentDescription);
        if (CHAR_TABLE.containsKey(contentDescription)) {
            stages.setContentDescription(CHAR_TABLE.get(contentDescription));
        }
    }

    public void runLevelInfoToEn(RunLevelInfo runLevelInfo) {
        if (runLevelInfo == null) {
            return;
        }
        String name = runLevelInfo.getName();
        cache(name);
        if (CHAR_TABLE.containsKey(name)) {
            runLevelInfo.setName(CHAR_TABLE.get(name));
        }
    }

    public void taskInfosToEn(List<TaskInfo> taskInfos) {
        if (!CollectionUtils.isEmpty(taskInfos)) {
            for (TaskInfo ti : taskInfos) {
                taskInfoToEn(ti);
            }
        }
    }

    public void taskInfoToEn(TaskInfo taskInfo) {
        if (taskInfo == null) {
            return;
        }

        String description = taskInfo.getDescription();
        cache(description);
        if (CHAR_TABLE.containsKey(description)) {
            taskInfo.setDescription(CHAR_TABLE.get(description));
        }
    }


    public void accountFlowsToEn(List<AccountFlow> accountFlows) {
        if (CollectionUtils.isEmpty(accountFlows)) {
            return;
        }
        for (AccountFlow accountFlow : accountFlows) {
            accountFlowToEn(accountFlow);
        }

    }

    public void accountFlowToEn(AccountFlow accountFlow) {
        if (accountFlow == null) {
            return;
        }

        String des = accountFlow.getDescription();
        cache(des);
        if (CHAR_TABLE.containsKey("des")) {
            accountFlow.setDescription(CHAR_TABLE.get(des));
        }
    }

}
