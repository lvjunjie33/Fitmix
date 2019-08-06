package com.business.core.entity.channel;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2016/12/13.
 *
 * 用户 注册渠道、运动、下载音乐，信息统计
 */
public class ChannelUserStat{

    /**
     * 渠道字典值
     */
    private Integer channelId;
    /**
     * 注册用户人数
     */
    private Long registerNum;
    /**
     * 运动用户数
     */
    private Long runNum;
    /**
     * 下载音乐人数
     */
    private Long downloadMusicNum;
    /**
     * 统计的 日期 yyyyMMdd
     */
    private Integer statDay;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Long getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(Long registerNum) {
        this.registerNum = registerNum;
    }

    public Long getRunNum() {
        return runNum;
    }

    public void setRunNum(Long runNum) {
        this.runNum = runNum;
    }

    public Long getDownloadMusicNum() {
        return downloadMusicNum;
    }

    public void setDownloadMusicNum(Long downloadMusicNum) {
        this.downloadMusicNum = downloadMusicNum;
    }

    public Integer getStatDay() {
        return statDay;
    }

    public void setStatDay(Integer statDay) {
        this.statDay = statDay;
    }
}
