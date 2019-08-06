package com.business.work.channelApp;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.DicConstants;
import com.business.core.entity.Dictionary;
import com.business.core.entity.Page;
import com.business.core.entity.channel.ChannelApp;
import com.business.core.entity.channel.ChannelAppAnalysis;
import com.business.core.entity.channel.ChannelUserStat;
import com.business.core.entity.user.User;
import com.business.core.sort.SortFactory;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.core.utils.DicUtil;
import com.business.work.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by fenxio on 2016/5/9.
 */
@Service
public class ChannelAppService {

    @Autowired
    private ChannelAppDao channelAppDao;
    @Autowired
    private UserDao userDao;

    /**
     * 绑定渠道APP 信息
     *
     * @param channelApp
     * @return
     */
    public Object[] channelAppAdd(ChannelApp channelApp) {
        channelApp.setAddTime(System.currentTimeMillis());
        channelApp.setStatus(ChannelApp.STATUS_INVALID);
        channelAppDao.insertChannelApp(channelApp);
        return new Object[]{0, channelApp};
    }

    /**
     * 根据id查询 channelApp 信息
     *
     * @param id
     * @param fields
     * @return
     */
    public ChannelApp findChannelAppById(Long id, String... fields) {
        return channelAppDao.findChannelAppById(id, fields);
    }

    /**
     * 修改channelApp 基本信息
     *
     * @param channelApp
     */
    public void channelAppModify(ChannelApp channelApp) {
        Update update = new Update();
        update.set("iosUrl", channelApp.getIosUrl()).set("channelId", channelApp.getChannelId());
        channelAppDao.updateChannelAppById(channelApp.getId(), update);
    }

    /**
     * 修改channelApp 状态
     *
     * @param id     channelApp id
     * @param status channelApp 状态
     */
    public void channelAppModifyStatus(Long id, Integer status) {
        Update update = new Update();
        update.set("status", status);
        channelAppDao.updateChannelAppById(id, update);
    }

    /**
     * 获取channelApp 列表
     *
     * @param page 分页信息
     */
    public void list(Page<ChannelApp> page) {
        channelAppDao.findChannelAppPage(page);
        List<Dictionary> diclist = DicUtil.getDictionary(DicConstants.DIC_TYPE_CHANNEL);
        Map<Integer, Dictionary> map = CollectionUtil.buildMap(diclist, Integer.class, Dictionary.class, "value");
        //设置channelName
        for (ChannelApp channelApp : page.getResult()) {
            if (map.containsKey(channelApp.getChannelId())) {
                channelApp.setChannelName(map.get(channelApp.getChannelId()).getName());
            }
        }

    }


    /**
     * 删除渠道信息
     *
     * @param id 渠道ID
     */
    public void channelAppRemove(Integer id) {
        channelAppDao.channelAppRemove(id);
    }

    /**
     * 修改APK 链接
     *
     * @param id      渠道信息Id
     * @param fileUrl apkUrl
     */
    public void channelAppModifyApk(Long id, String fileUrl) {
        //删除之前的文件
        ChannelApp channelApp = channelAppDao.findChannelAppById(id);
        if (null != channelApp.getAndroidApkUrl()) {
            FileCenterClient.removeFile(channelApp.getAndroidApkUrl());
        }
        Update update = new Update();
        update.set("androidApkUrl", fileUrl);
        channelAppDao.updateChannelAppById(id, update);
    }


    /**
     * 获取统计分析列表
     *
     * @param page 分页
     */
    public void getChannelAnalysisList(Page<ChannelAppAnalysis> page) {
        channelAppDao.findChannelAnalysisPage(page);
        //组装渠道名称
        List<Dictionary> dictionaryList = DicUtil.getDictionary(DicConstants.DIC_TYPE_CHANNEL);
        Map<Integer, Dictionary> map = CollectionUtil.buildMap(dictionaryList, Integer.class, Dictionary.class, "value");
        for (ChannelAppAnalysis channelAppAnalysis : page.getResult()) {
            if (map.containsKey(channelAppAnalysis.getChannelId())) {
                channelAppAnalysis.setChannelName(map.get(channelAppAnalysis.getChannelId()).getName());
            }
        }
    }

    /**
     * 新用户统计 用户 注册渠道、运动、下载音乐，信息统计
     */
    public List<ChannelUserStat> newUserStat(String bTime, String eTime, Integer channelId) {
        List<User> users = userDao.findByAddTimeAndChannel(DateUtil.parse(bTime, "yyyy-MM-dd").getTime(), DateUtil.parse(eTime, "yyyy-MM-dd").getTime(),
                channelId.toString(), "registerChannel", "addTime", "downloadCount", "lastRun");
        Map<String, ChannelUserStat> channelUserStatMap = new HashMap<>();
        for (User user : users) {
            if (user.getAddTime() == null) {
                continue;
            }
            String key = DateUtil.formatTimestamp(user.getAddTime(), "yyyyMMdd");
            if (!channelUserStatMap.containsKey(key)) {
                ChannelUserStat channelUserStat = new ChannelUserStat();
                channelUserStat.setStatDay(Integer.parseInt(key));
                channelUserStat.setChannelId(channelId);
                channelUserStat.setDownloadMusicNum(0L);
                channelUserStat.setRegisterNum(0L);
                channelUserStat.setRunNum(0L);
                channelUserStatMap.put(key, channelUserStat);
            }
            ChannelUserStat channelUserStat = channelUserStatMap.get(key);
            if (user.getLastRun() != null) {
                channelUserStat.setRunNum(channelUserStat.getRunNum() + 1);
            }
            Integer downloadCount = user.getDownloadCount();
            if (downloadCount != null && downloadCount > 0) {
                channelUserStat.setDownloadMusicNum(channelUserStat.getRunNum() + 1);
            }
            channelUserStat.setRegisterNum(channelUserStat.getRegisterNum() + 1);
        }
        List<ChannelUserStat> channelUserStats = new ArrayList<>(channelUserStatMap.values());

        Collections.sort(channelUserStats, SortFactory.CHANNEL_USER_STAT_SORT);
        return channelUserStats;
    }
}
