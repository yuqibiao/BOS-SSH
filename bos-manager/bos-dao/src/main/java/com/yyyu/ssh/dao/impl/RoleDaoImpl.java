package com.yyyu.ssh.dao.impl;

import com.yyyu.ssh.dao.bean.TreeNode;
import com.yyyu.ssh.dao.inter.IRoleDao;
import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.templete.BaseDaoImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<SysRole> implements IRoleDao {

    @Override
    public void addRole(SysRole role) {
        save(role);
    }

    @Override
    public void deleteRole(SysRole role) {
        delete(role);
    }

    @Override
    public Integer getRolesTotal() {
        return getTotalCount(getCriteria());
    }

    @Override
    public List<SysRole> getRoleByPage(DetachedCriteria criteria, Integer start, Integer length) {
        return getPageList(criteria, start, length);
    }

    @Override
    public List<SysPermissions> getRolePermissions(Long roleId) {
        StringBuilder sb = getPreSql();
        sb.append("and sys_role.role_id =?  ");
        String sql = sb.toString();
        return getAllListBySql(sql, new Long[]{roleId} , SysPermissions.class);
    }

    @Override
    public List<SysPermissions> getRoleOptions(long roleId) {
        StringBuilder sb = getPreSql().append("and sys_permissions.type =0 ");
        sb.append("and sys_role.role_id =?  ");
        String sql = sb.toString();
        return getAllListBySql(sql, new Long[]{roleId} , SysPermissions.class);
    }


    private StringBuilder getPreSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("select sys_permissions.* ");
        sb.append("from  sys_role , sys_role_permissions,sys_permissions ");
        sb.append("where sys_role.role_id=sys_role_permissions.role_id ");
        sb.append("and sys_role_permissions.per_id=sys_permissions.per_id ");
        return sb;
    }

}
