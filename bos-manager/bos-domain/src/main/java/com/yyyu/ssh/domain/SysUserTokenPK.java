package com.yyyu.ssh.domain;

import java.io.Serializable;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/11/15.
 */
public class SysUserTokenPK implements Serializable {
    private long userId;
    private String userToken;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUserTokenPK that = (SysUserTokenPK) o;

        if (userId != that.userId) return false;
        if (userToken != null ? !userToken.equals(that.userToken) : that.userToken != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userToken != null ? userToken.hashCode() : 0);
        return result;
    }
}
