package com.business.core.entity.community.discuss;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.constants.Constants;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * Created by edward on 2017/5/8.
 * 主题贴、公告、活动
 */
@Document(collection = "Theme")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Theme extends IncIdEntity<Long> {

    /**
     * 水军
     */
    public static final Integer IS_WATER_ARMY = 1;
    /**
     * 根据添加时间排序
     */
    public static final Integer SEARCH_TYPE_ADD_TIME = 1;
    /**
     * 根据回答数排序
     */
    public static final Integer SEARCH_TYPE_DISCUSS_NUM = 2;
    /**
     * 根据点赞数排序
     */
    public static final Integer SEARCH_TYPE_UP_NUM = 3;
    /**
     * 根据讨论数排序
     */
    public static final Integer SEARCH_TYPE_TAO_LUN_NUM = 4;
    /**
     * 点赞
     */
    public static final Integer OPINION_UP = 1;
    /**
     * 中立
     */
    public static final Integer OPINION_FLAT = 0;
    /**
     * 问题
     */
    public static final Integer THEME_TYPE_PROBLEM = 1;
    /**
     * 回答
     */
    public static final Integer THEME_TYPE_ANSWER = 2;

    /**
     * 板块编号
     * {@link Categorys#id}
     */
    private Integer categoryId;
    /*================================内容=======================================*/
    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 标题 -->标题党
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 主题帖类型
     * {@link Theme#THEME_TYPE_PROBLEM}
     * {@link Theme#THEME_TYPE_ANSWER}
     */
    private Integer themeType;
    /**
     * 父级主题帖编号
     */
    private Long parentThemeId;
    /**
     * 父级主题贴的标题
     */
    @Transient
    private Theme parentTheme;
    /*================================管理===========================================*/
    /**
     * banner 话题的位置，为空就是不是banner话题
     */
    private Integer bannerSort;
    /**
     * banner 封面图地址
     */
    private String backImage;
    /**
     * 突出、精华贴
     */
    private Integer fine;
    /**
     * 0、关闭回复
     * 1、开启回复(默认) {@link Constants#ENABLED}
     */
    private Integer isReply;
    /**
     * 0、未审核
     * 1、审核了 (默认) {@link com.business.core.constants.Constants#CHECK_STATUS_SUCCESS}
     */
    private Integer isConfirmed;
    /**
     * 0、禁用 (敏感内容)
     * 1、正常 (默认正常) {@link com.business.core.constants.Constants#ENABLED}
     */
    private Integer isSensitive;

    /*================================统计相关数据===========================================*/
    /**
     * 点赞数
     */
    private Integer upNum;
    private Set<Integer> upUserIds;
    /**
     * 点击数
     */
    private Integer clickNum;
    /**
     * 回答数
     */
    private Integer discussNum;
    /**
     * 讨论数
     */
    private Integer taoLunNum;

    /**
     * 选中的回答
     */
    @Transient
    private Theme selectNodeTheme;
    /**
     * 用户头像 {@link com.business.core.entity.user.User#avatar}
     */
    @Transient
    private String avatar;
    /**
     * 个性签名 {@link com.business.core.entity.user.User#signature}
     */
    @Transient
    private String signature;
    /**
     * 用户名称 {@link com.business.core.entity.user.User#name}
     */
    @Transient
    private String name;
    /**
     * 认证用户
     */
    @Transient
    private Integer themeVip;
    /**
     * 新消息数
     */
    @Transient
    private Integer newMsgNum;
    /**
     * 回答列表
     */
    @Transient
    private List<Theme> answerList;
    /**
     * 讨论列表
     */
    @Transient
    private List<Discuss> discussList;
    /**
     * 我的回答编号 {@link Theme#id}
     */
    private Long answerId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    public Integer getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Integer isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Integer getUpNum() {
        return upNum;
    }

    public void setUpNum(Integer upNum) {
        this.upNum = upNum;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Discuss> getDiscussList() {
        return discussList;
    }

    public void setDiscussList(List<Discuss> discussList) {
        this.discussList = discussList;
    }

    public Integer getDiscussNum() {
        return discussNum;
    }

    public void setDiscussNum(Integer discussNum) {
        this.discussNum = discussNum;
    }

    public Set<Integer> getUpUserIds() {
        return upUserIds;
    }

    public void setUpUserIds(Set<Integer> upUserIds) {
        this.upUserIds = upUserIds;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getNewMsgNum() {
        return newMsgNum;
    }

    public void setNewMsgNum(Integer newMsgNum) {
        this.newMsgNum = newMsgNum;
    }

    public Integer getTaoLunNum() {
        return taoLunNum;
    }

    public void setTaoLunNum(Integer taoLunNum) {
        this.taoLunNum = taoLunNum;
    }

    public Integer getThemeType() {
        return themeType;
    }

    public void setThemeType(Integer themeType) {
        this.themeType = themeType;
    }

    public Long getParentThemeId() {
        return parentThemeId;
    }

    public void setParentThemeId(Long parentThemeId) {
        this.parentThemeId = parentThemeId;
    }

    public Theme getSelectNodeTheme() {
        return selectNodeTheme;
    }

    public void setSelectNodeTheme(Theme selectNodeTheme) {
        this.selectNodeTheme = selectNodeTheme;
    }

    public List<Theme> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Theme> answerList) {
        this.answerList = answerList;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Integer getBannerSort() {
        return bannerSort;
    }

    public void setBannerSort(Integer bannerSort) {
        this.bannerSort = bannerSort;
    }

    public Theme getParentTheme() {
        return parentTheme;
    }

    public void setParentTheme(Theme parentTheme) {
        this.parentTheme = parentTheme;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
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
