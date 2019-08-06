package com.business.core.entity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Created by Administrator on 2015/4/29.
 * 字典
 */
@Document(collection = "Dictionary")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Dictionary extends IncIdEntity<Integer> implements Cloneable{

    public static final String[] BASIC_INFO_FIELDS = new String[] {"id", "type", "value", "name", "nameEn", "sort", "appDisplay", "other"};

    /**
     * 类型
     */
    private Integer type;
    /**
     * 类型-->值
     */
    private Integer value;
    /**
     * 名称 (区分语言, China)
     */
    private String name;
    /**
     * 名称 (区分语音, English)
     */
    private String nameEn;
    /**
     * 描述
     */
    private String des;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 由后台管理界面控制是否在app端下发该字典
     * {@link com.business.core.constants.Constants#SWITCH_OPEN}
     *
     * 1、显示
     * 0、不显示
     */
    private Integer appDisplay;

    ///
    /// other 其它信息 扩展  (image)

    private Map<String, Object> other;

    @Override
    public Dictionary clone() throws CloneNotSupportedException {
        return (Dictionary) super.clone();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Map<String, Object> getOther() {
        return other;
    }

    public void setOther(Map<String, Object> other) {
        this.other = other;
    }

    public Integer getAppDisplay() {
        return appDisplay;
    }

    public void setAppDisplay(Integer appDisplay) {
        this.appDisplay = appDisplay;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
