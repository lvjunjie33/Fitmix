package com.business.core.entity.logs;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sin on 2015/9/14.
 */
@Document(collection = "log_AppErrorLog")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class AppErrorLog {

    /**
     * ios log
     */
    public static final Integer TYPE_IOS_LOG = 0;
    /**
     * android log
     */
    public static final Integer TYPE_ANDROID_LOG = 1;

    /**
     * 错误信息
     */
    private String content;
    /**
     * 其他信息（app client 自定义）
     */
    private Object object;
    /**
     * error 类型
     */
    private Integer type;
    /**
     * 添加时间
     */
    private Long addTime;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
