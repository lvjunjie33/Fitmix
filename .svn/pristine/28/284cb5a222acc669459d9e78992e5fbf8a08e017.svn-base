package com.business.work.bbs.banner;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.bbs.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by fenxio on 2016/9/9.
 */
@Controller
@RequestMapping("bbs/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * banner 首页
     * @return
     */
    @RequestMapping("banner-list")
    public String bannerList(Model model) {
        model.addAttribute("bannerList", bannerService.getAllBanner());
        return "bbs/banner/banner-list";
    }

    /**
     * 跳转添加页面
     * @return
     */
    @RequestMapping(value = "banner-add", method = RequestMethod.GET)
    public String bannerAdd() {
        return "bbs/banner/banner-add";
    }

    /**
     * 添加banner
     * @param banner
     */
    @RequestMapping(value = "banner-add", method = RequestMethod.POST)
    public void add(Banner banner,@RequestParam("file") MultipartFile file) {
        if(file != null) {
            String path = FileCenterClient.upload(file, FileConstants.FILE_TYPE_BBS_BANNER);
            banner.setBackImage(path);
        }
        bannerService.saveBanner(banner);
    }

    /**
     * 跳转修改页面
     * @param id
     * @return
     */
    @RequestMapping(value = "banner-modify", method = RequestMethod.GET)
    public String bannerModify(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("banner", bannerService.findById(id));
        return "bbs/banner/banner-modify";
    }

    /**
     * 修改banner
     * @param banner
     */
    @RequestMapping(value = "banner-modify", method = RequestMethod.POST)
    public void bannerModify(Banner banner, MultipartFile file) {
        if(null != file) {
            banner.setBackImage(FileCenterClient.upload(file, FileConstants.FILE_TYPE_BBS_BANNER));
        }
        bannerService.modifyBannerById(banner);
    }

    /**
     * 删除banner
     * @param id
     */
    @RequestMapping("banner-remove")
    public void bannerRemove(@RequestParam("id") Integer id) {
        bannerService.removeBannerById(id);
    }

}
