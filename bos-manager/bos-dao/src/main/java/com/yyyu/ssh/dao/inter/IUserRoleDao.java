package com.yyyu.ssh.dao.inter;

import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.domain.SysUserRole;
import com.yyyu.ssh.templete.inter.IBaseDao;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/11/4
 */
public interface IUserRoleDao extends IBaseDao<SysUserRole>{

    List<SysRole> getRoleByUserId(long userId);

    void deleteRoleByUserId(long userId);
}
