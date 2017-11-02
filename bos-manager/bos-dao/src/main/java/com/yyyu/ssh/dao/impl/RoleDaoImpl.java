package com.yyyu.ssh.dao.impl;

import com.yyyu.ssh.dao.inter.IRoleDao;
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
}
