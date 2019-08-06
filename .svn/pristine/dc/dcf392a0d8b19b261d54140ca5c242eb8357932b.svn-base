package com.business.core.entity.logs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2017/7/18.
 *
 * 验证码日志文件
 */
@Document(collection = "VerifyCodeLog")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class VerifyCodeLog {
    /**
     * 手机号码
     */
    public static final Integer CODE_TYPE_MOBILE = 1;
    /**
     * 邮件
     */
    public static final Integer CODE_TYPE_MAIL = 2;
    /**
     * 1、手机号码(短信验证码)
     * 2、Email(邮件验证码)
     */
    private Integer type;
    /**
     * 关键字 (手机号码、邮件)
     */
    private String keywords;
    /**
     * 验证码
     */
    private String code;
    /**
     * 添加时间 用于判断验证码有效期
     */
    private Long addTime;

    /**
     * 请求客户端IP
     */
    private String remoteAddr;

    /**
     * 端短信发送次数
     * */
    private Integer sendCount;

    /**
     * 第一次添加时间 用于判断一定时间内发送短信验证码
     */
    private Long oneAddTime;
    /**
     * 短信有效时间
     */
    private Long effectiveTime;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public void setRemoteAddr(String remoteAddr) {this.remoteAddr = remoteAddr;}

    public String getRemoteAddr() {return remoteAddr;}

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Long getOneAddTime() {
        return oneAddTime;
    }

    public void setOneAddTime(Long oneAddTime) {
        this.oneAddTime = oneAddTime;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
}
