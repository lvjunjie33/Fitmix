package com.business.work.bbs.article;

import com.business.core.entity.Page;
import com.business.core.entity.bbs.Article;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;

/**
 * Created by fenxio on 2016/9/9.
 */
@Repository
public class ArticleDao extends BaseMongoDaoSupport {
    /**
     * 保存文章信息
     * @param article
     */
    public void insert(Article article) {
        insertToMongo(article);
    }

    /**
     * 根据编号查找文章
     * @param id
     * @param fields
     * @return
     */
    public Article findById(Integer id, String ... fields) {
        return findEntityById(Article.class, id, fields);
    }

    /**
     * 分页查找文章
     * @param page
     * @param fields
     */
    public void findArticlePage(Page<Article> page, String ... fields) {
        Criteria criteria = new Criteria();
        LinkedHashMap<String, Object> filter = page.getFilter();
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        findEntityPage(Article.class, page, query, fields);
    }

    /**
     * 根据编号 更新文章信息
     * @param id
     * @param update
     */
    public void updateArticleById(Integer id, Update update) {
        updateEntityFieldsById(Article.class, id, update);
    }

    /**
     * 删除文章
     * @param id
     */
    public void deleteById(Integer id) {
        removeEntityById(Article.class, id);
    }
}
