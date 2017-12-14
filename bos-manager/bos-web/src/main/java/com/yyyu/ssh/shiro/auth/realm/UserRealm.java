package com.yyyu.ssh.shiro.auth.realm;

import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.domain.SysUser;
import com.yyyu.ssh.service.inter.IRolePermissionsService;
import com.yyyu.ssh.service.inter.IUserRoleService;
import com.yyyu.ssh.service.inter.IUserService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/26.
 */
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRolePermissionsService rolePermissionsService;

    Logger logger = Logger.getLogger(StatelessRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        logger.info("================UserRealm========AuthorizationInfo=========");

        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<SysRole> roleCodeList = userRoleService.getRoleByUsername(username);
        Set<String> roleCodeSet = new HashSet<>();
        Set<String> perCodeSet = new HashSet<>();
        for (SysRole sysRole:roleCodeList) {
            roleCodeSet.add(sysRole.getCode());
            long roleId = sysRole.getRoleId();
            List<SysPermissions> permissionsList = rolePermissionsService.getPermissionByRoleId(roleId);
            Set<String> perCodeSetSub = new HashSet<>();
            for (SysPermissions sysPermissions:permissionsList) {
                perCodeSetSub.add(sysPermissions.getCode());
            }
            perCodeSet.addAll(perCodeSetSub);
        }
        //1.设置角色编号
        authorizationInfo.setRoles(roleCodeSet);
        //2.设置权限编号
        authorizationInfo.setStringPermissions(perCodeSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 1. 把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2.取出用户名
        String username = usernamePasswordToken.getUsername();
        SysUser user = userService.getUserByUsername(username);
        if (user==null){
            throw new UnknownAccountException("用户不存在");
        }
        String password = user.getPassword();
        String realmName = getName();
        ByteSource salt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username , password , salt , realmName);
        return info;
    }
}
