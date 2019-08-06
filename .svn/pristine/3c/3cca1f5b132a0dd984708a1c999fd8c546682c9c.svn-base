package com.business.app.activityOther;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.entity.SysParam;
import com.business.core.entity.activity.ActivityUser;
import com.business.core.entity.activityOther.ActivityJiaFeiMao;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.MathUtil;
import com.business.core.utils.WXUtil;
import com.business.core.utils.XmlUtil;
import org.apache.http.entity.StringEntity;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Sin on 2016/2/17.
 * TODO 该接口不提供api
 */
@ApiIgnore
@Controller
@RequestMapping("activity/jia-fei-mao")
public class ActivityJiaFeiMaoController extends BaseControllerSupport {

    @Autowired
    private ActivityJiaFeiMaoService activityJiaFeiMaoService;

    ///
    /// 加菲猫 活动

    /**
     * 用户报名页
     */
    @RequestMapping(value = "sign-up", method = RequestMethod.GET)
    public String signUp(Model model) {
        Object[] result = activityJiaFeiMaoService.getSignUpCount();
        model.addAttribute("signUpCount", result[0]);
        model.addAttribute("publicGroupCount", result[1]);
        model.addAttribute("sanRenGroupCount", result[2]);
        return "activityOther/jiaFeiMao/sign-up";
    }

    /**
     * 加菲猫 活动 报名
     *
     * @param group 组
     * @param email 电子邮箱
     * @param mobileName 移动电话名称
     * @param mobilePhone 移动电话
     * @param emergencyName 紧急联系人名
     * @param emergencyPhone 紧急联系人电话
     * @param memberJson 成员JSON
     * @return 0、成功
     */
    @RequestMapping(value = "sign-up-data", method = RequestMethod.POST)
    public void signUpData(Model model, @RequestParam("group") String group,
                             @RequestParam("email") String email,
                             @RequestParam("mobileName") String mobileName,
                             @RequestParam("mobilePhone") String mobilePhone,
                             @RequestParam("emergencyName") String emergencyName,
                             @RequestParam("emergencyPhone") String emergencyPhone,
                             @RequestParam("memberJson") String memberJson) {

        Object[] result = activityJiaFeiMaoService.signUpData(group, email, mobileName, mobilePhone, emergencyName, emergencyPhone, memberJson);

        switch ((int)result[0]) {
            case 0:
                ActivityJiaFeiMao activityJiaFeiMao = (ActivityJiaFeiMao) result[1];
                model.addAttribute("redirect", "activity/jia-fei-mao/pay-redirect.htm?signUpId=" + activityJiaFeiMao.getId());
                break;
            case 1:
                model.addAttribute("code", "2383");
                model.addAttribute("msg", "改手机号已成功注册过！");
                break;
        }
    }



    /**
     * 支付跳转
     *
     * @param signUpId 编号
     */
    @RequestMapping("pay-redirect")
    public String payRedirect(HttpServletResponse response, @RequestParam("signUpId") Integer signUpId) throws IOException {
        ActivityJiaFeiMao activityJiaFeiMao = activityJiaFeiMaoService.getActivityJiaFeiMao(signUpId);
        if (activityJiaFeiMao == null) {
            response.getWriter().println("支付失败.. 请稍后再试!");
            return redirect("activity/jia-fei-mao/sign-up.htm");
        }
        else {
            if (activityJiaFeiMao != null && activityJiaFeiMao.getTradeStatus().equals(ActivityUser.TRADE_STATUS_SUCCESS)) {
                return redirect("activity/jia-fei-mao/queryDetail.htm?mobilePhone=" + activityJiaFeiMao.getMobilePhone());
            }
            return redirect("activity/jia-fei-mao/we-chat-auth.htm?signUpId=" + signUpId + "&money=" + activityJiaFeiMao.getNeedMoney());
        }
    }

    @RequestMapping("we-chat-auth")
    public String weChatAuth(HttpServletRequest request,
                             @RequestParam("signUpId") Integer signUpId,
                             @RequestParam("money") Double money) {
        String str = HttpUtil.getServerPath(request);
        str = str.substring(0, str.lastIndexOf(":")) + "/";    // TODO: 2015/12/4 暂没好的处理方法  地址会带上 80 端口
        String backUri = str + "activity/jia-fei-mao/we-chat-pay.htm?signUpId=" + signUpId + "&body=加菲猫&money=" + money;
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
        return redirectOutside(url2);
    }


    @RequestMapping("we-chat-pay")
    public String activity20151201Pay (Model model,
                                       HttpServletRequest request,
                                       @RequestParam(value = "signUpId") Integer signUpId,
                                       @RequestParam(value = "money") Double money,
                                       @RequestParam(value = "body") String body,
                                       @RequestParam(value = "code") String code) {
        String finalmoney = String.format("%.2f", money);
        finalmoney = finalmoney.replace(".", "");

        String notifyUrl = HttpUtil.getServerPath(request);
        notifyUrl = notifyUrl.substring(0, notifyUrl.lastIndexOf(":")) + "/activity/jia-fei-mao/we-chat-notify.htm";

        //商户相关资料
        String appId = SysParam.INSTANCE.APP_MP_WEI_XIN_APP_ID;
        String appSecret = SysParam.INSTANCE.APP_MP_WEI_XIN_APP_SECRET;
        String partnerKey = SysParam.INSTANCE.APP_MP_WEI_XIN_APP_PARTNER_KEY;
        String openid = "";
        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        String auth2 = HttpUtil.get(URL);
        System.out.println(auth2);
        JSONObject jsonObject = JSON.parseObject(auth2);
        if (null != jsonObject) {
            openid = jsonObject.getString("openid");
        }

        //非必输
        Map<String, String> packageParams = new TreeMap<>();
        packageParams.put("appid", appId);
        packageParams.put("mch_id", "1293221201");
        packageParams.put("nonce_str", MathUtil.randomStr(20));
        packageParams.put("body", body);
        packageParams.put("attach", "abc");
        packageParams.put("out_trade_no",  MathUtil.randomStr(20));

        //这里写的金额为1 分到时修改
        packageParams.put("total_fee", finalmoney);
        packageParams.put("spbill_create_ip", HttpUtil.getIP(request));
        packageParams.put("notify_url", notifyUrl);

        packageParams.put("trade_type", "JSAPI");
//        packageParams.put("openid", "omfTQvqBW5rd52Ca3SkOnvIePAIE");
        packageParams.put("openid", openid);

        String sign = WXUtil.getSign(packageParams, partnerKey);
        packageParams.put("sign", sign);

        String xml = XmlUtil.toXml(packageParams);

        System.out.println(xml);
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String result = HttpUtil.post(createOrderURL, new StringEntity(xml, "UTF-8"));
        System.out.println(result);

        try {
            //读取输入流 解析 xml
            Map<String, Element> elementMap = XmlUtil.parseMap(result);

            String prepay_id = elementMap.get("prepay_id").getStringValue();

            activityJiaFeiMaoService.tradeCarried(signUpId,
                    packageParams.get("nonce_str"), packageParams.get("out_trade_no"), money, openid);

            SortedMap<String, String> paySignMap = new TreeMap<>();
            paySignMap.put("appId", appId);
            paySignMap.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
            paySignMap.put("nonceStr", packageParams.get("nonce_str"));
            paySignMap.put("package", "prepay_id=" + prepay_id);
            paySignMap.put("signType", "MD5");
            String paySign = WXUtil.getSign(paySignMap, partnerKey);

            model.addAttribute("signUpId", signUpId); // 用户成功跳转
            model.addAttribute("appId", paySignMap.get("appId"));
            model.addAttribute("timeStamp", paySignMap.get("timeStamp"));
            model.addAttribute("nonceStr", paySignMap.get("nonceStr"));
            model.addAttribute("packageValue", paySignMap.get("package"));
            model.addAttribute("sign", paySign);
        } catch (Exception e) {
            System.out.println("编号：" + signUpId);
            e.printStackTrace();
        }
        return "activityOther/jiaFeiMao/we-chat-pay";
    }


    @RequestMapping("we-chat-notify")
    @ResponseBody
    public String weChatPayNotify(HttpServletRequest request) {
        try {
            Map<String, Element> elementMap = XmlUtil.parseMap(request.getInputStream());
            for (Map.Entry<String, Element> entry : elementMap.entrySet()) {
                System.out.println(entry.getKey() +" --- " + entry.getValue().getStringValue());
            }
            activityJiaFeiMaoService.weChatNotify(elementMap);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("queryInput")
    public String queryInput(Model model) {
        return "activityOther/jiaFeiMao/query-input";
    }

    @RequestMapping("queryDetail")
    public String queryDetail(Model model, @RequestParam("mobilePhone") String mobilePhone) {
        model.addAttribute("data", activityJiaFeiMaoService.queryDetail(mobilePhone));
        return "activityOther/jiaFeiMao/query-detail";
    }

    @RequestMapping("pay-success")
    public String paySuccess() {
        return "activityOther/jiaFeiMao/success";
    }

    @RequestMapping("sm")
    public String sm() {
        return "activityOther/jiaFeiMao/sm";
    }
}
