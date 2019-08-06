package com.business.app.mix;

import com.business.core.constants.DicConstants;
import com.business.core.entity.Page;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixDetail;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sin on 15/4/13.
 */
@Repository
public class MixDao extends BaseMongoDaoSupport {


    /**
     * scene 场景分页 mix
     * @param scene 场景
     * @param genre 曲风
     * @param limit 每页数量
     * @param index 第几页
     * @param sortType 类型（1、精选 2、热门 3、新歌）
     * @param fields 列
     * @return mix 数据
     */
    public List<Mix> findMixByScenePage(String lan,Integer scene, Integer genre, int limit, int index, Integer sortType, String... fields) {
        Criteria criteria = Criteria.where("state").is(Mix.STATE_2);
        if (genre != null) {
            criteria.and("genre").is(genre);
        }
        if (scene != null) {
            criteria.and("scene").is(scene);
        }

        Query query = new Query(criteria);
        includeFields(query, fields);
        index = index + 1 < 1 ? 0 : limit * index;
        query.skip(index).limit(limit);

        // 排序规则 （1、精选 2、热门 3、新歌）
        switch (sortType) {
            case 1:
                query.with(new Sort(Sort.Direction.DESC, "sort"));
                break;
            case 2:
                query.with(new Sort(Sort.Direction.DESC, "downloadCount"));
                query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
                break;
            case 3:
                query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
                break;
            default:
                query.with(new Sort(Sort.Direction.DESC, "sort"));
                query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
                break;
        }
        List<Mix> list=getRoutingMongoOps().find(query, Mix.class);
        //2018-08-31 查询歌曲名称和简介多语言
        for(int i=0;i<list.size();i++){
            list.get(i).setIntroduce("");
            list.get(i).setName("");
            MixDetail mixDetails= findMixDetailCommon(lan,list.get(i).getId());
            if(mixDetails!=null){
                list.get(i).setIntroduce(mixDetails.getIntroduce());
                list.get(i).setName(mixDetails.getName());
            }
        }
//        return getRoutingMongoOps().find(query, Mix.class);
        return list;
    }


    /**
     * scene 场景分页 mix
     * @param type 分类
     * @param scene 场景
     * @param page 分页
     * @param fields 列
     * @return mix 数据
     */
    public void findMixByScenePage(String lan,Integer type, Integer scene, Page<Mix> page, String... fields) {
        Criteria criteria = new Criteria();
        if(null != type && type == Mix.TYPE_RADIO) {
            criteria.and("state").is(Mix.STATE_2).and("scene").is(scene).and("type").is(type);
        } else {
            criteria.and("state").is(Mix.STATE_2).and("scene").is(scene).and("type").in(Mix.TYPE_SPORT, null);
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
        findEntityPage(Mix.class, page, query, fields);
        //2018-08-31 查询歌曲名称和简介多语言
        for(int i=0;i<page.getResult().size();i++){
            page.getResult().get(i).setIntroduce("");
            page.getResult().get(i).setName("");
            MixDetail mixDetails= findMixDetailCommon(lan,page.getResult().get(i).getId());
            if(mixDetails!=null){
                page.getResult().get(i).setIntroduce(mixDetails.getIntroduce());
                page.getResult().get(i).setName(mixDetails.getName());
            }
        }
    }

    /**
     * scene 场景分页 mix 重新排序
     * @param type 分类
     * @param scene 场景
     * @param page 分页
     * @param ids 编号
     * @param sortType 排序方式
     * @param fields 列
     * @return mix 数据
     */
    public void findMixByScenePageAndSort(String lan,Integer type, Integer scene, Page<Mix> page, Collection<Integer> ids, Integer sortType, String ... fields) {
        Criteria criteria = new Criteria();
        if(null != type && type == Mix.TYPE_RADIO) {
            criteria.and("state").is(Mix.STATE_2).and("scene").is(scene).and("type").is(type);
        } else {
            criteria.and("state").is(Mix.STATE_2).and("scene").is(scene).and("type").in(Mix.TYPE_SPORT, null).and("id").nin(ids);
        }
        Query query = new Query(criteria);
        // 排序规则 （1、最新 2、最热）
        switch (sortType) {
            case 1:
                query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
                break;
            case 2:
                query.with(new Sort(Sort.Direction.DESC, "downloadCount"));
                break;
            default:
                query.with(new Sort(Sort.Direction.DESC, "sort", "shelvesTime"));
                break;
        }
        findEntityPage(Mix.class, page, query, fields);
        //2018-08-31 查询歌曲名称和简介多语言
        for(int i=0;i<page.getResult().size();i++){
            page.getResult().get(i).setIntroduce("");
            page.getResult().get(i).setName("");
            MixDetail mixDetails= findMixDetailCommon(lan,page.getResult().get(i).getId());
            if(mixDetails!=null){
                page.getResult().get(i).setIntroduce(mixDetails.getIntroduce());
                page.getResult().get(i).setName(mixDetails.getName());
            }
        }
    }


    /**
     * genre 曲风分页。mix
     * @param genreParent 曲风
     * @param limit 每页数量
     * @param index 分页PageNO
     * @param fields 列
     * @return List 曲风分页
     */
    public List<Mix> findMixByGenreParentPage(String lan,Integer genreParent, int limit, int index, String... fields) {
        Criteria criteria = Criteria.where("state").is(Mix.STATE_2);
        // TODO DicConstants 修改成 [系统参数]
        switch (genreParent) {
            case 1:
                criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_POP_CHILDREN);
                break;
            case 2:
                criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_ROCK_CHILDREN);
                break;
            case 3:
                criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_ELECTRONIC_CHILDREN);
                break;
            case 4:
                criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_HIP_HOP_CHILDREN);
                break;
            case 5:
                criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_JAZZ_CHILDREN);
                break;
            case 6:
                criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_CLASSICAL_CHILDREN);
                break;
            case 7:
                criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_WORLD_MUSIC_CHILDREN);
                break;
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        index = index + 1 < 1 ? 0 : limit * index;
        query.skip(index).limit(limit);
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
        //2018-08-31 查询歌曲名称和简介多语言
        List<Mix> list=getRoutingMongoOps().find(query, Mix.class);
        for(int i=0;i<list.size();i++){
            list.get(i).setIntroduce("");
            list.get(i).setName("");
            MixDetail mixDetails= findMixDetailCommon(lan,list.get(i).getId());
            if(mixDetails!=null){
                list.get(i).setIntroduce(mixDetails.getIntroduce());
                list.get(i).setName(mixDetails.getName());
            }
        }
//        return getRoutingMongoOps().find(query, Mix.class);
        return list;
    }

    /**
     * mix 歌曲搜索
     * @param dicBpm 字典bpm类型
     * @param scene 场景
     * @param genre 曲风
     * @param dicTrackLength 字典歌曲长度类型
     * @param limit 每页数量
     * @param index 分页index
     * @param search 搜索内容， bpm,scene,genre,trackLength,name
     * @return Mix集合
     */
    public List<Mix> searchMix (String lan,Integer dicBpm, Integer scene, Integer genre, Integer dicTrackLength, Integer limit, Integer index, String search) {
        Criteria criteria = new Criteria();
        criteria.and("state").is(Mix.STATE_2);
        ///
        /// 选择栏条件
        // bpm
        if (null != dicBpm) {
            switch (dicBpm) {
                case 1: // <120
                    criteria.andOperator(Criteria.where("bpmVariableBegin").lt(120), Criteria.where("bpmVariableEnd").lt(120));
                    break;
                case 2: // 120-129
                    criteria.and("bpmVariableBegin").gte(120).and("bpmVariableEnd").lte(129);
                    break;
                case 3: // 130-139
                    criteria.and("bpmVariableBegin").gte(130).and("bpmVariableEnd").lte(139);
                    break;
                case 4: // 140-149
                    criteria.and("bpmVariableBegin").gte(140).and("bpmVariableEnd").lte(149);
                    break;
                case 5: // 150-159
                    criteria.and("bpmVariableBegin").gte(150).and("bpmVariableEnd").lte(159);
                    break;
                case 6: // 160-169
                    criteria.and("bpmVariableBegin").gte(160).and("bpmVariableEnd").lte(169);
                    break;
                case 7: // 170-179
                    criteria.and("bpmVariableBegin").gte(170).and("bpmVariableEnd").lte(179);
                    break;
                case 8: // >=180
                    criteria.andOperator(Criteria.where("bpmVariableBegin").gte(180), Criteria.where("bpmVariableEnd").gte(180));
                    break;
            }
        }


        // 时长
        if (null != dicTrackLength) {
            switch (dicTrackLength) {
                case 1: // <30分钟
                    criteria.and("trackLength").lt(30 * 60);
                    break;
                case 2: // 30-59分钟
                    criteria.and("trackLength").gte(30 * 60).lte(59 * 60);
                    break;
                case 3: // 60-89分钟
                    criteria.and("trackLength").gte(60 * 60).lte(89 * 60);
                    break;
                case 4: // 90-119分钟
                    criteria.and("trackLength").gte(90 * 60).lte(119 * 60);
                    break;
                case 5: // >=120分钟
                    criteria.and("trackLength").gte(120 * 60);
                    break;
            }
        }

        if (scene != null) {
            criteria.and("scene").is(scene);
        }
        if (genre != null) {
            // 处理 genre 分类关系.
            switch (genre) {
                case 1:
                    criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_POP_CHILDREN);
                    break;
                case 2:
                    criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_ROCK_CHILDREN);
                    break;
                case 3:
                    criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_ELECTRONIC_CHILDREN);
                    break;
                case 4:
                    criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_HIP_HOP_CHILDREN);
                    break;
                case 5:
                    criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_JAZZ_CHILDREN);
                    break;
                case 6:
                    criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_CLASSICAL_CHILDREN);
                    break;
                case 7:
                    criteria.and("genre").in(DicConstants.MIX_GENRE_PARENT_WORLD_MUSIC_CHILDREN);
                    break;
            }
        }

        // search （输入框条件）
        if (!StringUtils.isEmpty(search)) {
            Criteria[] criteriaArr;
            if (NumberUtils.isNumber(search)) {
                criteriaArr = new Criteria[3];
                criteriaArr[0] = Criteria.where("trackLength").is(Math.floor(Double.valueOf(search)) * 60);
                criteriaArr[1] = Criteria.where("bpmVariableBegin").gte(Math.floor(Double.valueOf(search))).and("bpmVariableEnd").lte(Math.floor(Double.valueOf(search)));
                //2018-08-31屏蔽查询歌曲主表名称
//                criteriaArr[2] = Criteria.where("name").regex(String.format(".*(?i)%s.*", search));
                criteriaArr[2] = Criteria.where("name").regex(String.format(".*(?i)%s.*", "---"));
            }
            else {
                criteriaArr = new Criteria[1];
                //2018-08-31屏蔽查询歌曲主表名称
//                criteriaArr[0] = Criteria.where("name").regex(String.format(".*(?i)%s.*", search));
                criteriaArr[0] = Criteria.where("name").regex(String.format(".*(?i)%s.*", "---"));
            }
            criteria.orOperator(criteriaArr);
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "sort", "_id"));

        index = index + 1 < 1 ? 0 : limit * index;
        query.skip(index).limit(limit);
        //2018-08-31 查询歌曲名称和简介多语言
        List<Mix> list=getRoutingMongoOps().find(query, Mix.class);
        List<MixDetail> mixDetailList= findMixDetailCommonSearch(lan,search);
        List<Integer> integelist=new ArrayList<Integer>();
        if(mixDetailList!=null){
            for(int s=0;s<mixDetailList.size();s++){
                integelist.add(mixDetailList.get(s).getMixid());
            }
        }
        Query querys = new Query(new Criteria().where("id").in(integelist));
        List<Mix> detailQueryList=getRoutingMongoOps().find(querys, Mix.class);
        list.addAll(detailQueryList);
        for(int i=0;i<list.size();i++){
            list.get(i).setIntroduce("");
            list.get(i).setName("");
            MixDetail mixDetails= findMixDetailCommon(lan,list.get(i).getId());
            if(mixDetails!=null){
                list.get(i).setIntroduce(mixDetails.getIntroduce());
                list.get(i).setName(mixDetails.getName());
            }
        }
//        return getRoutingMongoOps().find(query, Mix.class);
        return list;
    }

    public void updateById(Integer id, Update update) {
        Query query = Query.query(Criteria.where("_id").is(id).and("state").is(Mix.STATE_2));
        getRoutingMongoOps().updateFirst(query, update, Mix.class);
    }

    public void updateByIds (Collection<Integer> ids, Update update) {
        Query query = Query.query(Criteria.where("_id").in(ids).and("state").is(Mix.STATE_2));
        getRoutingMongoOps().updateMulti(query, update, Mix.class);
    }

    public Mix findMixById (String lan,Integer id, String... fields) {
        Query query = new Query(Criteria.where("id").is(id).and("state").is(Mix.STATE_2));
        includeFields(query, fields);
        //2018-08-31
        Mix mix=getRoutingMongoOps().findOne(query, Mix.class);
        if(mix!=null) {
            mix.setIntroduce("");
            mix.setName("");
            MixDetail mixDetails = findMixDetailCommon(lan, mix.getId());
            if (mixDetails != null) {
                mix.setIntroduce(mixDetails.getIntroduce());
                mix.setName(mixDetails.getName());
            }
        }
//        return getRoutingMongoOps().findOne(query, Mix.class);
        return mix;
    }

    public List<Mix> findMixByIds (String lan,Collection<Integer> ids, String...fields) {
        Query query = new Query(Criteria.where("state").is(Mix.STATE_2).and("id").in(ids));
        includeFields(query, fields);
        //2018-08-31 查询歌曲名称和简介多语言
        List<Mix> list=getRoutingMongoOps().find(query, Mix.class);
        for(int i=0;i<list.size();i++){
            list.get(i).setIntroduce("");
            list.get(i).setName("");
            MixDetail mixDetails= findMixDetailCommon(lan,list.get(i).getId());
            if(mixDetails!=null){
                list.get(i).setIntroduce(mixDetails.getIntroduce());
                list.get(i).setName(mixDetails.getName());
            }
        }
//        return getRoutingMongoOps().find(query, Mix.class);
        return list;
    }

    /**
     * 根据语言类型查询歌曲名称和简介公共方法
     * */
    public List<MixDetail> findMixDetailCommonSearch (String lan,String search, String...fields) {
        Query query = new Query(Criteria.where("name").regex(String.format(".*(?i)%s.*", search)).and("introduce_lan").is(lan));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, MixDetail.class);
    }

    /**
     * 根据语言类型查询歌曲名称和简介公共方法
     * */
    public MixDetail findMixDetailCommon (String introduce_lan ,Integer mixid, String...fields) {
        Query query = new Query(Criteria.where("introduce_lan").is(introduce_lan).and("mixid").is(mixid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, MixDetail.class);
    }
    /**
     * 获取首个MIX信息
     * @param scene 场景
     * @param type 类型
     * @param fields
     * @return
     */
    public Mix findMixBySceneFirst(String lan,Integer scene, Integer type, String...fields) {
        if(null != type && type == Mix.TYPE_RADIO) {
            Criteria.where("state").is(Mix.STATE_2).and("scene").is(scene).and("type").is(type);
        } else {
            Criteria.where("state").is(Mix.STATE_2).and("scene").is(scene).and("type").in(Mix.TYPE_SPORT, null);
        }
        Query query = new Query();
        includeFields(query, fields);
        // 排序规则 （1、精选 2、热门 3、新歌）
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "downloadCount"));
        query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
        //2018-08-31
        Mix mix=getRoutingMongoOps().findOne(query, Mix.class);
        if(mix!=null) {
            mix.setIntroduce("");
            mix.setName("");
            MixDetail mixDetails = findMixDetailCommon(lan, mix.getId());
            if (mixDetails != null) {
                mix.setIntroduce(mixDetails.getIntroduce());
                mix.setName(mixDetails.getName());
            }
        }
//        return getRoutingMongoOps().findOne(query, Mix.class);
        return mix;
    }

    /**
     * 获取歌曲下载量前十
     * @param fields    mix属性
     * @return 歌曲信息List
     */
    public List<Mix> findMixRankingByDownloadCount(String lan,Integer limit,String...fields){
        Query query = new Query(Criteria.where("state").is(Mix.STATE_2));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "downloadCount")).limit(limit);

        //2018-08-31 查询歌曲名称和简介多语言
        List<Mix> list=getRoutingMongoOps().find(query, Mix.class);
        for(int i=0;i<list.size();i++){
            list.get(i).setIntroduce("");
            list.get(i).setName("");
            MixDetail mixDetails= findMixDetailCommon(lan,list.get(i).getId());
            if(mixDetails!=null){
                list.get(i).setIntroduce(mixDetails.getIntroduce());
                list.get(i).setName(mixDetails.getName());
            }
        }
//        return getRoutingMongoOps().find(query, Mix.class);
        return list;
    }

    /**
     * 获取今日新歌
     * @param shelvesTime 今日时间
     * @param fields   mix属性
     * @return
     */
    public List<Mix> findMixByShelvesTime(String lan,Long shelvesTime, String...fields){
        Query query = new Query(Criteria.where("state").is(Mix.STATE_2).and("shelvesTime").gte(shelvesTime));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
        //2018-08-31 查询歌曲名称和简介多语言
        List<Mix> list=getRoutingMongoOps().find(query, Mix.class);
        for(int i=0;i<list.size();i++){
            list.get(i).setIntroduce("");
            list.get(i).setName("");
            MixDetail mixDetails= findMixDetailCommon(lan,list.get(i).getId());
            if(mixDetails!=null){
                list.get(i).setIntroduce(mixDetails.getIntroduce());
                list.get(i).setName(mixDetails.getName());
            }
        }
//        return getRoutingMongoOps().find(query, Mix.class);
        return list;
    }

    /**
     * 获取场景前几个数据
     * @param scene 场景
     * @param type 类型
     * @param limit 个数
     * @return
     */
    public List<Mix> findMixListBySceneAndType(String lan,Integer scene, Integer type, Integer limit) {
        Criteria criteria = new Criteria();
        if(null != type && type == Mix.TYPE_RADIO) {
            criteria.and("state").is(Mix.STATE_2).and("scene").is(scene).and("type").is(type).and("sort").gt(0);
        } else {
            criteria.and("state").is(Mix.STATE_2).and("scene").is(scene).and("type").in(Mix.TYPE_SPORT, null).and("sort").gt(0);
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.with(new Sort(Sort.Direction.DESC, "shelvesTime"));
        query.limit(limit);
        //2018-08-31 查询歌曲名称和简介多语言
        List<Mix> list=getRoutingMongoOps().find(query, Mix.class);
        for(int i=0;i<list.size();i++){
            list.get(i).setIntroduce("");
            list.get(i).setName("");
            MixDetail mixDetails= findMixDetailCommon(lan,list.get(i).getId());
            if(mixDetails!=null){
                list.get(i).setIntroduce(mixDetails.getIntroduce());
                list.get(i).setName(mixDetails.getName());
            }
        }
//        return getRoutingMongoOps().find(query, Mix.class);
        return list;
    }


    /**
     * 查询今日推荐的歌曲
     *
     * @param currentDate 今天的时间
     * @param fields 查询的字段
     */
    public List<Mix> findMixByRecommendDate(String lan,Long currentDate, String...fields) {
        Query query = Query.query(Criteria.where("recommendBeginDate").lte(currentDate).and("recommendEndDate").gte(currentDate));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "collectNumber"));
        //2018-08-31 查询歌曲名称和简介多语言
        List<Mix> list=getRoutingMongoOps().find(query, Mix.class);
        for(int i=0;i<list.size();i++){
            list.get(i).setIntroduce("");
            list.get(i).setName("");
            MixDetail mixDetails= findMixDetailCommon(lan,list.get(i).getId());
            if(mixDetails!=null){
                list.get(i).setIntroduce(mixDetails.getIntroduce());
                list.get(i).setName(mixDetails.getName());
            }
        }
//        return getRoutingMongoOps().find(query, Mix.class);
        return list;
    }

}
