package com.business.app.clubMember;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.user.UserService;
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
 * 俱乐部成员
 */
@Api(value = "club-member", description = "俱乐部成员")
@Controller
@RequestMapping("club-member")
public class ClubMemberController extends BaseControllerSupport {

    @Autowired
    private ClubMemberService clubMemberService;
    @Autowired
    private UserService userService;

    /**
     * 俱乐部 成员分页
     *
     * @param clubId 俱乐部编号
     */
    @ApiOperation(value = "list", notes = "成员列表", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("list")
    public void list(@ApiIgnore Model model,
                     @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                     @ApiParam(required = true, value = "页码") @RequestParam("index") Integer index) {
        model.addAttribute("list", clubMemberService.list(clubId, index));
    }

    /**
     * 添加俱乐部 成员
     *
     * <p>
     *     添加俱乐部成员，如果在期间 加入过俱乐部
     *     则恢复俱乐部之前的数据 修改 status 状态
     *     <strong>
     *         只有在首次加入的用户才，进行添加处理
     *     </strong>
     *     俱乐部成功信息
     *     <result>
     *          0、成功
     *          1、用户不存在
     *          2、俱乐部不存在
     *          3、已是俱乐部成员
     *          4、之前加入过俱乐部，恢复之前数据
     *     </result>
     * </p>
     * @param clubId 俱乐部编号
     * @param addUid 加入用户
     */
    @ApiOperation(value = "add", notes = "添加俱乐部成员", response = String.class, position = 2, httpMethod = "POST")
    @RequestMapping("add")
    public void add(@ApiIgnore Model model,
                    @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                    @ApiParam(required = true, value = "添加的用户编号") @RequestParam("addUid") Integer addUid) {
        Object[] result = clubMemberService.add(clubId, addUid, null);

        switch ((int)result[0]) {
            case 0:
                break;
            case 3:
                tip(model, CodeConstants.CLUB_IS_CLUB_MEMBER);
                break;
            case 4:
                tip(model, CodeConstants.CLUB_RECOVERY_MEMBER);
                model.addAttribute("member", result[1]);
                break;
            case 1:
                tip(model, CodeConstants.USER_MIX_COLLECTION_USER_EXIST);
                break;
            case 2:
                tip(model, CodeConstants.CLUB_JOIN_ERROR_NOT_EXIST);
                break;
            case 5:
                tip(model, CodeConstants.CLUB_JOIN_ERROR_THE_REMOVE);
                break;
            case 6:
                tip(model, CodeConstants.CLUB_JOIN_MEMBER_LIMIT);
                break;
        }
    }

    /**
     * 更新 成员信息 返回 修改后的信息
     *
     * @param clubId 俱乐部编号
     * @param uid 成员编号
     * @param modifyName 修改名称
     */
    @ApiOperation(value = "modify-name", notes = "修改俱乐部成员名字", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("modify-name")
    public void modifyName(@ApiIgnore Model model,
                           @ApiParam(required = true, value = "用户编号") @RequestParam("uid") Integer uid,
                           @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                           @ApiParam(required = true, value = "新的名字") @RequestParam("modifyName") String modifyName) {
        model.addAttribute("member", clubMemberService.modify(clubId, uid, modifyName));
    }

    /**
     * 退出
     *
     * @param clubId 俱乐部
     */
    @ApiOperation(value = "quit", notes = "退出俱乐部", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("quit")
    public void remove(HttpServletRequest request,
                       @ApiIgnore Model model,
                       @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId) {
        int result = clubMemberService.quit(getCurrentUserId(request), clubId);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.CLUB_NOT_EXIST);
                break;
            case 2:
                tip(model, CodeConstants.CLUB_CREATE_USER_NOT_QUIT);
                break;
        }
    }

    /**
     * 强制移除用户
     *
     * @param clubId 俱乐部
     * @param quitUid 移除用户的编号
     */
    @ApiOperation(value = "forced-quit", notes = "俱乐部部长移除成员", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("forced-quit")
    public void forcedQuit(@ApiIgnore Model model, HttpServletRequest request,
                           @ApiParam(required = true, value = "俱乐部编号") @RequestParam("clubId") Long clubId,
                           @ApiParam(required = true, value = "移除的成员编号") @RequestParam("quitUid") Integer quitUid) {
        int result = clubMemberService.forcedQuit(getCurrentUserId(request), clubId, quitUid);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.CLUB_MEMBER_IS_REMOVE);
                break;
        }
    }

    /**
     * 查看 用户最后一次运动记录
     *
     * @param targetUid 目标用户 id，查看的用户 id
     */
    @ApiOperation(value = "last-run", notes = "获取用户最后一次运动记录", response = String.class, position = 2, httpMethod = "GET")
    @RequestMapping("last-run")
    public void lastRun(@ApiIgnore Model model,
                        @ApiParam(required = true, value = "目标用户编号") @RequestParam("targetUid") Integer targetUid) {
        model.addAttribute("user", userService.lastRun(targetUid));
    }
}
