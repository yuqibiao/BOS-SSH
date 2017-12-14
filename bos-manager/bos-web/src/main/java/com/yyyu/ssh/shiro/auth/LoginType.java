package com.yyyu.ssh.shiro.auth;

import com.yyyu.ssh.shiro.auth.realm.StatelessRealm;
import com.yyyu.ssh.shiro.auth.realm.UserRealm;

/**
 * 功能：登录类别
 *
 * @author yu
 * @date 2017/12/14.
 */
public enum LoginType {

    WEB(UserRealm.class),
    STATELESS(StatelessRealm.class);

    private Class realmName;//xml中配置的realm名字

    LoginType(Class type) {
        this.realmName = type;
    }

    public Class getRealmName() {
        return realmName;
    }

    public void setRealmName(Class realmName) {
        this.realmName = realmName;
    }
}
