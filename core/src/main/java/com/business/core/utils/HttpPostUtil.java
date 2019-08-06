package com.business.core.utils;

import com.business.core.constants.ApplicationConfig;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Administrator on 2015/4/28.
 */
public class HttpPostUtil {
    URL url;
    HttpURLConnection conn;
    String boundary = "--------" + UUID.randomUUID().toString().replace("-", "");
    Map<String, String> textParams = new HashMap<>();
    Map<String, File> fileParams = new HashMap<>();
    Map<String, byte[]> fileParamsByte = new HashMap<>();
    DataOutputStream ds;

    public HttpURLConnection getConn() {
        return conn;
    }

    private static final int BYTE_SIZE = 1024 * 5;

    public HttpPostUtil(String url) throws Exception {
        this.url = new URL(url);
    }
    //重新设置要请求的服务器地址，即上传文件的地址。
    public void setUrl(String url) throws Exception {
        this.url = new URL(url);
    }
    //增加一个普通字符串数据到form表单数据中
    public void addTextParameter(String name, String value) {
        textParams.put(name, value);
    }
    //增加一个文件到form表单数据中
    public void addFileParameter(String name, File value) {
        fileParams.put(name, value);
    }
    //增加一个文件到form表单数据中
    public void addFileParameter(String name, byte[] bytes ) {
        fileParamsByte.put(name, bytes);
    }
    // 清空所有已添加的form表单数据
    public void clearAllParameters() {
        textParams.clear();
        fileParams.clear();
    }
    // 发送数据到服务器，返回一个字节包含服务器的返回结果的数组
    public byte[] send() throws Exception {
        initConnection();
        try {
            conn.connect();
        } catch (SocketTimeoutException e) {
            // something
            throw new RuntimeException();
        }
        ds = new DataOutputStream(conn.getOutputStream());
        writeFileParams();
        writeFileParamsByte();
        writeStringParams();
        paramsEnd();
        InputStream in = conn.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[BYTE_SIZE];
        while ((in.read(bytes)) != -1) {
            out.write(bytes);
        }
        conn.disconnect();
        return out.toByteArray();
    }
    //文件上传的connection的一些必须设置
    private void initConnection() throws Exception {
        conn = (HttpURLConnection) this.url.openConnection();
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setConnectTimeout(10000); //连接超时为10秒
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
    }
    //普通字符串数据
    private void writeStringParams() throws Exception {
        Set<String> keySet = textParams.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
            String name = it.next();
            String value = textParams.get(name);
            ds.writeBytes("--" + boundary + "\r\n");
            ds.writeBytes("Content-Disposition: form-data; name=\"" + name
                    + "\"\r\n");
            ds.writeBytes("\r\n");
            ds.writeBytes(encode(value) + "\r\n");
        }
    }
    //文件数据
    private void writeFileParams() throws Exception {
        Set<String> keySet = fileParams.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
            String name = it.next();
            File value = fileParams.get(name);
            ds.writeBytes("--" + boundary + "\r\n");
            ds.writeBytes("Content-Disposition: form-data; name=\"" + name
                    + "\"; filename=\"" + encode(value.getName()) + "\"\r\n");
            ds.writeBytes("Content-Type: " + getContentType(value) + "\r\n");
            ds.writeBytes("\r\n");
            ds.write(getBytes(value));
            ds.writeBytes("\r\n");
        }
    }
    //文件数据
    private void writeFileParamsByte() throws Exception {
        Set<String> keySet = fileParamsByte.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
            String name = it.next();
            byte[] value = fileParamsByte.get(name);
            ds.writeBytes("--" + boundary + "\r\n");
            ds.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + name + "\"\r\n");
            ds.writeBytes("Content-Type: application/octet-stream\r\n");
            ds.writeBytes("\r\n");
            ds.write(value);
            ds.writeBytes("\r\n");
        }
    }
    //获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
    private String getContentType(File f) throws Exception {

//      return "application/octet-stream";  // 此行不再细分是否为图片，全部作为application/octet-stream 类型
        ImageInputStream imagein = ImageIO.createImageInputStream(f);
        if (imagein == null) {
            return "application/octet-stream";
        }
        Iterator<ImageReader> it = ImageIO.getImageReaders(imagein);
        if (!it.hasNext()) {
            imagein.close();
            return "application/octet-stream";
        }
        imagein.close();
        return "image/" + it.next().getFormatName().toLowerCase();//将FormatName返回的值转换成小写，默认为大写

    }
    //把文件转换成字节数组
    private byte[] getBytes(File f) throws Exception {
        FileInputStream in = new FileInputStream(f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = in.read(b)) != -1) {
            out.write(b, 0, n);
        }
        in.close();
        return out.toByteArray();
    }
    //添加结尾数据
    private void paramsEnd() throws Exception {
        ds.writeBytes("--" + boundary + "--" + "\r\n");
        ds.writeBytes("\r\n");
    }
    // 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码
    private String encode(String value) throws Exception{
        return URLEncoder.encode(value, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
//        String token = "31f5XC1EPSfbXw9f2rsT6N3m_PmY_fs45RBxOvWa32SGSJlHSUfu6oPb8xj0c3PtMowHMhUZg6AaYRDE6Hmv6Y6JB_9yjRHbBdefHX1DWQ8";
//        String openid = "oBmGhuPBytPMk-vNeEqvt5ahSZaY";
//
//        HttpPostUtil u = new HttpPostUtil("https://api.weixin.qq.com/hardware/bracelet/setstep");
////        u.addFileParameter("file", new File("E:\\CloudMusic\\M2M - Mirror Mirror.mp3"));
////        InputStream inputStream = new FileInputStream(new File("E:\\CloudMusic\\M2M - Mirror Mirror.mp3"));
////        u.addFileParameter("file", IOUtils.toByteArray(inputStream));
//        u.addTextParameter("openeid", openid);
//        u.addTextParameter("access_token", token);
//        u.addTextParameter("timestamp", String.valueOf(System.currentTimeMillis()));
//        u.addTextParameter("step", 99 + "");
//        byte[] b = u.send();
//        String result = new String(b, "GBK");
//        System.out.println(result);


//        String token = "XJWH9bbPqMd2oQqGPDw-8twCkA4jeAaUcuJetOxwChJRwXAcxtyIBUtWxjs6aclJ6bBuYE6Vchillqn3ge4QGlnVjFouAUrD8WGVWgSgXHU";
//        String openid = "oBmGhuPBytPMk-vNeEqvt5ahSZaY";
//        String param = String.format("https://api.weixin.qq.com/hardware/bracelet/setstep?access_token=%s&openeid=%s&timestamp=%s&step=%s", token, openid, System.currentTimeMillis(), 99);
//        System.out.println(param);
//        HttpGet httpGet = new HttpGet(param);
//        httpGet.setHeader(new BasicHeader("lan", "ch"));
//        CloseableHttpResponse response = HttpUtil.getHttpClient().execute(httpGet);
//        int statusCode = response.getStatusLine().getStatusCode();
//        if (statusCode == 200) {
//            System.out.println(EntityUtils.toString(response.getEntity(), "GBK"));
//        }
//        else {
//            throw new IOException("返回结果：" + statusCode);
//        }

//        CloseableHttpResponse response = null;
//        try {
//            HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/hardware/bracelet/setstep");
//            httpPost.addHeader("User-Agent", "IOS");
//            httpPost.setEntity(MultipartEntityBuilder.create()
//                    .addTextBody("openeid", "oBmGhuPBytPMk-vNeEqvt5ahSZaY")
//                    .addTextBody("access_token", "XJWH9bbPqMd2oQqGPDw-8twCkA4jeAaUcuJetOxwChJRwXAcxtyIBUtWxjs6aclJ6bBuYE6Vchillqn3ge4QGlnVjFouAUrD8WGVWgSgXHU")
//                    .addTextBody("timestamp", String.valueOf(System.currentTimeMillis()))
//                    .addTextBody("step", String.valueOf(99)).build());
//
//            response = HttpUtil.getHttpClient().execute(httpPost);
//            int status = response.getStatusLine().getStatusCode();
//            String responseText = EntityUtils.toString(response.getEntity());
//            if (status == HttpStatus.SC_OK) {
//                System.out.println(CommonUtil.jsonFormatter(responseText));
//            } else {
//                System.err.println("错误的结果：" + status);
//                System.err.println(responseText);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HttpUtil.closeQuietly(response);
//        }


//        CloseableHttpResponse response = null;
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.addTextBody("openid", "oBmGhuPBytPMk-vNeEqvt5ahSZaY");
//        builder.addTextBody("device_id", "qwertyuiop");
//        builder.addTextBody("ticket", "1241231231212");
//        try {
//            String token = "maMlwt7ZcMdW3ak8Rzipcbo_Gl3bXSPIeExbQSL2DbIyQq0nLuPnIab7KJx18Nz_bOzNjmRou8OEN955T77hodTWX9Hoxl5CoxbwozwQ-p4";
//            // 请求
//            HttpPost post = new HttpPost("https://api.weixin.qq.com/device/bind?access_token=" + token);
//            post.addHeader("User-Agent", "IOS");
//            post.setEntity(builder.build());
//            response = HttpUtil.getHttpClient().execute(post);
//            int status = response.getStatusLine().getStatusCode();
//            String responseText = EntityUtils.toString(response.getEntity());
//            if (status == HttpStatus.SC_OK) {
//                System.out.println(responseText);
////                System.out.println(CommonUtil.jsonFormatter(responseText));
//            } else {
//                System.err.println("错误的结果：" + status);
//                System.err.println(responseText);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            HttpUtil.closeQuietly(response);
//        }


        CloseableHttpResponse response = null;
        String responseText = "";
        try {
            // 请求
            HttpPost post = new HttpPost("http://localhost:8068/test.json");
            post.addHeader("User-Agent", "IOS");
            post.setEntity(MultipartEntityBuilder.create().build());
            response = HttpUtil.getHttpClient().execute(post);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                responseText = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.err.println(responseText);
            } else {
                System.err.println("错误的结果：" + status);
                System.err.println(responseText);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }
}
