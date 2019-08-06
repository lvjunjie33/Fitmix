package com.business.app.channelApp;

import com.business.core.entity.channel.BurnFatFighting;
import com.business.core.entity.channel.ChannelApp;
import com.business.core.entity.channel.ChannelAppDownload;
import com.business.core.entity.channel.ChannelAppStatistics;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by fenxio on 2016/5/10.
 */
@Repository
public class ChannelAppDao extends BaseMongoDaoSupport {

    /**
     * 根据ID 查找 channelApp
     */
    public ChannelApp findChannelAppById(Integer id, String ... fields) {
        return findEntityById(ChannelApp.class,id,fields);
    }

    /**
     * 根据渠道ID 查找ChannelApp
     * @param channelId 渠道编号
     * @param fields 查询的字段
     */
    public ChannelApp findChannelAppByChannelId(Integer channelId, String ... fields) {
        Query query = new Query(Criteria.where("channelId").is(channelId));
        return getRoutingMongoOps().findOne(query,ChannelApp.class);
    }

    /**
     * 插入下载统计记录
     * @param channelAppDownload 下载统计信息
     */
    public void channelAppDownloadAdd(ChannelAppDownload channelAppDownload) {
        insertToMongo(channelAppDownload);
    }


    /**
     * 添加 channelAppStatistics
     */
    public void channelAppStatisticsAdd(ChannelAppStatistics channelAppStatistics) {
        insertToMongo(channelAppStatistics);
    }


    /**
     * 更新openStatus状态
     * @param id 渠道编号
     * @param update 修改的内容
     */
    public void modifyOpenStatusById(Integer id, Update update) {
        Query query = Query.query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, ChannelAppStatistics.class);
    }

    /**
     * 新增 燃脂统计
     * @param burnFatFighting
     */
    public void insertBurnFatFighting(BurnFatFighting burnFatFighting) {
        insertToMongo(burnFatFighting);
    }

    public BurnFatFighting findBurnFatFightingByIpAndRandom(String ip, String random) {
        Query query = Query.query(Criteria.where("ip").is(ip).and("random").is(random));
        return getRoutingMongoOps().findOne(query, BurnFatFighting.class);
    }

    public void updateBurnFatFightingByIpAndRandom(BurnFatFighting burnFatFighting) {
        Query query = Query.query(Criteria.where("ip").is(burnFatFighting.getIp()).and("random").is(burnFatFighting.getRandom()));
        Update update = Update.update("type", burnFatFighting.getType());
        if(burnFatFighting.getShareCount() != null) {
            update.inc("shareCount", 1);
        }
        getRoutingMongoOps().updateFirst(query, update, BurnFatFighting.class);
    }

    public Long getCountGroupByType(Integer type) {
        Query query = Query.query(Criteria.where("type").is(type));
        return getRoutingMongoOps().count(query, BurnFatFighting.class);
    }
}
