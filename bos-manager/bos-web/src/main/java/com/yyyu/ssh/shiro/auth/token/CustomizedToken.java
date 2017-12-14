package com.yyyu.ssh.shiro.auth.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 功能：自定义Token、有loginType
 *
 * @author yu
 * @date 2017/12/14.
 */
public class CustomizedToken extends UsernamePasswordToken{

    protected Class realmType;

    public CustomizedToken(String username, String password, Class realmType) {
        super(username, password);
        this.realmType = realmType;
    }

    public Class getRealmType() {
        return realmType;
    }

    public void setRealmType(Class realmType) {
        this.realmType = realmType;
    }
}
