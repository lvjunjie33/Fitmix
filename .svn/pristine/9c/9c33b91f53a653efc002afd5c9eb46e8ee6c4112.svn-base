package com.business.work.surprise;

import com.business.core.client.FileCenterClient;
import com.business.core.entity.Page;
import com.business.core.entity.surprise.Surprise;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by fenxio on 2016/5/19.
 */
@Service
public class SurpriseService {

    @Autowired
    private SurpriseDao surpriseDao;

    /**
     * 添加彩蛋信息
     * @param surprise 彩蛋对象
     * @return
     */
    public Object[] surpriseAdd(Surprise surprise) {
        surprise.setState(Surprise.STATE_1);
        surprise.setAddTime(System.currentTimeMillis());
        surpriseDao.insertSurprise(surprise);
        return new Object[]{0, surprise};
    }

    /**
     * 更新图片信息
     * @param id 编号
     * @param imageUrl 图片链接地址
     */
    public void surpriseModifyImage(Long id, String imageUrl) {
        Surprise surprise  = surpriseDao.findSurpriseById(id,"id","imgUrl");
        if(null != surprise.getImgUrl()) {
            FileCenterClient.removeFile(surprise.getImgUrl());
        }
        surpriseDao.updateSurpriseById(id, Update.update("imgUrl", imageUrl));
    }

    /**
     * 根据编号查找彩蛋信息
     * @param id 编号
     * @return
     */
    public Surprise findSurpriseById(Long id) {
        return surpriseDao.findSurpriseById(id);
    }

    /**
     * 修改彩蛋基本信息
     * @param surprise 彩蛋对象
     */
    public void surpriseModify(Surprise surprise) {
        Update update = new Update();
        update.set("title", surprise.getTitle()).set("content", surprise.getContent()).set("startTime", surprise.getStartTime())
              .set("endTime", surprise.getEndTime()).set("name", surprise.getName());
        surpriseDao.updateSurpriseById(surprise.getId(), update);
    }

    /**
     * 修改彩蛋上架状态
     * @param id 编号
     * @param state 上架状态
     */
    public Integer surpriseModifyState(Long id, Integer state) {
        Integer result = 1;
        if(state == Surprise.STATE_2) {
            Surprise surprise = surpriseDao.findSurpriseById(id);
            // 判断是否满足上架条件
            if(surpriseDao.isCanPutaway(surprise)) {
                surpriseDao.updateSurpriseById(id, Update.update("state", state));
                result = 0;
            }
        } else {
            surpriseDao.updateSurpriseById(id, Update.update("state", state));
            result = 0;
        }

        return result;
    }

    /**
     * 删除彩蛋信息
     * @param id 编号
     */
    public void surpriseRemoveById(Long id) {
        surpriseDao.surpriseRemoveById(id);
    }

    /**
     * 获取彩蛋列表
     * @param page 分页信息
     */
    public void list(Page<Surprise> page) {
        surpriseDao.findSurprisePage(page);
    }
}
