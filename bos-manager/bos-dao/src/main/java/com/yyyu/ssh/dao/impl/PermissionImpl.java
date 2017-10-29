package com.yyyu.ssh.dao.impl;

import com.yyyu.ssh.dao.inter.IPermissionsDao;
import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.templete.BaseDaoImpl;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public class PermissionImpl extends BaseDaoImpl<SysPermissions> implements IPermissionsDao{

    @Override
    public void createPermission(SysPermissions permission) {
        save(permission);
    }

    @Override
    public void deletePermission(SysPermissions permission) {
        delete(permission);
    }
}
