package com.yyyu.service.test;

import com.yyyu.ssh.service.inter.IUserTokenService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/11/15.
 */
public class UserTokenTest extends BaseTest{

    @Autowired
    private IUserTokenService userTokenService;

    @Override
    public void initTest() {

    }

    @Test
    public void testGetToken(){
        userTokenService.getUserTokenByUserId(23L);
    }

}
