package com.business.core.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.core.utils.HttpUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * QQ中心Client
 * Created by yunai on 13-11-28.
 */
public class QQCenterClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(QQCenterClient.class);

    /**
     * 结果码 - 成功
     */
    public static final Integer RET_0000 = 0;
    /**
     * 结果码 - 未登录
     */
    public static final Integer RET_1002 = 1002;

    private static final String SCHEME = "https";
    private static final String URI = "graph.qq.com/";

    private static final String PARAM_COMMON_OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    private static final String PARAM_COMMON_ACCESS_TOKEN_KEY = "access_token";
    private static final String PARAM_COMMON_OPENID_KEY = "openid";
    private static final String PARAM_COMMON_FORMAT_KEY = "format";
    private static final String PARAM_COMMON_FORMAT_VALUE_JSON = "json";

    private static final String PATH_GET_USER_INFO = "user/get_user_info";

    private static final String PATH_BLOG_ADD_ONE_BLOG = "blog/add_one_blog";
    private static final String PATH_BLOG_ADD_ONE_BLOG_PARAM_TITLE_KEY = "title";
    private static final String PATH_BLOG_ADD_ONE_BLOG_PARAM_CONTENT_KEY = "content";

    private static final String PATH_BLOG_ADD_T = "t/add_t";
    private static final String PATH_BLOG_ADD_T_PARAM_CONTENT = "content";

    private static final String PATH_BLOG_ADD_PIC_T = "t/add_pic_t";
    private static final String PATH_BLOG_ADD_PIC_T_PARAM_CONTENT = "content";
    public static final String PATH_BLOG_ADD_PIC_T_PARAM_PIC = "pic";

    /**
     * HTTP重试次数
     */
    private static final int HTTP_RETRY_COUNT = 3;

    /**
     * 获得QQ用户信息
     *
     * @param channel 客户端渠道类型
     * @param accessToken token
     * @param openid      openid
     * @return 返回信息
     */
    public static JSONObject getUserInfo(String channel, String accessToken, String openid) {
        for (int i = 0; i < HTTP_RETRY_COUNT; i++) {
            CloseableHttpResponse response = null;
            try {
                URIBuilder builder = new URIBuilder()
                        .setScheme(SCHEME)
                        .setHost(URI)
                        .setPath(PATH_GET_USER_INFO)
                        .addParameter(PARAM_COMMON_OAUTH_CONSUMER_KEY, channel)//chan
                        .addParameter(PARAM_COMMON_ACCESS_TOKEN_KEY, accessToken) //token
                        .addParameter(PARAM_COMMON_OPENID_KEY, openid)
                        .addParameter(PARAM_COMMON_FORMAT_KEY, PARAM_COMMON_FORMAT_VALUE_JSON);
                HttpGet get = new HttpGet(builder.build());
                response = HttpUtil.getHttpClient().execute(get);
                String responseText = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = JSON.parseObject(responseText);
                if (jsonObject.get("ret").equals(0)) {
                    return jsonObject;
                }
            } catch (Exception e) {
                LOGGER.error("[getUserInfo] exception({})]", ExceptionUtils.getStackTrace(e));
            } finally {
                HttpUtil.closeQuietly(response);
            }
        }
        throw new RuntimeException(String.format("[getUserInfo] channel(%s) accessToken(%s) openid(%s)", channel, accessToken, openid));
    }

    /**
     * TODO 【优先级：低】权限不够
     * 发送日志到QQ空间
     *
     * @param channel 客户端渠道类型
     * @param accessToken token
     * @param openid      openid
     * @param title       标题
     * @param content     内容
     * @return 发送结果
     */
    public static JSONObject addOneBlog(Integer channel, String accessToken, String openid, String title, String content) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(URI)
                    .setPath(PATH_BLOG_ADD_ONE_BLOG);
            HttpPost post = new HttpPost(builder.build());
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.addTextBody(PARAM_COMMON_OAUTH_CONSUMER_KEY, obtainConsumerKey(channel))
                    .addTextBody(PARAM_COMMON_ACCESS_TOKEN_KEY, accessToken)
                    .addTextBody(PARAM_COMMON_OPENID_KEY, openid)
                    .addTextBody(PATH_BLOG_ADD_ONE_BLOG_PARAM_TITLE_KEY, title)
                    .addTextBody(PATH_BLOG_ADD_ONE_BLOG_PARAM_CONTENT_KEY, content);
            post.setEntity(entityBuilder.build());
            response = HttpUtil.getHttpClient().execute(post);
            String responseText = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(responseText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    /**
     * 发送腾讯微博。
     * 当调用接口发生异常时，仅仅记录该异常，不会抛出。
     *
     * @param channel 客户端渠道类型
     * @param accessToken token
     * @param openid      openid
     * @param content     内容
     */
    public static void addT(Integer channel, String accessToken, String openid, String content) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(URI)
                    .setPath(PATH_BLOG_ADD_T);
            HttpPost post = new HttpPost(builder.build());
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.addTextBody(PARAM_COMMON_OAUTH_CONSUMER_KEY, obtainConsumerKey(channel))
                    .addTextBody(PARAM_COMMON_ACCESS_TOKEN_KEY, accessToken)
                    .addTextBody(PARAM_COMMON_OPENID_KEY, openid)
                    .addTextBody(PATH_BLOG_ADD_PIC_T_PARAM_CONTENT, content, HttpUtil.TEXT_PLAIN_UTF_8);
            post.setEntity(entityBuilder.build());
            response = HttpUtil.getHttpClient().execute(post);
            EntityUtils.consumeQuietly(response.getEntity());
//            String responseText = EntityUtils.toString(response.getEntity());
//            System.out.println(responseText);
        } catch (Exception e) {
            LOGGER.error("[addT][accessToken:[{}] openid:[{}] content:[{}]发生异常:[{}]]", accessToken, openid,
                    content, ExceptionUtils.getStackTrace(e));
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    /**
     * 发送呆图片的腾讯微博。
     * 当调用接口发生异常时，仅仅记录异常，不会抛出
     *
     * @param channel     客户端渠道
     * @param accessToken token
     * @param openid openid
     * @param content 内容
     * @param pic 图片二进制数组
     */
    public static void addPicT(Integer channel, String accessToken, String openid, String content, byte[] pic) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(URI)
                    .setPath(PATH_BLOG_ADD_PIC_T);
            HttpPost post = new HttpPost(builder.build());
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.addTextBody(PARAM_COMMON_OAUTH_CONSUMER_KEY, obtainConsumerKey(channel))
                    .addTextBody(PARAM_COMMON_ACCESS_TOKEN_KEY, accessToken)
                    .addTextBody(PARAM_COMMON_OPENID_KEY, openid)
                    .addBinaryBody(PATH_BLOG_ADD_PIC_T_PARAM_PIC, pic, ContentType.DEFAULT_BINARY, String.valueOf(System.currentTimeMillis()))
                    .addTextBody(PATH_BLOG_ADD_PIC_T_PARAM_CONTENT, content, HttpUtil.TEXT_PLAIN_UTF_8);
            post.setEntity(entityBuilder.build());
            response = HttpUtil.getHttpClient().execute(post);
//          String responseText = EntityUtils.toString(response.getEntity());
//          System.out.println(responseText);
            EntityUtils.consumeQuietly(response.getEntity());
        } catch (Exception e) {
            LOGGER.error("[addT][accessToken:[{}] openid:[{}] content:[{}]发生异常:[{}]]", accessToken, openid,
                    content, ExceptionUtils.getStackTrace(e));
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    /**
     * @param channel 客户端渠道
     * @return 客户端渠道对应的应用KEY
     */
    public static String obtainConsumerKey(Integer channel) {
//        if (ComeFromInfo.CHANNEL_FUYOU.equals(channel)) {
//            return SysParam.INSTANCE.QQ_OAUTH_CONSUMER_FUYOU_KEY;
//        }
//        return SysParam.INSTANCE.QQ_OAUTH_CONSUMER_KEY;
        return "";
    }

    public static void main (String[] args) {
//        JSONObject qqInfo = QQCenterClient.getUserInfo(context.getChannel(), token, openid);
        JSONObject qqInfo = getUserInfo("1104452331 ", "F79BFE34D60FCF5A0F4235BEF42BD84A", "331058A9BD8580E629D7618086C35813");
        String a = "a";
    }
}
