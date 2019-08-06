package com.business.app.mixAuthor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collection;

/**
 * Created by sin on 2015/8/7 0007.
 * mix 作者
 */
@Api(value = "mix-author", description = "歌曲作者")
@Controller
@RequestMapping("mix-author")
public class MixAuthorController {

    @Autowired
    private MixAuthorService mixAuthorService;

    /**
     * mix 歌曲作者
     * @param ids 作者编号
     */
    @ApiOperation(value = "info", notes = "详情", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("info")
    public void info(@ApiIgnore Model model,
                     @ApiParam(required = true, value = "歌曲编号列表") @RequestParam("ids") Collection<Integer> ids) {
        model.addAttribute("author", mixAuthorService.info(ids));
    }
}
