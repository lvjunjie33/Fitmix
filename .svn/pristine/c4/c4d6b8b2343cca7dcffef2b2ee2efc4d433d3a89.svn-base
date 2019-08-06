package com.business.work.mixBanner;

import com.business.core.entity.IncIdEntity;
import com.business.core.entity.Page;
import com.business.core.entity.mix.MixBanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Sin on 2016/3/7.
 */
@Controller
@RequestMapping("mix-banner")
public class MixBannerController {

    @Autowired
    private MixBannerService mixBannerService;

    /**
     * banner 分页
     *
     * @param page
     * @return
     */
    @RequestMapping("list")
    public String list(Page<MixBanner> page) {
        page.convertInt("id", "status");
        mixBannerService.list(page);
        return "mixBanner/list";
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
    @RequestMapping("add")
    public void add(@RequestParam("file") MultipartFile file,
                    @RequestParam("title") String title,
                    @RequestParam("sort") Integer sort,
                    @RequestParam("type") Integer type,
                    @RequestParam(value = "typeValue", required = false) String typeValue,
                    @RequestParam(value = "iosSchemesUrl", required = false) String iosSchemesUrl,
                    @RequestParam(value = "androidSchemesUrl", required = false) String androidSchemesUrl,
                    @RequestParam("channel") Integer channel,
                    @RequestParam("desc") String desc) {
        mixBannerService.add(file, title, sort, type, typeValue, desc, channel, iosSchemesUrl, androidSchemesUrl);
    }

    /**
     * 查询一个 mix Banner
     *
     * @param bannerId 编号
     * @return banner 信息
     */
    @RequestMapping("modify")
    public String modify(Model model, @RequestParam("bannerId") Integer bannerId) {
        model.addAttribute("data", mixBannerService.findMixBanner(bannerId));
        return "mixBanner/modify";
    }

    /**
     * 修改 banner
     *
     * @param bannerId 编号
     * @param file backImage 图片
     * @param title 标题
     * @param sort 排序
     * @param type 类型 （1、mix 专辑 2、url 网页 3、单曲 4、电台）
     * @param status 状态
     * @param typeValue 类型值
     * @param desc 描述
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modify(@RequestParam("bannerId") Integer bannerId,
                        @RequestParam(value = "file", required = false) MultipartFile file,
                       @RequestParam("title") String title,
                       @RequestParam("sort") Integer sort,
                       @RequestParam("type") Integer type,
                       @RequestParam("status") Integer status,
                       @RequestParam("channel") Integer channel,
                       @RequestParam(value = "typeValue", required = false) String typeValue,
                       @RequestParam(value = "iosSchemesUrl", required = false) String iosSchemesUrl,
                       @RequestParam(value = "androidSchemesUrl", required = false) String androidSchemesUrl,
                       @RequestParam("desc") String desc) {
        mixBannerService.modify(bannerId, file, title,
                sort, type, status, typeValue, desc, channel, iosSchemesUrl, androidSchemesUrl);
    }

    /**
     * 修改 状态：0、发布 1、未发布
     *
     * @param bannerId 编号
     * @param status 状态 0、发布 1、未发布
     */
    @RequestMapping("modify-status")
    public void modifyStatus(@RequestParam("bannerId") Integer bannerId,
                             @RequestParam("status") Integer status) {
        mixBannerService.modifyStatus(bannerId, status);
    }
}
