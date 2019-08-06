package com.business.work.advert;

import com.business.core.entity.Page;
import com.business.core.entity.advert.Advert;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by edward on 2016/5/16.
 */
@Repository
public class AdvertDao extends BaseMongoDaoSupport {

    /**
     * 广告分页查询
     *
     * @param page 分页对象
     * @param fields 查询的字段
     */
    public void page(Page<Advert> page, String...fields) {
        Map<String, Object> filter =  page.getFilter();
        Criteria criteria = new Criteria();
        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(Advert.class, page, query, fields);
    }

    /**
     * 新增广告
     *
     * @param advert 新的广告
     */
    public void addAdvert(Advert advert) {
        insertToMongo(advert);
    }

    /**
     * 通过广告编号 查询广告
     * @param id 广告编号
     * @param filed 查询的字段
     */
    public Advert findEntityById(Long id, String...filed) {
        return findEntityById(Advert.class, id, filed);
    }

    /**
     * 修改广告信息
     *
     * @param id 广告编号
     * @param update 修改的内容
     * @param fields 修改的字段
     */
    public void modifyAdvert(Long id, Update update, String...fields) {
        findAndModifyEntityFieldsById(Advert.class, id, update, fields);
    }
}
