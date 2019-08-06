package com.business.core.operations.mixComment;

import com.business.core.entity.SysParam;
import com.business.core.entity.mix.MixComment;
import com.business.core.entity.user.User;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sin on 2015/8/5 0005.
 */
@Service
public class MixCommentCoreService {

    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private MixCommentCoreDao mixCommentCoreDao;

    @Autowired
    private UserCoreService userCoreService;


    /**
     * 构建评论信息 用户信息 和 被回复用户信息
     * @param mixCommentList 评论集合
     */
    public void buildMixCommentWithUserAndReplayUserInfo(List<MixComment> mixCommentList) {
        if (!CollectionUtils.isEmpty(mixCommentList)) {
            Set<Integer> uidSet = CollectionUtil.buildSet(mixCommentList, Integer.class, "uid"); // 获取用户信息
            Map<Integer, User> userMap = CollectionUtil.buildMap(userCoreDao.findUserById(uidSet, null, "id", "name", "avatar"), Integer.class, User.class, "id"); // 构建map

            Map<Long, MixComment> mixCommentMap = CollectionUtil.buildMap(mixCommentList, Long.class, MixComment.class, "id"); // 构建map
            for (MixComment mixComment : mixCommentList) {
                User user = userMap.get(mixComment.getUid());
                userCoreService.buildUserFileUrl(user);
                mixComment.setUser(user);
                mixComment.setReplyMixComment(mixCommentMap.get(mixComment.getReplyMixCommentId()));
            }
        }
    }
}
