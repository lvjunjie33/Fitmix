package com.business.core.entity.mix;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2018/8/30.
 */
@Document(collection = "MixDetail")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class MixDetail extends IncIdEntity<Integer> {

    /**
     * 歌曲ID
     * */
    private Integer mixid;
    /**
     * 名称
     * */
    private String name;
    /**
     * 描述
     * */
    private String introduce;
    /**
     * 新增时间
     * */
    private Long addTime;

    private String name_lan;

    private String introduce_lan;

    public void setName_lan(String name_lan) {
        this.name_lan = name_lan;
    }

    public void setIntroduce_lan(String introduce_lan) {
        this.introduce_lan = introduce_lan;
    }

    public String getName_lan() {
        return name_lan;
    }

    public String getIntroduce_lan() {
        return introduce_lan;
    }

    public Integer getMixid() {
        return mixid;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setMixid(Integer mixid) {
        this.mixid = mixid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
