package com.business.work.wxMenu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.core.entity.user.User;
import com.business.core.entity.wx.WXCustomerService;
import com.business.core.entity.wx.WXMenu;
import com.business.core.entity.wx.WXUser;
import com.business.core.operations.schedulerValue.SchedulerValueCoreService;
import com.business.core.operations.wxUser.WXUserCoreDao;
import com.business.core.utils.*;
import com.business.work.user.UserDao;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by sin on 2015/10/21.
 */
@Service
public class WXMenuService {

    private Logger logger = LoggerFactory.getLogger(WXMenuService.class);

    @Autowired
    private WXMenuDao wxMenuDao;
    @Autowired
    private SchedulerValueCoreService schedulerValueCoreService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private WXUserCoreDao wxUserCoreDao;

    /**
     * 获取 微信菜单
     * 构建微信菜单 子父级结构
     * @return 结构型微信菜单
     */
    public List<WXMenu> wxMenu() {
        List<WXMenu> wxMenuList = wxMenuDao.findAll();
        return buildWXMenuListStructure(wxMenuList);
    }

    /**
     * 构建 菜单列表上下级结构
     * @param wxMenuList 所有菜单
     * @return 构建的微信菜单
     */
    public List<WXMenu> buildWXMenuListStructure(List<WXMenu> wxMenuList) {
        if (CollectionUtils.isEmpty(wxMenuList)) {
            return Collections.emptyList();
        }
        // Build Multimap ，root 菜单 parent 为 null
        Map<Integer, List<WXMenu>> parentWXMenuMap = CollectionUtil.buildMultimap(wxMenuList, Integer.class, WXMenu.class, "parent");
        for (WXMenu wxMenu : parentWXMenuMap.get(null)) {
            wxMenu.setChildMenu(parentWXMenuMap.get(wxMenu.getId()));
        }
        return parentWXMenuMap.get(null);
    }

    /**
     * 更新 菜单排序
     * @param id 编号
     * @param type 类型
     *            将id 换到 id
     */
    public void updateSort(Integer id, String type) {
        WXMenu wxMenu = wxMenuDao.findWXMenuById(id);

        /// 确定 排序数据源， 父级菜单排序 or 子菜单排序
        Map<Integer, WXMenu> childWXMenuMap;
        if (null == wxMenu.getParent()) {
            childWXMenuMap = CollectionUtil.buildMap(wxMenuDao.findAllParentWXMenu(), Integer.class, WXMenu.class, "sort");
        }
        else {
            childWXMenuMap = CollectionUtil.buildMap(wxMenuDao.findWXMenuByParent(wxMenu.getParent()), Integer.class, WXMenu.class, "sort");
        }

        /// 确定排序 上移 or 下移
        WXMenu destWXMenu;
        if (type == "desc") { // ++
            destWXMenu = childWXMenuMap.get(wxMenu.getSort() + 1);
        }
        else {
            destWXMenu = childWXMenuMap.get(wxMenu.getSort() - 1);
        }
        if (destWXMenu != null) {
            wxMenuDao.updateWXMenuById(wxMenu.getId(), Update.update("sort", destWXMenu.getSort()));
            wxMenuDao.updateWXMenuById(destWXMenu.getId(), Update.update("sort", wxMenu.getSort()));
        }
    }

    /**
     * 添加 微信菜单
     * @param name 名称
     * @param parent 父菜单编号
     * @param type button类型，参考微信文档，暂只支持 url跳转
     */
    public void addMenu(String name, Integer parent, String type) {
        WXMenu wxMenu = new WXMenu();
        wxMenu.setName(name);
        wxMenu.setUrl("");
        wxMenu.setParent(parent);
        wxMenu.setType(type);
        wxMenu.setKey("");
        /// 判断是否 根菜单
        if (parent == null) {
            /// 跟菜单，检查是否存在其他根菜单
            List<WXMenu> parentWXMenuList = wxMenuDao.findAllParentWXMenu();
            if(CollectionUtils.isEmpty(parentWXMenuList)) {
                wxMenu.setSort(0);
            }
            else {
                /// 最后一级 根菜单 排序 ++
                wxMenu.setSort(parentWXMenuList.get(0).getSort() + 1);
            }
        }
        else {
            /// 当前父菜单中，是否存在子菜单
            List<WXMenu> wxMenuChildList = wxMenuDao.findWXMenuByParent(parent);
            if (CollectionUtils.isEmpty(wxMenuChildList)) {
                /// 不存在子菜单时，默认值0
                wxMenu.setSort(0);
            }
            else {
                /// 存在子菜单， 最后子菜单 排序++
                wxMenu.setSort(wxMenuChildList.get(0).getSort() + 1);
            }
        }
        wxMenu.setAddTime(System.currentTimeMillis());
        /// 添加菜单
        wxMenuDao.insertWXMenu(wxMenu);
    }

    /**
     * 更新 url
     * @param id 编号
     * @param url 地址信息
     */
    public void updateUrl(Integer id, String url) {
        wxMenuDao.updateWXMenuById(id, Update.update("url", url));
    }

    /**
     * 更新 menu名称
     * @param id 编号
     * @param name 名称
     */
    public void updateName(Integer id, String name) {
        wxMenuDao.updateWXMenuById(id, Update.update("name", name));
    }

    /**
     * 更新 menu click key
     * @param id 编号
     * @param key click 按钮 key
     */
    public void updateKey(Integer id, String key) {
        wxMenuDao.updateWXMenuById(id, Update.update("key", key));
    }


    /**
     * 删除 微信菜单
     * @param id 编号
     */
    public void remove(Integer id) {
        List<WXMenu> wxMenuList = wxMenuDao.findWXMenuByParent(id);
        // 判断是否有子菜单 ？ 没有直接删除 : 删除子菜单 和 父级菜单
        if (CollectionUtils.isEmpty(wxMenuList)) {
            wxMenuDao.removeWXMenuById(id);
        }
        else {
            Set<Integer> ids = CollectionUtil.buildSet(wxMenuList, Integer.class, "id");
            ids.add(id);
            wxMenuDao.removeWXMenuByIds(ids);
        }
    }

    /**
     * 提交给微信服务器
     * @return 0、成功  1、提交出错  2、微信认证失败
     */
    public int sendWXServer() {
        // 构建的微信菜单
        List<WXMenu> buildWXMenuList = buildWXMenuListStructure(wxMenuDao.findAll());
        JSONObject paramJsonObject = new JSONObject();
        for (WXMenu wxMenu : buildWXMenuList) {
            wxMenu.setSub_button(wxMenu.getChildMenu());
            wxMenu.setChildMenu(Collections.<WXMenu>emptyList());
        }
        paramJsonObject.put("button", buildWXMenuList);
        try {
            String token = schedulerValueCoreService.getCurrentAccessToken();
            System.out.println(CommonUtil.jsonFormatter(JSON.toJSONString(paramJsonObject)));
            String resultText = WXUtil.setMenu(token, JSON.toJSONString(paramJsonObject));
            JSONObject jsonObject = JSON.parseObject(resultText);
            if (Integer.parseInt(jsonObject.get("errcode").toString()) != 0) {
                return 2;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 获取客服列表
     */
    public Map<String, Object> getCS() {
        String token = schedulerValueCoreService.getCurrentAccessToken();
        logger.error("token = " + token);

        String a = WXUtil.getCSList(token, "");
        logger.error(a);
        Map<String, Object> list;
        if (StringUtils.isEmpty(a)) {
            list = Collections.EMPTY_MAP;
        } else {
            list = (Map<String, Object>) JSON.parse(a);
        }
        return list;
    }

    /**
     * 查询所有的微信客服帐号
     */
    public List<WXCustomerService> findAllCS() {
        return wxMenuDao.findAllCS();
    }

    /**
     * 发送微信客服消息
     */
    public void sendCSMsg(Integer uid, Integer csId, String msg) {
        String token = schedulerValueCoreService.getCurrentAccessToken();
        User user = userDao.findUserByUid(uid, "unionId");
        if (user == null || StringUtils.isEmpty(user.getUnionId())) {
            return;
        }

        WXUser wxUser = wxUserCoreDao.findWXUserByOpenid(user.getUnionId());
        if (wxUser == null || StringUtils.isEmpty(wxUser.getUnionid())) {
            return;
        }

        if (StringUtils.isEmpty(wxUser.getOpenid())) {
            return;
        }

        WXCustomerService wxCustomerService = wxMenuDao.findCSById(csId);
        if (wxCustomerService == null || StringUtils.isEmpty(wxCustomerService.getAccount())) {
            return;
        }

        String a = WXUtil.sendCSMsg(token, wxUser.getOpenid(), msg, wxCustomerService.getAccount());
        logger.error(a);
        Map<String, Object> list;
        if (StringUtils.isEmpty(a)) {
            list = Collections.EMPTY_MAP;
        } else {
            list = (Map<String, Object>) JSON.parse(a);
        }
    }

    /**
     * 新增微信客服
     *
     * @param cs 微信客服
     */
    public void addCS(WXCustomerService cs) {
        String token = schedulerValueCoreService.getCurrentAccessToken();

        String a = WXUtil.addCS(token, cs.getAccount(), cs.getNickname(), MD5Util.MD5Encode(cs.getPassword(), MD5Util.CHARSET_NAME_UTF));
        logger.error(a);
        Map<String, Object> map;
        if (StringUtils.isEmpty(a)) {
            map = Collections.EMPTY_MAP;
        } else {
            map = (Map<String, Object>) JSON.parse(a);
            if ("0".equals(map.get("errcode"))) {
                wxMenuDao.addCS(cs);
            }
        }
    }
}
