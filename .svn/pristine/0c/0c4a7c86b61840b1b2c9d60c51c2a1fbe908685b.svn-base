package com.business.work.mixBanner;

import com.business.core.entity.Page;
import com.business.core.entity.mix.MixBanner;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Sin on 2016/3/7.
 */
@Repository
public class MixBannerDao extends BaseMongoDaoSupport {

    /**
     * 查询 mixBanner
     *
     * @param page 分页信息
     */
    public void findPageMixBanner(Page<MixBanner> page) {
        LinkedHashMap filter = page.getFilter();

        Criteria criteria = new Criteria();

        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }

        if (filter.containsKey("status")) {
            criteria.and("status").is(filter.get("status"));
        }

        if (!StringUtils.isEmpty(filter.get("title"))) {
            criteria.and("title").regex(String.format(".*%s.*", filter.get("title")));
        }

        if (filter.containsKey("channel")) {
            criteria.and("channel").is(filter.get("channel"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(MixBanner.class, page, query);
    }

    /**
     * mix banner 查询
     *
     * @param id 编号
     * @param fields 列
     */
    public MixBanner findMixBannerById(Integer id, String...fields) {
        Query query = new Query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, MixBanner.class);
    }

    /**
     * 添加 一条 banner
     *
     * @param mixBanner banner 信息
     */
    public void insertMixBanner(MixBanner mixBanner) {
        insertToMongo(mixBanner);
    }

    /**
     * 根据 编号 更新 banner
     *
     * @param id 编号
     * @param update 更新信息
     */
    public void updateMixBannerById(Integer id, Update update) {
        updateEntityFieldsById(MixBanner.class, id, update);
    }

    /**
     * 查询banner
     *
     * @param ids banner 编号列表
     * @param fields 查询的字段
     */
    public List<MixBanner> findMixBannerByIds(Collection<Integer> ids, String...fields) {
        Query query = new Query(Criteria.where("id").in(ids));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, MixBanner.class);
    }
}
