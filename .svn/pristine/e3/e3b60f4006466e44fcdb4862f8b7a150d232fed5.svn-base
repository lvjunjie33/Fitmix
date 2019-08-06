package com.business.work.base.shiro;

import com.business.core.entity.auth.Resource;

import java.util.List;

/**
 * 菜单节点
 * menuNode
 */
public class MenuNode {

    /**
     * 菜单资源
     */
    private Resource menu;
    /**
     * 角色数组
     */
    private List<String> roles;
    /**
     * 子菜单资源数组
     */
    private List<MenuNode> children;

    /**
     * 创建菜单节点
     *
     * @param menu     菜单资源
     * @param roles    角色数组
     * @param children 子菜单资源数组
     */
    public MenuNode(Resource menu, List<String> roles, List<MenuNode> children) {
        this.menu = menu;
        this.roles = roles;
        this.children = children;
    }

    public Resource getMenu() {
        return menu;
    }

    public void setMenu(Resource menu) {
        this.menu = menu;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<MenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }
}