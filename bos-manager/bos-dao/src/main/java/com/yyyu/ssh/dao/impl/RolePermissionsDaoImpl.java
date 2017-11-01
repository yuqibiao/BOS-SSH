package com.yyyu.ssh.dao.impl;

import com.yyyu.ssh.dao.inter.IRolePermissionsDao;
import com.yyyu.ssh.domain.SysRolePermissions;
import com.yyyu.ssh.templete.BaseDaoImpl;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/31.
 */
@Repository
public class RolePermissionsDaoImpl extends BaseDaoImpl<SysRolePermissions> implements IRolePermissionsDao {

    @Override
    public void addRolePermissions(List<SysRolePermissions> rolePermissionsList) {
        for (SysRolePermissions rolePermissions : rolePermissionsList) {
            save(rolePermissions);
        }
    }

    @Override
    public void deleteBeforeAddNewRolePermissions(Long userId, List<SysRolePermissions> rolePermissionsList) {
        StringBuilder sbHql = new StringBuilder();
        sbHql.append("delete from SysRolePermissions where roleId=?");
        executeHql(sbHql.toString(), new Object[]{userId});
        addRolePermissions(rolePermissionsList);
    }

}
