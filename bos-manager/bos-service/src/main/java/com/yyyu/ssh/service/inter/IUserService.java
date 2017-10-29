package com.yyyu.ssh.service.inter;

import com.yyyu.ssh.domain.SysUser;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/8/30.
 */
public interface IUserService {

    DetachedCriteria getCriteria();

    SysUser getUserById(Long userId);

    void save(SysUser SysUser);

    SysUser getUserByUsername(String SysUsername);

    SysUser getUserByUsernameAndPwd(String SysUsername, String password);

    Integer getUsersTotal();

    List<SysUser> getUserByPage(DetachedCriteria criteria, Integer start, Integer length);

    List<String> getUserRoleName(String SysUsername);

    List<String>getUserPermissions(String username);

    List<String> getUserMenus(String username);

}
