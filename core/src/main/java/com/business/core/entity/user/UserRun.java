package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Created by sin on 15/4/13.
 *
 * 用户运动数据
 *
 * 备注：运动记录以运动开始时间为准
 */
@Document(collection = "UserRum")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserRun extends IncIdEntity<Long> {
    /**
     * state 无效
     */
    public static final Integer STATE_INVALID = 0;
    /**
     * state 有效
     */
    public static final Integer STATE_EFFECTIVE = 1;
    /**
     * 未被统计
     */
    public static final Integer STAT_FALSE = 0;
    /**
     * 统计过了
     */
    public static final Integer STAT_TRUE = 1;
    /**
     * 用户跑步定位类型：GPRS 定位
     */
    public static final Integer LOCATION_TYPE_GPRS = 1;
    /**
     * 用户跑步定位类型：流量（wifi） 定位
     */
    public static final Integer LOCATION_TYPE_WIFI = 2;
    /**
     *  跑步模式 ： 室外
     */
    public static final Integer MODAL_TYPE_INDOOR = 1;
    /**
     * 跑步模式 ： 室内.
     */
    public static final Integer MODAL_TYPE_OUTDOOR = 2;

    ///
    ///运动类型

    /**
     * 位置情况下：0
     */
    public static final Integer TYPE_UNKNOWN = 0;
    /**
     * 跑步
     * <strong>
     *     老版本 0 位置，也记为 跑步
     * </strong>
     */
    public static final Integer TYPE_RUN = 1;
    /**
     * 跳绳
     */
    public static final Integer TYPE_SKIP_ROPE = 2;
    /**
     * 手表运动
     */
    public static final Integer TYPE_WATCH_SPORT = 3;

    ///
    ///=====================================基本数据=======================================================
    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    @Indexed
    private Integer uid;
    /**
     * 运动类型(0,1 跑步、2跳绳之类的)
     */
    @Indexed
    private Integer type;
    /**
     * 运动时间 (秒)
     */
    private Long runTime;
    /**
     * 运动开始时间
     */
    @Indexed
    private Long startTime;
    /**
     * 运动结束时间
     */
    private Long endTime;
    /**
     * 状态 0 无效 1 有效
     */
    private Integer state;
    /**
     * 添加时间，上传时间
     */
    private Long addTime;
    /**
     * 设备类型
     * 1、IOS，2、安卓
     */
    private Integer deviceType;


    ///
    ///============================================跑步数据===================================================

    /**
     * 运动(m)
     */
    private Long distance;
    /**
     * 步(多少步)
     */
    private Long step;
    /**
     * 卡路里
     */
    private Long calorie;
    /**
     * 燃脂量(运动消耗的脂肪)
     */
    private Double consumeFat;
    /**
     * 起始 经度
     */
    private Double startLng;
    /*
     * 起始 纬度
     */
    private Double startLat;
    /**
     * 结束 经度
     */
    private Double endLng;
    /*
     * 结束 纬度
     */
    private Double endLat;
    /**
     * 运动相关文件
     */
    private String zipUrl;
    /**
     * 运动详细（存的是本地文件，会上传文件服务器）
     */
    private String detail ;
    /**
     *  步数详细
     */
    private String stepDetail;
    /**
     * mix BPM值 {@link com.business.core.entity.mix.Mix#bpm} （平均频率）步频
     */
    private Integer bpm;

    /**
     * 运动更新时间 （暂时只有 用户删除历史运动记录时使用）
     */
    private Long updateTime;
    /**
     * 定位类型，（1、GPRS， 2、流量）
     */
    private Integer locationType;
    /**
     * 跑步模式，1、室外 2、室内
     */
    private Integer model;
    /**
     * bpm 匹
     * 度，（计算后的 匹配度，因为会计算 大于 100% 的用户， 如 ： 130% = 130 % 100 bomMatch = 30% ） （跑步完后，计算的 匹配值）
     */
    private Double bpmMatch;
    /**
     * bpm 匹配度，(用户实际的匹配度) （跑步完后，计算的 匹配值）
     */
    private Double userBpmMatch;
    /**
     * 用户打分， 超越多少用户
     */
    @Deprecated
    private Double mark;


    ///
    ///============================================================运动跳绳=======================================
	
    /**
     *  跳绳详细（存的是本地文件，会上传文件服务器）
     */
    private String skipDetail;
    /**
     * 跳跃总个数
     */
    private Long skipNum;

    ///
    ///心率带 --- 心率数据
    /**
     * 用户运动心率数
     */
    private UserHeartRate heartRate;

    private Double elevation;
    private Integer isNew;
    /**
     * 已经统计
     * 1、已经统计 {@link UserRun#STAT_TRUE}
     * 0、未被统计 {@link UserRun#STAT_FALSE}
     */
    private Integer isStat;

    ///
    ///=====================================手表运动的数据=======================================================
    /**
     * 手表相关的运动数据
     */
    private Map<String, Object> watch;
    /**
     * 手表数据文件
     */
    private String watchZipFile;

    /**
     * 手表数据源文件（二进制文件）
     */
    private String watchSourceZipFile;

    ///
    /// 非存储参数

    @Transient
    private User user;
    //2018-07-31 用户运动数据头像字段（官网专用，无其他用途）
    private String gw_avatar;
    private String gw_name;

    public void setGw_name(String gw_name) {
        this.gw_name = gw_name;
    }

    public String getGw_name() {

        return gw_name;
    }

    public String getGw_avatar() {
        return gw_avatar;
    }

    public void setGw_avatar(String gw_avatar) {

        this.gw_avatar = gw_avatar;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
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

    public Double getStartLng() {
        return startLng;
    }

    public void setStartLng(Double startLng) {
        this.startLng = startLng;
    }

    public Double getStartLat() {
        return startLat;
    }

    public void setStartLat(Double startLat) {
        this.startLat = startLat;
    }

    public Double getEndLng() {
        return endLng;
    }

    public void setEndLng(Double endLng) {
        this.endLng = endLng;
    }

    public Double getEndLat() {
        return endLat;
    }

    public void setEndLat(Double endLat) {
        this.endLat = endLat;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStepDetail() {
        return stepDetail;
    }

    public void setStepDetail(String stepDetail) {
        this.stepDetail = stepDetail;
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

    public Integer getLocationType() {
        return locationType;
    }

    public void setLocationType(Integer locationType) {
        this.locationType = locationType;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Double getBpmMatch() {
        return bpmMatch;
    }

    public void setBpmMatch(Double bpmMatch) {
        this.bpmMatch = bpmMatch;
    }

    public Double getUserBpmMatch() {
        return userBpmMatch;
    }

    public void setUserBpmMatch(Double userBpmMatch) {
        this.userBpmMatch = userBpmMatch;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSkipDetail() {
        return skipDetail;
    }

    public void setSkipDetail(String skipDetail) {
        this.skipDetail = skipDetail;
    }

    public Long getSkipNum() {
        return skipNum;
    }

    public void setSkipNum(Long skipNum) {
        this.skipNum = skipNum;
    }

    public UserHeartRate getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(UserHeartRate heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Double getConsumeFat() {
        return consumeFat;
    }

    public void setConsumeFat(Double consumeFat) {
        this.consumeFat = consumeFat;
    }

    public String getZipUrl() {
        return zipUrl;
    }

    public void setZipUrl(String zipUrl) {
        this.zipUrl = zipUrl;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsStat() {
        return isStat;
    }

    public void setIsStat(Integer isStat) {
        this.isStat = isStat;
    }

    public Map<String, Object> getWatch() {
        return watch;
    }

    public void setWatch(Map<String, Object> watch) {
        this.watch = watch;
    }

    public String getWatchZipFile() {
        return watchZipFile;
    }

    public void setWatchZipFile(String watchZipFile) {
        this.watchZipFile = watchZipFile;
    }

    public void setWatchSourceZipFile(String watchSourceZipFile) {
        this.watchSourceZipFile = watchSourceZipFile;
    }

    public String getWatchSourceZipFile() {

        return watchSourceZipFile;
    }
}
