package com.business.work.bbs.recommend;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.bbs.Banner;
import com.business.core.entity.bbs.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by fenxio on 2016/9/14.
 */
@Controller
@RequestMapping("bbs/recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    /**
     * 推荐 首页
     * @return
     */
    @RequestMapping("recommend-list")
    public String bannerList(Model model) {
        model.addAttribute("recommendList", recommendService.getAllRecommend());
        return "bbs/recommend/recommend-list";
    }

    /**
     * 跳转添加页面
     * @return
     */
    @RequestMapping(value = "recommend-add", method = RequestMethod.GET)
    public String recommendAdd() {
        return "bbs/recommend/recommend-add";
    }

    /**
     * 添加 推荐信息
     * @param recommend
     */
    @RequestMapping(value = "recommend-add", method = RequestMethod.POST)
    public void add(Recommend recommend, @RequestParam("file") MultipartFile file) {
        if(file != null) {
            String path = FileCenterClient.upload(file, FileConstants.FILE_TYPE_BBS_RECOMMEND);
            recommend.setBackImage(path);
        }
        recommendService.saveRecommend(recommend);
    }

    /**
     * 跳转修改页面
     * @param id
     * @return
     */
    @RequestMapping(value = "recommend-modify", method = RequestMethod.GET)
    public String bannerModify(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("recommend", recommendService.findRecommendById(id));
        return "bbs/recommend/recommend-modify";
    }

    /**
     * 修改 推荐信息
     * @param recommend
     */
    @RequestMapping(value = "recommend-modify", method = RequestMethod.POST)
    public void bannerModify(Recommend recommend, MultipartFile file) {
        if(null != file) {
            recommend.setBackImage(FileCenterClient.upload(file, FileConstants.FILE_TYPE_BBS_BANNER));
        }
        recommendService.modifyRecommendById(recommend);
    }

    /**
     * 删除 推荐信息
     * @param id
     */
    @RequestMapping("recommend-remove")
    public void bannerRemove(@RequestParam("id") Integer id) {
        recommendService.removeRecommendById(id);
    }


}
