package com.business.core.operations.advert;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.entity.advert.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by edward on 2016/5/16.
 */
@Service
public class AdvertCoreService {

    @Autowired
    private AdvertCoreDao advertCoreDao;
    /**
     * 查询有效的广告
     */
    public List<Advert> findAdverts() {
        List<Advert> adverts = advertCoreDao.findAdverts(Constants.STATUS_NORMAL, Constants.RELEASE_STATUS_RELEASE, System.currentTimeMillis(), "title", "toUrl", "advertImg", "operationType");
        for (Advert advert : adverts) {
            advert.setAdvertImg(FileCenterClient.buildUrl(advert.getAdvertImg()));
        }
        return adverts;
    }
}
