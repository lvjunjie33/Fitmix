package com.business.core.entity.shop;

public class Product {


    /**
     * status/正常
     */
    public static final Integer STATUS_NORMAL = 0;
    /**
     * status/无效
     */
    public static final Integer STATUS_INVALID = 1;

    /**
     * 产品类型
     */
    public static final Integer TYPE_LIUMI = 0;

    private Integer id;

    private String name;

    private Integer coin;

    private Integer type;

    private Integer quantity;

    private Integer status;

    private String virtualKey;

    private Integer sort;

    private Long addTime;

    private Long modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVirtualKey() {
        return virtualKey;
    }

    public void setVirtualKey(String virtualKey) {
        this.virtualKey = virtualKey == null ? null : virtualKey.trim();
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Product(String name, Integer coin, Integer type, Integer quantity, Integer status, String virtualKey, Long addTime, Long modifyTime) {
        this.name = name;
        this.coin = coin;
        this.type = type;
        this.quantity = quantity;
        this.status = status;
        this.virtualKey = virtualKey;
        this.addTime = addTime;
        this.modifyTime = modifyTime;
    }

    public Product() {
    }
}