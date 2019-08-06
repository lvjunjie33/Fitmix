package com.business.work.club;

import com.business.core.entity.Page;
import com.business.core.entity.club.Club;
import com.business.core.entity.club.ClubMember;
import com.business.core.entity.user.User;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Created by zhangtao on 2016/4/20.
 */
@Repository
public class ClubDao extends BaseMongoDaoSupport {


    /**
     * club 分页
     * @param page 分页
     */
    public void findClubPage(Page<Club> page, String...fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (!StringUtils.isEmpty(filter.get("id"))) {
            criteria.and("id").is(filter.get("id"));
        }
        if (!StringUtils.isEmpty(filter.get("name"))) {
            criteria.and("name").regex(String.format("^(?i)%s.*$", filter.get("name").toString()));
        }
        if (!StringUtils.isEmpty(filter.get("uid"))) {
            criteria.and("uid").is(filter.get("uid"));
        }

        Query query = new Query(criteria);
        findEntityPage(Club.class, page, query, fields);
    }

    /**
     * 获取club信息
     * @param id 俱乐部ID
     * @return
     */
    public Club findClubById(Integer id, String...fields) {
        Query query = new Query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Club.class);
    }

    public void findMemberInfoById(Page<ClubMember> page, Integer id, String...fields) {
        Query query = new Query(Criteria.where("clubId").is(id));
        findEntityPage(ClubMember.class, page, query, fields);
    }

    /**
     * 修改俱乐部信息
     *
     * @param id 俱乐部编号
     * @param update 修改的内容
     */
    public void modifyClub(Long id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, Club.class);
    }

    /**\
     * 查询俱乐部人数
     */
    public Long findClubMemberCount(Long id) {
        return getRoutingMongoOps().count(Query.query(Criteria.where("clubId").is(id)), ClubMember.class);
    }
}
