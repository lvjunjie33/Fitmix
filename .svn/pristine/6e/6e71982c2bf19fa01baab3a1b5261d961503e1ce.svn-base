package com.business.ugc.admin;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.ApplicationConfig;
import com.business.core.constants.CodeConstants;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.mix.MixAlbum;
import com.business.core.entity.user.User;
import com.business.core.sms.SmsCoreService;
import com.business.core.utils.MD5Util;
import com.business.ugc.base.QxMap;
import com.business.ugc.base.support.BaseControllerSupport;
import com.business.ugc.base.view.WebModel;
import com.business.ugc.content.albums.MixAlbumsService;
import com.business.ugc.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by fenxio on 2016/8/23.
 */
@Controller
@RequestMapping("ugc/admin")
public class AdminController extends BaseControllerSupport {

    @Autowired
    private UserService userService;
    @Autowired
    private SmsCoreService smsCoreService;
    @Autowired
    private MixAlbumsService mixAlbumsService;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@CookieValue(value = "uid", required = false) Integer uid, @CookieValue(value = "token", required = false) String token,
                        @CookieValue(value = "rememberMe", required = false) Integer rememberMe , HttpServletRequest request) {
        request.getSession().setAttribute(Constants.BASE_URI, ApplicationConfig.FILECENTER_STORAGE_PATH);
        //记住我
        if(null != uid && null != token && null != rememberMe && rememberMe == 1) {
            User user = userService.findById(uid);
            String userToken = MD5Util.MD5Encode(user.getId()+"["+user.getPassword()+"]", "utf-8");
            if(userToken.equals(token)) {
                request.getSession().setAttribute(Constants.CURRENT_USER, user);
                //初始化常量
                this.init(request, user.getId());
                String url = "ugc/data/"+uid+"/type/7";
                return redirect(url);
            }
        }
        return "admin/login";
    }

    /**
     * 登录
     *
     * @param email 邮箱
     * @param password 密码
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public WebModel login(@RequestParam("email") String email, @RequestParam("password") String password, Integer rememberMe, HttpServletRequest request,
                          HttpServletResponse response) {
        User user = userService.selectByEmail(email);
        if(null == user) {
            return new WebModel(CodeConstants.MISS, "用户不存在");
        } else {
            if(user.getPassword().equals(password)) {
                request.getSession().setAttribute(Constants.CURRENT_USER, user);
                if(null != rememberMe && rememberMe == 1) {
                    String userToken = MD5Util.MD5Encode(user.getId()+"["+user.getPassword()+"]", "utf-8");
                    response.addCookie(new Cookie("uid", user.getId() + ""));
                    response.addCookie(new Cookie("token", userToken));
                    response.addCookie(new Cookie("rememberMe", rememberMe + ""));
                }
                //初始化常量
                this.init(request, user.getId());
                return new WebModel(CodeConstants.SUCCESS, user.getId(), "登录成功");
            } else {
                return new WebModel(CodeConstants.MISS, "用户或密码错误");
            }
        }
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        //清除cookie
        Cookie uid = new Cookie("uid", null);
        uid.setMaxAge(0);
        Cookie token = new Cookie("token", null);
        token.setMaxAge(0);
        Cookie rememberMe = new Cookie("rememberMe", null);
        rememberMe.setMaxAge(0);
        response.addCookie(uid);
        response.addCookie(token);
        response.addCookie(rememberMe);
        return "admin/login";
    }


    /**
     * 跳转 注册 页面
     * @param type 注册类型
     * @return
     */
    @RequestMapping(value = "register/{type}", method = RequestMethod.GET)
    public String register(@PathVariable("type") String type, Model model) {
        if(type.equals("qq")) {

        }
        if(type.equals("wx")) {

        }
        if(type.equals("wb")) {

        }
        return "admin/register";
    }

    /**
     * 注册
     * @param user
     * @param request
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public WebModel register(User user, @RequestParam("verifyCode") String verifyCode, @RequestParam("password1") String password1,
                         @RequestParam("password2") String password2, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute(Constants.VERIFYCODE);
        String mobile = (String) request.getSession().getAttribute(Constants.MOBILE);
        if(code.equals(verifyCode) && mobile.equals(user.getMobile())) {
            if(null != password1 && password1.equals(password2)) {
                user.setPassword(password1);
                user.setRegisterPlatform(Constants.REGISTER_PLATFORM_UGC);
                user.setActivatesUgc(User.STATE_ACTIVATES);
                user.setGender(Constants.GENDER_SECRECY);
                user.setAddTime(System.currentTimeMillis());
                user.setBirthday(new QxMap<String, String>().add("year", "00").add("month", "00").add("day", "00"));
                user.setCity("");
                user.setSignature("");
                userService.insert(user);
                request.getSession().setAttribute(Constants.CURRENT_USER, user);
                this.init(request, user.getId());
                return new WebModel(CodeConstants.SUCCESS, user.getId());
            } else {
                return new WebModel(CodeConstants.MISS, null, "密码不一致");
            }
        } else {
            return new WebModel(CodeConstants.MISS, null, "验证码不正确");
        }
    }

    /**
     * 忘记密码页面
     * @return
     */
    @RequestMapping(value = "forget", method = RequestMethod.GET)
    public String forget() {
        return "admin/forget";
    }

    /**
     * 重置密码
     * @param password
     * @param verifyCode
     * @param email
     * @param request
     * @return
     */
    @RequestMapping(value = "reset", method = RequestMethod.POST)
    @ResponseBody
    public WebModel reset(@RequestParam("password") String password,
                          @RequestParam("verifyCode") String verifyCode,
                          @RequestParam("email") String email,
                          HttpServletRequest request) {
        User user = userService.selectByEmail(email);
        if(password.trim().equals("") || password.length() < 6) {
            return new WebModel(CodeConstants.MISS, null, "密码格式不正确");
        } else if(!verifyCode.equals(request.getSession().getAttribute(Constants.VERIFYCODE))) {
            return new WebModel(CodeConstants.MISS, null, "验证码不正确");
        } else if(null == user) {
            return new WebModel(CodeConstants.MISS, null, "用户不存在");
        } else {
            user.setPassword(password);
            userService.updateByIdSelective(user);
            return new WebModel(CodeConstants.SUCCESS, null);
        }


    }

    /**
     * 上传头像
     * @param file 头像文件
     * @return
     */
    @RequestMapping(value = "upload/avatar", method = RequestMethod.POST)
    @ResponseBody
    public WebModel avatar(MultipartFile file) {
        String path = FileCenterClient.upload(file, FileConstants.FILE_TYPE_USER_AVATAR_IMAGE);
        String url = FileCenterClient.buildUrl(path);
        return new WebModel(CodeConstants.SUCCESS, new QxMap<>().add("url", url).add("path", path));
    }

    /**
     * 获取验证码
     * @param mobile 手机号
     * @param request
     * @return
     */
    @RequestMapping(value = "verifyCode", method = RequestMethod.POST)
    @ResponseBody
    public WebModel verifyCode(@RequestParam("mobile") String mobile,
                               @RequestParam(value = "email", required = false) String email,
                               HttpServletRequest request) {
        String code = "";
        if(!"".equals(mobile.trim())) {
            code = smsCoreService.sendVerificationCode(mobile, "");
        } else {
            User user = userService.selectByEmail(email);
            if(null != user && user.getMobile() != null) {
                mobile = user.getMobile();
                code = smsCoreService.sendVerificationCode(user.getMobile(), "");
            }
        }

        System.out.println("CODE:" + code);
        request.getSession().setAttribute(Constants.VERIFYCODE, code);
        request.getSession().setAttribute(Constants.MOBILE, mobile);
        return new WebModel(CodeConstants.SUCCESS, null);
    }

    /**
     * 注册校验
     * @param type 类型
     * @param key 值
     * @return
     */
    @RequestMapping(value = "check", method = RequestMethod.GET)
    @ResponseBody
    public WebModel check(@RequestParam("type") String type, @RequestParam("key") String key) {
        if("email".equals(type)) {
            User user = userService.selectByEmail(key);
            if(null == user) {
                return new WebModel(CodeConstants.SUCCESS, null);
            } else {
                return new WebModel(CodeConstants.MISS, null, "邮箱已存在");
            }
        } else if("mobile".equals(type)) {
            User user = userService.selectByMobile(key);
            if(null == user) {
                return new WebModel(CodeConstants.SUCCESS, null);
            } else {
                return new WebModel(CodeConstants.MISS, null, "手机号已经被占用");
            }
        } else if("forget".equals(type)) {
            User user = userService.selectByEmail(key);
            if(null != user) {
                return new WebModel(CodeConstants.SUCCESS, new QxMap<>("email", user.getEmail()).add("mobile", "*******"+user.getMobile().substring(user.getMobile().length()-4,user.getMobile().length()-1)));
            } else {
                return new WebModel(CodeConstants.MISS, null, "邮箱不存在");
            }

        } else {
            return new WebModel(CodeConstants.MISS, null);
        }
    }

    /**
     * 初始化常量
     * @param request
     */
    public void init(HttpServletRequest request, Integer uid) {
        //初始化常量
        request.getSession().setAttribute(Constants.BASE_URI, ApplicationConfig.FILECENTER_STORAGE_PATH);
        List<MixAlbum> mixAlbumList = mixAlbumsService.findByMap(new QxMap<String, Object>("uid", uid), new QxMap<>("addTime", Sort.Direction.DESC));
        MixAlbum mixAlbum = new MixAlbum();
        if(null != mixAlbumList && mixAlbumList.size() > 0) {
            mixAlbum = mixAlbumList.get(0);
        }
        request.getSession().setAttribute(Constants.MIX_ALBUM_LIST, mixAlbumList);
        request.getSession().setAttribute(Constants.CURRENT_MIX_ALBUMS, mixAlbum);
    }

}
