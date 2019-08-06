package com.business.work.auth;

import com.business.core.entity.auth.Admin;
import com.business.core.entity.auth.Resource;
import com.business.core.entity.auth.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/4/21.
 */
@Service
public class AuthService {

    @Autowired
    private AuthDao authDao;

    @Autowired
    private com.business.work.base.shiro.MyRolesAuthorizationFilter authorizationFilter;

    ///
    /// admin 管理员

    /**
     * 所有 admin
     * @return admin 集合
     */
    public List<Admin> allAdmin () {
        return authDao.findAllAdmin();
    }

    /**
     * admin 添加
     * @param loginName 用户登录名
     * @param name 用户名
     * @param password 用户密码
     * @param activate
     * @return 0 成功， 1 loginName 重复
     */
    public int addAdmin (String loginName, String name, String password, Integer activate) {
        if (authDao.findAdminByLoginName(loginName, "id") != null) {
            return 1;
        }
        Admin admin = new Admin();
        admin.setLoginName(loginName);
        admin.setActivate(activate);
        admin.setName(name);
        admin.setPassword(password);
        admin.setLoginCount(0);
        admin.setAddTime(new Date());
        admin.setRoles(Collections.<String>emptyList()); // 设置默认值
        authDao.insertAdmin(admin);
        return 0;
    }

    /**
     * admin 信息更新
     * @param id 编号
     * @param name 名称
     * @param password 密码
     * @return 0 成功， 1 admin 不存在
     */
    public int modifyAdmin (Integer id, String name, String password, Integer activate) {
        if (authDao.findAdminById(id, "id") == null) {
            return 1;
        }
        authDao.updateAdminById(id, Update.update("name", name).set("password", password).set("activate", activate));
        return 0;
    }

    /**
     * 更新 admin roles 信息
     * @param id admin 编号
     * @param roles role集合 -- > name
     * @return 0 成功， 1 admin 不存在
     */
    public int adminRole (Integer id, List<String> roles) {
        if (authDao.findAdminById(id, "id") == null) {
            return 1;
        }
        if (CollectionUtils.isEmpty(roles)) {
            roles = Collections.emptyList();
        }
        authDao.updateAdminById(id, new Update().set("roles", roles));
        authorizationFilter.init();
        return 0;
    }

    ///
    /// role 角色

    /**
     * 角色管理
     * @return role 集合
     */
    public List<Role> allRole () {
        return authDao.findAllRole();
    }

    /**
     * 添加 role
     * @param name 用户名
     * @param extensionName 扩展名
     * @param des 描述
     * @return 0 成功， 1 名称重复
     */
    public int addRole (String name, String extensionName, String des) {
        if (authDao.findRoleByName(name) != null) {
            return 1;
        }
        Role role = new Role();
        role.setName(name);
        role.setExtensionName(extensionName);
        role.setDes(des);
        role.setAddTime(new Date());
        authDao.insertRole(role);
        return 0;
    }

    /**
     * 更新 role
     * @param id 编号
     * @param name 名称
     * @param extensionName 扩展名
     * @param des 描述
     * @return 0 成功， 1 role不存在， 2 名称重复
     */
    public int modifyRole (Integer id, String name, String extensionName, String des) {
        Role role = authDao.findRoleByName(name);
        if (!role.getId().equals(id) && authDao.findRoleByName(name) != null) {
            return 1;
        }
        if (authDao.findRoleById(id) == null) {
            return 2;
        }
        authDao.updateRoleById(id, Update.update("name", name).set("extensionName", extensionName).set("des", des));
        return 0;
    }

    /**
     * role 拥有的 resource
     * @param id role 编号
     * @return 0 成功， 1 role 不存在
     */
    public Object[] roleResource (Integer id) {
        Role role = authDao.findRoleById(id, "name");
        if (role == null) {
            return new Object[]{1};
        }
        return new Object[]{0, authDao.findResourceByRoles(Arrays.asList(role.getName()))};
    }

    /**
     * 更新 resource role
     * @param id role
     * @param resources
     * @return
     */
    public int roleResource (Integer id, List<Integer> resources) {
        Role role = authDao.findRoleById(id, "name");
        if (role == null) {
            return 1;
        }
        authDao.updateResourceByIds(resources, new Update().addToSet("roles", role.getName()));
        authDao.updateResourceByNotIds(resources, new Update().pull("roles", role.getName()));
        authorizationFilter.init();
        return 0;
    }

    ///
    /// Resource 资源

    /**
     * 所有资源信息
     * @return
     */
    public List<Resource> allResource () {
        return authDao.findAllResource();
    }

    /**
     * 添加资源
     * @param name 名称
     * @param extensionName 扩展名
     * @param type 类型
     * @param sort 排序
     * @param pid 父级资源编号
     * @param handling 操作
     * @return 0 成功， 1 名称重复， 2 扩展名重复
     */
    public int addResource (String name, String extensionName, Integer type, Integer sort, Integer pid, String handling) {
        if (authDao.findResourceByName(name) != null) {
            return 1;
        }
        if (authDao.findResourceByExtensionName(extensionName) != null) {
            return 2;
        }
        Resource resource = new Resource();
        resource.setName(name);
        resource.setExtensionName(extensionName);
        resource.setType(type);
        resource.setSort(sort);
        resource.setAddTime(new Date());
        if (!type.equals(Resource.TYPE_ROOT)) {
            resource.setPid(pid);
            resource.setHandling(handling);
        }
        // 初始化
        resource.setRoles(Collections.<String>emptyList());
        authDao.insertResource(resource);
        authorizationFilter.init();
        return 0;
    }

    /**
     * 修改 Resource
     * @param id 编号
     * @param name 名称
     * @param extensionName 扩展名
     * @param type 类型
     * @param sort 排序
     * @param pid 父级资源编号
     * @param handling 操作
     * @return 0 成功， 1 名称重复， 2 扩展名重复， 3 资源不存在
     */
    public int modifyResource (Integer id, String name, String extensionName, Integer type, Integer sort, Integer pid, String handling) {

        if (authDao.findResourceById(id) == null) {
            return 3;
        }
        Resource resource = authDao.findResourceByName(name);
        if (resource != null && !resource.getId().equals(id)) {
            return 1;
        }
        resource = authDao.findResourceByExtensionName(extensionName);
        if (authDao.findResourceByExtensionName(extensionName) != null && !resource.getId().equals(id)) {
            return 2;
        }
        Update update = new Update().set("name", name).set("extensionName", extensionName).set("type", type).set("sort", sort);
        if (!type.equals(Resource.TYPE_ROOT)) {
            Assert.notNull(pid);
            update.set("pid", pid).set("handling", handling);
        }
        else {
            update.set("pid", "").set("handling", "");
        }
        authDao.updateResourceById(id, update);
        return 0;
    }

}
