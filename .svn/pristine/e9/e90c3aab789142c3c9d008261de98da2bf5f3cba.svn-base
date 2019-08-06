package com.business.core.entity.runPlan;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by weird on 2016/10/8.
 */
@Document(collection = "RunPlanExtraStage")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class RunPlanExtraStage extends IncIdEntity<Integer> {

    /**
     *  类型
     */
    private Integer type;

    /**
     *  各类型下的类别
     */
    private Integer classify;

    /**
     *  该类型下的所有阶段
     */
    private List<Stages> stages;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
