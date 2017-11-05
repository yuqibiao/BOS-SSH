package com.yyyu.ssh.dao.inter;

import com.yyyu.ssh.domain.SysRolePermissions;
import com.yyyu.ssh.templete.BaseDaoImpl;
import com.yyyu.ssh.templete.inter.IBaseDao;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/31.
 */
public interface IRolePermissionsDao extends IBaseDao<SysRolePermissions> {

     void addRolePermissions(List<SysRolePermissions> rolePermissionsList);

     void deleteBeforeAddNewRolePermissions(Long roleId , List<SysRolePermissions> rolePermissionsList);

}
