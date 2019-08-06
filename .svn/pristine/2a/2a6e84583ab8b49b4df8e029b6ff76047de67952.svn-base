package com.business.core.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangtao on 2016/11/15.
 */
public class LiumiUtil {
    //测试环境
    private static final String APPKEY = "AQFhbmcFkw";
    private static final String APPSECRET = "gsghkGSKGfhs";
    private static final String BASE_URL = "http://yfbapi.liumi.com/server/";
    // 正式环境
//    private static final String BASE_URL = "http://api.tenchang.com/server/";
//    private static final String APPKEY = "dNzbgUNzTL";
//    private static final String APPSECRET = "Z0Wifdlrnls925QC";
    private static final String CHARSET = "UTF-8";

    private static final String MOBILE_PATTERN = "^(((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[6-8])|(145))\\d{8})|(170[0,5,9])\\d{7}$";

    /**
     *  提交流量订单
     * @param phoneNumber
     * @param extno
     * @param postpackage
     * @param token
     * @return
     */
    public static String placeOrder(String phoneNumber, String extno, String postpackage, String token) {
        String result = "";
        Map<String, String> map = new HashMap<>();
        map.put("appkey", LiumiUtil.APPKEY);
        map.put("token", token);
        map.put("mobile", phoneNumber);
        map.put("postpackage", postpackage);
        map.put("extno", extno);
        map.put("appver", "Http");
        map.put("apiver", "2.0");
        map.put("fixtime", "");
        map.put("des", "0");
        map.put("appsecret", MD5Util.MD5Encode(LiumiUtil.APPSECRET, CHARSET));
        map.put("sign", getSign(map));

        try {

            HttpPost httpPost = new HttpPost(LiumiUtil.BASE_URL + "placeOrder");
            httpPost.setEntity(new StringEntity(JSON.toJSONString(map), CHARSET));
            httpPost.addHeader("Content-Type", "application/json");
            HttpResponse httpResponse = HttpUtil.getHttpClient().execute(httpPost);
            String status = httpResponse.getStatusLine().toString();
            //getEntity()
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity, CHARSET);
                //打印响应内容
                System.out.println("response:" + " status = " + status + " body:" + result);
            }
            //释放资源

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    /**
     * 获取 流米平台 token
     * @return
     */
    public static String getToken() {
        String token = null;
        // 1.获取签名
        Map<String, String> map = new HashMap<>();
        map.put("appkey", LiumiUtil.APPKEY);
        map.put("appsecret", MD5Util.MD5Encode(LiumiUtil.APPSECRET, CHARSET));
        String sign = LiumiUtil.getSign(map);
        map.put("sign", sign);
        try {
            //2.发送请求获取 token
            HttpPost httpPost = new HttpPost(LiumiUtil.BASE_URL + "getToken");
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("appkey", LiumiUtil.APPKEY));
            list.add(new BasicNameValuePair("appsecret", MD5Util.MD5Encode(LiumiUtil.APPSECRET, CHARSET)));
            list.add(new BasicNameValuePair("sign", sign));
            System.out.println(JSON.toJSONString(map));
            httpPost.setEntity(new StringEntity(JSON.toJSONString(map), CHARSET));
            httpPost.addHeader("Content-Type", "application/json");

            HttpResponse httpResponse = HttpUtil.getHttpClient().execute(httpPost);

            String status = httpResponse.getStatusLine().toString();
            //getEntity()
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                token = EntityUtils.toString(httpEntity, CHARSET);
                //打印响应内容
                System.out.println("response:" + " status = " + status + " body:" + token);
            }
            //释放资源

        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    /**
     * 获取手机信息
     * @param phoneNumber
     * @param token
     * @return
     */
    public static String getPhoneInfo(String phoneNumber, String token) {
        String phoneInfo = "";
        Map<String, String> map = new HashMap<>();
        map.put("appkey", LiumiUtil.APPKEY);
        map.put("token", token);
        map.put("mobile", phoneNumber);
        map.put("sign", getSign(map));
        try {
            HttpPost httpPost = new HttpPost(LiumiUtil.BASE_URL + "/phoneInfo");
            httpPost.setEntity(new StringEntity(JSON.toJSONString(map), CHARSET));
            httpPost.addHeader("Content-Type", "application/json");
            HttpResponse httpResponse = HttpUtil.getHttpClient().execute(httpPost);
            String status = httpResponse.getStatusLine().toString();
            //getEntity()
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                phoneInfo = EntityUtils.toString(httpEntity, CHARSET);
                //打印响应内容
                System.out.println("response:" + " status = " + status + " body:" + phoneInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phoneInfo;
    }

    /**
     * 联通 资格预判
     * @param phoneNumber
     * @param token
     * @return
     */
    public static String checkLTPhone(String phoneNumber, String token) {
        String result = "";
        Map<String, String> map = new HashMap<>();
        map.put("appkey", LiumiUtil.APPKEY);
        map.put("token", token);
        map.put("mobile", phoneNumber);
        map.put("appsecret", MD5Util.MD5Encode(LiumiUtil.APPSECRET, CHARSET));
        map.put("packageid", "LT50");
        map.put("sign", getSign(map));
        try {
            HttpPost httpPost = new HttpPost(LiumiUtil.BASE_URL + "/checkLTPhone");
            httpPost.setEntity(new StringEntity(JSON.toJSONString(map), CHARSET));
            httpPost.addHeader("Content-Type", "application/json");
            HttpResponse httpResponse = HttpUtil.getHttpClient().execute(httpPost);
            String status = httpResponse.getStatusLine().toString();
            //getEntity()
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity, CHARSET);
                //打印响应内容
                System.out.println("response:" + " status = " + status + " body:" + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 把数组所有元素排序，并按照“参数参数值”的模式拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 签名字符串
     */
    public static String getSign(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (value != null && !value.equals("")) {
                prestr += key + value;
            }
        }
        System.out.println(" 排序后的参数 ===== " + prestr);
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(prestr.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            prestr = hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return prestr;
    }


    public static String decodeUnicode(String utfString){
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while((i=utfString.indexOf("\\u", pos)) != -1){
            sb.append(utfString.substring(pos, i));
            if(i+5 < utfString.length()){
                pos = i+6;
                sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.print(LiumiUtil.getToken());
//        String token = "2f12d609-d5a8-4aed-a56e-4b3e472bb78e";
//
//        System.out.println(" 电话信息 ： "  + checkLTPhone("18664598804", token));
//        System.out.println(" 电话信息 ： "  + placeOrder("17727809273", PayUtil.createOrderNo(), "DX10", token));

//        System.out.println(decodeUnicode("{\"code\":\"000\",\"isp\":\"1\",\"belongto\":\"\\u5e7f\\u4e1c-\\u6df1\\u5733\\u5e02\\\"}"));
//        System.out.println(isMobile("15110000000"));


        String reg = "^callback\\((.*?)\\);$";
        String target = "callback( {\"client_id\":\"101350480\",\"openid\":\"693789A0B373B16C24850BDDBB082597\"} );";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(target);
        String strs = "";
        while (matcher.find()) {
            strs += matcher.group(1);
        }
        System.out.println("结果：" + strs);


    }


    public static boolean isSuccess(String status) {
        return status.equals("成功");
    }

    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile(MOBILE_PATTERN);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
}
