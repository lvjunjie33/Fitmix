package com.business.app.clubRanking;

import com.business.core.entity.Page;
import com.business.core.entity.club.ClubRanking;
import com.business.core.entity.club.ClubUserRunStat;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by sin on 2015/11/24.
 */
@Repository
public class ClubRankingDao extends BaseMongoDaoSupport {

    /**
     * 更新 俱乐部 排行榜信息
     *
     * @param id 编号
     * @param update 更新信息
     */
    public void updateClubRankingById(long id, Update update) {
        updateEntityFieldsById(ClubRanking.class, id, update);
    }

    /**
     * 查询 俱乐部 排行榜信息 根据 distance 倒序排序
     *
     * @param clubId 俱乐部编号
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param fields 列
     * @return 俱乐部信息
     */
    public List<ClubRanking> findClubRankingByClubIdAndTypeAndAddTimeForAddTimeToDesc(long clubId, int type, long beginTime, long endTime, String...fields) {
        Query query = new Query(Criteria.where("clubId").is(clubId).and("type").is(type).and("addTime").gte(beginTime).lte(endTime));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, ClubRanking.class);
    }

    /**
     * 查询 俱乐部 排行榜信息 根据 distance 倒序排序
     *
     * @param clubId 俱乐部编号
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param fields 列
     * @return 俱乐部信息
     */
    public ClubRanking findClubRankingByClubIdAndTypeAndAddTimeForAddTimeToDesc2(long clubId, int type, long beginTime, long endTime, String...fields) {
        Query query = new Query(Criteria.where("clubId").is(clubId).and("type").is(type).and("addTime").gte(beginTime).lte(endTime));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, ClubRanking.class);
    }


    ///
    /// ClubUserRunStat 俱乐部用户运动 统计

    /**
     * 俱乐部用户统计
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param type 类型
     * @param fields 列
     * @return 俱乐部用户统计 信息
     */
    public List<ClubUserRunStat> findClubUserRunStatByUidListAndTypeAndAddTimeForDistanceToDesc
    (Collection<Integer> uidList, int type, long beginTime, long endTime, String...fields) {
        Query query = new Query(Criteria.where("uid").in(uidList).
                and("type").is(type).
                and("addTime").gte(beginTime).lte(endTime));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "distance"));
        return getRoutingMongoOps().find(query, ClubUserRunStat.class);
    }

    /**
     * 俱乐部用户数据 统计
     *
     * @param page 分页
     * @param fields 列
     */
    public void findPageClubUserStat(Page<ClubUserRunStat> page, String...fields) {
        LinkedHashMap filter = page.getFilter();
        Criteria criteria = new Criteria();

        if (filter.containsKey("type")) {
            criteria.and("type").is(filter.get("type"));
        }
        if (filter.containsKey("uidSet")) {
            criteria.and("uid").in((Set<Integer>) filter.get("uidSet"));
        }
        if (filter.containsKey("beginTime") && filter.containsKey("endTime")) {
            criteria.and("addTime").gte(filter.get("beginTime")).lte(filter.get("endTime"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "distance"));
        findEntityPage(ClubUserRunStat.class, page, query, fields);
    }
}
