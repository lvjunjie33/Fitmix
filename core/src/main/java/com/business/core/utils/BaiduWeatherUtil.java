package com.business.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by fenxio on 2016/6/30.
 */
public class BaiduWeatherUtil {

    private static final String AK = "98dfV32uFI52e1iUnlpYKdbbXSBYhiQY";

    private static final String BASE_URI = "http://api.map.baidu.com/telematics/v3/weather?output=json";

    /**
     * 百度 APIKEY
     */
    private static final String APIKEY = "29e2eb9862b5533e440c3a8654a2cdc0";
    /**
     * 中国和世界天气全能版 (百度API) URI
     */
    private static final String BASE_URI_WORLD = "http://apis.baidu.com/heweather/pro/weather";

    /**
     * 获取百度天气
     * @param cityName 城市名称
     * @return
     */
    public static JSONObject getWeather(String cityName) {
        String url = BASE_URI+ "&ak=" + AK + "&location=" + cityName;
        return JSON.parseObject(HttpUtil.get(url));
    }

    /**
     * 中国和世界天气全能版 (百度API)
     * @param cityName 城市名称
     * @return
     */
    public static String getWorldWeather(String cityName) {
        CloseableHttpResponse response = null;
        String responseText = "";
        try {
            String url = BASE_URI_WORLD + "?city=" + URLEncoder.encode(cityName, "utf-8");
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("apikey", APIKEY);
            response = HttpUtil.getHttpClient().execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                responseText = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                throw new IOException("返回结果：" + statusCode);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
        return responseText;
    }


    public static void main(String[] arg0) {
        String aa =  BaiduWeatherUtil.getWorldWeather("深圳");
        System.out.println(aa);
        if (true) {
            return;
        }
        JSONObject jsonObject = JSON.parseObject(BaiduWeatherUtil.getWorldWeather("深圳"));
        JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
        String stauts = jsonArray.getJSONObject(0).get("status").toString();
        String now = jsonArray.getJSONObject(0).get("now").toString();
        String suggestion = jsonArray.getJSONObject(0).get("suggestion").toString();
//        System.out.println("status:" + stauts + " now:" + now + " suggestion:" +suggestion);
        System.out.println(jsonArray.toString());
    }

}
