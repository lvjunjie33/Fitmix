package com.business.work.userRunShareResource;

import com.business.core.entity.Page;
import com.business.core.entity.user.UserRunShareResource;
import com.business.work.base.constants.CodeConstants;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by sin on 2016/1/14.
 */
@Controller
@RequestMapping("user-run-share-resource")
public class UserRunShareResourceController extends BaseControllerSupport {

    @Autowired
    private UserRunShareResourceService userRunShareResourceService;


    /**
     * 查询 Page 资源分享
     * @param pageType 类型：1、内容 2、图片
     * @param page 分页信息
     */
    @RequestMapping("page/{pageType}")
    public String page(@PathVariable(value = "pageType") String pageType, Page<UserRunShareResource> page) {
        page.convertInt("id", "type");
        String resultPage;
        if (pageType.equals("content")) {
            page.getFilter().put("type", UserRunShareResource.TYPE_CONTENT);
            resultPage = "userRunResource/content-page";
        }
        else {
            page.getFilter().put("type", UserRunShareResource.TYPE_IMAGE);
            resultPage = "userRunResource/image-page";
        }
        userRunShareResourceService.page(page);
        return resultPage;
    }


    /**
     * 添加 分享资源信息
     * <result>
     *     0、成功 1、内容不能为空 2、图片不能为空
     * </result>
     *
     * @param image 图片
     * @param content 内容
     * @param type 类型：{@link UserRunShareResource#type}
     */
    @RequestMapping("add")
    public void add(Model model,
                    @RequestParam(value = "image", required = false) MultipartFile image,
                    @RequestParam(value = "content", required = false) String content,
                    @RequestParam("type") Integer type) {
        int result = userRunShareResourceService.add(image, content, type);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.USER_RUN_SHARE_RESOURCE_CONTENT_NOT_EXIST, "内容资源不存在");
                break;
            case 2:
                tip(model, CodeConstants.USER_RUN_SHARE_RESOURCE_IMAGE_NOT_EXIST, "图片资源不存在");
                break;
        }
    }


    /**
     * 修改 资源图片
     * <result>
     *     0、成功 1、资源不存在 2、修改的资源不能为空
     * </result>
     * @param id 编号
     * @param image 图片
     */
    @RequestMapping("modify-image")
    public void modifyImage(Model model,
                            @RequestParam("id") Integer id,
                            @RequestParam("image") MultipartFile image) {
        int result = userRunShareResourceService.modifyImage(id, image);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.USER_RUN_SHARE_RESOURCE_NOT_EXIST, "资源不存在");
                break;
            case 2:
                tip(model, CodeConstants.USER_RUN_SHARE_RESOURCE_IMAGE_NOT_EXIST, "图片资源不存在");
                break;
        }
    }

    /**
     * 修改 资源内容
     * <result>
     *     0、成功 1、资源不存在 2、资源内容不能为空
     * </result>
     *
     * @param id 编号
     * @param content 资源内容信息
     */
    @RequestMapping("modify-content")
    public void modifyContent(Model model,
                            @RequestParam("id") Integer id,
                            @RequestParam("content") String content) {
        int result = userRunShareResourceService.modifyContent(id, content);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.USER_RUN_SHARE_RESOURCE_NOT_EXIST, "资源不存在");
                break;
            case 2:
                tip(model, CodeConstants.USER_RUN_SHARE_RESOURCE_CONTENT_NOT_EXIST, "内容资源不存在");
                break;
        }
    }

    /**
     * 修改 资源状态
     * <result>
     *     0、成功 1、资源不存在
     * </result>
     *
     * @param id 编号
     * @param status 状态
     */
    @RequestMapping("modify-status")
    public void modifyStatus(Model model,
                             @RequestParam("id") Integer id,
                             @RequestParam("status") Integer status) {
        int result = userRunShareResourceService.modifyStatus(id, status);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.USER_RUN_SHARE_RESOURCE_NOT_EXIST, "资源不存在");
                break;
        }
    }
}
