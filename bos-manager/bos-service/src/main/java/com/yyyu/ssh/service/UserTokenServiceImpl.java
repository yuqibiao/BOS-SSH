package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.inter.IUserTokenDao;
import com.yyyu.ssh.domain.SysUserToken;
import com.yyyu.ssh.service.inter.IUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/11/15.
 */
@Service
public class UserTokenServiceImpl implements IUserTokenService{

    @Autowired
    private IUserTokenDao userTokenDao;

    @Override
    public void save(SysUserToken sysUserToken) {
        userTokenDao.save(sysUserToken);
    }

    @Override
    public void saveOrUpdate(SysUserToken sysUserToken) {
        userTokenDao.saveOrUpdate(sysUserToken);
    }

    @Override
    public SysUserToken getUserTokenByUserId(Long userId) {
        return userTokenDao.getTokenByUserId(userId);
    }
}
