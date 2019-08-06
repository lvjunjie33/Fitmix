package com.business.core.entity.community.discuss;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2017/5/8.
 * 论坛板块，范畴，聚合，种类，专栏
 *
 * 1、Mix、2、电台、3、赛事、4、运动视界、5、客服、6、公告、7、专业知识、8、自由板块 (开放板块、提供用户自由发表主题贴)
 */
@Document(collection = "Categorys")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Categorys extends IncIdEntity<Integer> {

    //Mix
    public static final Integer TYPE_MIX = 1;
    //电台
    public static final Integer TYPE_RADIO = 2;
    //赛事
    public static final Integer TYPE_ACTIVITY = 3;
    //运动视界
    public static final Integer TYPE_VIDEO = 4;
    //客服
    public static final Integer TYPE_CUSTOMER_SERVICE = 5;
    //公告
    public static final Integer TYPE_BULLETIN = 6;
    //专业知识
    public static final Integer TYPE_PROFESSIONAL_KNOWLEDGE = 7;
    //自由板块
    public static final Integer TYPE_OPEN = 8;

    /**
     * 板块类型
     */
    private Integer type;
    /**
     * 1、开放板块，0、非开放板块
     */
    private Integer isOpen;
    /**
     * 添加时间
     */
    private Long addTime;

    /**
     * 板块标题
     */
    private String title;
    /**
     * 描述
     */
    private String des;
    /**
     * 状态
     * 0、关闭
     * 1、开启
     */
    private Integer status;
    /**
     * 添加人编号 {@link com.business.core.entity.auth.Admin#loginName}
     */
    private String loginName;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
