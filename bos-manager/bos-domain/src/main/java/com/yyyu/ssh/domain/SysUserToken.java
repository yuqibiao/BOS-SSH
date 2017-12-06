package com.yyyu.ssh.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/11/15.
 */
public class SysUserToken implements Serializable {
    private long userId;
    private String userToken;
    private Timestamp createTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUserToken userToken1 = (SysUserToken) o;

        if (userId != userToken1.userId) return false;
        if (userToken != null ? !userToken.equals(userToken1.userToken) : userToken1.userToken != null) return false;
        if (createTime != null ? !createTime.equals(userToken1.createTime) : userToken1.createTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userToken != null ? userToken.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
