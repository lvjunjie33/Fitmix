package com.business.core.entity.runPlan;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by weird on 2016/6/15.
 */
@Document(collection = "UserRunPlan")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class UserRunPlan extends IncIdEntity<Integer> {

    /**
     * 计划已废弃
     */
    public static final Integer PLAN_OBSOLETE = 0;

    /**
     * 计划执行中
     */
    public static final Integer PLAN_IMPLEMENTATION = 1;

    /**
     * 计划已完成
     */
    public static final Integer PLAN_OFF_THE_STHCKS = 2;

    /**
     *  计划类型——训练计划
     */
    public static final Integer PLAN_TYPE_TRAINING = 1;

    /**
     *  计划类型——比赛计划
     */
    public static final Integer PLAN_TYPE_COMPETITION = 2;


    /**
     * 用户id
     */
    private Integer uid;

    /**
     *  计划类型(训练 or 比赛)
     */
    private Integer planType;

    /**
     * 计划类型
     */
    private Integer type;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 能力类型
     */
    private Integer ability;

    /**
     * 所选能力类型的时间
     */
    private Double abilityTime;

    /**
     * 创建日期所属星期几
     */
    private Integer beginTime;

    /**
     * 计划开始时间
     */
    private Long planTime;

    /**
     * 目标日期
     */
    private Long competitionTime;

    /**
     * 计划结束时间
     */
    private Long endTime;

    /**
     * 预测计划后的完成目标时间
     */
    private Integer resultTime;

    /**
     * 预计完成目标速度时间格式
     */
    private String resultTimeStr;

    /**
     * 阶段内容
     */
    @Transient
    private Map<Integer, List<Stages>> stages;

    /**
     * 阶段内容——List<List<Stages>></>
     */
    @Transient
    private List<List<Stages>> stagesList;

    /**
     * 阶段内容——List<Stages></>
     */
    private List<Stages> stagesLists;

    /**
     * 总跑步距离
     */
    private Double distance;

    /**
     * 已跑距离
     */
    private Double completedDistance;

    /**
     * 总跑步天数
     */
    private Integer runDay;

    /**
     * 已跑天数
     */
    private Integer completedRunDay;

    /**
     * 完成度
     */
    private Integer complete_degree;

    /**
     * 当前计划状态
     */
    private Integer plan_state;

    /**
     * 当前执行阶段
     */
    private Integer executeStage;

    /**
     * 所处执行阶段的第几天
     */
    private Integer executeNo;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    public Double getAbilityTime() {
        return abilityTime;
    }

    public void setAbilityTime(Double abilityTime) {
        this.abilityTime = abilityTime;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Long getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Long planTime) {
        this.planTime = planTime;
    }

    public Long getCompetitionTime() {
        return competitionTime;
    }

    public void setCompetitionTime(Long competitionTime) {
        this.competitionTime = competitionTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getResultTime() {
        switch (type) {
            case 0:
                switch (ability) {
                    case 3:
                        if (abilityTime <= 13.53) {
                            resultTime = 808;
                        } else if (abilityTime <= 14.42) {
                            resultTime = (int) (58.44 * abilityTime + 17.31);
                        } else if (abilityTime < 34) {
                            resultTime = (int) (56 * abilityTime + 72);
                        } else {
                            resultTime = 1963;
                        }
                        break;
                    case 4:
                        if (abilityTime <= 28) {
                            resultTime = 808;
                        } else if (abilityTime < 56) {
                            resultTime = (int) (26 * abilityTime + 95);
                        } else if (abilityTime <= 74) {
                            resultTime = (int) (23 * abilityTime + 263);
                        } else {
                            resultTime = 1970;
                        }
                        break;
                    case 5:
                        if (abilityTime <= 61) {
                            resultTime = 808;
                        } else if (abilityTime <= 109) {
                            resultTime = (int) (11 * abilityTime + 166);
                        } else if (abilityTime <= 169) {
                            resultTime = (int) (10 * abilityTime + 276);
                        } else {
                            resultTime = 1970;
                        }
                        break;
                    case 6:
                        if (abilityTime <= 127) {
                            resultTime = 808;
                        } else if (abilityTime <= 225) {
                            resultTime = (int) (5 * abilityTime + 180);
                        } else if (abilityTime < 391) {
                            resultTime = (int) (4 * abilityTime + 406);
                        } else {
                            resultTime = 1970;
                        }
                }
                break;
            case 1:
                switch (ability) {
                    case 3:
                        if (abilityTime <= 13.53) {
                            resultTime = 1665;
                        } else if (abilityTime <= 15) {
                            resultTime = (int) (114 * abilityTime + 119.12);
                        } else if (abilityTime <= 34) {
                            resultTime = (int) (125 * abilityTime - 8);
                        } else {
                            resultTime = 4230;
                        }
                        break;
                    case 4:
                        if (abilityTime <= 27.06) {
                            resultTime = 1665;
                        } else if (abilityTime <= 30) {
                            resultTime = (int) (44.56 * abilityTime + 433.89);
                        } else if (abilityTime < 74) {
                            resultTime = (int) (55 * abilityTime + 189);
                        } else {
                            resultTime = 4230;
                        }
                        break;
                    case 5:
                        if (abilityTime <= 60) {
                            resultTime = 1665;
                        } else if (abilityTime <= 62) {
                            resultTime = (int) (22.5 * abilityTime + 311.5);
                        } else if (abilityTime <= 134) {
                            resultTime = (int) (24 * abilityTime + 254);
                        } else if (abilityTime < 172) {
                            resultTime = (int) (21 * abilityTime + 640);
                        } else {
                            resultTime = 4230;
                        }
                        break;
                    case 6:
                        if (abilityTime <= 129) {
                            resultTime = 1665;
                        } else if (abilityTime < 263) {
                            resultTime = (int) (10.5 * abilityTime + 349.5);
                        } else if (abilityTime < 389) {
                            resultTime = (int) (9 * abilityTime + 743);
                        } else {
                            resultTime = 4230;
                        }
                        break;
                }
                break;
            case 2:
                switch (ability) {
                    case 3:
                        if (abilityTime <= 13) {
                            resultTime = 3597;
                        } else if (abilityTime <= 14) {
                            resultTime = (int) (100 * abilityTime + 2297);
                        } else if (abilityTime <= 34) {
                            resultTime = (int) (288 * abilityTime - 271);
                        } else {
                            resultTime = 9495;
                        }
                        break;
                    case 4:
                        if (abilityTime <= 28) {
                            resultTime = 3597;
                        } else if (abilityTime <= 59) {
                            resultTime = (int) (131 * abilityTime - 75);
                        } else if (abilityTime < 74) {
                            resultTime = (int) (121 * abilityTime + 518);
                        } else {
                            resultTime = 9464;
                        }
                        break;
                    case 5:
                        if (abilityTime <= 60) {
                            resultTime = 3597;
                        } else if (abilityTime < 126) {
                            resultTime = (int) (56 * abilityTime + 238);
                        } else if (abilityTime <= 170) {
                            resultTime = (int) (49 * abilityTime + 1114);
                        } else {
                            resultTime = 9464;
                        }
                        break;
                    case 6:
                        if (abilityTime <= 129) {
                            resultTime = 3597;
                        } else if (abilityTime <= 281) {
                            resultTime = (int) (24 * abilityTime + 565);
                        } else if (abilityTime < 389) {
                            resultTime = (int) (20 * abilityTime + 1694);
                        } else {
                            resultTime = 9464;
                        }
                        break;
                }
                break;
            case 3:
                switch (ability) {
                    case 3:
                        if (abilityTime <= 13) {
                            resultTime = 7650;
                        } else if (abilityTime <= 14) {
                            resultTime = (int) (96 * abilityTime + 6402);
                        } else if (abilityTime <= 23) {
                            resultTime = (int) (653 * abilityTime - 1383);
                        } else if (abilityTime < 34) {
                            resultTime = (int) (623 * abilityTime - 414);
                        } else {
                            resultTime = 20697;
                        }
                        break;
                    case 4:
                        if (abilityTime <= 28) {
                            resultTime = 7650;
                        } else if (abilityTime < 73) {
                            resultTime = (int) (294 * abilityTime - 633);
                        } else {
                            resultTime = 20697;
                        }
                        break;
                    case 5:
                        if (abilityTime <= 61) {
                            resultTime = 7650;
                        } else if (abilityTime <= 130) {
                            resultTime = (int) (126 * abilityTime + 4);
                        } else if (abilityTime < 170) {
                            resultTime = (int) (108 * abilityTime + 2348);
                        } else {
                            resultTime = 20697;
                        }
                        break;
                    case 6:
                        if (abilityTime <= 129) {
                            resultTime = 7650;
                        } else if (abilityTime <= 281) {
                            resultTime = (int) (54 * abilityTime + 796);
                        } else if (abilityTime < 387) {
                            resultTime = (int) (44.5 * abilityTime + 3478.5);
                        } else {
                            resultTime = 20697;
                        }
                        break;
                }
                break;
        }
        return resultTime;
    }

    public void setResultTime(Integer resultTime) {
        this.resultTime = resultTime;
    }

    public String getResultTimeStr() {
        return resultTimeStr;
    }

    public void setResultTimeStr(String resultTimeStr) {
        this.resultTimeStr = resultTimeStr;
    }

    public Map<Integer, List<Stages>> getStages() {
        return stages;
    }

    public void setStages(Map<Integer, List<Stages>> stages) {
        this.stages = stages;
    }

    public List<List<Stages>> getStagesList() {
        return stagesList;
    }

    public void setStagesList(List<List<Stages>> stagesList) {
        this.stagesList = stagesList;
    }

    public List<Stages> getStagesLists() {
        return stagesLists;
    }

    public void setStagesLists(List<Stages> stagesLists) {
        this.stagesLists = stagesLists;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getCompletedDistance() {
        return completedDistance;
    }

    public void setCompletedDistance(Double completedDistance) {
        this.completedDistance = completedDistance;
    }

    public Integer getRunDay() {
        return runDay;
    }

    public void setRunDay(Integer runDay) {
        this.runDay = runDay;
    }

    public Integer getCompletedRunDay() {
        return completedRunDay;
    }

    public void setCompletedRunDay(Integer completedRunDay) {
        this.completedRunDay = completedRunDay;
    }

    public Integer getComplete_degree() {
        return complete_degree;
    }

    public void setComplete_degree(Integer complete_degree) {
        this.complete_degree = complete_degree;
    }

    public Integer getPlan_state() {
        return plan_state;
    }

    public void setPlan_state(Integer plan_state) {
        this.plan_state = plan_state;
    }

    public Integer getExecuteStage() {
        return executeStage;
    }

    public void setExecuteStage(Integer executeStage) {
        this.executeStage = executeStage;
    }

    public Integer getExecuteNo() {
        return executeNo;
    }

    public void setExecuteNo(Integer executeNo) {
        this.executeNo = executeNo;
    }
}
