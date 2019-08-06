package com.business.core.entity.runPlan;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by weird on 2016/5/11.
 */
@Document(collection = "RunPlanSpeed")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class RunPlanSpeed extends IncIdEntity<Integer> {

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
     *  类别：少于20分钟
     */
    public static final Integer CLASSIFY_LESS20MIN = 0;

    /**
     *  类别：20-30分钟
     */
    public static final Integer CLASSIFY_20TO30MIN = 1;

    /**
     *  类别：30-60分钟
     */
    public static final Integer CLASSIFY_30TO60MIN = 2;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 类别
     */
    private Integer classify;

    /**
     * 所属年龄段
     */
    private Integer ages;

    /**
     * 速度
     */
    private String speed;

    /**
     * 性别
     */
    private Integer gender;

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

    public Integer getAges() {
        return ages;
    }

    public void setAges(Integer ages) {
        this.ages = ages;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}

