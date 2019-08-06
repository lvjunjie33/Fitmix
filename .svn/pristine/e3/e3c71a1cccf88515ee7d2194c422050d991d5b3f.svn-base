package com.business.work.mix;

import com.business.core.entity.Page;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixDetail;
import com.business.core.entity.mix.RunMix;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
* Created by sin on 15/4/13.
*/
@Repository
public class MixDao extends BaseMongoDaoSupport {

    /**
     * Mix 分业
     *
     * @param page 分业工具
     * @param fields 列
     */
    public void findMixPage (Page<Mix> page, String...fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();
        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }
        if (!StringUtils.isEmpty(filter.get("name"))) {
            criteria.and("name").regex(String.format("^(?i)%s.*$", filter.get("name").toString()));
        }
        if (filter.containsKey("trackLength")) {
            criteria.and("trackLength").is(filter.get("trackLength"));
        }
        if (!StringUtils.isEmpty(filter.get("beginTime")) && StringUtils.isEmpty(filter.get("endTime"))) {
            criteria.and("shelvesTime").gte(page.parseBeginTime("beginTime")).lte(page.parseEndTime("endTime"));
        }
        else if (!StringUtils.isEmpty(filter.get("beginTime"))) {
            criteria.and("shelvesTime").gte(page.parseBeginTime("beginTime"));
        } else if (!StringUtils.isEmpty(filter.get("endTime"))) {
            criteria.and("shelvesTime").lte(page.parseEndTime("endTime"));
        }
        if (filter.containsKey("scene")) {
            criteria.and("scene").in(filter.get("scene"));
        }
        if (filter.containsKey("genre")) {
            criteria.and("genre").in(filter.get("genre"));
        }
        if (filter.containsKey("state")) {
            criteria.and("state").in(filter.get("state"));
        }

        if(filter.containsKey("type")) {
            //// TODO 兼容之前数据，之后去掉
            if(filter.get("type") == Mix.TYPE_SPORT) {
                criteria.and("type").in(Mix.TYPE_SPORT, null);
            } else {
                criteria.and("type").is(filter.get("type"));
            }

        }

        if (!StringUtils.isEmpty(filter.get("customIdentification"))) {
            criteria.and("customIdentification").is(filter.get("customIdentification"));
        }
        Query query = new Query(criteria);
        // 处理排序规则
        if (!StringUtils.isEmpty(filter.get("bpmSort"))) {
            if (filter.get("bpmSort").toString().equals(Page.DESC)) {
                query.with(new Sort(Sort.Direction.DESC, "bpm"));
            }
            else {
                query.with(new Sort(Sort.Direction.ASC, "bpm"));
            }
        }

        if (!StringUtils.isEmpty(filter.get("sortSort"))) {
            if (filter.get("sortSort").toString().equals(Page.DESC)) {
                query.with(new Sort(Sort.Direction.DESC, "sort"));
            }
            else {
                query.with(new Sort(Sort.Direction.ASC, "sort"));
            }
        }

        if (!StringUtils.isEmpty(filter.get("shelvesTimeSort"))) {
            if (filter.get("shelvesTimeSort").toString().equals(Page.DESC)) {
                query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
            }
            else {
                query.with(new Sort(Sort.Direction.ASC, "shelvesTime"));
            }
        }

        if (!StringUtils.isEmpty(filter.get("customIdentificationSort"))) {
            if (filter.get("customIdentificationSort").toString().equals(Page.DESC)) {
                query.with(new Sort(Sort.Direction.DESC, "customIdentification"));
            }
            else {
                query.with(new Sort(Sort.Direction.ASC, "customIdentification"));
            }
        }

        if (!StringUtils.isEmpty(filter.get("downloadSort"))) {
            if (filter.get("downloadSort").toString().equals(Page.DESC)) {
                query.with(new Sort(Sort.Direction.DESC, "downloadCount"));
            }
            else {
                query.with(new Sort(Sort.Direction.ASC, "downloadCount"));
            }
        }

        if (!StringUtils.isEmpty(filter.get("collectSort"))) {
            if (filter.get("collectSort").toString().equals(Page.DESC)) {
                query.with(new Sort(Sort.Direction.DESC, "collectNumber"));
            }
            else {
                query.with(new Sort(Sort.Direction.ASC, "collectNumber"));
            }
        }
        query.with(new Sort(Sort.Direction.ASC, "state"));
        findEntityPage(Mix.class, page, query, fields);
    }

    /**
     * Mix 添加
     *
     * @param mix 对象
     */
    public void insertMix (Mix mix) {
        insertToMongo(mix);
    }

    /**
     * MixDetail 添加
     *
     * @param mixDetail 对象
     */
    public void insertMixDetail (MixDetail mixDetail) {
        insertToMongo(mixDetail);
    }

    /**
     * 更具 Mix id 更新 mix 信息
     *
     * @param id 编号
     * @param update 更新底下
     */
    public void updateMixById (Integer id , Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, Mix.class);
    }


    /**
     * 更具 MixDetail id 更新 MixDetail 信息
     *
     * @param id 编号
     * @param update 更新底下
     */
    public void updateMixDetail (Integer id , Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, MixDetail.class);
    }

    /**
     * 更具 id 查询 Mix
     *
     * @param id
     * @param fields
     * @return
     */
    public Mix findMixById (Integer id, String...fields) {
        Query query = new Query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, Mix.class);
    }

    /**
     * 更具 id 查询 Mix
     *
     * @param ids 编号
     * @param fields 列
     * @return mix 信息
     */
    public List<Mix> findMixByids(Collection<Integer> ids, String...fields) {
        Query query = new Query(Criteria.where("id").in(ids));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Mix.class);
    }

    /**
     * 删除文件信息 mix
     *
     * @param mid 歌曲编号
     */
    public void removeMixByIds (Integer mid) {
        Query query = new Query(Criteria.where("id").is(mid));
        getRoutingMongoOps().remove(query, Mix.class);
    }

    /**
     * 更新所有 Mix 歌曲
     *
     * @param update 更新
     */
    public void updateAll (Update update) {
        Query query = new Query();
        getRoutingMongoOps().updateMulti(query, update, Mix.class);
    }

    public List<Mix> findByMixAlbumsId(Integer maid) {
        Query query = new Query(Criteria.where("mixAlbumsId").is(maid));
        return getRoutingMongoOps().find(query, Mix.class);
    }

    /**
     * 查询 推荐的歌曲列表
     *
     * @param currentDate 当前时间
     */
    public List<Mix> findByRecommend(Long currentDate) {
        Query query = new Query(Criteria.where("recommendEndDate").gte(currentDate));
        query.with(new Sort(Sort.Direction.ASC, "recommendBeginDate"));
        return getRoutingMongoOps().find(query, Mix.class);
    }

    /**
     * 运动音乐管理
     *
     * @param page 分页对象
     * @param fields 查询的字段
     */
    public void runMixPage(Page<RunMix> page, String...fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();
        if (filter.containsKey("id")) {
            criteria.and("id").is(filter.get("id"));
        }
        if (!StringUtils.isEmpty(filter.get("title"))) {
            criteria.and("title").regex(filter.get("title").toString());
        }

        if(filter.containsKey("songStyle")) {
            criteria.and("songStyle").is(filter.get("songStyle"));
        }

        if(filter.containsKey("duration")) {
            criteria.and("duration").is(filter.get("duration"));
        }

        if(filter.containsKey("bBpm")) {
            criteria.and("bpm").gte(filter.get("bBpm")).lte(filter.get("eBpm"));
        }

        Query query = new Query(criteria);

        query.with(new Sort(Sort.Direction.ASC, "energyLevel"));
        findEntityPage(RunMix.class, page, query, fields);
    }

    /**
     * 保存运动音乐
     *
     * @param runMix 运动音乐
     */
    public void runMixAdd(RunMix runMix) {
        insertToMongo(runMix);
    }

    /**
     * 修改运动音乐
     *
     * @param id 编号
     * @param update 修改的内容
     */
    public void runMixModify(Long id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, RunMix.class);
    }

    public RunMix runMixFindById(Long id, String...fields) {
        Query query = Query.query(Criteria.where("id").is(id));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, RunMix.class);
    }

    public void remove() {
        getRoutingMongoOps().remove(new Query(), RunMix.class);
    }

    public void updateRandomVal(Set<Long> ids) {
        getRoutingMongoOps().updateMulti(Query.query(Criteria.where("id").in(ids)), new Update().inc("randomVal", 1), RunMix.class);
    }

    /**
     * 根据 mix customIdentification 查询
     * @param introduce_lan 介绍语言
     * @param mixid 歌曲主表ID
     * @param fields 列
     * @return mix 歌曲
     */
    public MixDetail findMixDetail (String introduce_lan ,Integer mixid, String...fields) {
        Query query = new Query(Criteria.where("introduce_lan").is(introduce_lan).and("mixid").is(mixid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, MixDetail.class);
    }
}
