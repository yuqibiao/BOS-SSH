package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.inter.IPermissionsDao;
import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.service.inter.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/31.
 */
@Service
public class PermissionServiceImpl implements IPermissionsService {

    @Autowired
    private IPermissionsDao permissionsDao;

    @Override
    public List<SysPermissions> getAllPermissions() {
        return permissionsDao.getAllPermissions();
    }

    @Override
    public void addPermission(SysPermissions permission) {
        permissionsDao.addPermission(permission);
    }

    @Override
    public void deletePermission(SysPermissions permission) {
        permissionsDao.deletePermission(permission);
    }

}
