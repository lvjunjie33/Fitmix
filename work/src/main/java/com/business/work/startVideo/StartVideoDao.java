package com.business.work.startVideo;

import com.business.core.entity.Page;
import com.business.core.entity.startVideo.StartVideo;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by edward on 2016/5/17.
 */
@Repository
public class StartVideoDao extends BaseMongoDaoSupport{

    /**
     * 启动视频分页查询
     *
     * @param page 分页对象
     */
    public void page(Page<StartVideo> page, String...fields) {
        Criteria criteria = new Criteria();
        Map<String, Object> filter = page.getFilter();
        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(StartVideo.class, page, query, fields);
    }

    /**
     * 添加新的启动视频
     *
     * @param startVideo 启动视频
     */
    public void add(StartVideo startVideo) {
        insertToMongo(startVideo);
    }

    /**
     * 通过主键查询 启动视频
     * @param id 编号
     * @param fields 查询的字段
     */
    public StartVideo findEntityById(Long id, String...fields) {
        return findEntityById(StartVideo.class, id, fields);
    }

    public void modify(Long id, Update update) {
        updateEntityFieldsById(StartVideo.class, id, update);
    }
}
