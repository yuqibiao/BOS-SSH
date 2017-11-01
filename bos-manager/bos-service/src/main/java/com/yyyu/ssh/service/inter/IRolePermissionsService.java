package com.yyyu.ssh.service.inter;

import com.yyyu.ssh.domain.SysRolePermissions;
import com.yyyu.ssh.templete.inter.IBaseDao;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/31.
 */
public interface IRolePermissionsService{

     void addRolePermissions(List<SysRolePermissions> rolePermissionsList);

     void deleteBeforeAddNewRolePermissions(Long userId, List<SysRolePermissions> rolePermissionsList);

}
