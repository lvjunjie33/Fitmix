package com.business.app.dynamic;

import com.business.app.base.support.BaseControllerSupport;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.dynamic.Comment;
import com.business.core.entity.dynamic.Dynamic;
import com.business.core.entity.dynamic.Flower;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by fenxio on 2016/10/8.
 */
@Api(value = "dynamic", description = "朋友圈动态")
@Controller
@RequestMapping("/dynamic")
public class DynamicController extends BaseControllerSupport {

    @Autowired
    private DynamicService dynamicService;

    /**
     * 发布动态
     * @param uid 用户编号
     * @param type 类型
     * @param content 内容
     * @param permission 权限
     */
    @ApiOperation(value = "add", notes = "发布动态", response = String.class, position = 1, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "图片文件数组", required = false, name = "files", paramType = "MultipartFile")
    })
    @RequestMapping("/add")
    public void add(@ApiParam(value = "用户编号", required = true) @RequestParam("uid") Integer uid,
                    @ApiParam(value = "类型 1：文字，2：图文", required = true) @RequestParam("type") Integer type,
                    @ApiParam(value = "内容", required = true) @RequestParam("content") String content,
                    @ApiParam(value = "权限 1：公开", required = true) @RequestParam("permission") Integer permission,
//                    @ApiParam(value = "图片文件数组", required = false) @RequestParam(value = "files", required = false) MultipartFile[] files,
                    @ApiIgnore HttpServletRequest request,
                    @ApiIgnore Model model) {
        Dynamic dynamic = new Dynamic(uid, type, content, permission);
        if(request instanceof MultipartHttpServletRequest) {
            MultiValueMap<String, MultipartFile> files = ((MultipartHttpServletRequest) request).getMultiFileMap();
            for(String key : files.keySet()) {
                for(MultipartFile multipartFile : files.get(key)) {
                    String path = FileCenterClient.upload(multipartFile, FileConstants.FILE_TYPE_DYNAMIC_IMG);
                    if(dynamic.getImgs() == null) {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(path);
                        dynamic.setImgs(list);
                    } else {
                        dynamic.getImgs().add(path);
                    }
                }
            }
        }

        dynamicService.saveDynamic(dynamic);
        model.addAttribute("dynamic", dynamic);
    }

    /**
     * 删除动态
     * @param id 动态编号
     * @param uid 用户编号
     */
    @ApiOperation(value = "remove", notes = "删除动态", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("/remove")
    public void remove(@ApiParam(value = "动态编号", required = true) @RequestParam("id") Long id,
                       @ApiParam(value = "用户编号", required = true) @RequestParam("uid") Integer uid) {
        dynamicService.removeDynamicByIdAndUid(id, uid);
    }

    /**
     * 添加点赞信息
     * @param did 动态编号
     * @param uid 用户编号
     */
    @ApiOperation(value = "/flower/add", notes = "添加点赞信息", response = String.class, position = 3, httpMethod = "POST")
    @RequestMapping("/flower/add")
    public void flowerAdd(@ApiParam(value = "动态编号", required = true) @RequestParam("did") Long did,
                          @ApiParam(value = "用户编号", required = true) @RequestParam("uid") Integer uid) {
        Flower flower = new Flower(did, uid);
        dynamicService.saveFlower(flower);
    }

    /**
     * 删除点赞信息
     * @param id 点赞编号
     */
    @ApiOperation(value = "/flower/remove", notes = "删除点赞信息", response = String.class, position = 4, httpMethod = "POST")
    @RequestMapping("/flower/remove")
    public void flowerRemove(@ApiParam(value = "点赞编号", required = true) @RequestParam("id") Long id,
                             @ApiParam(value = "用户编号", required = true) @RequestParam("uid") Integer uid) {
        dynamicService.removeFlowerByIdAndUid(id, uid);
    }

    /**
     * 添加评论信息
     * @param did 动态编号
     * @param fromUid 评论用户编号
     * @param toUid 回复用户编号
     * @param content 内容
     */
    @ApiOperation(value = "/comment/add", notes = "添加评论信息", response = String.class, position = 5, httpMethod = "POST")
    @RequestMapping("/comment/add")
    public void commentAdd(@ApiParam(value = "动态编号", required = true) @RequestParam("did") Long did,
                           @ApiParam(value = "评论用户编号", required = true) @RequestParam("fromUid") Integer fromUid,
                           @ApiParam(value = "回复用户编号", required = false) @RequestParam(value = "toUid", required = false) Integer toUid,
                           @ApiParam(value = "评论内容", required = true) @RequestParam("content") String content) {
        Comment comment = new Comment(did, fromUid, content);
        if(null != toUid) {
            comment.setToUid(toUid);
        }
        dynamicService.saveComment(comment);
    }

    /**
     * 删除评论信息
     * @param id 评论编号
     * @param uid 用户编号
     */
    @ApiOperation(value = "/comment/remove", notes = "删除评论信息", response = String.class, position = 6, httpMethod = "POST")
    @RequestMapping("/comment/remove")
    public void commentRemove(@ApiParam(value = "评论编号", required = true) @RequestParam("id") Long id,
                              @ApiParam(value = "用户编号", required = true) @RequestParam("uid") Integer uid) {
        dynamicService.removeCommentByIdAndUid(id, uid);
    }


}
