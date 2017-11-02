package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.inter.IRoleDao;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.service.inter.IRoleService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/31.
 */
@Service
public class RoleSeviceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public void addRole(SysRole role) {
        roleDao.addRole(role);
    }

    @Override
    public void deleteRole(SysRole role) {
        roleDao.deleteRole(role);
    }

    @Override
    public Integer getRolesTotal() {
        return roleDao.getRolesTotal();
    }

    @Override
    public DetachedCriteria getCriteria() {
        return roleDao.getCriteria();
    }

    @Override
    public List<SysRole> getRoleByPage(DetachedCriteria criteria, Integer start, Integer length) {
        return roleDao.getRoleByPage(criteria, start, length);
    }

    @Override
    public void deleteRole(long roleId) {
        roleDao.delete(roleId);
    }

    @Override
    public void modifyRole(SysRole role) {
        roleDao.update(role);
    }

    @Override
    public SysRole getRoleById(Long roleId) {
        return roleDao.getById(roleId);
    }
}
