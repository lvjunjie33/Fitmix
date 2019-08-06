package com.business.bbs.user;

import com.alibaba.fastjson.JSONObject;
import com.business.bbs.base.constants.CodeConstants;
import com.business.bbs.base.support.BaseControllerSupport;
import com.business.core.client.WBCenterClient;
import com.business.core.client.WXCenterClient;
import com.business.core.constants.VersionConstants;
import com.business.core.entity.user.User;
import com.business.core.sms.SmsCoreService;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by weird on 2016/10/8.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseControllerSupport {

    @Autowired
    private UserService userService;


    /**
     *  登录页面入口
     * @return
     */
    @RequestMapping("login-page")
    public String turnToLogin() {
        return "user/login";
    }

    /**
     *  注册页面入口
     * @return
     */
    @RequestMapping("register-page")
    public String turnToRegister() {
        return "user/register";
    }

    /**
     *  忘记密码页面入口
     * @return
     */
    @RequestMapping("forget-account-page")
    public String turnToForgetAccount() {
        return "user/forget-account";
    }

    /**
     *  用户登录
     * @param email 邮箱 or mobile or uid
     * @param password  密码
     * @param redirectUrl   回调地址
     */
    @RequestMapping("login")
    public void webLogin(Model model,
                         HttpServletRequest request,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {
        Object[] objects = userService.login(email, password);
        switch ((int) objects[0]) {
            case 0:
                User user = (User) objects[1];
                request.getSession().setAttribute("user", user);

                if (!StringUtil.isEmpty(redirectUrl)) {
                    model.addAttribute("redirectUrl", redirectUrl);
                } else {
                    model.addAttribute("redirectUrl", "/bbs");
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
                    request.getSession().setAttribute("mult_login", false);
                    model.addAttribute("mult_login", false);
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
     * @param mobile    邮箱 or mobile
     * @param password  密码
     * @param regType   注册类型
     * @param verifyCode    验证码(注册类型不是手机则为-1)
     */
    @RequestMapping("register")
    public void webRegister(Model model,
                            HttpServletRequest request,
                            @RequestParam("mobile") String mobile,
                            @RequestParam("password") String password,
                            @RequestParam("regType") Integer regType,
                            @RequestParam("verifyCode") String verifyCode
    ) {
        if (regType == 0) {
            if (verifyCode.equals(request.getSession().getAttribute("code"))) {
                Object[] result = userService.mobileRegister(mobile, password, HttpUtil.getIP(request));
                switch ((int) result[0]) {
                    case 0:
                        request.getSession().setAttribute("user", result[1]);
                        model.addAttribute("user", (User) result[1]);
                        model.addAttribute("regType", regType);
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
            Object[] objects = userService.register(email, email, password, HttpUtil.getIP(request));
            switch ((int) objects[0]) {
                case 0:
                    User user = (User) objects[1];
                    model.addAttribute("regType", regType);
                    model.addAttribute("user", user);
                    request.getSession().setAttribute("user", user);
                    break;
                case 1:
                    tip(model, CodeConstants.REGISTER_APP_USER_EMAIL_REPEAT);
                    break;
                case 2:
                    tip(model, CodeConstants.REGISTER_APP_USER_EMAIL_NO_CORRECT);
                    break;
                case 3:
                    tip(model, CodeConstants.REGISTER_APP_USER_PASSWORD_LENGTH);
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
    }

    /**
     *
     * @return
     */
    @RequestMapping("web-login-qq")
    public String loginQQ() {
        return "user/qqlogin";
    }

    /**
     *
     * @param token
     * @param openid
     */
    @RequestMapping("qq-login-result")
    public void login_result(Model model,
                             HttpServletRequest request,
                             @RequestParam("access_token") String token,
                             @RequestParam("openid") String openid) {
        Object[] objects = userService.loginQQ(token, openid, HttpUtil.getIP(request));
        switch ((int) objects[0]) {
            case 0:
                User user = (User) objects[1];
                model.addAttribute("user", user);
                request.getSession().setAttribute("user", user);
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
        JSONObject jsonObject = WBCenterClient.getAccessToken(code);
        String access_token = jsonObject.get("access_token").toString();
        String openid = jsonObject.get("uid").toString();
        Object[] objects = userService.loginWB(access_token, openid, HttpUtil.getIP(request));
        switch ((int) objects[0]) {
            case 0:
                User user = (User) objects[1];
                request.getSession().setAttribute("user", user);
                model.addAttribute("user", user);
                break;
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

        Object[] objects = userService.loginWX(access_token, openid, HttpUtil.getIP(request));

        switch ((int) objects[0]) {
            case 0:
                User user = (User) objects[1];
                model.addAttribute("user", user);
                request.getSession().setAttribute("user", user);
                break;
            default:break;
        }

    }

    /**
     * 用户手机注册 (验证码)
     *
     * @param mobile 手机号
     */
    @ApiOperation(value = "用户手机注册 (验证码)", notes = "用户手机注册 (验证码)", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("user-verify-code")
    public void userVerifyCode(@ApiIgnore HttpServletRequest request, @ApiIgnore Model model,
                               @ApiParam(required = true, value = "手机号") @RequestParam("mobile") String mobile) {
        Object[] result = userService.mobileRegisterCode(request, mobile);

        switch ((int) result[0]) {
            case 0:
                request.getSession().setAttribute("code", result[1]);
                model.addAttribute("vcode", result[1]);
                break;
        }
    }

}
