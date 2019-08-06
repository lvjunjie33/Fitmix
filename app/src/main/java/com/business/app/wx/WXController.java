package com.business.app.wx;

import com.business.core.client.WXCenterClient;
import com.business.core.utils.EncryptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by sin on 2015/10/15.
 */
@Controller
public class WXController {


    /**
     * 校验来自微信服务器的请求
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @throws Exception
     */
    @RequestMapping(value = "/weiXinInterface", method = RequestMethod.GET)
    @ResponseBody
    public void weiXinInterface(HttpServletResponse response,
                                @RequestParam(value = "signature") String signature,
                                @RequestParam(value = "timestamp") String timestamp,
                                @RequestParam(value = "nonce") String nonce,
                                @RequestParam(value = "echostr") String echostr) throws Exception {
        // 将token、timestamp、nonce三个参数进行字典序排序
        String[] strArray = new String[]{"ZXCVBNMQWERTYUIOP", timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder content = new StringBuilder();
        for (String s : strArray) {
            content.append(s);
        }
        //将三个参数字符串拼接成一个字符串进行sha1加密，并与signature对比,如果相同返回echostr
        if (EncryptUtils.sha(content.toString()).equals(signature)) {
            response.getWriter().print(echostr);
        }
    }


    /**
     * 处理所有请求
     * @throws Exception
     */
    @RequestMapping(value = "/weiXinInterface", method = RequestMethod.POST)
    @ResponseBody
    public String weiXinInterface(HttpServletRequest request) throws Exception {
        return WXCenterClient.parseMessage(request);
    }
}
