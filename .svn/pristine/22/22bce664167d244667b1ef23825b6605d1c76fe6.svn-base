package com.business.core.entity.user.watch;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 *
 * 运动手表相关数据
 *
 * Created by edward on 2017/4/5.
 */
@Document(collection = "UserSportsWatch")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserSportsWatch extends IncIdEntity<Long> {

    public static Integer SPORTS_TYPE_RUN_OUTDOOR = 1;  //室外跑
    public static Integer SPORTS_TYPE_RUN_INDOOR = 2;  //室内跑
    public static Integer SPORTS_TYPE_WALK = 3;  //徒步
    public static Integer SPORTS_TYPE_CROSSCOUNTRY = 4;  //越野
    public static Integer SPORTS_TYPE_RIDE = 5;  //骑行
    public static Integer SPORTS_TYPE_SWIM_INDOOR = 6;  //室内游泳
    public static Integer SPORTS_TYPE_SWIM_OUTDOOR = 7; //室外游泳
    public static Integer SPORTS_TYPE_ROCK_CLIMB = 8; //攀岩
    public static Integer SPORTS_TYPE_MOUNTAINS_CLIMB = 9;  //爬山
    public static Integer SPORTS_TYPE_SKI = 10; //滑雪
    public static Integer SPORTS_TYPE_FISH = 11;  //钓鱼
    public static Integer SPORTS_TYPE_JUMP = 12;  //跳伞
    public static Integer SPORTS_TYPE_ROPE = 13;  //跳绳
    public static Integer SPORTS_TYPE_DIVE = 14;  //潜水
    public static Integer SPORTS_TYPE_ROW = 15;  //划船*/

    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 运动类型
     */
    private Integer type;
    /**
     * 运动开始时间
     */
    private Long sportBTime;
    /**
     * 运动结束时间
     */
    private Long sportETime;
    /**
     * 运动时长
     */
    private Long sportSumTime;
    /**
     * 运动数据文件
     */
    private String sportFile;
    /**
     * 各类运动 明细数据
     */
    private Map<String, Object> sportDetails;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 数据状态
     */
    private Integer status;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getSportSumTime() {
        return sportSumTime;
    }

    public void setSportSumTime(Long sportSumTime) {
        this.sportSumTime = sportSumTime;
    }

    public String getSportFile() {
        return sportFile;
    }

    public void setSportFile(String sportFile) {
        this.sportFile = sportFile;
    }

    public Map<String, Object> getSportDetails() {
        return sportDetails;
    }

    public void setSportDetails(Map<String, Object> sportDetails) {
        this.sportDetails = sportDetails;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSportBTime() {
        return sportBTime;
    }

    public void setSportBTime(Long sportBTime) {
        this.sportBTime = sportBTime;
    }

    public Long getSportETime() {
        return sportETime;
    }

    public void setSportETime(Long sportETime) {
        this.sportETime = sportETime;
    }
}
