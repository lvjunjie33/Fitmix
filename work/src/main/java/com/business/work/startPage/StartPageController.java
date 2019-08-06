package com.business.work.startPage;

import com.business.core.entity.Page;
import com.business.core.entity.startPage.StartPage;
import com.business.core.utils.DateUtil;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edward on 2016/5/16.
 * 启动页管理
 */
@Controller
@RequestMapping("start-page")
public class StartPageController extends BaseControllerSupport {

    @Autowired
    private StartPageService startPageService;

    @RequestMapping("list")
    public String list(Page<StartPage> page) {
        page.removeEmpty("id").convertLong("id");
        startPageService.page(page);
        return "startPage/list";
    }

    /**
     * 添加闪屏页
     *
     * @param title 闪屏标题
     * @param countdown 闪屏时长
     * @param backgroundImg 闪屏背景图
     * @param des 说明
     */
    @RequestMapping("add")
    public void addNewStartPage(String title, Integer countdown, MultipartFile backgroundImg, String startTime, String deadline, String des) {
        startPageService.addNewStartPage(title, countdown, backgroundImg,
                DateUtil.parse(startTime, "yyyy-MM-dd HH:mm").getTime(), DateUtil.parse(deadline,"yyyy-MM-dd HH:mm").getTime(), des);
    }

    /**
     * 修改 闪屏页
     * @param id 闪屏编号
     */
    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public String modifyStartPage(Model model, Long id) {
        model.addAttribute("sp", startPageService.findStartPage(id));
        return "startPage/modify";
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modifyStartPage(Long id, String title, Integer countdown, MultipartFile backgroundImg, String startTime, String deadline, String des, Integer status, Integer releaseStatus) {
        startPageService.modifyStartPage(id, title, countdown, backgroundImg,
                DateUtil.parse(startTime, "yyyy-MM-dd HH:mm").getTime(), DateUtil.parse(deadline,"yyyy-MM-dd HH:mm").getTime(), des, status, releaseStatus);
    }
}
