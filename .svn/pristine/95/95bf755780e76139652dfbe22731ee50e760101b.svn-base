package com.business.scheduler.jobs.wx;

import com.business.core.entity.SysParam;
import com.business.core.entity.wx.WXGetUserInfoKey;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.HttpConnectionManager;
import com.business.core.utils.HttpPostUtil;
import com.business.core.utils.HttpUtil;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class WXUserGetInfoKeyJob extends BaseMongoDaoSupport {

//    private final static String APPID = "wx7adcfc1457f072b4";
//    private final static String APPSECRET = "3022239db350cccc777bebc315a2dc51";
    private static String url;


    public static void setUrl(String url) {
        WXUserGetInfoKeyJob.url = url;
    }


    public void execute () throws IOException {

        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7adcfc1457f072b4&secret=3022239db350cccc777bebc315a2dc51");
        CloseableHttpResponse response = HttpUtil.getHttpClient().execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        String returnStr = EntityUtils.toString(response.getEntity());
        if (statusCode == HttpStatus.SC_OK) {
            String jsapi_ticket = returnStr.split(",")[0].split(":")[1].replace("\"", "");
            // 注意 URL 一定要动态获取，不能 hardcode
            WXUserGetInfoKeyJob.setUrl(SysParam.INSTANCE.APP_USER_LIVE_SHARE_DNS);
            String url = WXUserGetInfoKeyJob.url;
            Map<String, String> ret = sign(jsapi_ticket, url);

            if (CollectionUtils.isEmpty(getRoutingMongoOps().findAll(WXGetUserInfoKey.class))) {
                // 更新数据
                WXGetUserInfoKey wxUserGetInfoKey = new WXGetUserInfoKey();
                wxUserGetInfoKey.setTimestamp(ret.get("timestamp"));
                wxUserGetInfoKey.setNonceStr(ret.get("nonceStr"));
                wxUserGetInfoKey.setSignature(ret.get("signature"));
                wxUserGetInfoKey.setUpdateTime(new Date());
                getRoutingMongoOps().insert(wxUserGetInfoKey);
            }
            else {
                // 更新数据
                getRoutingMongoOps().updateFirst(
                        Query.query(new Criteria()),
                        Update.update("timestamp", ret.get("timestamp")).set("nonceStr", ret.get("nonceStr")).set("signature", ret.get("signature")).set("updateTime", new Date()),
                        WXGetUserInfoKey.class);
            }
        }
        else {
            System.out.println("error code:" + statusCode);
        }
    }

    public static Map<String, String> sign(String jsapi_ticket, String url) {

        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        //System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            //Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
               System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
