package com.business.core.entity.mix;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Sin on 2016/3/8.
 *
 * mix album （专辑）
 */
@Document(collection = "MixAlbum")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class MixAlbum extends IncIdEntity<Integer> {

    /**
     * banner 状态 - 发布
     */
    public static final int STATUS_RELEASE = 0;
    /**
     * banner 状态 - 没有发布
     */
    public static final int STATUS_NOT_RELEASE = 1;
    /**
     * 乐享动官方创建
     */
    public static final Integer TYPE_FITMIX_CREATE = 1;
    /**
     * 用户创建
     */
    public static final Integer TYPE_USER_CREATE = 2;

    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String desc;
    /**
     * 背景图片
     */
    private String backImage;
    /**
     * 排序（置顶）
     */
    private Integer sort;
    /**
     * 专辑编号
     */
    private List<Integer> mixIds;
    /**
     * banner 状态：0、发布 1、未发布
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 专辑类型
     */
    private Integer type;
    /**
     * 审核状态
     */
    private Integer checkStatus;
    /**
     * 审核信息
     */
    private String checkMessage;
    /**
     * 专辑拥有者
     */
    private Integer uid;

    ///
    /// 非存储字段

    @Transient
    private List<Mix> mixList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Integer> getMixIds() {
        return mixIds;
    }

    public void setMixIds(List<Integer> mixIds) {
        this.mixIds = mixIds;
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

    public List<Mix> getMixList() {
        return mixList;
    }

    public void setMixList(List<Mix> mixList) {
        this.mixList = mixList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCheckMessage() {
        return checkMessage;
    }

    public void setCheckMessage(String checkMessage) {
        this.checkMessage = checkMessage;
    }
}
