package com.business.core.operations.startVideo;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.entity.startVideo.StartVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by edward on 2016/5/17.
 */
@Service
public class StartVideoCoreService {

    @Autowired
    private StartVideoCoreDao startVideoCoreDao;

    /**
     * 查询有效的启动视频
     */
    public StartVideo findStartVideo() {
        StartVideo video = startVideoCoreDao.findStartVideo(Constants.STATUS_NORMAL, Constants.RELEASE_STATUS_RELEASE, System.currentTimeMillis(), "title", "backgroundImg", "video");
        if (video == null) {
            return null;
        }
        if (null != video.getBackgroundImg()) {
            video.setBackgroundImg(FileCenterClient.buildUrl(video.getBackgroundImg()));
        }
        if (null != video.getVideo()) {
            video.setVideo(FileCenterClient.buildUrl(video.getVideo()));
        }
        return video;
    }
}
