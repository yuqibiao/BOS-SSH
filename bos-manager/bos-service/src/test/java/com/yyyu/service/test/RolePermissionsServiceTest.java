package com.yyyu.service.test;

import com.yyyu.ssh.domain.SysRolePermissions;
import com.yyyu.ssh.service.inter.IRolePermissionsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/31.
 */
public class RolePermissionsServiceTest extends BaseTest{

    @Autowired
    private IRolePermissionsService rolePermissionsService;

    @Override
    public void initTest() {
        
    }

    @Test
    public void testDeleteBeforeAddNewRolePermissions(){

        Long roleId=1L;
        List<SysRolePermissions> sysRolePermissionsList = new ArrayList<>();
        SysRolePermissions sysRolePermissions1 = new SysRolePermissions(roleId , 1);
        SysRolePermissions sysRolePermissions2 = new SysRolePermissions(roleId , 2);
        SysRolePermissions sysRolePermissions3 = new SysRolePermissions(roleId , 3);
        SysRolePermissions sysRolePermissions4 = new SysRolePermissions(roleId , 4);
        SysRolePermissions sysRolePermissions5 = new SysRolePermissions(roleId , 5);
        sysRolePermissionsList.add(sysRolePermissions1);
        sysRolePermissionsList.add(sysRolePermissions2);
        sysRolePermissionsList.add(sysRolePermissions3);
        sysRolePermissionsList.add(sysRolePermissions4);
        sysRolePermissionsList.add(sysRolePermissions5);

        rolePermissionsService.deleteBeforeAddNewRolePermissions(roleId , sysRolePermissionsList);

    }

}
