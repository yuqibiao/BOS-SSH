package com.yyyu.ssh.dao.impl;

import com.yyyu.ssh.dao.inter.IUserRoleDao;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.domain.SysUserRole;
import com.yyyu.ssh.templete.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/11/4
 */
@Repository
public class UserRoleDaoImpl extends BaseDaoImpl<SysUserRole> implements IUserRoleDao {

    @Override
    public List<SysRole> getRoleByUserId(long userId) {

        StringBuilder sbSql = new StringBuilder();
        sbSql.append("select sys_role.* ");
        sbSql.append("from sys_role , sys_user_role ");
        sbSql.append("where sys_user_role.role_id=sys_role.role_id  ");
        sbSql.append("and sys_user_role.user_id=? ");
        List<SysRole> roleList = getAllListBySql(sbSql.toString(), new Object[]{userId}, SysRole.class);

        return roleList;
    }

    @Override
    public void deleteRoleByUserId(long userId) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("delete from SysUserRole ");
        sbSql.append("where userId=? ");
        executeHql(sbSql.toString() , new Object[]{userId});
    }

    @Override
    public List<SysRole> getRoleCodeByUsername(String username) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("select sys_role.* ");
        sbSql.append("from sys_user , sys_user_role ,sys_role  ");
        sbSql.append("where sys_user_role.user_id=sys_user.user_id  and sys_user_role.role_id= sys_role.role_id  ");
        sbSql.append("and sys_user.username=?  ");
        List<SysRole>  roleCodeList = getAllListBySql(sbSql.toString(), new Object[]{username}, SysRole.class);
        return roleCodeList;
    }


}
