package com.business.app.webUser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.runPlan.RunPlanService;
import com.business.app.user.UserService;
import com.business.app.userCollection.UserCollectService;
import com.business.core.client.FileCenterClient;
import com.business.core.client.WXCenterClient;
import com.business.core.constants.DicConstants;
import com.business.core.constants.FileConstants;
import com.business.core.constants.RedisConstants;
import com.business.core.entity.SysParam;
import com.business.core.entity.runPlan.UserRunPlan;
import com.business.core.entity.user.User;
import com.business.core.utils.DicUtil;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weird on 2016/8/25.
 */
@Controller
@RequestMapping("web-user")
public class WebUserController extends BaseControllerSupport {

    @Autowired
    private UserService userService;
    @Autowired
    private UserCollectService userCollectService;
    @Autowired
    private RunPlanService runPlanService;
    @Autowired
    private WebUserService webUserService;

    /**
     * TODO 临时入口
     *
     * @return
     */
    @RequestMapping("login-page")
    public String turnToLogin() {
        return "user/login";
    }

    /**
     * TODO 临时入口
     *
     * @return
     */
    @RequestMapping("register-page")
    public String turnToRegister() {
        return "user/register";
    }

    /**
     * TODO 临时入口
     *
     * @return
     */
    @RequestMapping("forget-account-page")
    public String turnToForgetAccount() {
        return "user/forget-account";
    }

    /**
     * TODO 临时入口
     *
     * @return
     */
    @RequestMapping("my-zone")
    public String turnToUserZone() {
        return "user/my-zone";
    }

    /**
     * 用户登录(web)
     *
     * @param email       邮箱 or mobile or uid
     * @param password    密码
     * @param redirectUrl 回调地址
     */
    @RequestMapping("web-login")
    public void webLogin(Model model,
                         HttpServletRequest request,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {
        Object[] objects = userService.login(email, password, null);
        switch ((int) objects[0]) {
            case 0:
                User user = (User) objects[1];
                request.getSession().setAttribute("user", user);
                Object[] _objects = runPlanService.getPresentPlan(user.getId());
                Object[] h_objects = runPlanService.getEndPlans(user.getId());

                if (_objects[0].equals(1)) {
                    request.getSession().setAttribute("user_plan", (UserRunPlan) _objects[1]);
                    request.getSession().setAttribute("info", runPlanService.getInfo((UserRunPlan) _objects[1]));
                }

                if (h_objects[0].equals(1)) {
                    request.getSession().setAttribute("user_history_plan", (List<UserRunPlan>) h_objects[1]);
                }
                if (!StringUtil.isEmpty(redirectUrl)) {
                    model.addAttribute("redirectUrl", redirectUrl);
                } else {
                    model.addAttribute("redirectUrl", "/web-user/my-zone.htm");
                }
                break;
            case 1:
                tip(model, CodeConstants.LOGIN_USER_NOT_EXIST);
                break;
            case 2:
                tip(model, CodeConstants.LOGIN_USER_STATE_NO_ACTIVATES);

                break;
            case 3:
                tip(model, CodeConstants.LOGIN_USER_PASSWORD_ERROR);
                if (request.getSession().getAttribute("error_login") == null || request.getSession().getAttribute("error_login").equals("")) {
                    request.getSession().setAttribute("error_login", 0);
                } else {
                    int error_login = (int) (request.getSession().getAttribute("error_login")) + 1;
                    request.getSession().setAttribute("error_login", error_login);
                }
                if ((int) (request.getSession().getAttribute("error_login")) >= 3) {
                    model.addAttribute("mult_login", false);
                    request.getSession().setAttribute("mult_login", false);
                } else {
                    model.addAttribute("mult_login", true);
                    request.getSession().setAttribute("mult_login", true);
                }
                break;
        }
    }

    /**
     * 用户注册(web)
     * todo 网页注册的注册类型(loginType)为暂定为1
     *
     * @param model
     * @param request
     * @param mobile
     * @param password
     * @param regType
     * @param verifyCode
     */
    @RequestMapping("web-reg")
    public void webRegister(Model model,
                            HttpServletRequest request,
                            @RequestParam("mobile") String mobile,
                            @RequestParam("password") String password,
                            @RequestParam("regType") Integer regType,
                            @RequestParam("verifyCode") String verifyCode
    ) {
        if (regType == 0) {
            if (verifyCode.equals(request.getSession().getAttribute("code"))) {
                Object[] result = userService.mobileRegister(mobile, password,
                        getContext().getChannel(), HttpUtil.getIP(request), null);
                switch ((int) result[0]) {
                    case 0:
                        model.addAttribute("user", (User) result[1]);
                        model.addAttribute("regType", regType);
                        request.getSession().setAttribute("user", result[1]);
                        break;
                    case 1:
                        tip(model, CodeConstants.USER_REGISTER_MOBILE_EXIST);
                        break;
                }
            } else {
                tip(model, CodeConstants.USER_REGISTER_MOBILE_CODE_ERROR);
            }
        } else {
            String email = mobile;
            Object[] objects = userService.register(email, email, password, getContext().getChannel(), HttpUtil.getIP(request), null);
            switch ((int) objects[0]) {
                case 0:
                    User user = (User) objects[1];
                    model.addAttribute("regType", regType);
                    model.addAttribute("user", user);
                    request.getSession().setAttribute("user", user);
                    break;
                case 1:
                    tip(model, CodeConstants.REGISTER_APP_USER_EMAIL_REPEAT, "邮箱已存在");
                    break;
                case 2:
                    tip(model, CodeConstants.REGISTER_APP_USER_EMAIL_NO_CORRECT, "邮箱格式不正确");
                    break;
                case 3:
                    tip(model, CodeConstants.REGISTER_APP_USER_PASSWORD_LENGTH, "密码长度不能小于 5");
                    break;
            }
        }

    }

    /**
     * 注销
     *
     * @param request
     */
    @RequestMapping("layout")
    public void layout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("user_plan");
    }

    /**
     * 验证登录状态
     *
     * @param model
     * @param request
     */
    @RequestMapping("login-exist")
    public void loginExist(Model model, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            tip(model, CodeConstants.LOGIN_USER_STATE_NO_LOGIN);
        }
    }

    /**
     * 个性签名修改
     */
    @RequestMapping("update-signature")
    public void updateSignature(Model model,
                                HttpServletRequest request,
                                @RequestParam("signature") String signature
    ) {
        int result;
        User user = null;
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            result = 1;
        } else {
            user = (User) object;
            result = webUserService.updateSignature(user, signature);
        }

        switch (result) {
            case 0:
                request.getSession().setAttribute("user", RedisConstants.cacheGetOnlineUser(user.getId()));
                break;
            case 1:
                //登录失效
                tip(model, CodeConstants.LOGIN_USER_STATE_EXPIRATION);
                break;
        }

    }

    /**
     * 上传预览图片
     *
     * @param request
     */
    @RequestMapping("upload-preview")
    public void uploadPreview(Model model, HttpServletRequest request) throws IOException {
        int result;
        User user = null;
        String avatarUrl = null;
        String fileName = null;
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            result = 1;
        } else {
            user = (User) object;
            Map<String, MultipartFile> fileMap = FileCenterClient.buildMultipartFile(request);
            if (!CollectionUtils.isEmpty(fileMap)) {
                for (Map.Entry<String, MultipartFile> multipartFile : fileMap.entrySet()) {
                    MultipartFile file = multipartFile.getValue();
                /*avatarUrl = FileCenterClient.upload(file, FileConstants.FILE_TYPE_USER_AVATAR_IMAGE);  // 转发文件服务器*/
/*                System.err.println("文件长度:" + file.getSize());
                System.err.println("文件类型:" + file.getContentType());
                System.err.println("文件名称:" + file.getName());
                System.err.println("文件原名:" + file.getOriginalFilename());*/
                    fileName = file.getOriginalFilename();
                    avatarUrl = request.getSession().getServletContext().getRealPath("/imgs/upload/");
                    FileUtils.copyInputStreamToFile(file.getInputStream(), new File(avatarUrl, file.getOriginalFilename()));

                }
                result = 0;
            } else {
                result = 2;
            }
        }
        switch (result) {
            case 0:
                model.addAttribute("avatarUrl", "imgs/upload/" + fileName);
                break;
            case 1:
                //登录失效
                tip(model, CodeConstants.LOGIN_USER_STATE_EXPIRATION);
                break;
            case 2:
                //上传头像失败
                tip(model, CodeConstants.USER_INFO_MODIFY_FILE_UPLOAD_FAILED);
        }

    }


    /**
     * 裁剪图片并上传
     *
     * @param model
     * @param request
     * @param x       裁剪图片的起始位置x轴坐标
     * @param y       裁剪图片的起始位置y轴坐标
     * @param w       裁剪图片的width
     * @param h       裁剪图片的height
     * @param picPath 裁剪图片的原始路径
     */
    @RequestMapping("upload-avatar")
    public void uploadAvatar(Model model,
                             HttpServletRequest request,
                             @RequestParam("x") double x,
                             @RequestParam("y") double y,
                             @RequestParam("w") double w,
                             @RequestParam("h") double h,
                             @RequestParam("scaleX") double scaleX,
                             @RequestParam("scaleY") double scaleY,
                             @RequestParam("picPath") String picPath) {
        try {
            System.err.println("x:" + x);
            System.err.println("y:" + y);
            System.err.println("w:" + w);
            System.err.println("h:" + h);
            Image img;
            ImageFilter cropFilter;
            String realPath = request.getSession().getServletContext().getRealPath("/");
            picPath = realPath + picPath;
            File file = new File(picPath);
            String fileName = file.getName();
            System.err.println(picPath);
            BufferedImage bi = ImageIO.read(file);
            int srcWidth = bi.getWidth();
            int srcHeight = bi.getHeight();

            x = srcWidth / scaleX * x;
            y = srcHeight / scaleY * y;
            w = srcWidth / scaleX * w;
            h = srcHeight / scaleY * h;

            if (srcWidth >= w && srcHeight >= h) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                cropFilter = new CropImageFilter((int) x, (int) y, (int) w, (int) h);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage((int) w, (int) h, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null);
                g.dispose();
                String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
                ImageIO.write(tag, prefix, file);
                String avatarUrl = null;
                FileInputStream input = new FileInputStream(file);
                System.err.println("fileName:" + fileName);
                avatarUrl = FileCenterClient.upload(input, FileConstants.FILE_TYPE_USER_AVATAR_IMAGE, fileName);
                User user = (User) request.getSession().getAttribute("user");
                webUserService.updateAvatar(user, avatarUrl);
                request.getSession().setAttribute("user", RedisConstants.cacheGetOnlineUser(user.getId()));
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改用户信息
     *
     * @param model
     * @param request
     * @param name
     * @param gender
     * @param age
     * @param height
     * @param weight
     * @param type
     */
    @RequestMapping("web-info-modify")
    public void infoModify(Model model,
                           HttpServletRequest request,
                           @RequestParam(value = "name", required = false) String name, // 用户名 1.2 版本开始
                           @RequestParam("gender") Integer gender,
                           @RequestParam("age") Integer age,
                           @RequestParam("height") Double height,
                           @RequestParam("weight") Double weight,
                           @RequestParam("type") Integer type) {
        int result;
        User user = null;
        Object object = request.getSession().getAttribute("user");

        height = (double) height.intValue();
        weight = (double) weight.intValue();

        if (age < 1) {
            tip(model, CodeConstants.USER_INFO_MODIFY_AGE_ERROR);
            return;
        }
        if (DicUtil.getDictionary(DicConstants.DIC_TYPE_GENDER, gender) == null) {
            tip(model, CodeConstants.USER_INFO_MODIFY_GENDER_ERROR);
            return;
        }
        if (DicUtil.getDictionary(DicConstants.DIC_TYPE_USER_TYPE, type) == null) {
            tip(model, CodeConstants.USER_INFO_MODIFY_TYPE_ERROR, "用户类型不正确");
            return;
        }

        if (object == null) {
            result = 5;
        } else {
            user = (User) object;
            Integer uid = user.getId();
            result = userService.userInfoModify(uid, name, gender, age, height, weight, type, null, null);
        }

        switch (result) {
            case 0:
                request.getSession().setAttribute("user", RedisConstants.cacheGetOnlineUser(user.getId()));
                break;
            case 4:
                tip(model, CodeConstants.LOGIN_USER_NOT_EXIST, "用户不存在");
                break;
            case 5:
                tip(model, CodeConstants.LOGIN_USER_STATE_EXPIRATION);
                break;
        }
    }

    @RequestMapping("web-login-qq")
    public String loginQQ() {
        return "user/qqlogin";
    }

    @RequestMapping("qq-login-result")
    public void login_result(Model model,
                             HttpServletRequest request,
                             @RequestParam("access_token") String token,
                             @RequestParam("openid") String openid) {
        Object[] objects = userService.loginQQ(token, openid, getContext().getChannel(), HttpUtil.getIP(request), null);
        switch ((int) objects[0]) {
            case 0:
                User user = (User) objects[1];
                model.addAttribute("user", user);
                request.getSession().setAttribute("user", user);

                Object[] _objects = runPlanService.getPresentPlan(user.getId());
                Object[] h_objects = runPlanService.getEndPlans(user.getId());

                if (_objects[0].equals(1)) {
                    request.getSession().setAttribute("user_plan", (UserRunPlan) _objects[1]);
                    request.getSession().setAttribute("info", runPlanService.getInfo((UserRunPlan) _objects[1]));
                }

                if (h_objects[0].equals(1)) {
                    request.getSession().setAttribute("user_history_plan", (List<UserRunPlan>) h_objects[1]);
                }
                break;
        }
    }

    @RequestMapping("web-login-wb")
    public String loginWB() {
        return "user/wblogin";
    }

    @RequestMapping("login-wb")
    public void webLogin_wb(Model model,
                            HttpServletRequest request,
                            @RequestParam("code") String code) {
        CloseableHttpResponse response = null;
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
            JSONObject jsonObject = JSON.parseObject(responseText);
            String access_token = jsonObject.get("access_token").toString();
            String openid = jsonObject.get("uid").toString();
            Object[] objects = userService.loginWB(access_token, openid, getContext().getChannel(), HttpUtil.getIP(request), null);
            switch ((int) objects[0]) {
                case 0:
                    User user = (User) objects[1];
                    model.addAttribute("user", user);
                    request.getSession().setAttribute("user", user);

                    Object[] _objects = runPlanService.getPresentPlan(user.getId());
                    Object[] h_objects = runPlanService.getEndPlans(user.getId());

                    if (_objects[0].equals(1)) {
                        request.getSession().setAttribute("user_plan", (UserRunPlan) _objects[1]);
                        request.getSession().setAttribute("info", runPlanService.getInfo((UserRunPlan) _objects[1]));
                    }

                    if (h_objects[0].equals(1)) {
                        request.getSession().setAttribute("user_history_plan", (List<UserRunPlan>) h_objects[1]);
                    }
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    @RequestMapping("web-connect-wx")
    public String connectWX() {
        return "user/wxlogin";
    }

    /**
     * 微信登录
     * @param model
     * @param request
     * @param code
     */
    @RequestMapping("login-wx")
    public void webLogin_wx(Model model,
                            HttpServletRequest request,
                            @RequestParam("code") String code) {
        JSONObject jsonObject = WXCenterClient.getAccessToken(code);
        String access_token = jsonObject.get("access_token").toString();
        String refresh_token = jsonObject.get("refresh_token").toString();
        String openid = jsonObject.get("openid").toString();

        Object[] objects = webUserService.loginWX(access_token, openid, getContext().getChannel(), HttpUtil.getIP(request));

        switch ((int) objects[0]) {
            case 0:
                User user = (User) objects[1];
                model.addAttribute("user", user);
                request.getSession().setAttribute("user", user);

                Object[] _objects = runPlanService.getPresentPlan(user.getId());
                Object[] h_objects = runPlanService.getEndPlans(user.getId());

                if (_objects[0].equals(1)) {
                    request.getSession().setAttribute("user_plan", (UserRunPlan) _objects[1]);
                    request.getSession().setAttribute("info", runPlanService.getInfo((UserRunPlan) _objects[1]));
                }

                if (h_objects[0].equals(1)) {
                    request.getSession().setAttribute("user_history_plan", (List<UserRunPlan>) h_objects[1]);
                }
                break;
        }

    }

    /**
     * 绑定用户账号(非绑定手机则验证码为-1)
     * todo 暂时只绑定手机和邮箱
     *
     * @param model
     * @param request
     */
    @RequestMapping("binding")
    public void binding(Model model,
                        HttpServletRequest request,
                        @RequestParam("bindingContent") String bindingContent,
                        @RequestParam("verifyCode") String verifyCode,
                        @RequestParam("type") Integer type
    ) {
        int result;
        String vcode = "";
        User user = null;
        Object objects = request.getSession().getAttribute("user");
        if (objects == null) {
            result = 4;
        } else if (verifyCode.equals("-1")) {
            user = (User) request.getSession().getAttribute("user");
            result = userService.binding(user.getId(), bindingContent, "", null, type);
        } else {
            Object object = request.getSession().getAttribute("code");
            if (object == null) {
                result = 3;
            } else {
                vcode = object.toString();
                if (vcode.equals(verifyCode)) {
                    user = (User) request.getSession().getAttribute("user");
                    result = userService.binding(user.getId(), bindingContent, "", null, type);
                } else {
                    result = 3;
                }
            }
        }
        switch (result) {
            case 0:
                request.getSession().setAttribute("user", RedisConstants.cacheGetOnlineUser(user.getId()));
                break;
            case 1:
                tip(model, CodeConstants.LOGIN_USER_NOT_EXIST);
                break;
            case 2:
                tip(model, CodeConstants.USER_BINDING_IS_FITMIX_USER);
                break;
            case 3:
                //验证码错误
                tip(model, CodeConstants.USER_REGISTER_MOBILE_CODE_ERROR);
                break;
            case 4:
                //登录失效
                tip(model, CodeConstants.LOGIN_USER_STATE_EXPIRATION);
                break;
        }

    }

    @RequestMapping("wx-connect-bind")
    public String wxBind() {
        return "user/wxbind";
    }


    /**
     * 第三方账号绑定
     * todo 暂定wechat
     *
     * @param model
     * @param request
     * @param code
     */
    @RequestMapping("other-binding")
    public void otherBinding(Model model,
                             HttpServletRequest request,
                             @RequestParam("code") String code) {
        int result;
        Object object = request.getSession().getAttribute("user");
        User user = null;

        if (object == null) {
            result = 1;
        } else {
            user = (User) object;
            JSONObject jsonObject = WXCenterClient.getAccessToken(code);
            String access_token = jsonObject.get("access_token").toString();
            String openid = jsonObject.get("openid").toString();
            result = webUserService.bindWX(user, access_token, openid);

        }

        switch(result) {
            case 0:
                request.getSession().setAttribute("user", RedisConstants.cacheGetOnlineUser(user.getId()));
                break;
            case 1:
                //登录失效
                tip(model,CodeConstants.LOGIN_USER_STATE_EXPIRATION);
                break;
            case 2:
                //该微信已经是ifitmix用户
                tip(model,CodeConstants.WECHAT_IS_BINDED);
                break;
            case 3:
                //token失效
                tip(model,CodeConstants.WECHAT_TOKEN_GET_ERROR);
                break;

        }

    }

    /**
     * 用户手机解绑验证码
     *
     * @param request
     * @param model
     */
    @RequestMapping("user-verify-code")
    public void userVerifyCode(HttpServletRequest request, Model model) {
        Object object = request.getSession().getAttribute("user");

        if (object == null) {
            tip(model, CodeConstants.LOGIN_USER_STATE_EXPIRATION);
        } else {
            User user = (User) object;
            String mobile = user.getMobile();
            Object[] result = userService.mobileRegisterCode(request, mobile);

            switch ((int) result[0]) {
                case 0:
                    // TODO 版本控制
                    if (getTerminalType() == com.business.core.constants.Constants.TERMINAL_WEB) {
                        request.getSession().setAttribute("code", result[1]);
                        model.addAttribute("vcode", result[1]);
                    }
                    break;
            }
        }

    }

    /**
     * 解绑
     *
     * @param model
     * @param request
     * @param verifyCode
     * @param type
     */
    @RequestMapping("unbinding")
    public void unbinding(Model model,
                          HttpServletRequest request,
                          @RequestParam("verifyCode") String verifyCode,
                          @RequestParam("type") Integer type
    ) {
        int result;
        String vcode = "";
        User user = null;
        if (verifyCode.equals("-1")) {
            user = (User) request.getSession().getAttribute("user");
            result = userService.binding(user.getId(), "", "", null, type);
        } else {
            Object object = request.getSession().getAttribute("code");
            Object objects = request.getSession().getAttribute("user");
            if (object == null) {
                result = 4;
            } else if (objects == null) {
                result = 5;
            } else {
                vcode = object.toString();
                if (vcode.equals(verifyCode)) {
                    user = (User) request.getSession().getAttribute("user");
                    result = userService.binding(user.getId(), "", "", null, type);
                } else {
                    result = 4;
                }
            }
        }
        switch (result) {
            case 0:
                request.getSession().setAttribute("user", RedisConstants.cacheGetOnlineUser(user.getId()));
                break;
            case 1:
                tip(model, CodeConstants.LOGIN_USER_NOT_EXIST);
                break;
            case 2:
                tip(model, CodeConstants.USER_BINDING_IS_FITMIX_USER);
                break;
            case 3:
                tip(model, CodeConstants.USER_UNBINDING_LOGIN_NOT_OPERATION);
                break;
            case 4:
                tip(model, CodeConstants.USER_REGISTER_MOBILE_CODE_ERROR);
                break;
            case 5:
                tip(model, CodeConstants.LOGIN_USER_STATE_EXPIRATION);
        }
    }

    /**
     * 修改密码
     *
     * @param model
     * @param request
     * @param oldPwd
     * @param nowPwd
     */
    @RequestMapping("modify-password")
    public void modifyPassword(Model model,
                               HttpServletRequest request,
                               @RequestParam("oldPwd") String oldPwd,
                               @RequestParam("nowPwd") String nowPwd) {
        int result;
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            result = 5;
        }
        User user = (User) request.getSession().getAttribute("user");
        result = userService.modifyPassword(user.getId(), oldPwd, nowPwd);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.LOGIN_USER_NOT_EXIST);
                break;
//            case 2:
//                tip(model, CodeConstants.USER_NON_APP_REGISTER_NOT_MODIFY_PASSWORD);
//                break;
            case 3:
                tip(model, CodeConstants.LOGIN_USER_PASSWORD_ERROR);
                break;
            case 4:
                tip(model, CodeConstants.REGISTER_APP_USER_PASSWORD_LENGTH);
                break;
            case 5:
                tip(model, CodeConstants.LOGIN_USER_STATE_EXPIRATION);
        }
    }

}
