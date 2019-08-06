package com.business.core.entity.auth;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 后台管理员实体
 * User: sin
 * Date: 13-8-1
 * Time: 下午4:16
 */
@Document(collection = "Admin")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Admin extends IncIdEntity<Integer> implements Serializable {

    /**
     * 是否激活 - 激活
     */
    public static final Integer ACTIVATE_1 = 1;
    /**
     * 是否激活 - 否
     */
    public static final Integer ACTIVATE_2 = 2;

    /**
     * 登录帐号
     */
    private String loginName;
    /**
     * 名字
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否激活
     */
    private Integer activate;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 登录次数
     */
    private Integer loginCount;
    /**
     * 角色编号
     */
    private List<String> roles;


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActivate() {
        return activate;
    }

    public void setActivate(Integer activate) {
        this.activate = activate;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
