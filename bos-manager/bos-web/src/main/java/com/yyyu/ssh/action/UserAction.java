package com.yyyu.ssh.action;

import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.service.inter.IUserService;
import com.yyyu.ssh.templete.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
            @Result(name = ERROR , location = "/WEB-INF/view/user/login.jsp"),
            @Result(name = "error_500"  , location="/WEB-INF/view/error/error500.jsp")
    })
    public String checkUser(){
        String username = getModel().getUsername();
        String password = getModel().getPassword();
        try {
            User user = userService.getUserByUsernameAndPwd(username, password);
            if (user!=null){
                getSession().put("user" , user);
                return SUCCESS;
            }else{
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            getSession().put("errorInfo" , e.getMessage());
            return "error_500";
        }
    }


    @Action(value = "geUserInfo")
    public void geUserInfo(){
        User user = userService.get(getModel().getId());
        printJson(user , null);
    }

}
