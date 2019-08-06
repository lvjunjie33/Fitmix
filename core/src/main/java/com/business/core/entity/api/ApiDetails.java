package com.business.core.entity.api;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by zhangtao on 2016/4/26.
 */
@Document(collection = "ApiDetails")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class ApiDetails extends IncIdEntity<Integer> {

    /**
     * API id {@link com.business.core.entity.api.Api#id}
     */
    private Integer apiId;

    /**
     * 名称
     */
    private String name;
    /**
     * 接口地址
     */
    private String url;
    /**
     * 字段列表
     */
    private List<Fields> fieldsList;
    /**
     * 描述
     */
    private String desc;
    /**
     * 返回结果
     */
    private String result;
    /**
     * 错误代码
     */
    private List<ErrerCode> errerCodeList;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Long addTime;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Fields> getFieldsList() {
        return fieldsList;
    }

    public void setFieldsList(List<Fields> fieldsList) {
        this.fieldsList = fieldsList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ErrerCode> getErrerCodeList() {
        return errerCodeList;
    }

    public void setErrerCodeList(List<ErrerCode> errerCodeList) {
        this.errerCodeList = errerCodeList;
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
