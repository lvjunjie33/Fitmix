package com.business.app.payOrder;

import com.business.core.entity.payOrder.PayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Sin on 2016/4/13.
 *
 * 支付订单
 */
@Service
public class PayOrderService {

    @Autowired
    private PayOrderDao payOrderDao;

    /**
     * 支付订单
     *
     * @param orderNo 订单编号
     * @param payName 支付名称
     * @param payMoney 支付金额
     * @param payPlatform 支付平台
     * @param paymentMethod 支付方式
     */
    public void addPayOrder(String orderNo, String payName,
                            double payMoney, int payPlatform, int paymentMethod) {

        PayOrder payOrder = buildPayOrder(orderNo, payName, payMoney, payPlatform, paymentMethod);
        payOrderDao.insertPayOrder(payOrder);
    }

    /**
     * 下单时间（与第三方下单 时 更新）
     *
     * @param orderNo 订单编号
     */
    public void placeOrder(String orderNo) {
        payOrderDao.updatePayOrderByOrderNo(orderNo,
                Update.update("payState", PayOrder.PAY_STATE_WAIT).
                        set("placeOrderTime", System.currentTimeMillis()));
    }

    /**
     * 支付通知
     *
     * @param orderNo 支付单号
     * @param platformNo 支付平台单号
     */
    public void payNotify(String orderNo, String platformNo) {
        payOrderDao.updatePayOrderByOrderNo(orderNo,
                Update.update("payState", PayOrder.PAY_STATE_SUCCESS).
                        set("platformNo", platformNo).
                        set("successTime", System.currentTimeMillis()));
    }

    /**
     * 支付 异常
     *
     * @param orderNo 订单号
     */
    public void payError(String orderNo) {
        payOrderDao.updatePayOrderByOrderNo(orderNo,
                Update.update("payState", PayOrder.PAY_STATE_ERROR));
    }

    /**
     * 构建 支付订单
     *
     * @param orderNo 订单编号
     * @param payName 支付名称
     * @param payMoney 支付金额
     * @param payPlatform 支付平台
     * @param paymentMethod 支付方式
     * @return 支付订单
     */
    public PayOrder buildPayOrder(String orderNo, String payName,
                                  double payMoney, int payPlatform, int paymentMethod) {
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderNo(orderNo);
        payOrder.setPayName(payName);
        payOrder.setPayMoney(payMoney);
        payOrder.setPayPlatform(payPlatform);
        payOrder.setPaymentMethod(paymentMethod);

        payOrder.setAddTime(System.currentTimeMillis());
        payOrder.setPayState(PayOrder.PAY_STATE_CREATE);
        return payOrder;
    }
}
