package com.business.core.operations.mix;

import com.business.core.entity.mix.Mix;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2015/6/8 0008.
 */
@Repository
public class MixCoreDao extends BaseMongoDaoSupport {

    /**
     * 根据 mix customIdentification 查询
     * @param customIdentification 歌曲序号
     * @param fields 列
     * @return mix 歌曲
     */
    public Mix findMixByCustomIdentification (String customIdentification, String...fields) {
        Query query = new Query(Criteria.where("customIdentification").is(customIdentification));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Mix.class);
    }

    /**
     * 查询 Mix 歌曲信息
     * @param id 编号
     * @param fields 列
     * @return Mix歌曲信息
     */
    public Mix findMixById (Integer id, String...fields) {
        Query query = new Query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Mix.class);
    }

    /**
     * 查询 mix 歌曲信息
     * @param maid 作者编号
     * @param fields 列
     * @return mix信息集合
     */
    public List<Mix> findMixWithMaids(Integer maid, String...fields) {
        Query query = new Query(Criteria.where("maid").is(maid));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Mix.class);
    }

    /**
     * 更新 Mix 歌曲信息
     * @param ids id
     * @param update 更新内容
     */
    public void updateMixWithIds(Collection<Integer> ids, Update update) {
        Query query = new Query(Criteria.where("id").in(ids));
        getRoutingMongoOps().updateMulti(query, update, Mix.class);
    }
}
