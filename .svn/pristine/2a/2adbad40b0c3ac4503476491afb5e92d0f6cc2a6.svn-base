package com.business.app.club;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.core.client.FileCenterClient;
import com.business.core.utils.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by sin on 2015/11/20.
 */
@Api(value = "club", description = "俱乐部管理")
@Controller
@RequestMapping("club")
public class ClubController extends BaseControllerSupport {

    @Autowired
    private ClubService clubService;

    /**
     * 用户 俱乐部列表
     *
     * @param uid 用户编号
     */
    @RequestMapping("list")
    public void list(Model model, @RequestParam("uid") Integer uid) {
        model.addAttribute("list", clubService.list(uid));
    }

    /**
     * 俱乐部查询
     *
     * @param uid 用户编号
     * @param clubId 俱乐部编号
     */
    @RequestMapping("find")
    public void find(Model model, Integer uid, Long clubId) {
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(clubId)) {
            return;
        }
        model.addAttribute("detail", clubService.find(clubId, uid));
    }

    /**
     * 添加 俱乐部
     *
     * <result>
     *     0、正常
     *     1、俱乐部名称已存在
     * </result>
     * @param request 背景图
     * @param name 名称
     * @param desc 描述
     */
    @RequestMapping("add")
    private void add(@ApiIgnore Model model,
                     HttpServletRequest request,
                     @ApiParam(required = true, value = "俱乐部名称") @RequestParam("name") String name,
                     @ApiParam(required = true, value = "俱乐部描述") @RequestParam("desc") String desc) {
        Map<String, MultipartFile> multipartFile=  FileCenterClient.buildMultipartFile(request);
        MultipartFile imageMultipartFile = null;
        if (null != multipartFile.get("image")) {
            imageMultipartFile = multipartFile.get("image");
        }
        Object[] result = clubService.add(imageMultipartFile, getCurrentUserId(request), name, desc);

        switch ((int)result[0]) {
            case 0:
                model.addAttribute("club", result[1]);
                break;
            case 1:
                tip(model, CodeConstants.CLUB_NAME_REPEAT);
                break;
            case 2:
                tip(model, CodeConstants.LOGIN_USER_NOT_EXIST);
                break;
        }
    }

    /**
     * 修改 俱乐部
     *
     * @param request 背景图
     * @param name 名称
     * @param desc 描述
     */
    @RequestMapping("modify")
    public void modify(@ApiIgnore Model model,
                       HttpServletRequest request,
                       @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                       @ApiParam(required = true, value = "俱乐部名称") @RequestParam("name") String name,
                       @ApiParam(required = true, value = "俱乐部描述") @RequestParam("desc") String desc) {
        Map<String, MultipartFile> multipartFileMap = FileCenterClient.buildMultipartFile(request);
        MultipartFile imageMultipartFile = null;
        if (null != multipartFileMap.get("image")) {
            imageMultipartFile = multipartFileMap.get("image");
        }
        Object[] result = clubService.modify(imageMultipartFile, clubId, name, desc);

        switch ((int)result[0]) {
            case 0:
                model.addAttribute("club", result[1]);
                break;
            case 1:
                tip(model, CodeConstants.CLUB_NOT_EXIST);
                break;
            case 2:
                tip(model, CodeConstants.CLUB_NAME_REPEAT);
                break;
        }
    }

    /**
     * 删除俱乐部今夕
     *
     * @param clubId 俱乐部编号
     * @param uid 用户编号
     */
    @ApiOperation(value = "remove", notes = "删除俱乐部", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("remove")
    public void remove(@ApiIgnore Model model,
                       @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                       @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid) {
        int result = clubService.remove(clubId, uid);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.CLUB_NOT_EXIST);
                break;
        }
    }

    /**
     * 活跃
     * <p>
     *     返回 map 集合，返回俱乐部成员信息 和 今日动态
     *     memberCount ： 俱乐部成员
     *     activeCount ： 活跃人数
     * </p>
     * @param clubId 俱乐部编号
     */
    @ApiOperation(value = "active", notes = "俱乐部活跃", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("active")
    public void active(@ApiIgnore Model model,
                       @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId) {
        model.addAttribute("active", clubService.active(clubId));
    }

    /**
     * 活跃 用户
     *
     * @param clubId 俱乐部编号
     */
    @ApiOperation(value = "active-user", notes = "活跃 用户", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("active-user")
    public void activeUser(@ApiIgnore Model model, HttpServletRequest request,
                           @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId) {
        model.addAttribute("activeUser", clubService.activeUser(clubId, getCurrentUserId(request)));
    }

    /**
     * 分享
     *
     * @param clubId 俱乐部编号
     */
    @ApiOperation(value = "share-url", notes = "活跃 用户", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("share-url")
    public void share(@ApiIgnore Model model,
                      HttpServletRequest request,
                      @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId) {
        String sharePath = HttpUtil.getServerPath(request) + "club/share-info.htm?clubId=" +  clubId;
        model.addAttribute("shareUrl", sharePath);

    }

    /**
     * 俱乐部分享详细
     *
     * @param clubId 俱乐部编号
     */
    @ApiOperation(value = "share-info", notes = "活跃 用户", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("share-info")
    public String shareInfo(@ApiIgnore Model model,
                            @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId) {
        model.addAttribute("club", clubService.shareInfo(clubId));
        return "club/share-info";
    }

    /**
     * 俱乐部 动态
     *
     * @param clubId 俱乐部编号
     */
    @ApiOperation(value = "dynamic", notes = "俱乐部 动态", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("dynamic")
    public void list(@ApiIgnore Model model,
                     @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                     @ApiParam(required = true, value = "页码") @RequestParam("index") Integer index) {
        model.addAttribute("list", clubService.dynamic(clubId, index));
    }


    /**
     * 解散 俱乐部
     *
     * @param clubId 俱乐部编号
     */
    @ApiOperation(value = "dissolution", notes = "解散 俱乐部", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("dissolution")
    public void dissolution(@ApiIgnore Model model, HttpServletRequest request,
                            @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId) {
        int result = clubService.dissolution(getCurrentUserId(request), clubId);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.CLUB_INSUFFICIENT_AUTHORITY);
                break;
        }
    }
}
