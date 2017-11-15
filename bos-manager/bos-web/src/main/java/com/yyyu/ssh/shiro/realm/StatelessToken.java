package com.yyyu.ssh.shiro.realm;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * 功能：无状态Token
 *
 * @author yu
 * @date 2017/11/14.
 */
public class StatelessToken implements AuthenticationToken{

    private String userId;
    private Map<String , ?> params;
    private String clientDigest;

    public StatelessToken(String userId, Map<String, ?> params, String clientDigest) {
        this.userId = userId;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }
}
