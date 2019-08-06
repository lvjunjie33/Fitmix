package com.business.core.entity.user.info;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2017/9/22.
 *
 * 用户设备 活跃信息
 */
@Document(collection = "UserDevice")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class UserDevice{

    /**
     * 活跃
     */
    public static final Integer ACTIVE_TRUE = 1;
    /**
     * 不活跃
     */
    public static final Integer ACTIVE_FALSE = 0;

    /**
     * fitmix
     */
    public static final String PRODUCT_FITMIX = "fitmix";
    /**
     * C罗
     */
    public static final String PRODUCT_ROC = "ROC_RX";

    /**
     * 用户编号
     */
    @Indexed
    private Integer uid;
    /**
     * 活跃情况
     * 1活跃
     * 0不活跃
     */
    private Integer active;
    /**
     * sdk 设备类型
     */
    private String sdk;
    /**
     * 乐享动产品(fitmix)
     * C罗 ROC 睿响(ROC_RX)
     */
    @Indexed
    private String product;
    /**
     * 设备 token (push)
     */
    private String deviceToken;
    /**
     * 设备推送 (push)
     */
    private Integer terminal;
    /**
     * app 当前版本
     */
    private Integer currentVersion;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 修改时间
     */
    private Long modifyTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public Integer getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
