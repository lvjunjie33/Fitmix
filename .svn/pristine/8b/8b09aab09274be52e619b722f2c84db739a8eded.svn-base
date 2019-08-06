package com.business.work.bbs.banner;

import com.business.core.entity.bbs.Banner;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fenxio on 2016/9/9.
 */
@Repository
public class BannerDao extends BaseMongoDaoSupport {

    /**
     * 保存banner
     * @param banner
     */
    public void insertBanner(Banner banner) {
        insertToMongo(banner);
    }

    /**
     * 修改banner
     * @param id
     * @param update
     */
    public void modifyBannerById(Integer id, Update update) {
        updateEntityFieldsById(Banner.class, id, update);
    }

    /**
     * 删除Banner
     * @param id
     */
    public void removeBannerById(Integer id) {
        removeEntityById(Banner.class, id);
    }

    /**
     * 获取所有分页
     * @return
     */
    public List<Banner> getAllBanner() {
        return getRoutingMongoOps().findAll(Banner.class);
    }

    /**
     * 根据编查找banner
     * @param id
     * @return
     */
    public Banner findById(Integer id) {
        return findEntityById(Banner.class, id);
    }
}
