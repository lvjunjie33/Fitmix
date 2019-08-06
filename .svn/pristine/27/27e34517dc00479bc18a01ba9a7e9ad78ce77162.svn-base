package com.business.core.utils;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * lvjj
 * Created by edward on 2018/4/28.
 */
public class WXLoginUitl {

    private static final Logger logger = LoggerFactory.getLogger(WXLoginUitl.class);

    private static final String APPID="wx1eda2538cf0f77da";

    private static final String APPSECRET="92bd808e73076bb242b8c662cf82de00";


    /**
     * 通过微信会调域code请求获取openid access_token
     * */
    public static Map<String, String> getToken(String code){
        Map<String, String> data = new HashMap();
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+APPSECRET+"&code="+code+"&grant_type=authorization_code";
        Gson token_gson = new Gson();
        JsonObject object = token_gson.fromJson(get(url),JsonObject.class);
        data.put("openid", object.get("openid").toString().replaceAll("\"", ""));
        data.put("access_token", object.get("access_token").toString().replaceAll("\"", ""));
        return data;
    }

    /**
     * 封装get请求头
     * */
    public static String get(String url) {
        String body = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            logger.info("create httppost:" + url);
            HttpGet get = new HttpGet(url);
            get.addHeader("Accept-Charset","utf-8");
            HttpResponse response = sendRequest(httpClient, get);
            body = parseResponse(response);
        } catch (IOException e) {
            logger.error("send post request failed: {}", e.getMessage());
        }

        return body;
    }
    /**
     *  发送http请求
     * */
    private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost)
            throws IOException {
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        return response;
    }
    /**
     * 解析response数据，返回字符串结果集
     * */
    private static String parseResponse(HttpResponse response) {
        logger.info("get response from http server..");
        HttpEntity entity = response.getEntity();

        logger.info("response status: " + response.getStatusLine());
        Charset charset = ContentType.getOrDefault(entity).getCharset();
        if (charset != null) {
            logger.info(charset.name());
        }

        String body = null;
        try {
            body = EntityUtils.toString(entity, "utf-8");
            logger.info("body " + body);
        } catch (IOException e) {
            logger.warn("{}: cannot parse the entity", e.getMessage());
        }
        return body;
    }


    public static void main(String[] args) {
        try {
            Map<String,String> data=WXLoginUitl.getToken("061ZvGDx1y17Tg0BoRCx1n4hDx1ZvGD9");
            System.out.println("openid==>"+data.get("openid"));
            System.out.println("access_token==>"+data.get("access_token"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
