package com.business.work.wxMenu;

import com.alibaba.fastjson.JSON;
import com.business.core.entity.wx.WXCustomerService;
import com.business.work.base.constants.CodeConstants;
import com.business.work.base.support.BaseControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sin on 2015/10/20.
 */
@Controller
@RequestMapping("wx-menu")
public class WXMenuController extends BaseControllerSupport {

    @Autowired
    private WXMenuService wxMenuService;

    /**
     * 获取 微信菜单
     * 构建微信菜单 子父级结构
     * @return 结构型微信菜单
     */
    @RequestMapping("review")
    public String wxMenu(Model model) {
        model.addAttribute("wxMenu", JSON.toJSONString(wxMenuService.wxMenu()));
        return "wxMenu/review";
    }

    /**
     * 添加 微信菜单
     * @param name 名称
     * @param parent 父菜单编号
     * @param type button类型，参考微信文档，暂只支持 url跳转
     */
    @RequestMapping("add-menu")
    public void addMenu(@RequestParam String name, @RequestParam Integer parent, @RequestParam String type) {
        wxMenuService.addMenu(name, parent, type);
    }


    /**
     * 更新 菜单排序
     * @param id 编号
     * @param type 编号
     */
    @RequestMapping("update-sort")
    public void updateSort(@RequestParam Integer id, @RequestParam("type") String type) {
        wxMenuService.updateSort(id, type);
    }

    /**
     * 更新菜单
     * @param id 编号
     * @param url 地址数据
     */
    @RequestMapping("update-url")
    public void updateUrl(@RequestParam Integer id, @RequestParam String url) {
        wxMenuService.updateUrl(id, url);
    }

    /**
     * 更新 menu名称
     * @param id 编号
     * @param name 名称
     */
    @RequestMapping("update-name")
    public void updateName(@RequestParam Integer id, @RequestParam String name) {
        wxMenuService.updateName(id, name);
    }

    /**
     * 更新 menu click key
     * @param id 编号
     * @param key click 按钮 key
     */
    @RequestMapping("update-key")
    public void updateKey(@RequestParam Integer id, @RequestParam String key) {
        wxMenuService.updateKey(id, key);
    }

    /**
     * 删除菜单
     * @param id 编号
     */
    @RequestMapping("remove-menu")
    public void removeMenu(@RequestParam Integer id) {
        wxMenuService.remove(id);
    }

    /**
     * 提交给微信服务器
     * @return 0、成功  1、提交出错
     */
    @RequestMapping("send-wx-server")
    public void sendWXServer(Model model) {
        int result = wxMenuService.sendWXServer();
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.WX_MENU_SEND_WX_SERVER_ERROR, "提交微信服务器失败");
                break;
            case 2:
                tip(model, CodeConstants.WX_MENU_SEND_WX_SERVER_AUTH_ERROR, "微信认证失败");
                break;
        }
    }

    /*=======================================微信客服==============================================================*/

    /**
     * 数据库查询 客服帐号
     */
    @RequestMapping("cs/manager")
    public void csManager(Model model) {
        model.addAttribute("list", wxMenuService.findAllCS());
    }

    /**
     * 微信查询 客服帐号
     */
    @RequestMapping("cs/list")
    public void csList(Model model) {
        model.addAttribute("list", wxMenuService.getCS());
    }

    /**
     * 添加微信客服
     *
     * @param account 帐号
     * @param nice 昵称
     * @param password 密码
     */
    @RequestMapping("cs/add")
    public void csAdd(String account, String nice, String password) {
        WXCustomerService cs = new WXCustomerService();
        cs.setAccount(account);
        cs.setNickname(nice);
        cs.setPassword(password);
        wxMenuService.addCS(cs);
    }

    /**
     * 修改微信客服帐号信息
     *
     * @param account 帐号
     * @param nice 昵称
     * @param password 密码
     */
    @RequestMapping("cs/modify")
    public void csModify(String account, String nice, String password) {

    }

    /**
     * 微信客服头像设置
     *
     * @param id 编号
     * @param avatar 头像
     */
    @RequestMapping("cs/set/avatar")
    public void csSetAvatar(Integer id, String avatar) {

    }

    /**
     * 发送微信客服信息
     *
     * @param uid 用户编号
     */
    @RequestMapping("send/cs/msg")
    public void sendCSMsg(Integer uid, Integer csId, String msg) {
        wxMenuService.sendCSMsg(uid, csId, msg);
    }

}
