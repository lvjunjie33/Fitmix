package com.business.app.dynamic;

import com.business.core.entity.dynamic.Comment;
import com.business.core.entity.dynamic.Dynamic;
import com.business.core.entity.dynamic.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fenxio on 2016/10/8.
 */
@Service
public class DynamicService {

    @Autowired
    private DynamicDao dynamicDao;

    /**
     * 保存动态信息
     * @param dynamic 动态信息
     */
    public void saveDynamic(Dynamic dynamic) {
        dynamic.setAddTime(System.currentTimeMillis());
        dynamicDao.insert(dynamic);
    }

    /**
     * 根据动态编号和用户编号删除动态信息
     * @param id 动态编号
     * @param uid 用户编号
     */
    public void removeDynamicByIdAndUid(Long id, Integer uid) {
        Dynamic dynamic = dynamicDao.removeDynamicByIdAndUid(id, uid);
        if(null != dynamic) {
            //删除评论和点赞
            dynamicDao.removeCommentByDid(dynamic.getId());
            dynamicDao.removeFlowerByDid(dynamic.getId());
        }
    }

    /**
     * 保存点赞信息
     * @param flower 点赞信息
     */
    public void saveFlower(Flower flower) {
        flower.setAddTime(System.currentTimeMillis());
        dynamicDao.insertFlower(flower);
    }

    /**
     * 删除点赞信息
     * @param id 点赞编号
     * @param uid 用户编号
     */
    public void removeFlowerByIdAndUid(Long id, Integer uid) {
        dynamicDao.removeFlowerByIdAndUid(id, uid);
    }

    /**
     * 插入评论信息
     * @param comment 评论信息
     */
    public void saveComment(Comment comment) {
        comment.setAddTime(System.currentTimeMillis());
        dynamicDao.insertComment(comment);
    }

    /**
     * 根据评论编号和用户编号删除评论信息
     * @param id 评论编号
     * @param uid 用户编号
     */
    public void removeCommentByIdAndUid(Long id, Integer uid) {
        dynamicDao.removeCommentByIdAndUid(id, uid);
    }
}
