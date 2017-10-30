package com.yyyu.ssh.service;

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
    public List<SysPermissions> getUserMenus(String username) {
        return userDao.getUserMenus(username);
    }

    @Override
    public List<TreeNode> getAllPermissionByUserId(Long userId) {
        //1.得到所有的权限信息
        List<SysPermissions> allPermissions = permissionsDao.getAllPermissions();
        //2.得到用户对应的权限信息
        List<SysPermissions> userPermissions = userDao.getUserPermissions(userId);
        List<Long> userPerIds = new ArrayList<>();
        for (SysPermissions sysPermissions : userPermissions) {
            userPerIds.add(sysPermissions.getPerId());
        }
        //3.设置checked open
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (SysPermissions per : allPermissions) {
            TreeNode node = new TreeNode();
            Long perId = per.getPerId();
            node.setId(perId + "");
            node.setName(per.getName());
            Long perPid = per.getPerPid();
            if (perPid != null) {
                node.setpId(perPid + "");
                node.setOpen(true);
            } else {
                node.setpId(per.getPerId() + "");
                node.setOpen(false);
            }
            if (userPerIds.contains(perId)) {
                node.setChecked(true);
            } else {
                node.setChecked(false);
            }
            treeNodeList.add(node);
        }

        return treeNodeList;
    }


}
