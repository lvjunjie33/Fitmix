package com.business.work.mixAlbum;

import com.business.core.entity.Page;
import com.business.core.entity.mix.MixAlbum;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Created by Sin on 2016/3/9.
 *
 * mix 专辑
 */
@Repository
public class MixAlbumDao extends BaseMongoDaoSupport {


    public void findPageMixAlbum(Page<MixAlbum> page) {
        LinkedHashMap filter = page.getFilter();
        Criteria criteria = new Criteria();

        if (filter.containsKey("id") && !filter.get("id").equals("")) {
            criteria.and("id").is(filter.get("id"));
        }

        if (filter.containsKey("uid") && !filter.get("uid").equals("")) {
            criteria.and("uid").is(filter.get("uid"));
        }

        if (filter.containsKey("status")) {
            criteria.and("status").is(filter.get("status"));
        }

        if (filter.containsKey("type")) {
            criteria.and("type").is(filter.get("type"));
        }

        if (filter.containsKey("checkStatus") && !filter.get("checkStatus").equals("-1")) {
            criteria.and("checkStatus").is(Integer.parseInt((String) filter.get("checkStatus")));
        }

        if (!StringUtils.isEmpty(filter.get("title"))) {
            criteria.and("title").regex(String.format(".*%s.*", filter.get("title")));
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(MixAlbum.class, page, query);
    }

    public void insertMixAlbum(MixAlbum mixAlbum) {
        insertToMongo(mixAlbum);
    }

    public MixAlbum findMixAlbumById(Integer albumId, String...fields) {
        Query query = new Query(Criteria.where("id").is(albumId));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, MixAlbum.class);
    }

    public void updateMixAlbumById(Integer albumId, Update update) {
        updateEntityFieldsById(MixAlbum.class, albumId, update);
    }
}
