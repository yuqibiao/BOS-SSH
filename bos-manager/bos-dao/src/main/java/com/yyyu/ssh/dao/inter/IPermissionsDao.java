package com.yyyu.ssh.dao.inter;

import com.yyyu.ssh.domain.SysPermissions;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public interface IPermissionsDao {

    void createPermission(SysPermissions permission);

    void deletePermission(SysPermissions permission);

}
