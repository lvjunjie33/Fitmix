package com.business.work.announcement;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.sys.Announcement;
import com.business.core.utils.DateUtil;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by edward on 2017/9/25.
 */
@RequestMapping
@Controller
public class AnnouncementController extends BaseControllerSupport{

    @Autowired
    private AnnouncementService announcementService;

    /**
     * 公告分页
     *
     * @param page 分页对象
     */
    @RequestMapping("announcement/page")
    private String page(Page<Announcement> page) {
        announcementService.page(page);
        return "/announcement/page";
    }

    /**
     * 新增 公告
     * @param img 公告封面图
     * @param announcement 公告
     * @param beginTime 开始时间
     * @param endTime 结束时间
     */
    @RequestMapping("announcement/add")
    public void add(MultipartFile img, Announcement announcement, String beginTime, String endTime) {
        if (StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)) {
            return;
        }

        String imgLink = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RESOURCE_FILE, img);
        announcement.setImgLink(imgLink);

        Long bTime = DateUtil.parse(beginTime, "yyyy-MM-dd HH:mm").getTime();
        Long eTime = DateUtil.parse(endTime, "yyyy-MM-dd HH:mm").getTime();
        announcement.setbTime(bTime);
        announcement.seteTime(eTime);

        announcementService.add(announcement);
    }

    /**
     * 去修改页面
     *
     * @param id 编号
     */
    @RequestMapping(value = "announcement/modify", method = RequestMethod.GET)
    public String modify(Model model, Integer id) {
        model.addAttribute("announcement", announcementService.findById(id));
        return "/announcement/modify";
    }

    /**
     * 修改公告
     *
     * @param img 封面图
     * @param body 内容
     * @param desc 描述
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param displayNum 显示次数
     */
    @RequestMapping(value = "announcement/modify", method = RequestMethod.POST)
    public void modify(@RequestParam(required = false) MultipartFile img, Integer id, String body, String desc, String beginTime, String endTime, Integer displayNum, Integer status) {
        String imgLink = null;
        if (img != null) {
            imgLink = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_RESOURCE_FILE, img);
        }
        Long bTime = DateUtil.parse(beginTime, "yyyy-MM-dd HH:mm").getTime();
        Long eTime = DateUtil.parse(endTime, "yyyy-MM-dd HH:mm").getTime();

        announcementService.modify(id, imgLink, body, desc, bTime, eTime, displayNum, status);
    }
}
