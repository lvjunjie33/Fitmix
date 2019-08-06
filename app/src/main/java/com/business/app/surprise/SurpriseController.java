package com.business.app.surprise;

import com.alibaba.fastjson.JSONObject;
import com.business.app.base.constants.Constants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.entity.surprise.Surprise;
import com.business.core.utils.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fenxio on 2016/5/19.
 * update lvjj 2018/4/26
 */
@Api(value = "surprise", description = "彩蛋和天气信息接口")
@Controller
@RequestMapping("surprise")
public class SurpriseController extends BaseControllerSupport {

    @Autowired
    private SurpriseService surpriseService;

    /**
     * 获取彩蛋信息
     * @param uid 用户编号
     * @param cityName 城市名称
     * @param model
     */
    @ApiOperation(value = "获取彩蛋信息", notes = "获取彩蛋信息", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping(value = "get-surprise")
    public void getSurprise(@ApiParam(required = true, value = "用户编号") @RequestParam("uid") Long uid,
                            @ApiParam(required = true, value = "城市名称") @RequestParam("cityName") String cityName,
                            @ApiIgnore Model model) {
        Surprise surprise = surpriseService.findValidSurprise(cityName);
        model.addAttribute("surprise", surprise);
    }

    /**
     * 获取天气信息
     * @param lonlat 经纬度
     * @param model
     */
    @ApiOperation(value = " 获取天气信息", notes = " 获取天气信息", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping(value = "get-weather")
    public void getWeather(HttpServletRequest request,@ApiParam(required = true, value = "经纬度") @RequestParam("lonlat") String lonlat,
                           @ApiIgnore Model model) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        JSONObject jsonObject = surpriseService.findWeather(lonlat,language);
        model.addAttribute("surprise", jsonObject);
    }
}
