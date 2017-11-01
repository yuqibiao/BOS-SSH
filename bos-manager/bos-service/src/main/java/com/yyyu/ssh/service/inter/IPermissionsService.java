package com.yyyu.ssh.service.inter;

import com.yyyu.ssh.domain.SysPermissions;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public interface IPermissionsService {


    List<SysPermissions> getAllPermissions();

    void addPermission(SysPermissions permission);

    void deletePermission(SysPermissions permission);

}
