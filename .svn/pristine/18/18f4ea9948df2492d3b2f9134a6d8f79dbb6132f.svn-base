package com.business.core.entity.community.discuss;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * Created by edward on 2017/5/8.
 *
 * 回答的讨论、评论
 */
@Document(collection = "Discuss")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Discuss extends IncIdEntity<Long> {

    /**
     * 基础资料字段
     */
    public static final String[] BASIC_INFO_FIELDS = new String[] {"themeId", "upNum", "upUserIds", "content", "addTime", "userName", "uid",
            "avatar", "discussUid", "discussId", "discussUName"};


    /*================================关系======================================*/
    /**
     * 主题编号
     * {@link Theme#id}
     */
    private Long themeId;

    /*================================统计相关数据===========================================*/
    /**
     * 点赞数
     */
    private Integer upNum;
    private Set<Integer> upUserIds;

    /*==============================内容====================================*/

    /**
     * 内容
     */
    private String content;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 用户名
     * 返回给客户端的时候使用该字段，避免频繁查询
     * {@link com.business.core.entity.user.User#name}
     */
    private String userName;
    /**
     * 用户编号
     * {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 回答编号
     */
    private Long discussId;
    /**
     * 回复的编号(说明:讨论才有回复的编号)
     */
    private Integer discussUid;
    /**
     * 回复的目标用户的名称(我讨论的谁的回复、讨论)
     */
    private String discussUName;
    /**
     * 0、未审核
     * 1、审核了
     */
    private Integer isConfirmed;
    /**
     * 0、禁用 (敏感内容)
     * 1、正常 (默认正常)
     */
    private Integer isSensitive;
    /**
     * 新消息数
     */
    @Transient
    private Integer newMsgNum;
    /**
     * 认证用户
     */
    @Transient
    private Integer themeVip;

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getDiscussUName() {
        return discussUName;
    }

    public void setDiscussUName(String discussUName) {
        this.discussUName = discussUName;
    }

    public Integer getDiscussUid() {
        return discussUid;
    }

    public void setDiscussUid(Integer discussUid) {
        this.discussUid = discussUid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUpNum() {
        return upNum;
    }

    public void setUpNum(Integer upNum) {
        this.upNum = upNum;
    }

    public Set<Integer> getUpUserIds() {
        return upUserIds;
    }

    public void setUpUserIds(Set<Integer> upUserIds) {
        this.upUserIds = upUserIds;
    }

    public Long getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Long discussId) {
        this.discussId = discussId;
    }

    public Integer getNewMsgNum() {
        return newMsgNum;
    }

    public void setNewMsgNum(Integer newMsgNum) {
        this.newMsgNum = newMsgNum;
    }

    public Integer getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Integer isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Integer getIsSensitive() {
        return isSensitive;
    }

    public void setIsSensitive(Integer isSensitive) {
        this.isSensitive = isSensitive;
    }

    public Integer getThemeVip() {
        return themeVip;
    }

    public void setThemeVip(Integer themeVip) {
        this.themeVip = themeVip;
    }
}
