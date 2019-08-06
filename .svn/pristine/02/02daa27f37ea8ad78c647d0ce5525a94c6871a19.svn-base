package com.business.work.bbs.article;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.ApplicationConfig;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.bbs.Article;
import com.business.core.entity.bbs.Category;
import com.business.work.base.support.BaseControllerSupport;
import com.business.work.bbs.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/9/9.
 */
@Controller
@RequestMapping("bbs/article")
public class ArticleController extends BaseControllerSupport {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 跳转文章列表
     *
     * @param page
     * @return
     */
    @RequestMapping("article-list")
    public String categoryList(Page<Article> page) {
        articleService.list(page);
        return "bbs/article/article-list";
    }

    /**
     * 跳转 分类 添加页面
     *
     * @return
     */
    @RequestMapping(value = "article-add", method = RequestMethod.GET)
    public String add(Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList", categoryList);
        return "bbs/article/article-add";
    }

    /**
     * 保存文章 信息
     *
     * @param article
     * @param model
     */
    @RequestMapping(value = "article-add", method = RequestMethod.POST)
    public void add(Article article, MultipartFile file, Model model) throws Exception {
        if (file != null) {
            String path = FileCenterClient.upload(file, FileConstants.FILE_TYPE_BBS_ARTICLE);
            article.setCoverUrl(path);
        }
        article.setAddTime(System.currentTimeMillis());
        articleService.saveArticle(article);
        model.addAttribute("category", article);
    }

    /**
     * 跳转文章 修改信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "article-modify", method = RequestMethod.GET)
    public String articleModify(@RequestParam("id") Integer id, Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        Article article = articleService.findById(id);
        model.addAttribute("article", article)
             .addAttribute("categoryList", categoryList);
        return "bbs/article/article-modify";
    }

    /**
     * 跳转文章 修改信息
     *
     * @param article
     * @param model
     * @return
     */
    @RequestMapping(value = "article-modify", method = RequestMethod.POST)
    public void articleModify(Article article, Model model) {
        articleService.articleModify(article);
    }

    /**
     * 删除文章
     *
     * @param id
     */
    @RequestMapping(value = "article-remove", method = RequestMethod.POST)
    public void articleRemove(@RequestParam("id") Integer id) {
        articleService.deleteById(id);
    }


    /**
     * 上传文章图片
     */
    @RequestMapping("upload-img")
    public void uploadImg(HttpServletRequest request, Model model) {
        List<String> fileImgUrls = FileCenterClient.uploadFiles(request, FileConstants.FILE_TYPE_BBS_ARTICLE);
        if (!CollectionUtils.isEmpty(fileImgUrls)) {
            model.addAttribute("imgUrl", ApplicationConfig.FILECENTER_STORAGE_PATH + "/" + fileImgUrls.get(0));
        } else {
            tip(model, com.business.core.constants.CodeConstants.MISS, "上传文件失败!!!");
        }
    }

}
