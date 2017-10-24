package com.yyyu.ssh.action;

import com.yyyu.ssh.dao.bean.UserDataTablesReturn;
import com.yyyu.ssh.dao.bean.UserReturn;
import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.service.inter.IUserService;
import com.yyyu.ssh.templete.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
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
        try {

            String draw = getRequestParam("draw");
            String start = getRequestParam("start");
            String length = getRequestParam("length");
            String requestParam = getRequestParam("order[0][column]");
            String srarchValue = getRequestParam("search[value]");

            UserDataTablesReturn userDataTablesReturn = new UserDataTablesReturn();
            Integer usersTotal = userService.getUsersTotal();
            List<User> userList = userService.getUserByPage(0,10);
            if (draw!=null&&!"".equals(draw)){
                userDataTablesReturn.setDraw(Integer.parseInt(draw));
            }
            userDataTablesReturn.setRecordsTotal(usersTotal);
            userDataTablesReturn.setRecordsFiltered(usersTotal);
            List<UserReturn> userReturnList = new ArrayList<>();
            for (User user: userList) {
                UserReturn userReturn = new UserReturn();
                userReturn.setId(user.getId());
                userReturn.setUsername(user.getUsername());
                userReturn.setSalary(user.getSalary());
                userReturn.setBirthday(user.getBirthday());
                userReturn.setGender(user.getGender());
                userReturn.setRemark(user.getRemark());
                userReturn.setStation(user.getStation());
                userReturn.setTelephone(user.getTelephone());
                userReturnList.add(userReturn);
            }
            userDataTablesReturn.setData(userReturnList);
            printJson(userDataTablesReturn , null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
