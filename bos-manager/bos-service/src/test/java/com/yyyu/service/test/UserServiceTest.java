package com.yyyu.service.test;

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
        System.out.println("======admin"+admin);
    }
}
