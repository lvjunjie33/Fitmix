package com.business.work.shop.product;

import com.business.core.constants.CodeConstants;
import com.business.core.entity.Page;
import com.business.core.entity.shop.Product;
import com.business.work.base.support.BaseControllerSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangtao on 2016/11/23.
 */
@Controller
@RequestMapping("shop/product")
public class ProductController extends BaseControllerSupport {

    @Autowired
    private ProductService productService;

    /**
     * 产品列表
     * @param page
     * @param model
     * @return
     */
    @RequestMapping(value = "product-list")
    public String list(Page<Product> page, Model model) {
        PageHelper.startPage(page.getPageNo(), Page.DEFAULT_PAGE_SIZE).setOrderBy("sort desc");
        List<Product> list = productService.selectByParams(page.getFilter());
        page.setResult(list);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        page.setTotal(pageInfo.getTotal());
        model.addAttribute("page", page);
        return "shop/product/product-list";
    }

    /**
     * 跳转产品添加页面
     * @return
     */
    @RequestMapping(value = "product-add", method = RequestMethod.GET)
    public String productAdd() {
        return "shop/product/product-add";
    }

    /**
     * 添加产品信息
     * @param product
     * @param model
     */
    @RequestMapping(value = "product-add", method = RequestMethod.POST)
    public void productAdd(Product product, Model model) {
        product.setStatus(Product.STATUS_INVALID);
        product.setAddTime(System.currentTimeMillis());
        product.setModifyTime(System.currentTimeMillis());

        if(productService.insertSelective(product) != 1) {
            tip(model, CodeConstants.MISS, "新增失败");
        }

    }


    /**
     * 跳转产品修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "product-modify", method = RequestMethod.GET)
    public String productModify(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("product", productService.selectByPrimaryKey(id));
        return "shop/product/product-modify";
    }

    /**
     * 修改产品信息
     * @param product
     * @param model
     */
    @RequestMapping(value = "product-modify", method = RequestMethod.POST)
    public void productModify(Product product, Model model) {
        if(productService.updateByPrimaryKeySelective(product) != 1) {
            tip(model, CodeConstants.MISS, "修改失败");
        }
    }

}
