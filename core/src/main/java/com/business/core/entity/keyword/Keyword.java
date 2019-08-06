package com.business.core.entity.keyword;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/5/17.
 * 关键字信息
 */
@Document(collection = "Keyword")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Keyword  extends IncIdEntity<Long> {

    /**
     * 未上架
     */
    public static final Integer STATE_1 = 1;
    /**
     * 上架
     */
    public static final Integer STATE_2 = 2;

    /**
     * 关键词
     */
    private String name;
    /**
     * 状态 1、下架 2、上架
     */
    private Integer state;
    /**
     * 类型 1、搜索栏 2、关键字
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 添加时间
     */
    private Long addTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
