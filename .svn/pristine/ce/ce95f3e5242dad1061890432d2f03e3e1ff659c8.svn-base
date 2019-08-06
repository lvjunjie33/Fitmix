package com.business.app.mixBanner;

import com.business.app.base.constants.Constants;
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
 * Created by Sin on 2016/3/8.
 *
 * mix banner 信息
 */
@Api(value = "mix-banner", description = "歌曲Banner")
@Controller
@RequestMapping("mix-banner")
public class MixBannerController {

    @Autowired
    private MixBannerService mixBannerService;

    /**
     * mix banner 列表
     */
    @ApiOperation(value = "list", notes = "Banner 列表", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("list")
    public void list(HttpServletRequest request,@ApiIgnore Model model,
                     @ApiParam(required = false, value = "城市") @RequestParam(value = "city", required = false) String city,
                     @ApiParam(required = false, value = "万德智道编号") @RequestParam(value = "wayId", required = false) String wayId) {
        String language = HttpUtil.getParameter(request, Constants.PARAM_LANGUAGE);
        if (city == null || wayId == null) {
            model.addAttribute("data", mixBannerService.list(language));
        } else {// 渠道channel == 万德
            model.addAttribute("data", mixBannerService.list(language,city, wayId));
        }
    }
}
