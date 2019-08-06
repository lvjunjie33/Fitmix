package com.business.core.entity.user;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.mix.Mix;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Administrator on 2015/4/20.
 * 用户收藏 （物理删除，不保存记录）
 */
@Document(collection = "UserCollect")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class UserCollect extends IncIdEntity<Long> {

    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * mix 编号
     */
    private Integer mid;
    /**
     * 添加时间,收藏时间
     */
    private Long addTime;

    ///
    /// 非存储字段

    @Transient
    private Mix mix;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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
}
