package com.business.work.gw;

import com.business.core.entity.Page;
import com.business.core.entity.file.File;
import com.business.core.entity.gw.CommonProblem;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

/**
 * Created by edward on 2018/9/18.
 */
@Repository
public class GwDao  extends BaseMongoDaoSupport {

    public void page(Page page,String ... fields) {
        Map<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();
        //预留查询条件
//        if (filter.containsKey("CommonProblem")) {
//            criteria.and("CommonProblem").is(filter.get("CommonProblem"));
//        }
//        if (filter.containsKey("problemContent")) {
//            criteria.and("problemContent").is(filter.get("problemContent"));
//        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "problemTitile", "problemContent","addTime","status","problemTitile_en","problemContent_en","problem_lan"));
        findEntityPage(CommonProblem.class, page, query, fields);
    }

    public void add(CommonProblem commonProblem) {
        insertToMongo(commonProblem);
    }
    public CommonProblem findCommonProblemById(Long id, String ...fields) {
        return findEntityById(CommonProblem.class, id, fields);
    }


    public void modify(Long id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, CommonProblem.class);
    }

    public void deleteFile(Long id) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("id").is(id)), CommonProblem.class);
    }

    public CommonProblem findFileAjax(Long id, String lan,String ...fields) {
        Criteria criteria = new Criteria();
        criteria.and("_id").is(id).and("problem_lan").is(lan);
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, CommonProblem.class);
    }
}
