package com.business.work.advert;

import com.business.core.entity.Page;
import com.business.core.entity.advert.Advert;
import com.business.core.utils.DateUtil;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by edward on 2016/5/16.
 * app广告 Controller
 */
@Controller
@RequestMapping("advert")
public class AdvertController extends BaseControllerSupport{

    @Autowired
    private AdvertService advertService;

    /**
     * 广告列表
     *
     * @param page 数据集
     */
    @RequestMapping("list")
    public String list(Page<Advert> page) {
        page.removeEmpty("id").convertLong("id");
        advertService.page(page);
        return "advert/list";
    }

    /**
     * 添加新的广告
     *
     * @param title 广告标题
     * @param toUrl 广告指向地址
     * @param operationType 操作类型
     * @param advertImg 广告图片
     * @param des 描述
     * @param startTime 开始时间
     * @param deadline 截止时间
     * @param position 广告显示位置
     */
    @RequestMapping("add")
    public void add(String title, @RequestParam(defaultValue = "") String toUrl, MultipartFile advertImg, String des, String startTime, String deadline,
                    @RequestParam(value = "operationType", defaultValue = "1") Integer operationType, @RequestParam(value = "position", defaultValue = "1") Integer position) {
        advertService.addAdvert(title, toUrl, operationType, advertImg, des,
                DateUtil.parse(startTime, "yyyy-MM-dd HH:mm").getTime(), DateUtil.parse(deadline, "yyyy-MM-dd HH:mm").getTime(), position);
    }

    /**
     * 修改广告
     *
     * @param id 广告编号
     */
    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public String modify(Model model, Long id) {
        model.addAttribute("advert", advertService.findAdvertById(id));
        return "advert/modify";
    }

    /**
     * 修改广告
     *
     * @param id 广告编号
     * @param title 标题
     * @param toUrl 广告地址
     * @param advertImg 广告图片
     * @param des 广告描述
     * @param startTime 开始时间
     * @param deadline 截止时间
     * @param status 数据状态
     * @param releaseStatus 发布状态
     * @param operationType 操作类型
     * @param position 广告位置
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modify(Long id, String title, @RequestParam(defaultValue = "")String toUrl, MultipartFile advertImg, String des, String startTime, String deadline,
                       Integer status, Integer releaseStatus,
                       @RequestParam(value = "operationType", defaultValue = "1") Integer operationType, @RequestParam(value = "position", defaultValue = "1") Integer position) {
        advertService.modifyAdvert(id, title, toUrl, advertImg, des,
                DateUtil.parse(startTime, "yyyy-MM-dd HH:mm").getTime(), DateUtil.parse(deadline, "yyyy-MM-dd HH:mm").getTime(), status, releaseStatus, operationType, position);
    }

}
