package com.business.work.startVideo;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.ApplicationConfig;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.startVideo.StartVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by edward on 2016/5/17.
 */
@Service
public class StartVideoService {

    @Autowired
    private StartVideoDao startVideoDao;

    /**
     * 启动视频分页查询
     *
     * @param page 分页对象
     */
    public void page(Page<StartVideo> page) {
        startVideoDao.page(page);
    }

    /**
     * 添加新的app启动视频
     *
     * @param title 标题
     * @param backgroundImg 背景图
     * @param video 启动视频
     * @param des 视频描述
     * @param startTime 开始时间
     * @param deadline 截止时间
     */
    public void addStartVideo(String title, MultipartFile backgroundImg, MultipartFile video, String des, Long startTime, Long deadline) {
        StartVideo startVideo = new StartVideo();
        startVideo.setTitle(title);
        if (backgroundImg != null) {
            // TODO 文件不保存绝对路径  ，使用文件 使用    FileCenterClient.buildUrl()
            startVideo.setBackgroundImg(ApplicationConfig.FILECENTER_STORAGE_PATH + "/" + AliyunCenterClient.putFile(FileConstants.FILE_TYPE_START_VIDEO_IMG, backgroundImg));
        }
        if (video != null) {
            // TODO 文件不保存绝对路径  ，使用文件 使用    FileCenterClient.buildUrl()
            startVideo.setVideo(ApplicationConfig.FILECENTER_STORAGE_PATH + "/" + AliyunCenterClient.putFile(FileConstants.FILE_TYPE_START_VIDEO, video));
        }
        startVideo.setDes(des);
        startVideo.setStartTime(startTime);
        startVideo.setDeadline(deadline);

        startVideo.setAddTime(System.currentTimeMillis());
        startVideo.setReleaseStatus(Constants.RELEASE_STATUS_WAIT_RELEASE);
        startVideo.setStatus(Constants.STATUS_NORMAL);

        startVideoDao.add(startVideo);
    }

    /**
     * 通过主键查询启动视频
     * @param id 启动视频编号
     */
    public StartVideo findStartVideoById(Long id) {
        return startVideoDao.findEntityById(id);
    }

    /**
     * 修改 启动视频信息
     *
     * @param id 启动视频编号
     * @param title 标题
     * @param backgroundImg 图片
     * @param video 视频
     * @param des 描述
     * @param startTime 开始时间
     * @param deadline 截止时间
     * @param status 数据状态
     * @param releaseStatus 发布状态
     */
    public void modifyStartVideo(Long id, String title, MultipartFile backgroundImg, MultipartFile video, String des,
                                 Long startTime, Long deadline, Integer status, Integer releaseStatus) {

        Update update = Update.update("title", title).set("des", des).set("startTime", startTime).set("deadline", deadline)
                .set("status", status).set("releaseStatus", releaseStatus);
        if (backgroundImg != null) {
            update.set("backgroundImg", AliyunCenterClient.putFile(FileConstants.FILE_TYPE_START_VIDEO_IMG, backgroundImg));
        }
        if(video != null) {
            update.set("video", AliyunCenterClient.putFile(FileConstants.FILE_TYPE_START_VIDEO, video));
        }
        startVideoDao.modify(id, update);
    }
}
