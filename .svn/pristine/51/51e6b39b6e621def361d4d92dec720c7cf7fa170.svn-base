package com.business.bbs.article;

import com.business.bbs.base.support.AbstractDao;
import com.business.bbs.base.support.AbstractService;
import com.business.core.entity.bbs.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fenxio on 2016/9/13.
 */
@Service
public class ArticleService extends AbstractService<Article> {

    @Autowired
    private ArticleDao articleDao;

    @Override
    protected AbstractDao<Article> getAbstractDao() {
        return articleDao;
    }

    /**
     * 获取首页展示文章列表
     * @return
     */
    public List<Article> getIndexArticleList() {
        Query query = new Query(Criteria.where("status").is(Article.STATUS_RELEASE));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        query.limit(5);
        return articleDao.find(query);
    }

    /**
     * 更新浏览量
     * @param aid
     */
    public void incViewCountById(Integer aid) {
        Query query = new Query(Criteria.where("id").is(aid));
        Update update = new Update();
        update.inc("viewCount", 1);
        articleDao.updateFirst(query, update);
    }

    /**
     * 获取运动早知道 列表
     * @return
     */
    public List<Article> findSportArticle() {
        Query query = new Query(Criteria.where("status").is(Article.STATUS_RELEASE).and("category").is("运动早知道"));
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        query.limit(5);
        return articleDao.find(query);
    }
}
