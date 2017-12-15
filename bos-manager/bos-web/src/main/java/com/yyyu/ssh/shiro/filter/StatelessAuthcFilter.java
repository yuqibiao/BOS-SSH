package com.yyyu.ssh.shiro.filter;

import com.google.gson.Gson;
import com.yyyu.ssh.shiro.auth.token.StatelessToken;
import com.yyyu.ssh.utils.ResultUtils;
import com.yyyu.ssh.utils.TextUtils;
import com.yyyu.ssh.utils.bean.BaseJsonResult;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
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

        HttpServletRequest request = WebUtils.toHttp(servletRequest);

        try {
            //1.获取客户端传过来的身份信息（digest）
            String digest = request.getHeader("token");
            if (TextUtils.isEmpty(digest)){
                throw new UnsupportedOperationException("传入的token为空");
            }
            //TODO token凭证解密操作
            String decodeDigest = digest;
            //查询该digest对应的用户名
            String userId = request.getHeader("userId");
            //客户端请求参数列表
            Map<String, String[]> params = new HashMap<>();
            params.remove("token");
            //生成无状态Token
            StatelessToken token = new StatelessToken(userId , params , decodeDigest);
            Subject subject = getSubject(servletRequest, servletResponse);
            //委托给realm进行登录
            subject.login(token);
        } catch (AuthenticationException e) {
            BaseJsonResult error = ResultUtils.error(401, e.getMessage());
            if (e instanceof IncorrectCredentialsException){
                error.setMsg("认证信息有误");
            }
            servletResponse.setContentType("text/json;charset=utf-8");
            String result = new Gson().toJson(error);
            servletResponse.getWriter().print(result);
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
