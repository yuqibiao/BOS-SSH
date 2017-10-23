package com.yyyu.ssh.utils.bean;

/**
 * 功能：返回的J送数据
 *
 * @author yu
 * @date 2017/10/23.
 */
public class BaseJsonResult<T> {

    private int code;//返回码 200:成功 500：失败
    private String msg;//返回信息，请求错误时返回错误码
    private T data;//返回数据，请求错误时返回空

    public BaseJsonResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
