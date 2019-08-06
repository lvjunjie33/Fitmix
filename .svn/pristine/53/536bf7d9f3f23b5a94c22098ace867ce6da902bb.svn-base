package com.business.app.community;

import com.business.app.message.MessageDao;
import com.business.app.user.UserDao;
import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.constants.MsgConstants;
import com.business.core.constants.RedisConstants;
import com.business.core.entity.Page;
import com.business.core.entity.community.discuss.CategoryMsg;
import com.business.core.entity.community.discuss.Discuss;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.User;
import com.business.core.entity.user.info.UserDevice;
import com.business.core.operations.community.CategoryCoreDao;
import com.business.core.operations.community.CategoryCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by edward on 2017/5/31.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryCoreDao categoryCoreDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private CategoryCoreService categoryCoreService;
    @Autowired
    private MessageDao messageDao;

    /**
     * 添加话题
     *
     * @param title 标题
     * @param content 内容
     * @param uid 用户编号
     */
    public Object[] themeAdd(String title, String content, Integer uid) {
        User user = RedisConstants.cacheGetOnlineUser(uid);
        if (user == null) {//用户未登录
            return new Object[]{1};
        }

        Theme theme = new Theme();
        theme.setUid(uid);

        theme.setThemeType(Theme.THEME_TYPE_PROBLEM);
        theme.setContent(content);
        theme.setTitle(title);
        theme.setAddTime(System.currentTimeMillis());
        theme.setCategoryId(5);

        theme.setClickNum(0);
        theme.setUpNum(0);
        theme.setDiscussNum(0);
        theme.setTaoLunNum(0);

        //TODO 目前默认审核通过
        theme.setIsConfirmed(Constants.CHECK_STATUS_SUCCESS);
        theme.setIsReply(Constants.ENABLED);
        theme.setIsSensitive(Constants.ENABLED);

//        theme.setFine();

        categoryCoreDao.saveTheme(theme);

        theme.setAvatar(FileCenterClient.buildUrl(theme.getAvatar()));
        theme.setName(user.getName());
        return new Object[]{0, theme};
    }

    /**
     * 修改回答的内容
     *
     * @param themeId 回答编号
     * @param content 回答的内容
     * @param uid 用户编号
     */
    public Integer themeModifyAnswer(Long themeId, String content, Integer uid) {
        Theme theme = categoryCoreDao.findThemeById(themeId, "uid", "themeType");
        if (theme == null || !theme.getUid().equals(uid) || !Theme.THEME_TYPE_ANSWER.equals(theme.getThemeType())) {
            return 1;//非法操作
        }
        categoryCoreDao.modifyTheme(themeId, Update.update("content", content));
        return 0;
    }

    /**
     * 上传图片
     *
     * @param file 上传的文件
     */
    public String uploadImage(MultipartFile file) {
        String img = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_USER_RUN_DETAIL, file);
        return FileCenterClient.buildUrl(img);
    }



    /**
     * 获取banner 列表
     */
    public List<Theme> themeBanners(Integer uid) {
        List<Theme> banners = categoryDao.findBannerTheme();
        for (Theme theme : banners) {
            List<Theme> nodes = categoryCoreDao.findPopularByThemeId(theme.getId(), Theme.SEARCH_TYPE_TAO_LUN_NUM, "id");
            if (!CollectionUtils.isEmpty(nodes)) {
                Long targetId = nodes.get(0).getId();
                if (targetId != null) {
                    Theme node = categoryCoreDao.findThemeById(targetId);
                    if (node != null) {
                        theme.setSelectNodeTheme(node);
                        categoryCoreService.handleTheme(node);
                    }
                }
            }

            //判断我是否回答了
            Theme myAnswer = categoryCoreDao.findThemeByUidAndParentThemeId(theme.getId(), uid, "uid");
            if (myAnswer != null) {
                theme.setAnswerId(myAnswer.getId());
            }
            categoryCoreService.handleTheme(theme);
        }
        return banners;
    }

    /**
     * 主题帖点赞
     *
     * @param themeId 话题编号
     * @param uid 用户编号
     * @param type 点赞类型
     */
    public Theme themeOpinion(Long themeId, Integer uid, Integer type) {
        Theme theme = categoryCoreDao.findThemeById(themeId, "upUserIds", "upNum");
        if (type == null) {
            return theme;
        }

        if (Theme.OPINION_UP.equals(type)) {
            if (CollectionUtils.isEmpty(theme.getUpUserIds())) {
                categoryCoreDao.modifyTheme(themeId, new Update().inc("upNum", 1).push("upUserIds", uid));
            } else if(!theme.getUpUserIds().contains(uid)) {
                categoryCoreDao.modifyTheme(themeId, new Update().inc("upNum", 1).push("upUserIds", uid));
            }
        } else if (Theme.OPINION_FLAT.equals(type)) {
            for (Integer u : theme.getUpUserIds()) {
                if (uid == u) {
                    categoryCoreDao.modifyTheme(themeId, new Update().inc("upNum", -1).pull("upUserIds", uid));
                    break;
                }
            }
        }
        theme = categoryCoreDao.findThemeById(themeId, "upNum");

        return theme;
    }

    /**
     * 回复点赞
     *
     * @param discussId 回复编号
     * @param uid 用户编号
     * @param type 点赞类型
     */
    public Discuss discussOpinion(Long discussId, Integer uid, Integer type) {
        Discuss discuss = categoryCoreDao.findDiscussById(discussId, "upUserIds", "upNum");
        if (type == null) {
            return discuss;
        }

        if (Theme.OPINION_UP.equals(type)) {
            if (CollectionUtils.isEmpty(discuss.getUpUserIds())) {
                categoryCoreDao.modifyDiscussById(discussId, new Update().inc("upNum", 1).push("upUserIds", uid));
            } else if(!discuss.getUpUserIds().contains(uid)) {
                categoryCoreDao.modifyDiscussById(discussId, new Update().inc("upNum", 1).push("upUserIds", uid));
            }
        } else if (Theme.OPINION_FLAT.equals(type)) {
            for (Integer u : discuss.getUpUserIds()) {
                if (uid == u) {
                    categoryCoreDao.modifyDiscussById(discussId, new Update().inc("upNum", -1).pull("upUserIds", uid));
                    break;
                }
            }
        }

        discuss = categoryCoreDao.findDiscussById(discussId, "upNum");

        return discuss;
    }

    /**
     * 下一篇回答
     *
     * @param answerId 回答编号
     */
    public Theme getNextAnswer(Long answerId, Integer sortType) {
        Theme answer = categoryCoreDao.findThemeById(answerId, "parentThemeId", "addTime", "upNum");
        List<Theme> themes = categoryCoreDao.findPopularByThemeId(answer.getParentThemeId(), sortType, "id");
        if (CollectionUtils.isEmpty(themes)) {
            return null;
        }
        Long targetId = null;
        for (int i = 0, count = themes.size(); i < count; i++) {
            if (answer.getId().equals(themes.get(i).getId())) {
                if (i + 1 < count) {
                    targetId = themes.get(i + 1).getId();
                }
                break;
            }
        }
        if (targetId == null) {
            return null;
        }
        Theme theme = categoryCoreDao.findThemeById(targetId);
        categoryCoreService.handleTheme(theme);
        return theme;
    }
    /**
     * 上一篇回答
     *
     * @param answerId 回答编号
     */
    public Theme getUpTheme(Long answerId, Integer sortType) {
        Theme answer = categoryCoreDao.findThemeById(answerId, "parentThemeId", "addTime", "upNum");
        List<Theme> themes = categoryCoreDao.findPopularByThemeId(answer.getParentThemeId(), sortType, "id");
        if (CollectionUtils.isEmpty(themes)) {
            return null;
        }
        Long targetId = null;
        for (int i = 0; i < themes.size(); i++) {
            if (answer.getId().equals(themes.get(i).getId())) {
                if (i - 1 >= 0) {
                    targetId = themes.get(i -1).getId();
                    break;
                }
            }
        }
        if (targetId == null) {
            return null;
        }
        Theme theme = categoryCoreDao.findThemeById(targetId);
        categoryCoreService.handleTheme(theme);
        return theme;
    }

    public Theme toAnswer(Long answerId) {
        Theme answer = categoryCoreService.findAnswerById(answerId);
        if (answer == null) {
            return null;
        }

        Theme theme = categoryCoreDao.findThemeById(answer.getParentThemeId(), "title");
        answer.setParentTheme(theme);
        return answer;
    }

    /**
     * 获取自己的问题列表
     *
     * @param page 分页实体
     * @param uid 用户编号
     */
    public Integer myTheme(Page<Theme> page, Integer uid) {
        List<Message> messages = messageDao.findMyThemeMsgByUid(uid.toString(), MsgConstants.CHANNEL_THEME_ANSWER, MsgConstants.HANDLE_STATUS_FALSE, "msgBody.themeId");
        page.getFilter().put("uid", uid);
        page.getFilter().put("themeType", Theme.THEME_TYPE_PROBLEM);
        categoryCoreDao.themeList(page);
        for (Theme theme : page.getResult()) {
            if (theme.getNewMsgNum() == null) {
                theme.setNewMsgNum(0);
            }

            for (Message msg : messages) {
                Map<String, String> body = (Map<String, String>) msg.getMsgBody();
                if (!theme.getId().equals(body.get("themeId"))) {
                    continue;
                }

                theme.setNewMsgNum(theme.getNewMsgNum() + 1);
            }
            categoryCoreService.handleTheme(theme);
        }
        handMsgStatus(messages);
        return messages.size();
    }

    /**
     * 我的回答列表
     *
     * @param page 消息
     * @param uid 用户编号
     */
    public Integer myAnswer(Page<Theme> page, Integer uid) {
        List<Message> messages = messageDao.findMyThemeMsgByUid(uid.toString(), MsgConstants.CHANNEL_ANSWER_DISCUSS, MsgConstants.HANDLE_STATUS_FALSE, "msgBody.answerId");
        page.getFilter().put("uid", uid);
        page.getFilter().put("themeType", Theme.THEME_TYPE_ANSWER);
        categoryCoreDao.themeList(page);
        for (Theme answer : page.getResult()) {
            if (answer.getNewMsgNum() == null) {
                answer.setNewMsgNum(0);
            }

            for (Message msg : messages) {
                Map<String, String> body = (Map<String, String>) msg.getMsgBody();
                if (!answer.getId().equals(body.get("answerId"))) {
                    continue;
                }
                answer.setNewMsgNum(answer.getNewMsgNum() + 1);
            }
            Theme parent = categoryDao.findThemeById(answer.getParentThemeId());
            answer.setParentTheme(parent);
            categoryCoreService.handleTheme(parent);
            categoryCoreService.handleTheme(answer);
        }

        handMsgStatus(messages);
        return messages.size();
    }

    private void handMsgStatus(List<Message> messages) {
        if (!CollectionUtils.isEmpty(messages)) {
            Set<Long> msgIds = new HashSet<>();
            for (Message message : messages) {
                msgIds.add(message.getId());
            }
            messageDao.modifyThemStatusByIds(msgIds);
        }
    }

    /**
     * 获取自己的讨论列表
     *
     * @param page 分页实体
     * @param uid 用户编号
     */
//    public Integer myDiscuss(Page<Discuss> page, Integer uid) {
//        page.getFilter().put("uid", uid);
//        categoryCoreDao.discussList(page);
//        for (Discuss discuss : page.getResult()) {
//            discuss.setAvatar(FileCenterClient.buildUrl(discuss.getAvatar()));
//        }
//        return 0;
//    }

//    /**
//     * 获取与自己相关的，有新的回答问题列表
//     *
//     * @param uid 用户编号
//     */
//    public List<Theme> myNewThemeMsg(Integer uid) {
//        List<CategoryMsg> categoryMsgs = categoryDao.findCategoryMsgByUid(uid, CategoryMsg.TYPE_THEME_ANSWER, Constants.STATE_EFFECTIVE, "targetId");
//        Set<Long> themeIds = new HashSet<>();
//        for (CategoryMsg msg : categoryMsgs) {
//            themeIds.add(msg.getTargetId());
//        }
//        List<Theme> themes = categoryDao.findThemes(new ArrayList<>(themeIds), uid);
//        for (Theme theme : themes) {
//            categoryCoreService.handleTheme(theme);
//        }
//        return themes;
//    }

    /**
     * 获取与自己相关的，有新的讨论的回答列表
     *
     * @param uid 用户编号
     */
    public List<Discuss> myNewDiscussMsg(Integer uid) {
        List<CategoryMsg> categoryMsgs = categoryDao.findCategoryMsgByUid(uid, CategoryMsg.TYPE_THEME_DISCUSS, Constants.STATE_EFFECTIVE, "targetId");
        Set<Long> discussIds = new HashSet<>();
        for (CategoryMsg msg : categoryMsgs) {
            discussIds.add(msg.getTargetId());
        }
        List<Discuss> discusses = categoryDao.findDiscuss(new ArrayList<Long>(discussIds));
        for (Discuss discuss : discusses) {
            discuss.setAvatar(FileCenterClient.buildUrl(discuss.getAvatar()));
        }
        return discusses;
    }
}
