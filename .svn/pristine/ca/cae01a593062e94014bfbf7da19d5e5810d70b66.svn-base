package com.business.work.bbs.category;

import com.business.core.entity.Page;
import com.business.core.entity.bbs.Category;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fenxio on 2016/9/12.
 */
@Repository
public class CategoryDao extends BaseMongoDaoSupport {

    /**
     * 保存分类信息
     * @param category
     */
    public void insertCategory(Category category) {
        insertToMongo(category);
    }

    /**
     * 根据分类编号 删除分类信息
     * @param id
     */
    public void deleteCategoryById(Integer id) {
        removeEntityById(Category.class, id);
    }

    /**
     * 分页查找 分类信息
     * @param page
     */
    public void findCategoryPage(Page<Category> page) {
        Query query = new Query();
        findEntityPage(Category.class, page, query);
    }

    /**
     * 根据分类编号
     * @param id
     * @return
     */
    public Category findCategoryById(Integer id) {
        return findEntityById(Category.class, id);
    }

    /**
     * 修改分类信息
     * @param id
     * @param update
     */
    public void modifyCategoryById(Integer id, Update update) {
        updateEntityFieldsById(Category.class, id, update);
    }

    /**
     * 获取所有分类信息
     */
    public List<Category> getAllCategory() {
        return getRoutingMongoOps().findAll(Category.class);
    }
}
