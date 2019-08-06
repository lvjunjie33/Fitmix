package com.business.core.entity.auth;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * 资源权限模型实体
 * User: jeff
 * Time: 2012-11-15 11:36
 */
@Document(collection = "Resource")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Resource extends IncIdEntity<Integer> {
    /**
     * 父级资源编号 - 根资源
     */
    public static final Integer TYPE_ROOT = 0;
    /**
     * 资源类型 - 菜单
     */
    public static final Integer TYPE_MENU = 1;
    /**
     * 资源类型 - 操作
     */
    public static final Integer TYPE_OPERATION = 2;

    /**
     * 资源名字
     */
    private String name;
    /**
     * 资源类型
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 展示名
     */
    private String extensionName;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 父级资源编号(外键：{@link Resource#id})
     */
    private Integer pid;
    /**
     * 操作
     */
    private String handling;
    /**
     * 权限 ： {@link com.business.core.entity.auth.Role#name}
     */
    private List<String> roles;


    ///
    /// 非存储字段

    /**
     * 子资源
     */
    @Transient
    private List<Resource> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getExtensionName() {
        return extensionName;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getHandling() {
        return handling;
    }

    public void setHandling(String handling) {
        this.handling = handling;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }
}
