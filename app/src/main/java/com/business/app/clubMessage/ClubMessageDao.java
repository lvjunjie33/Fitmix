package com.business.app.clubMessage;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.Page;
import com.business.core.entity.club.ClubMessage;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;

/**
 * Created by sin on 2015/11/24.
 */
@Repository
public class ClubMessageDao extends BaseMongoDaoSupport {

    /**
     * 查询 俱乐部 留言消息
     *
     * @param page 分页信息
     * @param fields 列
     */
    public void page(Page<ClubMessage> page, String...fields) {

        LinkedHashMap filter = page.getFilter();
        Criteria criteria = new Criteria();

        if (filter.containsKey("clubId")) {
            criteria.and("clubId").is(filter.get("clubId"));
        }

        if (filter.containsKey("isOldVersion")) {
            criteria.and("msgType").in(null, MsgConstants.CONTENT_TYPE_TEXT);
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "_id"));
        findEntityPage(ClubMessage.class, page, query, fields);
    }

    /**
     * 添加 消息（留言板）
     * @param clubMessage 俱乐部消息
     */
    public void insertClubMessage(ClubMessage clubMessage) {
        insertToMongo(clubMessage);
    }

}
