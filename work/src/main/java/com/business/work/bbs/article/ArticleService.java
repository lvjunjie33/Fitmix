package com.business.work.bbs.article;

import com.business.core.entity.Page;
import com.business.core.entity.bbs.Article;
import com.business.core.utils.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/9/9.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 保存文章信息
     * @param article
     */
    public void saveArticle(Article article) {
        article.setStatus(Article.STATUS_NOT_RELEASE);
        article.setAddTime(System.currentTimeMillis());
        articleDao.insert(article);
    }

    /**
     * 根据编号查找 文章
     * @param id
     * @return
     */
    public Article findById(Integer id) {
        return articleDao.findById(id);
    }

    /**
     * 修改文章信息
     * @param article
     */
    public void articleModify(Article article) {
        Update update = ReflectionUtil.getUpdateInfo(article);
        articleDao.updateArticleById(article.getId(), update);
    }

    /**
     * 删除文章
     * @param id
     */
    public void deleteById(Integer id) {
        articleDao.deleteById(id);
    }
    /**
     * 分页查找文章信息
     * @param page
     */
    public void list(Page<Article> page) {
        articleDao.findArticlePage(page);
    }



}
