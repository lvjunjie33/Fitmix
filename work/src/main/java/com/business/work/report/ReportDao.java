package com.business.work.report;

import com.business.core.entity.Page;
import com.business.core.entity.activity.Activity;
import com.business.core.entity.report.Watch;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Created by edward on 2017/6/28.
 */
@Repository
public class ReportDao extends BaseMongoDaoSupport {

    /**
     * 查询手表测试报告
     *
     * @param id 编号
     */
    public Watch findWatchById(Integer id, String...fields) {
        return findEntityById(Watch.class, id, fields);
    }

    /**
     * 手表测试报告
     *
     * @param page 分页对象
     * @param fields 查询的字段
     */
    public void watchPage(Page<Watch> page, String...fields) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "testTime"));
        findEntityPage(Watch.class, page, query, fields);
    }
}
