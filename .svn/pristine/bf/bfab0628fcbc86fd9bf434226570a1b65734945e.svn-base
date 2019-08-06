package com.business.core.entity.dynamic;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.user.User;
import com.business.core.mongo.MongoType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by fenxio on 2016/10/8.
 * 朋友圈动态信息表
 */
@Document(collection = "Dynamic")
@MongoDocumentDB(MongoType.DB_BUSINESS)
public class Dynamic extends IncIdEntity<Long> {

    /**
     * 用户编号 {@link com.business.core.entity.user.User#id}
     */
    private Integer uid;
    /**
     * 类型：1：文字 2：图文
     */
    private Integer type;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片
     */
    private List<String> imgs;
    /**
     * 阅读权限：1：公开
     */
    private Integer permission;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 评论列表
     */
    @Transient
    private List<Comment> commentList;
    /**
     * 点赞列表
     */
    @Transient
    private List<Flower> flowerList;
    /**
     * 用户
     */
    @Transient
    private User user;
    /**
     * 评论总数
     */
    @Transient
    private Long commentCount;
    /**
     * 点赞总数
     */
    @Transient
    private Long flowerCount;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Flower> getFlowerList() {
        return flowerList;
    }

    public void setFlowerList(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getFlowerCount() {
        return flowerCount;
    }

    public void setFlowerCount(Long flowerCount) {
        this.flowerCount = flowerCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Dynamic(Integer uid, Integer type, String content, Integer permission) {
        this.uid = uid;
        this.type = type;
        this.content = content;
        this.permission = permission;
    }
}
