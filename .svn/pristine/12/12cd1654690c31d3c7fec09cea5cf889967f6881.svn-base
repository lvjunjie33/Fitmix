package com.business.core.entity.sys;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2017/9/25.
 *
 * app弹窗公告
 */
@Document(collection = "Announcement")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class Announcement extends IncIdEntity<Integer> {

    public static final String[] fields = {"type", "imgLink", "displayNum", "bTime", "eTime", "body"};

    //图片
    public static final int TYPE_IMG = 1;
    //外链
    public static final int TYPE_THREE_LINK = 2;
    //话题
    public static final int TYPE_THEME = 3;
    //Mix
    public static final int TYPE_MIX = 4;
    //赛事
    public static final int TYPE_ACTIVITY = 5;

    /**
     * 公告类型
     *
     * 1、图片公告(文本让设计做成图片)
     * 2、广告外链公告
     * 3、话题公告
     * 4、Mix公告
     * 5、赛事公告
     */
    private Integer type;
    /**
     * 图片link
     */
    private String imgLink;
    /**
     * 展示次数
     */
    private Integer displayNum;
    /**
     * 开始时间
     */
    private Long bTime;
    /**
     * 结束时间
     */
    private Long eTime;
    /**
     * 描述
     */
    private String desc;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 内容
     *
     * 普通文本公告
     *
     */
    private Object body;
    /**
     * 发布--状态
     * 1、发布
     * 0、未发布
     */
    private Integer status;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Integer getDisplayNum() {
        return displayNum;
    }

    public void setDisplayNum(Integer displayNum) {
        this.displayNum = displayNum;
    }

    public Long getbTime() {
        return bTime;
    }

    public void setbTime(Long bTime) {
        this.bTime = bTime;
    }

    public Long geteTime() {
        return eTime;
    }

    public void seteTime(Long eTime) {
        this.eTime = eTime;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
