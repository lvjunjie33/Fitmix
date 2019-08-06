package com.business.app.clubNotice;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by sin on 2015/12/7.
 */
@Api(value = "club-notice", description = "俱乐部公告")
@Controller
@RequestMapping("club-notice")
public class ClubNoticeController extends BaseControllerSupport {

    @Autowired
    private ClubNoticeService clubNoticeService;

    /**
     * 公告分页
     * @param clubId 俱乐部编号
     * @param index 第几页
     */
    @ApiOperation(value = "page", notes = "公告列表", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("page")
    public void page(@ApiIgnore Model model,
                     @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                     @ApiParam(required = true, value = "页码") @RequestParam("index") Integer index) {
        model.addAttribute("page", clubNoticeService.page(clubId, index));
    }

    /**
     * 公告信息 添加
     * @param uid 用户编号
     * @param clubId 俱乐部编号
     * @param name 公告名称
     * @param content 公告内容
     * @param address 公告地址
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param desc 描述
     * @param image 背景图
     */
    @RequestMapping("add")
    public void add(@ApiIgnore Model model,
                    @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                    @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                    @ApiParam(required = true, value = "公告名称") @RequestParam("name") String name,
                    @ApiParam(required = true, value = "公告内容") @RequestParam("content") String content,
                    @ApiParam(required = true, value = "公告地址") @RequestParam("address") String address,
                    @ApiParam(required = true, value = "开始时间") @RequestParam("beginTime") Long beginTime,
                    @ApiParam(required = true, value = "结束时间") @RequestParam("endTime") Long endTime,
                    @ApiParam(required = true, value = "描述") @RequestParam("desc") String desc,
                    @ApiParam(required = true, value = "背景图") @RequestParam(value = "image") MultipartFile image) {

        Object[] result = clubNoticeService.add(uid, clubId, name, content, address, beginTime, endTime, desc, image);

        switch ((int)result[0]) {
            case 0:
                model.addAttribute("notice", result[0]);
                break;
            case 1:
                tip(model, CodeConstants.CLUB_NOT_EXIST);
                break;
        }
    }

    /**
     * 公告信息 修改
     * @param uid 用户编号
     * @param clubId 俱乐部编号
     * @param name 公告名称
     * @param content 公告内容
     * @param address 公告地址
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param desc 描述
     * @param image 背景图
     */
    @ApiOperation(value = "modify", notes = "修改公告", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("modify")
    public void modify(@ApiIgnore Model model,
                       @ApiParam(required = true, value = "公告编号") @RequestParam("id") Long id,
                       @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                       @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                       @ApiParam(required = true, value = "公告名称") @RequestParam("name") String name,
                       @ApiParam(required = true, value = "公告内容") @RequestParam("content") String content,
                       @ApiParam(required = true, value = "公告地址") @RequestParam("address") String address,
                       @ApiParam(required = true, value = "开始时间") @RequestParam("beginTime") Long beginTime,
                       @ApiParam(required = true, value = "结束时间") @RequestParam("endTime") Long endTime,
                       @ApiParam(required = true, value = "描述") @RequestParam("desc") String desc,
                       @ApiParam(required = false, value = "背景图") @RequestParam(value = "image", required = false) MultipartFile image) {
        model.addAttribute("notice", clubNoticeService.
                modify(id, uid, clubId, name, content, address, beginTime, endTime, desc, image));
    }

    /**
     * 公告
     * @param id 公告编号
     * @param clubId 俱乐部编号
     * @param uid 用户编号
     */
    @ApiOperation(value = "remove", notes = "删除公告", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("remove")
    public void remove(@ApiParam(required = true, value = "公告编号") @RequestParam("id") Long id,
                       @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                       @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid) {
        clubNoticeService.remove(id, clubId, uid);
    }
}
