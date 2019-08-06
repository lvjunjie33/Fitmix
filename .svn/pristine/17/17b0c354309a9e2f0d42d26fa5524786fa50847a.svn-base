package com.business.app.userCollection;

import com.business.app.mix.MixDao;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.user.UserCollect;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
* Created by Administrator on 2015/4/20.
*/
@Service
public class UserCollectService {

    @Autowired
    private MixDao mixDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserCollectDao userCollectDao;


    /**
     * 用户收藏/再次调用则取消收藏
     * @param uid 用户编号
     * @param ids mix编号
     */
    public int collect(Integer uid, Collection<Integer> ids) {
        ids = new HashSet<>(ids);
        if (uid != null && userCoreDao.findUserById(uid, "id") == null) {
            return 1;
        }
//        if (mixDao.findMixById(mid, "id") == null) {
//            return 2;
//        }
        if (uid == null) {
            // 更新 mix 歌曲收藏信息
            for (Integer mid : ids) {
                mixDao.updateById(mid, new Update().inc("collectNumber", 1));
            }
        }
        else {
            for (Integer mid : ids) {
                UserCollect findUserCollect = userCollectDao.findUserCollectByUidAndMid(uid, mid);
                if (findUserCollect == null) { // 没有收藏则收藏
                    UserCollect userCollect = new UserCollect();
                    userCollect.setUid(uid);
                    userCollect.setMid(mid);
                    userCollect.setAddTime(System.currentTimeMillis());
                    userCollectDao.insertUserCollect(userCollect);
                    // 更新 mix 歌曲收藏信息
                    mixDao.updateById(mid, new Update().inc("collectNumber", 1));
                }
                else { // 有则收藏，则删除收藏 （物理删除，不保存记录）
                    userCollectDao.removeUserCollect(findUserCollect.getId());
                    // 更新 mix 歌曲收藏信息
                    mixDao.updateById(mid, new Update().inc("collectNumber", -1));
                }
            }
        }
        return 0;
    }

    /**
     * 用户收藏分页
     * @param uid 用户编号
     * @param index 分页
     * @return 用户收藏集合 0 成功， 1 用户存在
     */
    public Object[] page(String lan,Integer uid, Integer index) {
        if (userCoreDao.findUserById(uid, "id") == null) {
            return new Object[]{1};
        }
        List<UserCollect> UserCollects = userCollectDao.findUserCollectPage(uid, 10, index);
        if (!CollectionUtils.isEmpty(UserCollects)) {
            // 获取 UserCollects 集合的 mid
            Set<Integer> mixIdSet = CollectionUtil.buildSet(UserCollects, Integer.class, "mid");
            List<Mix> mixList = mixDao.findMixByIds(lan,mixIdSet);
            if (!CollectionUtils.isEmpty(mixList)) {
                // mixList 转换为 map ， 并设置 UserCollects Mix 信息
                Map<Long, Mix> mixMap = CollectionUtil.buildMap(mixList, Long.class, Mix.class, "id");
                for (UserCollect userCollect: UserCollects) {
                    Mix mix = mixMap.get(userCollect.getMid());
                    mix.setUrl(FileCenterClient.buildUrl(mix.getUrl()));
                    mix.setAlbumUrl(FileCenterClient.buildUrl(mix.getAlbumUrl()));
                    userCollect.setMix(mix);
                }
            }
        }
        return new Object[]{0, UserCollects};
    }

    /**
     * 用户收藏集合
     * @param uid 用户编号
     * @return 用户收藏
     */
    public int userCollectCount (Integer uid) {
        return userCollectDao.findUserCollectByUid(uid, "id").size();
    }

    /**
     * 查询用户收藏信息
     * @param uid 用户编号
     * @return 集合
     */
    public List<UserCollect> userCollect (Integer uid) {
        return userCollectDao.findUserCollectByUid(uid);
    }


    public void userShare () {

    }
}
