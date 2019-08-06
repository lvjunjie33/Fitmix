package com.business.app.mixAlbum;

import com.business.core.entity.mix.MixAlbum;
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
public class MixAlbumDao extends BaseMongoDaoSupport {

    /**
     * 查询 歌曲专辑
     *
     * @param id 编号
     * @param fields 列
     * @return 专辑信息
     */
    public MixAlbum findMixAlbumById(Integer id, String...fields) {
        Query query = new Query(Criteria.where("id").is(id).and("status").is(MixAlbum.STATUS_RELEASE));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, MixAlbum.class);
    }

    /**
     * 所有 歌曲专辑
     *
     * @param fields 列
     * @return 专辑信息
     */
    public List<MixAlbum> findAllMixAlbum(String...fields) {
        Query query = new Query(Criteria.where("status").is(MixAlbum.STATUS_RELEASE));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, MixAlbum.class);
    }
}
