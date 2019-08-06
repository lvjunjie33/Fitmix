package com.business.core.entity.user;

/**
 * Created by edward on 2016/5/23.
 * 用户跳绳
 */
public class UserSkipRope {

    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 运动开始时间
     */
    private Long startTime;
    /**
     * 运动结束时间
     */
    private Long endTime;
    /**
     *  跳绳详细（存的是本地文件，会上传文件服务器）
     */
    private String skipDetail;
    /**
     * 卡路里
     */
    private Long calorie;
    /**
     * 心率带记录的用户心率
     */
    private Double heartRate;

    /**
     * 运动时间 (秒)
     */
    private Long runTime;
    /**
     * 跳跃总个数
     */
    private Long skipNum;

    /**
     * mix BPM值 {@link com.business.core.entity.mix.Mix#bpm} （平均频率）
     */
    private Integer bpm;
    /**
     * bpm 匹
     * 度，（计算后的 匹配度，因为会计算 大于 100% 的用户， 如 ： 130% = 130 % 100 bomMatch = 30% ） （跑步完后，计算的 匹配值）
     */
    private Double bpmMatch;

    /**
     * 运动类型(0跑步、1骑行、2跳绳之类的)
     */
    private Integer type;

    /**
     * 状态 0 无效 1 有效
     * {@link com.business.core.constants.Constants#STATE_INVALID}
     * {@link com.business.core.constants.Constants#STATE_EFFECTIVE}
     */
    private Integer state;
    /**
     * 运动更新时间 （暂时只有 用户删除历史运动记录时使用）
     */
    private Long updateTime;
    /**
     * 添加时间，上传时间
     */
    private Long addTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getCalorie() {
        return calorie;
    }

    public void setCalorie(Long calorie) {
        this.calorie = calorie;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public Long getSkipNum() {
        return skipNum;
    }

    public void setSkipNum(Long skipNum) {
        this.skipNum = skipNum;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getSkipDetail() {
        return skipDetail;
    }

    public void setSkipDetail(String skipDetail) {
        this.skipDetail = skipDetail;
    }

    public Double getBpmMatch() {
        return bpmMatch;
    }

    public void setBpmMatch(Double bpmMatch) {
        this.bpmMatch = bpmMatch;
    }
}
