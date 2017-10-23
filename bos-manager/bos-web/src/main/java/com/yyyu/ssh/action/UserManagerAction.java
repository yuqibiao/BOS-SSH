package com.yyyu.ssh.action;

import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.service.inter.IUserService;
import com.yyyu.ssh.templete.BaseAction;
import com.yyyu.ssh.utils.ResultUtils;
import com.yyyu.ssh.utils.bean.BaseJsonResult;
import com.yyyu.ssh.utils.page.Page;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 功能：用户管理Action
 *
 * @author yu
 * @date 2017/10/23.
 */
@Controller
@Scope("prototype")
@Namespace("/userManager")
public class UserManagerAction extends BaseAction<User>{

    @Autowired
    private IUserService userService;

    @Action(value = "getUserByPage")
    public void getUserByPage(){
        BaseJsonResult result ;
        try {
            Page<User> page = new Page<>();
            Integer usersTotal = userService.getUsersTotal();
            List<User> userList = userService.getUserByPage(0,10);
            page.setRows(userList);
            page.setTotal(usersTotal);
            getSession().put("page" , page);
            result = ResultUtils.success(userList);
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultUtils.error(500 , e.getMessage());
        }
        printJson(result , new String[]{"password" , "birthday" ,"noticebills" ,"roles","code" , "msg"});
    }

}
