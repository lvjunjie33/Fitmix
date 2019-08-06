package com.business.work.notice;

import com.business.core.entity.Page;
import com.business.core.entity.notice.Notice;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.DateUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Sin on 2016/1/5.
 */
@Repository
public class NoticeDao extends BaseMongoDaoSupport {

    public void page(Page<Notice> page, String...fields) {
        LinkedHashMap<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();

        if (!StringUtils.isEmpty(filter.get("channel"))) {
            criteria.and("channel").is(filter.get("channel"));
        }

        if (!StringUtils.isEmpty(filter.get("beginTime"))) {
            criteria.and("addTime").
                    gte(DateUtil.getDayBegin(DateUtil.parse(filter.get("beginTime").toString(), "yyyy-MM-dd")).getTime()).
                    lte(DateUtil.getDayEnd(DateUtil.parse(filter.get("beginTime").toString(), "yyyy-MM-dd")).getTime());
        }

        Query query = new Query(criteria);
        findEntityPage(Notice.class, page, query, fields);
    }

    public List<Notice> export(Page<Notice> page, String...fields) {
        LinkedHashMap<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();

        if (!StringUtils.isEmpty(filter.get("channel"))) {
            criteria.and("channel").is(filter.get("channel"));
        }

        if (!StringUtils.isEmpty(filter.get("beginTime"))) {
            criteria.and("addTime").
                    gte(DateUtil.getDayBegin(DateUtil.parse(filter.get("beginTime").toString(), "yyyy-MM-dd")).getTime()).
                    lte(DateUtil.getDayEnd(DateUtil.parse(filter.get("beginTime").toString(), "yyyy-MM-dd")).getTime());
        }

        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Notice.class);
    }
}
