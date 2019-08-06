package com.business.core.utils;

import com.business.core.entity.SysParam;

import java.util.UUID;

/**
 * Created by Sin on 2016/4/13.
 */
public class PayUtil {

    /**
     * 生产订单号
     * @return 新的订单号
     */
    public static String createOrderNo() {
//        return UUID.randomUUID().toString().replace("-", "");
        return String.valueOf(System.currentTimeMillis()) + String.valueOf(MathUtil.randomStr(10));
    }
}
