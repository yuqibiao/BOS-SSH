package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.inter.IUserDao;
import com.yyyu.ssh.domain.SysUser;
import com.yyyu.ssh.service.inter.IUserService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：User service
 *
 * @author yu
 * @date 2017/8/30.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public DetachedCriteria getCriteria() {
        return userDao.getCriteria();
    }

    @Override
    public SysUser getUserById(Long userId) {
        return userDao.getById(userId);
    }

    @Override
    public void save(SysUser user) {
        userDao.save(user);
    }

    @Override
    public SysUser getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public SysUser getUserByUsernameAndPwd(String username, String password) {
        return userDao.getUserByUsernameAndPwd(username, password);
    }

    @Override
    public Integer getUsersTotal() {
        return userDao.getUsersTotal();
    }

    @Override
    public List<SysUser> getUserByPage(DetachedCriteria criteria, Integer start, Integer length) {
        return userDao.getUserListByPage(criteria, start, length);
    }

    @Override
    public List<String> getUserRoleName(String username) {
        return userDao.getUserRoleName(username);
    }

    @Override
    public List<String> getUserPermissions(String username) {
        return userDao.getUserPermissions(username);
    }

    @Override
    public List<String> getUserMenus(String username) {
        return userDao.getUserMenus(username);
    }


}
