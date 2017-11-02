package com.yyyu.ssh.service.inter;

import com.yyyu.ssh.domain.SysPermissions;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public interface IPermissionsService {


    List<SysPermissions> getAllPermissions();

    List<SysPermissions> getPermissionByPage(DetachedCriteria criteria , Integer start , Integer length);

    void addPermission(SysPermissions permission);

    void deletePermission(SysPermissions permission);

    Integer getPermissionTotal();

    DetachedCriteria getCriteria();

    void modifyPermission(SysPermissions permissions);

    SysPermissions getPermissionById(long perId);
}
