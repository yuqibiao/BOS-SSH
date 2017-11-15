package com.yyyu.ssh.shiro.realm;

import com.yyyu.ssh.dao.bean.SelectRole;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.domain.SysUserToken;
import com.yyyu.ssh.service.inter.IUserRoleService;
import com.yyyu.ssh.service.inter.IUserTokenService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能：无状态realm
 *
 * @author yu
 * @date 2017/11/14.
 */
public class StatelessRealm extends AuthorizingRealm{

    @Autowired
    private IUserTokenService userTokenService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof  StatelessToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.err.println("================StatelessRealm========AuthorizationInfo=========");

        String userId = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        List<SelectRole> roleList= userRoleService.getAllRoleByUserId(Long.parseLong(userId));
        Set<String> roleCodeSet = new HashSet<>();
        for (SysRole sysRole:roleList) {
            roleCodeSet.add(sysRole.getCode());
        }
        //--TODO 把权限信息加入内存缓存中
        //添加角色/权限
        authorizationInfo.setRoles(roleCodeSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StatelessToken token = (StatelessToken) authenticationToken;
        String userId = token.getUserId();
        SysUserToken userToken = userTokenService.getUserTokenByUserId(Long.parseLong(userId));
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userId, userToken.getUserToken() , getName());
        return info;
    }


}
