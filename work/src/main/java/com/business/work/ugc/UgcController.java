package com.business.work.ugc;

import com.business.core.constants.CodeConstants;
import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.mix.MixAlbum;
import com.business.core.entity.user.User;
import com.business.work.base.support.BaseControllerSupport;
import com.business.work.mix.MixService;
import com.business.work.mixAlbum.MixAlbumService;
import com.business.work.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fenxio on 2016/9/1.
 */
@Controller
@RequestMapping("ugc")
public class UgcController extends BaseControllerSupport {

    @Autowired
    private MixAlbumService mixAlbumService;
    @Autowired
    private MixService mixService;
    @Autowired
    private UserService userService;

    /**
     * 用户创建 专辑列表
     * @return
     */
    @RequestMapping("albums-list")
    public String albumsList(Page<MixAlbum> page, Model model) {
        page.getFilter().put("type", MixAlbum.TYPE_USER_CREATE);
        mixAlbumService.list(page);
        model.addAttribute("page", page);
        return "ugc/albums-list";
    }

    /**
     * 审查页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("check-albums")
    public String checkAlbums(@RequestParam("id") Integer id, Model model) {
        MixAlbum mixAlbum = mixAlbumService.findById(id);
        List<Mix> mixList = mixService.findByMixAlbumsId(id);
        model.addAttribute("mixAlbum", mixAlbum)
             .addAttribute("mixList", mixList);
        return "ugc/check-albums";
    }

    /**
     * 修改 mix 审查状态
     * @param id
     * @param checkStatus
     */
    @RequestMapping("check-mix")
    public void checkMix(@RequestParam("id") Integer id, @RequestParam("checkStatus") Integer checkStatus) {
        mixService.mixModifyCheckStatus(id, checkStatus);
    }

    /**
     * 修改 mixAlbum 审查状态
     * @param id
     * @param checkStatus
     */
    @RequestMapping("check-mixAlbum")
    public void checkMixAlbum(@RequestParam("id") Integer id,
                              @RequestParam("checkStatus") Integer checkStatus,
                              @RequestParam(value = "checkMessage", required = false) String checkMessage,
                              Model model) {

        if(checkStatus == Constants.CHECK_STATUS_SUCCESS) {
            List<Mix> list = mixService.findByMixAlbumsId(id);
            boolean flag = true;
            for (Mix mix : list) {
                if(mix.getCheckStatus() != Constants.CHECK_STATUS_SUCCESS) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                mixAlbumService.mixAlbumModifyCheckStatus(id, checkStatus, checkMessage);
            } else {
                model.addAttribute("code", CodeConstants.MISS).addAttribute("message", "还有节目未通过审核");

            }
        } else {
            mixAlbumService.mixAlbumModifyCheckStatus(id, checkStatus, checkMessage);
        }


    }

    /**
     * UGC 用户列表
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("user-list")
    public String userList(Page<User> page, Model model) {
        page.getFilter().put("activatesUgc", User.STATE_ACTIVATES);
        userService.list(page);
        model.addAttribute("page", page);
        return "ugc/user-list";
    }

    /**
     * 审核用户信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "check-user", method = RequestMethod.GET)
    public String checkUser(@RequestParam("id") Integer id, Model model) {
        User user = userService.userInfo(id);
        model.addAttribute("user", user);
        return "ugc/check-user";
    }

    /**
     * 审核
     * @param id
     * @param checkStatus
     * @param checkMessage
     */
    @RequestMapping(value = "check-user", method = RequestMethod.POST)
    public void checkUser(@RequestParam("id") Integer id,
                          @RequestParam("checkStatus") Integer checkStatus,
                          @RequestParam(value = "checkMessage", required = false) String checkMessage) {
        userService.userModifyCheckStatus(id, checkStatus, checkMessage);
    }

}
