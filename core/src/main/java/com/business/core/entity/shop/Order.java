package com.business.core.entity.shop;

public class Order {

    /**
     * 订单ID
     */
    private Integer id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 金额
     */
    private Integer totalCoin;
    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 状态
     */
    private String status;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 修改时间
     */
    private Long modifyTime;

    public Order(String orderNo, Integer totalCoin, Integer uid, String status, Long addTime, Long modifyTime) {
        this.orderNo = orderNo;
        this.totalCoin = totalCoin;
        this.uid = uid;
        this.status = status;
        this.addTime = addTime;
        this.modifyTime = modifyTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getTotalCoin() {
        return totalCoin;
    }

    public void setTotalCoin(Integer totalCoin) {
        this.totalCoin = totalCoin;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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