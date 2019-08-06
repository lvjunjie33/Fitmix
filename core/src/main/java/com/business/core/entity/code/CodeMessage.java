package com.business.core.entity.code;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/11/23.
 * app error code
 */
@Document(collection = "CodeMessage")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class CodeMessage extends IncIdEntity<Integer> implements Cloneable{

    /**
     * 属于哪个系统/app
     */
    public static final String SYS_APP = "app";

    /**
     * 属于哪个系统
     */
    private String sys;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息 中文
     */
    private String msgChina;
    /**
     * 提示消息 english
     */
    private String msgEnglish;
    /**
     * 备注
     */
    private String memo;

    @Override
    public CodeMessage clone() throws CloneNotSupportedException {
        return (CodeMessage) super.clone();
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsgChina() {
        return msgChina;
    }

    public void setMsgChina(String msgChina) {
        this.msgChina = msgChina;
    }

    public String getMsgEnglish() {
        return msgEnglish;
    }

    public void setMsgEnglish(String msgEnglish) {
        this.msgEnglish = msgEnglish;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
