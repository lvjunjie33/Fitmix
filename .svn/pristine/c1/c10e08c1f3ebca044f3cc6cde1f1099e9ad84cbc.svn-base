package com.business.work.bbs.recommend;

import com.business.core.entity.Page;
import com.business.core.entity.bbs.Recommend;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fenxio on 2016/9/14.
 */
@Repository
public class RecommendDao extends BaseMongoDaoSupport {

    /**
     * 保存 推荐信息
     * @param recommend
     */
    public void insertRecommend(Recommend recommend) {
        insertToMongo(recommend);
    }

    /**
     * 根据编号修改 推荐信息
     * @param id
     * @param update
     */
    public void modifyRecommendById(Integer id, Update update) {
        updateEntityFieldsById(Recommend.class, id, update);
    }

    /**
     * 根据编号 删除 推荐信息
     * @param id
     */
    public void removeRecommendById(Integer id) {
        removeEntityById(Recommend.class, id);
    }

    /**
     * 获取分页信息
     * @param page
     * @return
     */
    public void findRecommendPage(Page<Recommend> page) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        findEntityPage(Recommend.class, page, query);
    }

    public Recommend findRecommendById(Integer id) {
        return findEntityById(Recommend.class, id);
    }

    public List<Recommend> getAllRecommend() {
        return getRoutingMongoOps().findAll(Recommend.class);
    }
}
