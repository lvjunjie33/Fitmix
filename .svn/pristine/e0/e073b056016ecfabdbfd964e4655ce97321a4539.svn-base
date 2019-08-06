package com.business.work.joinActivity;

import com.business.core.entity.Page;
import com.business.core.entity.joinActivity.JoinActivity;
import com.business.core.entity.joinActivity.JoinActivityEntered;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Created by fenxio on 2016/5/17.
 */
@Repository
public class JoinActivityDao extends BaseMongoDaoSupport {


    /**
     * 分页
     * @param page
     * @param fields
     */
    public void findJoinActivityPage(Page<JoinActivity> page, String...fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (!StringUtils.isEmpty(filter.get("id"))) {
            criteria.and("id").is(filter.get("id"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "status").and(new Sort(Sort.Direction.DESC, "addTime")));
        findEntityPage(JoinActivity.class, page, query, fields);
    }

    /**
     * 修改第三方赛事状态
     * @param id
     * @param status
     */
    public void modifyJoinActivityStatus(Long id, Integer status) {
        Update update = Update.update("status", status);
        updateEntityFieldsById(JoinActivity.class, id, update);
    }

    /**
     * 获取第三方赛事
     * @param id
     * @return
     */
    public JoinActivity findJoinActivityById(Long id) {
        return findEntityById(JoinActivity.class, id);
    }
}
