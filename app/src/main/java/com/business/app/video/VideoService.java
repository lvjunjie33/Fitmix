package com.business.app.video;

import com.business.app.base.support.BaseServiceSupport;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.Page;
import com.business.core.entity.video.Video;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fenxio on 2016/5/5.
 */
@Service
public class VideoService  extends BaseServiceSupport {

    @Autowired
    private VideoDao videoDao;

    /**
     * 获取视频列表
     * @param page 分页
     */
    public void list(Page<Video> page) {
        page.getFilter().put("status", Video.STATUS_NORMAL);
        videoDao.list(page);
        buildFileUrls(page.getResult());
    }

    /**
     * 根据ID 获取视频信息
     * @param videoId 视频ID
     * @return
     */
    public Video findVideoById(Integer videoId) {
        Video video = videoDao.findVideoById(videoId);
        buildFileUrl(video);
        return video;
    }


    /**
     * 构建多个 视频文件路径
     * @param videoList 视频集合
     */
    public void buildFileUrls(List<Video> videoList) {
        if (!CollectionUtils.isEmpty(videoList)) {
            for (Video activity : videoList) {
                buildFileUrl(activity);
            }
        }
    }

    /**
     * 构建一个 活动文件路径
     * @param video 活动集合
     */
    public void buildFileUrl(Video video) {
        if (video != null) {
            if (null != video.getPosterUrl()) {
                video.setPosterUrl(FileCenterClient.buildUrl(video.getPosterUrl()));
            }
            if(null != video.getVideoUrl()){
                video.setVideoUrl(FileCenterClient.buildUrl(video.getVideoUrl()));
            }
        }
    }

    /**
     * 分享数+1
     * @param video
     */
    public void modiftyShareCountById(Video video) {
        Update update = new Update();
        update.inc("shareCount", 1);
        videoDao.modiftyShareCountById(video.getId(), update);
    }
}
