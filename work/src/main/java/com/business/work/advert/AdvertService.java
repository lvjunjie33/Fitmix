package com.business.work.advert;

import com.business.core.client.AliyunCenterClient;
import com.business.core.constants.ApplicationConfig;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.advert.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by edward on 2016/5/16.
 */
@Service
public class AdvertService {

    @Autowired
    private AdvertDao advertDao;

    /**
     * 广告分页查询
     *
     * @param page 分页对象
     */
    public void page(Page<Advert> page) {
        advertDao.page(page);
    }

    /**
     * 添加广告
     *
     * @param title 标题
     * @param toUrl 广告地址
     * @param operationType 操作类型
     * @param advertImg 广告图片
     * @param des 描述
     * @param startTime 开始时间
     * @param deadline 截止时间
     * @param position 位置
     */
    public void addAdvert(String title, String toUrl, Integer operationType, MultipartFile advertImg, String des,
                          Long startTime, Long deadline, Integer position) {
        Advert advert = new Advert();
        advert.setTitle(title);
        advert.setDes(des);
        advert.setStartTime(startTime);
        advert.setDeadline(deadline);
        advert.setToUrl(toUrl);
        String img = "";
        if (advertImg != null) {
            // TODO 文件不保存绝对路径  ，使用文件 使用    FileCenterClient.buildUrl()
            img = ApplicationConfig.FILECENTER_STORAGE_PATH + "/" + AliyunCenterClient.putFile(FileConstants.FILE_TYPE_ADVERT_IMAGE, advertImg);
        }

        advert.setAdvertImg(img);
        advert.setOperationType(operationType);
        advert.setPosition(position);

        advert.setAddTime(System.currentTimeMillis());
        advert.setStatus(Constants.STATUS_NORMAL);
        advert.setReleaseStatus(Constants.RELEASE_STATUS_WAIT_RELEASE);
        advertDao.addAdvert(advert);
    }

    /**
     * 通过广告编号查询广告
     *
     * @param id 广告编号
     */
    public Advert findAdvertById(Long id) {
        return advertDao.findEntityById(id);
    }

    /**
     * 修改广告信息
     *
     * @param id 广告编号
     * @param title 标题
     * @param toUrl 广告地址
     * @param advertImg 广告图片
     * @param des 广告描述
     * @param startTime 开始时间
     * @param deadline 截止时间
     * @param status 数据状态
     * @param releaseStatus 发布状态
     * @param operationType 操作类型
     * @param position 广告位置
     */
    public void modifyAdvert(Long id, String title, String toUrl, MultipartFile advertImg, String des, Long startTime, Long deadline,
                             Integer status, Integer releaseStatus, Integer operationType, Integer position) {
        Update update = new Update();
        update.set("title", title).set("toUrl", toUrl).set("des", des).set("startTime", startTime).set("deadline", deadline)
                .set("status", status).set("releaseStatus", releaseStatus).set("operationType", operationType).set("position", position);
        if (advertImg != null) {
            update.set("advertImg",  AliyunCenterClient.putFile(FileConstants.FILE_TYPE_ADVERT_IMAGE, advertImg));
        }
        advertDao.modifyAdvert(id, update);
    }
}
