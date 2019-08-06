package com.business.app.clubMember;

import com.business.core.entity.Page;
import com.business.core.entity.club.Club;
import com.business.core.entity.club.ClubMember;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by sin on 2015/11/23.
 */
@Repository
public class ClubMemberDao extends BaseMongoDaoSupport {


    /**
     * 添加 俱乐部成员
     *
     * @param clubMember 俱乐部
     */
    public void insertClubMember(ClubMember clubMember) {
        insertToMongo(clubMember);
    }


    /**
     * 俱乐部成员分页 查询
     *
     * @param page 分页
     * @param fields 列
     */
    public void findPageClubMember(Page<ClubMember> page, String...fields) {
        LinkedHashMap<String, Object> filter = page.getFilter();
        Criteria criteria = Criteria.where("status").is(ClubMember.STATUS_NORMAL);

        if (filter.containsKey("clubId")) {
            criteria.and("clubId").is(filter.get("clubId"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "addTime"));
        findEntityPage(ClubMember.class, page, query, fields);
    }

    /**
     * 俱乐部人数
     * <p>
     *     查询俱乐部成员人数，没有返回 0
     * </p>
     * @param clubId 俱乐部编号
     * @return 俱乐部人数
     */
    public long findClubMemberCountByClubId(Long clubId) {
        Query query = new Query(Criteria.where("clubId").is(clubId).and("status").is(ClubMember.STATUS_NORMAL));
        return getRoutingMongoOps().count(query, ClubMember.class);
    }

    /**
     * 查询俱乐部成员
     * <p>
     *     根据 俱乐部编号 和 成员编号查询 成员信息
     * </p>
     * @param clubId 俱乐部编号
     * @param uid 用户编号
     * @param status 状态 {@link ClubMember#status}
     * @param fields 列
     * @return 成员信息
     */
    public ClubMember findClubMemberByClubIdAndUidAndStatusAndType(Long clubId, Integer uid, Integer status, Integer type, String...fields) {
        Criteria criteria = Criteria.where("clubId").is(clubId);
        if (uid != null) {
            criteria.and("uid").is(uid);
        }
        if (status != null) {
            criteria.and("status").is(status);
        }
        if (type != null) {
            criteria.and("type").is(type);
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, ClubMember.class);
    }

    /**
     * 查询 俱乐部 成员
     * @param clubId 俱乐部编号
     * @param type 类型：创建者、成员
     * @param fields 列
     * @return 俱乐部成员信息
     */
    public List<ClubMember> findClubMemberByClubIdByType(Long clubId, Integer type, String...fields) {
        Criteria criteria = Criteria.where("clubId").is(clubId).and("status").is(Club.STATUS_NORMAL);
        if (type != null) {
            criteria.and("type").is(type);
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, ClubMember.class);
    }

    /**
     * 更新 成员信息
     * @param clubId 俱乐部编号
     * @param uid 成员编号
     * @param update 更新信息
     */
    public void updateClubMemberByClubIdAndUid(Long clubId, Integer uid, Update update) {
        Query query = new Query(Criteria.where("clubId").is(clubId).and("uid").is(uid));
        getRoutingMongoOps().updateFirst(query, update, ClubMember.class);
    }


    /**
     * 通过用户编号和俱乐部编号查询俱乐部成员信息
     * @param clubId 俱乐部编号
     * @param uid 用户编号
     */
    public ClubMember findClubMemberByClubIdAndUid(Long clubId, Integer uid, String...fields) {
        Query query = new Query(Criteria.where("clubId").is(clubId).and("uid").is(uid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, ClubMember.class);
    }

    /**
     * 通过主键修改俱乐部成员信息
     * @param id 俱乐部成员编号
     * @param update 更新信息
     */
    public void updateClubMemberById(Long id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, ClubMember.class);
    }

    /**
     * 更新 俱乐部 成员信息
     * @param clubId 俱乐部编号
     * @param update 更新信息
     */
    public void updateClubMemberByClubId(Long clubId, Update update) {
        Query query = new Query(Criteria.where("clubId").is(clubId).and("status").is(ClubMember.STATUS_NORMAL));
        getRoutingMongoOps().updateMulti(query, update, ClubMember.class);
    }

    /**
     * 更新 成员信息 返回 修改后的信息
     * @param clubId 俱乐部编号
     * @param uid 成员编号
     * @param update 更新信息
     * @return 更新后信息
     */
    public ClubMember findAndModifyNewByClubIdAndUid(Long clubId, Integer uid, Update update) {
        Query query = new Query(Criteria.where("clubId").is(clubId).and("uid").is(uid).and("status").is(ClubMember.STATUS_NORMAL));
        return getRoutingMongoOps().findAndModify(query, update, FIND_AND_MODIFY_OPTIONS_RETURN_NEW,ClubMember.class);
    }

}
