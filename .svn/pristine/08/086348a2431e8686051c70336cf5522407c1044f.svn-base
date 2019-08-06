package com.business.app.restHeartRate;

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

/**
 * Created by edward on 2016/9/5.
 */
@Api(value = "RestHeartRate", description = "静心心率")
@RequestMapping("rest-heart-rate")
@Controller
public class RestHeartRateController extends BaseControllerSupport {

    @Autowired
    private RestHeartRateService restHeartRateService;

    /**
     * 保存静息心率
     *
     * @param uid 用户编号
     * @param heartRateVal 心率值
     * @param detectTime 检测时间
     */
    @ApiOperation(value = "保存静息心率", notes = "保存静息心率", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("add")
    public void save(Model model,
            @ApiParam(required = true, value = "用户编号") Integer uid ,
                     @ApiParam(required = true, value = "心率值") Integer heartRateVal,
                     @ApiParam(required = true, value = "检测时间") Long detectTime) {
        model.addAttribute("detectTime", restHeartRateService.save(uid, heartRateVal, detectTime));
    }

    /**
     * 删除静息心率
     *
     * @param id 删除
     * @param uid 用户编号
     */
    @ApiOperation(value = "删除静息心率", notes = "删除静息心率", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("remove")
    public void remove(@ApiParam(required = true, value = "静息心率编号") Long id,
                       @ApiParam(required = true, value = "用户编号") Integer uid) {
        restHeartRateService.remove(id, uid);
    }

    /**
     * 用户静息心率列表
     *
     * @param uid 用户编号
     * @param index 页码
     * @param isAll 是否是全列表 [ 1=全列表、0=非全列表 ]
     */
    @ApiOperation(value = "用户静息心率列表", notes = "用户静息心率列表", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("list")
    public void list(@ApiIgnore Model model,
                     @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                     @ApiParam(required = true, value = "页码") @RequestParam("index") Integer index,
                     @ApiParam(required = true, value = "是否是全列表 [ 1=全列表、0=非全列表 ]") @RequestParam(value = "isAll", defaultValue = "0") Integer isAll) {
        model.addAttribute("list", restHeartRateService.page(index, uid, isAll));
    }
}
