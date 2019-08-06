package com.business.core.entity.stat;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.address.Position;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by edward on 2017/7/26.
 */
@Document(collection = "MapRunSearchStat")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class MapRunSearchStat extends IncIdEntity<Long> {

    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户运动编号
     */
    private Long userRunId;
    /**
     * 运动距离
     */
    private Long distance;
    /**
     * 运动步数
     */
    private Long step;
    /**
     * 运动消耗的卡路里
     */
    private Long calorie;
    /**
     * bpm值
     */
    private Integer bpm;
    /**
     * 起始坐标
     */
    private Position outset;
    /**
     * 终点
     */
    private Position finish;
    /**
     * 运动轨迹
     */
    private List<Position> tracks;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }

    public Long getCalorie() {
        return calorie;
    }

    public void setCalorie(Long calorie) {
        this.calorie = calorie;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public List<Position> getTracks() {
        return tracks;
    }

    public void setTracks(List<Position> tracks) {
        this.tracks = tracks;
    }

    public Long getUserRunId() {
        return userRunId;
    }

    public void setUserRunId(Long userRunId) {
        this.userRunId = userRunId;
    }

    public Position getOutset() {
        return outset;
    }

    public void setOutset(Position outset) {
        this.outset = outset;
    }

    public Position getFinish() {
        return finish;
    }

    public void setFinish(Position finish) {
        this.finish = finish;
    }
}