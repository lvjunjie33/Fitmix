package com.business.core.operations.club;

import com.business.core.entity.club.ClubMember;
import com.business.core.entity.club.ClubRanking;
import com.business.core.entity.club.ClubUserRunStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by edward on 2016/7/20.
 */
@Repository
public class ClubCoreDao extends BaseMongoDaoSupport{

    /**
     * 查询 成员 所在的俱乐部
     * <p>
     *     type：注意 如果为空 先个人创建的 俱乐部 排第一，再按时间倒序
     * </p>
     * @param uid 用户编号
     * @param type 类型：创建者 或 成员
     * @param fields 列
     * @return 所在俱乐部信息
     */
    public List<ClubMember> findClubMemberByUidAndType(Integer uid, Integer type, String...fields) {
        Criteria criteria = Criteria.where("uid").is(uid).and("status").is(ClubMember.STATUS_NORMAL);
        if (type != null) {
            criteria.and("type").is(type);
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.ASC, "type"));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, ClubMember.class);
    }

    /**
     * 根据时间 查询 俱乐部用户运动
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param fields 列
     * @return 俱乐部信息
     */
    public ClubUserRunStat findOneClubUserRunStatByUidAndTypeAndAddTime(int uid, int type, long beginTime, long endTime, String...fields) {
        Query query = new Query(Criteria.where("uid").is(uid).
                and("type").is(type).
                and("addTime").gte(beginTime).lte(endTime));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, ClubUserRunStat.class);
    }

    /**
     * 俱乐部用户运动 添加
     *
     * @param clubUserRunStat 俱乐部用户运动
     */
    public void insertClubUserRunStat(ClubUserRunStat clubUserRunStat) {
        insertToMongo(clubUserRunStat);
    }

    /**
     * 更新 俱乐部用户统计
     *
     * @param id 编号
     * @param update 更新信息
     */
    public void updateClubUserRunStatById(long id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, ClubUserRunStat.class);
    }

    /**
     * 查询 俱乐部 排行榜数据
     *
     * @param clubId 俱乐部编号
     * @param fields 列
     * @return 排行榜数据
     */
    public ClubRanking findClubRankingByClubIdAndTypeAndAddTime(long clubId, int type, long beginTime, long endTime, String...fields) {
        Criteria criteria = Criteria.where("clubId").is(clubId).and("type").is(type).and("addTime").gte(beginTime).lte(endTime);
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, ClubRanking.class);
    }

    /**
     * 添加 俱乐部 排行榜数据
     *
     * @param clubRanking 排行榜数据
     */
    public void insertClubRanking(ClubRanking clubRanking) {
        insertToMongo(clubRanking);
    }

    /**
     * 更新 俱乐部 排行榜信息
     *
     * @param id 编号
     * @param update 更新信息
     */
    public void updateClubRankingById(long id, Update update) {
        updateEntityFieldsById(ClubRanking.class, id, update);
    }

}
