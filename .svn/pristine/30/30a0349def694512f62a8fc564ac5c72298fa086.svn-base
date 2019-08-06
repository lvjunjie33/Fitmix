package com.business.work.announcement;

import com.business.core.entity.Page;
import com.business.core.entity.activity.ActivitySignUp;
import com.business.core.entity.sys.Announcement;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

/**
 * Created by edward on 2017/9/25.
 */
@Repository
public class AnnouncementDao extends BaseMongoDaoSupport {

    public void page(Page<Announcement> page, String...fields) {
        Criteria criteria = new Criteria();
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "id"));
        findEntityPage(Announcement.class, page, query, fields);
    }

    /**
     * 新增公告
     *
     * @param announcement 公告对象
     */
    public void add(Announcement announcement) {
        insertToMongo(announcement);
    }

    /**
     * 查询公告
     *
     * @param id 公告编号
     * @param fields 查询的字段
     */
    public Announcement findById(Integer id, String...fields) {
        Query query = Query.query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Announcement.class);
    }

    /**
     * 修改公告
     *
     * @param id 编号
     * @param update 修改的内容
     */
    public void modify(Integer id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, Announcement.class);
    }
}
