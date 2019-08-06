package com.business.work.language;

import com.business.core.entity.CityNo;
import com.business.core.entity.Dictionary;
import com.business.core.entity.Page;
import com.business.core.entity.code.CodeMessage;
import com.business.core.entity.language.CharTable;
import com.business.core.entity.runPlan.Stages;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.entity.task.TaskInfo;
import com.business.core.entity.user.RunLevelInfo;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.language.CharTableCoreDao;
import com.business.work.dic.DictionaryService;
import com.business.work.runLevel.RunLevelService;
import com.business.work.task.TaskInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by edward on 2017/8/17.
 */
@Service
public class CharTableService {

    private static Logger logger = LoggerFactory.getLogger(CharTableService.class);

    @Autowired
    private DefaultDao defaultDao;
    @Autowired
    private RunLevelService runLevelService;
    @Autowired
    private CharTableCoreDao charTableCoreDao;
    @Autowired
    private CharTableDao charTableDao;
    @Autowired
    private TaskInfoService taskInfoService;
    @Autowired
    private DictionaryService dictionaryService;

    public void page(Page<CharTable> page) {
        charTableCoreDao.page(page);
    }

    /**
     * 新增需要翻译的 中文
     *
     * @param strCN 中文
     */
    public void addCharTable(String strCN) {
        charTableCoreDao.upset(strCN);
    }

    public void toCompared(Integer table) {
        switch (table) {
            case CharTable.TYPE_DICTIONARY:
                comparedDictionary();
                break;
            case CharTable.TYPE_CITY_NO:
                compareCityNo();
                break;
            case CharTable.TYPE_CODE_MESSAGE:
                compareCodeMessage();
                break;
            case CharTable.TYPE_USER_RUN_PLAN:
                compareUserRunPlan();
                break;
            case CharTable.TYPE_RUN_LEVEL_INFO:
                compareRunLevelInfo();
                break;
            case CharTable.TYPE_TASK_TYPE:
                compareTaskType();
                break;
        }
    }

    public void editEn(String cn, String en) {
        charTableDao.editEn(cn, en);
    }

    /**
     * 字典表 翻译
     */
    private void comparedDictionary() {
        List<Dictionary> sources = dictionaryService.findAll();
        for (Dictionary d : sources) {
            charTableCoreDao.upset(d.getName());
        }
    }

    /**
     * 地理位置表
     */
    private void compareCityNo() {
        if (true) {//目前不开放该翻译
            return;
        }
        List<CityNo> sources = defaultDao.findAll(CityNo.class);
        for (CityNo c : sources) {
            charTableCoreDao.upset(c.getAreaCh());
            charTableCoreDao.upset(c.getCountryCh());
            charTableCoreDao.upset(c.getDistrictCh());
            charTableCoreDao.upset(c.getProvCh());
        }
    }

    /**
     * 错误码翻译
     */
    private void compareCodeMessage() {
        List<CodeMessage> sources = defaultDao.findAll(CodeMessage.class);
        for (CodeMessage c : sources) {
            charTableCoreDao.upset(c.getMsgChina());
        }
    }

    /**
     * 训练计划翻译
     */
    private void compareUserRunPlan() {
        List<UserRunPlan> sources = defaultDao.query(new Query(), UserRunPlan.class, "uid");

        Integer beginId = 0;
        for (int i = 0; i < sources.size(); i += 100) {
            logger.error("beginId = " + beginId );
            if (i % 100 == 0) {
                UserRunPlan runPlan = sources.get(i);
                List<UserRunPlan> targets = defaultDao.query(new Query(Criteria.where("id").gte(beginId).lt(runPlan.getId())), UserRunPlan.class, "stagesLists");
                forRunPlan(targets);
                beginId = runPlan.getId();
            }
            if (i + 100 > sources.size()) {
                UserRunPlan endPlan = sources.get(sources.size() - 1);
                List<UserRunPlan> targets = defaultDao.query(new Query(Criteria.where("id").gte(beginId).lt(endPlan.getId())), UserRunPlan.class, "stagesLists");
                forRunPlan(targets);
                break;
            }
        }
    }

    private void forRunPlan(List<UserRunPlan> targets) {

        Set<String> target1 = new HashSet<>();
        Set<String> target2 = new HashSet<>();
        Set<String> target3 = new HashSet<>();

        for (UserRunPlan rp : targets) {
            if (!CollectionUtils.isEmpty(rp.getStagesLists())) {
                for (Stages stages : rp.getStagesLists()) {
                    target1.add(stages.getStageName());
                    target2.add(stages.getStageContent());
                    target3.add(stages.getContentDescription());
                }
            }
        }

        for (String a : target1) {
            charTableCoreDao.upset(a);
        }

        for (String a : target2) {
            charTableCoreDao.upset(a);
        }

        for (String a : target3) {
            charTableCoreDao.upset(a);
        }
    }

    /**
     * 跑步等级
     */
    private void compareRunLevelInfo() {
        List<RunLevelInfo> sources = runLevelService.selectByParams(new HashMap<String, Object>());
        for (RunLevelInfo r : sources) {
            charTableCoreDao.upset(r.getName());
        }
    }

    /**
     * 金币任务
     */
    private void compareTaskType() {
        List<TaskInfo> sources = taskInfoService.list();
        for (TaskInfo ti : sources) {
            charTableCoreDao.upset(ti.getDescription());
        }
    }

}
