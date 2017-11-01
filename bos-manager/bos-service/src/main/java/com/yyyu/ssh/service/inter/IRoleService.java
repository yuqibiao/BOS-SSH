package com.yyyu.ssh.service.inter;

import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.templete.inter.IBaseDao;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public interface IRoleService {

    void addRole(SysRole role);

    void deleteRole(SysRole role);

}
