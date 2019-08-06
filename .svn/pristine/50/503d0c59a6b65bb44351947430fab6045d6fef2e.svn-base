package com.business.core.operations.language;

import com.business.core.entity.Page;
import com.business.core.entity.language.CharTable;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;

/**
 * Created by edward on 2017/8/29.
 */
@Repository
public class CharTableCoreDao extends BaseMongoDaoSupport {

    public void upset(String strCn) {
        getRoutingMongoOps().upsert(Query.query(Criteria.where("strCN").is(strCn)),
                Update.update("strCN", strCn).set("addTime", System.currentTimeMillis()), CharTable.class);
    }

    public void page(Page<CharTable> page, String ... fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();
        if (filter.containsKey("strCN")) {
            criteria.and("strCN").regex(filter.get("strCN").toString());
        }

        if (filter.containsKey("isEmpty")) {
            criteria.and("strEN").is(null);
        } else {
            criteria.and("strEN").ne(null);
        }

        Query query = new Query(criteria);
        if (filter.containsKey("sort")) {
            Integer sort = (Integer) filter.get("sort");
            if (sort == 1) {
                query.with(new Sort(Sort.Direction.DESC, "addTime"));
                query.with(new Sort(Sort.Direction.ASC, "strCN"));
            } else if (sort == 2) {
                query.with(new Sort(Sort.Direction.ASC, "strCN", "addTime"));
            }
        } else {
            query.with(new Sort(Sort.Direction.DESC, "addTime"));
        }
        findEntityPage(CharTable.class, page, query, fields);
    }
}
