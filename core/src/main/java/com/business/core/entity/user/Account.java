package com.business.core.entity.user;


import org.springframework.data.annotation.Transient;

/**
 * Created by fenxio on 2016/11/8.
 */
public class Account {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 积分
     */
    private Integer coin;
    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 修改时间
     */
    private Long modifyTime;

    @Transient
    private Long spendCoin;

    @Transient
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public Long getSpendCoin() {
        return spendCoin;
    }

    public void setSpendCoin(Long spendCoin) {
        this.spendCoin = spendCoin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account(Integer coin, Integer uid, Long addTime, Long modifyTime) {
        this.coin = coin;
        this.uid = uid;
        this.addTime = addTime;
        this.modifyTime = modifyTime;
    }

    public Account() {
    }
}
