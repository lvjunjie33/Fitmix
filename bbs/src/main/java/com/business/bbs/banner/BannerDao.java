package com.business.bbs.banner;

import com.business.bbs.base.support.AbstractDao;
import com.business.core.entity.bbs.Banner;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fenxio on 2016/9/13.
 */
@Repository
public class BannerDao extends AbstractDao<Banner> {

    /**
     * 查找banner列表
     * @param query
     * @return
     */
    public List<Banner> find(Query query) {
        return getRoutingMongoOps().find(query, Banner.class);
    }
}
