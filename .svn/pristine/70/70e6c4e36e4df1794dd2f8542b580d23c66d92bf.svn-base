package com.business.app.stat;

import com.business.core.utils.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sin on 2015/6/11 0011.
 */
@Api(value = "Stat", description = "用户设备信息")
@Controller
@RequestMapping("stat")
public class StatController {

    @Autowired
    private StatService statService;

    /**
     * 用户设备信息
     *
     * uid 和 udid 区分是否是 试用用户
     *
     * @param uid 用户编号
     * @param udid 设备编号
     * @param version app 版本
     * @param operators 运营商
     * @param sdk 手机系统 SDK
     * @param channel 渠道
     * @param mobileType 手机型号
     * @param idfa IOS渠道推广
     * @param deviceToken 设备信息
     * @param unionid 微信唯一标识（用于跟 openid 授权）
     * @param appid qq唯一标识（用于跟 openid 授权）
     */
    @ApiOperation(value = "用户设备信息", notes = "用户设备信息", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("user-devices-info")
    public void userDevicesInfo (@ApiIgnore HttpServletRequest request,
                                 @ApiParam(required = false, value = "用户编号") @RequestParam(value = "uid", required = false) Integer uid,
                                 @ApiParam(required = false, value = "设备号") @RequestParam(value = "udid", required = false) String udid, //　TODO version 4 兼容老版本，几个版本后 (必填）
                                 @ApiParam(required = true, value = "版本") @RequestParam String version,
                                 @ApiParam(required = true, value = "运营商") @RequestParam String operators,
                                 @ApiParam(required = true, value = "手机系统") @RequestParam String sdk,
                                 @ApiParam(required = true, value = "渠道") @RequestParam String channel,
                                 @ApiParam(required = true, value = "手机型号") @RequestParam String mobileType,
                                 @ApiParam(required = false, value = "iOS 渠道推广") @RequestParam(value = "idfa", required = false, defaultValue = "") String idfa,  // ios 独有  用户与接入  爱思
                                 @ApiParam(required = false, value = "设备信息") @RequestParam(value = "deviceToken", required = false, defaultValue = "") String deviceToken,
                                 @ApiParam(required = false, value = "微信唯一标识") @RequestParam(value = "unionid", required = false, defaultValue = "") String unionid,
                                 @ApiParam(required = false, value = "qq唯一标识") @RequestParam(value = "appid", required = false, defaultValue = "") String appid) {
        statService.userDevicesInfo(uid, udid, version, operators, sdk, channel, mobileType, HttpUtil.getIP(request), deviceToken, idfa, unionid, appid);
    }

    /**
     * 用户行为上传
     * @param uid 用户编号
     * @param type 行为情况 1、音乐  2、运动
     * @param lng 经度
     * @param lat 纬度
     */
    @ApiOperation(value = "用户行为上传", notes = "用户行为上传", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("user-behavior")
    public void userBehavior(@ApiParam(required = true, value = "用户编号") @RequestParam Integer uid,
                             @ApiParam(required = true, value = "行为情况 1、音乐  2、运动") @RequestParam Integer type,
                             @ApiParam(required = false, value = "经度") @RequestParam(required = false) Double lng,
                             @ApiParam(required = false, value = "纬度") @RequestParam(required = false) Double lat) {
        statService.userBehavior(uid, type, lng, lat);
    }
}
