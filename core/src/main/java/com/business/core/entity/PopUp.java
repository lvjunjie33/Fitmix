package com.business.core.entity;

import com.business.core.entity.user.UserEveryDayRun;
import com.business.core.entity.user.UserRun;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * 弹窗
 * 昨日运动数据+天气（气象、温度、aqi、pm2.5）
 * Created by weird on 2016/2/29.
 */
public class PopUp {

    /**
     * 地区编号
     */
    private Integer areaId;

    /**
     * 地区名 en
     */
    private String areaEn;

    /**
     * 地区名 ch
     */
    private String areaCh;

    /**
     *  pm2.5
     */
    private Integer pm25;

    /**
     *  aqi(空气质量指数)
     *  优、良、轻度污染、中度污染、重度污染、严重污染
     */
    private String aqi;

    /**
     * 白天天气现象
     */
    private String weather1;

    /**
     * 晚上天气现象
     */
    private String weather2;

    /**
     * 白天天气温度(摄氏度)
     */
    private Integer temperature1;

    /**
     * 晚上天气温度(摄氏度)
     */
    private Integer temperature2;

    ///
    /// 非存储字段
    /**
     * 用户昨日跑步相关数据
     */
    @Transient
    private UserEveryDayRun userEveryDayRuns;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaEn() {
        return areaEn;
    }

    public void setAreaEn(String areaEn) {
        this.areaEn = areaEn;
    }

    public String getAreaCh() {
        return areaCh;
    }

    public void setAreaCh(String areaCh) {
        this.areaCh = areaCh;
    }

    public Integer getPm25() {
        return pm25;
    }

    public void setPm25(Integer pm25) {
        this.pm25 = pm25;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getWeather1() {
        return weather1;
    }

    public void setWeather1(String weather1) {
        this.weather1 = weather1;
    }

    public String getWeather2() {
        return weather2;
    }

    public void setWeather2(String weather2) {
        this.weather2 = weather2;
    }

    public Integer getTemperature1() {
        return temperature1;
    }

    public void setTemperature1(Integer temperature1) {
        this.temperature1 = temperature1;
    }

    public Integer getTemperature2() {
        return temperature2;
    }

    public void setTemperature2(Integer temperature2) {
        this.temperature2 = temperature2;
    }

    public UserEveryDayRun getUserEveryDayRun() {
        return userEveryDayRuns;
    }

    public void setUserEveryDayRun(UserEveryDayRun userEveryDayRuns) {
        this.userEveryDayRuns = userEveryDayRuns;
    }
}
