package com.business.core.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.core.entity.SysParam;
import com.business.core.utils.HttpUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 微博中心Client
 * User: yunai
 * Date: 13-12-3
 * Time: 上午11:31
 */
public class WBCenterClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WBCenterClient.class);

    private static final String SCHEME = "https";
    private static final String URI = "api.weibo.com/";

    private static final String PARAM_COMMON_SOURCE = "source";
    private static final String PARAM_COMMON_ACCESS_TOKEN = "access_token";
    private static final String PARAM_COMMON_UID = "uid";

    private static final String PATH_USERS_SHOW = "2/users/show.json";

    private static final String PATH_STATUSES_UPDATE = "2/statuses/update.json";
    private static final String PATH_STATUSES_UPDATE_PARAM_STATUS_KEY = "status";

    private static final String PATH_GET_TOKEN_INFO = "oauth2/get_token_info";

    private static final String PATH_STATUSES_UPLOAD_URL_TEXT = "2/statuses/upload_url_text.json";
    private static final String PATH_STATUSES_UPLOAD_URL_TEXT_PARAM_STATUS_KEY = "status";
    private static final String PATH_STATUSES_UPLOAD_URL_TEXT_PARAM_URL = "url";

    private static final String PATH_STATUSES_UPLOAD = "2/statuses/upload.json";
    private static final String PATH_STATUSES_UPLOAD_PARAM_STATUS_KEY = "status";
    private static final String PATH_STATUSES_UPLOAD_PARAM_PIC_KEY = "pic";

    /**
     * 根据用户ID获取用户信息
     *
     * @param channel 客户端渠道类型
     * @param accessToken 采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
     * @param openid         需要查询的用户ID。
     * @return 返回信息
     * @see <a href="http://open.weibo.com/wiki/2/users/show">http://open.weibo.com/wiki/2/users/show</a>
     */
    public static JSONObject userInfo(String channel, String accessToken, String openid) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(URI)
                    .setPath(PATH_USERS_SHOW)
                    .addParameter(PARAM_COMMON_SOURCE, channel)
                    .addParameter(PARAM_COMMON_ACCESS_TOKEN, accessToken)
                    .addParameter(PARAM_COMMON_UID, openid);
            HttpGet get = new HttpGet(builder.build());
            response = HttpUtil.getHttpClient().execute(get);
            String responseText = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(responseText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    /**
     * 发送新浪微博。
     * 当调用接口发生异常时，仅仅记录该异常，不会抛出。
     *
     * @param channel 客户端渠道类型
     * @param accessToken 采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
     * @param status      博客内容
     */
    public static void statusesUpdate(Integer channel, String accessToken, String status) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(URI)
                    .setPath(PATH_STATUSES_UPDATE);
            HttpPost post = new HttpPost(builder.build());
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair(PARAM_COMMON_SOURCE, obtainAppKey(channel)));
            nvps.add(new BasicNameValuePair(PARAM_COMMON_ACCESS_TOKEN, accessToken));
            nvps.add(new BasicNameValuePair(PATH_STATUSES_UPDATE_PARAM_STATUS_KEY, status));
            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            response = HttpUtil.getHttpClient().execute(post);
            EntityUtils.consumeQuietly(response.getEntity());
//            String responseText = EntityUtils.toString(response.getEntity());
//            System.out.println(responseText);
        } catch (Exception e) {
            LOGGER.error("[statusesUpdate][accessToken:[{}] status:[{}]发生异常:[{}]]", accessToken, status,
                    ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    /**
     * TODO 【优先级：低】权限不够
     * 发送带图片的新浪微博。
     * 当调用接口发生异常时，仅仅记录该异常，不会抛出。
     *
     * @param channel 客户端渠道类型
     * @param accessToken 采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
     * @param status      博客内容
     */
    public static void statusesUploadUrlText(Integer channel, String accessToken, String status, String url) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(URI)
                    .setPath(PATH_STATUSES_UPLOAD_URL_TEXT);
            HttpPost post = new HttpPost(builder.build());
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair(PARAM_COMMON_SOURCE, obtainAppKey(channel)));
            nvps.add(new BasicNameValuePair(PARAM_COMMON_ACCESS_TOKEN, accessToken));
            nvps.add(new BasicNameValuePair(PATH_STATUSES_UPLOAD_URL_TEXT_PARAM_STATUS_KEY, status));
            nvps.add(new BasicNameValuePair(PATH_STATUSES_UPLOAD_URL_TEXT_PARAM_URL, url));
            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            response = HttpUtil.getHttpClient().execute(post);
            EntityUtils.consumeQuietly(response.getEntity());
//            String responseText = EntityUtils.toString(response.getEntity());
//            System.out.println(responseText);
        } catch (Exception e) {
            LOGGER.error("[statusesUploadUrlText][accessToken:[{}] status:[{}] url:[{}]发生异常:[{}]]", accessToken,
                    status, url, ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    /**
     * 发送呆图片的腾讯微博。
     * 当调用接口发生异常时，仅仅记录异常，不会抛出
     *
     * @param channel 客户端渠道类型
     * @param accessToken 采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
     * @param status      博客内容
     * @param pic         图片二进制数组
     */
    public static void statusesUpload(Integer channel, String accessToken, String status, byte[] pic) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(URI)
                    .setPath(PATH_STATUSES_UPLOAD);
            HttpPost post = new HttpPost(builder.build());
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.addTextBody(PARAM_COMMON_SOURCE, obtainAppKey(channel))
                    .addTextBody(PARAM_COMMON_ACCESS_TOKEN, accessToken)
                    .addTextBody(PATH_STATUSES_UPLOAD_PARAM_STATUS_KEY, status, HttpUtil.TEXT_PLAIN_UTF_8)
                    .addBinaryBody(PATH_STATUSES_UPLOAD_PARAM_PIC_KEY, pic, ContentType.DEFAULT_BINARY, String.valueOf(System.currentTimeMillis()));
            post.setEntity(entityBuilder.build());
            response = HttpUtil.getHttpClient().execute(post);
//          String responseText = EntityUtils.toString(response.getEntity());
//          System.out.println(responseText);
            EntityUtils.consumeQuietly(response.getEntity());
        } catch (Exception e) {
            LOGGER.error("[statusesUpload][accessToken:[{}] status:[{}] 发生异常:[{}]]", accessToken, status,
                    ExceptionUtils.getStackTrace(e));
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    /**
     * @param channel 客户端渠道
     * @param accessToken 采用OAuth授权方式为必填参数，其他授权方式不需要此参数，OAuth授权后获得。
     * @return Token信息
     */
    public static JSONObject getTokenInfo(Integer channel, String accessToken) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(URI)
                    .setPath(PATH_GET_TOKEN_INFO);
            HttpPost post = new HttpPost(builder.build());
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair(PARAM_COMMON_SOURCE, obtainAppKey(channel)));
            nvps.add(new BasicNameValuePair(PARAM_COMMON_ACCESS_TOKEN, accessToken));
            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            response = HttpUtil.getHttpClient().execute(post);
            String responseText = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(responseText);
        } catch (Exception e) {
            LOGGER.error("[statusesUpdate][getTokenInfo:[{}] 发生异常:[{}]]", accessToken, ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    /**
     * @param channel 客户端渠道
     * @return 客户端渠道对应的应用KEY
     */
    private static String obtainAppKey(Integer channel) {
//        if (ComeFromInfo.CHANNEL_FUYOU.equals(channel)) {
//            return SysParam.INSTANCE.WEIBO_OAUTH_APP_FUYOU_KEY;
//        }
//        return SysParam.INSTANCE.WEIBO_OAUTH_APP_KEY;
        return "";
    }
    //2.00QBgKAC03t9ZIe886cdb2686NQrjC openid =1834810470
    //F79BFE34D60FCF5A0F4235BEF42BD84A  openid = 331058A9BD8580E629D7618086C35813
    public static void main (String[] args) {
        JSONObject jsonObject = userInfo("1415931559", "F79BFE34D60FCF5A0F4235BEF42BD84A", "331058A9BD8580E629D7618086C35813");
        String a = "";
    }

    /**
     *  获取access_token
     * @param code
     * @return
     */
    public static JSONObject getAccessToken(String code) {
        CloseableHttpResponse response = null;
        JSONObject jsonObject = null;
        try {
            HttpPost post = new HttpPost("https://api.weibo.com/oauth2/access_token");
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("client_id", SysParam.INSTANCE.WEB_LOGIN_WB_KEY));
            params.add(new BasicNameValuePair("client_secret", SysParam.INSTANCE.WEB_LOGIN_WB_SECRET));
            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
            params.add(new BasicNameValuePair("code", code));
            params.add(new BasicNameValuePair("redirect_uri", SysParam.INSTANCE.WEB_LOGIN_WB_REDIRECTURL));
            post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            response = HttpUtil.getHttpClient().execute(post);
            String responseText = EntityUtils.toString(response.getEntity());
            jsonObject = JSON.parseObject(responseText);
        }catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
        return jsonObject;
    }

}