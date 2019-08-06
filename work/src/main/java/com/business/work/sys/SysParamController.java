package com.business.work.sys;

import com.business.core.constants.ApplicationConfig;
import com.business.core.mongo.DefaultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/29.
 */
@Controller
@RequestMapping()
public class SysParamController {

    @Autowired
    private SysParamService sysParamService;

    @RequestMapping("sys/manager")
    public String manager() {
        return "sys/manager";
    }

    @RequestMapping("sys/list")
    public String list (Model model) {
        model.addAttribute("paramList", sysParamService.sysParamList());
        model.addAttribute("refreshUrls", ApplicationConfig.APP_SYS_PARAM_URL);
        return "sys/list";
    }

    /**
     * app版本相关的数据字典
     */
    @RequestMapping("sys/app/version")
    public String appVersion(Model model) {
        List<Map<String, Object>> list = sysParamService.sysParamList();
        List<Map<String, Object>> appVersions = new ArrayList<>();
        for (Map<String, Object> map : list) {
            if(map.get("name").equals("APP_IOS_UPGRADE_VERSION")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("APP_ANDROID_UPGRADE_VERSION")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("APP_ROC_ANDROID_UPGRADE_VERSION")) {
                appVersions.add(map);
                continue;
            }

            if(map.get("name").equals("APP_IOS_UPGRADE_VERSION_VIEW")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("APP_ANDROID_UPGRADE_VERSION_VIEW")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("APP_ROC_ANDROID_UPGRADE_VERSION_VIEW")) {
                appVersions.add(map);
                continue;
            }

            if(map.get("name").equals("APP_IOS_UPGRADE_VERSION_INTRODUCTION_EN")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("APP_ANDROID_UPGRADE_VERSION_INTRODUCTION_EN")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("APP_ROC_ANDROID_UPGRADE_VERSION_INTRODUCTION_EN")) {
                appVersions.add(map);
                continue;
            }

            if(map.get("name").equals("APP_IOS_UPGRADE_VERSION_INTRODUCTION")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("APP_ANDROID_UPGRADE_VERSION_INTRODUCTION")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("APP_ROC_ANDROID_UPGRADE_VERSION_INTRODUCTION")) {
                appVersions.add(map);
                continue;
            }

            if(map.get("name").equals("APP_IOS_UPGRADE_URL")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("APP_ANDROID_UPGRADE_URL")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("APP_ROC_ANDROID_UPGRADE_URL")) {
                appVersions.add(map);
                continue;
            }

            if(map.get("name").equals("APP_ROC_ANDROID_UPGRADE_URL")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("TIMESTAMP_CHECK_SIZE")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("UPGRADE_CONTENT")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("UPGRADE_ROC_CONTENT")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("SWITCH_APP_UPGRADE")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("NEWEST_APP_ANDROID_VERSION")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("NEWEST_APP_ROC_ANDROID_VERSION")) {
                appVersions.add(map);
                continue;
            }
        }
        model.addAttribute("paramList", appVersions);
        return "sys/node";
    }

    /**
     * 短信邮件相关的消息模版
     */
    @RequestMapping("sys/message")
    public String message(Model model) {
        List<Map<String, Object>> list = sysParamService.sysParamList();
        List<Map<String, Object>> appVersions = new ArrayList<>();
        for (Map<String, Object> map : list) {
            if(map.get("name").equals("MAIL_USER_RECOVERY_PASSWORD_SUBJECT")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("MAIL_USER_RECOVERY_PASSWORD_TEMPLATE")) {
                appVersions.add(map);
                continue;
            }
            if(map.get("name").equals("MAIL_USER_RECOVERY_VERIFICATION_CODE_TEMPLATE")) {
                appVersions.add(map);
                continue;
            }

            if(map.get("name").equals("MAIL_USER_RECOVERY_VERIFICATION_CODE_TEMPLATE_ROC")) {
                appVersions.add(map);
                continue;
            }
        }
        model.addAttribute("paramList", appVersions);
        return "sys/node";
    }

    @RequestMapping("sys/modify")
    public void modify(@RequestParam("name") String name, @RequestParam("value") String value) {
        sysParamService.modify(name, value);
    }
}
