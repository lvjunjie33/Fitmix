package com.business.core.entity.bbs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/9/12.
 */
@Document(collection = "Category")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Category extends IncIdEntity<Integer> {

    /**
     * 类别名称
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
