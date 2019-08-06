package com.business.app.userMusic;

import com.business.core.entity.user.UserMusic;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by sin on 2015/11/18.
 */
@Repository
public class UserMusicDao extends BaseMongoDaoSupport {

    /**
     * 添加歌单信息
     * @param userMusic 歌单信息
     */
    public void insertUserMusic(UserMusic userMusic) {
        insertToMongo(userMusic);
    }

    /**
     * 查询 UserMusic 信息
     * <p>
     *     根据 名称 uid  groupId确认 歌曲
     * </p>
     * @param name 名称
     * @param fields 列
     * @return 歌曲信息
     */
    public UserMusic findUserMusicByNameAndGroupIdAndUid(String name, Integer uid, String...fields) {
        Query query = new Query(Criteria.where("name").is(name));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, UserMusic.class);
    }

    /**
     * 更新多个 UserMusic
     * <p>
     *     根据 ids 集合更新
     * </p>
     * @param ids 编号集合
     * @param update 更新信息
     */
    public void updateUserMusicByIds(Collection<Long> ids, Update update) {
        Query query = new Query(Criteria.where("id").in(ids));
        getRoutingMongoOps().updateMulti(query, update, UserMusic.class);
    }

    /**
     * 根据 歌曲分组 来更新歌曲信息
     * @param groupId 分组编号
     * @param update 更新信息
     */
    public void updateUserMusicByGroupId(Long groupId, Update update) {
        Query query = new Query(Criteria.where("groupId").is(groupId));
        getRoutingMongoOps().updateMulti(query, update, UserMusic.class);
    }

    /**
     * 更新 UserMusic
     * <p>
     *     注意：本根据 id 就可以确认歌曲信息，
     *     但存在风险，进一步根据 groupId 和 uid 来确认信息
     * </p>
     * @param ids 歌曲编号
     * @param groupId 分组编号
     * @param uid 用户编号
     * @param update 更新信息
     */
    public void updateUserMusicByIdsAndGroupIdAndUids(Collection<Long> ids, Long groupId, Integer uid, Update update) {
        Query query = new Query(Criteria.where("id").in(ids).and("groupId").is(groupId).and("uid").is(uid));
        getRoutingMongoOps().updateMulti(query, update, UserMusic.class);
    }

    /**
     * 更新 UserMusic
     * <p>
     *     根据 用户编号 分组 和多个歌曲名称
     * </p>
     * @param uid 用户编号
     * @param groupId 分组
     * @param name 歌曲名称
     * @param update 更新信息
     */
    public void updateUserMusicByUidAndGroupIdByName(Integer uid, Long groupId, String name, Update update) {
        Query query = new Query(Criteria.where("uid").is(uid).and("groupId").is(groupId).and("name").is(name));
        getRoutingMongoOps().updateMulti(query, update, UserMusic.class);
    }

    /**
     * 更新多个 UserMusic
     * <p>
     *      根据 用户编号 分组 和歌曲名称
     * </p>
     * @param uid 用户编号
     * @param groupId 分组
     * @param names 歌曲名称集合
     * @param update 更新信息
     */
    public void updateUserMusicByUidAndGroupIdByNames(Integer uid, Long groupId, Collection<String> names, Update update) {
        Query query = new Query(Criteria.where("uid").is(uid).and("groupId").is(groupId).and("name").in(names));
        getRoutingMongoOps().updateMulti(query, update, UserMusic.class);
    }
}
