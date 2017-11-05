package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.bean.SelectRole;
import com.yyyu.ssh.dao.bean.TreeNode;
import com.yyyu.ssh.dao.inter.IPermissionsDao;
import com.yyyu.ssh.dao.inter.IUserDao;
import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.domain.SysUser;
import com.yyyu.ssh.service.inter.IUserService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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

    @Autowired
    private IPermissionsDao permissionsDao;

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
    public List<SysPermissions> getUserPermissions(String username) {
        return userDao.getUserOptions(username);
    }

    @Override
    public  List<TreeNode> getUserMenus(String username) {
        List<SysPermissions> userMenus = userDao.getUserMenus(username);
        List<TreeNode> nodeList = new ArrayList<>();
        for (SysPermissions permission :userMenus) {
            TreeNode node = new TreeNode();
            node.setName(permission.getName());
            node.setId(""+permission.getPerId());
            node.setpId(""+permission.getPerPid());
            node.setPage(permission.getPage());
            nodeList.add(node);
        }
        return nodeList;
    }

    @Override
    public void modifyUser(SysUser user) {
        userDao.update(user);
    }

    @Override
    public boolean hasUser(String username) {
        SysUser user = userDao.getUserByUsername(username);
        return user!=null;
    }

    @Override
    public void deleteUserById(Long userId) {
         userDao.delete(userId);
    }

    @Override
    public List<SelectRole> getRoleByUserId(long userId) {

        return null;
    }


}
