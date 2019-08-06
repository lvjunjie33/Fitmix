package com.business.work.bbs.category;

import com.business.core.entity.Page;
import com.business.core.entity.bbs.Category;
import com.business.core.utils.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/9/12.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 保存分类信息
     * @param category
     */
    public void saveCategory(Category category) {
        category.setAddTime(System.currentTimeMillis());
        categoryDao.insertCategory(category);
    }

    /**
     * 删除分类信息
     * @param id
     */
    public void deleteCategoryById(Integer id) {
        categoryDao.deleteCategoryById(id);
    }

    /**
     * 根据编号 查找分类信息
     * @param id
     * @return
     */
    public Category findCategoryById(Integer id) {
        return categoryDao.findCategoryById(id);
    }

    /**
     * 修改分类信息
     * @param category
     */
    public void modifyCategoryById(Category category) {
        Update update = ReflectionUtil.getUpdateInfo(category);
        categoryDao.modifyCategoryById(category.getId(), update);
    }

    /**
     * 分页查找 分类信息
     * @param page
     */
    public void list(Page<Category> page) {
        categoryDao.findCategoryPage(page);
    }


    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }
}
