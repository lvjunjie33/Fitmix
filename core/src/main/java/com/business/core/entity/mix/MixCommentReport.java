package com.business.core.entity.mix;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by sin on 2015/11/18.
 * 举报信息 TODO 已经废弃
 */
@Document(collection = "MixCommentReport")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class MixCommentReport extends IncIdEntity<Long>{

    /**
     * 未处理
     */
    public static final Integer STATUS_NOT_HANDLER = 0;
    /**
     * 已处理
     */
    public static final Integer STATUS_HANDLER = 1;

    /**
     * mix comment id
     * {@link MixComment#id}
     */
    private Long mcId;
    /**
     * 歌曲编号
     * {@link Mix#id}
     */
    private Integer mid;
    /**
     * 被举报用户
     */
    private Integer uid;
    /**
     * 被举报用户
     */
    private Integer reportUid;
    /**
     * 内容
     */
    private String content;
    /**
     * 状态 0、未处理  1、已处理
     */
    private Integer status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 添加时间
     */
    private Long addTime;

    public Long getMcId() {
        return mcId;
    }

    public void setMcId(Long mcId) {
        this.mcId = mcId;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getReportUid() {
        return reportUid;
    }

    public void setReportUid(Integer reportUid) {
        this.reportUid = reportUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
