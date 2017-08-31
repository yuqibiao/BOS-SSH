package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.inter.IUserDao;
import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.service.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：User service
 *
 * @author yu
 * @date 2017/8/30.
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Override
    public User get(String userId) {
        return userDao.getById(userId);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

}
