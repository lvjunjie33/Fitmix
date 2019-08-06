package com.business.app.video;

import com.business.core.entity.Page;
import com.business.core.entity.video.Video;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;

/**
 * Created by fenxio on 2016/5/5.
 */
@Repository
public class VideoDao extends BaseMongoDaoSupport {

    /**
     * 获取视频列表
     * @param page 分页
     * @param fields 字段
     */
    public void list(Page<Video> page, String ... fields) {
        LinkedHashMap<String, Object> filter = page.getFilter();

        Criteria criteria = new Criteria();
        // status
        if (filter.containsKey("status")) {
            criteria.and("status").is(filter.get("status"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "status"));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "id"));

        findEntityPage(Video.class, page, query, fields);

    }

    /**
     * 获取视频信息
     * @param videoId 视频ID
     * @param fields  字段
     * @return
     */
    public Video findVideoById(Integer videoId, String ... fields) {
        Query query = Query.query(Criteria.where("id").is(videoId));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Video.class);
    }

    /**
     * 分享视频数 +1
     * @param id  编号
     * @param update 更新
     */
    public void modiftyShareCountById(Integer id, Update update) {
        Query query = Query.query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, Video.class);
    }
}
