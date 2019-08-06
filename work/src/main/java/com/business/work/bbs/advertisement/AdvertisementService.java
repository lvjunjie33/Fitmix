package com.business.work.bbs.advertisement;

import com.business.core.entity.bbs.Advertisement;
import com.business.core.utils.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/9/19.
 */
@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementDao advertisementDao;

    /**
     * 保存广告
     * @param advertisement
     */
    public void saveAdvertisement(Advertisement advertisement) {
        advertisement.setAddTime(System.currentTimeMillis());
        advertisement.setStatus(Advertisement.STATUS_NOT_RELEASE);
        advertisementDao.insertAdvertisement(advertisement);
    }

    /**
     * 根据ID 修改广告
     * @param advertisement
     */
    public void modifyAdvertisementById(Advertisement advertisement) {
        Update update = ReflectionUtil.getUpdateInfo(advertisement);
        advertisementDao.modifyAdvertisementById(advertisement.getId(), update);
    }

    /**
     * 根据id 查找广告
     * @param id
     * @return
     */
    public Advertisement findAdvertisementById(Integer id) {
        return advertisementDao.findAdvertisementById(id);
    }

    /**
     * 根据ID 删除广告
     * @param id
     */
    public void removeAdvertisementById(Integer id) {
        advertisementDao.removeAdvertisementById(id);
    }

    /**
     * 获取所有
     * @return
     */
    public List<Advertisement> getAllAdvertisement() {
        return advertisementDao.getAllAdvertisement();
    }

}
