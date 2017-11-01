package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.inter.IRoleDao;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.service.inter.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
