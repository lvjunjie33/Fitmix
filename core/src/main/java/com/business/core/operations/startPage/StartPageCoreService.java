package com.business.core.operations.startPage;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.entity.startPage.StartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by edward on 2016/5/16.
 */
@Service
public class StartPageCoreService {

    @Autowired
    private StartPageCoreDao startPageCoreDao;

    /**
     * 查询最后一次发布的有效的启动闪图
     *
     * @return
     */
    public StartPage findStartPage() {
        StartPage startPage = startPageCoreDao.findStartPage(Constants.STATUS_NORMAL, Constants.RELEASE_STATUS_RELEASE, System.currentTimeMillis(), "title", "countdown", "backgroundImg");
        if (startPage == null) {
            return null;
        }
        if (null != startPage.getBackgroundImg()) {
            startPage.setBackgroundImg(FileCenterClient.buildUrl(startPage.getBackgroundImg()));
        }
        return startPage;
    }
}
