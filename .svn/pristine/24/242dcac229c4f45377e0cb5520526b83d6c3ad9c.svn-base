package com.business.core.utils;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * Created by sin on 2015/10/22.
 */
public class WXUtil{


    public static String setMenu(String token, String param) {
        CloseableHttpResponse response = null;
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
        String responseText = "";
        try {
            // 请求
            HttpPost post = new HttpPost(url);
            post.addHeader("User-Agent", "IOS");
            post.setEntity(new StringEntity(param, "UTF-8"));
            response = HttpUtil.getHttpClient().execute(post);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                responseText = EntityUtils.toString(response.getEntity());
                System.out.println(CommonUtil.jsonFormatter(responseText));
            } else {
                System.err.println("错误的结果：" + status);
                System.err.println(responseText);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
            return responseText;
        }
    }

    public static String getSign(Map<String, String> parameters, String key) {

        StringBuffer stringBuffer = new StringBuffer();
        List<Map.Entry<String, String>> entryListSort = asciiSort(parameters);
        for (Map.Entry<String, String> entry : entryListSort) {
            stringBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        stringBuffer.append("key").append("=").append(key);
        return MD5Util.MD5Encode(stringBuffer.toString(), "UTF-8").toUpperCase();
    }

    public static <V> List<Map.Entry<String, V>> asciiSort(Map<String, V> parameters) {
        List<Map.Entry<String, V>> entryListSort = new ArrayList<>(parameters.entrySet());
        Collections.sort(entryListSort,
                new Comparator<Map.Entry<String, V>>() {
                    @Override
                    public int compare(Map.Entry<String, V> o1, Map.Entry<String, V> o2) {
                        return (o1.getKey()).toString().compareTo(o2.getKey());
                    }
                });
        return entryListSort;
    }

    /**
     * 获取客服列表
     */
    public static String getCSList(String accessToken, String param) {
        String url = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=" + accessToken;
        return handle(url, param);
    }

    /**
     * 发送客服消息
     */
    public static String sendCSMsg(String accessToken, String wxOpenId, String content, String account) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;

        JSONObject param = new JSONObject();
        {
            param.put("msgtype", "text");
            param.put("touser", wxOpenId);

            //内容
            JSONObject text = new JSONObject();
            text.put("content", content);
            param.put("text", text);

            //客服
            JSONObject customService = new JSONObject();
            customService.put("kf_account", account);
            param.put("customservice", customService);
        }

//        {
//            "touser":"OPENID",
//                "msgtype":"text",
//                "text": { "content":"Hello World"},
//            "customservice": { "kf_account": "test1@kftest"}
//        }

        return handle(url, JSON.toJSONString(param));
    }

    /**
     * 添加客服帐号
     *
     * @param accessToken 添加客服帐号
     */
    public static String addCS(String accessToken, String account, String nickname, String password) {
        String url = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=" + accessToken;

        JSONObject param = new JSONObject();
        {

            param.put("kf_account", account);
            param.put("nickname", nickname);
            param.put("password", password);
        }

//        {
//            "kf_account" : "test1@test",
//                "nickname" : "客服1",
//                "password" : "pswmd5",
//        }

        return handle(url, JSON.toJSONString(param));
    }

    private static String handle(String url, String param) {
        CloseableHttpResponse response = null;
        String responseText = "";
        try {
            // 请求
            HttpPost post = new HttpPost(url);
//            post.addHeader("User-Agent", "IOS");
            post.setEntity(new StringEntity(param, "UTF-8"));
            response = HttpUtil.getHttpClient().execute(post);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                responseText = EntityUtils.toString(response.getEntity());
            } else {
                System.err.println("错误的结果：" + status);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
            return responseText;
        }
    }


}
