package com.business.core.entity.runPlan;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.omg.CORBA.Object;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by weird on 2016/5/16.
 */
@Document(collection = "RunPlanStageRatio")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class RunPlanStageRatio {

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
     *  所属类型
     */
    private Integer type;

/*    *//**
     *  该字段存储内容为该类型的名称
     *//*
    private Object[] jog;

    *//**
     *  舒适跑与比赛速度的比值，若长度不为1，则为范围
     *//*
    private Object[] comfortable;

    *//**
     *  间隔跑与比赛速度的比值，若长度不为1，则为范围
     *//*
    private Object[] interval;

    *//**
     *  渐进加速跑与比赛速度的比值，若长度不为1，则为范围
     *//*
    private Object[] buildUp;

    *//**
     *  快跑与比赛速度的比值，若长度不为1，则为范围
     *//*
    private Object[] fast;

    *//**
     *  定速跑与比赛速度的比值，若长度不为1，则为范围
     *//*
    private Object[] pace;*/

    /**
     *  通过map存储每个跑步类型的相应数据：Map<跑步类型名称(中文),与比赛速度的比值，若长度不为1，则为范围>
     */
    private List<RunStage> runStages;

    /**
     *  该类型的实际路程
     */
    private Double distance;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<RunStage> getRunStages() {
        return runStages;
    }

    public void setRunStages(List<RunStage> runStages) {
        this.runStages = runStages;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
