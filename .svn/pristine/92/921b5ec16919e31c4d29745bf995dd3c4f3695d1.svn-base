package com.business.core.entity.mix;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Sin on 2016/3/7.
 *
 * mix banner （广告 推广）
 */
@Document(collection = "MixBanner")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class MixBanner extends IncIdEntity<Integer> {

    /**
     * banner 类型 - mix 专辑
     */
    public static final int TYPE_MIX_ALBUM = 1;
    /**
     * banner 类型 - url 网页
     */
    public static final int TYPE_URL = 2;
    /**
     * banner 类型 - 单曲
     */
    public static final int TYPE_UNIT_MIX = 3;
    /**
     * banner 类型 - 电台
     */
    public static final int TYPE_RADIO = 4;
    /**
     * banner 类型 - 三方平台
     */
    public static final int TYPE_PLATFORM = 5;

    /**
     * banner 状态 - 发布
     */
    public static final int STATUS_RELEASE = 0;
    /**
     * banner 状态 - 未发布
     */
    public static final int STATUS_NOT_RELEASE = 1;
    /**
     * 平台
     */
    public static final int CHANNEL_SYS = 1;
    /**
     * 万德
     */
    public static final int CHANNEL_WD = 2;

    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String desc;
    /**
     * 背景图片
     */
    private String backImage;
    /**
     * 排序（置顶）
     */
    private Integer sort;
    /**
     * banner 类型：1、mix 专辑 2、url 网页 3、单曲 4、电台
     */
    private Integer type;

    ///
    /// banner 类型 ：1、mix 专辑

    /**
     * 专辑编号 {@link MixAlbum#id}
     */
    private String typeValue;
    /**
     * 渠道
     * 1、平台
     * 2、万德
     */
    private Integer channel;


    /**
     * 启动地址
     */
    private String iosSchemesUrl;

    private String androidSchemesUrl;

    ///
    /// 其他信息

    /**
     * banner 状态：0、发布 1、未发布
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Long addTime;
    ///
    /// 非存储字段

    @Transient
    private Mix mix;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Mix getMix() {
        return mix;
    }

    public void setMix(Mix mix) {
        this.mix = mix;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getAndroidSchemesUrl() {
        return androidSchemesUrl;
    }

    public void setAndroidSchemesUrl(String androidSchemesUrl) {
        this.androidSchemesUrl = androidSchemesUrl;
    }

    public String getIosSchemesUrl() {
        return iosSchemesUrl;
    }

    public void setIosSchemesUrl(String iosSchemesUrl) {
        this.iosSchemesUrl = iosSchemesUrl;
    }
}
