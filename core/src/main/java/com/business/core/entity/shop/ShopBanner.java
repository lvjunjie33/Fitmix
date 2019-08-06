package com.business.core.entity.shop;

/**
 * Created by zhangtao on 2016/11/25.
 */
public class ShopBanner {

    /**
     * status/正常
     */
    public static final Integer STATUS_NORMAL = 0;
    /**
     * status/无效
     */
    public static final Integer STATUS_INVALID = 1;
    /**
     * banner 类型 - mix 专辑
     */
    public static final int TYPE_MIX_ALBUM = 1;
    /**
     * banner 类型 - url 网页
     */
    public static final int TYPE_URL = 2;
    /**
     * banner 类型 - 单曲
     */
    public static final int TYPE_UNIT_MIX = 3;
    /**
     * banner 类型 - 电台
     */
    public static final int TYPE_RADIO = 4;

    /**
     * banner Id
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 背景图
     */
    private String backImage;
    /**
     * banner 类型：1、mix 专辑 2、url 网页 3、单曲 4、电台
     */
    private Integer type;
    /**
     * 类型对应的值
     */
    private String typeValue;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 修改时间
     */
    private Long modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue == null ? null : typeValue.trim();
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
