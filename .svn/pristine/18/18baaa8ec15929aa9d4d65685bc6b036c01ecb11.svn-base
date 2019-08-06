package com.business.ugc.user;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.CodeConstants;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserIdentityInfo;
import com.business.ugc.base.support.BaseControllerSupport;
import com.business.ugc.base.view.WebModel;
import com.business.ugc.util.ReflectionUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by fenxio on 2016/8/19.
 */
@Controller
@RequestMapping("ugc/user")
public class UserController extends BaseControllerSupport {

    @Autowired
    private UserService userService;

    /**
     * 跳转用户信息列表
     * @param uid 用户ID
     * @return
     */
    @RequestMapping(value = "{uid}/info", method = RequestMethod.GET)
    public String getUserInfo(@PathVariable("uid") Integer uid) {
        return "user/info";
    }


    /**
     * 更新用户 部分信息
     * @param user
     * @param uid
     * @return
     */
    @RequestMapping(value = "{uid}", method = {RequestMethod.PUT, RequestMethod.POST })
    @ResponseBody
    public WebModel updateUserInfo(User user, @PathVariable("uid") Integer uid,
                                   @RequestParam(value = "verifyCode", required = false) String verifyCode,
                                   @RequestParam(value = "avatarImg", required = false) String avatarImg,
                                   @RequestParam(value = "avatarImgName", required = false) String avatarImgName,
//                                   @RequestParam(value = "idCardFile", required = false) MultipartFile idCardFile,
//                                   @RequestParam(value = "radioUploadFile", required = false) MultipartFile radioUploadFile,
//                                   @RequestParam(value = "followerFile", required = false) MultipartFile followerFile,
                                   HttpServletRequest request) {
        //头像
        if(null != avatarImg && null != avatarImgName) {
            avatarImg = avatarImg.split(",")[1];
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                byte[] decodedBytes = decoder.decodeBuffer(avatarImg);
                String path = FileCenterClient.upload(decodedBytes, FileConstants.FILE_TYPE_USER_AVATAR_IMAGE, avatarImgName);
                user.setAvatar(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        User currentUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        if(null != user.getMobile()) {
            if(currentUser.getMobile().equals(user.getMobile())
               || !user.getMobile().equals(request.getSession().getAttribute(Constants.MOBILE))
               || null == verifyCode || !verifyCode.equals(request.getSession().getAttribute(Constants.VERIFYCODE))) {
                return new WebModel(CodeConstants.MISS, null, "手机号错误或者验证码错误");
            }
        }
        // 检测是不是存在上传文件
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(isMultipart) {
            Map<String, MultipartFile> fileMap = FileCenterClient.buildMultipartFile(request);
            //身份证照片
            MultipartFile idCardFile = fileMap.get("idCardFile");
            MultipartFile radioUploadFile = fileMap.get("radioUploadFile");
            MultipartFile followerFile = fileMap.get("followerFile");
            if(null != idCardFile) {
                String path = FileCenterClient.upload(idCardFile, FileConstants.FILE_TYPE_USER_IDENTIFY);
                user.getUserIdentityInfo().setCheckStatus(Constants.CHECK_STATUS_READY);
                user.getUserIdentityInfo().setIdCardImg(path);
            }
            if(null != radioUploadFile) {
                String path = FileCenterClient.upload(radioUploadFile, FileConstants.FILE_TYPE_USER_IDENTIFY);
                user.getUserIdentityInfo().setCheckStatus(Constants.CHECK_STATUS_READY);
                user.getUserIdentityInfo().setRadioUploadImg(path);
            }
            if(null != followerFile) {
                String path = FileCenterClient.upload(followerFile, FileConstants.FILE_TYPE_USER_IDENTIFY);
                user.getUserIdentityInfo().setCheckStatus(Constants.CHECK_STATUS_READY);
                user.getUserIdentityInfo().setFollowerImg(path);
            }
        }

        user.setId(uid);
        userService.updateByIdSelective(user);
        //更新session
        currentUser = userService.findById(user.getId());
        request.getSession().setAttribute(Constants.CURRENT_USER, currentUser);
        return new WebModel(CodeConstants.SUCCESS, ReflectionUtil.beanToMap(user));
    }

    /**
     * 修改密码
     * @param uid 用户ID
     * @param password 旧密码
     * @param password1 新密码
     * @param password2 新密码
     * @param request
     * @return
     */
    @RequestMapping(value = "{uid}/password", method = RequestMethod.PATCH)
    @ResponseBody
    public WebModel updatePassword(@PathVariable("uid") Integer uid, @RequestParam("password") String password,
                                   @RequestParam("password1") String password1, @RequestParam("password2") String password2, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        if(currentUser.getPassword().equals(password)) {
            if(null != password1 && password1.equals(password2)) {
                User updateUser = new User();
                updateUser.setId(uid);
                updateUser.setPassword(password1);
                userService.updateByIdSelective(updateUser);
            } else {
                return new WebModel(CodeConstants.MISS, null, "密码不一致");
            }
        } else {
            return new WebModel(CodeConstants.MISS, null, "旧密码错误");
        }
        return new WebModel(CodeConstants.SUCCESS, null);
    }


    @RequestMapping("{uid}/identity")
    public String identity(@PathVariable("uid") Integer uid) {
        return "user/identity";
    }

    @RequestMapping("{uid}/enter")
    public String enter(@PathVariable("uid") Integer uid) {
        return "user/enter";
    }

}
