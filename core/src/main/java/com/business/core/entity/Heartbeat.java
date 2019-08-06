package com.business.core.entity;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 *     低版本无法修复的问题：
 *     com.mongodb.DBPortPool gotError
 *     java.net.SocketException: Software caused connection abort: recv failed
 *     http://www.4byte.cn/question/509187/com-mongodb-dbportpool-goterror-warning-emptying-dbportpool-to-ip-27017-b-c-of-error-using-spring-mongotemplate.html
 * </p>
 * Created by sin on 2015/12/12.
 */
@Document(collection = "Sys_Heartbeat")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Heartbeat extends IncIdEntity<Integer> {

    /**
     * 心跳间隔时间
     */
    public static Long HEART_BEAT_INTERVAL_TIME = 1000 * 20L;
    /**
     * 心跳时间
     */
    private Long heartBeatTime;


    public Long getHeartBeatTime() {
        return heartBeatTime;
    }

    public void setHeartBeatTime(Long heartBeatTime) {
        this.heartBeatTime = heartBeatTime;
    }
}
