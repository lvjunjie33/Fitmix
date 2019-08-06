package com.business.work.startVideo;

import com.business.core.entity.Page;
import com.business.core.entity.startVideo.StartVideo;
import com.business.core.utils.DateUtil;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by edward on 2016/5/17.
 * app首次登录启动视频 Controller
 */
@Controller
@RequestMapping("start-video")
public class StartVideoController extends BaseControllerSupport {

    @Autowired
    private StartVideoService startVideoService;

    @RequestMapping("list")
    public String list(Page<StartVideo> page) {
        page.removeEmpty("id").convertLong("id");
        startVideoService.page(page);
        return "startVideo/list";
    }

    /**
     * 添加app启动视频
     *
     * @param title 标题
     * @param backgroundImg 背景图片
     * @param video 视频
     * @param des 描述
     * @param startTime 开始时间
     * @param deadline 截止时间
     */
    @RequestMapping("add")
    public void add(String title, MultipartFile backgroundImg, MultipartFile video, String des, String startTime, String deadline) {
        startVideoService.addStartVideo(title, backgroundImg, video, des,
                DateUtil.parse(startTime, "yyyy-MM-dd HH:mm").getTime(), DateUtil.parse(deadline, "yyyy-MM-dd HH:mm").getTime());
    }

    /**
     * 去修改启动视频页
     * @param id 视频编号
     */
    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public String modify(Model model, Long id) {
        model.addAttribute("sv", startVideoService.findStartVideoById(id));
        return "startVideo/modify";
    }

    /**
     * 修改启动视频
     *
     * @param id 编号
     * @param title 标题
     * @param backgroundImg 图片
     * @param video 视频
     * @param des 描述
     * @param startTime 开始时间
     * @param deadline 截止时间
     * @param status 数据状态
     * @param releaseStatus 发布状态
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modify(Long id, String title, MultipartFile backgroundImg, MultipartFile video, String des, String startTime, String deadline, Integer status, Integer releaseStatus) {
        startVideoService.modifyStartVideo(id, title, backgroundImg, video, des,
                DateUtil.parse(startTime, "yyyy-MM-dd HH:mm").getTime(), DateUtil.parse(deadline, "yyyy-MM-dd HH:mm").getTime(), status, releaseStatus);
    }
}
