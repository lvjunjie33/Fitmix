package com.business.work.mixComment;

import com.business.core.entity.Page;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixComment;
import com.business.core.operations.mixComment.MixCommentCoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * Created by sin on 2015/8/7 0007.
 */
@Service
public class MixCommentService {

    @Autowired
    private MixCommentDao mixCommentDao;
    @Autowired
    private MixCommentCoreDao mixCommentCoreDao;

    /**
     * 分页查询
     * @param page
     */
    public void mixCommentPage(Page<MixComment> page) {
        mixCommentDao.findMixCommentPage(page);
    }

    /**
     * 删除 mix歌曲评论
     * @param id 编号
     */
    public void removeMixComment(Long id) {
        mixCommentCoreDao.updateMixCommentById(id, Update.update("state", MixComment.STAT_NO));
    }

    /**
     * 置顶评论
     * @param mid 用户编号
     * @param id 评论编号
     */
    public void topMixComment(Integer mid, Long id) {
        MixComment mixComment = mixCommentDao.findMixCommentWithSortFirst(mid, "sort");
        mixCommentCoreDao.updateMixCommentById(id, Update.update("sort", mixComment.getSort() + 1));
    }
}
