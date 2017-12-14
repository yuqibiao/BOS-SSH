package com.yyyu.ssh.shiro.auth.pam;

import com.yyyu.ssh.shiro.auth.token.CustomizedToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/12/14.
 */
public class CustomizedModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {

        if (authenticationToken instanceof  CustomizedToken){
            CustomizedToken customizedToken = (CustomizedToken) authenticationToken;
            Class realmType = customizedToken.getRealmType();
            assertRealmsConfigured();
            //得到所有realm
            Collection<Realm> realms = this.getRealms();
            //过滤出对应登录类型对应的realm
            Collection<Realm> typeRealms = new ArrayList<>();
            for (Realm realm : realms) {
                Class<? extends Realm> aClass = realm.getClass();
                if (aClass.equals(realmType) ){
                    typeRealms.add(realm);
                }
            }
            return typeRealms.size() == 1?
                    doSingleRealmAuthentication(typeRealms.iterator().next(), customizedToken)
                    :doMultiRealmAuthentication(typeRealms, customizedToken);
        }else{
            return super.doAuthenticate(authenticationToken);
        }
    }
}
