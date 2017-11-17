package com.yyyu.ssh.shiro.filter;

import com.yyyu.ssh.shiro.realm.StatelessToken;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能：无状态认证拦截器（Restful 风格API）
 *
 * @author yu
 * @date 2017/11/14.
 */
public class StatelessAuthcFilter extends AccessControlFilter{

    Logger logger = Logger.getLogger(StatelessAuthcFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //1.获取客户端传过来的身份信息（digest）
        String digest = servletRequest.getParameter("token");
        //TODO token凭证解密操作
        String decodeDigest = digest;
        //查询该digest对应的用户名
        String userId = servletRequest.getParameter("userId");
        //客户端请求参数列表
        Map<String, String[]> params = new HashMap<>();
        params.remove("token");
        //生成无状态Token
        StatelessToken token = new StatelessToken(userId , params , decodeDigest);
        //委托给realm进行登录
        try {
            Subject subject = getSubject(servletRequest, servletResponse);
            String sessionId = subject.getSession().getId().toString();
            logger.info("sessionId==========="+sessionId);
            subject.getSession().setTimeout(0);
            subject.login(token);
            //.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
