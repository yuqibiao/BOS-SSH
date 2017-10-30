package com.yyyu.ssh.domain;

import javax.persistence.*;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
@Entity
@Table(name = "sys_permissions", schema = "bos")
public class SysPermissions {
    private long perId;
    private Long perPid;
    private String name;
    private String description;
    private String code;
    private String page;
    private Byte available;
    private Short type;

    @Id
    @Column(name = "per_id")
    public long getPerId() {
        return perId;
    }

    public void setPerId(long perId) {
        this.perId = perId;
    }

    @Basic
    @Column(name = "per_pid")
    public Long getPerPid() {
        return perPid;
    }

    public void setPerPid(Long perPid) {
        this.perPid = perPid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "page")
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Basic
    @Column(name = "available")
    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SysPermissions{" +
                "perId=" + perId +
                ", perPid=" + perPid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", page='" + page + '\'' +
                ", available=" + available +
                ", type=" + type +
                '}';
    }
}
