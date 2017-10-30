package com.yyyu.service.test;

import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.service.inter.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/26
 */
public class UserServiceTest extends BaseTest{

    @Autowired
    private IUserService userService;

    @Override
    public void initTest() {

    }

    @Test
    public void testGetRole(){
        List<String> admin = userService.getUserRoleName("admin");
        System.out.println("======"+admin.get(0));
    }

    @Test
    public void testGetPermissions(){
        List<SysPermissions> permissions = userService.getUserPermissions("admin");
        for (SysPermissions permission: permissions) {
            System.out.println(""+permission);
        }

    }

    @Test
    public void testGetMenus(){
        List<SysPermissions> permissions = userService.getUserMenus("yu");

        System.out.println(""+permissions);

    }

}
