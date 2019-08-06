package com.business.app.userMusicGroup;

import com.business.core.entity.user.UserMusicGroup;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sin on 2015/11/18.
 * 歌曲分组
 */
@Repository
public class UserMusicGroupDao extends BaseMongoDaoSupport {

    /**
     * 查询所有 歌曲分组
     * @return 集合
     */
    public List<UserMusicGroup> findUserMusicGroup(Integer uid) {
        Query query = new Query(Criteria.where("status").is(UserMusicGroup.STATUS_SONG_NORMAL).and("uid").is(uid));
        query.with(new Sort(Sort.Direction.ASC, "name"));
        return getRoutingMongoOps().find(query, UserMusicGroup.class);
    }

    /**
     * 添加 用户歌曲组
     * @param userSongGroup 用户歌曲分组信息
     */
    public void insertUserMusicGroup(UserMusicGroup userSongGroup) {
        insertToMongo(userSongGroup);
    }

    /**
     * 获取用户分组信息
     * <p>
     *     根据 组名称 和 uid 确认分组
     * </p>
     * @param name 分组名称
     * @param uid 用户编号
     * @param fields 列
     * @return 用户组信息
     */
    public UserMusicGroup findUserMusicGroupByNameAndUid(String name, Integer uid, String...fields) {
        Query query = new Query(Criteria.where("name").is(name).and("uid").is(uid));
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, UserMusicGroup.class);
    }

    /**
     * 查询 并 更新
     * <p>
     *     根据 id 查询并更新，返回更新后的数据
     * </p>
     * @param id 编号
     * @param update 更新内容
     * @return 更新后信息
     */
    public UserMusicGroup findAndModifyNew(Long id, Update update) {
        Query query = new Query(Criteria.where("id").is(id));
        return getRoutingMongoOps().findAndModify(query, update, FIND_AND_MODIFY_OPTIONS_RETURN_NEW, UserMusicGroup.class);
    }

    /**
     * 更新 歌曲分组信息
     * <p>
     *     注意：
     *     id 本可以确认 用户歌曲
     *     以防万一，加上 用户编号 uid 来进一步确认
     * </p>
     * @param id 编号
     * @param uid 用户编号
     * @param update 更新信息
     */
    public void updateUserMusicGroupByIdAndUid(Long id, Integer uid, Update update) {
        Query query = new Query(Criteria.where("id").is(id).and("uid").is(uid));
        getRoutingMongoOps().updateFirst(query, update, UserMusicGroup.class);
    }
}
