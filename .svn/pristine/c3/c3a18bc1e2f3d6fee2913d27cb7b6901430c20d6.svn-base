package com.business.app.userRunShare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.business.app.base.constants.CodeConstants;
import com.business.app.base.constants.Constants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.entity.SysParam;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.UserRunShare;
import com.business.core.utils.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by sin on 2015-05-22.
 */
@Api(value = "UserRunShare", description = "用户运动分享")
@Controller
@RequestMapping("user-run-share")
public class UserRunShareController extends BaseControllerSupport {

    @Autowired
    private UserRunShareService userRunShareService;

    /**
     * 用户分享数据查看
     *
     * @param data 分享运动编号
     * @return 分享数据页面
     */
    @ApiOperation(value = "用户分享数据查看", notes = "用户分享数据查看", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("share")
    public String share(HttpServletRequest request,@ApiIgnore Model model,
                        @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                        @ApiParam(required = true, value = "分享运动编号") @RequestParam("data") String data) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        UserRunShare userRunShear = userRunShareService.share(language,data, uid);
        model.addAttribute("share", userRunShear);
        model.addAttribute("AndroidDownLoad", SysParam.INSTANCE.APP_USER_RUN_SHARE_DOWNLOAD_ANDROID);
        model.addAttribute("IOSDownLoad", SysParam.INSTANCE.APP_USER_RUN_SHARE_DOWNLOAD_IOS);
        return "userRunShare/user-run-share";
    }

    /**
     * 用户运动分享（2.0 修改）
     *
     * @param uid           用户编号
     * @param distance      距离
     * @param runTime       运动时间
     * @param step          步数
     * @param matchingSpeed 配速
     * @param calorie       卡路里
     */
    @ApiOperation(value = "用户运动分享（2.0 修改）", notes = "用户运动分享（2.0 修改）", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("add")
    public void add(@ApiIgnore Model model,
                    @ApiIgnore HttpServletRequest request,
                    @ApiParam(required = true, value = "用户编号") @RequestParam(value = "uid") Integer uid,
                    @ApiParam(required = true, value = "距离") @RequestParam(value = "distance") String distance,
                    @ApiParam(required = true, value = "运动时间") @RequestParam(value = "runTime") String runTime,
                    @ApiParam(required = true, value = "步数") @RequestParam(value = "step") String step,
                    @ApiParam(required = true, value = "配速") @RequestParam(value = "matchingSpeed") String matchingSpeed,
                    @ApiParam(required = true, value = "卡路里") @RequestParam(value = "calorie") String calorie) {
        UserRunShare userRunShare = userRunShareService.add(uid, distance, runTime, step, matchingSpeed, calorie);
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        model.addAttribute("share", String.format("%suser-run-share/share.htm?data=%s&uid=%s", basePath, userRunShare.getId(), uid));
    }


    ///
    /// 新 运动分享 (用户分享 确保运动记录已上传，不再保存 UserRunShare)


    /**
     * 用户运动 分享添加
     *
     * @param startTime 运动编号
     */
    @RequestMapping("add-new")
    public void addNew(Model model, HttpServletRequest request, Long startTime) {
        Object[] result = userRunShareService.addNew(getCurrentUserId(request), startTime);
        switch ((int) result[0]) {
            case 0:
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
                model.addAttribute("share", String.format("%suser-run-share/share-new.htm?startTime=%s&uid=%s", basePath, startTime, getCurrentUserId(request)));
                break;
            case 1:
                tip(model, CodeConstants.USER_RUN_SHARE_NEW_RUN_DATA_NULL);
                break;
        }
    }

    /**
     * 用户运动 分享
     *
     * @param startTime 运动编号
     */
    @ApiOperation(value = "用户运动分享页面", notes = "用户运动分享页面", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("share-new")
    public String shareNew(@ApiIgnore Model model,
                           @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                           @ApiParam(required = true, value = "运动时间") @RequestParam("startTime") Long startTime) {
        UserRun userRun = userRunShareService.shareNew(uid, startTime);
        Boolean isIos = false;
        JSONObject jsonObject = JSON.parseObject(userRun.getStepDetail());
        JSONArray jsonArray = jsonObject.getJSONArray("array");
        if(jsonArray != null && jsonArray.size() > 0) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
            String time = jsonObject1.getString("time");
            if(Long.parseLong(time) < 1000000) {
                isIos = true;
            }
        }
        String name = userRun.getUser().getName();
        if (!StringUtils.isEmpty(name)) {
            name = name.replaceAll("\n", " n");
            name = name.replaceAll("\t", " t");
            name = name.replaceAll("\r", " r");
            name = name.replaceAll("/n", " n");
            name = name.replaceAll("/t", " t");
            name = name.replaceAll("/r", " r");
            userRun.getUser().setName(name);
        }
        model.addAttribute("isIos", isIos);
        model.addAttribute("share", userRun);
        model.addAttribute("AndroidDownLoad", SysParam.INSTANCE.APP_USER_RUN_SHARE_DOWNLOAD_ANDROID);
        model.addAttribute("IOSDownLoad", SysParam.INSTANCE.APP_USER_RUN_SHARE_DOWNLOAD_IOS);
        if (UserRun.TYPE_WATCH_SPORT.equals(userRun.getType())) {
            return "userRunShare/user-run-share-new-2";
        } else {
            return "userRunShare/user-run-share-new-1";
        }
    }
}
