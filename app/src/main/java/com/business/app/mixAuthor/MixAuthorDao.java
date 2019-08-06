package com.business.app.mixAuthor;

import com.business.core.entity.mix.MixAuthor;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by sin on 2015/8/7 0007.
 */
@Repository
public class MixAuthorDao extends BaseMongoDaoSupport {

    /**
     * mix 歌曲作者
     * @param ids 作者编号
     * @param fields 列
     * @return 歌曲作者
     */
    public List<MixAuthor> findMixAuthorByIds(Collection<Integer> ids, String...fields) {
        Query query = new Query(Criteria.where("id").is(ids));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, MixAuthor.class);
    }

}
