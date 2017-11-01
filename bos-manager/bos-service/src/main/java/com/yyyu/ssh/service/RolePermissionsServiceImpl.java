package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.inter.IRolePermissionsDao;
import com.yyyu.ssh.domain.SysRolePermissions;
import com.yyyu.ssh.service.inter.IRolePermissionsService;
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
public class RolePermissionsServiceImpl implements IRolePermissionsService{

    @Autowired
    private IRolePermissionsDao permissionsDao;

    @Override
    public void addRolePermissions(List<SysRolePermissions> rolePermissionsList) {
        permissionsDao.addRolePermissions(rolePermissionsList);
    }

    @Override
    public void deleteBeforeAddNewRolePermissions(Long userId, List<SysRolePermissions> rolePermissionsList) {
        permissionsDao.deleteBeforeAddNewRolePermissions(userId , rolePermissionsList);
    }
}
