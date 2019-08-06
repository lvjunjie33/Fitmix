package com.business.app.userSkipRope;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.user.UserHeartRate;
import com.business.core.entity.user.UserRun;
import com.business.core.utils.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by edward on 2016/5/23.
 */
@Api(value = "UserSkipRope", description = "用户跳绳接口")
@Controller
@RequestMapping("user-skip-rope")
public class UserSkipRopeController extends BaseControllerSupport {

    @Autowired
    private UserSkipRopeService userSkipRopeService;

    /**
     * 记录跳绳记录
     *
     * @param uid       用户编号
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param calorie   卡路里
     * @param runTime   运动时间
     * @param skipNum   跳跃次数
     * @param bpm       平均频率
     * @param bpmMatch  bpm匹配度
     * @param type      运动类型(0跑步、1骑行、2跳绳之类的)
     * @param consumeFat 燃烧脂肪量 {@link UserRun#consumeFat}
     */
    @ApiOperation(value = "记录跳绳记录", notes = "记录跳绳记录", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("/{version}/add")
    public void addSkipRope(@ApiIgnore Model mode, HttpServletRequest request,
                            @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                            @ApiParam(required = true, value = "开始时间") @RequestParam("startTime") Long startTime,
                            @ApiParam(required = true, value = "结束时间") @RequestParam("endTime") Long endTime,
                            @ApiParam(required = false, value = "卡路里") @RequestParam(value = "calorie", required = false) Long calorie,
                            @ApiParam(required = true, value = "运动时间") @RequestParam("runTime") Long runTime,
                            @ApiParam(required = true, value = "跳跃次数") @RequestParam("skipNum") Long skipNum,
                            @ApiParam(required = true, value = "平均频率") @RequestParam("bpm") Integer bpm,
                            @ApiParam(required = true, value = "bpm匹配度") @RequestParam("bpmMatch") Double bpmMatch,
                            @ApiParam(required = true, value = "运动类型(0跑步、1骑行、2跳绳之类的)") @RequestParam("type") Integer type,
                            @RequestParam(value = "consumeFat", required = false) Double consumeFat,
                            @ApiParam(required = false, value = "心率") @RequestParam(value = "heartRate", required = false) String userHeartRate,
                            @ApiParam(required = true, value = "版本") @PathVariable("version") Integer version) {

        UserHeartRate heartRate = null;
        if (!StringUtils.isEmpty(userHeartRate)) {
            //TODO 关闭跳绳心率入口 to edward
//            heartRate = JSON.parseObject(userHeartRate, UserHeartRate.class);
        }

        String sdk = HttpUtil.getParameter(request, "_sdk");//sdk 设备类型
        Object[] result = userSkipRopeService.addSkipRope(FileCenterClient.buildMultipartFile(request), uid, startTime, endTime,
                calorie, runTime, skipNum, bpm, bpmMatch, type, consumeFat, heartRate, sdk.contains("iPhone"));

        switch ((int) result[0]) {
            case 0:
            case 2:
                mode.addAttribute("userSkipRope", result[1]);
                mode.addAttribute("lastAddTime", ((UserRun) result[1]).getAddTime());
                break;
            case 1:
                tip(mode, CodeConstants.USER_SKIP_ROPE_DETAIL_NOT_UPLOAD);
                break;
        }
    }

    /**
     * 删除跳绳记录
     *
     * @param uid       用户编号
     * @param startTime 跳绳开始时间
     */
    @ApiOperation(value = "删除跳绳记录（单个）", notes = "删除跳绳记录（单个）", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("remove")
    public void removeSkipRope(@ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                               @ApiParam(required = true, value = "跳绳开始时间") @RequestParam("startTime") Long startTime) {
        userSkipRopeService.removeSkipRope(uid, startTime);
    }

    /**
     * 删除多个跳绳记录
     *
     * @param uid       用户编号
     * @param startTime 跳绳开始时间 多个（, 号分割  ）
     */
    @ApiOperation(value = "删除多个跳绳记录", notes = "删除多个跳绳记录", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("remove-more")
    public void removeSkipRopes(@ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                                @ApiParam(required = true, value = "跳绳开始时间 多个（, 号分割  ）") @RequestParam("startTime") Long[] startTime) {
        userSkipRopeService.removeSkipRopes(uid, startTime);
    }

    /**
     * 用户跳绳查询 or  lastLoginTime 空则返回 所有
     *
     * @param uid         用户编号
     * @param lastAddTime 分页
     */
    @ApiOperation(value = "用户跳绳查询", notes = "用户跳绳查询", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("history")
    public void historyRun(Model model,
                           @ApiParam(required = true, value = "用户编号") @RequestParam(value = "uid", required = true) Integer uid,
                           @ApiParam(required = true, value = "最后跟新时间") @RequestParam(value = "lastAddTime", required = false) Long lastAddTime) {
        Object[] objects = userSkipRopeService.historySkipRope(uid, lastAddTime);
        if (lastAddTime != null) {
            model.addAttribute("new", objects[0]);
            model.addAttribute("remove", objects[1]);
            // 数据为空时返回 传入的 lastAddTime，否则给最后更新记录时间
            UserRun lastUpdateUserSkipRope = (UserRun) objects[2];
            if (null != objects[1] && lastUpdateUserSkipRope != null && null != lastUpdateUserSkipRope.getUpdateTime()) {
                model.addAttribute("lastAddTime", lastUpdateUserSkipRope.getUpdateTime());
            } else {
                model.addAttribute("lastAddTime", lastAddTime);
            }
            model.addAttribute("lastAddTime", (lastUpdateUserSkipRope == null || null == lastUpdateUserSkipRope.getUpdateTime()) ? lastAddTime : lastUpdateUserSkipRope.getUpdateTime());
        } else {
            model.addAttribute("new", objects[0]);
            // 第一次更新 lastAddTime 为空则 给服务器时间，否则给最后更新记录时间
            UserRun lastUpdateUserSkipRope = (UserRun) objects[1];
            if (null != objects[1] && null != lastUpdateUserSkipRope.getUpdateTime()) {
                model.addAttribute("lastAddTime", lastUpdateUserSkipRope.getUpdateTime());
            } else {
                model.addAttribute("lastAddTime", System.currentTimeMillis());
            }
        }
    }

}
