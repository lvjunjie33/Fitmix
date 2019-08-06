package com.business.work.video;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.video.Video;
import com.business.work.base.constants.CodeConstants;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by zhangtao on 2016/4/25.
 */
@Controller
@RequestMapping("video")
public class VideoController extends BaseControllerSupport {

    @Autowired
    private VideoService videoService;


    /**
     * 视频列表
     * @param page 分页信息
     * @return
     */
    @RequestMapping("video-list")
    public String videoList(Page<Video> page) {
        page.convertInt("id");
        videoService.list(page);
        return "video/video-list";
    }

    /**
     * 视频详情
     * @param id 视频ID
     * @param model
     * @return
     */
    @RequestMapping("video-info")
    public String videoInfo(@RequestParam("id") Integer id,Model model) {
        model.addAttribute("video",videoService.findVideoById(id));
        return "video/video-info";
    }

    /**
     * video 添加页面
     * @return
     */
    @RequestMapping(value = "video-add",method = RequestMethod.GET)
    public String videoAdd(){
        return "video/video-add";
    }

    /**
     * 添加 video
     * @param video 视频信息
     * @param model
     */
    @RequestMapping(value = "video-add",method = RequestMethod.POST)
    public void videoAdd(Video video,Model model) {
        Object[] result = videoService.videoAdd(video);
        switch ((Integer) result[0]) {
            case 0:
                model.addAttribute("video", result[1]);
                break;
        }
    }

    /**
     * video 修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "video-modify",method = RequestMethod.GET)
    public String videoModify(Integer id, Model model) {
        model.addAttribute("video",videoService.findVideoById(id));
        return "video/video-modify";
    }

    /**
     * 修改Video
     * @param video 视频信息
     * @param model
     */
    @RequestMapping(value = "video-modify",method = RequestMethod.POST)
    public void videoModify(Video video, Model model){
        videoService.videoModify(video);
    }


    /**
     * 上传视频
     * @param vid 视频ID
     * @param part 视频分割下标
     * @param size 视频大小
     * @param name 视频名称
     * @param partSize 分割大小
     * @param file 视频文件
     */
    @RequestMapping(value = "video-file-modify",method = RequestMethod.POST)
    public void videoFileModify(@RequestParam("vid") Integer vid,
                                @RequestParam("part") Long part,
                                @RequestParam("size") Long size,
                                @RequestParam("name") String name,
                                @RequestParam("partSize") Long partSize,
                                @RequestParam("file") MultipartFile file) {
        String fileUrl = AliyunCenterClient.multipartFile(FileConstants.FILE_TYPE_VIDEO.toString(), file, name, size, partSize, part);
        if (partSize * part >= size) {
            videoService.videoFileModify(vid, fileUrl, name);
        }

    }

    /**
     * 上传封面
     * @param vid 视频ID
     * @param image 封面文件
     * @throws IOException
     */
    @RequestMapping(value = "video-image-modify",method = RequestMethod.POST)
    public void videoImageModify(@RequestParam("vid") Integer vid,
                                 @RequestParam("image") MultipartFile image) throws IOException {
        String imageUrl = FileCenterClient.upload(image.getBytes(), FileConstants.FILE_TYPE_VIDEO_IMAGE, image.getOriginalFilename());
        videoService.videoImageModify(vid, imageUrl, image.getOriginalFilename());
    }

    /**
     * 根据ID 删除视频信息
     * @param id 视频ID
     */
    @RequestMapping(value = "video-remove",method = RequestMethod.POST)
    public void videoRemove(@RequestParam("id") Integer id){
        videoService.videoRemove(id);
    }

    /**
     * 修改视频排序
     * @param id 视频ID
     * @param sort 排序ID
     */
    @RequestMapping(value = "video-modify-sort",method = RequestMethod.POST)
    public void videoSortModify(@RequestParam("id") Integer id,
                          @RequestParam("sort") Integer sort) {
        videoService.videoSortModify(id, sort);
    }

    /**
     * 修改视频状态
     * @param id 视频id
     * @param status 视频状态
     */
    @RequestMapping(value = "video-modify-status",method = RequestMethod.POST)
    public void videoStatusModify(@RequestParam("id") Integer id,
                          @RequestParam("status") Integer status) {
        videoService.videoStatusModify(id, status);
    }

}
