package com.yyyu.ssh.utils;

import com.yyyu.ssh.utils.bean.BaseJsonResult;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/23.
 */
public class ResultUtils {

    /**
     * 返回成功
     *
     * @param t
     * @param <T>
     * @param msg
     * @return
     */
    public static <T>BaseJsonResult success(String msg , T t){
        BaseJsonResult<T> result = new BaseJsonResult<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(t);
        return result;
    }

    public static <T>BaseJsonResult success(T t){
        return success("成功" ,t);
    }

    public static BaseJsonResult success(String msg){
        return success(msg , null);
    }

    public static BaseJsonResult success(){
        return success(null);
    }

    /**
     * 返回异常
     *
     * @param code
     * @param msg
     * @return
     */
    public static  BaseJsonResult error(Integer code ,String msg){
        BaseJsonResult<Object>  result = new BaseJsonResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
