package com.business.core.entity.surprise;

import com.alibaba.fastjson.JSONObject;
import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.PopUp;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/5/19.
 *
 * 彩蛋信息
 *
 */
@Document(collection = "Surprise")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Surprise extends IncIdEntity<Long> {

    /**
     * 默认类型
     */
    public static final Integer TYPE_DEFAULT = 0;
    /**
     * 静态图片
     */
    public static final Integer TYPE_PICTURE = 1;
    /**
     * 文字
     */
    public static final Integer TYPE_TEXT = 2;
    /**
     * 天气
     */
    public static final Integer TYPE_WEATHER = 3;

    /**
     * 未上架
     */
    public static final Integer STATE_1 = 1;
    /**
     * 上架
     */
    public static final Integer STATE_2 = 2;

    /**
     * 名称
     */
    private String name;
    /**
     * 类型 0:默认 1：静态图片 2：文字 3：天气
     */
    private Integer type;
    /**
     * 上架状态 1.未上架 2.已上架
     */
    private Integer state;
    /**
     * 图片链接
     */
    private String imgUrl;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 添加时间
     */
    private Long addTime;

    /**
     * 天气预报信息
     */
    @Transient
    private PopUp popUp;

    @Transient
    private JSONObject baiduWeather;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public PopUp getPopUp() {
        return popUp;
    }

    public void setPopUp(PopUp popUp) {
        this.popUp = popUp;
    }

    public JSONObject getBaiduWeather() {
        return baiduWeather;
    }

    public void setBaiduWeather(JSONObject baiduWeather) {
        this.baiduWeather = baiduWeather;
    }
}
