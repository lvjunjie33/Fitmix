package com.business.bbs.article;

import com.business.bbs.advertisement.AdvertisementService;
import com.business.bbs.base.QxMap;
import com.business.bbs.base.support.BaseControllerSupport;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.Page;
import com.business.core.entity.bbs.Advertisement;
import com.business.core.entity.bbs.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by fenxio on 2016/9/14.
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController extends BaseControllerSupport {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private AdvertisementService advertisementService;

    /**
     * 文章页面
     * @return
     */
    @RequestMapping(value = "article/page", method = RequestMethod.GET)
    public String articlePage(Model model) {
        //广告
        List<Advertisement> advertisementList = advertisementService.findAdvertisementByModule(Advertisement.MODULE_ARTICLE);
        //运动早知道
        List<Article> sportList = articleService.findSportArticle();
        model.addAttribute("sportList", sportList)
             .addAttribute("advertisementList", advertisementList);
        return "bbs/article";
    }

    /**
     * 文章列表
     * @return
     */
    @RequestMapping(value = "articleList", method = RequestMethod.GET)
    @ResponseBody
    public Page<Article> article(Page<Article> page) {
        page.getFilter().put("status", Article.STATUS_RELEASE);
        articleService.findByPage(page, new QxMap<>("addTime", Sort.Direction.DESC));
        buildUrl(page.getResult());
        return page;
    }

    /**
     * 文章详情
     *
     * @param aid 文章编号
     * @return
     */
    @RequestMapping(value = "article/{aid}", method = RequestMethod.GET)
    public String articleInfo(@PathVariable("aid") Integer aid, Model model) {
        //运动早知道
        List<Article> sportList = articleService.findSportArticle();
        //浏览量+1
        articleService.incViewCountById(aid);
        model.addAttribute("article", articleService.findById(aid))
             .addAttribute("sportList", sportList);
        return "bbs/articleInfo";
    }

    /**
     * 构建地址
     * @param articleList
     */
    public void buildUrl(List<Article> articleList) {
        for(Article article : articleList) {
            article.setCoverUrl(FileCenterClient.buildUrl(article.getCoverUrl()));
        }
    }

}
