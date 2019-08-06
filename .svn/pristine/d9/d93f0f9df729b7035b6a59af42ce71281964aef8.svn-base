package com.business.app.video;

import com.business.app.base.support.BaseControllerSupport;
import com.business.core.entity.Page;
import com.business.core.entity.video.Video;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by fenxio on 2016/5/5.
 */
@Api(value = "video", description = "视频接口")
@Controller
@RequestMapping("video")
public class VideoController extends BaseControllerSupport {

    @Autowired
    private VideoService videoService;

    /**
     *  获取视频列表
     * @param uid 用户ID
     * @param page 分页
     */
    @ApiOperation(value = "获取视频列表", notes = "获取视频接口", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping(value = "get-video-list")
    public void getVideoList(@ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                             @ApiParam(required = false, value = "分页信息") Page<Video> page) {
        videoService.list(page);
    }

    /**
     * 视频详情
     * @param uid 用户ID
     * @param videoId 视频ID
     * @param model
     * @return
     */
    @ApiOperation(value = "获取视频详情", notes = "获取视频详情", response = Video.class, position = 2, httpMethod = "GET")
    @RequestMapping(value = "get-video-details")
    public void videoDetails(@ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                             @ApiParam(required = true, value = "视频编号") @RequestParam("videoId") Integer videoId,
                             @ApiIgnore Model model) {
        Video video = videoService.findVideoById(videoId);
        model.addAttribute("video", video);
    }

    /**
     * 视频分享页面
     * @param uid 用户ID
     * @param videoId 视频ID
     * @param model
     * @return
     */
    @ApiOperation(value = "视频分享页面", notes = "视频分享页面", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("share-video")
    public String shareVideo(@ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                             @ApiParam(required = true, value = "视频编号") @RequestParam("videoId") Integer videoId,
                             @ApiIgnore Model model) {
        Video video = videoService.findVideoById(videoId);
        //分享数+1
        if (video != null) {
            videoService.modiftyShareCountById(video);
        }
        //最新视频
        Page<Video> page = new Page<>();
        videoService.list(page);
        model.addAttribute("video", video)
             .addAttribute("page", page);
        return "video/share-video";
    }

}
