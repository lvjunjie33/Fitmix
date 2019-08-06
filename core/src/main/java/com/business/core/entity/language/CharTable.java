package com.business.core.entity.language;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.DefaultDao;
import com.business.core.mongo.MongoType;
import com.business.core.utils.BeanManager;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created by edward on 2017/8/15.
 */
@Document(collection = "CharTable")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class CharTable{

    private static final String TYPE_CN = "cn";

    private static final String TYPE_EN = "en";

    /**
     * 字典表
     */
    public static final int TYPE_DICTIONARY = 1;
    /**
     * 地址表
     */
    public static final int TYPE_CITY_NO = 2;
    /**
     * 错误码
     */
    public static final int TYPE_CODE_MESSAGE = 3;
    /**
     * 训练计划翻译
     */
    public static final int TYPE_USER_RUN_PLAN = 4;
    /**
     * 跑步等级
     */
    public static final int TYPE_RUN_LEVEL_INFO = 5;
    /**
     * 金币任务
     */
    public static final int TYPE_TASK_TYPE = 6;

    /**
     * 英文
     */
    private String strEN;
    /**
     * 中文
     */
    private String strCN;
    /**
     * 1、下发 (下发app)
     * 0、不下发 (默认)
     */
    private Integer issued;
    /**
     * 添加时间
     */
    private Long addTime;

    public String getStrCN() {
        return strCN;
    }

    public void setStrCN(String strCN) {
        this.strCN = strCN;
    }

    public Integer getIssued() {
        return issued;
    }

    public void setIssued(Integer issued) {
        this.issued = issued;
    }

    public String cnToEn(String strCN) {
        CharTable charTable = BeanManager.getBean(DefaultDao.class).findOne(Query.query(Criteria.where("strCn").is(strCN)), CharTable.class);
        if (charTable == null) {
            return strCN;
        }
        return charTable.getStrEN();
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getStrEN() {
        return strEN;
    }

    public void setStrEN(String strEN) {
        this.strEN = strEN;
    }
}
