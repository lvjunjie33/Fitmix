package com.business.work.version;

import com.business.core.client.AliyunCenterClient;
import com.business.core.constants.ApplicationConfig;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.version.Version;
import com.business.work.base.support.BaseControllerSupport;
import com.business.work.sys.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by fenxio on 2016/5/25.
 */
@Controller
@RequestMapping("version")
public class VersionController extends BaseControllerSupport {

    @Autowired
    private VersionService versionService;
    @Autowired
    private SysParamService sysParamService;


    /**
     * 版本信息列表
     * @param page 分页
     * @return
     */
    @RequestMapping("version-list")
    public String versionList(Page<Version> page, Model model) {
        page.convertLong("id");
        versionService.list(page);
        model.addAttribute("refreshUrls", ApplicationConfig.APP_SYS_PARAM_URL);
        return "version/version-list";
    }

    /**
     * 添加版本信息页面
     * @return
     */
    @RequestMapping(value = "version-add", method = RequestMethod.GET)
    public String versionAdd() {
        return "version/version-add";
    }

    /**
     * 添加版本信息
     * @param version
     * @param model
     */
    @RequestMapping(value = "version-add", method = RequestMethod.POST)
    public void versionAdd(Version version, Model model) {
        Object[] result = versionService.versionAdd(version);
        switch ((Integer) result[0]) {
            case 0:
                model.addAttribute("version", result[1]);
                break;
        }
    }

    /**
     * 跳转修改版本页面
     * @param id 编号
     * @param model
     * @return
     */
    @RequestMapping(value = "version-modify", method = RequestMethod.GET)
    public String versionModify(@RequestParam("id") Long id, Model model) {
        model.addAttribute("version", versionService.findVersionById(id));
        return "version/version-modify";
    }

    /**
     * 修改版本基本信息
     * @param version
     */
    @RequestMapping(value = "version-modify", method = RequestMethod.POST)
    public void versionModify(Version version, Model model) {
        Version v = versionService.findVersionById(version.getId());
        if(v.getState() != Version.STATE_2) {
            versionService.versionModify(version);
        } else if(v.getState() == Version.STATE_2) {
            tip(model, 1, "不能修改线上版本信息");
        }
    }


    /**
     * 上传Android 包
     * @param id 编号
     * @param part APK分割下标
     * @param size APK大小
     * @param name APK名称
     * @param partSize 分割大小
     * @param file APK文件
     */
    @RequestMapping(value = "version-modify-file",method = RequestMethod.POST)
    public void versionModifyFile(@RequestParam("id") Long id,
                                @RequestParam("part") Long part,
                                @RequestParam("size") Long size,
                                @RequestParam("name") String name,
                                @RequestParam("partSize") Long partSize,
                                @RequestParam("file") MultipartFile file, Model model){
        Version version = versionService.findVersionById(id);
        if(version.getState() == Version.STATE_1) {
            String fileUrl = AliyunCenterClient.multipartFile(FileConstants.FILE_TYPE_ANDROID_APK.toString(), file, name, size, partSize, part);
            if (partSize * part >= size) {
                versionService.versionModifyUrl(id, fileUrl);
            }
        } else {
            tip(model, 1, "不能修改线上APP地址");
        }


    }

    /**
     * 修改上架状态
     * @param id 编号
     * @param state 上架状态
     */
    @RequestMapping(value = "version-modify-state", method = RequestMethod.POST)
    public void versionModifyState(@RequestParam("id") Long id, @RequestParam("state") Integer state, @RequestParam("type") Integer type, Model model) {
        Version version = versionService.findVersionById(id);
        if(version.getUrl() == null || version.getVersionCode() == null){
            tip(model, 1, "APK文件未设置 或 版本号未设置");
            return;
        }
        // 1.修改上架状态
        versionService.versionModifyState(id, state, type);
        // 2.修改系统参数
        if(type == Version.TYPE_ANDROID && state == Version.STATE_2) {
            sysParamService.modify("APP_ANDROID_UPGRADE_VERSION", version.getVersionCode().toString());
            sysParamService.modify("APP_ANDROID_UPGRADE_URL", version.getUrl());
        } else if(type == Version.TYPE_IOS) {
            // TODO: 2016/5/25 以后增加
        }

    }

}
