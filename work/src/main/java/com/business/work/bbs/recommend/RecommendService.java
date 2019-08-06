package com.business.work.bbs.recommend;

import com.business.core.entity.Page;
import com.business.core.entity.bbs.Recommend;
import com.business.core.utils.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/9/14.
 */
@Service
public class RecommendService {
    @Autowired
    private RecommendDao recommendDao;

    /**
     * 保存 推荐信息
     * @param recommend
     */
    public void saveRecommend(Recommend recommend) {
        recommend.setAddTime(System.currentTimeMillis());
        recommend.setStatus(Recommend.STATUS_NOT_RELEASE);
        recommendDao.insertRecommend(recommend);
    }

    /**
     * 根据编号 修改推荐信息
     * @param recommend
     */
    public void modifyRecommendById(Recommend recommend) {
        Update update = ReflectionUtil.getUpdateInfo(recommend);
        recommendDao.modifyRecommendById(recommend.getId(), update);
    }

    /**
     * 根据编号删除
     * @param id
     */
    public void removeRecommendById(Integer id) {
        recommendDao.removeRecommendById(id);
    }

    /**
     * 获取分页信息
     * @param page
     */
    public void list(Page<Recommend> page) {
        recommendDao.findRecommendPage(page);
    }

    /**
     * 根据编号获取推荐信息
     * @param id
     * @return
     */
    public Recommend findRecommendById(Integer id) {
        return  recommendDao.findRecommendById(id);
    }

    public List<Recommend> getAllRecommend() {
        return recommendDao.getAllRecommend();
    }
}
