package com.business.core.operations.message;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.msg.Message;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by edward on 2017/11/16.
 */
@Repository
public class MessageCoreDao extends BaseMongoDaoSupport {

    /**
     * 修改消息状态
     *
     * @param answerId 回答编号
     */
    public void modifyMessageStatusByAnswerId(Long answerId, Integer uid) {
        Query query = Query.query(Criteria.where("msgBody.answerId").is(answerId.toString()).and("msgBody.targetUid").is(uid.toString()).and("selectChannel").is(MsgConstants.CHANNEL_ANSWER_DISCUSS));
        getRoutingMongoOps().updateMulti(query, Update.update("status", MsgConstants.HANDLE_STATUS_TRUE), Message.class);
    }
}
