package com.yyyu.ssh.action;

import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.service.inter.IUserService;
import com.yyyu.ssh.templete.BaseAction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

/**
 * 功能：User相关请求Action
 *
 * @author yu
 * @date 2017/8/30.
 */
@Controller
@Scope("prototype")
@Namespace("/user")
public class UserAction extends BaseAction<User>{

    @Autowired
    private IUserService userService;


    @Action(value="checkUser" ,results = {
            @Result(name = SUCCESS  ,location = "/WEB-INF/view/user/userManager.jsp" ),
            @Result(name = ERROR , location = "/login.jsp"),
            @Result(name = "error_500"  , location="/WEB-INF/view/error/error500.jsp")
    })
    public String checkUser(){
        String username = getModel().getUsername();
        String password = getModel().getPassword();

        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser!=null){
            UsernamePasswordToken token = new UsernamePasswordToken(username , password);
            token.setRememberMe(false);
            try {
                currentUser.login(token);
                return SUCCESS;
            } catch (IncorrectCredentialsException e) {
                System.out.println("用户名密码不正确");
            }catch (LockedAccountException lae) {
                System.out.println("账户已被冻结！");
            } catch (AuthenticationException ae) {
                System.out.println(ae.getMessage());
            }
        }
        return ERROR;

    }


    @Action(value = "geUserInfo")
    public void geUserInfo(){
        User user = userService.get(getModel().getId());
        printJson(user , null);
    }

}
