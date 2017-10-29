package com.yyyu.ssh.dao.inter;

import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.templete.inter.IBaseDao;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public interface IRoleDao extends IBaseDao<SysRole> {

    void createRole(SysRole role);

    void deleteRole(SysRole role);

}
