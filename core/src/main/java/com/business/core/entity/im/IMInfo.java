package com.business.core.entity.im;

import com.business.core.entity.IncIdEntity;

import java.util.Map;

/**
 * Created by edward on 2016/10/17.
 *
 * 用户、群组 环信帐号相关信息
 */
public abstract class IMInfo extends IncIdEntity<Long>{

    /**
     * 用户
     */
    public static final Integer USER_TYPE = 1;
    /**
     * 群组
     */
    public static final Integer GROUP_TYPE = 2;
    /**
     * 俱乐部
     */
    public static final Integer CLUB_TYPE = 3;

    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 状态 1、正常、2、删除、3、封禁
     */
    private Integer state;
    /**
     * 1、用户
     * 2、聊天室
     * 3、俱乐部
     */
    private Integer type;
    /**
     * 其他信息
     */
    private Map<String, Object> createInfo;

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Map<String, Object> getCreateInfo() {
        return createInfo;
    }

    public void setCreateInfo(Map<String, Object> createInfo) {
        this.createInfo = createInfo;
    }
}
