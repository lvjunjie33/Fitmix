package com.business.bbs.article;

import com.business.bbs.base.support.AbstractDao;
import com.business.core.entity.bbs.Article;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fenxio on 2016/9/13.
 */
@Repository
public class ArticleDao extends AbstractDao<Article> {
    public List<Article> find(Query query) {
        return getRoutingMongoOps().find(query, Article.class);
    }
}
