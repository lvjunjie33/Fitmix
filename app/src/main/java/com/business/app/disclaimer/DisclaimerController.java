package com.business.app.disclaimer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by wjcaozhi on 16/4/8.
 *
 * app 免责申明
 */
@ApiIgnore
@Controller
public class DisclaimerController {

    /***
     * 免责声明
     * @return
     */
    @RequestMapping("PrivacyStripBox")
    public String disclaimer(){
        return "disclaimer/PrivacyStripBox";
    }

}
