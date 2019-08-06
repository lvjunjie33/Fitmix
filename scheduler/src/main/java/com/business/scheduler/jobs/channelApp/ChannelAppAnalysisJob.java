package com.business.scheduler.jobs.channelApp;

import com.business.core.constants.Constants;
import com.business.core.entity.channel.ChannelAppAnalysis;
import com.business.core.entity.channel.ChannelAppDownload;
import com.business.core.entity.channel.ChannelAppStatistics;
import com.business.core.operations.channelApp.ChannelAppCoreDao;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.scheduler.base.BaseJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by fenxio on 2016/5/16.
 *
 * 渠道统计分析任务
 *
 */
@Service
public class ChannelAppAnalysisJob extends BaseJob {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChannelAppCoreDao channelAppCoreDao;

    @Scheduled(cron = "0 0 3 * * ?")
    public void execute() {
        long now = System.currentTimeMillis();
        logger.info("CPC 统计 开始...");
        Long date = DateUtil.getDayBegin(DateUtil.getDayBefore(new Date())).getTime();
        //页面统计
        Map<Integer,Long> uvMap = getChannelAppAnalysisResult("uv");
        Map<Integer,Long> pvMap = getChannelAppAnalysisResult("pv");
        List<ChannelAppAnalysis> clickList = new ArrayList<>();
        for(Integer channel : uvMap.keySet()) {
            ChannelAppAnalysis c = new ChannelAppAnalysis();
            c.setAddTime(System.currentTimeMillis());
            c.setDate(date);
            c.setUV(uvMap.get(channel));
            c.setChannelId(channel);
            c.setType(ChannelAppAnalysis.CLICK_TYPE);

            if(pvMap.containsKey(channel)) {
                c.setPV(pvMap.get(channel));
            } else {
                c.setPV(0L);
            }
            clickList.add(c);
        }

        channelAppCoreDao.insetList(clickList);

        //下载统计
        Map<Integer, Map<String, Long>> downloadMap = getDownloadAnalysisResult();
        List<ChannelAppAnalysis> downloadList = new ArrayList<>();
        for(Integer channel : downloadMap.keySet()) {
            ChannelAppAnalysis c = new ChannelAppAnalysis();
            c.setAddTime(System.currentTimeMillis());
            c.setDate(date);
            c.setChannelId(channel);
            c.setType(ChannelAppAnalysis.DOWNLOAD_TYPE);
            c.setIosDownloadCount(downloadMap.get(channel).get(Constants.DOWNLOAD_TYPE_IOS));
            c.setAndroidDownloadCount(downloadMap.get(channel).get(Constants.DOWNLOAD_TYPE_ANDROID));
            downloadList.add(c);
        }
        channelAppCoreDao.insetList(downloadList);


        logger.info("CPC 统计 结束...消耗 {} 毫秒!", System.currentTimeMillis() - now);
    }


    /**
     * 统计昨天每个渠道 UV/PV
     * @param type 类型 uv/pv
     * @return map: key 渠道值，value uv/pv 总数
     */
    public Map<Integer,Long> getChannelAppAnalysisResult(String type) {

        Map<Integer,Long> result = new HashMap<>();
        //1.获取昨天所有统计数据
        Map<String,Object> map = new HashMap<>();
        Date yesterday = DateUtil.getDayBefore(new Date());
        map.put("startTime", DateUtil.getDayBegin(yesterday).getTime());
        map.put("endTime", DateUtil.getDayEnd(yesterday).getTime());
        if(type.equals("pv")) {
            map.put("openStatus", ChannelAppStatistics.OPENSTATUS_SUCCESS);
        }
        List<ChannelAppStatistics> list = channelAppCoreDao.getChannelAppStatisticsByAddTimeAndOpenStatus(map);
        //2.按渠道值 分组
        Map<Integer, List<ChannelAppStatistics>> channelMap = CollectionUtil.buildMultimap(list, Integer.class, ChannelAppStatistics.class, "channelId");
        //3.统计有效UV(同一个IP 4个小时内 只算一次)
        for(Integer key : channelMap.keySet()) {
            Long uv = 0L;
            List<ChannelAppStatistics> channelList = channelMap.get(key);
            // 按 IP 分组
            Map<String, List<ChannelAppStatistics>> ipMap = CollectionUtil.buildMultimap(channelList, String.class, ChannelAppStatistics.class, "ip");
            for(String ip : ipMap.keySet()) {
                List<ChannelAppStatistics> ipList = ipMap.get(ip);
                if(type.equals("uv")) {
                    //如果 ipList 长度 是1,则直接统计, 否则去除无效 UV
                    if(ipList.size() == 1) {
                        uv++;
                    } else {
                        Long validIpCount = getValidIpCount(ipList);
                        uv += validIpCount;
                    }
                } else if(type.equals("pv")) {
                    //pv 不用去重可以直接统计
                    uv += ipList.size();
                }


            }
            result.put(key,uv);
        }
        return result;
    }

    /**
     * 统计昨天下载 信息
     * @return  Map<Integer,Map<String,Long>> 第一个key 渠道号，第二个key 下载类型，value 下载数
     */
    public Map<Integer, Map<String, Long>> getDownloadAnalysisResult() {
        Map<Integer, Map<String, Long>> result = new HashMap<>();
        //1.获取昨天所有统计数据
        Map<String,Object> map = new HashMap<>();
        Date yesterday = DateUtil.getDayBefore(new Date());
        map.put("startTime", DateUtil.getDayBegin(yesterday).getTime());
        map.put("endTime", DateUtil.getDayEnd(yesterday).getTime());
        List<ChannelAppDownload> list = channelAppCoreDao.getChannelAppDownloadByAddTime(map);
        //2.按渠道值 分组
        Map<Integer, List<ChannelAppDownload>> channelMap = CollectionUtil.buildMultimap(list, Integer.class, ChannelAppDownload.class, "channelId");
        //3.统计有效UV(同一个IP 4个小时内 只算一次)
        for(Integer key : channelMap.keySet()){
            Long iosDownloadCount = 0L;
            Long androidDownloadCount = 0L;
            List<ChannelAppDownload> channelList = channelMap.get(key);
            // 按 IP 分组
            Map<String, List<ChannelAppDownload>> ipMap = CollectionUtil.buildMultimap(channelList, String.class, ChannelAppDownload.class, "ip");
            for(String ip : ipMap.keySet()) {
                List<ChannelAppDownload> ipList = ipMap.get(ip);
                //如果 ipList 长度 是1,则直接统计, 否则去除无效 数据
                if(ipList.size() == 1) {
                    if(ipList.get(0).getType().equals(Constants.DOWNLOAD_TYPE_ANDROID)) {
                        androidDownloadCount++;
                    } else if(ipList.get(0).getType().equals(Constants.DOWNLOAD_TYPE_IOS)) {
                        iosDownloadCount++;
                    }
                } else {
                    Map<String, Long> validMap = getValidDownloadCountCount(ipList);
                    androidDownloadCount += validMap.get(Constants.DOWNLOAD_TYPE_ANDROID);
                    iosDownloadCount += validMap.get(Constants.DOWNLOAD_TYPE_IOS);
                }

            }
            Map<String, Long> downloadMap = new HashMap<>();
            downloadMap.put(Constants.DOWNLOAD_TYPE_ANDROID,androidDownloadCount);
            downloadMap.put(Constants.DOWNLOAD_TYPE_IOS,iosDownloadCount);
            result.put(key, downloadMap);
        }
        return result;
    }


    /**
     * 获取有效IP总数
     * @param ipList 渠道统计列表
     * @return
     */
    public Long getValidIpCount(List<ChannelAppStatistics> ipList) {
        Long result = 1L;
        Long indexTime = ipList.get(0).getAddTime();
        for(int i = 1; i < ipList.size(); i++) {
            if((ipList.get(i).getAddTime() - indexTime)/1000/60/60 >= 4) {
                result++;
                indexTime = ipList.get(i).getAddTime();
            }
        }
        return result;
    }

    /**
     * 获取有效下载数据
     * @param list
     * @return
     */
    public Map<String,Long> getValidDownloadCountCount(List<ChannelAppDownload> list) {
        Map<String,Long> map = new HashMap<>();
        Long iosCount = 0L;
        Long androidCount = 0L;
        Map<String, List<ChannelAppDownload>> typeMap = CollectionUtil.buildMultimap(list, String.class, ChannelAppDownload.class, "type");

        if(typeMap.containsKey(Constants.DOWNLOAD_TYPE_ANDROID)) {
            androidCount = 1L;
            List<ChannelAppDownload> androidList = typeMap.get(Constants.DOWNLOAD_TYPE_ANDROID);
            Long indexTime = androidList.get(0).getAddTime();
            for(int i = 1; i < androidList.size(); i++) {
                if((androidList.get(i).getAddTime() - indexTime) / 1000 / 60 / 60 >= 4){
                    androidCount++;
                    indexTime = androidList.get(i).getAddTime();
                }
            }
        }

        if(typeMap.containsKey(Constants.DOWNLOAD_TYPE_IOS)) {
            iosCount = 1L;
            List<ChannelAppDownload> iosList = typeMap.get(Constants.DOWNLOAD_TYPE_IOS);
            Long indexTime = iosList.get(0).getAddTime();
            for(int i = 1; i < iosList.size(); i++){
                if((iosList.get(i).getAddTime() - indexTime)/ 1000 / 60 / 60 >= 4){
                    iosCount++;
                    indexTime = iosList.get(i).getAddTime();
                }
            }
        }
        map.put(Constants.DOWNLOAD_TYPE_ANDROID, androidCount);
        map.put(Constants.DOWNLOAD_TYPE_IOS, iosCount);
        return map;
    }

}
