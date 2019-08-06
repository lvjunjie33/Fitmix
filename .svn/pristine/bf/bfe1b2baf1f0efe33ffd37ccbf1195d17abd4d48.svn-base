package com.business.work.language;

import com.business.core.entity.Page;
import com.business.core.entity.SysParam;
import com.business.core.entity.language.CharTable;
import com.business.core.entity.user.User;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.DateUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by edward on 2017/8/17.
 */
@Repository
public class CharTableDao extends BaseMongoDaoSupport {

    /**
     * 翻译
     *
     * @param cn 中文
     * @param en 英文
     */
    public void editEn(String cn, String en) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("strCN").is(cn)), Update.update("strEN", en), CharTable.class);
    }

    public void delete() {
        {
            Date d = DateUtil.parse("2017-08-30 22:33", "yyyy-MM-dd hh:mm");
            Date e = DateUtil.parse("2017-08-30 15:33", "yyyy-MM-dd hh:mm");
            Query query = Query.query(Criteria.where("addTime").lt(d.getTime()).gt(e).and("isNew").is(1));
            getRoutingMongoOps().remove(query, User.class);
        }

        {
            Date d = DateUtil.parse("2017-08-29 23:33", "yyyy-MM-dd hh:mm");
            Date e = DateUtil.parse("2017-08-29 15:33", "yyyy-MM-dd hh:mm");
            Query query = Query.query(Criteria.where("addTime").lt(d.getTime()).gt(e).and("isNew").is(1));
            getRoutingMongoOps().remove(query, User.class);
        }

        {
            Date d = DateUtil.parse("2017-08-28 23:35", "yyyy-MM-dd hh:mm");
            Date e = DateUtil.parse("2017-08-28 15:33", "yyyy-MM-dd hh:mm");
            Query query = Query.query(Criteria.where("addTime").lt(d.getTime()).gt(e).and("isNew").is(1));
            getRoutingMongoOps().remove(query, User.class);
        }

    }
}
