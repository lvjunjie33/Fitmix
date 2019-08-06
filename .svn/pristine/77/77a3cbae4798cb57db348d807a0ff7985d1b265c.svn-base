package com.business.work.payOrder;

import com.business.core.entity.Page;
import com.business.core.entity.payOrder.PayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fenxio on 2016/5/28.
 */
@Service
public class PayOrderService {

    @Autowired
    private PayOrderDao payOrderDao;

    /**
     * 获取订单分页信息
     * @param page 分页
     */
    public void list(Page<PayOrder> page) {
        payOrderDao.findPayOrderPage(page);
    }
}
