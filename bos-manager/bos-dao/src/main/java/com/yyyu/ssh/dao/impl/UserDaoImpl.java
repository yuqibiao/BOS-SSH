package com.yyyu.ssh.dao.impl;

import com.yyyu.ssh.dao.inter.IUserDao;
import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.domain.SysUser;
import com.yyyu.ssh.templete.BaseDaoImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能：用户相关Dao操作
 *
 * @author yu
 * @date 2017/8/30.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<SysUser> implements IUserDao {
    @Override
    public SysUser getUserByUsername(String username) {
        DetachedCriteria criteria = getCriteria();
        criteria.add(Restrictions.eq("username", username));
        List<SysUser> userList = getPageList(criteria, 0, 1);
        return fetchOne(userList);
    }

    @Override
    public SysUser getUserByUsernameAndPwd(String username, String password) {
        DetachedCriteria criteria = getCriteria();
        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("password", password));
        List<SysUser> userList = getPageList(criteria, 0, 1);
        return fetchOne(userList);
    }

    private SysUser fetchOne(List<SysUser> userList) {
        if (userList != null && userList.size() > 0) {
            SysUser user = userList.get(0);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public Integer getUsersTotal() {
        return getTotalCount(getCriteria());
    }

    @Override
    public List<SysUser> getUserListByPage(DetachedCriteria criteria, Integer start, Integer length) {
        return getPageList(criteria, start, length);
    }

    @Override
    public List<String> getUserRoleName(String username) {
        StringBuilder sb = new StringBuilder();
        sb.append("select sys_role.role_name ");
        sb.append("from sys_user , sys_user_role , sys_role ");
        sb.append("where sys_user.user_id = sys_user_role.user_id ");
        sb.append("and sys_user_role.role_id=sys_role.role_id ");
        sb.append("and sys_user.username =?  ");
        String sql = sb.toString();
        return getAllListBySql(sql, new String[]{username} , null);
    }

    @Override
    public List<SysPermissions> getUserPermissions(Long userId) {
        StringBuilder sb = getPreSql();//.append("and sys_permissions.type =1  ");
        sb.append("and sys_user.user_id =?  ");
        String sql = sb.toString();
        return getAllListBySql(sql, new Long[]{userId} , SysPermissions.class);
    }

    @Override
    public List<SysPermissions> getUserOptions(String username) {
        StringBuilder sb = getPreSql();//.append("and sys_permissions.type =1  ");
        sb.append("and sys_user.username =?  ");
        String sql = sb.toString();
        return getAllListBySql(sql, new String[]{username} , SysPermissions.class);
    }

    @Override
    public List<SysPermissions> getUserMenus(String username) {
        StringBuilder sb = getPreSql().append("and sys_permissions.type =0 ");
        sb.append("and sys_user.username =?  ");
        String sql = sb.toString();
        return getAllListBySql(sql, new String[]{username} , SysPermissions.class);
    }

    private StringBuilder getPreSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("select sys_permissions.* ");
        sb.append("from sys_user,sys_user_role , sys_role , sys_role_permissions,sys_permissions ");
        sb.append("where sys_user.user_id = sys_user_role.user_id ");
        sb.append("and sys_user_role.role_id=sys_role.role_id ");
        sb.append("and sys_role.role_id=sys_role_permissions.role_id ");
        sb.append("and sys_role_permissions.per_id=sys_permissions.per_id ");
        return sb;
    }

}
