package com.yyyu.ssh.dao.impl;

import com.yyyu.ssh.dao.inter.IPermissionsDao;
import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.templete.BaseDaoImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
@Repository
public class PermissionImpl extends BaseDaoImpl<SysPermissions> implements IPermissionsDao {

    @Override
    public List<SysPermissions> getAllPermissions() {
        DetachedCriteria criteria = getCriteria();
        //criteria.add(Restrictions.eq("type" , 0));
        return getAllList(criteria);
    }

    @Override
    public void addPermission(SysPermissions permission) {
        save(permission);
    }

    @Override
    public void deletePermission(SysPermissions permission) {
        delete(permission);
    }
}
