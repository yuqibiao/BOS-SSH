package com.yyyu.ssh.action;

import com.yyyu.ssh.dao.bean.SelectRole;
import com.yyyu.ssh.domain.SysUserRole;
import com.yyyu.ssh.service.inter.IUserRoleService;
import com.yyyu.ssh.templete.BaseAction;
import com.yyyu.ssh.utils.ResultUtils;
import com.yyyu.ssh.utils.bean.BaseJsonResult;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/11/4
 */
@Controller
@Scope("prototype")
@Namespace("/userRole")
public class UserRoleAction extends BaseAction<SysUserRole>{

    @Autowired
    private IUserRoleService userRoleService;

    @Action("getAllRoleByUserId")
    public void getAllRoleByUserId(){

        long userId = getModel().getUserId();
        BaseJsonResult result;
        try {
            List<SelectRole> roleList = userRoleService.getAllRoleByUserId(userId);
            result = ResultUtils.success(roleList);
        } catch (Exception e) {
            result = ResultUtils.error(500 , e.getMessage());
            e.printStackTrace();
        }
        printJson(result , null);

    }

}
