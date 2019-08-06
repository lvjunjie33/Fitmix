package com.business.work.bbs.advertisement;

import com.business.core.entity.bbs.Advertisement;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fenxio on 2016/9/19.
 */
@Repository
public class AdvertisementDao extends BaseMongoDaoSupport {
    /**
     * 插入广告
     * @param advertisement
     */
    public void insertAdvertisement(Advertisement advertisement) {
        insertToMongo(advertisement);
    }

    /**
     * 根据ID 修改
     * @param id
     * @param update
     */
    public void modifyAdvertisementById(Integer id, Update update) {
        updateEntityFieldsById(Advertisement.class, id, update);
    }

    /**
     * 根据ID 查找广告
     * @param id
     * @return
     */
    public Advertisement findAdvertisementById(Integer id) {
        return findEntityById(Advertisement.class, id);
    }

    /**
     * 根据id 删除广告
     * @param id
     */
    public void removeAdvertisementById(Integer id) {
        removeEntityById(Advertisement.class, id);
    }

    /**
     * 获取所有 广告
     * @return
     */
    public List<Advertisement> getAllAdvertisement() {
       return getRoutingMongoOps().findAll(Advertisement.class);
    }
}
