package com.business.work.auth;

import com.alibaba.fastjson.JSON;
import com.business.core.entity.auth.Resource;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.HttpUtil;
import com.business.work.base.constants.CodeConstants;
import com.business.work.base.support.BaseControllerSupport;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/4/21.
 */
@Controller
public class AuthController extends BaseControllerSupport {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    ///
    /// home 页面

    @RequestMapping("home")
    public String home () {
        return "home";
    }


    ///
    /// unauthorized 页面

    @RequestMapping("unauthorized")
    public String unauthorized () {
        return "unauthorized";
    }

    ///
    /// login 登录

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public String login () {
        if (getCurrentAdmin() == null) {
            return "login";
        }
        return "redirect:home.htm";
    }

    @RequestMapping(value = "login.json", method = RequestMethod.POST)
    public void login (HttpServletRequest request, Model model,
                       @RequestParam(value = "username") String username,
                       @RequestParam(value = "password") String password) {
        try {
            if ("sin".equals(username)) {
                String ip = HttpUtil.getIP(request);
                logger.error("ip >> " + ip);
            }

            Subject user = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            user.login(token);
        } catch (UnknownAccountException e) {
            tip(model, 1, "帐号不存在");
        } catch (DisabledAccountException e) {
            tip(model, 2, "帐号被禁用");
        } catch (IncorrectCredentialsException e) {
            tip(model, 3, "密码不正确");
        } catch (Exception e) {
            e.printStackTrace();
            tip(model, 4, "未知错误");
        }
    }

    ///
    /// admin 管理员

    /**
     * admin 管理
     */
    @RequestMapping("admin")
    public String admin (Model model) {
        model.addAttribute("allAdmin", authService.allAdmin());
        return "/auth/admin";
    }

    /**
     * admin 信息添加
     * @param loginName 登录用户名
     * @param password 用户密码
     * @param activate 是否激活
     */
    @RequestMapping("add-admin")
    public void addAdmin (Model model,
                          @RequestParam("loginName") String loginName,
                          @RequestParam("name") String name,
                          @RequestParam("password") String password,
                          @RequestParam("activate") Integer activate) {
        int result = authService.addAdmin(loginName, name, password, activate);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.AUTH_ADMIN_LOGIN_NAME_REPEAT, "登录用户名重复");
                break;
        }
    }

    /**
     * admin 信息修改
     * @param id 用户编号
     * @param name 用户名称
     * @param password 用户密码
     */
    @RequestMapping("modify-admin")
    public void modifyAdmin (Model model,
                             @RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("password") String password,
                             @RequestParam("activate") Integer activate) {
        int result = authService.modifyAdmin(id, name, password, activate);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.AUTH_ADMIN_NOT_EXIST, "管理员不存在");
                break;
        }
    }

    /**
     * 修改 admin role
     * @param id admin 编号
     * @param roles role集合 name
     */
    @RequestMapping("admin-role")
    public void adminRole (Model model, @RequestParam("id") Integer id, @RequestParam(value = "roles",required = false) String roles) {
        int result = authService.adminRole(id, CollectionUtil.splitList(roles, ","));
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.AUTH_ROLE_NOT_EXIST, "角色不存在");
                break;
        }
    }

    ///
    /// role 添加，角色

    @RequestMapping("role")
    public String role (Model model) {
        model.addAttribute("allRole", authService.allRole());
        return "/auth/role";
    }

    /**
     * 添加 role
     * @param name 用户名
     * @param extensionName 扩展名
     * @param des 描述
     */
    @RequestMapping("add-role")
    public void addRole (Model model,
                         @RequestParam("name") String name,
                         @RequestParam("extensionName") String extensionName,
                         @RequestParam("des") String des) {
        int result = authService.addRole(name, extensionName, des);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.AUTH_ROLE_NAME_REPEAT, "角色名重复");
                break;
        }
    }

    /**
     * role 拥有的 resource
     * @param id role 编号
     */
    @RequestMapping(value = "role-resource")
    public String roleResource (Model model, @RequestParam("id") Integer id) {
        Object[] objects = authService.roleResource(id);
        int result = (int) objects[0];
        switch (result) {
            case 0:
                List<Resource> roleResourceList = (List<Resource>) objects[1];
                List<Resource> resources = authService.allResource();

                Map<Integer, List<Resource>> resourceWithTypeMap = CollectionUtil.buildMultimap(resources, Integer.class, Resource.class, "type");
                List<Resource> operationResourceList = new ArrayList<>(0);
                operationResourceList.addAll(resourceWithTypeMap.get(Resource.TYPE_MENU));
                operationResourceList.addAll(resourceWithTypeMap.get(Resource.TYPE_OPERATION));
                if (!CollectionUtils.isEmpty(roleResourceList)) {
                    model.addAttribute("roleResourceMap", JSON.toJSON(CollectionUtil.buildMap(roleResourceList, Integer.class, Resource.class, "id")));
                }
                else { // 防止 js 报错
                    model.addAttribute("roleResourceMap", JSON.toJSON(new ArrayList<>(0)));
                }
                if (!CollectionUtils.isEmpty(resources)) {
                    model.addAttribute("pidMap", JSON.toJSON(CollectionUtil.buildMultimap(resources, Integer.class, Resource.class, "pid")));
                }
                else { // 防止 js 报错
                    model.addAttribute("pidMap", JSON.toJSON(new ArrayList<>(0)));
                }
                if (!CollectionUtils.isEmpty(operationResourceList)) { // 防止 js 报错
                    model.addAttribute("menuWithOperationMap", JSON.toJSON(CollectionUtil.buildMultimap(operationResourceList, Integer.class, Resource.class, "pid")));
                }
                else {
                    model.addAttribute("menuWithOperationMap", JSON.toJSON(new ArrayList<>(0)));
                }
                model.addAttribute("id", id);
                break;
            case 1:
                tip(model, CodeConstants.AUTH_ROLE_NOT_EXIST, "Role 不存在");
                break;
        }
        return "auth/role-resource";
    }

    /**
     * role 拥有的 resource
     * @param id role 编号
     */
    @RequestMapping(value = "role-resource.json")
    public void roleResource (Model model, @RequestParam("id") Integer id, @RequestParam("resources") List<Integer> resources) {
        int result = authService.roleResource(id, resources);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.AUTH_ROLE_NOT_EXIST, "Role 不存在");
                break;
        }
    }

    /**
     * 修改 role
     * @param id 编号
     * @param name 名称
     * @param extensionName 扩展名
     * @param des 描述
     */
    @RequestMapping("modify-role")
    public void modifyRole (Model model,
                            @RequestParam("id") Integer id,
                            @RequestParam("name") String name,
                            @RequestParam("extensionName") String extensionName,
                            @RequestParam("des") String des) {
        int result = authService.modifyRole(id, name, extensionName, des);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.AUTH_ROLE_NOT_EXIST, "角色不存在");
                break;
            case 2:
                tip(model, CodeConstants.AUTH_ROLE_NAME_REPEAT, "角色名重复");
                break;
        }
    }

    ///
    /// Resource 资源

    /**
     * 所有 resource
     */
    @RequestMapping("resource")
    public String resource (Model model) {
        List<Resource> resourceList = authService.allResource();
        if (!CollectionUtils.isEmpty(resourceList)) {
            // 根据菜单 type 区分 resource 类型（root，menu，operation）
            Map<Integer, List<Resource>> resourceWithTypeMap = CollectionUtil.buildMultimap(resourceList, Integer.class, Resource.class, "type");
            // 构建 menu 数据，ROOT 菜单对应的， resource 子菜单
            Map<Resource, List<Resource>> menuResourceWithRootMenuMap = new LinkedHashMap<>();
            Map<Integer, List<Resource>> menuResourceMap = CollectionUtil.buildMultimap(resourceList, Integer.class, Resource.class, "pid");
            for (Resource resource : resourceWithTypeMap.get(Resource.TYPE_ROOT)) {
                menuResourceWithRootMenuMap.put(resource, menuResourceMap.get(resource.getId()));
            }
            // 构建 operation 数据
            Map<Resource, List<Resource>> operationResourceWithRootMenuMap = new LinkedHashMap<>();
            for (Resource resource : resourceWithTypeMap.get(Resource.TYPE_MENU)) {
                List<Resource> operationMenuChildrenList = menuResourceMap.get(resource.getId());
                if (!CollectionUtils.isEmpty(operationMenuChildrenList)) { // 排除，没有 operation 的空菜单
                    operationResourceWithRootMenuMap.put(resource, operationMenuChildrenList);
                }
            }
            model.addAttribute("menuResourceWithRootMenuMap", menuResourceWithRootMenuMap);
            model.addAttribute("resourceChildren", resourceWithTypeMap.get(Resource.TYPE_MENU));
            model.addAttribute("operationResourceWithRootMenuMap", operationResourceWithRootMenuMap);
        }
        return "/auth/resource";
    }

    /**
     * 添加资源
     * @param name 名称
     * @param extensionName 扩展名
     * @param type 类型
     * @param sort 排序
     * @param pid 父级资源编号
     * @param handling 操作
     */
    @RequestMapping("add-resource")
    public void addResource (Model model,
                             @RequestParam("name") String name,
                             @RequestParam("extensionName") String extensionName,
                             @RequestParam("type") Integer type,
                             @RequestParam("sort") Integer sort,
                             @RequestParam(value = "pid",required = false) Integer pid,
                             @RequestParam(value = "handling", required = false) String handling) {
        int result = authService.addResource(name, extensionName, type, sort, pid, handling);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.AUTH_RESOURCE_NAME_REPEAT, "名称重复");
                break;
            case 2:
                tip(model, CodeConstants.AUTH_RESOURCE_EXTENSION_NAME_REPEAT, "扩展名重复");
                break;
        }
    }

    /**
     * 更新资源
     * @param id 编号
     * @param name 名称
     * @param extensionName 扩展名
     * @param type 类型
     * @param sort 排序
     * @param pid 父级编号
     * @param handling 操作
     */
    @RequestMapping("modify-resource")
    public void modifyResource (Model model,
                                @RequestParam("id") Integer id,
                                @RequestParam("name") String name,
                                @RequestParam("extensionName") String extensionName,
                                @RequestParam("type") Integer type,
                                @RequestParam("sort") Integer sort,
                                @RequestParam(value = "pid",required = false) Integer pid,
                                @RequestParam(value = "handling", required = false) String handling) {
        int result = authService.modifyResource(id, name, extensionName, type, sort, pid, handling);
        switch (result) {
            case 0:
                break;
            case 1:
                tip(model, CodeConstants.AUTH_RESOURCE_NAME_REPEAT, "名称重复");
                break;
            case 2:
                tip(model, CodeConstants.AUTH_RESOURCE_EXTENSION_NAME_REPEAT, "扩展名重复");
                break;
            case 3:
                tip(model, CodeConstants.AUTH_RESOURCE_NOT_EXIST, "资源不存在");
                break;
        }
    }
}
