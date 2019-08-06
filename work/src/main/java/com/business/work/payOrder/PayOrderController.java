package com.business.work.payOrder;

import com.business.core.entity.Page;
import com.business.core.entity.payOrder.PayOrder;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fenxio on 2016/5/28.
 */
@Controller
@RequestMapping("pay-order")
public class PayOrderController extends BaseControllerSupport {

    @Autowired
    private PayOrderService payOrderService;

    /**
     * 订单列表页
     * @param page 分页
     * @return
     */
    @RequestMapping("pay-order-list")
    public String payOrderList(Page<PayOrder> page) {
        payOrderService.list(page);
        return "payOrder/pay-order-list";
    }
}
