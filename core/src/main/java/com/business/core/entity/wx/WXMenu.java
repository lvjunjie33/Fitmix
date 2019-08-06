package com.business.core.entity.wx;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by sin on 2015/10/20.
 */
@Document(collection = "WXMenu")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class WXMenu extends IncIdEntity<Integer> {

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 父级菜单
     */
    private Integer parent;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 类型：click view
     */
    private String type;
    /**
     * key click 独有
     */
    private String key;

    ///
    /// 非存储字段

    /**
     * 子菜单
     */
    @Transient
    private List<WXMenu> childMenu;
    /**
     * 子菜单
     */
    @Transient
    private List<WXMenu> sub_button;


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

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<WXMenu> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<WXMenu> childMenu) {
        this.childMenu = childMenu;
    }

    public List<WXMenu> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<WXMenu> sub_button) {
        this.sub_button = sub_button;
    }
}
