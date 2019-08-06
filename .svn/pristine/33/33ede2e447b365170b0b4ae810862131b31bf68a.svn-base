package com.business.core.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by sin on 2015/9/7.
 */
public class MiGuUtil {

    private static Logger logger = LoggerFactory.getLogger(MiGuUtil.class);

    /**
     * 咪咕请求地址（前缀）
     */
    public static final String MIGU_URL_PREFIX = "http://218.200.230.142:85/opServer/1.0";

    public static String send(String url, String xmlParam, String netMode, String appId, String imsi) {
        String result = null;
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(MIGU_URL_PREFIX + url);
        try {
            String buildXml = buildRequestXml(xmlParam);

            HttpMethodParams params = new HttpMethodParams();
            params.setSoTimeout(3000);
            params.setContentCharset("UTF-8");
            params.setParameter(HttpMethodParams.SO_TIMEOUT, 3000); // 超时时间
            params.setParameter(HttpMethodParams.RETRY_HANDLER, 2); // 重试 2次
            params.setParameter("Content-length", String.valueOf(buildXml.getBytes().length));
            params.setParameter("Accept", "*/*");
            params.setParameter("Content-Type", "*/*");
            params.setParameter("Connection", "Keep-Alive");
            params.setParameter("Charset", "UTF-8");
            params.setParameter("Token", "");
            params.setParameter("excode", "");

            postMethod.setParams(params);

            setIMSIAuthorization(postMethod, netMode, appId, imsi); // 设置 IMSI 请求头

            postMethod.setRequestEntity(new StringRequestEntity(buildXml, "text/xlm", "utf-8"));
            Integer statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                result = postMethod.getResponseBodyAsString();
            }

            logger.info("MiGu send status code:{}", statusCode);
            logger.info("MiGu send result:{}", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection(); // 释放链接资源
            return result;
        }
    }

    public static String buildRequestXml(String parameter) {
        return "<?xml version='1.0' encoding='UTF-8'?><request>" + parameter + "</request>";
    }

    private static void setIMSIAuthorization(HttpMethod httpMethod, String netMode, String appId, String imsi) {
        httpMethod.addRequestHeader(
        "Authorization",
        "OEPAUTH realm=\"OEP\",IMSI=\"" + imsi + "\",appID=\""
                + appId + "\",pubKey=\""
                + "15C5C6F0C91FDA3053CF7608C85317B5" + "\",netMode=\""
                + netMode + "\",packageName=\""
                + "com.fitmix.sdk" + "\",version=\""
                + "C1.0" + "\",excode=\""
                + null + "\"");
    }











//    private void setTokenAuthorization(HttpURLConnection httpConn) {
//        httpConn.addRequestProperty(
//                "Authorization",
//                "OEPAUTH realm=\"OEP\",Token=\"" + "" + "\",appID=\""
//                        + "007942440150516624" + "\",pubKey=\""
//                        + "15C5C6F0C91FDA3053CF7608C85317B5" + "\",netMode=\""
//                        + "WIFI" + "\",packageName=\""
//                        + "com.fitmix.sdk" + "\",version=\""
//                        + "C1.0" + "\",excode=\""
//                        + null + "\"");
//    }
//
//    private void setIMSIAuthorization(HttpURLConnection httpConn) {
//        httpConn.addRequestProperty(
//                "Authorization",
//                "OEPAUTH realm=\"OEP\",IMSI=\"" + "3366433DA2FEB130F2514C36732A9705" + "\",appID=\""
//                        + "007942440150516624" + "\",pubKey=\""
//                        + "15C5C6F0C91FDA3053CF7608C85317B5" + "\",netMode=\""
//                        + "WIFI" + "\",packageName=\""
//                        + "com.fitmix.sdk" + "\",version=\""
//                        + "C1.0" + "\",excode=\""
//                        + null + "\"");
//    }
//
//
//    private void setAuthorization(HttpURLConnection httpConn) {
//        httpConn.addRequestProperty("Authorization",
//                "OEPAUTH realm=\"OEP\",IMSI=\"" + "\",appID=\"" + "007942440150516624"
//                        + "\",pubKey=\"" + "15C5C6F0C91FDA3053CF7608C85317B5" + "\",netMode=\""
//                        + "WIFI" + "\",packageName=\""
//                        + "com.fitmix.sdk" + "\",version=\""
//                        + "C1.0" + "\",excode=\""
//                        + null + "\"");
//    }
}
