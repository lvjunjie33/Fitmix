package com.business.work.startPage;

import com.business.core.client.AliyunCenterClient;
import com.business.core.constants.ApplicationConfig;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.startPage.StartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by edward on 2016/5/16.
 */
@Service
public class StartPageService {

    @Autowired
    private StartPageDao startPageDao;

    /**
     * 添加启动闪屏页
     *
     * @param title 闪屏标题
     * @param countdown 闪屏时长
     * @param backgroundImg 闪屏背景图
     * @param des 说明
     */
    public void addNewStartPage(String title, Integer countdown, MultipartFile backgroundImg, Long startTime, Long deadline, String des) {
        StartPage startPage = new StartPage();
        startPage.setTitle(title);
        startPage.setCountdown(countdown);
        startPage.setStartTime(startTime);
        startPage.setDeadline(deadline);
        String imageUrl = "";
        if (backgroundImg != null) {
            // TODO 文件不保存绝对路径  ，使用文件 使用    FileCenterClient.buildUrl()
            imageUrl = ApplicationConfig.FILECENTER_STORAGE_PATH + "/" + AliyunCenterClient.putFile(FileConstants.FILE_TYPE_START_PAGE_IMAGE, backgroundImg);
        }
        startPage.setBackgroundImg(imageUrl);
        startPage.setDes(des);
        startPage.setReleaseStatus(Constants.RELEASE_STATUS_WAIT_RELEASE);
        startPage.setStatus(Constants.STATUS_NORMAL);
        startPage.setAddTime(System.currentTimeMillis());
        startPageDao.addStartPage(startPage);
    }

    /**
     * 查询启动页信息
     *
     * @param id 编号
     */
    public StartPage findStartPage(Long id) {
        return startPageDao.findStartPage(id);
    }

    /**
     * 闪屏 分页查询
     */
    public void page(Page<StartPage> page) {
        startPageDao.page(page);
    }

    /**
     * 修改闪屏信息
     *
     * @param id 闪屏编号
     * @param title 闪屏标题
     * @param countdown 闪屏时长
     * @param backgroundImg 闪屏背景图
     * @param des 说明
     * @param status 记录状态
     * @param releaseStatus 发布状态
     */
    public void modifyStartPage(Long id, String title, Integer countdown, MultipartFile backgroundImg, Long startTime, Long deadline, String des, Integer status, Integer releaseStatus) {
        Update update = Update.update("title", title).set("countdown", countdown).set("des", des).set("status", status)
                .set("releaseStatus", releaseStatus).set("startTime", startTime).set("deadline", deadline);
        if (backgroundImg != null) {
            update.set("backgroundImg", AliyunCenterClient.putFile(FileConstants.FILE_TYPE_START_PAGE_IMAGE, backgroundImg));
        }
        startPageDao.modifyStartPage(id, update);
    }

}
