package com.business.core.entity.dynamic;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fenxio on 2016/10/8.
 * 动态点赞列表
 */
@Document(collection = "Flower")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class Flower extends IncIdEntity<Long> {

    /**
     * 动态编号 {@link com.business.core.entity.dynamic.Dynamic#id}
     */
    private Long did;
    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 添加时间
     */
    private Long addTime;

    @Transient
    private User user;

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flower(Long did, Integer uid) {
        this.did = did;
        this.uid = uid;
    }
}


