package com.business.app.userSkipRopeShare;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.user.UserService;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.SysParam;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserSkipRopeShare;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by edward on 2016/5/24.
 */
@Api(value = "UserSkipRopeShare", description = "用户跳绳分享接口")
@Controller
@RequestMapping("user-skip-rope")
public class UserSkipRopeShareController extends BaseControllerSupport {

    @Autowired
    private UserSkipRopeShareService userSkipRopeShareService;
    @Autowired
    private UserService userService;
    ///
    /// 运动分享 (用户分享 确保运动记录已上传，不再保存 UserRunShare)

    /**
     * 用户跳绳 分享添加
     *
     * @param startTime 运动编号
     */
    @ApiIgnore
    @ApiOperation(value = "用户跳绳分享添加 (废弃)", notes = "用户跳绳 分享添加", response = String.class, position = 1, httpMethod = "POST")
    @RequestMapping("add-share")
    public void addNew(Model model,
                       HttpServletRequest request,
                       @RequestParam("startTime") Long startTime) {
        Object[] result = userSkipRopeShareService.add(getCurrentUserId(request), startTime);
        switch ((int) result[0]) {
            case 0:
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
                model.addAttribute("share", MessageFormat.format("{0}user-skip-rope/share.htm?startTime={1}&uid={2}", basePath, startTime, getCurrentUserId(request)));
                break;
            case 1:
                tip(model, CodeConstants.USER_SKIP_ROPE_SHARE_DATA_NULL);
                break;
        }
    }

    /**
     * 用户跳绳分享
     *
     * @param uid
     * @param startTime
     * @param file
     * @param model
     */
    @ApiOperation(value = "上传用户分享跳绳记录", notes = "上传用户分享跳绳记录", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("add-share-new")
    public void addShareNew(@ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                            @ApiParam(required = true, value = "运动开始时间") @RequestParam("startTime") Long startTime,
                            @ApiParam(required = true, value = "跳绳文件") @RequestParam("file") MultipartFile file,
                            @ApiIgnore Model model,
                            @ApiIgnore HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        //1.先判断 是否已经分享过
        UserSkipRopeShare userSkipRopeShare = userSkipRopeShareService.findUserSkipRopeShareByIdAndStartTime(uid, startTime);
        if (null == userSkipRopeShare) {
            String imgUrl = FileCenterClient.upload(file, FileConstants.FILE_TYPE_USER_SKIP_ROPE_SHARE);
            userSkipRopeShare = new UserSkipRopeShare();
            userSkipRopeShare.setAddTime(System.currentTimeMillis());
            userSkipRopeShare.setUid(uid);
            userSkipRopeShare.setStartTime(startTime);
            userSkipRopeShare.setBrowseCount(0);
            userSkipRopeShare.setImgUrl(imgUrl);
            userSkipRopeShareService.saveUserSkipRopeShare(userSkipRopeShare);
        }
        model.addAttribute("share", MessageFormat.format("{0}user-skip-rope/share.htm?startTime={1}&uid={2}", basePath, startTime + "", uid + ""));
    }

    /**
     * 用户跳绳 分享
     *
     * @param startTime 运动编号
     */
    @ApiOperation(value = "用户跳绳分享页面", notes = "用户跳绳分享页面", response = String.class, position = 3, httpMethod = "GET")
    @RequestMapping("share")
    public String share(@ApiIgnore Model model,
                        @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                        @ApiParam(required = true, value = "运动开始时间") @RequestParam("startTime") Long startTime) {
        User user = userService.findUserById(uid, "uid", "avatar", "name");
        if (null != user) {
            user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
        }
        UserSkipRopeShare userSkipRopeShare = userSkipRopeShareService.findUserSkipRopeShareByIdAndStartTime(uid, startTime);
        if (null != userSkipRopeShare) {
            userSkipRopeShare.setImgUrl(FileCenterClient.buildUrl(userSkipRopeShare.getImgUrl()));
        }
        model.addAttribute("user", user);
        model.addAttribute("share", userSkipRopeShare);
        model.addAttribute("AndroidDownLoad", SysParam.INSTANCE.APP_USER_RUN_SHARE_DOWNLOAD_ANDROID);
        model.addAttribute("IOSDownLoad", SysParam.INSTANCE.APP_USER_RUN_SHARE_DOWNLOAD_IOS);
        return "userSkipRopeShare/user-skip-rope-share";
    }
}
