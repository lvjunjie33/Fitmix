package com.business.core.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.core.entity.SysParam;
import com.business.core.entity.wx.WXUser;
import com.business.core.operations.schedulerValue.SchedulerValueCoreService;
import com.business.core.operations.wxUser.WXUserCoreDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.HttpUtil;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信认证Center--开放平台
 * Created by kobe.li on 2014/7/11.
 */
public class WXCenterClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WXCenterClient.class);
    /**
     * 结果码 - invalid openid(无效的OpenID)
     */
    public static final Integer RESULT_40003 = 40003;
    /**
     * 结果码 - invalid credential(无效的凭证)
     */
    public static final Integer RESULT_40001 = 40001;

    /**
     * HTTP重试次数
     */
    private static final int HTTP_RETRY_COUNT = 3;
    /**
     * 请求协议头
     */
    private static final String SCHEME = "https";
    /**
     * host
     */
    private static final String HOST = "api.weixin.qq.com/";
    /**
     * 获取用户基本信息
     * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
     */
    private static final String PATH_USER_INFO = "sns/userinfo";
    /**
     * 参数 - access
     */
    private static final String PARAMS_ACCESS_TOKEN = "access_token";
    /**
     * 参数 - 微信openid
     */
    private static final String PARAMS_OPENID = "openid";

    /**
     * 获得微信用户信息
     *
     * @param openid 微信openid
     * @param token 微信用户token
     * @return 用户信息
     */
    public static JSONObject userInfo(String token, String openid) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(HOST)
                    .setPath(PATH_USER_INFO)
                    .addParameter("refresh_token", "wx523eacdfe8922b80")
                    .addParameter(PARAMS_ACCESS_TOKEN, token)
                    .addParameter(PARAMS_OPENID, openid);
            HttpGet post = new HttpGet(builder.build());
            response = HttpUtil.getHttpClient().execute(post);
            String responseText = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            return JSON.parseObject(responseText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    /**
     * 获得微信用户信息 ROC
     *
     * @param openid 微信openid
     * @param token 微信用户token
     * @return 用户信息
     */
    public static JSONObject userInfoROC(String token, String openid) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(HOST)
                    .setPath(PATH_USER_INFO)
                    .addParameter("refresh_token", "wx7f6b7b9226afcc9c")
                    .addParameter(PARAMS_ACCESS_TOKEN, token)
                    .addParameter(PARAMS_OPENID, openid);
            HttpGet post = new HttpGet(builder.build());
            response = HttpUtil.getHttpClient().execute(post);
            String responseText = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            return JSON.parseObject(responseText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    public static String parseMessage(HttpServletRequest request) {
        String responseText = "";
        try (InputStream inputStream = request.getInputStream()) {
            //读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            //noinspection unchecked
            List<Element> elementList = root.elements();

            Map<String, Element> elementMap = new HashMap<>();

            for (Element element : elementList) {
                System.out.println(element.getName() + "---" +  element.getStringValue());
                elementMap.put(element.getName(), element);
            }

            /// 分为 消息，和事件
            if (elementMap.containsKey("Event")) {
                /// 事件
                String event = elementMap.get("Event").getStringValue();
                StringBuffer str = new StringBuffer();
                StringBuffer resultStr = new StringBuffer();
                switch (event) {
                    /// Even 点击事件
                    case "CLICK":
                        String key = elementMap.get("EventKey").getStringValue();
                        switch (key) {
                            case "RANKING_LIST": /// 排行榜
                                str.append("<xml>");
                                str.append("<ToUserName><![CDATA[" + elementMap.get("FromUserName").getStringValue() + "]]></ToUserName>");
                                str.append("<FromUserName><![CDATA[" + elementMap.get("ToUserName").getStringValue() + "]]></FromUserName>");
                                str.append("<CreateTime>" + elementMap.get("CreateTime").getStringValue() + "</CreateTime>");
                                str.append("<MsgType><![CDATA[hardware]]></MsgType>");
                                str.append("<HardWare>");
                                str.append("<MessageView><![CDATA[myrank]]></MessageView>");
                                str.append("<MessageAction><![CDATA[ranklist]]></MessageAction>");
                                str.append("</HardWare>");
                                str.append("<FuncFlag>0</FuncFlag>");
                                str.append("</xml>");
                                responseText = str.toString();
                                break;
                            case "KEY_QUESTION":

                                resultStr.append("如果您在使用乐享动APP时出现以下问题，请回复【问题序号】：\n").
                                        append("1、\t没有步数/手机如何设置；\n").
                                        append("2、\t安卓系统感觉步数少了/锁屏不记步；\n").
                                        append("3、\t跑完，微信运动数据没更新；\n").
                                        append("（建议：在回复数字“3”之前，请先回复数字“1”，进行适当的手机设置。）");
//                                        append("4、\t更新安卓6.0之后遇到的乐享动使用问题；\n");

                                str.append("<xml>");
                                str.append("<ToUserName><![CDATA[" + elementMap.get("FromUserName").getStringValue() + "]]></ToUserName>");
                                str.append("<FromUserName><![CDATA[" + elementMap.get("ToUserName").getStringValue() + "]]></FromUserName>");
                                str.append("<CreateTime>" + elementMap.get("CreateTime").getStringValue() + "</CreateTime>");
                                str.append("<MsgType><![CDATA[text]]></MsgType>");
                                str.append("<Content><![CDATA[" + resultStr.toString() + "]]></Content>");
                                str.append("</xml>");
                                responseText = str.toString();
                                break;

                            case "KEY_MAX":
                                resultStr.append("你对《疯运事》的哪期嘉宾感兴趣？快来看看他们的资料吧，好多运动型的帅哥美女哦~回复节目编号，格式：FYS+节目期数，如FYS001。");

                                str.append("<xml>");
                                str.append("<ToUserName><![CDATA[" + elementMap.get("FromUserName").getStringValue() + "]]></ToUserName>");
                                str.append("<FromUserName><![CDATA[" + elementMap.get("ToUserName").getStringValue() + "]]></FromUserName>");
                                str.append("<CreateTime>" + elementMap.get("CreateTime").getStringValue() + "</CreateTime>");
                                str.append("<MsgType><![CDATA[text]]></MsgType>");
                                str.append("<Content><![CDATA[" + resultStr.toString() + "]]></Content>");
                                str.append("</xml>");
                                responseText = str.toString();
                                break;
                        }
                        break;
                    /// Even 关注事件
                    case "subscribe":
                        String contentSubscribe = SysParam.INSTANCE.WX_SUBSCRIBE_REPLY_CONTENT;
                        str.append("<xml>");
                        str.append("<ToUserName><![CDATA[" + elementMap.get("FromUserName").getStringValue() + "]]></ToUserName>");
                        str.append("<FromUserName><![CDATA[" + elementMap.get("ToUserName").getStringValue() + "]]></FromUserName>");
                        str.append("<CreateTime>" + elementMap.get("CreateTime").getStringValue() + "</CreateTime>");
                        str.append("<MsgType><![CDATA[text]]></MsgType>");
                        str.append("<Content><![CDATA[" + contentSubscribe + "]]></Content>");
                        str.append("</xml>");
                        responseText = str.toString();

                        // 检查 是否存在微信用户，不存在则拉取用户数据，并保存数据库(用于公众平台关注用户 和 app 用户关联)
                        WXUserCoreDao wxUserCoreDao = BeanManager.getBean(WXUserCoreDao.class);
                        if (wxUserCoreDao.findWXUserByOpenid(elementMap.get("FromUserName").getStringValue(), "openid") == null) {
                            WXUser wxUser = getWXUserInfo(elementMap.get("FromUserName").getStringValue());
                            wxUserCoreDao.insertWXUser(wxUser);
                        }
                        break;
                }
            }
            else {
                /// 消息
                String msgType = elementMap.get("MsgType").getStringValue();

                StringBuffer str = new StringBuffer();
                switch (msgType) {
                    case "text": /* img 等。全部回复此文字 */
                        Object object = JSON.parseObject(SysParam.INSTANCE.WX_REPLY).get(elementMap.get("Content").getStringValue());

                        if (object == null) {
                            break;
                        }


                        JSONObject jsonObject = JSON.parseObject(SysParam.INSTANCE.WX_REPLY);
                        String resultContent = jsonObject.get(elementMap.get("Content").getStringValue()).toString();


                        if (resultContent.indexOf("http") == -1) {
                            str.append("<xml>");
                            str.append("<ToUserName><![CDATA[" + elementMap.get("FromUserName").getStringValue() + "]]></ToUserName>");
                            str.append("<FromUserName><![CDATA[" + elementMap.get("ToUserName").getStringValue() + "]]></FromUserName>");
                            str.append("<CreateTime>" + elementMap.get("CreateTime").getStringValue() + "</CreateTime>");
                            str.append("<MsgType><![CDATA[text]]></MsgType>");
                            str.append("<Content><![CDATA[" + resultContent + "]]></Content>");
                            str.append("</xml>");
                            responseText = str.toString();
                        }
                        else {
                            responseText = buildNews(elementMap.get("FromUserName").getStringValue(),
                                    elementMap.get("ToUserName").getStringValue(),
                                    elementMap.get("CreateTime").getStringValue(),
                                    jsonObject.get(elementMap.get("Content").getStringValue() + "_title").toString(),
                                    "",
                                    jsonObject.get(elementMap.get("Content").getStringValue() + "_img").toString(),
                                    resultContent);
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        System.out.println(responseText);
        return responseText;
    }

    /**
     * 拉取微信用户信息
     * @param openid openid
     * @return 微信用户详细
     */
    public static WXUser getWXUserInfo(String openid) {
        SchedulerValueCoreService schedulerValueCoreService = BeanManager.getBean(SchedulerValueCoreService.class);
        String wxUserInfoUrl = String.format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN", schedulerValueCoreService.getCurrentAccessToken(), openid);
        String resultWXUserText = HttpUtil.get(wxUserInfoUrl);
        return JSON.parseObject(resultWXUserText, WXUser.class);
    }


    public static String buildNews(String fromUserName, String toUserName, String createTime, String title, String description, String picUrl, String url) {
        StringBuffer str = new StringBuffer();

        str.append("<xml>");
            str.append("<ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>");
            str.append("<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>");
            str.append("<CreateTime>" + createTime + "</CreateTime>");
            str.append("<MsgType><![CDATA[news]]></MsgType>");
            str.append("<ArticleCount>1</ArticleCount>");

            str.append("<Articles>");
                str.append("<item>");
                    str.append("<Title><![CDATA[" + title + "]]></Title>");
                    str.append("<Description><![CDATA[" + description + "]]></Description>");
                    str.append("<PicUrl><![CDATA[" + picUrl + "]]></PicUrl>");
                    str.append("<Url><![CDATA[" + url + "]]></Url>");
                str.append("</item>");

//                str.append("<item>");
//                    str.append("<Title><![CDATA[title]]></Title>");
//                    str.append("<Description><![CDATA[description]]></Description>");
//                    str.append("<PicUrl><![CDATA[picurl]]></PicUrl>");
//                    str.append("<Url><![CDATA[url]]></Url>");
//                str.append("</item>");
            str.append("</Articles>");
        str.append("</xml>");

        return str.toString();
    }


    /**
     *  获取access_token
     * @param code  authorization_code
     * @return jsonObject{
     * access_token,
     *
     *
     * }
     */
    public static JSONObject getAccessToken(String code) {
        CloseableHttpResponse response = null;
        JSONObject jsonObject = null;
        try {
            HttpPost post = new HttpPost("https://api.weixin.qq.com/sns/oauth2/access_token");
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("appid", SysParam.INSTANCE.WEB_LOGIN_WX_KEY));
            params.add(new BasicNameValuePair("secret", SysParam.INSTANCE.WEB_LOGIN_WX_SECRET));
            params.add(new BasicNameValuePair("code", code));
            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
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
