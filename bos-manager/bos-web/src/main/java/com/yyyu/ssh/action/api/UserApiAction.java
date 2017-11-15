package com.yyyu.ssh.action.api;

import com.yyyu.ssh.dao.bean.ApiLoginReturn;
import com.yyyu.ssh.dao.bean.UserReturn;
import com.yyyu.ssh.domain.SysUser;
import com.yyyu.ssh.domain.SysUserToken;
import com.yyyu.ssh.service.inter.IUserService;
import com.yyyu.ssh.service.inter.IUserTokenService;
import com.yyyu.ssh.templete.BaseAction;
import com.yyyu.ssh.utils.ResultUtils;
import com.yyyu.ssh.utils.bean.BaseJsonResult;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * 功能：无状态UserAction
 *
 * @author yu
 * @date 2017/11/15.
 */
@Controller
@Scope("prototype")
@Namespace("/api/user")
public class UserApiAction extends BaseAction<SysUser>{

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserTokenService userTokenService;

    Logger logger = Logger.getLogger(UserApiAction.class);

    @Action(value = "login")
    public void login(){
        BaseJsonResult result = new BaseJsonResult<>();
        String username = getModel().getUsername();
        String pwd = getModel().getPassword();

        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
            token.setRememberMe(false);
            try {
                currentUser.login(token);
                SysUser sysUser = userService.getUserByUsername(username);
                SysUserToken userToken = new SysUserToken();
                userToken.setUserId(sysUser.getUserId());
                //生成token
                // TODO 改变token生成策略
                String tokenDigest = UUID.randomUUID().toString();
                userToken.setUserToken(tokenDigest);
                userTokenService.saveOrUpdate(userToken);
                ApiLoginReturn apiLoginReturn = new ApiLoginReturn();
                UserReturn userReturn = new UserReturn();
                userReturn.setUserId(sysUser.getUserId());
                userReturn.setUsername(sysUser.getUsername());
                userReturn.setSalary(sysUser.getSalary());
                userReturn.setBirthday(sysUser.getBirthday());
                userReturn.setGender(sysUser.getGender());
                userReturn.setRemark(sysUser.getRemark());
                userReturn.setStation(sysUser.getStation());
                userReturn.setTel(sysUser.getTel());
                apiLoginReturn.setUserInfo(userReturn);
                apiLoginReturn.setToken(tokenDigest);
                result = ResultUtils.success(apiLoginReturn);
            } catch (IncorrectCredentialsException e) {
                result = ResultUtils.error(501, "用户名或密码错误");
            } catch (LockedAccountException lae) {
                result = ResultUtils.error(502, "账户已被冻结");
            } catch (AuthenticationException ae) {
                result = ResultUtils.error(501, "用户名或密码错误");
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        printJson(result , null);
    }

    @Action("getUserInfo")
    public void getUserInfo(){
        BaseJsonResult<UserReturn> result;
        Long userId = getModel().getUserId();
        try {
            Subject currentUser = SecurityUtils.getSubject();
            boolean hasRole = currentUser.hasRole("role:admin");
            if (!hasRole){
                result = ResultUtils.error(501 , "没有权限");
            }else{
                SysUser sysUser = userService.getUserById(userId);
                UserReturn userReturn = new UserReturn();
                userReturn.setUserId(sysUser.getUserId());
                userReturn.setUsername(sysUser.getUsername());
                userReturn.setSalary(sysUser.getSalary());
                userReturn.setBirthday(sysUser.getBirthday());
                userReturn.setGender(sysUser.getGender());
                userReturn.setRemark(sysUser.getRemark());
                userReturn.setStation(sysUser.getStation());
                userReturn.setTel(sysUser.getTel());
                result = ResultUtils.success(userReturn);
            }
        } catch (Exception e) {
            result = ResultUtils.error(500 , ""+e.getMessage());
            e.printStackTrace();
        }
        printJson(result , null);
    }

    @RequiresRoles("admin")
    private void get(){
        System.out.println("===============get");
    }

}
