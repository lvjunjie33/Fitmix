package com.business.work.shop.banner;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.shop.ShopBanner;
import com.business.work.base.support.BaseControllerSupport;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangtao on 2016/11/25.
 */
@Controller
@RequestMapping("shop/banner")
public class ShopBannerController extends BaseControllerSupport {

    @Autowired
    private ShopBannerService shopBannerService;

    /**
     * 封面列表
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("banner-list")
    public String getBannerList(Page<ShopBanner> page, Model model) {
        PageHelper.startPage(page.getPageNo(), Page.DEFAULT_PAGE_SIZE).setOrderBy("sort desc");
        List<ShopBanner> list = shopBannerService.selectByParams(page.getFilter());
        page.setResult(list);
        page.setTotal(((com.github.pagehelper.Page) list).getTotal());
        model.addAttribute("page", page);
        return "shop/banner/banner-list";
    }

    /**
     * 跳转添加页面
     * @return
     */
    @RequestMapping(value = "banner-add", method = RequestMethod.GET)
    public String bannerAdd() {
        return "shop/banner/banner-add";
    }

    /**
     * 添加banner
     * @param banner
     */
    @RequestMapping(value = "banner-add", method = RequestMethod.POST)
    public void add(ShopBanner banner, @RequestParam("file") MultipartFile file) {
        if(file != null) {
            String path = FileCenterClient.upload(file, FileConstants.FILE_TYPE_SHOP_BANNER);
            banner.setBackImage(path);
        }
        banner.setAddTime(System.currentTimeMillis());
        banner.setModifyTime(System.currentTimeMillis());
        shopBannerService.insertSelective(banner);
    }

    /**
     * 跳转修改页面
     * @param id
     * @return
     */
    @RequestMapping(value = "banner-modify", method = RequestMethod.GET)
    public String bannerModify(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("banner", shopBannerService.selectByPrimaryKey(id));
        return "shop/banner/banner-modify";
    }

    /**
     * 修改banner
     * @param banner
     */
    @RequestMapping(value = "banner-modify", method = RequestMethod.POST)
    public void bannerModify(ShopBanner banner, MultipartFile file) {
        if(null != file) {
            banner.setBackImage(FileCenterClient.upload(file, FileConstants.FILE_TYPE_SHOP_BANNER));
        }
        banner.setModifyTime(System.currentTimeMillis());
        shopBannerService.updateByPrimaryKeySelective(banner);
    }

    /**
     * 删除banner
     * @param id
     */
    @RequestMapping("banner-remove")
    public void bannerRemove(@RequestParam("id") Integer id) {
        shopBannerService.deleteByPrimaryKey(id);
    }

}
