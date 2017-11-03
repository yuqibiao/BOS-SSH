package com.yyyu.ssh.action;

import com.yyyu.ssh.TextUtils;
import com.yyyu.ssh.dao.bean.TreeNode;
import com.yyyu.ssh.dao.bean.UserDataTablesReturn;
import com.yyyu.ssh.dao.bean.UserReturn;
import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.domain.SysUser;
import com.yyyu.ssh.service.inter.IUserService;
import com.yyyu.ssh.shiro.encrypt.PasswordEncrypt;
import com.yyyu.ssh.templete.BaseAction;
import com.yyyu.ssh.utils.ResultUtils;
import com.yyyu.ssh.utils.TypeConversion;
import com.yyyu.ssh.utils.bean.BaseJsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
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
@Namespace("/user")
public class UserAction extends BaseAction<SysUser> {

    @Autowired
    private IUserService userService;

    private String[] columns = new String[]{"userId", "username", "salary", "telephone", "gender", "remark"};
    private String orderName;
    private String orderDid;

    @Action(value = "getUserByPage")
    public void getUserByPage() {
        try {
            //draw的次数，原样返回
            String draw = getRequestParam("draw");
            //开始行
            String start = getRequestParam("start");
            //分页长度
            String length = getRequestParam("length");
            //需要排序列的索引
            String orderColumn = getRequestParam("order[0][column]");
            //过滤条件
            String searchValue = getRequestParam("search[value]");

            UserDataTablesReturn userDataTablesReturn = new UserDataTablesReturn();
            Integer usersTotal = userService.getUsersTotal();
            int startInt = TypeConversion.str2Int(start);
            int lengthInt = TypeConversion.str2Int(length, 10);
            DetachedCriteria criteria = userService.getCriteria();
            if (!TextUtils.isEmpty(searchValue)) {//搜索
                criteria.add(Restrictions.or(
                        Restrictions.like("tel", searchValue + "%"),
                        Restrictions.like("username", searchValue + "%")
                ));
            }
            if (!TextUtils.isEmpty(orderColumn)) {//排序(不为空时赋值，为空用原始排序规则)
                //需要排序的列名
                orderName = columns[TypeConversion.str2Int(orderColumn, 0)];
                //排序方式 asc des
                orderDid = getRequestParam("order[0][dir]");
            }
            if ("asc".equalsIgnoreCase(orderDid)) {
                criteria.addOrder(Order.asc(orderName));
            } else if ("desc".equalsIgnoreCase(orderDid)) {
                criteria.addOrder(Order.desc(orderName));
            }
            List<SysUser> userList = userService.getUserByPage(criteria, startInt, lengthInt);
            userDataTablesReturn.setDraw(TypeConversion.str2Int(draw, 0));
            userDataTablesReturn.setRecordsTotal(usersTotal);
            userDataTablesReturn.setRecordsFiltered(usersTotal);
            List<UserReturn> userReturnList = new ArrayList<>();
            for (SysUser user : userList) {
                UserReturn userReturn = new UserReturn();
                userReturn.setUserId(user.getUserId());
                userReturn.setUsername(user.getUsername());
                userReturn.setSalary(user.getSalary());
                userReturn.setBirthday(user.getBirthday());
                userReturn.setGender(user.getGender());
                userReturn.setRemark(user.getRemark());
                userReturn.setStation(user.getStation());
                userReturn.setTel(user.getTel());
                userReturnList.add(userReturn);
            }
            userDataTablesReturn.setData(userReturnList);
            printJson(userDataTablesReturn, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过userId得到用户对应的菜单
     * 用户展示用户菜单
     */
    @Action("getUserPermissionsByUserId")
    public void getUserMenusByUserId() {

    }

    /**
     * 通过userId得到所有的权限
     * 用户没有的权限checked为false
     */
    @Action("geAllPermissionsByUserId")
    public void geAllPermissionsByUserId() {
        Long userId = getModel().getUserId();
        BaseJsonResult result;
        try {
            List<TreeNode> nodeList = userService.getAllPermissionByUserId(userId);
            result = ResultUtils.success(nodeList);
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultUtils.error(500, e.getMessage());
        }
        printJson(result, null);
    }

    @Action("addUser")
    public void addUser() {
        BaseJsonResult result;
        SysUser model = getModel();
        try {
            String username = getModel().getUsername();
            String pwd = getModel().getPassword();
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
                result = ResultUtils.error(501, "用户名或密码存在空值");
            } else if (userService.hasUser(username)) {
                result = ResultUtils.error(502, "该用户名已经存在");
            } else {
                model.setPassword(PasswordEncrypt.Md5(1, username, pwd));
                userService.save(model);
                result = ResultUtils.success("注册成功");
            }
        } catch (Exception e) {
            result = ResultUtils.error(500, e.getMessage());
            e.printStackTrace();
        }
        printJson(result, null);
    }

    @Action("deleteUser")
    public void deleteUser(){
        long userId = getModel().getUserId();
        BaseJsonResult result;
        try {
            userService.deleteUserById(userId);
            result = ResultUtils.success("删除成功");
        } catch (Exception e) {
            result = ResultUtils.error(500 , e.getMessage());
            e.printStackTrace();
        }
        printJson(result , null);
    }


    @Action("modifyUser")
    public void modifyUser() {
        long userId = getModel().getUserId();
        String username = getModel().getUsername();
        String password = getModel().getPassword();
        String tel = getModel().getTel();
        String salt = getModel().getSalt();
        String station = getModel().getStation();
        Integer salary = getModel().getSalary();
        String gender = getModel().getGender();
        String remark = getModel().getRemark();
        Timestamp birthday = getModel().getBirthday();
        Byte locked = getModel().getLocked();

        SysUser user = userService.getUserById(userId);

        BaseJsonResult result;
        try {
            if (userService.hasUser(username)){
                result = ResultUtils.error(502, "用户名已被注册");
            }else{
                if (!TextUtils.isEmpty(username)) {
                    user.setUsername(username);
                }
                if (!TextUtils.isEmpty(password)) {
                    user.setPassword(password);
                }
                if (!TextUtils.isEmpty(tel)) {
                    user.setTel(tel);
                }
                if (!TextUtils.isEmpty(salt)) {
                    user.setSalt(salt);
                }
                if (!TextUtils.isEmpty(station)) {
                    user.setStation(station);
                }
                if (!TextUtils.isEmpty(remark)) {
                    user.setRemark(remark);
                }
                if (!TextUtils.isEmpty(gender)) {
                    user.setGender(gender);
                }
                if (salary != null) {
                    user.setSalary(salary);
                }
                if (birthday != null) {
                    user.setBirthday(birthday);
                }
                if (locked != null) {
                    user.setLocked(locked);
                }
                userService.modifyUser(user);
                result = ResultUtils.success("修改成功");
            }
        } catch (Exception e) {
            result = ResultUtils.error(500, e.getMessage());
            e.printStackTrace();
        }
        printJson(result, null);

    }

    @Action(value = "checkUser", results = {
            @Result(name = SUCCESS, location = "/WEB-INF/view/user/userManager.jsp"),
            @Result(name = ERROR, location = "/login.jsp"),
            @Result(name = "error_500", location = "/WEB-INF/view/error/error500.jsp")
    })
    public String checkUser() {
        String username = getModel().getUsername();
        String password = getModel().getPassword();

        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(false);
            try {
                currentUser.login(token);
                SysUser loginUser = userService.getUserByUsername(username);
                setSessionValue("user" , loginUser);
                return SUCCESS;
            } catch (IncorrectCredentialsException e) {
                System.out.println("用户名密码不正确");
            } catch (LockedAccountException lae) {
                System.out.println("账户已被冻结！");
            } catch (AuthenticationException ae) {
                System.out.println(ae.getMessage());
            }
        }
        return ERROR;

    }

    @Action(value = "getUserMenus")
    public void getUserMenus() {
        String username = getModel().getUsername();
        BaseJsonResult<List<SysPermissions>> result;
        try {
            List<TreeNode> userMenus = userService.getUserMenus(username);

            result = ResultUtils.success(userMenus);
        } catch (Exception e) {
            result = ResultUtils.error(500, e.getMessage());
            e.printStackTrace();
        }
        printJson(result, null);
    }

    @Action(value = "geUserById")
    public void geUserById() {
        BaseJsonResult result;
        try {
            SysUser user  = userService.getUserById(getModel().getUserId());
            result= ResultUtils.success(user);
        } catch (Exception e) {
            result = ResultUtils.error(500 , e.getMessage());
            e.printStackTrace();
        }
        printJson(result, null);
    }


}
