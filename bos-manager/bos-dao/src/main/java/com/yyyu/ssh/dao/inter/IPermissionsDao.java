package com.yyyu.ssh.dao.inter;

import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.templete.inter.IBaseDao;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public interface IPermissionsDao extends IBaseDao<SysPermissions>{


    List<SysPermissions> getAllPermissions();

    void addPermission(SysPermissions permission);

    void deletePermission(SysPermissions permission);

    List<SysPermissions> getPermissionByPage(DetachedCriteria criteria , Integer start, Integer length);

    Integer getPermissionTotal();

}
