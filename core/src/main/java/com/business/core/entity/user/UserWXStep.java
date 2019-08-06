package com.business.core.entity.user;

/**
 * Created by edward on 2016/8/31.
 *
 * 微信步数设置记录
 */
public class UserWXStep {

    /**
     * 微信设置步数时的的unionid
     */
    private String unionid;
    /**
     * 微信授权登录的openId
     */
    private String openid;
    /**
     * 设置的步数
     */
    private Integer step;
    /**
     * 执行时间
     */
    private Long runTime;

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }
}
