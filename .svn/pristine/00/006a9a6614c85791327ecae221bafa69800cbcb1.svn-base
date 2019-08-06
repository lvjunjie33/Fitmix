package com.business.work.wd;

import com.business.core.entity.Page;
import com.business.core.entity.user.UserAddress;
import com.business.core.entity.wd.Speedway;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by edward on 2016/7/19.
 */
@Repository
public class SpeedwayDao extends BaseMongoDaoSupport {

    /**
     * 保存万德赛道信息
     *
     * @param speedway 万德赛道信息
     */
    public void save(Speedway speedway) {
        insertToMongo(speedway);
    }

    /**
     * 赛道分页
     *
     * @param page 分页对象
     */
    public void page(Page<Speedway> page) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(Speedway.class, page, query);
    }

    /**
     * 修改万德赛道信息
     *
     * @param speedwayId 赛道编号
     * @param update 修改的内容
     */
    public void modifyById(Integer speedwayId, Update update) {
        Query query = new Query(Criteria.where("id").is(speedwayId));
        getRoutingMongoOps().updateFirst(query, update, Speedway.class);
    }

    /**
     * 查询万德赛道信息
     *
     * @param id 万德赛道编号
     */
    public Speedway findById(Integer id) {
        return findEntityById(Speedway.class, id);
    }

}
