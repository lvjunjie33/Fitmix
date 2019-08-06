package com.business.core.entity.logs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Created by sin on 2015/6/8 0008.
 */
@Document(collection = "Log_UserLoginLog")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class UserLoginLog {

    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * log 值, 登录类型 {@link com.business.core.entity.user.User#terminal}
     */
    private Integer terminal;
    /**
     * log 信息
     */
    private String info;
    /**
     * 添加时间
     */
    private Long addTime;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
