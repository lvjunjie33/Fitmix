package com.business.core.entity.logs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Created by edward on 2017/9/1.
 */
@Document(collection = "SnapTable")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class SnapTable extends IncIdEntity<Long> {

    /**
     * 用户运动城市分布 统计的计划
     */
    public static final Integer TYPE_USER_RUN_PLAN = 1;
    /**
     * 用户运动城市统计
     */
    public static final Integer TYPE_USER_RUN_CITY_STAT = 2;
    /**
     * 重复的用户
     */
    public static final Integer TYPE_REPEAT_USER = 3;

    /**
     * 数据类型
     */
    private Integer type;

    //添加时间
    private Long addTime;

    /**
     * 存储的值
     */
    private Map<String, Object> values;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }
}
