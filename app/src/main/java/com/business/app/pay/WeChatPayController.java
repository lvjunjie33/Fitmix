package com.business.app.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.payOrder.PayOrderService;
import com.business.core.entity.SysParam;
import com.business.core.entity.payOrder.PayOrder;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.PayUtil;
import com.business.core.utils.WXUtil;
import com.business.core.utils.XmlUtil;
import org.apache.http.entity.StringEntity;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Sin on 2016/4/13.
 *
 * 微信支付平台
 *
 * TODO 这个类的 api目前不提供
 */
@ApiIgnore
@Controller
@RequestMapping("we_chat/pay")
public class WeChatPayController extends BaseControllerSupport {

    Logger LOGGER = LoggerFactory.getLogger(WeChatPayController.class);

    @Autowired
    private PayOrderService payOrderService;

    ///
    /// WeChat 公众号支付

    /**
     * WeChat 公众号支付
     *
     * @param orderNo 订单号
     * @param money 支付金额
     * @param payName 支付名称（名称） Fitmix - 迎新跑
     * @param callbackUrl 支付成功 回调地址
     *
     * @return 状态
     */
    @RequestMapping("public_number_pay")
    public String publicNumberPay(HttpServletRequest request,
                                  @RequestParam("orderNo") String orderNo,
                                  @RequestParam("money") Double money,
                                  @RequestParam("payName") String payName,
                                  @RequestParam(value = "callbackUrl", required = false) String callbackUrl) {

        payName = "Fitmix - " + payName;
        String str = HttpUtil.getServerPath(request);
        str = str.substring(0, str.lastIndexOf(":")) + "/";
        // 处理 callbackUrl
        callbackUrl = StringUtils.isEmpty(callbackUrl) ? "we_chat/pay/success.htm" : callbackUrl;
        String backUri = str + "we_chat/pay/public_number_auth_redirect.htm?orderNo=" + orderNo + "&money=" + money + "&payName=" + payName + "&callbackUrl=" + callbackUrl;

        System.out.println(backUri);
        backUri = URLEncoder.encode(backUri);
        //scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
        String url2 = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" +  SysParam.INSTANCE.APP_MP_WEI_XIN_APP_ID +
                "&redirect_uri=" + backUri +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=2323" +
                "#wechat_redirect";

        LOGGER.info("WeChat Pay Auth redirect. orderNo：{}  money：{} payName：{}", orderNo, money, payName);
        // 记录 信息
        payOrderService.addPayOrder(orderNo, payName, money, PayOrder.PAY_PLATFORM_WW_CHAT, PayOrder.PAY_STATE_WAIT);
        return redirectOutside(url2);
    }

    /**
     * WeChat 公众平台 授权认证
     *
     * @param orderNo 订单号
     * @param money 支付金额
     * @param payName 支付名称
     */
    @RequestMapping("public_number_auth_redirect")
    public String publicNumberAuthRedirect(Model model,
                                 HttpServletRequest request,
                                 @RequestParam("orderNo") String orderNo,
                                 @RequestParam("money") Double money,
                                 @RequestParam("payName") String payName,
                                 @RequestParam("callbackUrl") String callbackUrl,
                                 @RequestParam("code") String code) {
        // 格式化 金额
        String finalmoney = String.format("%.2f", money);
        finalmoney = finalmoney.replace(".", "");

        // 处理通知路径
        String notifyUrl = HttpUtil.getServerPath(request);
        notifyUrl = notifyUrl.substring(0, notifyUrl.lastIndexOf(":")) + "/we_chat/pay/pay_notify.htm";

        // 商户资料
        String appId = SysParam.INSTANCE.APP_MP_WEI_XIN_APP_ID;
        String appSecret = SysParam.INSTANCE.APP_MP_WEI_XIN_APP_SECRET;
        String partnerKey = SysParam.INSTANCE.APP_MP_WEI_XIN_APP_PARTNER_KEY;

        // 公众号授权，获取用户信息
        String openid = "";
        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        String auth2 = HttpUtil.get(URL);
        System.out.println(auth2);
        JSONObject jsonObject = JSON.parseObject(auth2);
        if (null != jsonObject) {
            openid = jsonObject.getString("openid");
        }
        if (callbackUrl.contains("?")) {// 将openId传入回调接口中
            callbackUrl += "&openId=" + openid;
        } else {
            callbackUrl += "?openId=" + openid;
        }

        //非必输
        Map<String, String> packageParams = new TreeMap<>();
        packageParams.put("appid", appId);
        packageParams.put("mch_id", "1293221201");
        packageParams.put("nonce_str", PayUtil.createOrderNo());
        packageParams.put("body", payName);
        packageParams.put("attach", "abc");
        packageParams.put("out_trade_no",  orderNo);

        packageParams.put("total_fee", finalmoney);
        packageParams.put("spbill_create_ip", HttpUtil.getIP(request));
        packageParams.put("notify_url", notifyUrl);

        packageParams.put("trade_type", "JSAPI");
        packageParams.put("openid", openid);
        // 签名加密
        String sign = WXUtil.getSign(packageParams, partnerKey);
        packageParams.put("sign", sign);
        // 转换 xml
        String xml = XmlUtil.toXml(packageParams);

        // 预付下单
        System.out.println(xml);
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String result = HttpUtil.post(createOrderURL, new StringEntity(xml, "UTF-8"));
        System.out.println(result);

        try {
            //读取输入流 解析 xml
            Map<String, Element> elementMap = XmlUtil.parseMap(result);

            // 获取预付订单号
            String prepay_id = elementMap.get("prepay_id").getStringValue();
            SortedMap<String, String> paySignMap = new TreeMap<>();
            paySignMap.put("appId", appId);
            paySignMap.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
            paySignMap.put("nonceStr", packageParams.get("nonce_str"));
            paySignMap.put("package", "prepay_id=" + prepay_id);
            paySignMap.put("signType", "MD5");

            // 再次签名加密 （返回 h5 用户进行支付）
            String paySign = WXUtil.getSign(paySignMap, partnerKey);
            model.addAttribute("appId", paySignMap.get("appId"));
            model.addAttribute("timeStamp", paySignMap.get("timeStamp"));
            model.addAttribute("nonceStr", paySignMap.get("nonceStr"));
            model.addAttribute("packageValue", paySignMap.get("package"));
            model.addAttribute("callbackUrl", callbackUrl);
            model.addAttribute("sign", paySign);

            // 更新下单信息
            payOrderService.placeOrder(orderNo);
            LOGGER.info("start placeOrder ...");
        } catch (Exception e) {
            // skip
            e.printStackTrace();
            // error
//            payOrderService.payError(orderNo);
        }
        return "orderPay/we-chat-pay";
    }

    /**
     * 微信支付 通知
     *
     * @return success  error
     */
    @RequestMapping("pay_notify")
    @ResponseBody
    public String payNotify(HttpServletRequest request) {
        try {
            Map<String, Element> elementMap = XmlUtil.parseMap(request.getInputStream());
            for (Map.Entry<String, Element> entry : elementMap.entrySet()) {
                System.out.println(entry.getKey() +" --- " + entry.getValue().getStringValue());
            }
            String orderNo = elementMap.get("out_trade_no").getStringValue(); // 订单号
            String platformNo = elementMap.get("transaction_id").getStringValue(); // 微信订单号
            // 通知更新
            payOrderService.payNotify(orderNo, platformNo);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 支付成功跳转
     *
     * @return 支付成功地址
     */
    @RequestMapping("success")
    public String success() {
        return "orderPay/success";
    }
}
