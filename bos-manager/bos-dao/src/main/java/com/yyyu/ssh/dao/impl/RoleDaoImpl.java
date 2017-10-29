package com.yyyu.ssh.dao.impl;

import com.yyyu.ssh.dao.inter.IRoleDao;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.templete.BaseDaoImpl;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public class RoleDaoImpl extends BaseDaoImpl<SysRole> implements IRoleDao {

    @Override
    public void createRole(SysRole role) {
        save(role);
    }

    @Override
    public void deleteRole(SysRole role) {
        delete(role);
    }
}