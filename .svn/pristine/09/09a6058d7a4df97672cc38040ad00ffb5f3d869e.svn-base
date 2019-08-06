package com.business.bbs.dynamic;

import com.business.bbs.base.support.AbstractDao;
import com.business.bbs.user.UserDao;
import com.business.core.entity.Page;
import com.business.core.entity.dynamic.Comment;
import com.business.core.entity.dynamic.Dynamic;
import com.business.core.entity.dynamic.Flower;
import com.business.core.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fenxio on 2016/10/8.
 */
@Repository
public class DynamicDao extends AbstractDao<Dynamic> {

    @Autowired
    private UserDao userDao;

    /**
     * 获取评论列表（分页）
     * @param id
     * @param index
     * @return
     */
    public Page<Comment> findCommentPageByDid(Long id, Integer index) {
        Page<Comment> page = new Page<>();
        page.setSize(10);
        page.setIndex(index);
        Query query = new Query(Criteria.where("did").is(id));
        query.with(new Sort(Sort.Direction.ASC, "addTime"));
        findEntityPage(Comment.class, page, query);
        if(page.getResult() != null && page.getResult().size() > 0) {
            List<Comment> list = page.getResult();
            for(Comment comment : list) {
                comment.setFromUser(userDao.findById(User.class, comment.getFromUid(), "id", "avatar", "name"));
                if(null != comment.getToUid()) {
                    comment.setToUser(userDao.findById(User.class, comment.getToUid(), "id", "avatar", "name"));
                }
            }
        }
        return page;
    }

    /**
     * 获取点赞列表
     * @param id
     * @param index
     * @return
     */
    public Page<Flower> findFlowerPageByDid(Long id, Integer index) {
        Page<Flower> page = new Page<>();
        page.setSize(10);
        page.setIndex(index);
        Query query = new Query(Criteria.where("did").is(id));
        query.with(new Sort(Sort.Direction.ASC, "addTime"));
        findEntityPage(Flower.class, page, query);
        if(page.getResult() != null && page.getResult().size() > 0) {
            List<Flower> list = page.getResult();
            for(Flower flower : list) {
                flower.setUser(userDao.findById(User.class, flower.getUid(), "id", "avatar", "name"));
            }
        }
        return page;
    }

    /**
     * 获取点赞数
     * @param id
     * @return
     */
    public Long getFlowerCountByDid(Long id) {
        Query query = new Query(Criteria.where("did").is(id));
        return getRoutingMongoOps().count(query, Flower.class);
    }

    /**
     * 获取评论数
     * @param id
     * @return
     */
    public Long getCommentCountByDid(Long id) {
        Query query = new Query(Criteria.where("did").is(id));
        return getRoutingMongoOps().count(query, Comment.class);
    }
}
