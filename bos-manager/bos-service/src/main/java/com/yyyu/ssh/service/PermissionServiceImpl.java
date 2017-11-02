package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.inter.IPermissionsDao;
import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.service.inter.IPermissionsService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/31.
 */
@Service
public class PermissionServiceImpl implements IPermissionsService {

    @Autowired
    private IPermissionsDao permissionsDao;

    @Override
    public List<SysPermissions> getAllPermissions() {
        return permissionsDao.getAllPermissions();
    }

    @Override
    public List<SysPermissions> getPermissionByPage(DetachedCriteria criteria , Integer start, Integer length) {
        return permissionsDao.getPermissionByPage(criteria ,start , length);
    }

    @Override
    public void addPermission(SysPermissions permission) {
        permissionsDao.addPermission(permission);
    }

    @Override
    public void deletePermission(SysPermissions permission) {
        permissionsDao.deletePermission(permission);
    }

    @Override
    public Integer getPermissionTotal() {
        return  permissionsDao.getPermissionTotal();
    }

    @Override
    public DetachedCriteria getCriteria() {
        return getCriteria();
    }

    @Override
    public void modifyPermission(SysPermissions permissions) {
        permissionsDao.update(permissions);
    }

    @Override
    public SysPermissions getPermissionById(long perId) {
         return permissionsDao.getById(perId);
    }

}
