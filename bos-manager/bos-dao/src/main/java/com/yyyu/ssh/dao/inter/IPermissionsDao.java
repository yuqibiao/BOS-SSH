package com.yyyu.ssh.dao.inter;

import com.yyyu.ssh.domain.SysPermissions;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public interface IPermissionsDao {


    List<SysPermissions> getAllPermissions();

    void createPermission(SysPermissions permission);

    void deletePermission(SysPermissions permission);

}
