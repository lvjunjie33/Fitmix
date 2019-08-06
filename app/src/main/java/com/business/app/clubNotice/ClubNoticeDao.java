package com.business.app.clubNotice;

import com.business.core.entity.Page;
import com.business.core.entity.club.ClubNotice;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by sin on 2015/12/7.
 */
@Repository
public class ClubNoticeDao extends BaseMongoDaoSupport {


    /**
     * 公告分页
     * @param page 分页对象
     * @param clubId 俱乐部信息
     * @param excludeFields 列
     */
    public void page(Page<ClubNotice> page, Long clubId, String...excludeFields) {
        Criteria criteria = Criteria.where("clubId").is(clubId).and("status").is(ClubNotice.STATUS_NORMAL);
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "beginTime"));
        excludeFields(query, excludeFields);
        findEntityPage(ClubNotice.class, page, query);
    }

    /**
     * 添加俱乐部公告
     * @param clubNotice 俱乐部公告
     */
    public void insertClubNotice(ClubNotice clubNotice) {
        insertToMongo(clubNotice);
    }

    /**
     * 查询 俱乐部公告
     * @param id 公告编号
     * @param clubId 俱乐部编号
     * @param uid 用户编号
     * @param status 状态 {@link ClubNotice#status}
     * @param fields 列
     * @return 公告信息
     */
    public ClubNotice findClubNoticeAndIdAndClubIdAndUid(Long id, Long clubId, Integer uid, Integer status, String...fields) {
        Query query = new Query(Criteria.where("id").is(id).
                and("clubId").is(clubId).and("uid").is(uid).and("status").is(status));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, ClubNotice.class);
    }

    /**
     * 查询并修改 ，返回新的
     * @param id 公告编号
     * @param clubId 俱乐部编号
     * @param uid 用户编号
     * @param update 更新信息
     */
    public ClubNotice findAndModifyNew(Long id, Long clubId, Integer uid, Update update) {
        Query query = new Query(Criteria.where("id").is(id).and("clubId").is(clubId).and("uid").is(uid));
        return getRoutingMongoOps().findAndModify(query, update, FIND_AND_MODIFY_OPTIONS_RETURN_NEW, ClubNotice.class);
    }
}
