package com.business.work.base.shiro;//package com.business.workshop.base.shiro;

import com.business.core.entity.auth.Admin;
import com.business.core.entity.auth.Resource;
import com.business.core.entity.auth.Role;
import com.business.core.utils.BeanManager;
import com.business.core.utils.CollectionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 角色认证过滤器
* User: yunai
* Date: 13-9-6
* Time: 下午3:20
*/

public class MyRolesAuthorizationFilter extends AuthorizationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRolesAuthorizationFilter.class);


    private static Map<String, Resource> handlingMap = new LinkedHashMap<>(0);

    private static List<MenuNode> menuNodes = new ArrayList<>(0);

    private static Map<String, List<String>> operationRoleMap = new LinkedHashMap<>(0);

    public void init() {
        clearInitData();
        com.business.work.auth.AuthDao authDao = BeanManager.getBean(com.business.work.auth.AuthDao.class);
        List<Resource> resources = authDao.findAllResource();
        if (!CollectionUtils.isEmpty(resources)) {
            Map<Integer, List<Resource>> resourceWithTypeMap = CollectionUtil.buildMultimap(resources, Integer.class, Resource.class, "type");
            List<Resource> menuResourceList = new ArrayList<>(0);
            menuResourceList.addAll(resourceWithTypeMap.get(Resource.TYPE_ROOT));
            menuResourceList.addAll(resourceWithTypeMap.get(Resource.TYPE_MENU));
            handlingMap = CollectionUtil.buildMap(menuResourceList, String.class, Resource.class, "handling");
            // 处理 menuNode
            Map<Integer, List<Resource>> resourcePidListMap = CollectionUtil.buildMultimap(resources, Integer.class, Resource.class, "pid");
            for (Resource resource : resourcePidListMap.get(null)) {
                List<MenuNode> childrenMenuNodeList = new ArrayList<>(0);
                List<Resource> childrenResourceList = resourcePidListMap.get(resource.getId());
                if (!CollectionUtils.isEmpty(childrenResourceList)) {
                    for (Resource childrenResource: childrenResourceList) {
                        MenuNode childrenMenuNode = new MenuNode(childrenResource, childrenResource.getRoles(), null);
                        childrenMenuNodeList.add(childrenMenuNode);
                    }
                }
                menuNodes.add(new MenuNode(resource, resource.getRoles(), childrenMenuNodeList));
            }
            // 处理 operation 操作
            List<Resource> operationResourceList = resourceWithTypeMap.get(Resource.TYPE_OPERATION);
            for (Resource resource :operationResourceList) {
                operationRoleMap.put(resource.getHandling(), resource.getRoles());
            }
        }
    }

    public void clearInitData () {
        handlingMap.clear();
        menuNodes.clear();
        operationRoleMap.clear();
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
//        if (!subject.isAuthenticated()) {
//            return false;
//        }
        // 判断是否有权限
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI().replaceFirst(req.getContextPath(), "").trim();
        Resource resource = handlingMap.get(removeURLPrefix(url));
        if (resource == null || CollectionUtils.isEmpty(resource.getRoles())) {
            onAccessAllowed(request, response);
            return true;
        }
        boolean access = hasOneRole(subject, resource.getRoles());
        if (access) {
            onAccessAllowed(request, response);
            return true;
        }
        return false;
    }

    /**
     * 当进入被允许时，进行一些公用的调用
     *
     * @param request  请求
     * @param response 响应
     */
    private void onAccessAllowed(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURI().endsWith(".htm")) {
            onHtmAccessAllowed(request, response);
        }
    }


    /**
     * 当后缀为".htm"的请求被允许时，进行菜单的加载
     *
     * @param request  请求
     * @param response 响应
     */
    private void onHtmAccessAllowed(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        List<Resource> menus = new ArrayList<>();
        for (MenuNode node : menuNodes) {
            List<Resource> children = new ArrayList<>();
            for (MenuNode subNode : node.getChildren()) {
                Admin admin= (Admin) SecurityUtils.getSubject().getPrincipal();
                if(admin!=null) {
                    if (admin.getRoles().contains(Role.ROLE_ROOT) || hasOneRole(subject, subNode.getRoles())) {
                        //lvjj 2018-08-06
//                    if (((Admin) SecurityUtils.getSubject().getPrincipal()).getRoles().contains(Role.ROLE_ROOT) || hasOneRole(subject, subNode.getRoles())) {
                        children.add(subNode.getMenu());
                    }
                }
            }
            if (!children.isEmpty()) {
                Resource parent = new Resource();
                parent.setHandling(node.getMenu().getHandling());
                parent.setName(node.getMenu().getName());
                parent.setExtensionName(node.getMenu().getExtensionName());
                parent.setType(node.getMenu().getType());
                parent.setChildren(children);
                menus.add(parent);
            }
        }
        request.setAttribute("authMenus", menus);
    }


    /**
     * 移除地址后缀，比如.htm/.json等<br />
     * 移除地址前缀的/
     *
     * @param url 地址
     * @return 移除地址后缀后的地址
     */
    private static String removeURLSuffix(String url) {
        if (url.startsWith("/")) {
            url = url.substring(1);
        }
        int index = url.lastIndexOf('.');
        return index < 0 ? url : url.substring(0, index);
    }

    /**
     * 移除地址前缀，比如 /login.htm, login.htm 等<br />
     * @param url
     * @return
     */
    private static String removeURLPrefix (String url) {
        if (url.startsWith("/")) {
            url = url.substring(1);
        }
        return url;
    }

    /**
     * 判断主体是否拥有角色里的某个
     *
     * @param subject 主体
     * @param roles   角色
     * @return 是否拥有
     */
    private static boolean hasOneRole(Subject subject, List<String> roles) {
        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param url url地址
     * @return 是否有该地址权限
     */
    public static boolean authorizating(String url) {
//        url = removeURLSuffix(url);
        List<String> roles = operationRoleMap.get(removeURLPrefix(url));
        return org.apache.shiro.util.CollectionUtils.isEmpty(roles) || hasOneRole(SecurityUtils.getSubject(), roles);
    }
}