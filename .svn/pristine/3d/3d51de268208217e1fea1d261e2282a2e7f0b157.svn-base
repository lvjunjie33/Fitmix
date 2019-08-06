package com.business.core.entity.channel;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zhangtao on 2017/3/29.
 * 燃脂战场页面 分享统计
 */
@Document(collection = "BurnFatFighting")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class BurnFatFighting extends IncIdEntity<Long> {

    public static final Integer TYPE_EASY = 1;

    public static final Integer TYPE_MIDDLE = 2;

    public static final Integer TYPE_HARD = 3;

    /**
     * 用户IP
     */
    private String ip;
    /**
     * 分享值
     *  0: 默认值
     *  1：从燃脂战场归来的XXX  期待更难的挑战
     *  2：从燃脂战场归来的XXX  感到爽歪歪，血脉喷张
     *  3：从燃脂战场归来的XXX  捡回一条命，真是死里逃生！
     */
    private Integer type;
    /**
     * 随机数
     */
    private String random;
    /**
     * 分享次数
     */
    private Integer shareCount;
    /**
     * 添加时间
     */
    private Long addTime;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }
}
