package com.business.core.entity.club;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * Created by sin on 2015/11/19.
 * 俱乐部
 * <p>
 *     运动俱乐部，来扩展用户群 和跑步人气之间的动态信息
 * </p>
 */
@Document(collection = "Club")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class Club extends IncIdEntity<Long> {

    /**
     * status/正常
     */
    public static final Integer STATUS_NORMAL = 0;
    /**
     * status/无效
     */
    public static final Integer STATUS_INVALID = 1;
    /**
     * type/用户俱乐部
     */
    public static final int TYPE_USER = 1;
    /**
     * type/开放俱乐部
     */
    public static final int TYPE_PUBLIC = 2;
    /**
     * 没有俱乐部成员
     */
    public static final int SAVE_MEMBER_NO = 0;
    /**
     * 有俱乐部成员
     */
    public static final int SAVE_MEMBER_YES = 1;


    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 俱乐部名称
     */
    private String name;
    /**
     * 俱乐部成员 数量
     */
    private Integer memberCount;
    /**
     * 俱乐部成员 用户编号：加入的用户
     */
    private Set<Integer> memberUidSet;
    /**
     * 俱乐部背景图
     */
    private String backImageUrl;
    /**
     * 最大成员
     */
    private Integer maxMember;
    /**
     * 描述
     */
    private String desc;
    /**
     * 状态 0、正常 1、无效（删除）
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Long addTime;


    ///
    /// 非存储 字段

    /**
     * true、用户俱乐部 false、开放俱乐部（永远显示 非俱乐部用户进入只可以查看）
     */
    @Transient
    private Integer type;
    /**
     * 0、没有 1、有
     */
    @Transient
    private Integer haveMember;
    /**
     * 用户
     */
    @Transient
    private User user;
    /**
     * 成员用户列表
     */
    @Transient
    private List<User> memberUserList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Set<Integer> getMemberUidSet() {
        return memberUidSet;
    }

    public void setMemberUidSet(Set<Integer> memberUidSet) {
        this.memberUidSet = memberUidSet;
    }

    public String getBackImageUrl() {
        return backImageUrl;
    }

    public void setBackImageUrl(String backImageUrl) {
        this.backImageUrl = backImageUrl;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHaveMember() {
        return haveMember;
    }

    public void setHaveMember(Integer haveMember) {
        this.haveMember = haveMember;
    }

    public List<User> getMemberUserList() {
        return memberUserList;
    }

    public void setMemberUserList(List<User> memberUserList) {
        this.memberUserList = memberUserList;
    }
}
