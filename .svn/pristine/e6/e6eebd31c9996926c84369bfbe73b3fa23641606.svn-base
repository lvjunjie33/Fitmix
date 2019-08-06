package com.business.core.operations.sysManager;

import com.business.core.entity.Page;
import com.business.core.entity.logs.RequestLog;
import com.business.core.entity.logs.SysErrorLog;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.mongo.MongoType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2016/4/18.
 */
@Repository
public class SysManagerDao extends BaseMongoDaoSupport {

    /**
     * 保存系统接口异常信息
     * @param log 异常日志
     */
    public void saveSysErrorLog(SysErrorLog log) {
        insertToMongo(log);
    }

    /**
     * 查询异常信息
     *
     * @param page 分页对象
     */
    public void findSysErrorLog(Page<SysErrorLog> page) {
        Criteria criteria = new Criteria();
        Map<String, Object> filter = page.getFilter();
        if (filter.containsKey("solveStatus")) {
            criteria.and("solveStatus").is((Integer)filter.get("solveStatus"));
        }

        if (filter.containsKey("sys")) {
            criteria.and("sys").is((Integer)filter.get("sys"));
        }

        if (filter.containsKey("ip")) {
            criteria.and("ip").is(filter.get("ip"));
        }

        if (filter.containsKey("matchedPath")) {
            criteria.and("matchedPath").is(filter.get("matchedPath"));
        }

        if (filter.containsKey("bTime") && filter.containsKey("eTime")) {
            criteria.and("addTime").gte(filter.get("bTime")).lte(filter.get("eTime"));
        } else if (filter.containsKey("bTime") && !filter.containsKey("eTime")) {
            criteria.and("addTime").gte(filter.get("bTime"));
        } else if (!filter.containsKey("bTime") && filter.containsKey("eTime")) {
            criteria.and("addTime").lte(filter.get("eTime"));
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(SysErrorLog.class, page, query);
    }

    /**
     * 修改异常日志信息
     * @param update 修改内容
     * @param id 异常日志编号
     */
    public void modifySysErrorLog(Update update, Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, SysErrorLog.class);
    }

    /**
     * 删除指定时间段的数据
     * @param bTime 开始时间
     * @param eTime 结束时间
     */
    public void removeSysErrorLog(Date bTime, Date eTime) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("addTime").gt(bTime).lt(eTime)), SysErrorLog.class);
    }
}
