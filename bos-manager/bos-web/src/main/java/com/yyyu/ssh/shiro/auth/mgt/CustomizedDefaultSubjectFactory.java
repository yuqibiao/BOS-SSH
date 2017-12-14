package com.yyyu.ssh.shiro.auth.mgt;

import com.yyyu.ssh.shiro.auth.LoginType;
import com.yyyu.ssh.shiro.auth.token.CustomizedToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 功能：无状态SubjectFactory
 *
 * @author yu
 * @date 2017/11/14.
 */
public class CustomizedDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        //获取token 得到loginType判断是否创建session
        AuthenticationToken token = context.getAuthenticationToken();
        if (token instanceof CustomizedToken){
            CustomizedToken customizedToken = (CustomizedToken) token;
            Class realmType = customizedToken.getRealmType();
            if (realmType==LoginType.STATELESS.getRealmName()){//无状态
                //不创建session
                context.setSessionCreationEnabled(false);
            }else{//有状态

            }
        }else{

        }
        return super.createSubject(context);
    }
}
