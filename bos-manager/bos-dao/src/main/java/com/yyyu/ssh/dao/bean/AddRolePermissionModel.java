package com.yyyu.ssh.dao.bean;

import com.yyyu.ssh.domain.SysRolePermissions;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/11/3
 */
public class AddRolePermissionModel {

    private long roleId;
    private String  perIdListJson;

    public AddRolePermissionModel() {
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getPerIdListJson() {
        return perIdListJson;
    }

    public void setPerIdListJson(String perIdListJson) {
        this.perIdListJson = perIdListJson;
    }
}
