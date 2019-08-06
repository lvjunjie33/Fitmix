package com.business.core.operations.channelApp;

import com.business.core.entity.channel.ChannelAppAnalysis;
import com.business.core.entity.channel.ChannelAppDownload;
import com.business.core.entity.channel.ChannelAppStatistics;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by fenxio on 2016/5/16.
 */
@Repository
public class ChannelAppCoreDao extends BaseMongoDaoSupport {

    /**
     * 根据时间和打开状态获取 渠道统计列表
     * @param map 条件
     * @return
     */
    public List<ChannelAppStatistics> getChannelAppStatisticsByAddTimeAndOpenStatus(Map<String,Object> map) {
        Criteria criteria = Criteria.where("addTime").gte(map.get("startTime")).lt(map.get("endTime"));
        if(map.containsKey("openStatus")){
            criteria.and("openStatus").is(map.get("openStatus"));
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "addTime"));
        return getRoutingMongoOps().find(query,ChannelAppStatistics.class);
    }

    /**
     * 批量插入 CPC 统计结果
     * @param list 统计结果列表
     */
    public void insetList(List<ChannelAppAnalysis> list){
        getRoutingMongoOps().insert(list, ChannelAppAnalysis.class);
    }

    /**
     * 根据时间获取下载统计列表
     * @param map 条件
     * @return
     */
    public List<ChannelAppDownload> getChannelAppDownloadByAddTime(Map<String, Object> map) {
        Criteria criteria = Criteria.where("addTime").gte(map.get("startTime")).lt(map.get("endTime"));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "addTime"));
        return getRoutingMongoOps().find(query,ChannelAppDownload.class);
    }
}
