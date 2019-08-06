package com.business.work.activity;

import com.business.core.entity.Page;
import com.business.core.entity.activity.ActivitySignUp;
import com.business.core.entity.stat.ActivityIntegralStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/9/11.
 */
@Repository
public class ActivityDao extends BaseMongoDaoSupport {

    /**
     * 查询活动报名信息
     * @param fields 查询的字段
     */
    public void findActivitySignUpInfo(Page<ActivitySignUp> page, String...fields) {

        Map<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();
        if (filter.containsKey("activityId")) {
            criteria.and("activityId").is(filter.get("activityId"));
        }
        if (filter.containsKey("groupId")) {
            criteria.and("groupId").is(filter.get("groupId"));
        }
        if (filter.containsKey("mobileName")) {
            criteria.and("mobileName").is(filter.get("mobileName"));
        }
        if (filter.containsKey("mobilePhone")) {
            criteria.and("mobilePhone").is(filter.get("mobilePhone"));
        }
        if (filter.containsKey("bTime") && filter.containsKey("eTime")) {
            criteria.and("addTime").gte(((Date)filter.get("bTime")).getTime()).lte(((Date)filter.get("eTime")).getTime());
        } else if (filter.containsKey("bTime") && !filter.containsKey("eTime")) {
            criteria.and("addTime").gte(((Date)filter.get("bTime")).getTime());
        } else if (!filter.containsKey("bTime") && filter.containsKey("eTime")) {
            criteria.and("addTime").lte(((Date)filter.get("eTime")).getTime());
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(ActivitySignUp.class, page, query, fields);
    }

    /**
     * 查询报名总人数
     *
     * @param activityId 赛事编号
     */
    public long findActivitySignUpCount(Integer activityId) {
        Query query = Query.query(Criteria.where("activityId").is(activityId));
        return getRoutingMongoOps().count(query, ActivitySignUp.class);
    }

    /**
     * 查询报名信息
     *
     * @param activityId 赛事编号
     * @return 报名清单
     */
    public List<ActivitySignUp> findActivitySignUps(Integer activityId) {
        Query query = Query.query(Criteria.where("activityId").is(activityId));
        return getRoutingMongoOps().find(query, ActivitySignUp.class);
    }

    /**
     * 通过手机号码 用户报名次数
     *
     * @param mobiles 手机号码
     */
    public List<ActivitySignUp> findActivitySignUpInfoByMobile(List<String> mobiles, String...fields) {
        Query query = Query.query(Criteria.where("mobilePhone").in(mobiles));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, ActivitySignUp.class);
    }

    /**
     * 查询积分赛事的排序信息
     *
     * @param activityId 赛事编号
     * @param statDate 统计时间
     * @param fields 查询的字段
     */
    public List<ActivityIntegralStat> findActivityIntegralStat(Integer activityId, Integer statDate, String...fields) {
        Query query = Query.query(Criteria.where("activityId").is(activityId).and("statDate").is(statDate));
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, ActivityIntegralStat.class);
    }

}
