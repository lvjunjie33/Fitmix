package com.business.core.entity.runPlan;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


/**
 * Created by weird on 2016/6/16.
 */
@Document(collection = "RunPlanTemplate")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class RunPlanTemplate extends IncIdEntity<Integer> {

    /**
     *  类型：5km
     */
    public static final Integer TYPE_5KM = 0;

    /**
     *  类型：10km
     */
    public static final Integer TYPE_10KM = 1;

    /**
     *  类型：半程马拉松
     */
    public static final Integer TYPE_HALFMARATHON = 2;

    /**
     *  类型：马拉松
     */
    public static final Integer TYPE_MARATHON = 3;

    /**
     *  开始时间：周一
     */
    public static final Integer BEGINTIME_MONDAY = 0;

    /**
     *  开始时间：周二
     */
    public static final Integer BEGINTIME_TUESDAY = 1;

    /**
     *  开始时间：周三
     */
    public static final Integer BEGINTIME_WEDNESDAY = 2;

    /**
     *  开始时间： 周四
     */
    public static final Integer BEGINTIME_THURSDAY = 3;

    /**
     *  开始时间：周五
     */
    public static final Integer BEGINTIME_FRIDAY = 4;

    /**
     *  开始时间：周六
     */
    public static final Integer BEGINTIME_SATURDAY = 5;

    /**
     *  开始时间：周日
     */
    public static final Integer BEGINTIME_SUNDAY= 6;

    /**
     *  类型
     */
    private Integer type;

    /**
     *  开始时间
     */
    private Integer beginTime;

    /**
     *  类型下的类别
     */
    private Integer classify;

    /**
     *  该类型下的所有阶段内容
     */
    private List<Stages> stages;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public List<Stages> getStages() {
        return stages;
    }

    public void setStages(List<Stages> stages) {
        this.stages = stages;
    }
}
