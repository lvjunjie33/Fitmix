package com.business.app.shop.share;

import com.business.app.base.support.BaseControllerSupport;
import com.business.app.shop.liumi.LiumiOrderService;
import com.business.app.user.UserService;
import com.business.core.entity.shop.LiumiOrder;
import com.business.core.entity.user.User;
import com.business.core.operations.user.UserCoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangtao on 2017/1/4.
 */
@Api(value = "Share", description = "分享")
@Controller
public class ShareController extends BaseControllerSupport {

    @Autowired
    private UserCoreService userCoreService;
    @Autowired
    private UserService userService;
    @Autowired
    private LiumiOrderService liumiOrderService;


    @ApiOperation(value = "流量分享页面", notes = "流量分享页面", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("share-flow")
    public String shareFlow(@RequestParam("uid") Integer uid,
                            @RequestParam("orderNo") String orderNo , Model model) {
        User user = userService.findUserById(uid, "id", "name", "avatar");
        userCoreService.buildUserFileUrl(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("orderNo", orderNo);
        List<LiumiOrder> list = liumiOrderService.selectByParams(map);
        Integer flow = 0;
        if(list != null && list.size() > 0) {
            String postpackage = list.get(0).getPostpackage();
            flow = Integer.parseInt(postpackage.substring(2, postpackage.length()));
        }
        model.addAttribute("user", user)
             .addAttribute("flow", flow);
        return "shop/share/flow";
    }


}
