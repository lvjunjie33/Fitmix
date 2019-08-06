package com.business.work.community;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.community.discuss.Categorys;
import com.business.core.entity.community.discuss.Discuss;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.entity.user.User;
import com.business.core.operations.community.CategoryCoreDao;
import com.business.core.operations.community.CategoryCoreService;
import com.business.work.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by edward on 2017/6/1.
 */
@Service
public class CategorysService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CategorysDao categorysDao;
    @Autowired
    private CategoryCoreDao categoryCoreDao;
    @Autowired
    private CategoryCoreService categoryCoreService;

    /**
     * 板块分页
     *
     * @param page 板块实体
     */
    public void categoryList(Page<Categorys> page) {
        categorysDao.categoryPage(page);
    }

    /**
     * 保存板块
     *
     * @param title 标题
     * @param des 描述
     * @param loginName 操作人登录名
     */
    public void categoryAdd(String title, String des, Integer type, Integer isOpen, String loginName) {
        Categorys category = new Categorys();
        category.setDes(des);
        category.setTitle(title);
        category.setLoginName(loginName);

        category.setIsOpen(isOpen);
        category.setType(type);

        category.setStatus(Constants.STATE_INVALID);
        category.setAddTime(System.currentTimeMillis());

        categorysDao.saveCategory(category);
    }

    /**
     * 话题列表
     *
     * @param page 话题实体
     */
    public void themeList(Page<Theme> page) {
        page.getFilter().put("themeType", Theme.THEME_TYPE_PROBLEM);
        categorysDao.themeList(page);
        for (Theme theme : page.getResult()) {
            User user = userDao.findUserByUid(theme.getUid(), "avatar", "signature", "name");
            theme.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
            theme.setSignature(user.getSignature());
            theme.setName(user.getName());
            theme.setBackImage(FileCenterClient.buildUrl(theme.getBackImage()));
        }
    }

    /**
     * 添加话题
     *
     * @param title 标题
     * @param uid 用户编号
     */
    public void themeAdd(String title, Integer uid) {
        User user = userDao.findUserByUid(uid);
        Theme theme = new Theme();
        theme.setTitle(title);
        theme.setCategoryId(5);
        theme.setThemeType(Theme.THEME_TYPE_PROBLEM);

        theme.setUid(uid);
        theme.setName(user.getName());
        theme.setAvatar(user.getAvatar());

        theme.setAddTime(System.currentTimeMillis());

        theme.setIsConfirmed(Constants.CHECK_STATUS_READY);
        theme.setIsReply(Constants.ENABLED);
        theme.setIsSensitive(Constants.ENABLED);

        theme.setClickNum(0);
        theme.setUpNum(0);
        theme.setDiscussNum(0);
        theme.setTaoLunNum(0);

        categoryCoreDao.saveTheme(theme);
    }

    /**
     * 查询问题明细
     *
     * @param id 问题编号
     */
    public Theme findThemeById(Long id) {
        return categorysDao.findThemeById(id);
    }

    /**
     * 查询theme明细
     *
     * @param id 编号
     */
    public Theme themeToDetail(Long id) {
        Theme theme = categorysDao.findThemeById(id);
        theme.setAvatar(FileCenterClient.buildUrl(theme.getAvatar()));

        theme.setBackImage(FileCenterClient.buildUrl(theme.getBackImage()));
        return theme;
    }

    /**
     * 添加话题内容
     *
     * @param themeId 话题编号
     * @param content 话题内容
     */
    public void themeModifyContent(Long themeId, String content) {
        categoryCoreDao.modifyTheme(themeId, Update.update("content", content));
    }

    /**
     * 设置banner
     *
     * @param id 话题编号
     * @param sort 排序
     * @param uploadFile 文件
     */
    public void themeSetBanner(Long id, Integer sort, MultipartFile uploadFile) {
        String fileLink = "";
        Update update = new Update();
        if (uploadFile != null) {
            fileLink = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RESOURCE_FILE, uploadFile);
            update.set("backImage", fileLink);
        }
        update.set("bannerSort", sort);
        categoryCoreDao.modifyTheme(id, update);
    }

    /**
     * 加精
     *
     * @param id 编号
     * @param sort 加精排序值
     */
    public void themeSetFine(Long id, Integer sort) {
        categoryCoreDao.modifyTheme(id, Update.update("fine", sort));
    }

    /**
     * 取消banner设置
     *
     * @param id 编号
     */
    public void themeUnSetBanner(Long id) {
        categoryCoreDao.modifyTheme(id, new Update().unset("bannerSort").unset("backImage"));
    }

    public void themeHandleIsSensitive(Long themeId) {
        Theme theme = categorysDao.findThemeById(themeId, "isSensitive");
        if (Constants.ENABLED.equals(theme.getIsSensitive())) {
            categoryCoreDao.modifyTheme(themeId, Update.update("isSensitive", Constants.DISABLED));
        } else {
            categoryCoreDao.modifyTheme(themeId, Update.update("isSensitive", Constants.ENABLED));
        }
    }

    public void discussHandleIsSensitive(Long discussId) {
        Discuss discuss = categorysDao.findDiscussById(discussId, "isSensitive");
        if (Constants.ENABLED.equals(discuss.getIsSensitive())) {
            categoryCoreDao.modifyDiscussById(discussId, Update.update("isSensitive", Constants.DISABLED));
        } else {
            categoryCoreDao.modifyDiscussById(discussId, Update.update("isSensitive", Constants.ENABLED));
        }
    }

    /**
     * 手动审核
     *
     * @param themeId 话题编号
     */
    public void themeSetConfirmed(Long themeId) {
        Theme theme = categorysDao.findThemeById(themeId, "isConfirmed");
        if (Constants.CHECK_STATUS_SUCCESS.equals(theme.getIsConfirmed())) {
            categoryCoreDao.modifyTheme(themeId, Update.update("isConfirmed", Constants.CHECK_STATUS_READY));
        } else {
            categoryCoreDao.modifyTheme(themeId, Update.update("isConfirmed", Constants.CHECK_STATUS_SUCCESS));
        }
    }

    public void clear() {
        categorysDao.clear();
    }
}
