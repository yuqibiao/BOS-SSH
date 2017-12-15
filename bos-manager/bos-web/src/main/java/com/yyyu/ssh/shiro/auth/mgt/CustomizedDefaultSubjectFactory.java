package com.yyyu.ssh.shiro.auth.mgt;

import com.yyyu.ssh.shiro.auth.LoginType;
import com.yyyu.ssh.shiro.auth.token.CustomizedToken;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.subject.WebSubjectContext;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 功能：无状态SubjectFactory
 *
 * @author yu
 * @date 2017/11/14.
 */
public class CustomizedDefaultSubjectFactory extends DefaultWebSubjectFactory {

    Logger logger = Logger.getLogger(CustomizedDefaultSubjectFactory.class);

    private DefaultSessionStorageEvaluator  storageEvaluator;

    public CustomizedDefaultSubjectFactory(DefaultSessionStorageEvaluator  storageEvaluator) {
        this.storageEvaluator = storageEvaluator;
        //logger.info("=========-=================CustomizedDefaultSubjectFactory========");
    }

    @Override
    public Subject createSubject(SubjectContext context) {
        this.storageEvaluator.setSessionStorageEnabled(true);
        //logger.info("=========createSubject===token="+token);
        WebSubjectContext wsc = (WebSubjectContext)context;
        ServletRequest servletRequest = wsc.resolveServletRequest();
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            this.storageEvaluator.setSessionStorageEnabled(false);
            String headerName = headerNames.nextElement().toString();
            if (headerName.equalsIgnoreCase("token")){//header中有token参数，说明是stateless请求
                context.setSessionCreationEnabled(false);
                //logger.info("=========STATELESS====不创建session");
                break;
            }
        }
        return super.createSubject(context);
    }
}
