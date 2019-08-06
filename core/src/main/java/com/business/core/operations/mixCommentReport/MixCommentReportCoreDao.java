package com.business.core.operations.mixCommentReport;

import com.business.core.entity.mix.MixCommentReport;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by sin on 2015/11/18.
 * Mix 评论举报
 */
@Repository
public class MixCommentReportCoreDao extends BaseMongoDaoSupport{

    /**
     * 添加评论
     * @param mixCommentReport 歌曲评论信息
     */
    public void insertMixCommentReport(MixCommentReport mixCommentReport) {
        insertToMongo(mixCommentReport);
    }

    /**
     * 更新 举报信息
     * @param id 编号
     * @param update 更新信息
     */
    public void updateMixCommentReportById(Long id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, MixCommentReport.class);
    }

    /**
     * 根据 Id 查询评论举报查询
     * @param id 编号
     * @param fields 列
     * @return 举报信息
     */
    public MixCommentReport findMixCommentReportById(Long id, String...fields) {
        return findEntityById(MixCommentReport.class, id, fields);
    }
}
