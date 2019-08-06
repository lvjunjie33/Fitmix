package com.business.core.utils;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by weird on 2016/2/26.
 */
public class SmartWeatherUtil {
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";
    private static final String appid = "c0cc8d8b1a588c35";
    private static final String private_key = "2750cf_SmartWeatherAPI_9e83080";
    private static final String url_header="http://open.weather.com.cn/data/?";

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     * @param url 被签名的字符串
     * @param privatekey  私钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String url, String privatekey) throws Exception
    {
        byte[] data=privatekey.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = url.getBytes(ENCODING);
        //完成 Mac 操作
        return mac.doFinal(text);
    }

    /**
     * 获取URL通过privatekey加密后的码
     * @param url
     * @param privatekey
     * @return
     * @throws Exception
     */
    private static String getKey(String url, String privatekey) throws Exception {
        byte[] key_bytes = HmacSHA1Encrypt(url, privatekey);
        return URLEncoder.encode(new BASE64Encoder().encode(key_bytes),ENCODING);
    }
    /**
     * 组装url的地址
     * @param areaid 地区id
     * @param type   数据类型
     * @param date  时间
     * @return
     * @throws Exception
     */
    private static String getInstanceURL(String areaid,String type,String date) throws Exception{
        String keyurl=url_header+"areaid="+areaid+"&type="+type+"&date="+date+"&appid=";
        String key=getKey(keyurl+appid,private_key);
        String appid6 = appid.substring(0, 6);
        return keyurl+appid6+"&key=" + key;
    }
    /**
     * 获取访问URL
     * @param areaid  地区编号
     * @param type   获取类型数:
     *               天气指数：index_f(基础) 、 index_v(常规)
    3 天常规预报 (24 小时 ):forecast_f(基础 ) 、forecast_v (常规)
     * @return
     */
    public static String getActionURL(String areaid,String type){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        String date = dateFormat.format(new Date());
        try {
            return getInstanceURL(areaid,type,date);
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String interfaceURL = getActionURL("101280601", "forecast_v");
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet(interfaceURL);
            request.addHeader(HttpHeaders.ACCEPT, "application/json;charset=utf-8");
            CloseableHttpResponse response = client.execute(request);
            String bodyAsString = EntityUtils.toString(response.getEntity(), "utf-8");
            response.close();
            System.err.println(bodyAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
