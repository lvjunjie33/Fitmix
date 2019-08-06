package com.business.app.im;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseControllerSupport;
import com.business.app.easeMob.EaseMobManager;
import com.business.app.easeMob.api.impl.EasemobIMUsers;
import com.business.app.easeMob.comm.EasemobRestAPIFactory;
import com.business.core.entity.Page;
import com.business.core.entity.im.IMInfoGroup;
import com.business.core.entity.user.User;
import com.business.core.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by edward on 2016/10/17.
 */
@Controller
@RequestMapping("im")
public class IMController extends BaseControllerSupport{

    @Autowired
    private IMService imService;

    @RequestMapping("tt")
    public void test(Integer key) {
        User user = new User();
        user.setId(key);
        imService.registerIM(user);
    }

    @RequestMapping("t2")
    public void test2(String username) {
        EasemobIMUsers easemobIMUsers = EaseMobManager.getOperationClass(EasemobRestAPIFactory.USER_CLASS);
        imService.checkEaseMobImUser(easemobIMUsers, username);
//        https://a1.easemob.com/shenzhenfirstbluechip/test/users
//        top:https://a1.easemob.com/shenzhenfirstbluechip/test/users/im666
    }

    @RequestMapping("t3")
    public void test3(String key) {
        imService.chatShareInfo(key, "im555");
    }

    /**
     * 获取目标用户的位置信息
     *
     * @param userNames 目标用户的帐号
     */
    @RequestMapping("get-positions")
    public void getPosition(Model model, String userNames) {
        String[] usernameStr = userNames.split(",");
        if (usernameStr.length == 0) {
            model.addAttribute("positions", Collections.EMPTY_LIST);
        } else {
            model.addAttribute("positions", imService.getPositions(Arrays.asList(usernameStr)));
        }
    }

    /**
     * 更新最新的用户位置信息
     *
     * @param username 用户编号
     * @param lng 经度
     * @param lat 纬度
     */
    @RequestMapping("modify-position")
    public void modifyPosition(String username, Double lng, Double lat) {
        imService.modifyPosition(username, lng, lat);
    }

    /**
     * app 下载分享
     *
     * @param clubId 俱乐部编号
     */
    @RequestMapping("app-share-url")
    public void appShareUrl(Model model, HttpServletRequest request, @RequestParam("clubId") Long clubId) {
            String sharePath = HttpUtil.getServerPath(request) + "club/share-info.htm?clubId=" +  clubId;
            model.addAttribute("shareUrl", sharePath);
    }

    /**
     * 俱乐部分享详细
     *
     * @param clubId 俱乐部编号
     */
    @RequestMapping("app-share-info")
    public String appShareInfo(@RequestParam("chatId") Long clubId) {
//        model.addAttribute("club", clubService.shareInfo(clubId));
        return "im/app-share-info";
    }

    @RequestMapping("chat-share-url")
    public void chatShareUrl(Model model, HttpServletRequest request, String chatName, String imUserName) {
        model.addAttribute("shareUrl", addServerPathPrefix(request, "im/chat-share-info.htm?chatName=" +  chatName+"&un=" + imUserName));
    }

    /**
     * 按住说群组分享详细
     *
     * @param chatName 群组编号
     */
    @RequestMapping("chat-share-info")
    public String chatShareInfo(Model model, @RequestParam("chatName") String chatName, @RequestParam("un") String imUserName) {
        model.addAttribute("chatInfo", imService.chatShareInfo(chatName, imUserName));
        return "im/chat-share-info";
    }

    /**
     * 添加新的群组
     *
     * @param userName 用户帐号名
     * @param groupName 群组名
     * @param groupPassword 群组密码
     * @param groupDes 群组描述
     */
    @RequestMapping("add-group")
    public void addGroup(Model model, String userName, String groupName, @RequestParam(required = false) String groupPassword, String groupDes) {
        IMInfoGroup imInfoGroup = imService.addGroup(userName, groupName, groupPassword, groupDes);
        if (imInfoGroup != null) {
            model.addAttribute("group", imInfoGroup);
        } else {
            tip(model, CodeConstants.IM_INFO_GROUP_CREATE_ERROR);
        }
    }

    /**
     * 修改环信群组加入的密码
     * @param groupId 群组编号
     * @param groupPassword 群组密码
     */
    @RequestMapping("modify-group-password")
    public void modifyGroupPassword(String groupId, @RequestParam(required = false) String groupPassword) {
        imService.modifyGroupPassword(groupPassword, groupId);
    }

    /**
     * 修改环信群组信息
     *
     * @param groupId 群组编号
     * @param groupName 群组名称
     * @param groupDes 群组描述
     */
    @RequestMapping("modify-group")
    public void modifyGroupInfo(Model model, String groupId, String groupName, String groupDes) {
        if (!imService.modifyGroupInfo(groupId, groupName, groupDes)) {
            tip(model, CodeConstants.IM_INFO_GROUP_MODIFY_INFO_ERROR);
        }
    }

    /**
     * 群组分页
     *
     * @param page 分页对象
     */
    @RequestMapping("group-page")
    public void groupPage(Page<IMInfoGroup> page) {
         imService.groupPage(page);
    }

    /**
     * 申请加入群组
     *
     * @param userName 用户名
     * @param groupId  群组编号
     * @param password 申请密码
     */
    @RequestMapping("add-group-user")
    public void addGroupUser(Model model, String userName, String groupId, String password) {
        int result = imService.addGroupUser(userName, groupId, password);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.IM_INFO_GROUP_ADD_SINGLE_USER_PASSWORD_ERROR);
                break;
            case 2:
                tip(model, CodeConstants.IM_INFO_GROUP_ADD_SINGLE_USER_INFO_ERROR);
                break;
            case 3:
                tip(model, CodeConstants.IM_INFO_GROUP_ADD_SINGLE_UN_GROUP);
                break;
        }
    }

    /**
     * 获取用户头像
     *
     * @param userNames 用户帐号名
     */
    @RequestMapping("get-users-info")
    public void getUsersAvatar(Model model, String userNames) {
        String[] unames = userNames.split(",");
        model.addAttribute("users", imService.findUsersAvatar(unames));
    }

    /**
     * 退群、解散群
     *
     * @param userName 用户名
     * @param groupId 群组编号
     */
    @RequestMapping("quit-group")
    public void quitGroup(Model model, String userName, String groupId) {
        if (StringUtils.isEmpty(userName)) {
            tip(model, CodeConstants.IM_INFO_USER_NAME_IS_NULL_ERROR);
            return;
        }
        if (StringUtils.isEmpty(groupId)) {
            tip(model, CodeConstants.IM_INFO_GROUP_ID_IS_NULL_ERROR);
            return;
        }
        imService.quitGroup(userName, groupId);
    }
}
