package com.business.bbs.home;

import com.business.bbs.advertisement.AdvertisementService;
import com.business.bbs.article.ArticleService;
import com.business.bbs.banner.BannerService;
import com.business.bbs.base.support.BaseControllerSupport;
import com.business.bbs.mix.MixService;
import com.business.bbs.recommend.RecommendService;
import com.business.bbs.userRunRank.UserRunRankService;
import com.business.core.entity.bbs.Advertisement;
import com.business.core.entity.bbs.Article;
import com.business.core.entity.bbs.Banner;
import com.business.core.entity.bbs.Recommend;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.user.UserRunRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by fenxio on 2016/9/7.
 */
@Controller
@RequestMapping("/bbs")
public class HomeController extends BaseControllerSupport {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private UserRunRankService userRunRankService;
    @Autowired
    private MixService mixService;
    @Autowired
    private AdvertisementService advertisementService;

    /**
     * 社区首页
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(Model model) {
        //封面
        List<Banner> bannerList = bannerService.getBannerList();
        buildBannerUrl(bannerList);

        //文章列表
        List<Article> articleList = articleService.getIndexArticleList();

        //推荐列表
        List<Recommend> recommendList = recommendService.getAllRecommend();

        //榜单
        List<UserRunRank> rankList = userRunRankService.getRankList();
        Mix mix = mixService.getRank();

        //广告
        List<Advertisement> advertisementList = advertisementService.findAdvertisementByModule(Advertisement.MODULE_BBS);

        //运动早知道
        List<Article> sportList = articleService.findSportArticle();

        model.addAttribute("bannerList", bannerList)
             .addAttribute("articleList", articleList)
             .addAttribute("recommendList", recommendList)
             .addAttribute("rankList", rankList)
             .addAttribute("mix", mix)
             .addAttribute("advertisementList", advertisementList)
             .addAttribute("sportList", sportList);
        return "bbs/home";
    }



    /**
     * 构建banner跳转路径
     * @param list
     */
    public void buildBannerUrl(List<Banner> list) {
        for (Banner banner : list) {
            if (banner.getTypeValue() != null && !banner.getTypeValue().startsWith("http")) {
                if (banner.getType() == Banner.TYPE_ARTICLE) {
                    banner.setTypeValue("/bbs/article/" + banner.getTypeValue());
                }
            }
        }
    }

}
