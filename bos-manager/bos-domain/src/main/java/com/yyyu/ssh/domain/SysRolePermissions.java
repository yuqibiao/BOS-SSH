package com.yyyu.ssh.domain;

import javax.persistence.*;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
@Entity
@Table(name = "sys_role_permissions", schema = "bos", catalog = "")
@IdClass(SysRolePermissionsPK.class)
public class SysRolePermissions {
    private long roleId;
    private long perId;

    @Id
    @Column(name = "role_id")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "per_id")
    public long getPerId() {
        return perId;
    }

    public void setPerId(long perId) {
        this.perId = perId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRolePermissions that = (SysRolePermissions) o;

        if (roleId != that.roleId) return false;
        if (perId != that.perId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (int) (perId ^ (perId >>> 32));
        return result;
    }
}
