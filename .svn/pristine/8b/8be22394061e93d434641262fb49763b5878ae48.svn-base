package com.business.core.entity.runPlan;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by weird on 2016/6/17.
 */
@Document(collection = "StageAssortment")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class StageAssortment extends IncIdEntity<Integer>{

    /**
     *  阶段类型
     */
    private Integer stageType;

    /**
     *  阶段名
     */
    private String stageName;

    /**
     *  跑步类型
     */
    private Integer type;

    /**
     *  跑步类型名称
     */
    private String typeName;

    /**
     *  排序方式
     */
    private Integer sort;

    public Integer getStageType() {
        return stageType;
    }

    public void setStageType(Integer stageType) {
        this.stageType = stageType;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
