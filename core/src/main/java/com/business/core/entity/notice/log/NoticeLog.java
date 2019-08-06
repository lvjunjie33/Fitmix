package com.business.core.entity.notice.log;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by sin on 2015/12/9.
 */
@Document(collection = "Log_Notice")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class NoticeLog extends IncIdEntity<Long> {

    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * idfa 编号
     */
    private String idfa;
    /**
     * 内容
     */
    private String content;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 添加时间（冗余方便操作）
     */
    private Date addDate;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
