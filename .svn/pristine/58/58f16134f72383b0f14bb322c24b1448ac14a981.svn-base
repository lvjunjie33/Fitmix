package com.business.live.live;

import com.business.core.constants.ApplicationConfig;
import com.business.core.operations.wx.WXGetUserInfoKeyCodeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sin on 2015/6/19 0019.
 */
@Controller
public class LiveController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LiveService liveService;
    @Autowired
    private WXGetUserInfoKeyCodeDao wxGetUserInfoKeyCodeDao;

    @RequestMapping("live")
    public String live (Model model, @RequestParam Integer uid, @RequestParam Integer mid, @RequestParam Integer mo) {
        model.addAttribute("socketMessage", liveService.live(uid, mid));
        model.addAttribute("host", ApplicationConfig.LIVE_WEB_SOCKET_URL);
        model.addAttribute("mo", mo);
        model.addAttribute("key", wxGetUserInfoKeyCodeDao.findWXGetUserInfoKey());
        return "live";
    }

}
