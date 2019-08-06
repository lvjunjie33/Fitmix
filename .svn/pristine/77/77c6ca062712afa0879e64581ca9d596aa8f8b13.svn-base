package com.business.app.pay;

import com.alibaba.fastjson.JSON;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.payOrder.PayOrderService;
import com.business.core.alipay.config.AlipayConfig;
import com.business.core.alipay.util.AlipaySubmit;
import com.business.core.entity.payOrder.PayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sin on 2016/4/20.
 *
 * 阿里云 支付 （支付宝）
 *
 * TODO 这个类的 api目前不提供
 */
@ApiIgnore
@Controller
@RequestMapping("ali-pay")
public class AliPayController extends BaseControllerSupport {

    @Autowired
    private PayOrderService payOrderService;

    ///
    /// 网页 h5 支付

    @RequestMapping("h5Pay")
    public String h5Pay(Model model,
                        @RequestParam("tradeNo") String tradeNo,
                        @RequestParam("subject") String subject,
                        @RequestParam("returnUrl") String returnUrl,
                        @RequestParam("totalFee") String totalFee) {

        // 支付参数
        Map<String, String> sParaTemp = new HashMap<>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("return_url", returnUrl);
        sParaTemp.put("out_trade_no", tradeNo);//订单号 必须
        sParaTemp.put("subject", subject);//订单名称 必须
        sParaTemp.put("total_fee",  totalFee);//订单金额 必须
        //sParaTemp.put("uid", uid.toString());//用户id

        // 构建一个 请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        model.addAttribute("sHtmlText", sHtmlText);

        // 添加订单
        payOrderService.addPayOrder(tradeNo, subject, Double.valueOf(totalFee), PayOrder.PAY_PLATFORM_ALI_PAY, PayOrder.PAYMENT_METHOD_ALI_WEB_H5);

        return "alipay/pay";
    }


    @RequestMapping("return-url")
    public void returnUrl(HttpServletRequest request) {
        String returnStr = "RETURN_URL_RETURN";
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Enumeration es = request.getParameterNames();
        String parameterName, parameterValue;
        while (es.hasMoreElements()) {
            parameterName = es.nextElement().toString();
            parameterValue = request.getParameter(parameterName);
            params.put(String.valueOf(parameterName), String.valueOf(parameterValue));
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号 TODO 未使用 edward
//        String out_trade_no = params.get("out_trade_no");
        //支付宝交易号 TODO 未使用 edward
//        String trade_no = params.get("trade_no");
        //交易状态
        String trade_status = params.get("trade_status");

        //TODO 未使用 edward
//        String uid = out_trade_no.substring(out_trade_no.lastIndexOf("x") + 1);

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        //计算得出通知验证结果
        if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
            //判断该笔订单是否在商户网站中已经做过处理
            //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            //如果有做过处理，不执行商户的业务程序
            request.setAttribute(returnStr, "验证成功");
        } else {
            request.setAttribute(returnStr, "验证失败");
        }
    }


    @RequestMapping("notify-url")
    @ResponseBody
    public String notifyUrl(HttpServletRequest request) {
        String returnStr = "NOTIFY_URL_RETURN";
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        Enumeration es = request.getParameterNames();
        String parameterName, parameterValue;
        while (es.hasMoreElements()) {
            parameterName = es.nextElement().toString();
            parameterValue = request.getParameter(parameterName);
            params.put(String.valueOf(parameterName), String.valueOf(parameterValue));
        }

        System.out.println(JSON.toJSONString(params));

        for (Map.Entry<String, String> entry: params.entrySet()) {
            System.out.println(entry.getKey() + " - - - " + entry.getValue());
        }
        //商户订单号
        String out_trade_no = params.get("out_trade_no");
        //支付宝交易号
        String trade_no = params.get("trade_no");
        //交易状态 TODO 未使用
//        String trade_status = params.get("trade_status");

        if (params.get("trade_status").equals("TRADE_FINISHED") || params.get("trade_status").equals("TRADE_SUCCESS")) {
            // 更改订单
            payOrderService.payNotify(out_trade_no, trade_no);
        } else {//验证失败
            request.setAttribute(returnStr, "fail");//请不要修改或删除
        }
        return "success";
    }
}
