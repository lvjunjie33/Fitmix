package com.business.work.codeMessage;

import ch.ethz.ssh2.crypto.cipher.DES;
import com.business.core.entity.Page;
import com.business.core.entity.code.CodeMessage;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by sin on 2015/11/23.
 */
@Repository
public class CodeMessageDao extends BaseMongoDaoSupport {

    /**
     * 查询所有 错误信息
     * @return 集合
     */
    public List<CodeMessage> findAll() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "code"));
        return getRoutingMongoOps().find(query, CodeMessage.class);
    }

    /**
     * 查询 错误信息
     * @param code 编号
     * @param fields 列
     * @return 错误信息
     */
    public CodeMessage findCodeMessageByCode(int code, String...fields) {
        Query query = new Query(Criteria.where("code").is(code));
        return getRoutingMongoOps().findOne(query, CodeMessage.class);
    }

    /**
     * 添加一个 codeMessage
     * @param codeMessage 错误信息
     */
    public void insertCodeMessage(CodeMessage codeMessage) {
        insertToMongo(codeMessage);
    }

    /**
     * 删除 codeMessage
     * @param id 编号
     */
    public void deleteCodeMessage(int id) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().remove(query, CodeMessage.class);
    }

    /**
     * 更新 CodeMessage
     * @param id 编号
     * @param update 更新信息
     */
    public void updateCodeMessageById(int id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        getRoutingMongoOps().updateFirst(query, update, CodeMessage.class);
    }

    /**
     * 通过主键查询 消息码
     *
     * @param id 主键编号
     * @param fields 查询的字段
     */
    public CodeMessage findById(Integer id, String...fields) {
        return findEntityById(CodeMessage.class, id, fields);
    }
}
