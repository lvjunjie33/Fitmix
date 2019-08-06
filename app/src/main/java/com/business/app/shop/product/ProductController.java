package com.business.app.shop.product;

import com.business.app.base.support.BaseControllerSupport;
import com.business.app.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhangtao on 2016/11/28.
 */
@Api(value = "product", description = "产品接口")
@Controller
@RequestMapping("product")
public class ProductController extends BaseControllerSupport {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    /**
     * 获取流米产品列表
     */
    @ApiOperation(value = "获取流米产品接口", notes = "获取流米产品接口", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping(value = "get-liumi-product",method = RequestMethod.GET)
    public void getProductList(Model model) {
        model.addAttribute("product", productService.getLiumiProduct());
    }


}
