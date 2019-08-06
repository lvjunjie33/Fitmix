package com.business.bbs.dynamic;


import com.business.bbs.article.ArticleService;
import com.business.bbs.base.QxMap;
import com.business.bbs.base.support.BaseControllerSupport;
import com.business.bbs.mix.MixService;
import com.business.bbs.userRunRank.UserRunRankService;
import com.business.core.entity.Page;
import com.business.core.entity.bbs.Article;
import com.business.core.entity.dynamic.Comment;
import com.business.core.entity.dynamic.Dynamic;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.user.UserRunRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by fenxio on 2016/10/8.
 */
@Controller
@RequestMapping("/bbs")
public class DynamicController extends BaseControllerSupport {

    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private UserRunRankService userRunRankService;
    @Autowired
    private MixService mixService;
    @Autowired
    private ArticleService articleService;

    /**
     * 动态列表
     *
     * @return
     */
    @RequestMapping(value = "/dynamic/page", method = RequestMethod.GET)
    public String dynamic(Model model) {
        //榜单
        List<UserRunRank> rankList = userRunRankService.getRankList();
        Mix mix = mixService.getRank();

        //运动早知道
        List<Article> sportList = articleService.findSportArticle();

        model.addAttribute("rankList", rankList)
                .addAttribute("mix", mix)
                .addAttribute("sportList", sportList);
        return "bbs/dynamic";
    }

    /**
     * 获取动态列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/dynamicList", method = RequestMethod.GET)
    @ResponseBody
    public Page<Dynamic> page(Page<Dynamic> page) {
        dynamicService.findByPage(page, new QxMap<>("addTime", Sort.Direction.ASC));
        dynamicService.buildData(page);
        return page;
    }

    /**
     * 获取动态评论列表
     * @param did
     * @param index
     * @return
     */
    @RequestMapping(value = "/dynamic/comment", method = RequestMethod.GET)
    @ResponseBody
    public Page<Comment> commentPage(@RequestParam("did") Long did,
                                     @RequestParam("index") Integer index) {
       return dynamicService.findCommentByPage(did, index);
    }



}
