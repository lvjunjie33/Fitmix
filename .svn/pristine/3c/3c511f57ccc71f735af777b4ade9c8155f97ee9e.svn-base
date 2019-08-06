package com.business.core.entity.api;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by zhangtao on 2016/4/26.
 */
@Document(collection = "Api")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Api extends IncIdEntity<Integer> {

    /**
     * API 名称
     */
    private String apiName;
    /**
     * API链接
     */
    private String url;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 父ID
     */
    private Integer pid;
    /**
     * 所属模块
     */
    private String modules;
    /**
     * 排序ID
     */
    private Integer sort;

    @Transient
    private List<Api> children;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Api> getChildren() {
        return children;
    }

    public void setChildren(List<Api> children) {
        this.children = children;
    }
}
