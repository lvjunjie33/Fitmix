package com.business.work.bbs.banner;

import com.business.core.entity.bbs.Banner;
import com.business.core.utils.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/9/9.
 */
@Service
public class BannerService {

    @Autowired
    private BannerDao bannerDao;

    /**
     * 保存banner
     * @param banner
     */
    public void saveBanner(Banner banner) {
        banner.setAddTime(System.currentTimeMillis());
        banner.setStatus(Banner.STATUS_NOT_RELEASE);
        bannerDao.insertBanner(banner);
    }

    /**
     * 修改banner
     * @param banner
     */
    public void modifyBannerById(Banner banner) {
        Update update = ReflectionUtil.getUpdateInfo(banner);
        bannerDao.modifyBannerById(banner.getId(), update);
    }

    /**
     * 删除banner
     * @param id
     */
    public void removeBannerById(Integer id) {
        bannerDao.removeBannerById(id);
    }

    /**
     * 获取所有banner
     * @return
     */
    public List<Banner> getAllBanner() {
        return bannerDao.getAllBanner();
    }

    /**
     * 根据ID 查找banner
     * @param id
     * @return
     */
    public Banner findById(Integer id) {
        return bannerDao.findById(id);
    }
}
