package com.business.app.userEveryDayRun;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by weird on 2016/3/4.
 */
@Api(value = "user-run-everyday", description = "用户昨日运动数据")
@Controller
@RequestMapping("user-run-everyday")
public class UserEveryDayRunController {

    @Autowired
    private UserEveryDayRunService userEveryDayRunService;

    /**
     * 获取用户昨日运动数据
     * @param model
     * @param uid 用户id
     */
    @ApiOperation(value = "获取用户昨日运动数据", notes = "获取用户昨日运动数据", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("userRunInYesterday")
    public void findUserRunInYesterday(@ApiParam Model model,
                                       @ApiParam(required = true, value = "用户编号") @RequestParam(value= "uid", required = true) Integer uid){
        model.addAttribute("userRunInYesterday",userEveryDayRunService.findUserRunInYesterday(uid));
    }
}
