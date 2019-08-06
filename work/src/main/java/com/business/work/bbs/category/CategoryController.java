package com.business.work.bbs.category;

import com.business.core.entity.Page;
import com.business.core.entity.bbs.Category;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by fenxio on 2016/9/12.
 */
@Controller
@RequestMapping("bbs/category")
public class CategoryController extends BaseControllerSupport {

    @Autowired
    private CategoryService categoryService;

    /**
     * 跳转分类列表
     * @param page
     * @return
     */
    @RequestMapping("category-list")
    public String categoryList(Page<Category> page) {
        categoryService.list(page);
        return "bbs/category/category-list";
    }

    /**
     * 跳转 分类 添加页面
     * @return
     */
    @RequestMapping(value = "category-add", method = RequestMethod.GET)
    public String add(){
        return "bbs/category/category-add";
    }

    /**
     * 保存分类 信息
     * @param category
     * @param model
     */
    @RequestMapping(value = "category-add", method = RequestMethod.POST)
    public void add(Category category, Model model) throws Exception{
        categoryService.saveCategory(category);
        model.addAttribute("category", category);
    }

    /**
     * 跳转分类 修改信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "category-modify", method = RequestMethod.GET)
    public String channelAppModify(@RequestParam("id") Integer id, Model model){
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("category", category);
        return "bbs/category/category-modify";
    }

    /**
     * 跳转分类 修改信息
     * @param category
     * @param model
     * @return
     */
    @RequestMapping(value = "category-modify", method = RequestMethod.POST)
    public void channelAppModify(Category category, Model model){
        categoryService.modifyCategoryById(category);
    }

    /**
     * 删除分类
     * @param id
     */
    @RequestMapping(value = "category-remove", method = RequestMethod.POST)
    public void categoryRemove(@RequestParam("id") Integer id) {
        categoryService.deleteCategoryById(id);
    }

}
