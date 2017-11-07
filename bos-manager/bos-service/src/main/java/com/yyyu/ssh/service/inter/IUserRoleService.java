package com.yyyu.ssh.service.inter;

import com.yyyu.ssh.dao.bean.SelectRole;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.domain.SysUserRole;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/11/4
 */
public interface IUserRoleService {

    List<SysRole> getRoleByUsername(String username);

    List<SelectRole> getAllRoleByUserId(long userId);

    void saveOrUpdateAll(long userId, List<SysUserRole> userRoleList);
}
