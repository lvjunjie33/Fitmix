package com.business.core.operations.logs;

import com.business.core.entity.logs.AppErrorLog;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by sin on 2015/9/14.
 */
@Repository
public class AppErrorLogCoreDao extends BaseMongoDaoSupport {

    /**
     * 添加 IOS 错误日志
     * @param iosErrorLog 错误信息
     */
    public void insertAppErrorLog(AppErrorLog iosErrorLog) {
        insertToMongo(iosErrorLog);
    }
}
