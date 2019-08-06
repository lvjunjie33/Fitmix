package com.business.app.wxRun;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by sin on 2015/10/9.
 * 微信运动数据同步
 */
@Api(value = "WXRun", description = "微信运动数据同步")
@Controller
@RequestMapping("wx-run")
public class WXRunController extends BaseControllerSupport {

    @Autowired
    private WXRunService wxRunService;

    /**
     * 设置步数
     *
     * @param unionid 开放平台唯一标识
     * @param step    步数
     */
    @ApiOperation(value = "设置步数", notes = "设置步数", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("set-step-with-unionid")
    public void setStepWithUnionid(@ApiIgnore Model model, @ApiIgnore HttpServletRequest request,
                                   @ApiParam(required = true, value = "开放平台唯一标识") @RequestParam("unionid") String unionid,
                                   @ApiParam(required = true, value = "步数") @RequestParam("step") Integer step) {
        tip(model, CodeConstants.LOGIN_APP_TO_UPGRADE);
    }


    /**
     * 设置步数
     *
     * @param openid 公众平台唯一标识
     * @param step   步数
     */
    @ApiOperation(value = "设置步数", notes = "设置步数", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("set-step")
    public void setStep(Model model, HttpServletRequest request,
                        @ApiParam(required = false, value = "公众平台唯一标识") @RequestParam(value = "unionid", required = false) String unionid,
                        @ApiParam(required = false, value = "openid") @RequestParam(value = "openid", required = false) String openid,
                        @ApiParam(required = true, value = "步数") @RequestParam("step") Integer step, Integer uid) {

        tip(model, CodeConstants.LOGIN_APP_TO_UPGRADE);
    }

    @ApiOperation(value = "设置步数", notes = "设置步数", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("set/step")
    public void setStepNew(Model model, String parameter) throws Exception {
        Map<String, String> keyVals;
        try {
            keyVals = decodeParameter(parameter);
        } catch (Exception e) {
            tip(model, CodeConstants.WX_SET_STEP_FAIL_USER);
            return;
        }

        if (timestampCheck(keyVals.get("_tp"))) {
            tip(model, CodeConstants.WX_SET_STEP_FAIL_USER);
            return;
        }
        String unionid = keyVals.get("unionid");
        String openid = keyVals.get("openid");
        Integer uid;
        if (keyVals.containsKey("uid")) {
            uid = Integer.parseInt(keyVals.get("uid"));
        } else {
            tip(model, CodeConstants.WX_SET_STEP_ERROR_USER_INFO);
            return;
        }

        Integer step;
        if (keyVals.containsKey("step")) {
            step = Integer.parseInt(keyVals.get("step"));
        } else {
            tip(model, CodeConstants.WX_SET_STEP_ERROR_STEP);
            return;
        }

        if (uid == null) {
            tip(model, CodeConstants.WX_SET_STEP_ERROR_USER_INFO);
            return;
        }
        int result = wxRunService.setStep(uid, unionid, openid, step);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.WX_SET_STEP_ERROR_STEP);
                break;
            case 2:
                tip(model, CodeConstants.WX_SET_STEP_FREQUENT_ACCESS);
                break;
            case 3:
                tip(model, CodeConstants.WX_SET_STEP_ERROR_USER_INFO);
                break;
        }
    }


    ///
    /// 活动特别版

    // TODO sin 整个 特别版可以去掉


    /**
     * 活动 版本 排行榜
     * 特定活动排版，根据版本号区分
     * //     * @param rankingType  0、累计排名 1、当日排名 分为两种排行模式: 版本全部累计模式 和  版本当天累计模式
     */
    @ApiOperation(value = "活动 版本 排行榜", notes = "活动 版本 排行榜", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("activity-team-ranking")
    @Deprecated
    public void activityTeamRanking(@ApiParam(required = false, value = "版本号") @RequestParam(value = "version", required = false) String version, Model model) {
        Object[] result = wxRunService.activityTeamRanking(version);
        switch ((int) result[0]) {
            case 0:
                model.addAttribute("ranking", result[1]);
                break;
        }
    }

    /**
     * 活动 版本 排行榜详细
     * 团中成员排行
     * 0、成功 1、不是活动版本，无法进行活动排名
     */
    @ApiOperation(value = "活动 版本 排行榜详情", notes = "活动 版本 排行榜详情", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("activity-team-ranking-detail")
    @Deprecated
    public void activityTeamRankingDetail(Model model,
                                          @ApiParam(required = true, value = "版本号") @RequestParam("version") String version) {
        Object[] result = wxRunService.activityTeamRankingDetail(version);
        switch ((int) result[0]) {
            case 0:
                model.addAttribute("ranking", result[1]);
                break;
            case 1:
                tip(model, CodeConstants.ACTIVITY_USER_RUN_VERSION_NOT_EXIST, "活动用户版本不存在");
                break;
        }
    }

    /**
     * 活动 版本 排行榜
     * 特定活动排版，根据版本号区分
     * 分为两种排行模式 版本全部累计模式 和  版本当天累计模式
     *
     * @param version 版本号
     * @param type    类型
     */
    @ApiOperation(value = "活动 版本 排行榜", notes = "活动 版本 排行榜", response = String.class, position = 2, httpMethod = "POST")
    @Deprecated
    public void activityTeamUserRankingDetail(@RequestParam("version") String version, @RequestParam("type") Integer type) {

    }

    /* =============================== WX accessToken 监控============================================== */

    /**
     * 获取数据库和Redis中的AccessToken
     */
    @RequestMapping("access-token/info")
    public String accessTokenInfo(Model model) {
        model.addAttribute("info", wxRunService.accessTokenInfo());
        return "wxRun/access-token-info";
    }

    /**
     * 更新Redis 缓存中的AccessToken
     */
    @RequestMapping("update-redis/access-token")
    public String updateRedisAccessToken() {
        wxRunService.updateRedisAccessToken();
        return redirect("wx-run/access-token/info.htm");
    }

    /**
     * 去微信获取最新的AccessToken，并保存到数据库
     */
    @RequestMapping("to-wx/get-new-access-token")
    public String toWXGetNewAccessToken() {
        wxRunService.toWXGetNewAccessToken();
        return redirect("wx-run/access-token/info.htm");
    }
}
