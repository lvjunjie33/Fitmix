package com.business.core.entity.auth;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 描述: 用户角色
 * User: jeff
 * Time: 2012-11-14 14:51
 */
@Document(collection = "Role")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Role extends IncIdEntity<Integer> {

    /**
     * 超级管理员 - 系统管理员
     */
    public static final String ROLE_ROOT = "root";
    /**
     * 系统管理员 - 系统管理员
     */
    public static final String ROLE_ADMIN = "admin";

    /**
     * 角色标识
     */
    private String name;
    /**
     * 角色展示名
     */
    private String extensionName;
    /**
     * 描述
     */
    private String des;
    /**
     * 加添时间
     */
    private Date addTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtensionName() {
        return extensionName;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
