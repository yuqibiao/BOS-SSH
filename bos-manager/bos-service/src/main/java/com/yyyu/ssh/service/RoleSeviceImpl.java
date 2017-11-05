package com.yyyu.ssh.service;

import com.yyyu.ssh.dao.bean.TreeNode;
import com.yyyu.ssh.dao.inter.IPermissionsDao;
import com.yyyu.ssh.dao.inter.IRoleDao;
import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.service.inter.IRoleService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/31.
 */
@Service
public class RoleSeviceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IPermissionsDao permissionsDao;

    @Override
    public void addRole(SysRole role) {
        roleDao.addRole(role);
    }

    @Override
    public void deleteRole(SysRole role) {
        roleDao.deleteRole(role);
    }

    @Override
    public Integer getRolesTotal() {
        return roleDao.getRolesTotal();
    }

    @Override
    public DetachedCriteria getCriteria() {
        return roleDao.getCriteria();
    }

    @Override
    public List<SysRole> getRoleByPage(DetachedCriteria criteria, Integer start, Integer length) {
        return roleDao.getRoleByPage(criteria, start, length);
    }

    @Override
    public void deleteRole(long roleId) {
        roleDao.delete(roleId);
    }

    @Override
    public void modifyRole(SysRole role) {
        roleDao.update(role);
    }

    @Override
    public SysRole getRoleById(Long roleId) {
        return roleDao.getById(roleId);
    }

    @Override
    public List<TreeNode> getAllPermissionByRoleId(Long roleId) {
        //1.得到所有的权限信息
        List<SysPermissions> allPermissions = permissionsDao.getAllPermissions();
        //2.得到用户对应的权限信息
        List<SysPermissions> userPermissions = roleDao.getRolePermissions(roleId);
        List<Long> userPerIds = new ArrayList<>();
        for (SysPermissions sysPermissions : userPermissions) {
            userPerIds.add(sysPermissions.getPerId());
        }
        //3.设置checked open
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (SysPermissions per : allPermissions) {
            TreeNode node = new TreeNode();
            Long perId = per.getPerId();
            node.setId(perId + "");
            node.setName(per.getName());
            Long perPid = per.getPerPid();//pid
            if (perPid != null) {//有父节点
                node.setpId(perPid + "");
                node.setOpen(false);
            } else {//没有父节点
                node.setpId(per.getPerId() + "");
                node.setOpen(true);
                node.setIsParent(true);
            }
            if (userPerIds.contains(perId)) {//用户有该权限
                node.setChecked(true);
            } else {
                node.setChecked(false);
            }
            treeNodeList.add(node);
        }

        return treeNodeList;
    }

    @Override
    public List<SysRole> getAllRole() {
        return roleDao.getAllList(getCriteria());
    }
}
