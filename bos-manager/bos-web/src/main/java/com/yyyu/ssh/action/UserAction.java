package com.yyyu.ssh.action;

import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.service.inter.IUserService;
import com.yyyu.ssh.templete.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 功能：User相关请求Action
 *
 * @author yu
 * @date 2017/8/30.
 */
@Controller
@Namespace("/bos/user")
public class UserAction extends BaseAction<User>{

    @Autowired
    private IUserService userService;

    @Action(value = "geUserInfo")
    public void geUserInfo(){
        User user = userService.get(getModel().getId());
        printJson(user , null);
    }

}
