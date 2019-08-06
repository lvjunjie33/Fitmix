package com.business.work.video;

import com.business.core.client.FileCenterClient;
import com.business.core.entity.Page;
import com.business.core.entity.video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhangtao on 2016/4/25.
 */
@Service
public class VideoService {

    @Autowired
    private VideoDao videoDao;

    /**
     * 添加视频
     * @param video 视频
     * @return
     */
    public Object[] videoAdd(Video video) {
        video.setAddTime(System.currentTimeMillis());
        video.setStatus(Video.STATUS_INVALID);
        video.setVideoName("未设置文件");
        video.setVideoUrl("");
        video.setPosterName("未设置文件");
        video.setPosterUrl("");
        video.setShareCount(0);
        videoDao.insertVideo(video);
        return new Object[]{0,video};
    }

    /**
     * 根据ID 获取视频信息
     * @param id
     * @return
     */
    public Video findVideoById(Integer id) {
        return videoDao.findVideoById(id);
    }

    /**
     * 修改视频文件信息
     * @param vid 视频ID
     * @param fileUrl 视频路径
     * @param name 视频名称
     */
    public void videoFileModify(Integer vid, String fileUrl,String name) {
        Video video = videoDao.findVideoById(vid,"id","videoUrl");
        if (null != video.getVideoUrl()) {
            FileCenterClient.removeFile(video.getVideoUrl());
        }
        videoDao.updateVideoById(vid, Update.update("videoUrl", fileUrl).set("videoName",name));
    }

    /**
     * 修改视频封面信息
     * @param vid 视频ID
     * @param imageUrl 视频封面路径
     * @param name 视频封面名称
     */
    public void videoImageModify(Integer vid, String imageUrl, String name) {
        Video video = videoDao.findVideoById(vid,"id","posterUrl");
        if(null != video.getPosterUrl()){
            FileCenterClient.removeFile(video.getPosterUrl());
        }
        videoDao.updateVideoById(vid,Update.update("posterUrl",imageUrl).set("posterName",name));
    }

    /**
     * 修改视频基本信息
     * @param video 视频信息
     */
    public void videoModify(Video video) {
        Update update = new Update();
        update.set("title",video.getTitle()).set("content",video.getContent()).set("trackLength",video.getTrackLength())
                .set("sort",video.getSort());
        videoDao.updateVideoById(video.getId(),update);
    }

    /**
     * 获取视频列表
     * @param page 分页信息
     */
    public void list(Page<Video> page) {
        videoDao.findVideoPage(page);
    }

    /**
     * 根据编号删除视频
     * @param vid 编号
     */
    public void videoRemove(Integer vid) {
        videoDao.videoRemove(vid);
    }

    /**
     * 修改视频排序
     * @param vid 编号
     * @param sort 排序
     */
    public void videoSortModify(Integer vid, Integer sort) {
        videoDao.updateVideoById(vid, Update.update("sort", sort));
    }

    /**
     * 修改视频上架状态
     * @param vid 编号
     * @param status 状态
     */
    public void videoStatusModify(Integer vid, Integer status) {
        videoDao.updateVideoById(vid, Update.update("status", status));
    }
}
