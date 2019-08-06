package com.business.app.notice;

import com.business.core.entity.notice.Notice;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * Created by sin on 2015/12/9.
 */
@Repository
public class NoticeDao extends BaseMongoDaoSupport {

    public Notice findNoticeByIdfa(String idfa, String channel, String status, String...fields) {
        Criteria criteria = Criteria.where("idfa").is(idfa);
        if (!StringUtils.isEmpty(channel)) {
            criteria.and("channel").is(channel);
        }
        if (!StringUtils.isEmpty(status)) {
            criteria.and("status").is(status);
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Notice.class);
    }

    public Notice findAndModifyNew(Long id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        return getRoutingMongoOps().findAndModify(query, update, FIND_AND_MODIFY_OPTIONS_RETURN_NEW, Notice.class);
    }


    public List<Notice> findNoticeByIdfasAndChannelAndStatus(Collection<String> idfas, String channel, String status, long beginTime, long endTime, String...fields) {
        Criteria criteria = Criteria.where("idfa").in(idfas).and("addTime").gte(beginTime).lte(endTime);
        if (!StringUtils.isEmpty(channel)) {
            criteria.and("channel").is(channel);
        }
        if (!StringUtils.isEmpty(status)) {
            criteria.and("status").is(status);
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Notice.class);
    }

    public void updateNoticeByIdfas(Collection<String> idfas, Update update) {
        getRoutingMongoOps().updateMulti(Query.query(Criteria.where("idfa").in(idfas)), update, Notice.class);
    }
}
