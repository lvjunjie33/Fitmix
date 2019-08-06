package com.business.app.requestLog;

import com.business.core.entity.logs.RequestLog;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by Sin on 2016/4/20.
 *
 * log collections
 */
@Repository
public class RequestLogDao extends BaseMongoDaoSupport {

    /**
     * 添加 request log
     *
     * @param requestLog 请求日志
     */
    public void insertRequestLog(RequestLog requestLog) {
        insertToMongo(requestLog);
    }
}
