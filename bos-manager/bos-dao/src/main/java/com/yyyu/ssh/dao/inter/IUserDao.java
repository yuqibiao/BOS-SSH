package com.yyyu.ssh.dao.inter;

import com.yyyu.ssh.domain.SysUser;
import com.yyyu.ssh.templete.inter.IBaseDao;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 功能：User Dao 接口
 *
 * @author yu
 * @date 2017/8/30.
 */
public interface IUserDao extends IBaseDao<SysUser> {

    SysUser getUserByUsername(String username);

    SysUser getUserByUsernameAndPwd(String username, String password);

    Integer getUsersTotal();

    List<SysUser> getUserListByPage(DetachedCriteria criteria, Integer page, Integer size);

    List<String> getUserRoleName(String username);

    List<String>getUserPermissions(String username);

    List<String> getUserMenus(String username);

}
