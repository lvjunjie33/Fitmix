package com.business.core.entity.wx;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by sin on 2015/7/23 0023.
 */
@Document(collection = "WXGetUserInfoKey")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class WXGetUserInfoKey {

    private String timestamp;

    private String nonceStr;

    private String signature;

    private Date updateTime;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
