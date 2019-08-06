package com.business.work.channelApp;

import com.business.core.entity.Page;
import com.business.core.entity.channel.ChannelApp;
import com.business.core.entity.channel.ChannelAppAnalysis;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.DateUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by fenxio on 2016/5/9.
 */
@Repository
public class ChannelAppDao extends BaseMongoDaoSupport {

    /**
     * 绑定渠道APP 信息
     * @param channelApp
     */
    public void insertChannelApp(ChannelApp channelApp) {
        insertToMongo(channelApp);
    }

    /**
     * 根据id查询 channelApp 信息
     * @param id
     * @param fields
     * @return
     */
    public ChannelApp findChannelAppById(Long id, String... fields) {
        return findEntityById(ChannelApp.class,id,fields);
    }

    /**
     * 根据id 修改channelApp 信息
     * @param id
     * @param update
     */
    public void updateChannelAppById(Long id, Update update) {
        updateEntityFieldsById(ChannelApp.class,id,update);
    }

    /**
     * 获取channelApp 列表
     * @param page  分页信息
     * @param fields
     */
    public void findChannelAppPage(Page<ChannelApp> page, String ... fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (!StringUtils.isEmpty(filter.get("channelId"))) {
            criteria.and("channelId").is(filter.get("channelId"));
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(ChannelApp.class, page, query, fields);
    }

    /**
     * 删除渠道信息
     * @param id 渠道信息编号
     */
    public void channelAppRemove(Integer id) {
        removeEntityById(ChannelApp.class,id);
    }


    /**
     * 获取统计分析列表
     * @param page  分页
     * @param fields
     */
    public void findChannelAnalysisPage(Page<ChannelAppAnalysis> page, String ... fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();

        if (!StringUtils.isEmpty(filter.get("channelId"))) {
            criteria.and("channelId").is(filter.get("channelId"));
        } else {
            Query q = new Query();
            ChannelApp channelApp = getRoutingMongoOps().findOne(q, ChannelApp.class);
            if(channelApp != null){
                criteria.and("channelId").is(channelApp.getChannelId());
                page.getFilter().put("channelId",channelApp.getChannelId());
            }
        }

        if (!StringUtils.isEmpty(filter.get("startTime")) && !StringUtils.isEmpty(filter.get("endTime"))) {
            criteria.and("date").gte(page.parseBeginTime("startTime")).lt(page.parseEndTime("endTime"));
        }

        if(!StringUtils.isEmpty(filter.get("type"))){
            criteria.and("type").is(filter.get("type"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(ChannelAppAnalysis.class, page, query, fields);
    }
}
