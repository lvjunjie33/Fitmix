package com.business.work.mixAuthor;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.mix.MixAuthor;
import com.business.core.operations.mixAuthor.MixAuthorCoreService;
import com.business.work.base.constants.CodeConstants;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sin on 2015/8/4 0004.
 */
@Controller
@RequestMapping("mix-author")
public class MixAuthorController extends BaseControllerSupport {

    @Autowired
    private MixAuthorCoreService mixAuthorCoreService;
    @Autowired
    private MixAuthorService mixAuthorService;

    /**
     * MixAuthor 分页
     * @param page 分页工具
     */
    @RequestMapping("page-mix-author")
    public String pageMixAuthor(Page<MixAuthor> page) {
        page.convertInt("id");
        mixAuthorService.pageMixAuthor(page);
        return "mixAuthor/page-mix-author";
    }

    @RequestMapping("info-mix-author")
    public String infoMixAuthor(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("mixAuthor", mixAuthorCoreService.findMixAuthor(id));
        return "mixAuthor/info-mix-author";
    }

    /**
     * 添加 MixAuthor 页面跳转
     */
    @RequestMapping(value = "add-mix-author", method = RequestMethod.GET)
    public String addMixAuthor() {
        return "mixAuthor/add-mix-author";
    }

    /**
     * 添加 MixAuthor
     * @param name 名称
     * @param introduce 简介
     * @param introduceDetail 详细简介
     * @return 0、成功 1、名称重复
     */
    @RequestMapping(value = "add-mix-author", method = RequestMethod.POST)
    public void addMixAuthor(Model model,
                             @RequestParam("name") String name,
                             @RequestParam("introduce") String introduce,
                             @RequestParam("introduceDetail") String introduceDetail) {
        int result = mixAuthorService.addMixAuthor(name, introduce, introduceDetail);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.MIX_AUTHOR_NAME_REPEAT, "author 名称重复");
                break;
        }
    }

    /**
     * 上传头像
     */
    @RequestMapping(value = "upload-img", method = RequestMethod.GET)
    public String uploadImg(Model model, @RequestParam("id") Integer id) {
        model.addAttribute(mixAuthorCoreService.findMixAuthor(id));
        return "mixAuthor/upload-img";
    }

    /**
     * 上传头像
     * @param file 头像
     * @param id author编号
     */
    @RequestMapping(value = "upload-avatar", method = RequestMethod.POST)
    public void uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
        String url = FileCenterClient.upload(file, FileConstants.FILE_TYPE_MIX_AUTHOR_AVATAR);
        mixAuthorCoreService.uploadAvatar(id, url);
    }

    /**
     * 删除相册
     * @param url 文件地址
     */
    @RequestMapping("remove-photos")
    public void removePhotos(@RequestParam("id") Integer id, @RequestParam("url") String url) {
        FileCenterClient.removeFile(url);
        mixAuthorCoreService.removePhotos(id, url);
    }

    /**
     * 上传相册
     * @param request 多个Photos
     * @param id author编号
     */
    @RequestMapping("upload-photos")
    public void uploadPhotos(HttpServletRequest request, @RequestParam("id") Integer id) {
        List<String> photos = FileCenterClient.uploadFiles(request, FileConstants.FILE_TYPE_MIX_AUTHOR_PHOTOS);
        mixAuthorCoreService.uploadPhotos(id, photos);
    }

    /**
     * 删除作者
     * @param id 用户编号
     */
    @RequestMapping("remove-mix-author")
    public void removeMixAuthor(@RequestParam("id") Integer id) {
        mixAuthorService.removeMixAuthor(id);
    }

    /**
     * 修改 Author 基本资料 ：页面跳转
     */
    @RequestMapping(value = "modify-mix-author", method = RequestMethod.GET)
    public String modifyMixAuthor(Model model, @RequestParam("id") Integer id) {
        model.addAttribute(mixAuthorCoreService.findMixAuthor(id));
        return "mixAuthor/modify-mix-author";
    }

    /**
     * 修改 Author 基本资料
     * @param mixAuthor 歌曲作者信息
     * @return 0、成功 1、名称重复
     */
    @RequestMapping(value = "modify-mix-author", method = RequestMethod.POST)
    public void modifyMixAuthor(Model model, MixAuthor mixAuthor) {
       int result = mixAuthorService.modifyMixAuthor(mixAuthor);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.MIX_AUTHOR_NAME_REPEAT, "author 名称重复");
                break;
        }
    }
}
