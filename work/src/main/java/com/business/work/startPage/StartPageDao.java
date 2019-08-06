package com.business.work.startPage;

import com.business.core.entity.Page;
import com.business.core.entity.startPage.StartPage;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by edward on 2016/5/16.
 */
@Repository
public class StartPageDao extends BaseMongoDaoSupport{

    /**
     * 查询启动闪屏页
     * @param id 编号
     * @param fields 查询的字段
     */
    public StartPage findStartPage(Long id, String...fields) {
        return findEntityById(StartPage.class, id, fields);
    }

    /**
     * 添加新的闪屏页
     *
     * @param startPage 启动页
     */
    public void addStartPage(StartPage startPage) {
        insertToMongo(startPage);
    }

    /**
     * 闪屏 分页查询
     */
    public void page(Page<StartPage> page, String...fields) {
        Criteria criteria = new Criteria();
        if (page.getFilter().containsKey("id")) {
            criteria.and("id").is(page.getFilter().get("id"));
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(StartPage.class, page, query, fields);
    }

    /**
     * 修改 闪屏页
     * @param id 闪屏编号
     * @param update 修改的内容
     */
    public void modifyStartPage(Long id, Update update) {
        findAndModifyEntityFieldsById(StartPage.class, id, update);
    }
}
