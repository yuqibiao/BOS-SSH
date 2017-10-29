package com.yyyu.ssh.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
public class SysRolePermissionsPK implements Serializable {
    private long roleId;
    private long perId;

    @Column(name = "role_id")
    @Id
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "per_id")
    @Id
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

        SysRolePermissionsPK that = (SysRolePermissionsPK) o;

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
