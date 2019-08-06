package com.business.core.entity.runPlan;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by weird on 2016/6/1.
 */
@Document(collection = "RunPlanClassify")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class RunPlanClassify extends IncIdEntity<Integer> {

    /**
     * 类型：5km
     */
    public static final Integer TYPE_5KM = 0;

    /**
     * 类型：10km
     */
    public static final Integer TYPE_10KM = 1;

    /**
     * 类型：半程马拉松
     */
    public static final Integer TYPE_HALFMARATHON = 2;

    /**
     * 类型：马拉松
     */
    public static final Integer TYPE_MARATHON = 3;

    /**
     * 能完成项目：少于20分钟跑步
     */
    public static final Integer ABILITY_LESS20SECOND = 0;

    /**
     * 能完成项目：20-30分钟跑步
     */
    public static final Integer ABILITY_20TO30SECOND = 1;

    /**
     * 能完成项目： 30-60分钟跑步
     */
    public static final Integer ABILITY_30TO60SECONDE = 2;

    /**
     * 能完成项目：5公里跑步
     */
    public static final Integer ABILITY_5KM = 3;

    /**
     * 能完成项目：10公里跑步
     */
    public static final Integer ABILITY_10KM = 4;

    /**
     * 能完成项目：半程马拉松跑步
     */
    public static final Integer ABILITY_HALFMARATHON = 5;

    /**
     * 能完成项目：全程马拉松跑步
     */
    public static final Integer ABILITY_MARATHON = 6;

    /**
     * 所属类型
     */
    private Integer type;

    /**
     * 该类型下，指定类别的最小速度
     */
    private Integer speed_min;

    /**
     * 该类型下，指定类别的最大速度
     */
    private Integer speed_max;

    /**
     * 目前能跑的类型
     */
    private Integer ability;

    /**
     * 类别
     */
    private Integer classify;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSpeed_min() {
        return speed_min;
    }

    public void setSpeed_min(Integer speed_min) {
        this.speed_min = speed_min;
    }

    public Integer getSpeed_max() {
        return speed_max;
    }

    public void setSpeed_max(Integer speed_max) {
        this.speed_max = speed_max;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }
}
