package com.business.core.entity.file;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

/**
 * Created by edward on 2016/8/17.
 */
@Document(collection = "File")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class File extends IncIdEntity<Long> {

    /**
     * app 语音包 类型
     */
    public static final Integer FILE_TYPE_VOICE_PACKETS = 1;
    /**
     * 心率耳机升级包
     */
    public static final Integer FILE_TYPE_HEADSET = 20;
    /**
     * 手表升级包
     */
    public static final Integer FILE_TYPE_WATCH = 21;
    /**
     * 手表运动模块-阿波罗
     */
    public static final Integer FILE_TYPE_WATCH_MODE_ABO = 22;
    /**
     * 手表运动模块-心率
     */
    public static final Integer FILE_TYPE_WATCH_MODE_HEARTRATE = 23;

    /**
     * 手表固件升级工具
     */
    public static final Integer FILE_TYPE_WATCH_TOOL = 30;

    /**
     * 手表Flash升级工具
     */
    public static final Integer FILE_TYPE_WATCH_FLASH_TOOL = 31;
    /**
     * 标准普通文件
     */
    public static final Integer FILE_TYPE_COMMON = 11;


    /**
     * 标准普通文件
     */
    public static final Integer FILE_TYPE_WARCH_LOG = 12;

    /**
     * 文件链接
     */
    private String fileLink;
    /**
     * 文件类型
     * 1、语音包
     * 11、其他文件,如:js、css、img之类的
     */
    private Integer fileType;
    /**
     * 文件主题
     */
    private String title;

    /**
     * 是否强制升级（0，否;1,是）
     */
    private String isForce;
    /**
     * 文件描述
     */
    private String des;

    /**
     * 文件英文描述
     */
    private String des_en;

    /**
     * 描述语言中文
     */
    private String des_lan;
    /**
     * 描述语言英文
     */
    private String des_lan_en;
    /**
     * 状态
     * 1、正常 0、无效
     */
    private Integer status;



    /**
     * 添加时间
     */
    private Long addTime;

    /**
     * 用户ID
     * */
    private Integer uid;

    /**
     * 手表文件备注Url
     * */
    private String fileDesUrl;

    public String getDes_en() {
        return des_en;
    }

    public String getDes_lan() {
        return des_lan;
    }

    public void setDes_en(String des_en) {
        this.des_en = des_en;
    }

    public void setDes_lan(String des_lan) {
        this.des_lan = des_lan;
    }

    public String getDes_lan_en() {
        return des_lan_en;
    }

    public void setDes_lan_en(String des_lan_en) {
        this.des_lan_en = des_lan_en;
    }

    /**
     * 其他信息
     * 语音包
     *      {voiceId - 语音编号, dirName - 目录名称,  size - 文件大小, version - 版本号, targetApp - 支持的app}
     *
     * 心率耳机升级包
     *      {version- 版本号, serial = 系列号，number = 序号, isNew = 最新版本}
     *
     * 手表升级包
     *      {version- 版本号, serial = 系列号，number = 外部版本号, isNew = 最新版本}
     * 阿波罗升级包
     *      {version- 版本号, serial = 系列号，number = 外部版本号, isNew = 最新版本}
     */


    private Map<String, String> other;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Map<String, String> getOther() {
        return other;
    }

    public void setOther(Map<String, String> other) {
        this.other = other;
    }

    public String getIsForce() {
        return isForce;
    }

    public void setIsForce(String isForce) {
        this.isForce = isForce;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFileDesUrl() {
        return fileDesUrl;
    }

    public void setFileDesUrl(String fileDesUrl) {
        this.fileDesUrl = fileDesUrl;
    }
}
