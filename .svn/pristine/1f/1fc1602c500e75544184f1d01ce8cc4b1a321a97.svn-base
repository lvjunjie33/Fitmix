package com.business.bbs.recommend;

import com.business.bbs.base.support.AbstractDao;
import com.business.bbs.base.support.AbstractService;
import com.business.core.entity.bbs.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fenxio on 2016/9/14.
 */
@Service
public class RecommendService extends AbstractService<Recommend> {

    @Autowired
    private RecommendDao recommendDao;

    @Override
    protected AbstractDao<Recommend> getAbstractDao() {
        return recommendDao;
    }

    /**
     * 获取所有推荐列表
     * @return
     */
    public List<Recommend> getAllRecommend() {
        Query query = new Query(Criteria.where("status").is(Recommend.STATUS_RELEASE));
        return recommendDao.find(query);
    }
}
