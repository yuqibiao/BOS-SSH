package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.bean.SelectRole;
import com.yyyu.ssh.dao.inter.IRoleDao;
import com.yyyu.ssh.dao.inter.IUserRoleDao;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.domain.SysUserRole;
import com.yyyu.ssh.service.inter.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/11/4
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService{

    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IUserRoleDao userRoleDao;

    @Override
    public List<SysRole> getRoleByUsername(String username) {
        return userRoleDao.getRoleCodeByUsername(username);
    }

    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return userRoleDao.getRoleByUserId(userId);
    }

    @Override
    public List<SelectRole> getAllRoleByUserId(long userId) {

        List<SysRole> allRole = roleDao.getAllList(roleDao.getCriteria());
        List<SysRole> checkedRoleList = userRoleDao.getRoleByUserId(userId);

        List<SelectRole> result = new ArrayList<>();

        List<Long> checkedIdList = new ArrayList<>();
        for (SysRole sysRole:checkedRoleList) {
            checkedIdList.add(sysRole.getRoleId());
        }

        for (SysRole role :allRole) {
            SelectRole selectRole = new SelectRole();
            selectRole.setRoleId(role.getRoleId());
            selectRole.setRoleName(role.getRoleName());
            selectRole.setDescription(role.getDescription());
            selectRole.setCode(role.getCode());
            selectRole.setAvailable(role.getAvailable());
            if(checkedIdList.contains(role.getRoleId())){
                selectRole.setChecked(true);
            }else{
                selectRole.setChecked(false);
            }
            result.add(selectRole);
        }

        return result;
    }

    @Override
    public void saveOrUpdateAll(long userId, List<SysUserRole> userRoleList) {
        userRoleDao.deleteRoleByUserId(userId);
        userRoleDao.saveAll(userRoleList);
    }

}
