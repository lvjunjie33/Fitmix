package com.business.core.entity.im;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 环信群组信息
 * Created by edward on 2016/10/25.
 */
@Document(collection = "IMInfoGroup")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class IMInfoGroup extends IMInfo {

    public static String[] fields = new String[]{"groupName", "groupId", "des", "sort"};

    public static final Integer DEFAULT_SORT = 100;
    /**
     * 群组名
     */
    private String groupName;
    /**
     * 群组编号
     */
    private String groupId;
    /**
     * 群组描述
     */
    private String des;
    /**
     * 群组 加入密码
     */
    private String password;
    /**
     * 创建者 环信帐号
     * {@link IMInfoUser#username}
     */
    private String createIMUserName;
    /**
     * 排序值
     */
    private Integer sort;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateIMUserName() {
        return createIMUserName;
    }

    public void setCreateIMUserName(String createIMUserName) {
        this.createIMUserName = createIMUserName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
