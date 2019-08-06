package com.business.core.entity.user.info;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户 跑步 运动记录统计
 *
 * 运动结束之后汇总
 *
 * Created by edward on 2017/10/20.
 */
@Document(collection = "UserRunStat3")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class UserRunStat extends IncIdEntity<Long> {

    //总数据统计
    public static final String[] BASIC_INFO_FIELDS_SUM = new String[] {"uid", "type", "statTime", "sumStep", "sumDistance", "sumCalorie", "runNum", "runDay", "runTime", "sumConsumeFat"};

    //有效统计
    public static final String[] BASIC_INFO_FIELDS_VALID = new String[] {"uid", "type", "statTime", "sumStepValid", "sumDistanceValid",
            "sumCalorieValid", "runNumValid", "runDayValid", "runTimeValid", "level", "pace"};

    //全部
    public static final Integer STAT_SUM = 1;
    //年
    public static final Integer STAT_YEAR = 2;
    //月
    public static final Integer STAT_MONTH = 3;
    //周
    public static final Integer STAT_WEEK = 4;
    //日
    public static final Integer STAT_DAY = 5;


    /**
     * 用户编号
     */
    @Indexed
    private Integer uid;
    /**
     * 类型 (记录最近一次的汇总数据:如今年、本月、本周，此处仅仅用于数据展示)
     *
     * 1、总数据
     * 2、年记录
     * 3、月记录
     * 4、周记录
     * 5、日记录
     */
    @Indexed
    private Integer type;
    /**
     * 记录时间
     * 周: 2017-10-23 (周一)
     * 月: 2017-10-01 (月初)
     * 年: 2017-01-01 (年初)
     */
    @Indexed
    private String statTime;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 总步数
     */
    private Long sumStep;
    /**
     * 总里程
     */
    private Long sumDistance;
    /**
     * 总卡路里(大卡)
     */
    private Long sumCalorie;
    /**
     * 燃脂总量(运动消耗的脂肪总数量)
     */
    private Double sumConsumeFat;
    /**
     * 运动次数
     */
    private Integer runNum;
    /**
     * 运动天数 (一天多次运动只算1天)
     */
    private Integer runDay;
    /**
     * 运动时间(秒)
     */
    private Long runTime;
    /**
     * 修改时间
     */
    private Long modifyTime;

    //===========================================有效运动数据 步频大于120，单次运动里程大于1KM========================================================
    /**
     * 有效总步数
     */
    private Long sumStepValid;
    /**
     * 有效总里程
     */
    private Long sumDistanceValid;
    /**
     * 有效总卡路里
     */
    private Long sumCalorieValid;
    /**
     * 有效运动 次数
     */
    private Integer runNumValid;
    /**
     * 有效运动 天数 (一天多次运动只算1天)
     */
    private Integer runDayValid;
    /**
     * 有效运动 时间(秒)
     */
    private Long runTimeValid;
    /**
     * 有效运动 修改时间
     */
    private Long modifyTimeValid;
    /**
     * 平均配速
     * 1、青铜 (平均配速：10′00″ << X)
     * 2、白银 (平均配速：8′00 << X < 10′00″)
     * 3、黄金 (平均配速：6′30″ << X < 8′00″)
     * 4、铂金 (平均配速：5′30″ << X < 6′30″)
     * 5、钻石 (平均配速：X < 5′30″)
     */
    private Integer level;
    /**
     * 配速8′30″== 830
     * 冗余字段（便于查询）
     */
    @Indexed
    private Integer pace;
    /**
     * 注册时间
     *
     * 冗余字段（便于查询）
     */
    private Long registerTime;

    /**
     * 排行榜位置
     */
    @Transient
    private Long rank;
    /**
     * 用户信息
     */
    @Transient
    private User user;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getSumStep() {
        return sumStep;
    }

    public void setSumStep(Long sumStep) {
        this.sumStep = sumStep;
    }

    public Long getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(Long sumDistance) {
        this.sumDistance = sumDistance;
    }

    public Long getSumCalorie() {
        return sumCalorie;
    }

    public void setSumCalorie(Long sumCalorie) {
        this.sumCalorie = sumCalorie;
    }

    public Integer getRunNum() {
        return runNum;
    }

    public void setRunNum(Integer runNum) {
        this.runNum = runNum;
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

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public String getStatTime() {
        return statTime;
    }

    public void setStatTime(String statTime) {
        this.statTime = statTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getRunDay() {
        return runDay;
    }

    public void setRunDay(Integer runDay) {
        this.runDay = runDay;
    }

    public Long getSumStepValid() {
        return sumStepValid;
    }

    public void setSumStepValid(Long sumStepValid) {
        this.sumStepValid = sumStepValid;
    }

    public Long getSumDistanceValid() {
        return sumDistanceValid;
    }

    public void setSumDistanceValid(Long sumDistanceValid) {
        this.sumDistanceValid = sumDistanceValid;
    }

    public Long getSumCalorieValid() {
        return sumCalorieValid;
    }

    public void setSumCalorieValid(Long sumCalorieValid) {
        this.sumCalorieValid = sumCalorieValid;
    }

    public Integer getRunNumValid() {
        return runNumValid;
    }

    public void setRunNumValid(Integer runNumValid) {
        this.runNumValid = runNumValid;
    }

    public Integer getRunDayValid() {
        return runDayValid;
    }

    public void setRunDayValid(Integer runDayValid) {
        this.runDayValid = runDayValid;
    }

    public Long getRunTimeValid() {
        return runTimeValid;
    }

    public void setRunTimeValid(Long runTimeValid) {
        this.runTimeValid = runTimeValid;
    }


    public Integer getPace() {
        return pace;
    }

    public void setPace(Integer pace) {
        this.pace = pace;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getModifyTimeValid() {
        return modifyTimeValid;
    }

    public void setModifyTimeValid(Long modifyTimeValid) {
        this.modifyTimeValid = modifyTimeValid;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getSumConsumeFat() {
        return sumConsumeFat;
    }

    public void setSumConsumeFat(Double sumConsumeFat) {
        this.sumConsumeFat = sumConsumeFat;
    }
}
