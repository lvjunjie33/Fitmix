package com.business.app.liveShare;

import com.business.core.entity.SysParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/6/19 0019.
 */
@Api(value = "live-share", description = "直播")
@Controller
@RequestMapping("live-share")
public class LiveShareController {

    @ApiOperation(value = "share", notes = "分享", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("share")
    public void share (@ApiParam Model model,
                       @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                       @ApiParam(required = true, value = "歌曲编号") @RequestParam("mid") Integer mid,
                       @ApiParam(required = true, value = "模式,1=室内，2=室外") @RequestParam(value = "mo", defaultValue = "1") Integer mo) {
        model.addAttribute("url", String.format("%s?uid=%s&mid=%s&mo=%s", SysParam.INSTANCE.APP_USER_LIVE_SHARE_DNS, uid, mid, mo));
    }
}
