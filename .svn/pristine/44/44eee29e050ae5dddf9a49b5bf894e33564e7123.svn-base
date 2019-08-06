package com.business.work.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.SysParam;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.StaticCacheUtil;
import com.business.work.base.constants.CodeConstants;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/8/28.
 */
@Controller
@RequestMapping("app")
public class AppController extends BaseControllerSupport {

    @Autowired
    private AppService appService;

    /**
     * app 首页背景图
     */
    @RequestMapping("home-background-image")
    public String homeBackgroundImage(Model model) {
        JSONArray jsonArray = JSON.parseArray(SysParam.INSTANCE.APP_HOME_BACKGROUND_IMAGE);
        model.addAttribute("homeImages", jsonArray);
        return "app/home-background-image";
    }

    /**
     * 设置 app 首页背景图
     *
     * @param file 图片文件
     */
    @RequestMapping("set-home-background-image")
    public void setHomeBackgroundImage(Model model,
                                       @RequestParam("file") MultipartFile file,
                                       @RequestParam("version") String version) {
        JSONArray jsonArray = JSON.parseArray(SysParam.INSTANCE.APP_HOME_BACKGROUND_IMAGE);
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            if (version.equals(jsonObject.get("version").toString())) {
                if (FileCenterClient.removeFile(jsonObject.get("imageUrl").toString())) { // 删除 旧文件
                    String fileUrl = FileCenterClient.upload(file, FileConstants.FILE_TYPE_APP_BACKGROUND_IMAGE);  // 上传新文件
                    jsonObject.put("imageUrl", fileUrl);
                    appService.setHomeBackgroundImage(JSONObject.toJSONString(jsonArray)); // 设置缓存 新闻界
                    // 刷新 app 缓存
                    if (!StaticCacheUtil.refreshSysParam()) { // TODO 改换成 redis 缓存)
                        tip(model, CodeConstants.REFRESH_SYS_PARAM_CACHE_ERROR, "刷新系统缓存失败");
                    }
                    model.addAttribute("fileUrl", FileCenterClient.buildUrl(fileUrl));
                }
            }
        }
    }

    /**
     * 添加
     *
     * @param version
     * @return
     */
    @RequestMapping("add-home-background-image")
    public String addHomeBackgroundImage(@RequestParam("version") String version) {
        JSONArray jsonArray = JSON.parseArray(SysParam.INSTANCE.APP_HOME_BACKGROUND_IMAGE);
        JSONObject newJsonObject = new JSONObject();
        newJsonObject.put("version", version);
        newJsonObject.put("imageUrl", "");
        jsonArray.add(newJsonObject);
        appService.setHomeBackgroundImage(JSONObject.toJSONString(jsonArray)); // 设置缓存 新闻界
        // 刷新 app 缓存
        StaticCacheUtil.refreshSysParam();
        return "redirect:home-background-image.htm";
    }

    /**
     * 删除
     *
     * @param version
     * @return
     */
    @RequestMapping("remove-home-background-image")
    public String removeHomeBackgroundImage(@RequestParam("version") String version) {
        JSONArray jsonArray = JSON.parseArray(SysParam.INSTANCE.APP_HOME_BACKGROUND_IMAGE);

        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            if (version.equals(jsonObject.get("version").toString())) {
                if (FileCenterClient.removeFile(jsonObject.get("imageUrl").toString())) { // 删除 旧文件
                    jsonArray.remove(object);
                    appService.setHomeBackgroundImage(JSONObject.toJSONString(jsonArray)); // 设置缓存 新闻界
                    // 刷新 app 缓存
                    StaticCacheUtil.refreshSysParam();
                    break;
                }
            }
        }
        return "redirect:home-background-image.htm";
    }
}
