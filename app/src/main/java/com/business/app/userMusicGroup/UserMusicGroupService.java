package com.business.app.userMusicGroup;

import com.alibaba.fastjson.JSONObject;
import com.business.app.userMusic.UserMusicDao;
import com.business.core.client.AliyunCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.user.UserMusic;
import com.business.core.entity.user.UserMusicGroup;
import com.business.core.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by sin on 2015/11/18.
 * 歌曲分组
 */
@Service
public class UserMusicGroupService {

    @Autowired
    private UserMusicGroupDao userMusicGroupDao;
    @Autowired
    private UserMusicDao userMusicDao;


    /**
     * 查询所有 歌曲分组
     * @return 集合
     */
    public List<UserMusicGroup> list(Integer uid) {
        return userMusicGroupDao.findUserMusicGroup(uid);
    }


    /**
     * 添加 歌曲分组
     * <p>
     *     文件可能不存在，不存在时默认空字符
     *     <b>存在时，需要压缩图片 gif 文件此处不作处理，统一压缩</b>
     * </p>
     * @param uid 用户编号
     * @param name 名称
     * @param image 背景图
     */
    public UserMusicGroup addGroup(Integer uid, String name, String desc, MultipartFile image) {
        UserMusicGroup userSongGroup = new UserMusicGroup();
        if (image != null) {
            byte[] bytes = ImageUtil.compress(image);
            String imageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_USER_SONG_GROUP_IMAGE.toString(), image.getOriginalFilename(), bytes);
            userSongGroup.setImageUrl(imageUrl);
        } else {
            userSongGroup.setImageUrl("");
        }
        userSongGroup.setUid(uid);
        userSongGroup.setName(name);
        userSongGroup.setDesc(desc);
        userSongGroup.setStatus(UserMusicGroup.STATUS_SONG_NORMAL);
        userSongGroup.setAddTime(System.currentTimeMillis());
        userMusicGroupDao.insertUserMusicGroup(userSongGroup);

        /// 出去 返回 字段 ImageUrl
        userSongGroup.setImageUrl(null);
        return userSongGroup;
    }


    /**
     * 修改 自定义歌单（歌曲分组）
     * @param uid 用户编号
     * @param name 名称
     * @param image 图片  没有则不修改
     * @return 0、正常 1、歌单不存在
     */
    public Object[] modifyGroup(Integer uid, String name, String desc, MultipartFile image) {
        UserMusicGroup userMusicGroup = userMusicGroupDao.findUserMusicGroupByNameAndUid(name, uid);
        if (userMusicGroup == null) { // 歌单不存在
            return new Object[]{1};
        }

        Update update = Update.update("name", name).set("desc", desc);
        if (image != null) {
            String pathNew = AliyunCenterClient.putFile(String.valueOf(FileConstants.FILE_TYPE_USER_SONG_GROUP_IMAGE), image);
            AliyunCenterClient.deleteFile(userMusicGroup.getImageUrl());
            update.set("imageUrl", pathNew);
        }
        return new Object[]{0, userMusicGroupDao.findAndModifyNew(userMusicGroup.getId(), update)};
    }

    /**
     * 删除 歌曲分组
     * @param id 分组编号
     * @param uid 用户编号
     */
    public void removeGroup(Long id, Integer uid) {
        userMusicGroupDao.updateUserMusicGroupByIdAndUid(id, uid, Update.update("status", UserMusicGroup.STATUS_SONG_INVALID));
        /// 作废歌单所有歌曲
        userMusicDao.updateUserMusicByGroupId(id, Update.update("status", UserMusicGroup.STATUS_SONG_INVALID));
    }

    /**
     * 添加 歌单
     * @param uid 用户编号
     * @param groupId 分组编号
     * @param musics 歌单
     */
    public List<UserMusic> addMusic(Integer uid, Long groupId, List<JSONObject> musics) {
        List<UserMusic> userMusicList = new ArrayList<>(musics.size());
        for (JSONObject jsonObject : musics) {
            UserMusic userMusic = new UserMusic();
            userMusic.setUid(uid);
            userMusic.setName(String.valueOf(jsonObject.get("name")));
            userMusic.setAuthor(String.valueOf(jsonObject.get("author")));
            userMusic.setGroupId(groupId);
            userMusic.setStatus(UserMusicGroup.STATUS_SONG_NORMAL);
            userMusic.setAddTime(System.currentTimeMillis());
            userMusicDao.insertUserMusic(userMusic);
        }
        return userMusicList;
    }

    /**
     * 删除 UserMusic 信息
     * <p>
     *     根据歌曲名称 所在分组 和用户编号 确认信息
     * </p>
     * @param uid 用户编号
     * @param groupId 组
     * @param name 歌曲名称
     */
    public void removeMusic(Integer uid, Long groupId, String name){
        userMusicDao.updateUserMusicByUidAndGroupIdByName(uid, groupId, name, Update.update("status", UserMusicGroup.STATUS_SONG_INVALID));
    }

    /**
     * 删除多个 UserMusic 信息
     * <p>
     *     根据歌曲名称 所在分组 和用户编号 确认信息
     * </p>
     * @param uid 用户编号
     * @param groupId 组
     * @param names 歌曲名称
     */
    public void removeMusics(Integer uid, Long groupId, Collection<String> names){
        userMusicDao.updateUserMusicByUidAndGroupIdByNames(uid, groupId, names, Update.update("status", UserMusicGroup.STATUS_SONG_INVALID));
    }

    /**
     * 移动歌曲，将 UserMusic 移至新的分组
     * <p>
     *     根据歌曲名称 所在分组 和用户编号 确认信息
     *     将歌曲移至 新的分组
     * </p>
     * @param uid 用户编号
     * @param groupId 组
     * @param names 歌曲名称
     * @return 0、成功 1、该歌单不存在
     */
    public int moveMusicsByNames(Integer uid, Long groupId, Collection<String> names, String moveGroupName){
        UserMusicGroup userMusicGroupMove = userMusicGroupDao.findUserMusicGroupByNameAndUid(moveGroupName, uid, "id", "name");
        if (userMusicGroupDao.findUserMusicGroupByNameAndUid(moveGroupName, uid, "id", "name") == null) {
            return 1;
        }
        userMusicDao.updateUserMusicByUidAndGroupIdByNames(uid, groupId, names, Update.update("groupId", userMusicGroupMove.getId()));
        return 0;
    }


    ///

}
