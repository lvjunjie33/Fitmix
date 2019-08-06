package com.business.core.entity.user;


import com.google.common.collect.ImmutableMap;
import org.springframework.data.annotation.Transient;

/**
 * Created by fenxio on 2016/11/8.
 */
public class AccountFlow {

    /**
     * type : 赚取
     */
    public static final Integer FLOW_TYPE_GAIN = 0;
    /**
     * type : 消费
     */
    public static final Integer FLOW_TYPE_EXPENSE = 1;

    public static final ImmutableMap<String, Integer> levelStr = ImmutableMap.<String, Integer>builder()
            .put("初入跑坛", 1)
            .put("达到-初级跑者I", 2)
            .put("达到-初级跑者II",3)
            .put("达到-初级跑者III",4)
            .put("达到-中级跑者I",5)
            .put("达到-中级跑者II",6)
            .put("达到-中级跑者III",7)
            .put("达到-高级跑者I",8)
            .put("达到-高级跑者II",9)
            .put("达到-高级跑者III",10)
            .put("达到-顶级跑者I",11)
            .put("达到-顶级跑者II",12)
            .put("达到-顶级跑者III",13)
            .put("达到-神级跑者I",14)
            .put("达到-神级跑者II",15)
            .put("达到-神级跑者III",16)
            .build();


    /**
     * 编号
     */
    private Integer id;
    /**
     * 积分
     */
    private Integer coin;
    /**
     * 用户ID
     */
    private Integer uid;
    /**
     * 描述
     */
    private String description;
    /**
     * 账号编号
     */
    private Integer aid;
    /**
     * 流水类型 0：获取 1：消费
     */
    private Integer flowType;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 修改时间
     */
    private Long modifyTime;

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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountFlow(Integer coin, Integer uid, String description, Integer aid, Integer flowType, Long addTime, Long modifyTime) {
        this.coin = coin;
        this.uid = uid;
        this.description = description;
        this.aid = aid;
        this.flowType = flowType;
        this.addTime = addTime;
        this.modifyTime = modifyTime;
    }

    public AccountFlow() {
    }
}
