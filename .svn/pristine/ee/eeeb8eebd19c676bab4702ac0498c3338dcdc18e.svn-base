package com.business.core.entity.gw;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2018/9/18.
 */

@Document(collection = "CommonProblem")
@MongoDocumentDB(MongoType.BUSINESS_SECONDARY)
public class CommonProblem extends IncIdEntity<Long> {

    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 问题标题
     */
    private String problemTitle;
    /**
     * 问题内容
     */
    private String problemContent;
    /*
    * 常见问题语言
    **/
    private String problem_lan;

    /**
     * 问题标题(英文)
     */
    private String problemTitle_en;
    /**
     * 问题内容(英文)
     */
    private String problemContent_en;
    /**
     * 状态
     */
    private int status;

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public void setProblemTitle_en(String problemTitle_en) {
        this.problemTitle_en = problemTitle_en;
    }

    public String getProblemTitle() {

        return problemTitle;
    }

    public String getProblemTitle_en() {
        return problemTitle_en;
    }

    public void setProblemContent_en(String problemContent_en) {
        this.problemContent_en = problemContent_en;
    }

    public void setProblem_lan(String problem_lan) {
        this.problem_lan = problem_lan;
    }

    public Long getAddTime() {
        return addTime;
    }

    public String getProblemContent() {
        return problemContent;
    }
    public int getStatus() {
        return status;
    }
    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public void setProblemContent(String problemContent) {
        this.problemContent = problemContent;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getProblem_lan() {
        return problem_lan;
    }



    public String getProblemContent_en() {
        return problemContent_en;
    }

}
