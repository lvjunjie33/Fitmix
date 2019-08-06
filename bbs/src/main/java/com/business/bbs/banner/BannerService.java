package com.business.bbs.banner;

import com.business.bbs.base.support.AbstractDao;
import com.business.bbs.base.support.AbstractService;
import com.business.core.entity.bbs.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fenxio on 2016/9/13.
 */
@Service
public class BannerService extends AbstractService<Banner> {

    @Autowired
    private BannerDao bannerDao;

    @Override
    protected AbstractDao<Banner> getAbstractDao() {
        return bannerDao;
    }

    /**
     * 获取首页展示 banner 列表
     * @return
     */
    public List<Banner> getBannerList() {
        Query query = new Query(Criteria.where("status").is(Banner.STATUS_RELEASE));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        return bannerDao.find(query);
    }


}
