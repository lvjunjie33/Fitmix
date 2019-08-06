package com.business.core.operations.community;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.RedisConstants;
import com.business.core.entity.Page;
import com.business.core.entity.community.discuss.CategoryMsg;
import com.business.core.entity.community.discuss.Discuss;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.entity.user.User;
import com.business.core.operations.message.MessageCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.redis.RedisMsgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by edward on 2017/7/6.
 */
@Service
public class CategoryCoreService {

    @Autowired
    private CategoryCoreDao categoryCoreDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private MessageCoreDao messageCoreDao;

    /**
     * 回答
     *
     * @param content 内容
     * @param parentTheme 问题
     */
    public Theme themeAddAnswer(User user, String content, Theme parentTheme) {
        Integer targetUid = parentTheme.getUid();
        Theme answer = new Theme();
        {//保存回答
            answer.setUid(user.getId());

            answer.setThemeType(Theme.THEME_TYPE_ANSWER);
            answer.setContent(content);
            answer.setParentThemeId(parentTheme.getId());
            answer.setAddTime(System.currentTimeMillis());
            answer.setCategoryId(5);
            answer.setAvatar(user.getAvatar());

            answer.setClickNum(0);
            answer.setUpNum(0);
            answer.setDiscussNum(0);
            answer.setTaoLunNum(0);

            //TODO 目前默认审核通过
            answer.setIsConfirmed(Constants.CHECK_STATUS_SUCCESS);
            answer.setIsReply(Constants.ENABLED);
            answer.setIsSensitive(Constants.ENABLED);

            categoryCoreDao.saveTheme(answer);
            //每次评论数加一
            categoryCoreDao.modifyTheme(parentTheme.getId(), new Update().inc("discussNum", 1));
        }
        {//加入消息列表
            if (answer.getUid() != targetUid) {
                RedisMsgManager.sendThemeAnswer(answer, targetUid, parentTheme.getTitle());
            }
        }
        answer.setAvatar(FileCenterClient.buildUrl(answer.getAvatar()));
        answer.setName(user.getName());
        return answer;
    }

    /**
     * 讨论
     *
     * @param content 内容
     * @param uid 用户编号
     * @param themeId 主题贴编号
     * @param discussUid 回复的用户编号
     * @param discussId 讨论的回答编号
     */
    public Discuss themeAddDiscuss(User user, String content, Integer uid, Long themeId, Integer discussUid, Long discussId) {
        Long currentTime = System.currentTimeMillis();
        Theme answer = categoryCoreDao.findThemeById(themeId, "uid", "parentThemeId");
        Theme theme = categoryCoreDao.findThemeById(answer.getParentThemeId(), "title");
        Discuss discuss = new Discuss();
        {//保存讨论信息
            discuss.setContent(content);
            discuss.setThemeId(themeId);

            discuss.setUid(uid);
            discuss.setUserName(user.getName());
            discuss.setAvatar(user.getAvatar());
            discuss.setAddTime(currentTime);

            discuss.setIsConfirmed(Constants.CHECK_STATUS_SUCCESS);
            discuss.setIsSensitive(Constants.ENABLED);

            discuss.setUpNum(0);

            if (!StringUtils.isEmpty(discussId)) {//@ xxx
                User target = userCoreDao.findUserById(discussUid, "name");
                discuss.setDiscussUName(target.getName());
                discuss.setDiscussUid(target.getId());
            }
            categoryCoreDao.saveDiscuss(discuss);
            //每次评论数加一
            categoryCoreDao.modifyTheme(themeId, new Update().inc("taoLunNum", 1));
        }

        {//加入消息列表

            //新增讨论 发送回答拥有者消息
            if (answer.getUid() != uid) {
                RedisMsgManager.sendAnswerDiscuss(discuss, answer.getUid(), discuss.getContent());
            }

            //B新增讨论 发送B @ A的消息
            if (discussId != null && discuss.getUid() != discussUid) {
                RedisMsgManager.sendAnswerDiscuss(discuss, discussUid, discuss.getContent());
            }
        }
        return discuss;
    }

    /**
     * 话题列表
     *
     * @param page 话题实体
     */
    public void themeList(Page<Theme> page, Integer uid) {
        page.getFilter().put("themeType", Theme.THEME_TYPE_PROBLEM);
        categoryCoreDao.themeList(page);
        for (Theme theme : page.getResult()) {
            List<Theme> nodes = categoryCoreDao.findPopularByThemeId(theme.getId(), Theme.SEARCH_TYPE_TAO_LUN_NUM, "id");
            if (!CollectionUtils.isEmpty(nodes)) {
                Long targetId = nodes.get(0).getId();
                if (targetId != null) {
                    Theme node = categoryCoreDao.findThemeById(targetId);
                    if (node != null) {
                        theme.setSelectNodeTheme(node);
                        handleTheme(node);
                    }
                }

            }
            //判断我是否回答了
            Theme myAnswer = categoryCoreDao.findThemeByUidAndParentThemeId(theme.getId(), uid, "uid");
            if (myAnswer != null) {
                theme.setAnswerId(myAnswer.getId());
            }
            handleTheme(theme);
        }
    }

    public void handleTheme(Theme theme) {
        if (theme == null) {
            return;
        }

        User user = userCoreDao.findUserById(theme.getUid(), "avatar", "signature", "name", "themeVip");
        if(user!=null) {
            theme.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
            theme.setSignature(user.getSignature());
            theme.setName(user.getName());
            theme.setThemeVip(user.getThemeVip());
        }
        if (!StringUtils.isEmpty(theme.getBackImage())) {
            theme.setBackImage(FileCenterClient.buildUrl(theme.getBackImage()));
        }
    }

    /**
     * 查询回答
     *
     * @param themeId 回答编号
     */
    public Theme findAnswerById(Long themeId) {
        Theme theme = categoryCoreDao.findThemeById(themeId);
        handleTheme(theme);
        return theme;
    }

    /**
     * 查询回答
     *
     * @param themeId 回答编号
     */
    public Theme findThemeById(Long themeId) {
        Theme theme = categoryCoreDao.findThemeById(themeId);
        handleTheme(theme);
        return theme;
    }

    /**
     * 获取某个问题的回答列表
     *
     * @param page 分页对象
     * @param themeId 问题编号
     * @param uid 用户编号
     */
    public void themeAnswerList(Page<Theme> page, Long themeId, Integer uid) {
        page.getFilter().put("themeType", Theme.THEME_TYPE_ANSWER);
        page.getFilter().put("parentThemeId", themeId);
        categoryCoreDao.themeList(page);

        for (Theme theme : page.getResult()) {
            handleTheme(theme);
        }
    }

    /**
     * 回复列表
     *
     * @param page 回复实体
     * @param themeId 话题编号
     */
    public void discussList(Page<Discuss> page, Long themeId, Integer uid) {
        page.getFilter().put("themeId", themeId);
        categoryCoreDao.discussList(page, Discuss.BASIC_INFO_FIELDS);


        if (!CollectionUtils.isEmpty(page.getResult())) {//更改消息读取状态
            Set<Integer> uids = new HashSet<>();
            for (Discuss discuss : page.getResult()) {
                uids.add(discuss.getUid());
            }

            List<User> users = userCoreDao.findUserById(uids, User.STATE_ACTIVATES, "name", "avatar", "themeVip");
            Map<Integer, User> userMap = new HashMap<>();
            for (User user : users) {
                userMap.put(user.getId(), user);
            }
            for (Discuss discuss : page.getResult()) {
                User user = userMap.get(discuss.getUid());
                if (user != null) {
                    discuss.setUserName(user.getName());
                    discuss.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
                    discuss.setThemeVip(user.getThemeVip());
                }
            }

            Discuss discuss = page.getResult().get(0);
            messageCoreDao.modifyMessageStatusByAnswerId(discuss.getId(), uid);
        }
    }

    /**
     *
     * @param content 内容
     * @param parentThemeId 父级编号
     * @param uid 用户编号
     */
    public Object[] themeAddAnswer(String content, Long parentThemeId, Integer uid, Integer waterArmy) {
        User user = RedisConstants.cacheGetOnlineUser(uid);
        if (Theme.IS_WATER_ARMY.equals(waterArmy)) {
            user = userCoreDao.findUserById(uid);
        }
        if (user == null) {//用户未登录
            return new Object[]{1};
        }
        Theme parentTheme = categoryCoreDao.findThemeById(parentThemeId, "uid", "title");
        if (parentTheme == null) {
            return new Object[]{2};
        }

        //每个用户只能对一个问题回答一次
        long count = categoryCoreDao.findThemeByUidAndParentThemeId(parentThemeId, uid);
        if (count > 0) {
            return new Object[]{3};
        }
        return new Object[]{0, themeAddAnswer(user, content, parentTheme)};
    }

    /**
     * 讨论
     *
     * @param content 内容
     * @param uid 用户编号
     * @param themeId 主题贴编号
     * @param discussUid 回复的用户编号
     * @param discussId 讨论的回答编号
     */
    public Object[] themeAddDiscuss(String content, Integer uid, Long themeId, Integer discussUid, Long discussId, Integer waterArmy) {
        User user = RedisConstants.cacheGetOnlineUser(uid);
        if (Theme.IS_WATER_ARMY.equals(waterArmy)) {
            user = userCoreDao.findUserById(uid);
        }
        if (user == null) {//用户未登录
            return new Object[]{1};
        }

        Theme theme = categoryCoreDao.findThemeById(themeId, "uid");
        if (theme == null) {//话题不存在
            return new Object[]{2};
        }
        return new Object[]{0, themeAddDiscuss(user, content, uid, themeId, discussUid, discussId)};
    }
}
