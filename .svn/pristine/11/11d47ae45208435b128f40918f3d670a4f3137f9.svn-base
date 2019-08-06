package com.business.app.club;

import com.business.core.entity.club.Club;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by sin on 2015/11/23.
 */
@Repository
public class ClubDao extends BaseMongoDaoSupport {


    /**
     * 添加 俱乐部
     * @param club 俱乐部对象
     */
    public void insertClub(Club club) {
        insertToMongo(club);
    }

    /**
     * 查询 俱乐部 信息
     * @param ids 俱乐部编号
     * @param fields 列
     * @return 俱乐部信息
     */
    public List<Club> findClubByIds(Collection<Long> ids, String...fields) {
        Query query = new Query(Criteria.where("id").in(ids).and("status").is(Club.STATUS_NORMAL));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Club.class);
    }

    /**
     * 查询 俱乐部
     * @param id 编号
     * @param fields 列
     * @return 俱乐部信息
     */
    public Club findClubById(Long id, String...fields) {
        return findEntityById(Club.class, id, fields);
    }

    /**
     * 查询俱乐部 信息
     * @param id 俱乐部编号
     * @param uid 用户编号
     * @param fields 列
     * @return 俱乐部信息
     */
    public Club findClubByIdAndUid(Long id, Integer uid, String...fields) {
        Query query = new Query(Criteria.where("id").is(id).and("uid").is(uid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Club.class);
    }

    /**
     * 查询 用户 加入了那些俱乐部
     * @param uid 用户编号
     * @param fields 列
     * @return 俱乐部信息
     */
    public List<Club> findClubByUid(Integer uid, String...fields) {
        Query query = new Query(Criteria.where("uid").is(uid).and("status").is(Club.STATUS_NORMAL));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Club.class);
    }

    /**
     * 查询 用户 加入了那些俱乐部
     * @param uidCollections 用户编号
     * @param fields 列
     * @return 俱乐部信息
     */
    public List<Club> findClubByUid(Collection<Integer> uidCollections, String...fields) {
        Query query = new Query(Criteria.where("uid").in(uidCollections).and("status").is(Club.STATUS_NORMAL));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Club.class);
    }


    /**
     * 查询 俱乐部
     * <p>
     *     根据 名称确定， 但要根据 uid (用户编号)来确定
     * </p>
     * @param uid 用户编号
     * @param name 名称
     * @param fields 列
     * @return 俱乐部信息
     */
    public Club findClubByUidName(Integer uid, String name, String...fields) {
        Query query = new Query(Criteria.where("uid").is(uid).and("name").is(name));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Club.class);
    }

    /**
     * 查询 并更新
     * @param id 俱乐部编号
     * @param update 更新信息
     * @return 新的信息
     */
    public Club findAndModifyNew(Long id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        return getRoutingMongoOps().findAndModify(query, update, FIND_AND_MODIFY_OPTIONS_RETURN_NEW, Club.class);
    }

    public void updateClubById(Long clubId, Update update) {
        Query query = new Query(Criteria.where("id").is(clubId));
        getRoutingMongoOps().updateFirst(query, update, Club.class);
    }

    public void updateClubByIdAndUid(Long clubId, Integer uid, Update update) {
        Query query = new Query(Criteria.where("id").is(clubId).and("uid").is(uid));
        getRoutingMongoOps().updateFirst(query, update, Club.class);
    }
}
