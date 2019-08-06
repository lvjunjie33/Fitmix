package com.business.work.surprise;

import com.business.core.entity.Page;
import com.business.core.entity.surprise.Surprise;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Created by fenxio on 2016/5/19.
 */
@Repository
public class SurpriseDao extends BaseMongoDaoSupport {

    /**
     * 插入彩蛋信息
     * @param surprise 彩蛋对象
     */
    public void insertSurprise(Surprise surprise) {
        insertToMongo(surprise);
    }

    /**
     * 根据编号查找彩蛋信息
     * @param id 编号
     * @param fields 列
     * @return
     */
    public Surprise findSurpriseById(Long id, String ... fields) {
        return findEntityById(Surprise.class, id, fields);
    }

    /**
     * 根据编号修改彩蛋信息
     * @param id 编号
     * @param update 更新
     */
    public void updateSurpriseById(Long id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, Surprise.class);
    }

    /**
     * 根据编号删除彩蛋信息
     * @param id 编号
     */
    public void surpriseRemoveById(Long id) {
        removeEntityById(Surprise.class, id);
    }

    /**
     * 获取彩蛋列表
     * @param page 分页信息
     */
    public void findSurprisePage(Page<Surprise> page, String ... fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (!StringUtils.isEmpty(filter.get("id"))) {
            criteria.and("id").is(filter.get("id"));
        }

        Query query = new Query(criteria);
        findEntityPage(Surprise.class, page, query, fields);
    }

    /**
     * 获取时间段内的彩蛋
     * @param time
     * @return
     */
    public Surprise findSurpriseByStateTime(Long time) {
        Criteria criteria = Criteria.where("state").is(Surprise.STATE_2).and("startTime").lte(time).and("endTime").gte(time);
        Query query = new Query(criteria);
        return getRoutingMongoOps().findOne(query, Surprise.class);
    }

    /**
     * 判断是否可以上架
     * @param surprise
     * @return
     */
    public boolean isCanPutaway(Surprise surprise) {
        Boolean b = false;
        if(null == findSurpriseByStateTime(surprise.getStartTime()) && null == findSurpriseByStateTime(surprise.getEndTime())) {
            b = true;
        }
        return b;
    }
}
