package com.business.app.mixBanner;

import com.business.core.entity.mix.MixBanner;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sin on 2016/3/8.
 */
@Repository
public class MixBannerDao extends BaseMongoDaoSupport {

    /**
     * 查询有所 mixBanner
     *
     * @param fields 列
     * @return mixBanner 集合
     */
    public List<MixBanner> findAllMixBanner(String...fields) {
        Query query = new Query(Criteria.where("status").is(MixBanner.STATUS_RELEASE).and("channel").is(MixBanner.CHANNEL_SYS));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, MixBanner.class);
    }

    /**
     * 查询有所 mixBanner
     *
     * @param fields 列
     * @return mixBanner 集合
     */
    public List<MixBanner> findMixBannerByIds(List<Integer> ids, String...fields) {
        Query query = new Query(Criteria.where("status").is(MixBanner.STATUS_RELEASE).and("id").in(ids).and("channel").is(MixBanner.CHANNEL_WD));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, MixBanner.class);
    }
}
