package com.business.work.wd;

import com.business.core.entity.Page;
import com.business.core.entity.mix.MixBanner;
import com.business.core.entity.wd.Speedway;
import com.business.work.base.support.BaseControllerSupport;
import com.business.work.mixBanner.MixBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by edward on 2016/7/19.
 */
@Controller
@RequestMapping("speedway")
public class SpeedwayController extends BaseControllerSupport {

    @Autowired
    private SpeedwayService speedwayService;
    @Autowired
    private MixBannerService mixBannerService;

    /**
     * 万德赛道列表
     *
     * @param page 分页对象
     * @return
     */
    @RequestMapping("list")
    public String page(Page<Speedway> page) {
        speedwayService.page(page);
        return "wd/list";
    }

    /**
     * 保存万德 赛道信息
     * @param backgroundImage 赛道背景图
     * @param title 赛道主题
     * @param wayId 万德赛道编号
     * @param city 城市
     * @param des 描述
     */
    @RequestMapping("add")
    public void add(@RequestParam("backgroundImage") MultipartFile backgroundImage, String title, String wayId, String city, String des) {
        speedwayService.add(backgroundImage, title, wayId, city, des);
    }

    /**
     * 给赛道设置banner
     *
     * @param speedwayId 赛道编号
     * @param bannerId banner 编号
     */
    @RequestMapping("speedway-modify-banner")
    public void addBanner(Integer speedwayId, Integer bannerId, @RequestParam(defaultValue = "1") Integer isRemove) {
        speedwayService.addBanner(speedwayId, bannerId, isRemove);
    }

    /**
     * 查询万德赛道信息
     *
     * @param speedwayId 赛道编号
     */
    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public String modify(Model model, Integer speedwayId) {
        model.addAttribute("speedway", speedwayService.findSpeedway(speedwayId));
        return "wd/modify";
    }

    /**
     * 修改赛道信息
     *
     * @param speedwayId 赛道编号
     * @param backgroundImage 赛道背景图片
     * @param title 主题
     * @param wayId 赛道编号
     * @param city 城市编号
     * @param des 描述
     * @param status 状态
     * @param releaseStatus 发布状态
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modify(Integer speedwayId, @RequestParam(value = "backgroundImage", required = false) MultipartFile backgroundImage, String title,
                       String wayId, String city, String des, Integer status, Integer releaseStatus) {
        speedwayService.modify(speedwayId, backgroundImage, title, wayId, city, des, status, releaseStatus);
    }

    /**
     * banner 分页
     *
     * @param page
     * @return
     */
    @RequestMapping("list-banner")
    public String listBanner(Page<MixBanner> page) {
        page.convertInt("id", "status");
        speedwayService.list(page);
        return "wd/banner-list";
    }


    /**
     * 添加 banner
     *
     * @param file backImage 图片
     * @param title 标题
     * @param sort 排序

     * @param type 类型 （1、mix 专辑 2、url 网页 3、单曲 4、电台）
     * @param typeValue 类型值
     * @param desc 描述
     */
    @RequestMapping("add-banner")
    public void addBanner(@RequestParam("file") MultipartFile file,
                    @RequestParam("title") String title,
                    @RequestParam("sort") Integer sort,
                    @RequestParam("type") Integer type,
                    @RequestParam(value = "typeValue", required = false) String typeValue,
                    @RequestParam("desc") String desc) {
        speedwayService.add(file, title, sort, type, typeValue, desc);
    }

    /**
     * 查询一个 mix Banner
     *
     * @param bannerId 编号
     * @return banner 信息
     */
    @RequestMapping("modify-banner")
    public String modifyBanner(Model model, @RequestParam("bannerId") Integer bannerId) {
        model.addAttribute("data", mixBannerService.findMixBanner(bannerId));
        return "wd/banner-modify";
    }

}
