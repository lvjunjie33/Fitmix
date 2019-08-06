package com.business.core.entity.runPlan;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by weird on 2016/8/3.
 */
@Document(collection = "RunPlanDescription")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class RunPlanDescription extends IncIdEntity<Integer> {

    /**
     *  首页
     */
    public static Integer POSITION_PRESENTATION = 0;

    /**
     *  浏览页面
     */
    public static Integer POSITION_PREVIEW = 1;

    /**
     *  用户计划页面
     */
    public static Integer POSITION_USERPLAN = 2;

    private String key;

    private Integer position;

    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
