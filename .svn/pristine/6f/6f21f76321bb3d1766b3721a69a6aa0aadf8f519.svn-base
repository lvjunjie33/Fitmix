package com.business.app.message;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.Page;
import com.business.core.entity.community.discuss.CategoryMsg;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.info.UserGroup;
import com.business.core.entity.user.info.UserGroupRelation;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by edward on 2016/4/25.
 */
@Repository
public class MessageDao extends BaseMongoDaoSupport{

    /**
     * 查询最近的一条消息
     *
     * @param uid 用户编号
     * @param fields 查询的字段
     */
    public Message findMessageByUid(Integer uid, String channel, String...fields) {
        Query query = Query.query(Criteria.where("msgBody.uid").is(uid).and("selectChannel").is(channel));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().findOne(query, Message.class);
    }

    /**
     * 查询消息数量
     *
     * @param uid 用户编号
     * @param bTime 开始时间
     */
    public Long findMessageCount(Integer uid, String channel, Long bTime) {
        Query query = Query.query(Criteria.where("msgBody.uid").is(uid).and("addTime").gte(bTime).and("selectChannel").is(channel));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().count(query, Message.class);
    }

    /**
     * 查询用户组
     *
     * @param fromUid 用户成员
     * @param targetUid 目标用户
     * @param type 类型
     * @param fields 查询的字段
     */
    public UserGroup findUserGroupByMember(Integer fromUid, Integer targetUid, Integer type, String...fields) {
        Query query = Query.query(Criteria.where("member").all(targetUid, fromUid).and("type").is(type));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().findOne(query, UserGroup.class);
    }

    /**
     * 查询用户与用户组的关系
     *
     * @param groupId 用户组编号
     * @param uid 用户编号
     */
    public UserGroupRelation findUserGroupRelationByGroupId(Long groupId, Integer uid, String...fields) {
        Query query = Query.query(Criteria.where("groupId").is(groupId).and("uid").is(uid));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().findOne(query, UserGroupRelation.class);
    }

    /**
     * 保存用户组
     *
     * @param userGroup 用户组
     */
    public void saveUserGroup(UserGroup userGroup) {
        insertToMongo(userGroup);
    }

    /**
     * 保存用户关系
     *
     * @param userGroupRelation 用户关系
     */
    public void saveUserGroupRelation(UserGroupRelation userGroupRelation) {
        insertToMongo(userGroupRelation);
    }

    /**
     * 修改用户组冗余信息
     */
    public void modifyUserGroup(Long groupId, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(groupId)), update, UserGroup.class);
    }

    /**
     * 修改会话关系
     *
     * @param id 关系编号
     */
    public void modifyUserGroupRelationById(Long id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, UserGroupRelation.class);
    }

    /**
     * 修改会话关系
     *
     * @param groupId 组编号
     * @param update 修改的内容
     */
    public void modifyUserGroupRelationByGroupId(Long groupId, Update update) {
        getRoutingMongoOps().updateMulti(Query.query(Criteria.where("groupId").is(groupId)), update, UserGroupRelation.class);
    }

    /**
     * 用户组分页
     */
    public void userGroupPage(Page<UserGroup> page, String...fields) {
        Criteria criteria = new Criteria();
        Map<String, Object> filter = page.getFilter();

        if (filter.containsKey("type")) {
            criteria.and("type").is(filter.get("type"));
        }

        if (filter.containsKey("uid")) {
            criteria.and("member").in(filter.get("uid"));
        }

        if (filter.containsKey("groupIds")) {
            List<Long> groupIds = (List<Long>) filter.get("groupIds");
            criteria.and("id").in(groupIds);
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(UserGroup.class, page, query, fields);
    }

    /**
     * 消息分页
     *
     * @param page 消息分页对象
     */
    public void messagePage(Page<Message> page, String...fields) {
        Criteria criteria = new Criteria();
        Map<String, Object> filter = page.getFilter();

        if (filter.containsKey("groupId")) {
            criteria.and("msgBody.groupId").is(filter.get("groupId"));
        }

        if (filter.containsKey("selectChannel")) {
            criteria.and("selectChannel").is(filter.get("selectChannel"));
        }

        if (filter.containsKey("msgId")) {
            criteria.and("id").gt(filter.get("msgId"));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(Message.class, page, query, fields);
    }

    /**
     * 通过分组编号 查询私信明细
     *
     * @param groupId 分组编号
     * @param selectChannel 通道
     * @param status 状态
     * @param fields 查询的字段
     */
    public List<Message> findMessageByGroupId(Long groupId, String fromUid, String selectChannel, Integer status, String...fields) {
        Criteria criteria = Criteria.where("selectChannel").is(selectChannel).and("status").is(status).and("msgBody.fromUid").ne(fromUid);

        if (groupId != null) {
            criteria.and("msgBody.groupId").is(groupId.toString());
        }

        Query query = new Query(criteria);
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, Message.class);
    }



    /**
     * 通过用户组编号和用户编号修改用户会话信息
     *
     * @param groupId 用户组编号
     * @param uid 用户编号
     * @param update 修改的内容
     */
    public void modifyUserGroupRelationByGroupIdAndUid(Long groupId, Integer uid, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("groupId").is(groupId).and("uid").is(uid)), update, UserGroupRelation.class);
    }

    /**
     * 查询用户的关系表
     * @param uid 用户编号
     * @param state 关系状态
     */
    public List<UserGroupRelation> findUserGroupRelationByUid(Integer uid, Integer state, String...fields) {
        Query query = Query.query(Criteria.where("uid").is(uid).and("state").is(state));
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, UserGroupRelation.class);
    }

    /**
     * 通过编号查询分组关系
     *
     * @param groupIds
     * @param uid
     * @param fields
     * @return
     */
    public List<UserGroupRelation> findUserGroupRelationByGroupIds(Set<Long> groupIds, Integer uid, String...fields) {
        Query query = Query.query(Criteria.where("groupId").in(groupIds).and("uid").is(uid));
        includeFields(query, fields);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        return getRoutingMongoOps().find(query, UserGroupRelation.class);
    }

    /**
     * 修改消息状态
     *
     * @param msgIds 消息编号
     */
    public void modifyMessageStatus(Set<Long> msgIds, Integer fromUid) {
        getRoutingMongoOps().updateMulti(Query.query(Criteria.where("id").in(msgIds).and("msgBody.fromUid").ne(fromUid.toString())), Update.update("status", MsgConstants.HANDLE_STATUS_TRUE), Message.class);
    }

    /**
     * 获取用户通知
     */
    public List<Message> getUserNotice(Integer uid) {
        Query query = Query.query(Criteria.where("selectChannel").in(MsgConstants.CHANNEL_THEME_ANSWER, MsgConstants.CHANNEL_ANSWER_DISCUSS)
                .and("status").is(MsgConstants.HANDLE_STATUS_FALSE).and("msgBody.targetUid").is(uid.toString()));
        return getRoutingMongoOps().find(query, Message.class);
    }

    /**
     * 查询用户新的通知
     *
     * @param uid 用户编号
     */
    public Long findNewMsgCountByUid(Integer uid) {
        Query query = Query.query(Criteria.where("selectChannel").in(MsgConstants.CHANNEL_THEME_ANSWER, MsgConstants.CHANNEL_ANSWER_DISCUSS)
                .and("status").is(MsgConstants.HANDLE_STATUS_FALSE).and("msgBody.targetUid").is(uid.toString()));
        return getRoutingMongoOps().count(query, Message.class);
    }

    /**
     * 查询用户分组
     *
     * @param uid 用户编号
     * @param type 分组类型
     * @param fields 查询的字段
     */
    public List<UserGroup> findUserGroupByUid(Integer uid, Integer type, String...fields) {
        Query query = Query.query(Criteria.where("member").in(uid).and("type").is(type));
        return getRoutingMongoOps().find(query, UserGroup.class);
    }

    /**
     * 通过分组编号查询用户通知
     * @param groupIds
     * @return
     */
    public Long findNewMsgCountByGroupId(Set<String> groupIds, Integer fromUid) {
        Query query = Query.query(Criteria.where("selectChannel").is(MsgConstants.CHANNEL_TYPE_USER_PRIVATE_MSG).and("msgBody.fromUid").ne(fromUid.toString())
                .and("status").is(MsgConstants.HANDLE_STATUS_FALSE).and("msgBody.groupId").in(groupIds));
        return getRoutingMongoOps().count(query, Message.class);
    }

    /**
     * 查询新的消息
     *
     * @param targetUid 目标用户编号
     * @param selectChannel 消息类型
     */
    public List<Message> findMyThemeMsgByUid(String targetUid, String selectChannel, Integer status, String...fields) {
        Criteria criteria = Criteria.where("msgBody.targetUid").is(targetUid).and("selectChannel").is(selectChannel).and("status").is(status);
        Query query = Query.query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().find(query, Message.class);
    }

    /**
     * 修改话题消息的状态
     *
     * @param ids 消息编号
     */
    public void modifyThemStatusByIds(Set<Long> ids) {
        getRoutingMongoOps().updateMulti(Query.query(Criteria.where("id").in(ids)), Update.update("status", MsgConstants.HANDLE_STATUS_TRUE), Message.class);
    }

    /**
     * 消息编号
     *
     * @param msgId 消息编号
     * @param update 修改的内容
     */
    public void modifyMessageById(Long msgId, Integer uid, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(msgId).and("msgBody.targetUid").is(uid.toString())), update, Message.class);
    }
}
