package com.business.bbs.advertisement;

import com.business.bbs.base.support.AbstractDao;
import com.business.bbs.base.support.AbstractService;
import com.business.core.entity.bbs.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fenxio on 2016/9/19.
 */
@Service
public class AdvertisementService extends AbstractService<Advertisement> {

    @Autowired
    private AdvertisementDao advertisementDao;

    @Override
    protected AbstractDao<Advertisement> getAbstractDao() {
        return advertisementDao;
    }

    /**
     * 根据模块获取 有效广告
     * @param module
     * @return
     */
    public List<Advertisement> findAdvertisementByModule(int module) {
        Query query = new Query(Criteria.where("module").is(module).and("status").is(Advertisement.STATUS_RELEASE));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        return advertisementDao.find(query);
    }
}
