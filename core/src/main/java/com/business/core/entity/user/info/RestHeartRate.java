package com.business.core.entity.user.info;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2016/9/5.
 *
 * 用户静息心率
 */
@Document(collection = "RestHeartRate")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class RestHeartRate extends IncIdEntity<Long> {

    public static final String[] fields = {"uid", "heartRateVal", "detectTime"};

    /**
     * 全部
     */
    public static final Integer IS_ALL_YES = 1;

    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 静息心率值
     */
    private Integer heartRateVal;
    /**
     * 检测时间
     */
    private Long detectTime;
    /**
     * 最后一次更新时间
     */
    private Long lastUpdateTime;
    /**
     * 删除状态 [ 1 有效、0 删除 ]
     */
    private Integer isRemove;

    public Integer getHeartRateVal() {
        return heartRateVal;
    }

    public void setHeartRateVal(Integer heartRateVal) {
        this.heartRateVal = heartRateVal;
    }

    public Long getDetectTime() {
        return detectTime;
    }

    public void setDetectTime(Long detectTime) {
        this.detectTime = detectTime;
    }

    public Integer getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(Integer isRemove) {
        this.isRemove = isRemove;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
