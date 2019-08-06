package com.business.core.operations.runPlan;

import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weird on 2016/8/24.
 */
@Repository
public class RunPlanCoreDao extends BaseMongoDaoSupport {

    /**
     * 获取执行中的用户运动计划
     *
     * @return
     */
    public List<UserRunPlan> findPlanByExecute() {
        Query query = new Query(Criteria.where("plan_state").is(UserRunPlan.PLAN_IMPLEMENTATION));
        return getRoutingMongoOps().find(query, UserRunPlan.class);
    }

    /**
     * 修改用户跑步计划的状态
     *
     * @param update
     */
    public void upDateUserRunPlan(Integer id, Update update) {
        Query query = new Query(Criteria.where("_id").is(id));
        getRoutingMongoOps().updateFirst(query, update, UserRunPlan.class);
    }


}
