package com.business.app.community;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.community.discuss.Discuss;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.operations.community.CategoryCoreService;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2017/5/31.
 */
@Controller
@RequestMapping
public class CategoryController extends BaseControllerSupport{

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryCoreService categoryCoreService;

    /**
     * 上传图片
     *
     * 之后记录uid上传记录
     */
    @RequestMapping("upload/img")
    public void uploadImage(Model model, HttpServletRequest request, Integer uid) {
        Map<String, MultipartFile> fileMap = FileCenterClient.buildMultipartFile(request);
        MultipartFile img = fileMap.get("img");
        if (img == null) {
            tip(model, CodeConstants.IMG_UP_LOAD_IS_NULL);
            //文件为空
        } else {
            model.addAttribute("link", categoryService.uploadImage(img));
        }
    }

    /**
     * 添加话题
     *
     * @param title 标题
     * @param content 内容
     */
    @RequestMapping("theme/add")
    public void themeAdd(Model model, String title, String content, Integer uid) throws UnsupportedEncodingException {
        if(content.contains("script")) {
            tip(model, CodeConstants.THEME_CONTENT_ERROR);
            return;
        };

        if (title.length() < 4 && title.length() > 50) {
            tip(model, CodeConstants.THEME_CONTENT_ERROR);
            return;
        }

        Object[] result = categoryService.themeAdd(title, encode(content), uid);
        switch ((int)result[0]) {
            case 0:
                Theme theme = (Theme) result[1];
                if (theme != null) {
                    //下发解码
                    theme.setContent(decode(theme.getContent()));
                }
                model.addAttribute("theme", theme);
                break;
            case 1:
                tip(model, CodeConstants.LOGIN_USER_STATE_NO_LOGIN);
                break;
        }
    }

    /**
     * 提交问题的答案
     *
     * @param content 内容
     * @param parentThemeId 父级编号
     * @param uid 用户编号
     */
    @RequestMapping("theme/add/answer")
    public void themeAddAnswer(Model model, String content, Long parentThemeId, Integer uid) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(content)) {
            tip(model, CodeConstants.THEME_CONTENT_NULL);
            return;
        }
        if(content.contains("script")) {
            tip(model, CodeConstants.THEME_CONTENT_ERROR);
            return;
        };
        Object[] result = categoryCoreService.themeAddAnswer(encode(content), parentThemeId, uid, null);
        switch ((int)result[0]) {
            case 0:
                Theme theme = (Theme) result[1];
                if (theme != null) {
                    //下发解码
                    theme.setContent(decode(theme.getContent()));
                }
                model.addAttribute("theme", result[1]);
                break;
            case 1:
                tip(model, CodeConstants.LOGIN_USER_STATE_NO_LOGIN);
                break;
            case 2:
                tip(model, CodeConstants.THEME_IS_NULL_ERROR);
                break;
            case 3:
                tip(model, CodeConstants.THEME_ERROR_ANSWER);
                break;
        }
    }

    /**
     * 修改问题的答案
     *
     * @param themeId 回答的编号
     * @param content 回答的内容
     * @param uid 用户编号
     */
    @RequestMapping("theme/modify/answer")
    public void themeModifyAnswer(Model model, Long themeId, String content, Integer uid) {
        try {
            int code = categoryService.themeModifyAnswer(themeId, encodeTry(content), uid);
            if (code != 0) {
                tip(model, CodeConstants.THEME_OPERATION_ERROR);
            }
        } catch (UnsupportedEncodingException e) {
            tip(model, CodeConstants.THEME_ERROR_ANSWER);
        }
    }

    /**
     * 讨论
     *
     * @param content 内容
     * @param uid 用户编号
     * @param themeId 回答编号
     * @param discussUid 回复的用户编号
     * @param discussId 回复的讨论编号
     */
    @RequestMapping("theme/add/discuss")
    public void themeAddDiscuss(Model model, String content, Integer uid, Long themeId, @RequestParam(required=false) Integer discussUid, @RequestParam(required=false) Long discussId) {
        if (StringUtils.isEmpty(content)) {
            tip(model, CodeConstants.THEME_CONTENT_NULL);
            return;
        }
        if(content.contains("script")) {
            tip(model, CodeConstants.THEME_CONTENT_ERROR);
            return;
        };
        Object[] result = categoryCoreService.themeAddDiscuss(content, uid, themeId, discussUid, discussId, null);
        switch ((int)result[0]) {
            case 0:
                Discuss discuss = (Discuss) result[1];
                model.addAttribute("discuss", discuss);
                break;
            case 1:
                tip(model, CodeConstants.LOGIN_USER_STATE_NO_LOGIN);
                break;
            case 2:
                tip(model, CodeConstants.THEME_IS_NULL_ERROR);
                break;
        }
    }

    /**
     * 话题点赞观点
     *
     * @param uid 操作的用户
     * @param type 观点类型(1、点赞，0、中立不操作)
     */
    @RequestMapping("theme/opinion")
    public void themeOpinion(Model model, Long themeId, Integer uid, Integer type) {
        Theme theme = categoryService.themeOpinion(themeId, uid, type);
        model.addAttribute("upNum", theme.getUpNum());
    }

    /**
     * 回复点赞观点
     *
     * @param uid 操作的用户
     * @param type 观点类型(1、点赞,0、中立不操作)
     */
    @RequestMapping("discuss/opinion")
    public void discussOpinion(Model model, Long discussId, Integer uid, Integer type) {
        Discuss discuss = categoryService.discussOpinion(discussId, uid, type);
        model.addAttribute("upNum", discuss.getUpNum());
    }

    /**
     * 普通话题列表
     *
     * @param page 话题实体 filter['searchText']=xxx&filter['searchType']=1 (2=回答，1问题)
     */
    @RequestMapping("theme/list")
    public void themeList(Page<Theme> page, Integer uid) {
        page.removeEmptys("searchText", "searchType").convertInt("searchType");

        page.getFilter().put("isConfirmed", Constants.CHECK_STATUS_SUCCESS);
        page.getFilter().put("isSensitive", Constants.ENABLED);

        categoryCoreService.themeList(page, uid);
        handleThemeContent(page.getResult());
    }

    /**
     * banner 推荐话题列表
     * @param uid 用户编号
     */
    @RequestMapping("theme/banners")
    public void themeBanners(Model model, Integer uid) {
        List<Theme> themes = categoryService.themeBanners(uid);
        handleThemeContent(themes);
        model.addAttribute("banners", themes);
    }

    /**
     * 问题的回答列表
     *
     * @param themeId 问题编号
     */
    @RequestMapping("theme/answer/list")
    public void themeAnswerList(Model model, Page<Theme> page, Long themeId, Integer uid) {
        page.removeEmptys("searchType", "searchText", "getTheme").convertInt("searchType");

        page.getFilter().put("isConfirmed", Constants.CHECK_STATUS_SUCCESS);
        page.getFilter().put("isSensitive", Constants.ENABLED);

        categoryCoreService.themeAnswerList(page, themeId, uid);
        handleThemeContent(page.getResult());
        if (page.getFilter().containsKey("getTheme")) {
            Theme theme = categoryCoreService.findThemeById(themeId);
            if (theme != null) {
                theme.setContent(decode(theme.getContent()));
                model.addAttribute("theme", theme);
            }
        }
    }

    /**
     * 获取回答
     *
     * @param themeId 回答的编号(不是问题的编号)
     */
    @RequestMapping("theme/answer/get")
    public void Answer(Model model, Long themeId, Integer uid) {
        Theme theme = categoryCoreService.findAnswerById(themeId);
        if (theme != null) {
            theme.setContent(decode(theme.getContent()));
        }
        model.addAttribute("answer", theme);

    }

    /**
     * 问题、回答的讨论列表
     *
     * @param page 回复实体
     * @param themeId 话题编号
     */
    @RequestMapping("theme/discuss/list")
    public void discussList(Page<Discuss> page, Long themeId, Integer uid) {

        page.getFilter().put("isConfirmed", Constants.CHECK_STATUS_SUCCESS);
        page.getFilter().put("isSensitive", Constants.ENABLED);

        categoryCoreService.discussList(page, themeId, uid);
    }

    /**
     * 下一篇 回答
     *
     * @param answerId 回答的编号
     */
    @RequestMapping("theme/next/answer")
    public void getNextAnswer(Model model, Long answerId, Integer sortType) {
        Theme answer = categoryService.getNextAnswer(answerId, sortType);
        if (answer != null) {
            answer.setContent(decode(answer.getContent()));
        }
        model.addAttribute("answer", answer);
    }

    /**
     * 上一篇回答
     *
     * @param answerId 回答的编号
     */
    @RequestMapping("theme/up/answer")
    public void getUpAnswer(Model model, Long answerId, Integer sortType) {
        Theme answer = categoryService.getUpTheme(answerId, sortType);
        if (answer != null) {
            answer.setContent(decode(answer.getContent()));
        }
        model.addAttribute("answer", answer);
    }

    /**
     * 自己的问题列表
     *
     * @param page 分页对象
     * @param uid 用户编号
     */
    @Deprecated
    @RequestMapping("my/theme/list")
    public void myTheme(Model model, Page<Theme> page, Integer uid) {
        if (StringUtils.isEmpty(uid)) {
            return;
        }
        Integer newMsgSum = categoryService.myTheme(page, uid);
        model.addAttribute("newMsgSum", newMsgSum);
        handleThemeContent(page.getResult());
    }

    /**
     * 自己的回答列表
     *
     * @param page 分页对象
     * @param uid 用户编号
     */
    @Deprecated
    @RequestMapping("my/answer/list")
    public void myAnswer(Model model, Page<Theme> page, Integer uid) {
        if (StringUtils.isEmpty(uid)) {
            return;
        }
        Integer newMsgSum = categoryService.myAnswer(page, uid);
        model.addAttribute("newMsgSum", newMsgSum);
        handleThemeContent(page.getResult());
    }

    /**
     * 自己的讨论列表
     */
    @Deprecated
    @RequestMapping("my/discuss/list")
    public void myDiscuss(Model model, Page<Discuss> page, Integer uid) {
//        Integer newMsgSum = categoryService.myDiscuss(page, uid);
//        model.addAttribute("newMsgSum", newMsgSum);
    }

    /**
     * 获取与自己相关的，有新的回答问题列表 (废弃)
     *
     * @param uid 用户编号
     */
    @Deprecated
    @RequestMapping("my/new/theme/msg")
    public void myNewThemeMsg(Model model, Integer uid) {
//        List<Theme> themes = categoryService.myNewThemeMsg(uid);
//        handleThemeContent(themes);
//        model.addAttribute("themes", Collections.EMPTY_LIST);
    }

    /**
     *
     * 获取与自己相关的，有新的讨论的回答列表 (废弃)
     *
     * @param uid 用户编号
     */
    @Deprecated
    @RequestMapping("my/new/discuss/msg")
    public void myNewDiscussMsg(Model model, Integer uid) {
        List<Discuss> discuss = categoryService.myNewDiscussMsg(uid);
        model.addAttribute("discuss", discuss);
        model.addAttribute("discuss", Collections.EMPTY_LIST);
    }

    /**
     * 已阅消息 (废弃)
     *
     * @param id 编号
     * @param uid 用户编号
     * @param msgType 消息类型
     */
    @Deprecated
    @RequestMapping("my/message/read")
    public void myMessageRead(Model model, Long id, Integer uid, Integer msgType) {
//        categoryService.readMsg(id, uid, msgType);
    }

    private void handleThemeContent(List<Theme> themes) {
        if (CollectionUtils.isEmpty(themes)) {
            return;
        }
        for (Theme theme : themes) {
            theme.setContent(decode(theme.getContent()));
            if (theme.getSelectNodeTheme() != null) {
                theme.getSelectNodeTheme().setContent(decode(theme.getSelectNodeTheme().getContent()));
            }

            if (theme.getParentTheme() != null) {
                theme.getParentTheme().setContent(decode(theme.getParentTheme().getContent()));
            }
        }
    }

    /**
     * 分享页
     *
     * @param answerId 回答编号
     */
    @RequestMapping("/theme/to/answer")
    public String toAnswer(Model model, Long answerId) {
        Theme answer = categoryService.toAnswer(answerId);
        answer.setContent(decode(answer.getContent()));
        answer.setContent(decode(answer.getContent()));
        model.addAttribute("answer", answer);
        model.addAttribute("addTime", DateUtil.formatTimestamp(answer.getAddTime(), "yyyy年/MM/dd HH:mm:ss"));
        if (answer == null) {
            return "/common/404";
        }
        return "community/to-answer";
    }
}
