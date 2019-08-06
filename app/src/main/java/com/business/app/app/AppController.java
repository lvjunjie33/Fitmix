package com.business.app.app;

import com.business.app.base.support.BaseControllerSupport;
import com.business.app.shop.task.TaskInfoService;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.DicConstants;
import com.business.core.entity.Dictionary;
import com.business.core.entity.SysParam;
import com.business.core.entity.TaoBaoIp;
import com.business.core.entity.startPage.StartPage;
import com.business.core.entity.user.info.UserDevice;
import com.business.core.operations.advert.AdvertCoreService;
import com.business.core.operations.startPage.StartPageCoreService;
import com.business.core.operations.startVideo.StartVideoCoreService;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DicUtil;
import com.business.core.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sin on 2015-05-18.
 * TODO 该接口不提供api
 */
@ApiIgnore
@Controller
public class AppController extends BaseControllerSupport {

    @Autowired
    private AppService appService;
    @Autowired
    private StartPageCoreService startPageCoreService;
    @Autowired
    private AdvertCoreService advertCoreService;
    @Autowired
    private StartVideoCoreService startVideoCoreService;
    @Autowired
    private TaskInfoService taskInfoService;

    /**
     * app 初始化信息
     */
    @RequestMapping("init")
    public void init (HttpServletRequest request, Model model, String lan) throws CloneNotSupportedException {
        String product = HttpUtil.getParameter(request, "_product");
        if (UserDevice.PRODUCT_FITMIX.equals(product)) {
            fitMixInit(request, model, lan);
        } else if (UserDevice.PRODUCT_ROC.equals(product)) {
            //2018-10-08
//            rocInit(request, model, lan);
        }
    }

    /**
     * C罗 app 初始化接口
     */
    public void rocInit(HttpServletRequest request, Model model, String lan) throws CloneNotSupportedException {

        //TODO 考虑改为压缩包下发 to Sin
        // app 版本信息
        versionInfo(request, model,lan);

        /// 三方登录 有效 key
        model.addAttribute("APPLoginQQKey", SysParam.INSTANCE.APP_LOGIN_QQ_KEY);
        model.addAttribute("APPLoginWXKey", SysParam.INSTANCE.APP_LOGIN_WX_KEY);
        model.addAttribute("APPLoginWBKey", SysParam.INSTANCE.APP_LOGIN_WB_KEY);

        /// 字典信息， 并处理语音包
        List<Dictionary> dictionaries = new ArrayList<>();
        if (!CollectionUtils.isEmpty(DicUtil.allDictionary())) {
            List<Dictionary> dis = DicUtil.allDictionary(lan);
            for (Dictionary dic : dis) {
                // 去除渠道信息
                if (dic.getType().equals(DicConstants.DIC_TYPE_CHANNEL)) {
                    continue;
                }
                if (Constants.SWITCH_CLOSE.equals(dic.getAppDisplay())) {//后台开关控制是否下发该字典
                    continue;
                }
                Dictionary cloneDictionary = dic.clone();
                cloneDictionary.setNameEn(null);
                dictionaries.add(cloneDictionary);
            }
        }
        model.addAttribute("dic", dictionaries);
    }
    /**
     * app 初始化信息
     */
    public void fitMixInit(HttpServletRequest request, Model model, String lan) throws CloneNotSupportedException {

        if (Constants.SWITCH_OPEN.equals(SysParam.INSTANCE.SWITCH_APP_UPGRADE)) {//强制升级
            if (!checkSDKType(request)) {
                if(!checkVersion(request, SysParam.INSTANCE.NEWEST_APP_IOS_VERSION, SysParam.INSTANCE.NEWEST_APP_ANDROID_VERSION)) {
                    model.addAttribute("upgrade", Constants.SWITCH_OPEN);
                    model.addAttribute("content", SysParam.INSTANCE.UPGRADE_CONTENT);
                    if (!checkSDKType(request)) {
                        model.addAttribute("androidUpgradeUrl", SysParam.INSTANCE.APP_ANDROID_UPGRADE_URL);
                    }
                    return;
                }
            }
        }

        //TODO 考虑改为压缩包下发 to Sin
        // app 版本信息
        versionInfo(request, model,lan);

        /**
         * 开窗公告
         */
        model.addAttribute("announcements", appService.findAnnouncement());

        // 产品购买地址
        model.addAttribute("BOLTPayAddress", SysParam.INSTANCE.APP_GEEKERY_BOLT_PAY_ADDRESS);
        model.addAttribute("BAGPayAddress", SysParam.INSTANCE.APP_GEEKERY_BAO_PAY_ADDRESS);
        model.addAttribute("WATCHPayAddress", SysParam.INSTANCE.APP_GEEKERY_WATCH_PAY_ADDRESS);
        model.addAttribute("LIGHTPayAddress", SysParam.INSTANCE.APP_GEEKERY_LIGHT_PAY_ADDRESS);
        model.addAttribute("SHINEPayAddress", SysParam.INSTANCE.APP_GEEKERY_SHINE_PAY_ADDRESS);

        /// 三方登录 有效 key
        model.addAttribute("APPLoginQQKey", SysParam.INSTANCE.APP_LOGIN_QQ_KEY);
        model.addAttribute("APPLoginWXKey", SysParam.INSTANCE.APP_LOGIN_WX_KEY);
        model.addAttribute("APPLoginWBKey", SysParam.INSTANCE.APP_LOGIN_WB_KEY);

        //运动分享地址
        model.addAttribute("AppUserRunShareLink", SysParam.INSTANCE.APP_USER_RUN_SHARE_LINK);
        //话题（问题>答案） 分享地址
        model.addAttribute("AppThemeAnswerShareLink", SysParam.INSTANCE.APP_THEME_ANSWER_SHARE_LINK);
        //二维码图片地址
        model.addAttribute("AppDownloadQRCodeLink", SysParam.INSTANCE.APP_DOWNLOAD_QR_CODE_LINK);
        //公钥地址
        model.addAttribute("keyUrl1", FileCenterClient.buildUrl(SysParam.INSTANCE.PUBLIC_KEY_LINK1));
        model.addAttribute("keyUrl2", FileCenterClient.buildUrl(SysParam.INSTANCE.PUBLIC_KEY_LINK2));
        model.addAttribute("keyUrl3", FileCenterClient.buildUrl(SysParam.INSTANCE.PUBLIC_KEY_LINK3));

        /// 宣传图

        /*if (!StringUtils.isEmpty(SysParam.INSTANCE.APP_HOME_BACKGROUND_IMAGE)) {
            JSONArray jsonArray = JSONArray.parseArray(SysParam.INSTANCE.APP_HOME_BACKGROUND_IMAGE);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                if (NumberUtils.isNumber(jsonObject.get("version").toString()) && getVersion() == Integer.valueOf(jsonObject.get("version").toString())) {
                    model.addAttribute("homeBackgroundImage", FileCenterClient.buildUrl(jsonObject.get("imageUrl").toString()));
                }
            }
            // 如果没有设置过版本特有背景图，则推默认第一张
            if (!model.containsAttribute("homeBackgroundImage")) {
                model.addAttribute("homeBackgroundImage", FileCenterClient.buildUrl(((JSONObject)jsonArray.get(0)).get("imageUrl").toString()));
            }
        } else {
            JSONArray jsonArray = JSONArray.parseArray(SysParam.INSTANCE.APP_HOME_BACKGROUND_IMAGE);
            if (!CollectionUtils.isEmpty(jsonArray)) {
                model.addAttribute("homeBackgroundImage", FileCenterClient.buildUrl(((JSONObject)jsonArray.get(0)).get("imageUrl").toString()));
            }
        }*/

        /// 字典信息， 并处理语音包
        List<Dictionary> dictionaries = new ArrayList<>();
        if (!CollectionUtils.isEmpty(DicUtil.allDictionary())) {
            List<Dictionary> dis = DicUtil.allDictionary(lan);
            for (Dictionary dic : dis) {
                // 去除渠道信息
                if (dic.getType().equals(DicConstants.DIC_TYPE_CHANNEL)) {
                    continue;
                }
                if (Constants.SWITCH_CLOSE.equals(dic.getAppDisplay())) {//后台开关控制是否下发该字典
                    continue;
                }
                Dictionary cloneDictionary = dic.clone();
                cloneDictionary.setNameEn(null);
                dictionaries.add(cloneDictionary);
            }
        }

        {
            String version = HttpUtil.getParameter(request, "_v"); //用户版本信息
            String sdk = HttpUtil.getParameter(request, "_sdk");//sdk 设备类型

            if (version != null) {
                int v = Integer.parseInt(version);
                if (sdk.contains("iPhone") || sdk.contains("iOS1")) {//iPhone || iOS 两种系统型号
                    if (v > 9) {
                        /// 启动视频
                        model.addAttribute("startVideo", null);
                        /// 启动闪图
                        model.addAttribute("startPage", startPageCoreService.findStartPage());
                        /// 启动广告 列表
                        model.addAttribute("adverts", advertCoreService.findAdverts());
                    } else if (v == 9) {
                        /// 启动视频
                        model.addAttribute("startVideo", null);
                        StartPage startPage = new StartPage();
                        startPage.setBackgroundImg("");
                        /// 启动闪图
                        model.addAttribute("startPage", startPage);
                    }
                } else {//安卓
                    if (v > 42) {
                        /// 启动视频
                        model.addAttribute("startVideo", startVideoCoreService.findStartVideo());
                        /// 启动闪图
                        model.addAttribute("startPage", startPageCoreService.findStartPage());
                        /// 启动广告 列表
                        model.addAttribute("adverts", advertCoreService.findAdverts());
                    } else {
                        /// 启动视频
                        model.addAttribute("startVideo", null);
                        /// 启动闪图
                        model.addAttribute("startPage", null);
                        /// 启动广告 列表
                        model.addAttribute("adverts", new ArrayList<>());
                    }
                }
            }
        }
        model.addAttribute("dic", dictionaries);
    }

    /**
     * 手动检查版本
     */
    @RequestMapping("check-version")
    public void checkVersion (HttpServletRequest request, Model model) {
        String product = HttpUtil.getParameter(request, "_product");
        String language = HttpUtil.getParameter(request, com.business.app.base.constants.Constants.PARAM_LANGUAGE);
        if (UserDevice.PRODUCT_FITMIX.equals(product)) {
            if (Constants.SWITCH_OPEN.equals(SysParam.INSTANCE.SWITCH_APP_UPGRADE)) {//强制升级
                if(!checkVersion(request, SysParam.INSTANCE.NEWEST_APP_IOS_VERSION, SysParam.INSTANCE.NEWEST_APP_ANDROID_VERSION)) {
                    model.addAttribute("upgrade", Constants.SWITCH_OPEN);
                    model.addAttribute("content", SysParam.INSTANCE.UPGRADE_CONTENT);
                }
            }
            versionInfo(request, model,language);
        } else if (UserDevice.PRODUCT_ROC.equals(product)) {
            if (!checkSDKType(request)) {
                if (SysParam.INSTANCE.NEWEST_APP_ROC_ANDROID_VERSION > HttpUtil.getIntParameter(request, "_v")) {
                    model.addAttribute("upgrade", Constants.SWITCH_OPEN);
                    model.addAttribute("content", SysParam.INSTANCE.UPGRADE_ROC_CONTENT);

                    model.addAttribute("androidVersion", SysParam.INSTANCE.APP_ROC_ANDROID_UPGRADE_VERSION);
                    model.addAttribute("androidVersionView", SysParam.INSTANCE.APP_ROC_ANDROID_UPGRADE_VERSION_VIEW);
                    //2018-09-04 根据ROC app语言返回对应语言简介
                    if("zh".equals(language)) {
                        model.addAttribute("androidVersionIntroduction", SysParam.INSTANCE.APP_ROC_ANDROID_UPGRADE_VERSION_INTRODUCTION);
                    }
                    if("en".equals(language)){
                        model.addAttribute("androidVersionIntroduction", SysParam.INSTANCE.APP_ROC_ANDROID_UPGRADE_VERSION_INTRODUCTION_EN);
                    }
                    model.addAttribute("androidUpgradeUrl", SysParam.INSTANCE.APP_ROC_ANDROID_UPGRADE_URL);
                }
            }
        }


    }

    /**
     * 设置版本升级信息
     */
    public void versionInfo (HttpServletRequest request, Model model,String lan) {
        if(checkSDKType(request)){// app 版本信息
            model.addAttribute("iosVersion", SysParam.INSTANCE.APP_IOS_UPGRADE_VERSION);
            model.addAttribute("iosVersionView", SysParam.INSTANCE.APP_IOS_UPGRADE_VERSION_VIEW);
            //2018-09-04 根据app语言返回对应语言简介
            if("zh".equals(lan)) {
                model.addAttribute("iosVersionIntroduction", SysParam.INSTANCE.APP_IOS_UPGRADE_VERSION_INTRODUCTION);
            }
            if("en".equals(lan)){
                model.addAttribute("iosVersionIntroduction", SysParam.INSTANCE.APP_IOS_UPGRADE_VERSION_INTRODUCTION_EN);
            }
            model.addAttribute("iosUpgradeUrl", SysParam.INSTANCE.APP_IOS_UPGRADE_URL);
        } else {
            model.addAttribute("androidVersion", SysParam.INSTANCE.APP_ANDROID_UPGRADE_VERSION);
            model.addAttribute("androidVersionView", SysParam.INSTANCE.APP_ANDROID_UPGRADE_VERSION_VIEW);
            //2018-09-04 根据app语言返回对应语言简介
            if("zh".equals(lan)) {
                model.addAttribute("androidVersionIntroduction", SysParam.INSTANCE.APP_ANDROID_UPGRADE_VERSION_INTRODUCTION);
            }
            if("en".equals(lan)){
                model.addAttribute("androidVersionIntroduction", SysParam.INSTANCE.APP_ANDROID_UPGRADE_VERSION_INTRODUCTION_EN);
            }
            model.addAttribute("androidUpgradeUrl", SysParam.INSTANCE.APP_ANDROID_UPGRADE_URL);
        }

    }

    /*
     * 添加 app error 信息
     * @param content 错误信息
     * @param type log 类型
     * @param other 其他信息
     */
    /*@RequestMapping("app-error-upload")
    public void appErrorUpload(@RequestParam("content") String content,
                               @RequestParam("type") Integer type, @RequestParam(value = "other", required = false) Object other) {
        appService.appErrorUpload(content, type, other);
    }*/

    @RequestMapping("app-error-upload")
    public void appErrorUpload(HttpServletRequest request, @RequestParam("content") String content, @RequestParam(value = "other", required = false) String other) {
        appService.saveAppError(request, content, checkSDKType(request), other);
    }

    /**
     * 手动刷新缓存
     */
    @RequestMapping("refresh-sys-param")
    public void refreshSysParam () {
        SysParam.init();
    }

    /**
     * 手动刷新 Dic
     */
    @RequestMapping("refresh-dictionary")
    public void refreshDictionary () {
        DicUtil.init();
    }

    /**
     * 手动刷新 任务
     */
    @RequestMapping("refresh-task-info")
    public void refreshTaskInfo () {
        taskInfoService.init();
    }


    @RequestMapping("ip")
    public void ip(HttpServletRequest request) {
        TaoBaoIp taoBaoIp = HttpUtil.ipArea(HttpUtil.getIP(request));
        System.out.println(taoBaoIp);
    }

    /**
     * 隐私协议
     * @return
     */
    @RequestMapping("privacy-policy")
    public String privacyPolicy() {
        return "app/PrivacyStripBox";
    }

    /**
     * 获取邮箱的验证码
     */
    @RequestMapping("get/email/verification/code")
    public void getEmailVerificationCode(String email, HttpServletRequest request) {
        if (StringUtils.isEmpty(email)) {
            return;
        }
        if (!email.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")) {
            return;
        }

        appService.getEmailVerificationCode(email, HttpUtil.getParameter(request, "_product"));
    }
    /**
     * 获取邮箱的验证码
     */
    @RequestMapping("find/Mobile/verification/code")
    public void findMobileVerificationCode(Model model,String Mobile, HttpServletRequest request) {
        model.addAttribute("data", appService.queryMobileVerificationCode(Mobile));
    }

    /**
     * 获取手机号码的验证码
     */
    @RequestMapping("get/mobile/verification/new-code-ssl")
    public void getMobileVerificationCode(String mobile,HttpServletRequest request) {
        String remoteAddr = getIpAddress(request);
        if (StringUtils.isEmpty(mobile)) {
            return;
        }
        //lvjj 2018-07-30
        if(mobile.startsWith("+86")){
            String []strs=mobile.split("\\+86");
            mobile=strs[1];
        }
        appService.getMobileVerificationCode(mobile,remoteAddr);
    }

    /**
     * 用户手机注册 (验证码)
     *
     * @param mobile 手机号
     */
    @RequestMapping("/user/new-user-verify-code-ssl")
    public void userVerifyCode(Model model,HttpServletRequest request,@RequestParam("mobile") String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return;
        }
        //lvjj 2018-07-30
        if(mobile.startsWith("+86")){
            String []strs=mobile.split("\\+86");
            mobile=strs[1];
        }
        String remoteAddr = getIpAddress(request);
        String code = appService.getMobileVerificationCode(mobile,remoteAddr);
        model.addAttribute("code", code);
        model.addAttribute("data", code);
    }

    public static String getIpAddress(HttpServletRequest request){

        String ipAddress = request.getHeader("X-Forwarded-For");

        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknow".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("REMOTE-HOST");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();

            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡获取本机配置的IP地址
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inetAddress.getHostAddress();
            }
        }

        //对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
        if(null != ipAddress && ipAddress.length() > 15){
            //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",") > 0){
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        return ipAddress;
    }

}
