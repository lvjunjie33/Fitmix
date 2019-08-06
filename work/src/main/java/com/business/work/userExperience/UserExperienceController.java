package com.business.work.userExperience;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.DicConstants;
import com.business.core.entity.Page;
import com.business.core.entity.user.UserExperience;
import com.business.core.utils.DicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sin on 2015/9/7.
 */
@Controller
@RequestMapping("user-experience")
public class UserExperienceController {

    @Autowired
    private UserExperienceService userExperienceService;

    /**
     * 分页
     */
    @RequestMapping("page")
    public String page(Model model, Page<UserExperience> page) {
        page.convertInt("id", "beginExperienceCount", "endExperienceCount");
        model.addAttribute("channelDictionary", JSON.toJSONString(DicUtil.getDictionary(DicConstants.DIC_TYPE_CHANNEL)));
        userExperienceService.page(page);
        return "userExperience/user-experience-page";
    }
}
