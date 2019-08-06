package com.business.work.userRun;

import com.business.core.entity.Page;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.UserRunStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.mongo.MongoType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by zhangtao on 2016/4/20.
 */
@Repository
public class UserRunDao extends BaseMongoDaoSupport {

    /**
     * 根据用户ID 获取用户最后运动列表
     * @param uids 用户ID集合
     * @param fields 列
     * @return
     */
    public List<UserRun> findUserRunByUid(Set<Integer> uids, String... fields) {
        Criteria criteria = Criteria.where("uid").in(uids);
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserRun.class);
    }

    /**
     * 用户用户运动数据分页
     *
     * @param page
     */
    public void page(Page<UserRun> page, String...fields) {
        Map<String, Object> filter = page.getFilter();
        Criteria c = new Criteria();
        if (filter.containsKey("uid")) {
            c.and("uid").is(filter.get("uid"));
        }
        if (filter.containsKey("types")) {
            c.and("type").in((List)filter.get("types"));
        }
        if (filter.containsKey("id")) {
            c.and("id").is(filter.get("id"));
        }
        if (filter.containsKey("beginTime")) {
            if (filter.containsKey("endTime")) {
                c.and("startTime").gte(((Date)filter.get("beginTime")).getTime()).lte(((Date)filter.get("endTime")).getTime());
            } else {
                c.and("startTime").gte(((Date)filter.get("beginTime")).getTime());
            }
        } else {
            if (filter.containsKey("endTime")) {
                c.and("startTime").lte(((Date)filter.get("endTime")).getTime());
            }
        }
        Query query = new Query(c);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(UserRun.class, page, query, fields);
    }

    public List<UserRun> allUserRunsByUid(Integer uid, List<Integer> types, String...fields) {
        Query query = Query.query(Criteria.where("uid").is(uid).and("type").in(types));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.ASC, "startTime"));
        return getRoutingMongoOps().find(query, UserRun.class);
    }

    /**
     * 统计分页
     *
     * @param page 统计
     */
    public void userRunStatPage(Page<UserRunStat> page, String...fields) {
        Map<String, Object> filter = page.getFilter();
        Criteria c = new Criteria();
        if (filter.containsKey("uid")) {
            c.and("uid").is(filter.get("uid"));
        }
        if (filter.containsKey("type")) {
            c.and("type").is(filter.get("type"));
        }
        Query query = new Query(c);
        query.with(new Sort(Sort.Direction.ASC, "id"));
        findEntityPage(UserRunStat.class, page, query, fields);
    }

    public void remove() {
        getRoutingMongoOps().remove(Query.query(Criteria.where("type").is(UserRun.TYPE_WATCH_SPORT)), UserRun.class);
    }
}
