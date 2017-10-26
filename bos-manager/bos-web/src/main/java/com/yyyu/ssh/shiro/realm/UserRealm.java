package com.yyyu.ssh.shiro.realm;

import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.service.inter.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/26.
 */
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 1. 把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2.取出用户名
        String username = usernamePasswordToken.getUsername();
        User user = userService.getUserByUsername(username);
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
