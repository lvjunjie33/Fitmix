package com.business.work.community;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.community.discuss.Categorys;
import com.business.core.entity.community.discuss.Discuss;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.entity.user.User;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.community.CategoryCoreService;
import com.business.core.utils.BeanManager;
import com.business.work.base.support.BaseControllerSupport;
import com.business.work.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by edward on 2017/5/31.
 */
@RequestMapping
@Controller
public class CategorysController extends BaseControllerSupport{

    @Autowired
    private CategorysService categorysService;
    @Autowired
    private CategoryCoreService categoryCoreService;
    @Autowired
    private UserService userService;

    /**
     * 板块列表
     */
    @RequestMapping("category/list")
    public String categoryList(Page<Categorys> page) {
        page.removeEmptys("status", "title");
        categorysService.categoryList(page);
        return "community/category/list";
    }

    /**
     * 添加板块
     *
     * @param title 板块标题
     * @param des 描述
     */
    @RequestMapping("category/add")
    public void categoryAdd(String title, String des, Integer type, Integer isOpen) {
        categorysService.categoryAdd(title, des, type, isOpen, getCurrentAdmin().getLoginName());
    }

    /**
     * 话题分页
     */
    @RequestMapping("theme/list")
    public String themeList(Page<Theme> page) {
        page.removeEmptys("title", "categoryId", "searchType", "searchText").convertInt("categoryId", "searchType");
        categorysService.themeList(page);
        handleThemeContent(page.getResult());
        return "community/theme/list";
    }


    /**
     * 新增theme
     *
     * @param title 标题
     * @param uid 用户编号
     */
    @RequestMapping("theme/add")
    public void themeAdd(String title, Integer uid) {
        if (title.length() > 50) {
            return;
        }
        categorysService.themeAdd(title, uid);
    }

    /**
     * 查询theme明细
     *
     * @param id 编号
     */
    @RequestMapping("theme/to-detail")
    public String themeToDetail(Model model, Long id) {
        Theme theme = categorysService.themeToDetail(id);
        if (theme != null) {
            theme.setContent(decode(theme.getContent()));
            theme.setContent(decode(theme.getContent()));
        }
        model.addAttribute("theme", theme);
        return "community/theme/detail";
    }

    /**
     * 话题设置banner
     *
     * @param id 编号
     * @param sort banner排序值
     * @param uploadFile 封面图
     */
    @RequestMapping("theme/set/banner")
    public void themeSetBanner(Long id, Integer sort, @RequestParam(value = "uploadFile", required =false) MultipartFile uploadFile) {
        categorysService.themeSetBanner(id, sort, uploadFile);
    }

    /**
     * 话题加精
     *
     * @param id 编号
     * @param sort 加精排序值
     */
    @RequestMapping("theme/set/fine")
    public void themeSetFine(Long id, Integer sort) {
        categorysService.themeSetFine(id, sort);
    }

    /**
     * 取消banner设置
     *
     * @param id 编号
     */
    @RequestMapping("theme/un-set/banner")
    public void themeUnSetBanner(Long id) {
        categorysService.themeUnSetBanner(id);
    }

    /**
     * 查询theme明细
     *
     * @param id 编号
     */
    @RequestMapping("theme/get")
    public void themeGet(Model model, Long id) {
        Theme theme = categorysService.findThemeById(id);
        //数据库解码
        theme.setContent(decode(theme.getContent()));
        //下发解码
        theme.setContent(decode(theme.getContent()));
        model.addAttribute("theme", theme);
    }

    /**
     * 去话题内容编辑页面
     *
     * @param themeId 话题编号
     */
    @RequestMapping(value = "theme/modify-content", method = RequestMethod.GET)
    public String themeModifyContent(Model model, Long themeId) {
        model.addAttribute("themeId", themeId);
        return "community/theme/modify-content";
    }

    /**
     * 编辑话题内容
     *
     * @param themeId 话题编号
     * @param content 话题内容
     */
    @RequestMapping(value = "theme/modify-content", method = RequestMethod.POST)
    public void themeModifyContent(Model model, Long themeId, String content) {
        if (StringUtils.isEmpty(content)) {
            tip(model, 9999, "坑货，不要上传空的啊");
            return;
        }
        //编码后再入库
        try {
            content = encodeTry(content);
            content = encodeTry(content);
        } catch (UnsupportedEncodingException e) {
            tip(model, 9999, "报错拉");
        }
        categorysService.themeModifyContent(themeId, content);
    }

    /**
     * 话题问题列表
     *
     * @param page 分页对象
     * @param uid 用户编号
     */
    @RequestMapping("theme/mimicry")
    public String themeMimicry(Model model, Page<Theme> page, @RequestParam(defaultValue = "1", required = false) Integer uid) {
            page.removeEmptys("title", "categoryId","searchText").convertInt("categoryId");
        categoryCoreService.themeList(page, uid);
        handleThemeContent(page.getResult());

        Set<Integer> uids = new HashSet<>();
        for (int i = 1; i < 30; i++) {
            uids.add(i);
        }
        List<User> users = userService.findUserByIdIn(uids);
        for (User user : users) {
            user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
        }
        model.addAttribute("users", users);
        return "community/theme/mimicry-page";
    }

    /**
     * 问题的回答列表
     *
     * @param themeId 问题编号
     */
    @RequestMapping("theme/answer/list")
    public void themeAnswerList(Page<Theme> page, Long themeId, Integer uid) {
        page.removeEmptys("searchType", "searchText").convertInt("searchType");
        categoryCoreService.themeAnswerList(page, themeId, uid);
        handleThemeContent(page.getResult());
    }

    /**
     * 问题、回答的讨论列表
     *
     * @param page 回复实体
     * @param themeId 话题编号
     */
    @RequestMapping("theme/discuss/list")
    public void discussList(Page<Discuss> page, Long themeId, Integer uid) {
        categoryCoreService.discussList(page, themeId, uid);
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
            tip(model, 9999, "坑货，不要上传空的啊");
            return;
        }
        content = encode(content);
        content = encode(content);
        Object[] obj = categoryCoreService.themeAddAnswer(content, parentThemeId, uid, Theme.IS_WATER_ARMY);
        if ((Integer)obj[0] == 3) {
            tip(model, 9999, "该帐号已经水过该贴了");
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
            tip(model, 9999, "坑货，不要上传空的啊");
            return;
        }
        Object[] obj = categoryCoreService.themeAddDiscuss(content, uid, themeId, discussUid, discussId, Theme.IS_WATER_ARMY);
        if ((Integer)obj[0] != 0) {
            tip(model, 9999, "报错咯" + obj[0]);
        }
    }

    private void handleThemeContent(List<Theme> themes) {
        if (CollectionUtils.isEmpty(themes)) {
            return;
        }
        for (Theme theme : themes) {
            //数据库解码
            theme.setContent(decode(theme.getContent()));
            //下发解码
            theme.setContent(decode(theme.getContent()));
            if (theme.getSelectNodeTheme() != null) {
                //数据库解码
                theme.getSelectNodeTheme().setContent(decode(theme.getSelectNodeTheme().getContent()));
                //下发解码
                theme.getSelectNodeTheme().setContent(decode(theme.getSelectNodeTheme().getContent()));
            }
        }
    }

    /**
     * 屏蔽 敏感 问题和回答
     *
     * @param themeId 问题/回答编号
     */
    @RequestMapping("theme/handle/is/sensitive")
    public void themeHandleIsSensitive(Long themeId) {
        categorysService.themeHandleIsSensitive(themeId);
    }

    /**
     * 手动审核
     *
     * @param themeId 话题编号
     */
    @RequestMapping("theme/set/is/confirmed")
    public void themeSetConfirmed(Long themeId) {
        categorysService.themeSetConfirmed(themeId);
    }

    /**
     * 屏蔽 敏感 讨论
     * @param discussId 讨论编号
     */
    @RequestMapping("discuss/handle/is/sensitive")
    public void discussHandleIsSensitive(Long discussId) {
        categorysService.discussHandleIsSensitive(discussId);
    }
}
