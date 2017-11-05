package com.yyyu.ssh.action;

import com.yyyu.ssh.dao.bean.AddRolePermissionModel;
import com.yyyu.ssh.domain.SysRolePermissions;
import com.yyyu.ssh.service.inter.IRolePermissionsService;
import com.yyyu.ssh.templete.BaseAction;
import com.yyyu.ssh.utils.ResultUtils;
import com.yyyu.ssh.utils.bean.BaseJsonResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/11/3
 */
@Scope("prototype")
@Controller
@Namespace("/rolePermission")
public class RolePermissionAction extends BaseAction<AddRolePermissionModel>{

    @Autowired
    private IRolePermissionsService rolePermissionsService;

    @Action("addPermission2Role")
    public void addPermission2Role(){
        BaseJsonResult result;
        try {
            long roleId = getModel().getRoleId();
            JSONArray jsonArray = JSONArray.fromObject(getModel().getPerIdListJson());
            List<Long> perIdList = (List<Long>) JSONArray.toCollection(jsonArray , Long.class);
            List<SysRolePermissions> rolePermissionsList = new ArrayList<>();
            for (Long perId:perIdList) {
                SysRolePermissions rolePermissions = new SysRolePermissions();
                rolePermissions.setRoleId(roleId);
                rolePermissions.setPerId(perId);
                rolePermissionsList.add(rolePermissions);
            }
            rolePermissionsService.deleteBeforeAddNewRolePermissions(roleId , rolePermissionsList);
            result = ResultUtils.success("添加成功");
        } catch (Exception e) {
            result = ResultUtils.error(500, e.getMessage());
            e.printStackTrace();
        }
        printJson(result , null);
    }


}
