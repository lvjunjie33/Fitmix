package com.business.app.shop.liumi;

import com.alibaba.fastjson.JSONObject;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.user.UserService;
import com.business.core.entity.shop.LiumiOrder;
import com.business.core.utils.LiumiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by zhangtao on 2016/11/15.
 */
@Api(value = "liumi", description = "流米流量接口")
@Controller
@RequestMapping("liumi")
public class LiumiController extends BaseControllerSupport {

    @Autowired
    private LiumiOrderService liumiOrderService;
    @Autowired
    private UserService userService;

    /**
     * 流量兑换接口
     * @param mobile 手机号码
     * @param uid 用户编号
     * @param productId 产品编号
     */
    @ApiOperation(value = "兑换流量接口", notes = "兑换流量接口", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping(value = "exchange", method = RequestMethod.POST)
    public void exchange(HttpServletRequest request,
                         @ApiParam(required = true, value = "手机号") @RequestParam("mobile") String mobile,
                         @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                         @ApiParam(required = true, value = "产品编号") @RequestParam("productId") Integer productId,
                         Model model) {
        LiumiOrder liumiOrder = liumiOrderService.placeOrder(request, mobile, uid, productId, getLanguage());
        model.addAttribute("liumiOrder", liumiOrder);
    }

    /**
     * 流米回调接口
     * @param result 结果
     */
    @ApiIgnore
    @RequestMapping("callback")
    public void callback(HttpServletResponse response, @RequestBody String result) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(result);
        String orderNo = jsonObject.get("extNo").toString();
        String status = jsonObject.get("status").toString();

        LiumiOrder liumiOrder = liumiOrderService.selectByOrderNo(orderNo);
        if(liumiOrder != null) {
            liumiOrderService.callbackHandle(LiumiUtil.isSuccess(status), liumiOrder, result);
            PrintWriter out = response.getWriter();
            out.println(1);
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println(0);
            out.flush();
            out.close();
        }
    }
}
