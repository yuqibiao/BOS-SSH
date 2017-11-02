package com.yyyu.ssh.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/10/29
 */
@Entity
@Table(name = "sys_user", schema = "bos")
public class SysUser {
    private long userId;
    private String username;
    private String password;
    private String salt;
    private Byte locked;
    private Integer salary;
    private Timestamp birthday;
    private String gender;
    private String station;
    private String tel;
    private String remark;

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "locked")
    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    @Basic
    @Column(name = "salary")
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "birthday")
    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "station")
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUser sysUser = (SysUser) o;

        if (userId != sysUser.userId) return false;
        if (username != null ? !username.equals(sysUser.username) : sysUser.username != null) return false;
        if (password != null ? !password.equals(sysUser.password) : sysUser.password != null) return false;
        if (salt != null ? !salt.equals(sysUser.salt) : sysUser.salt != null) return false;
        if (locked != null ? !locked.equals(sysUser.locked) : sysUser.locked != null) return false;
        if (salary != null ? !salary.equals(sysUser.salary) : sysUser.salary != null) return false;
        if (birthday != null ? !birthday.equals(sysUser.birthday) : sysUser.birthday != null) return false;
        if (gender != null ? !gender.equals(sysUser.gender) : sysUser.gender != null) return false;
        if (station != null ? !station.equals(sysUser.station) : sysUser.station != null) return false;
        if (tel != null ? !tel.equals(sysUser.tel) : sysUser.tel != null) return false;
        if (remark != null ? !remark.equals(sysUser.remark) : sysUser.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (locked != null ? locked.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
