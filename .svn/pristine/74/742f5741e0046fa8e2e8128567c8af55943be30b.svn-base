package com.business.core.entity.wx;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2017/10/25.
 *
 * 微信客服帐号
 */
@Document(collection = "WXCustomerService")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class WXCustomerService extends IncIdEntity<Integer> {

    /**
     * 客服帐号
     */
    private String account;
    /**
     * 客服昵称
     */
    private String nickname;
    /**
     * 客服工号
     */
    private String csNumber;
    /**
     * 客服头像
     */
    private String avatar;
    /**
     * 客服密码
     */
    private String password;
    /**
     * 添加时间
     */
    private Long addTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCsNumber() {
        return csNumber;
    }

    public void setCsNumber(String csNumber) {
        this.csNumber = csNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
