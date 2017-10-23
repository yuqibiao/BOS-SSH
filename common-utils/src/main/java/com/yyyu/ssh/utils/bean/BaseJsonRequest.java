package com.yyyu.ssh.utils.bean;

/**
 * 功能：请求时的J送参数格式
 *
 * @author yu
 * @date 2017/10/23.
 */
public class BaseJsonRequest<T> {

    private String sessionId;//令牌
    private String timestamp;//请求时间戳
    private T data;//请求参数

    public BaseJsonRequest() {

    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
