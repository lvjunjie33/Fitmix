package com.business.app.clubMessage;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sin on 2015/11/20.
 * 俱乐部留言
 */
@Api(value = "club-message", description = "俱乐部留言")
@Controller
@RequestMapping("club-message")
public class ClubMessageController extends BaseControllerSupport {

    @Autowired
    private ClubMessageService clubMessageService;

    /**
     * 添加留言
     *
     * @param clubId 俱乐部编号
     * @param content 内容
     * @param contentType 内容类型 1、普通文本
     */
    @ApiOperation(value = "add", notes = "添加留言", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("add")
    public void add(HttpServletRequest request,
                    @ApiIgnore Model model,
                    @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                    @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                    @ApiParam(required = true, value = "内容") @RequestParam("content") String content,
                    @ApiParam(required = true, value = "内容类型:1、普通文本") @RequestParam(value = "contentType", defaultValue = "1") Integer contentType) {
        Object[] result = clubMessageService.add(clubId, uid, content, contentType, FileCenterClient.buildMultipartFile(request));
        switch ((int)result[0]) {
            case 0:
                model.addAttribute("message", result[0]);
                break;
            case 1:
                tip(model, CodeConstants.CLUB_NOT_EXIST);
                break;
            case 2:
                tip(model, CodeConstants.CLUB_ARE_NOT_MEMBER);
                break;
        }
    }

    /**
     * 留言分页
     *
     * @param clubId 俱乐部编号
     * @param index 分页
     */
    @ApiOperation(value = "page", notes = "留言列表", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("page")
    public void page(HttpServletRequest request, @ApiIgnore Model model,
                     @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                     @ApiParam(required = true, value = "页码") @RequestParam("index") Integer index) {
        String version = HttpUtil.getParameter(request, "_v"); //用户版本信息
//        String sdk = HttpUtil.getParameter(request, "_sdk");//sdk 设备类型

        boolean isOldVersion = false; //旧版本不支持语音和图片
        if (version != null) {
            int v = Integer.parseInt(version);
            if (checkSDKType(request)) {//iPhone
                if (v < 11) {
                    isOldVersion = true;
                }
            } else {
                if (v < 44) {
                    isOldVersion = true;
                }
            }
        }

        model.addAttribute("page", clubMessageService.page(clubId, index, isOldVersion));
    }
}
