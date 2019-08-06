package com.business.app.radio;

import com.business.app.base.constants.Constants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.mix.MixService;
import com.business.core.entity.Page;
import com.business.core.entity.mix.Mix;
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

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fenxio on 2016/5/22.
 * 电台接口
 */
@Api(value = "radio", description = "电台接口")
@Controller
@RequestMapping("radio")
public class RadioController extends BaseControllerSupport {

    @Autowired
    private MixService mixService;

    /**
     * 电台 场景列表首页
     * @param type 类型 1：运动歌单 2：电台
     */
    @ApiOperation(value = "scene-list", notes = "电台首页推荐", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("scene-list")
    public void sceneList(HttpServletRequest request,@ApiIgnore Model model,
                          @ApiParam(required = true, value = "类型 2：电台") @RequestParam("type") Integer type) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        model.addAttribute("list", mixService.sceneList(language,type));
    }

    /**
     * 场景 数据分页
     * @param type 分类
     * @param sortType 排序类型
     * @param scene 场景
     * @param index 第几页
     */
    @ApiOperation(value = "scene-page", notes = "电台场景歌曲列表", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("scene-page")
    public void scenePage(HttpServletRequest request, @ApiParam(required = false, value = "model")Model model,
                          @ApiParam(required = true, value = "类型 2：电台") @RequestParam("type") Integer type,
                          @ApiParam(required = true, value = "排序类型") @RequestParam("sortType") Integer sortType,
                          @ApiParam(required = true, value = "场景") @RequestParam("scene") Integer scene,
                          @ApiParam(required = true, value = "页码") @RequestParam("index") Integer index) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        Page<Mix> mixList = mixService.scenePage(language,type, scene, index, null, sortType);
        model.addAttribute("page", mixList);
    }

}
