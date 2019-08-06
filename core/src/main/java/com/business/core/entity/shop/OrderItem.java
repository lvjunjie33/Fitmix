package com.business.core.entity.shop;

public class OrderItem {

    private Integer id;

    private Integer orderId;

    private Integer productId;

    private Integer quantity;

    private Integer coin;

    private Long addTime;

    private Long modifyTime;

    public OrderItem(Integer orderId, Integer productId, Integer quantity, Integer coin, Long addTime, Long modifyTime) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.coin = coin;
        this.addTime = addTime;
        this.modifyTime = modifyTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
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
}