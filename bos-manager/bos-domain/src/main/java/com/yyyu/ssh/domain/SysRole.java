package com.yyyu.ssh.domain;

import javax.persistence.*;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
@Entity
@Table(name = "sys_role", schema = "bos")
public class SysRole {
    private long roleId;
    private String roleName;
    private String description;
    private String code;
    private Byte available;

    @Id
    @Column(name = "role_id")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "available")
    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRole sysRole = (SysRole) o;

        if (roleId != sysRole.roleId) return false;
        if (roleName != null ? !roleName.equals(sysRole.roleName) : sysRole.roleName != null) return false;
        if (description != null ? !description.equals(sysRole.description) : sysRole.description != null) return false;
        if (code != null ? !code.equals(sysRole.code) : sysRole.code != null) return false;
        if (available != null ? !available.equals(sysRole.available) : sysRole.available != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        return result;
    }
}
