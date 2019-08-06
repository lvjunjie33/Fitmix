package com.business.msg.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 短信http接口的java代码调用示例
 * 基于Apache HttpClient 4.3
 *
 * @author songchao
 * @since 2015-04-03
 */

public class YunPianUtil {

    private static Logger logger = LoggerFactory.getLogger(YunPianUtil.class);

    public static void main(String[] args) throws IOException, URISyntaxException {

        //修改为您要发送的手机号
        String mobile = "18681513201";
        String text = "恭喜您已成功报名第三届深圳迎新跑，您的编号为：xxxx，姓名：xxx，性别：男x，组别xx公里，衣服尺码：xxx，如信息有误，请及时联系我们，祝您2016新想事成！1月1日深圳湾运动公园，不见不散。";
        //模板发送的调用示例
        System.out.println(YunPianUtil.sendSms(text, mobile));
    }

    /**
     * 取账户信息
     *
     * @return json格式字符串
     * @throws IOException
     */

    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", apikey);
        return post(SmsConfig.URI_GET_USER_INFO, params);
    }

    /**
     * 智能匹配模版接口发短信
     *
     * @param text   　短信内容
     * @param mobile 　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String sendSms(String text, String mobile) {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", SmsConfig.API_KEY);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(SmsConfig.URI_SEND_SMS, params);
    }

    public static Map<String, String> sendSms(String text, Collection<String> mobiles) {
        Map<String, String> codes = new HashMap<>();
        for (String mobile : mobiles) {
            String code = sendSms(text, mobile);
            codes.put(mobile, code);
        }
        return codes;
    }

    /**
     * 通过模板发送短信(不推荐)
     *
     * @param tpl_id    　模板id
     * @param tpl_value 　模板变量值
     * @param mobile    　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String tplSendSms(long tpl_id, String tpl_value, String mobile) {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", SmsConfig.API_KEY);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        String result = post(SmsConfig.URI_TPL_SEND_SMS, params);
        logger.info("sms send tplSendSms [{}] success mobile {}", tpl_id, mobile);
        return result;
    }

    /**
     * 通过接口发送语音验证码
     * @param apikey apikey
     * @param mobile 接收的手机号
     * @param code   验证码
     * @return
     */

    public static String sendVoice(String apikey, String mobile, String code) {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(SmsConfig.URI_SEND_VOICE, params);
    }

    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, SmsConfig.ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }
}

class SmsConfig {

    //查账户信息的http地址
    protected static String URI_GET_USER_INFO = "http://yunpian.com/v1/user/get.json";

    //智能匹配模版发送接口的http地址
    protected static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";

    //模板发送接口的http地址
    protected static String URI_TPL_SEND_SMS = "http://yunpian.com/v1/sms/tpl_send.json";

    //发送语音验证码接口的http地址
    protected static String URI_SEND_VOICE = "http://yunpian.com/v1/voice/send.json";

    //编码格式。发送编码格式统一用UTF-8
    protected static String ENCODING = "UTF-8";

    protected static final String API_KEY = "9f15907074ecda5bd039ae92397556f3";
}


