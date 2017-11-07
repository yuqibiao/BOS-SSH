package com.yyyu.ssh.dao.impl;

import com.yyyu.ssh.dao.inter.IRolePermissionsDao;
import com.yyyu.ssh.domain.SysPermissions;
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
    public void deleteBeforeAddNewRolePermissions(Long roleId, List<SysRolePermissions> rolePermissionsList) {
        StringBuilder sbHql = new StringBuilder();
        sbHql.append("delete from SysRolePermissions where roleId=?");
        executeHql(sbHql.toString(), new Object[]{roleId});
        addRolePermissions(rolePermissionsList);
    }

    @Override
    public List<SysPermissions> getPermissionByRoleId(long roleId) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("select sys_permissions.*  ");
        sbSql.append("from sys_permissions , sys_role_permissions , sys_role ");
        sbSql.append("where sys_role_permissions.role_id=sys_role.role_id  and  sys_role_permissions.per_id=sys_permissions.per_id  ");
        sbSql.append("and  sys_role.role_id=? ");
        List<SysPermissions> permissionsList = getAllListBySql(sbSql.toString(), new Object[]{roleId}, SysPermissions.class);
        return permissionsList;
    }

}
