package com.business.work.sys;

import com.business.core.entity.SysParam;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/4/29.
 */
@Repository
public class SysParamDao extends BaseMongoDaoSupport {

    /**
     * 查询系统参数
     * @return 返回系统参数
     */
    public SysParam findSysParam () {
        return getRoutingMongoOps().findOne(new Query(), SysParam.class);
    }


    public void updateSysParam(Update update) {
        Query query = new Query();
        if (update.getUpdateObject().keySet().isEmpty()) {
            throw new RuntimeException("更新内容未设置！");
        }
        getRoutingMongoOps().updateFirst(query, update, SysParam.class);
    }
}
