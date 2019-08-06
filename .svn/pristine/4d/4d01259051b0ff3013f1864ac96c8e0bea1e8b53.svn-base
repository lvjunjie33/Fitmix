package com.business.work.wd;

import com.business.core.client.AliyunCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.mix.MixBanner;
import com.business.core.entity.wd.Speedway;
import com.business.work.mixBanner.MixBannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by edward on 2016/7/19.
 */
@Service
public class SpeedwayService {

    @Autowired
    private SpeedwayDao speedwayDao;

    /**
     * 保存万德 赛道信息
     * @param backgroundImage 赛道背景图
     * @param title 赛道主题
     * @param wayId 万德赛道编号
     * @param city 城市
     * @param des 描述
     */
    public void add(MultipartFile backgroundImage, String title, String wayId, String city, String des) {
        Speedway speedway = new Speedway();

        String imageUrl = "";
        if (backgroundImage != null) {
            imageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_ACTIVITY_THEME_IMAGE, backgroundImage);
        }

        speedway.setTitle(title);
        speedway.setWayId(wayId);
        speedway.setCity(city);
        speedway.setDes(des);
        speedway.setBackgroundImage(imageUrl);

        speedway.setStatus(Constants.STATE_EFFECTIVE);
        speedway.setReleaseStatus(Constants.RELEASE_STATUS_WAIT_RELEASE);

        speedwayDao.save(speedway);
    }

    /**
     * 给赛道设置banner
     *
     * @param speedwayId 赛道编号
     * @param bannerId banner 编号
     * @param isRemove 是否删除
     */
    public void addBanner(Integer speedwayId, Integer bannerId, Integer isRemove) {
        if (Constants.STATE_EFFECTIVE.equals(isRemove)) {
            speedwayDao.modifyById(speedwayId, new Update().push("bannerIds", bannerId));
        } else {
            speedwayDao.modifyById(speedwayId, new Update().pull("bannerIds", bannerId));
        }
    }

    /**
     * 万德赛道 分页
     * @param page 分页对象
     */
    public void page(Page<Speedway> page) {
        speedwayDao.page(page);
        for (Speedway speedway : page.getResult()) {
            List<Integer> bannerIds = speedway.getBannerIds();
            if (CollectionUtils.isEmpty(bannerIds)) {
                speedway.setMixBanners(Collections.EMPTY_LIST);
            } else {
                speedway.setMixBanners(mixBannerDao.findMixBannerByIds(bannerIds));
            }
        }
    }

    /**
     * 修改万德赛道信息
     *
     * @param backgroundImage 赛道背景图片
     * @param title 主题
     * @param wayId 赛道编号
     * @param city 城市
     * @param des 描述
     * @param status 状态
     * @param releaseStatus 发布状态
     */
    public void modify(Integer speedwayId, @RequestParam("backgroundImage") MultipartFile backgroundImage, String title, String wayId, String city, String des, Integer status, Integer releaseStatus) {
        Update update = new Update();
        String imageUrl = "";
        if (backgroundImage != null) {
            imageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_ACTIVITY_THEME_IMAGE, backgroundImage);
        }
        if (!StringUtils.isEmpty(imageUrl)) {
            update.set("backgroundImage", imageUrl);
        }
        update.set("title", title).set("wayId", wayId).set("city", city).set("des", des).set("status", status).set("releaseStatus", releaseStatus);
        speedwayDao.modifyById(speedwayId, update);
    }

    /**
     * 查询万德赛道信息
     *
     * @param id 赛道编号
     */
    public Speedway findSpeedway(Integer id) {
        return speedwayDao.findById(id);
    }

    @Autowired
    private MixBannerDao mixBannerDao;
    /**
     * banenr 分页
     *
     * @param page
     */
    public void list(Page<MixBanner> page) {
        page.getFilter().put("channel", MixBanner.CHANNEL_WD);
        mixBannerDao.findPageMixBanner(page);
    }

    /**
     * 添加 banner
     *
     * @param file backImage 图片
     * @param title 标题
     * @param sort 排序
     * @param type 类型 （1、mix 专辑 2、url 网页 3、单曲 4、电台）
     * @param typeValue 类型值
     * @param desc 描述
     */
    public void add(MultipartFile file, String title, Integer sort,
                    Integer type, String typeValue, String desc) {

        String backImageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_MIX_BANNER_IMAGE, file);

        MixBanner banner = new MixBanner();
        banner.setBackImage(backImageUrl);
        banner.setTitle(title);
        banner.setSort(sort);
        banner.setType(type);
        banner.setTypeValue(typeValue);
        banner.setDesc(desc);
        banner.setStatus(MixBanner.STATUS_NOT_RELEASE);
        banner.setAddTime(System.currentTimeMillis());
        banner.setChannel(MixBanner.CHANNEL_WD);
        mixBannerDao.insertMixBanner(banner);
    }
}
