package com.business.work.video;

import com.business.core.entity.Page;
import com.business.core.entity.video.Video;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Created by zhangtao on 2016/4/25.
 */
@Repository
public class VideoDao extends BaseMongoDaoSupport {

    /**
     * 插入视频
     * @param video
     */
    public void insertVideo(Video video){
        insertToMongo(video);
    }

    /**
     * 根据ID 获取视频信息
     * @param id
     * @param fields
     * @return
     */
    public Video findVideoById(Integer id, String... fields) {
        return findEntityById(Video.class,id,fields);
    }

    /**
     * 根据ID 更新视频信息
     * @param id
     * @param update
     */
    public void updateVideoById(Integer id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, Video.class);
    }

    /**
     * 获取视频列表
     * @param page 分页信息
     * @param fields 列
     */
    public void findVideoPage(Page<Video> page,String... fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (!StringUtils.isEmpty(filter.get("id"))) {
            criteria.and("id").is(filter.get("id"));
        }

        Query query = new Query(criteria);
        findEntityPage(Video.class, page, query, fields);
    }

    /**
     * 根据 编号删除视频
     * @param vid 编号
     */
    public void videoRemove(Integer vid) {
        removeEntityById(Video.class, vid);
    }
}
