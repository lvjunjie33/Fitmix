package com.business.work.base.shiro;

import com.business.core.entity.auth.Admin;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

/**
 * 描述:
 * User: jeff
 * Time: 12-8-14  上午1:49
 */
public class MyDbRealm extends AuthorizingRealm {

    @Autowired
    private com.business.work.auth.AuthDao authDao;

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        Admin admin = authDao.findAdminByLoginName(upToken.getUsername());
        // 帐号不存在
        if (admin == null) {
            throw new UnknownAccountException();
        }
        // 帐号被禁用
        if (Admin.ACTIVATE_2.equals(admin.getActivate())) {
            throw new DisabledAccountException();
        }
        // 密码错误
        if (!admin.getPassword().equals(new String(upToken.getPassword()))) {
            throw new IncorrectCredentialsException ();
        }
        return new SimpleAuthenticationInfo(admin, admin.getPassword(), getName());
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无患者的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Admin admin = (Admin) principals.fromRealm(getName()).iterator().next();
        // 处理角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Admin adminRoles = authDao.findAdminByLoginName(admin.getLoginName());
        // 添加 role
        for (String  role : adminRoles.getRoles()) {
            info.addRole(role);
        }
        // 更新 admin 信息
        authDao.updateAdminById(admin.getId(), Update.update("lastLoginTime", new Date()).inc("loginCount", 1));
        return info;
    }
}
