package com.yyyu.ssh.dao.bean;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/11/15.
 */
public class ApiLoginReturn {

    private UserReturn userInfo;

    private String token;

    public UserReturn getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserReturn userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
