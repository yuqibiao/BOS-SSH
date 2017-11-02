package com.yyyu.ssh.service.inter;

import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.templete.inter.IBaseDao;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public interface IRoleService {

    void addRole(SysRole role);

    void deleteRole(SysRole role);

    Integer getRolesTotal();

    DetachedCriteria getCriteria();

    List<SysRole> getRoleByPage(DetachedCriteria criteria, Integer start, Integer length);

    void deleteRole(long roleId);

    void modifyRole(SysRole role);

    SysRole getRoleById(Long roleId);
}
